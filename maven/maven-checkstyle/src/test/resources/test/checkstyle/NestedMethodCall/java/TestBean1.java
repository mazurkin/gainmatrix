package test.checkstyle.NestedMethodCall.java;

public class TestBean1 {

    public int call1(int value) {
        return value;
    }

    public int call2() {
        return 0;
    }

    public void legalMethodCall1() {
        call1(call2());
    }

    public void legalMethodCall2() {
        call1(0);
    }

    public void illegalMethodCall1() {
        call1(call1(0));
    }

    public void illegalMethodCall2() {
        call1(3 * call1(0));
    }

}
