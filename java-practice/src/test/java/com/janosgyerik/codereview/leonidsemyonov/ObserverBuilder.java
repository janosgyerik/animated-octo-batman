package com.janosgyerik.codereview.leonidsemyonov;

class Helper {

    public int getScrollCoordinate(View view) {
        return 0;
    }
}

class View {

    public void postDelayed(OnStopScrollingObserverTask onStopScrollingObserverTask, long delay) {

    }
}

class OnStopScrollingObserverTask implements Runnable {

    public static interface Listener {
        void onScrollStopped();
    }

    public static Builder builder(Helper helper, View view) {
        // Use this static method instead of Builder's constructor
        // in order not to write the keyword  << new >>  every time
        return new Builder(helper, view);
    }

    public static class Builder {
        private final Helper helper;
        private final View view;
        private Listener listener = DUMMY_LISTENER;
        private Long delay = DEFAULT_DELAY;

        private Builder(Helper helper, View view) {
            this.helper = helper;
            this.view = view;
        }

        public Builder delay(Long delay) {
            this.delay = delay;
            return this;
        }

        public Builder listener(Listener listener) {
            this.listener = listener;
            return this;
        }

        public OnStopScrollingObserverTask build() {
            return new OnStopScrollingObserverTask(this);
        }
    }


    private static final long DEFAULT_DELAY = 100;

    private static final Listener DUMMY_LISTENER = new Listener() {
        @Override
        public void onScrollStopped() {
            // dummy implementation
        }
    };


    private final Helper helper;
    private final View view;
    private final Listener listener;
    private final long delay;
    private int initialPosition;

    private OnStopScrollingObserverTask(Builder builder) {
        this.view = builder.view;
        this.helper = builder.helper;
        this.listener = builder.listener;
        this.delay = builder.delay;
        this.initialPosition = getScrollCoordinate();
    }

    private int getScrollCoordinate() {
        return helper.getScrollCoordinate(view);
    }

    @Override
    public void run() {
        int newPosition = getScrollCoordinate();
        int delta = initialPosition - newPosition;
        if (delta == 0) {
            listener.onScrollStopped();
        } else {
            this.initialPosition = getScrollCoordinate();
            view.postDelayed(this, delay);
        }
    }

    public void startDelayed() {
        this.initialPosition = getScrollCoordinate();
        view.postDelayed(this, delay);
    }
}

public class ObserverBuilder {
}
