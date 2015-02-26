package com.janosgyerik.codereview.randomuser;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

class ChangeDispenser {

    private static final BigDecimal TWENTYDOLLARS = new BigDecimal("20.00");
    private static final BigDecimal TENDOLLARS = new BigDecimal("10.00");
    private static final BigDecimal FIVEDOLLARS = new BigDecimal("5.00");
    private static final BigDecimal ONEDOLLAR = new BigDecimal("1.00");
    private static final BigDecimal TWENTYFIVECENTS = new BigDecimal("0.25");
    private static final BigDecimal TENCENTS = new BigDecimal("0.10");
    private static final BigDecimal FIVECENTS = BigDecimal.valueOf(0.05);
    private static final BigDecimal ONECENT = BigDecimal.valueOf(0.01);
    private static final Integer ZERO = 0;

    private Map<BigDecimal, Integer> denominationMap = new HashMap<BigDecimal, Integer>();

    public ChangeDispenser() {
        denominationMap.put(TWENTYDOLLARS, ZERO);
        denominationMap.put(TENDOLLARS, ZERO);
        denominationMap.put(FIVEDOLLARS, ZERO);
        denominationMap.put(ONEDOLLAR, ZERO);
        denominationMap.put(TWENTYFIVECENTS, ZERO);
        denominationMap.put(TENCENTS, ZERO);
        denominationMap.put(FIVECENTS, ZERO);
        denominationMap.put(ONECENT, ZERO);
    }

    // Method that does the logical work
    public void dispense(BigDecimal amount) {
        System.out.println("Entering dispense method");
        System.out.println("Amount submitted: " + amount.toString());

        if (amount.compareTo(TWENTYDOLLARS) == 0 || amount.compareTo(TWENTYDOLLARS) == 1) {
            System.out.println("$20 block");
            amount = calculateRemainder(amount, TWENTYDOLLARS);
        }

        if (amount.compareTo(TENDOLLARS) == 0 || amount.compareTo(TENDOLLARS) == 1) {
            System.out.println("$10 block");
            amount = calculateRemainder(amount, TENDOLLARS);
        }

        if (amount.compareTo(FIVEDOLLARS) == 0 || amount.compareTo(FIVEDOLLARS) == 1) {
            System.out.println("$5 block");
            amount = calculateRemainder(amount, FIVEDOLLARS);
        }

        if (amount.compareTo(ONEDOLLAR) == 0 || amount.compareTo(ONEDOLLAR) == 1) {
            System.out.println("$1 block");
            amount = calculateRemainder(amount, ONEDOLLAR);
        }

        if (amount.compareTo(TWENTYFIVECENTS) == 0 || amount.compareTo(TWENTYFIVECENTS) == 1) {
            System.out.println("25c block");
            amount = calculateRemainder(amount, TWENTYFIVECENTS);
        }

        if (amount.compareTo(TENCENTS) == 0 || amount.compareTo(TENCENTS) == 1) {
            System.out.println("10c block");
            amount = calculateRemainder(amount, TENCENTS);
        }

        if (amount.compareTo(FIVECENTS) == 0 || amount.compareTo(FIVECENTS) == 1) {
            System.out.println("5c block");
            amount = calculateRemainder(amount, FIVECENTS);
        }

        if (amount.compareTo(ONECENT) == 0 || amount.compareTo(ONECENT) == 1) {
            System.out.println("1c block");
            amount = calculateRemainder(amount, ONECENT);
        }
    }

    private BigDecimal calculateRemainder(BigDecimal dollarAmount, BigDecimal denomination) {
        System.out.println("Entering calculateRemainder method");
        int count = 0;
        while (dollarAmount.compareTo(denomination) == 0 || dollarAmount.compareTo(denomination) == 1) {
            dollarAmount = dollarAmount.subtract(denomination);
            count++;
        }
        denominationMap.put(denomination, count);
        return dollarAmount;
    }

    public Map<BigDecimal, Integer> getDenominationMap() {
        return denominationMap;
    }

    public void setDenominationMap(Map<BigDecimal, Integer> denominationMap) {
        this.denominationMap = denominationMap;
    }

}

public class CoinDispenserTest {

    private ChangeDispenser cdUnit;

    @Before
    public void setUp() {
        cdUnit = new ChangeDispenser();
    }

//    @Ignore
//    @Test
    public void test() {
        fail("Not yet implemented");
    }

    @Test
    public void testDispenserTwentyExact() {
        cdUnit.dispense(new BigDecimal("20.00"));
        assertEquals(1, cdUnit.getDenominationMap().get(new BigDecimal("20.00")).intValue());
        assertEquals(cdUnit.getDenominationMap().get(new BigDecimal("0.10")), new Integer(0));
        assertEquals(cdUnit.getDenominationMap().get(new BigDecimal("0.01")), new Integer(0));
    }

    @Test
    public void testDispenserZeroExact() {
        cdUnit.dispense(new BigDecimal("0.00"));
        assertEquals(cdUnit.getDenominationMap().get(new BigDecimal("20.00")), new Integer(0));
        assertEquals(cdUnit.getDenominationMap().get(new BigDecimal("0.10")), new Integer(0));
        assertEquals(cdUnit.getDenominationMap().get(new BigDecimal("0.01")), new Integer(0));
    }

    @Test
    public void testDispenserTwenty() {
        cdUnit.dispense(new BigDecimal("20.23"));
        assertEquals(cdUnit.getDenominationMap().get(new BigDecimal("20.00")), new Integer(1));
        assertEquals(cdUnit.getDenominationMap().get(new BigDecimal("0.10")), new Integer(2));
        assertEquals(cdUnit.getDenominationMap().get(new BigDecimal("0.01")), new Integer(3));
        assertEquals("{0.01=3, 0.05=0, 0.10=2, 0.25=0, 1.00=0, 5.00=0, 10.00=0, 20.00=1}",
                new TreeMap<>(cdUnit.getDenominationMap()).toString());
    }

    @Test
    public void testDispenserTen() {
        cdUnit.dispense(new BigDecimal("10.23"));
        assertEquals(cdUnit.getDenominationMap().get(new BigDecimal("10.00")), new Integer(1));
        assertEquals(cdUnit.getDenominationMap().get(new BigDecimal("0.10")), new Integer(2));
        assertEquals(cdUnit.getDenominationMap().get(new BigDecimal("0.01")), new Integer(3));
    }

    @Test
    public void testDispenserFive() {
        cdUnit.dispense(new BigDecimal("5.23"));
        assertEquals("{0.01=3, 20.00=0, 0.10=2, 0.25=0, 10.00=0, 0.05=0, 5.00=1, 1.00=0}",
                cdUnit.getDenominationMap().toString());
        assertEquals(cdUnit.getDenominationMap().get(new BigDecimal("5.00")), new Integer(1));
        assertEquals(cdUnit.getDenominationMap().get(new BigDecimal("0.10")), new Integer(2));
        assertEquals(cdUnit.getDenominationMap().get(new BigDecimal("0.01")), new Integer(3));
    }


    @Test
    public void testDispenserOneDollar() {
        cdUnit.dispense(new BigDecimal("3.23"));
        assertEquals(cdUnit.getDenominationMap().get(new BigDecimal("1.00")), new Integer(3));
        assertEquals(cdUnit.getDenominationMap().get(new BigDecimal("0.10")), new Integer(2));
        assertEquals(cdUnit.getDenominationMap().get(new BigDecimal("0.01")), new Integer(3));
    }

}
