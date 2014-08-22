package com.janosgyerik.stackoverflow.junk;

public class AbstractFieldsDemo {
    public static void main(String[] args) {
        Instrument instrument = new ElectricGuitar();
        instrument.play();
    }
}

abstract class Instrument {
    protected String name;

    abstract public void play();
}

abstract class StringedInstrument extends Instrument {
    protected int numberOfStrings;
}

class ElectricGuitar2 extends StringedInstrument {
    @Override
    public void play() {
        System.out.println(name);
    }
}

class ElectricGuitar extends StringedInstrument {

    private String name;

    public ElectricGuitar() {
        super();
        this.name = "Guitar";
        this.numberOfStrings = 6;
    }

    public ElectricGuitar(int numberOfStrings) {
        super();
        this.name = "Guitar";
        this.numberOfStrings = numberOfStrings;
    }

    @Override
    public void play() {
        int arr[] = {2,5,3};
        System.out.println(arr);
        char ch[] = {'a','a','a'};
        System.out.println(ch);
        System.out.println(0.1 + 0.2 == 0.3);
        System.out.println("An electric " + numberOfStrings + "-string " + name
                + " is rocking!");
    }
}