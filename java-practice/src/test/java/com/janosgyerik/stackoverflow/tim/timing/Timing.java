package com.janosgyerik.stackoverflow.tim.timing;

import com.janosgyerik.stackoverflow.tim.align.FormatedTableBuilder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.IntFunction;

/**
 * A timing class.
 *
 * Timings are run in chunks. For each chunk, the input is gathered before-hand.
 */
public class Timing {

    private final List<TimingObject> functionsToTime = new ArrayList<>();

    /**
     * amount of chunks to run.
     */
    private int amountChunks = 1_000;

    /**
     * amount of runs per chunk.
     */
    private int amountRunsPerChunk = 1_000;

    /**
     * adds a new function which will be timed.
     *
     * @param <R> return type of functionToTime (irrelevant)
     * @param <T> input type of functionToTime (same as return type of
     * inputConverter)
     * @param functionToTime a function expecting input of type T, returning
     * output of any type (R)
     * @param inputConverter converts the loop variable to type T and passes it
     * to functionToTime
     * @param name name of the function (used for output)
     */
    public <R, T> void add(Function<R, T> functionToTime, IntFunction<T> inputConverter, String name) {
        functionsToTime.add(new TimingObject(functionToTime, inputConverter, name));
    }

    /**
     * sets how many chunks should be run.
     *
     * The total amount of how often the given functions should be run when
     * timed is amountChunks * amountRunsPerChunk.
     *
     * @param amountChunks amountChunks
     */
    public void setAmountChunks(int amountChunks) {
        this.amountChunks = amountChunks;
    }

    /**
     * sets how often the function is run per chunk.
     *
     * The total amount of how often the given functions should be run when
     * timed is amountChunks * amountRunsPerChunk.
     *
     * @param amountRunsPerChunk amountRunsPerChunk
     */
    public void setAmountRunsPerChunk(int amountRunsPerChunk) {
        this.amountRunsPerChunk = amountRunsPerChunk;
    }

    /**
     * performs the actual timing for all given functions.
     */
    public void time() {
        for (int chunks = 0; chunks < amountChunks; chunks++) {
            // run a chunk of tests on this timingObject:
            for (TimingObject timingObject : functionsToTime) {
                // generate input:
                ArrayList<Object> input = new ArrayList<>();
                for (int runs = 0; runs < amountRunsPerChunk; runs++) {
                    input.add(timingObject.inputConverter.apply(chunks * amountRunsPerChunk + runs));
                }
                // run with input:
                long[] times = timeRuns(timingObject, input);
                timingObject.addTimeChunk(times);
            }
            Collections.shuffle(functionsToTime); // randomize functions each time
        }

        for (TimingObject timingObject : functionsToTime) {
            timingObject.processTimes();
        }
    }

    /**
     * runs a chunk of functions, timing each one.
     *
     * @param <T> input type
     * @param timingObject timingObject
     * @param input list of input for functions
     * @return array of times
     */
    private <T> long[] timeRuns(TimingObject timingObject, ArrayList<T> input) {
        long[] times = new long[input.size()];
        for (int i = 0; i < input.size(); i++) {
            long start = System.nanoTime();
            timingObject.function.apply(input.get(i));
            times[i] = System.nanoTime() - start;
        }
        return times;
    }

    /**
     * passes the result of the timing to the given consumer.
     *
     * @param consumer consumer
     * @param sort how to sort the result
     */
    public void output(Consumer<String> consumer, Sort sort) {
        switch (sort) {
            case ASC:
                Collections.sort(functionsToTime,
                        (TimingObject t1, TimingObject t2) -> (int) (t1.meanTime - t2.meanTime));
                break;
            case DESC:
                Collections.sort(functionsToTime,
                        (TimingObject t1, TimingObject t2) -> (int) (t2.meanTime - t1.meanTime));
                break;
            case NAME:
                Collections.sort(functionsToTime,
                        (TimingObject t1, TimingObject t2) -> t1.name.compareTo(t2.name));
                break;
            default:
                break;
        }

        FormatedTableBuilder formater = new FormatedTableBuilder();
        formater.addLine("name", "per call (mean, ns)", "per call (median, ns)", "95th percentile (ns)", "total (ms)");
        for (TimingObject timing : functionsToTime) {
            formater.addLine(timing.name,
                    String.valueOf(timing.getMeanTime()),
                    String.valueOf(timing.getMedianTime()),
                    String.valueOf(timing.getPercentile(95)),
                    String.valueOf(timing.getMeanTime() * timing.times.size() / 1000000000.0)
            );
        }
//        formater.add(functionsToTime, (TimingObject timing) -> new String[]{
//                timing.name,
//                String.valueOf(timing.getMeanTime()),
//                String.valueOf(timing.getMedianTime()),
//                String.valueOf(timing.getPercentile(95)),
//                String.valueOf(timing.getMeanTime() * timing.times.size() / 1000000000.0)
//        });
        consumer.accept(formater.format());
    }

    private class TimingObject {

        private final Function function;
        private final IntFunction inputConverter;
        private final String name;
        private final List<Long> times;
        private long meanTime;

        public TimingObject(Function function, IntFunction inputConverter, String name) {
            this.function = function;
            this.inputConverter = inputConverter;
            this.name = name;
            this.times = new ArrayList<>();
        }

        public void addTimeChunk(long[] chunks) {
            for (long chunk : chunks) {
                times.add(chunk);
            }
        }

        public void processTimes() {
            Statistics.removeWorstAndBest(times); // also sorts
            meanTime = (long) Statistics.calculateMean(times);
        }

        public long getMeanTime() {
            return meanTime;
        }

        public long getMedianTime() {
            return (long) Statistics.calculateMedian(times);
        }

        public long getPercentile(int percentile) {
            return (long) Statistics.percentile(times, percentile);
        }

    }

    public static enum Sort {

        ASC, DESC, NAME
    }
}