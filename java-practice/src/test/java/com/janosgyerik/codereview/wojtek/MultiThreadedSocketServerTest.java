package com.janosgyerik.codereview.wojtek;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

class ErrorListener {

	public void error(Exception e) {

	}

	public void warning(Exception e) {

	}

	public void error(String s) {
	}

	public void info(String s) {

	}

	public void warning(String s) {

	}
}

class TrafficHandlerFactory {

	public TrafficHandler create(InputStream inputStream, OutputStream outputStream) {
		return null;
	}
}

class NamedThreadFactory implements ThreadFactory {

	public NamedThreadFactory(String simpleName) {

	}

	@Override
	public Thread newThread(Runnable r) {
		return null;
	}
}

class TrafficHandler {

	public void handle() {
	}
}

class MultiThreadedSocketServer {
	private final int listeningPort;
	private final ErrorListener errorListener;
	private final TrafficHandlerFactory trafficHandlerFactory;

	private final AtomicReference<ServerSocket> serverSocket = new AtomicReference<>();
	private final AtomicReference<ExecutorService> executorService = new AtomicReference<>();
	private volatile int poisonLocalPort;
	private final InetAddress localPoisonAddress;

	public MultiThreadedSocketServer(int listeningPort, ErrorListener errorListener, TrafficHandlerFactory trafficHandlerFactory) {
		this.listeningPort = listeningPort;
		this.errorListener = errorListener;
		this.trafficHandlerFactory = trafficHandlerFactory;
		try {
			this.localPoisonAddress = InetAddress.getByName("localhost");
		} catch (UnknownHostException e) {
			errorListener.error(e);
			throw new RuntimeException(e);
		}
	}

	public void start() {
		executorService.set(Executors.newFixedThreadPool(10, new NamedThreadFactory(getClass().getSimpleName())));

		try {
			this.serverSocket.set(new ServerSocket(listeningPort, 0, InetAddress.getByName("127.0.0.1")));
		} catch (IOException e) {
			errorListener.error(e);
			return;
		}

		executorService.get().submit(new Runnable() {
			public void run() {
				while (!executorService.get().isShutdown()) {
					try {
						final Socket clientSocket = serverSocket.get().accept();
						InetSocketAddress remoteSocketAddress = (InetSocketAddress) clientSocket.getRemoteSocketAddress();
						if (remoteSocketAddress.getPort() == poisonLocalPort && remoteSocketAddress.getAddress().equals(localPoisonAddress)) {
							errorListener.info("Port " + poisonLocalPort + " was a poison pill. Terminating the server.");
							clientSocket.close();
							return;
						} else {
							executorService.get().submit(new Runnable() {
								@Override
								public void run() {
									try {
										TrafficHandler trafficHandler = trafficHandlerFactory.create(clientSocket.getInputStream(), clientSocket.getOutputStream());
										trafficHandler.handle();
										clientSocket.close();
									} catch (IOException e) {
										errorListener.warning(e);
									}
								}
							});
						}
					} catch (IOException e) {
						//                        if(executorService.get().isShutdown()) {
						//                            supportTeam.info(getClass().getSimpleName() + " stopped");
						//                        } else {
						errorListener.error(e);
						//                        }
					}
				}
			}
		});
	}

	public void stop() {
		if (serverSocket.get() == null || serverSocket.get().isClosed()) {
			errorListener.warning("Not started, server: " + serverSocket);
		} else {
			sendPoisonPill();
			shutdownAndAwaitTermination(executorService.get());
			executorService.set(null);
			try {
				serverSocket.get().close();
				serverSocket.set(null);
			} catch (IOException e) {
				errorListener.warning(e);
			}
		}
	}

	private void sendPoisonPill() {
		try {
			// TODO between finding a port and opening a socket some other thread could steal the port, fix it
			poisonLocalPort = findFreePort();
			Socket socket = new Socket("localhost", listeningPort, localPoisonAddress, poisonLocalPort);
			OutputStream outputStream = socket.getOutputStream();
			outputStream.write("poison".getBytes());
			socket.shutdownOutput();
			socket.close();
		} catch (IOException e) {
			errorListener.error(e);
		}
	}

	public int findFreePort() {
		int port;
		try {
			ServerSocket socket = new ServerSocket(0);
			port = socket.getLocalPort();
			socket.close();
		} catch (Exception e) {
			port = -1;
		}
		return port;
	}

	void shutdownAndAwaitTermination(ExecutorService pool) {
		pool.shutdown();
		try {
			if (!pool.awaitTermination(5, TimeUnit.SECONDS)) {
				pool.shutdownNow();
				if (!pool.awaitTermination(5, TimeUnit.SECONDS))
					errorListener.error(getClass().getSimpleName() + ": Pool did not terminate");
			}
		} catch (InterruptedException ie) {
			pool.shutdownNow();
			Thread.currentThread().interrupt();
		}
	}

}

public class MultiThreadedSocketServerTest {
}
