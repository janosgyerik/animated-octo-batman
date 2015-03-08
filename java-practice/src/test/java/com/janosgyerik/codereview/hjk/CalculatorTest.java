//package com.janosgyerik.codereview.hjk;
//
//import static org.hamcrest.MatcherAssert.assertThat;
//import static org.hamcrest.Matchers.equalTo;
//import static org.testng.Assert.assertEquals;
//
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.Collections;
//import java.util.EnumMap;
//import java.util.Iterator;
//import java.util.Map;
//import java.util.Objects;
//
//import org.testng.annotations.DataProvider;
//import org.testng.annotations.Test;
//
//public class CalculatorTest {
//
//    @DataProvider(name = "test-cases")
//    public Iterator<Object[]> getTestCases() {
//        final Collection<Object[]> cases = new ArrayList<>();
//        addCase(cases, 0, CaseBuilder.create());
//        addCase(cases, 0.01, CaseBuilder.create().with(Denomination.A_CENT, 1));
//        addCase(cases, 1.28, CaseBuilder.create()
//                .with(Denomination.A_DOLLAR, 1)
//                .with(Denomination.QUARTER, 1)
//                .with(Denomination.A_CENT, 3));
//        addCase(cases, 19.48, CaseBuilder.create()
//                .with(Denomination.TEN_DOLLARS, 1)
//                .with(Denomination.FIVE_DOLLARS, 1)
//                .with(Denomination.DOLLAR_NINETY_NINE, 2)
//                .with(Denomination.QUARTER, 2));
//        addCase(cases, 100.75, CaseBuilder.create()
//                .with(Denomination.FIFTY_DOLLARS, 2)
//                .with(Denomination.QUARTER, 3));
//        addCase(cases, 1_000_040.15, CaseBuilder.create()
//                .with(Denomination.A_MILLION, 1)
//                .with(Denomination.TWENTY_DOLLARS, 2)
//                .with(Denomination.DIME, 1)
//                .with(Denomination.NICKEL, 1));
//        return cases.iterator();
//    }
//
//    @Test(dataProvider = "test-cases")
//    public void test(final double testValue, final CaseBuilder builder) {
//        final Map<Denomination, Integer> expected = Objects.requireNonNull(builder).getExpected();
//        assertThat(Calculator.getBreakdown(testValue), equalTo(expected));
//        assertThat(Calculator.compute(expected), equalTo(testValue));
//    }
//
//    @Test
//    public void testx() {
//        int MULTIPLIER = 100;
//        double input = 100.75;
//        int value = 5000;
//        int intValue = Double.valueOf(MULTIPLIER * input).intValue();
//        int div = intValue / value;
//        int remainder = intValue % value;
//        System.out.println(div);
//        System.out.println(remainder);
//        System.out.println((double) remainder / MULTIPLIER);
//
////        assertEquals("", Calculator.getBreakdown(100.75).toString());
//    }
//
//    private static void addCase(final Collection<Object[]> cases, double testValue,
//                                final CaseBuilder builder) {
//        Objects.requireNonNull(cases).add(new Object[] {testValue, builder });
//    }
//
//    /**
//     * Helper class to build the expected {@link Map} of denominations and multipliers.
//     */
//    private static final class CaseBuilder {
//        private final Map<Denomination, Integer> map = new EnumMap<>(Denomination.class);
//
//        static CaseBuilder create() {
//            return new CaseBuilder();
//        }
//
//        /**
//         * Let <em>v</em> be the sum of the current value and <code>multiplier</code>.<br>
//         * If <em>v</em> is greater than zero, the value is updated as such, else the entry for
//         * <code>denominator</code> is removed.<br>
//         * As such, the generated {@link Map} will only have denominators with positive multipliers.
//         *
//         * @param denomination the denomination to add
//         * @param multiplier the multiplier to add
//         * @return this {@link CaseBuilder}
//         */
//        CaseBuilder with(final Denomination denomination, int multiplier) {
//            final int current = map.getOrDefault(Objects.requireNonNull(denomination), 0);
//            if (current + multiplier > 0) {
//                map.put(denomination, current + multiplier);
//            } else {
//                map.remove(denomination);
//            }
//            return this;
//        }
//
//        /**
//         * @return an unmodifiable copy of the underlying {@link Map}
//         */
//        Map<Denomination, Integer> getExpected() {
//            return Collections.unmodifiableMap(map);
//        }
//
//        /**
//         * @return a human-reable output
//         * @see Calculator#format(Map)
//         */
//        @Override
//        public String toString() {
//            return Calculator.format(map);
//        }
//    }
//
//}