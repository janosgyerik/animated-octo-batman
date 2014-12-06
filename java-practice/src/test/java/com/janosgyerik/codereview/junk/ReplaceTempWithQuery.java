package com.janosgyerik.codereview.junk;

public class ReplaceTempWithQuery {
    double hello() {
        if (basePrice() > 1000)
            return basePrice() * 0.95;
        else
            return basePrice() * 0.98;
    }

    double basePrice() {
        final double _quantity = 25;
        final double _itemPrice = 12.50;
        return _quantity * _itemPrice;
    }
}
