package test.checkstyle.PropertyFollowedEmptyLine.java;

public class TestBean1 {

    private static final int VALUE1 = 0;

    private static int value2 = 0;

    private int value3 = 0;

    private int[] value4 = {
        1, 2, 3, 4
    };

    public static int getValue1() {
        return VALUE1;
    }

    public static int getValue2() {
        return value2;
    }

    public static void setValue2(int value2) {
        TestBean1.value2 = value2;
    }

    public int getValue3() {
        return value3;
    }

    public void setValue3(int value3) {
        this.value3 = value3;
    }

    public int[] getValue4() {
        return value4;
    }

    public void setValue4(int[] value4) {
        this.value4 = value4;
    }
}
