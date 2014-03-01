package com.janosgyerik.crackint.callcenter;

public class MultiLevelCallCenter implements ICallCenter {
    private final ICallHandlerPicker picker;
    private final ICallCenter nextCallCenter;

    MultiLevelCallCenter(ICallHandlerPicker picker, ICallCenter nextCallCenter) {
        this.picker = picker;
        this.nextCallCenter = nextCallCenter;
    }

    @Override
    public ICallHandler getCallHandler(ITicket ticket) {
        ICallHandler handler = picker.getAvailableCallHandler();
        if (handler == null) {
            // nobody available. perhaps throw new NoSuchElementException() ?
            return null;
        }
        if (handler.canHandle(ticket)) {
            return handler;
        }
        return nextCallCenter.getCallHandler(ticket);
    }
}
