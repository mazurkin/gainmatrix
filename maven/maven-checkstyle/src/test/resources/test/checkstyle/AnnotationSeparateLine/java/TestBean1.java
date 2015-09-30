package test.checkstyle.AnnotationSeparateLine.java;

@Deprecated
public class TestBean1 {

    @Deprecated
    private int value;

    @Deprecated
    public int call1(
        @Deprecated
        int value
    ) {
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
