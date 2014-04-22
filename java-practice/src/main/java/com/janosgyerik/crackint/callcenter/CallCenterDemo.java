package com.janosgyerik.crackint.callcenter;

public class CallCenterDemo {
    ICallCenter createSingleLeadSingleManagerCallCenter(ICallHandlerPicker picker, final ICallHandler lead, ICallHandler manager) {
        ICallCenter managerCallCenter = new UltimateCallCenter(manager);
        ICallCenter leadCallCenter = new MultiLevelCallCenter(new ICallHandlerPicker() {
            @Override
            public ICallHandler getAvailableCallHandler() {
                return lead.isAvailable() ? lead : null;
            }
        }, managerCallCenter);
        return new MultiLevelCallCenter(picker, leadCallCenter);
    }
}