package com.janosgyerik.crackint.callcenter;

public interface ICallCenter {
    ICallHandler getCallHandler(ITicket ticket);
}
