package com.janosgyerik.stackoverflow.tim.align;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class FormatedTableBuilderTest {

    private static FormatedTableBuilder getAlign(int columns, int rows) {
        FormatedTableBuilder align = new FormatedTableBuilder();
        for (int i = 0; i < rows; i++) {
            String[] row = new String[columns];
            for (int j = 0; j < columns; j++) {
                row[j] = String.valueOf(i) + String.valueOf(j);
            }
            align.addLine(row);
        }
        return align;
    }

    /** TEST CONTENT **/

    @Test(expected=IllegalArgumentException.class)
    public void setNegativeColumn() {
        getAlign(2, 3).set(-1, 1, "new value");
    }

    @Test(expected=IllegalArgumentException.class)
    public void setNonExistingColumn() {
        getAlign(2, 3).set(20, 1, "new value");
    }

    @Test(expected=IllegalArgumentException.class)
    public void setNegativeLine() {
        getAlign(2, 3).set(1, -1, "new value");
    }

    @Test(expected=IllegalArgumentException.class)
    public void setNonExistingLine() {
        getAlign(2, 3).set(1, 20, "new value");
    }

    @Test()
    public void setExisting() {
        getAlign(2, 3).set(1, 1, "new value");
    }

    @Test()
    public void setExistingCornerCases() {
        getAlign(3, 3).set(0, 1, "new value");
        getAlign(3, 3).set(1, 0, "new value");
        getAlign(3, 3).set(1, 2, "new value");
        getAlign(3, 3).set(2, 1, "new value");
    }

    @Test(expected=IllegalArgumentException.class)
    public void addNegativeColumn() {
        getAlign(2, 3).addColumn(-1, "new");
    }

    @Test(expected=IllegalArgumentException.class)
    public void addNegativeLine() {
        getAlign(2, 3).addLine(-1, "new");
    }

    @Test()
    public void addShortColumn() {
        getAlign(2, 3).addColumn(1, "new");
    }

    @Test()
    public void addShortColumnOutsideField() {
        getAlign(2, 3).addColumn(5, "new");
    }

    @Test()
    public void addLongColumn() {
        getAlign(2, 3).addColumn(1, "new", "new", "new", "new", "new");
    }

    @Test()
    public void addLongColumnOutsideField() {
        getAlign(2, 3).addColumn(5, "new", "new", "new", "new", "new");
    }

    @Test()
    public void addShortLine() {
        getAlign(2, 3).addLine(1, "new");
    }

    @Test()
    public void addShortLineOutsideField() {
        getAlign(2, 3).addLine(5, "new");
    }

    @Test()
    public void addLongLine() {
        getAlign(2, 3).addLine(1, "new", "new", "new", "new", "new");
    }

    @Test()
    public void addLongLineOutsideField() {
        getAlign(2, 3).addLine(5, "new", "new", "new", "new", "new");
    }

    /** TEST ALIGN **/

    @Test()
    public void outputSetExisting() {
        FormatedTableBuilder align = getAlign(2, 3);
        align.set(1, 1, "new value");
        align.output(this::nothing);
    }

    @Test()
    public void outputAddShortColumn() {
        FormatedTableBuilder align = getAlign(2, 3);
        align.addColumn(1, "new");
        align.output(this::nothing);
    }

    @Test()
    public void outputAddShortColumnOutsideField() {
        FormatedTableBuilder align = getAlign(2, 3);
        align.addColumn(5, "new");
        align.output(this::nothing);
    }

    @Test()
    public void outputAddLongColumn() {
        FormatedTableBuilder align = getAlign(2, 3);
        align.addColumn(1, "new", "new", "new", "new", "new");
        assertEquals("00     new     01     \n" +
                "10     new     11     \n" +
                "20     new     21     \n" +
                "-      new     -      \n" +
                "-      new     -      \n", align.toString());
    }

    @Test()
    public void outputAddLongColumnOutsideField() {
        FormatedTableBuilder align = getAlign(2, 3);
        align.addColumn(5, "new", "new", "new", "new", "new");
        assertEquals("00     01     -     -     -     new     \n" +
                "10     11     -     -     -     new     \n" +
                "20     21     -     -     -     new     \n" +
                "-      -      -     -     -     new     \n" +
                "-      -      -     -     -     new     \n", align.toString());
    }

    @Test()
    public void outputAddShortLine() {
        FormatedTableBuilder align = getAlign(2, 3);
        align.addLine(1, "new");
        align.output(this::nothing);
    }

    @Test()
    public void outputAddShortLineOutsideField() {
        FormatedTableBuilder align = getAlign(2, 3);
        align.addLine(5, "new");
        assertEquals("00     01     \n" +
                "10     11     \n" +
                "20     21     \n" +
                "-      -      \n" +
                "-      -      \n" +
                "new    -      \n", align.toString());
    }

    @Test()
    public void outputAddLongLine() {
        FormatedTableBuilder align = getAlign(2, 3);
        align.addLine(1, "new", "new", "new", "new", "new");
        align.output(this::nothing);
    }

    @Test()
    public void outputAddLongLineOutsideField() {
        FormatedTableBuilder align = getAlign(2, 3);
        align.addLine(5, "new", "new", "new", "new", "new");
        align.output(this::nothing);
    }

    public void nothing(String s) {
//        System.out.println(s + "\n\n");
    }
}