package com.janosgyerik.crackint.callcenter;

public class SingleLeadSingleManagerCallCenter implements ICallCenter {
    private final ICallHandlerPicker picker;
    private final ICallHandler lead;
    private final ICallHandler manager;

    SingleLeadSingleManagerCallCenter(ICallHandlerPicker picker, ICallHandler lead, ICallHandler manager) {
        this.picker = picker;
        this.lead = lead;
        this.manager = manager;
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
        if (lead.isAvailable() && lead.canHandle(ticket)) {
            return lead;
        }
        return manager.isAvailable() ? manager : null;
    }
}
