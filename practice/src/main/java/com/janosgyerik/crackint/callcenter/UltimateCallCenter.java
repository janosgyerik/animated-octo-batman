package com.janosgyerik.crackint.callcenter;

public class UltimateCallCenter implements ICallCenter {
    private final ICallHandler handler;

    UltimateCallCenter(ICallHandler handler) {
        this.handler = handler;
    }

    @Override
    public ICallHandler getCallHandler(ITicket ticket) {
        return handler.isAvailable() ? handler : null;
    }
}
