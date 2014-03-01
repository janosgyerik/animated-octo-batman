package com.janosgyerik.crackint.callcenter;

public interface ICallHandler {
    boolean isAvailable();
    boolean canHandle(ITicket ticket);
}
