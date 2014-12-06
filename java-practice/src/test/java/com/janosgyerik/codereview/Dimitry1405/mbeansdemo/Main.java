package com.janosgyerik.codereview.Dimitry1405.mbeansdemo;

import javax.management.*;
import javax.management.remote.JMXConnectorServer;
import javax.management.remote.JMXConnectorServerFactory;
import javax.management.remote.JMXServiceURL;
import java.lang.management.ManagementFactory;
import java.net.BindException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.ExportException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

class MBeanContainer {
    static MBeanServer mBeanServer;
    private static Map<ParamInterface, BaseParam> mbeansList = new HashMap<>();

    public static void startMBeanServer(int port) {
        mBeanServer = ManagementFactory.getPlatformMBeanServer();
        try {
            LocateRegistry.createRegistry(port);
            JMXServiceURL url = new JMXServiceURL("service:jmx:rmi:///jndi/rmi://localhost:" + port + "/server");
            JMXConnectorServer cs = JMXConnectorServerFactory.newJMXConnectorServer(url, null, mBeanServer);
            cs.start();
        } catch (ExportException ee) { //hide trace exception and continue work when mbean server is already run.
            if (ee.getCause().getClass() == BindException.class)
                Utils.showMessage("Server is already started. \n " + ee.getCause().getMessage() + " \n continue...");
        } catch (Exception e) {
            Utils.processException(e);
        }
    }

    /**
     * Register mbean in server
     *
     */
    public static void registerMbean(ParamInterface baseParam) {
        BaseParam mbeanObj = new BaseParam(baseParam);
        try {
            ObjectName mbeanName = new ObjectName("JMXParam:name=" + baseParam.getType());
            mBeanServer.registerMBean(mbeanObj, mbeanName);
            mBeanServer.addNotificationListener(mbeanName, new ParamListener(), null, null);
            mbeansList.put(baseParam, mbeanObj);
        } catch (Exception e) {
            Utils.processException(e);
        }
    }


    /**
     * Return Mbean object by type of plugin
     *
     */
    public static BaseParam getParamMBeanByType(String type) {
        for (Map.Entry<ParamInterface, BaseParam> entry : mbeansList.entrySet()) {
            if (entry.getKey().getType().equals(type)) {
                return entry.getValue();
            }
        }
        Utils.log("getParamMBeanByType return null for param type: " + type);
        return null; //never unreached
    }

    public static void stopMonitoring() {
        for (BaseParam mbean : mbeansList.values()) {
            mbean.stop();
        }
    }

    public static void startMonitoring() {
        for (BaseParam mbean : mbeansList.values()) {
            mbean.start();
        }
    }

}

interface ParamInterface {
    public int getInterval();

    public String getName();

    public String getType();

    public String getValue();
}

interface BaseParamMBean {
    public void start();

    public void stop();

    public void setInterval(long time);
}

class BaseParam extends NotificationBroadcasterSupport implements BaseParamMBean, Runnable {
    private boolean stop = true;
    private int index = 0;
    private long interval = 1000;
    private String paramName;
    private String paramType;
    private String oldValue;
    private ParamInterface o;

    public BaseParam(ParamInterface o) {
        this.paramName = o.getName();
        this.paramType = o.getType();
        this.interval = o.getInterval();
        this.o = o;
        this.start();
    }

    public void setInterval(long interval) {
        this.interval = interval;
    }

    public void start() {
        try {
            stop = false;
            Thread t = new Thread(this);
            t.start();
        } catch (Exception e) {
            Utils.processException(e);
        }
    }

    public void stop() {
        stop = true;
    }

    public void run() {
        while (!stop) {
            try {
                Thread.sleep(interval);
            } catch (Exception e) {
                Utils.processException(e);
            }

            String newValue = o.getValue();
            AttributeChangeNotification notif = new AttributeChangeNotification(this, //object
                    index, //sequenceNumber
                    System.currentTimeMillis(), //cur time
                    "Attribute Change", //msg
                    this.paramName, //attributeName
                    this.paramType, //attributeType
                    oldValue, //old value
                    newValue //new value
            );
            sendNotification(notif);
            oldValue = newValue;
        }//while
    }

    public MBeanNotificationInfo[] getNotificationInfo() {
        String[] attChanges = {AttributeChangeNotification.ATTRIBUTE_CHANGE};
        MBeanNotificationInfo[] info = new MBeanNotificationInfo[1];
        info[0] = new MBeanNotificationInfo(attChanges, "javax.management.AttributeChangeNotification", this.paramName);
        return info;
    }

}

class ParamListener implements NotificationListener {

    public void handleNotification(Notification not, Object obj) {
        AttributeChangeNotification notif = (AttributeChangeNotification) not;
        System.out.println(notif.getAttributeName() + ": " + notif.getNewValue());
        //        CustomTable.updateCell(notif.getAttributeType(), notif.getNewValue().toString());
    }
}

class Utils {

    public static void processException(Exception e) {
        e.printStackTrace();
    }

    public static void showMessage(String s) {
        System.out.println(s);
    }

    public static void log(String s) {
        System.out.println("!" + s);
    }
}

class CpuParam implements ParamInterface {

    private int maximum = 100;
    private int minimum = 0;
    private final String name = "CPU load";
    private final String type = "cpu.attribute.check";
    private int interval = 5000;

    @Override
    public int getInterval() {
        return interval;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public String getValue() {
        Random rn = new Random();
        int range = maximum - minimum + 1;
        int randomNum = rn.nextInt(range) + minimum;
        return String.valueOf(randomNum);
    }
}

public class Main {
    public static void main(String args[]) {
        MBeanContainer.startMBeanServer(9999);
    }
}