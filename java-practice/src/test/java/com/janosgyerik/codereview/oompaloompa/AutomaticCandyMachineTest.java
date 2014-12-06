package com.janosgyerik.codereview.oompaloompa;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

enum Item {
    CandyBar(10),
    Gumball(3);

    final int price;

    Item(int price) {
        this.price = price;
    }
}

class AutomaticCandyMachineOutput {
    final Item item;
    final int itemCount;
    final int change;

    AutomaticCandyMachineOutput(Item item, int itemCount, int change) {
        this.item = item;
        this.itemCount = itemCount;
        this.change = change;
    }
}

class AutomaticCandyMachine {
    AutomaticCandyMachineOutput redeemCouponsForItems(int coupons, Item item) {
        int itemCount = coupons / item.price;
        int change = coupons % item.price;
        return new AutomaticCandyMachineOutput(item, itemCount, change);
    }

    AutomaticCandyMachineOutput redeemCouponsForCandybars(int coupons) {
        return redeemCouponsForItems(coupons, Item.CandyBar);
    }

    AutomaticCandyMachineOutput redeemCouponsForGumballs(int coupons) {
        return redeemCouponsForItems(coupons, Item.Gumball);
    }
}

public class AutomaticCandyMachineTest {
    @Test
    public void testCandybarsFirst() {
        int coupons = 99;
        AutomaticCandyMachine machine = new AutomaticCandyMachine();
        AutomaticCandyMachineOutput output1 = machine.redeemCouponsForCandybars(coupons);
        AutomaticCandyMachineOutput output2 = machine.redeemCouponsForGumballs(output1.change);
        assertEquals(9, output1.itemCount);
        assertEquals(3, output2.itemCount);
    }
}
