package test.checkstyle.PropertyFollowedEmptyLine.java;

public class TestBean3 {

    private static final int VALUE1 = 0;
    private static int value2;

    private int value3;
    private int value4;

    private int value5;

    public static int getValue1() {
        return VALUE1;
    }

    public static int getValue2() {
        return value2;
    }

    public static void setValue2(int value2) {
        TestBean3.value2 = value2;
    }

    public int getValue3() {
        return value3;
    }

    public void setValue3(int value3) {
        this.value3 = value3;
    }

    public int getValue4() {
        return value4;
    }

    public void setValue4(int value4) {
        this.value4 = value4;
    }

    private static class TestBean3Internal {

        private int value8;
        private int value9;

        private int value10;

        public int getValue10() {
            return value10;
        }

        public void setValue10(int value10) {
            this.value10 = value10;
        }

        public int getValue8() {
            return value8;
        }

        public void setValue8(int value8) {
            this.value8 = value8;
        }

        public int getValue9() {
            return value9;
        }

        public void setValue9(int value9) {
            this.value9 = value9;
        }
    }
}

class TestBean3Neighbour {

    private int value5;

    private int value6;
    private int value7;

    public int getValue5() {
        return value5;
    }

    public void setValue5(int value5) {
        this.value5 = value5;
    }

    public int getValue6() {
        return value6;
    }

    public void setValue6(int value6) {
        this.value6 = value6;
    }

    public int getValue7() {
        return value7;
    }

    public void setValue7(int value7) {
        this.value7 = value7;
    }
}
