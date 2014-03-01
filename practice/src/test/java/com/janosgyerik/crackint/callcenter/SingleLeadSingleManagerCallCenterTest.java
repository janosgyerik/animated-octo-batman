package com.janosgyerik.crackint.callcenter;

import org.junit.Assert;
import org.junit.Test;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;

public class SingleLeadSingleManagerCallCenterTest {

    class SimpleCallHandlerPicker implements ICallHandlerPicker {
        private final Collection<ICallHandler> handlers;

        SimpleCallHandlerPicker(Collection<ICallHandler> handlers) {
            this.handlers = handlers;
        }

        @Override
        public ICallHandler getAvailableCallHandler() {
            for (ICallHandler handler : handlers) {
                if (handler.isAvailable()) {
                    return handler;
                }
            }
            return null;
        }
    }

    class Ticket implements ITicket {}

    class Level1Ticket extends Ticket {}

    class Level2Ticket extends Ticket {}

    class Level3Ticket extends Ticket {}

    abstract class CallHandler implements ICallHandler {

        private boolean available = true;

        @Override
        public boolean isAvailable() {
            return available;
        }

        public void setAvailable(boolean available) {
            this.available = available;
        }
    }

    class Fresher extends CallHandler {
        @Override
        public boolean canHandle(ITicket ticket) {
            return ticket instanceof Level1Ticket;
        }
    }

    class TechLead extends CallHandler {
        @Override
        public boolean canHandle(ITicket ticket) {
            return ticket instanceof Level2Ticket;
        }
    }

    class Manager extends CallHandler {
        @Override
        public boolean canHandle(ITicket ticket) {
            return ticket instanceof Level3Ticket;
        }
    }

    private SingleLeadSingleManagerCallCenter createSampleCallCenter() {
        Collection<ICallHandler> freshers = new HashSet<ICallHandler>();
        freshers.add(new Fresher());
        ICallHandlerPicker picker = new SimpleCallHandlerPicker(freshers);
        ICallHandler lead = new TechLead();
        ICallHandler manager = new Manager();
        return new SingleLeadSingleManagerCallCenter(picker, lead, manager);
    }

    private ICallHandler getCallHandler(ITicket ticket) {
        ICallCenter callCenter = createSampleCallCenter();
        return callCenter.getCallHandler(ticket);
    }

    @Test
    public void testFresherCanHandleLevel1() {
        ICallHandler handler = getCallHandler(new Level1Ticket());
        Assert.assertNotNull(handler);
        Assert.assertTrue(handler instanceof Fresher);
    }

    @Test
    public void testLevel2DelegatedToTechLead() {
        ICallHandler handler = getCallHandler(new Level2Ticket());
        Assert.assertNotNull(handler);
        Assert.assertTrue(handler instanceof TechLead);
    }

    @Test
    public void testLevel3DelegatedToManager() {
        ICallHandler handler = getCallHandler(new Level3Ticket());
        Assert.assertNotNull(handler);
        Assert.assertTrue(handler instanceof Manager);
    }

    @Test
    public void testLevel2DelegatedToManagerWhenTechLeadBusy() {
        ICallCenter callCenter = createSampleCallCenter();
        ICallHandler handler = callCenter.getCallHandler(new Level2Ticket());
        Assert.assertNotNull(handler);
        TechLead lead = (TechLead) handler;
        lead.setAvailable(false);
        ICallHandler handler2 = callCenter.getCallHandler(new Level2Ticket());
        Assert.assertTrue(handler2 instanceof Manager);
    }

    @Test
    public void testGetNullIfNoFreshers() {
        Collection<ICallHandler> freshers = Collections.emptySet();
        ICallHandlerPicker picker = new SimpleCallHandlerPicker(freshers);
        ICallHandler lead = new TechLead();
        ICallHandler manager = new Manager();
        ICallCenter callCenter = new SingleLeadSingleManagerCallCenter(picker, lead, manager);
        Assert.assertNull(callCenter.getCallHandler(new Level1Ticket()));
        Assert.assertNull(callCenter.getCallHandler(new Level2Ticket()));
        Assert.assertNull(callCenter.getCallHandler(new Level3Ticket()));
    }

    @Test
    public void testGetNullIfFreshersBusy() {
        ICallCenter callCenter = createSampleCallCenter();
        ICallHandler handler = callCenter.getCallHandler(new Level1Ticket());
        Fresher fresher = (Fresher) handler;
        fresher.setAvailable(false);
        Assert.assertNull(callCenter.getCallHandler(new Level1Ticket()));
        Assert.assertNull(callCenter.getCallHandler(new Level2Ticket()));
        Assert.assertNull(callCenter.getCallHandler(new Level3Ticket()));
    }
}
