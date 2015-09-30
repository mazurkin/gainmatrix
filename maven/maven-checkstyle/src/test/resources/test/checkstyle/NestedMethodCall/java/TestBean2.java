package test.checkstyle.NestedMethodCall.java;

public class TestBean2 {

    public TestBean2(int a, int b) {
    }

    public TestBean2() {
    }

    public TestBean2(int a) {
    }

    public TestBean2(int a, TestBean2 bean2) {
    }

    public static TestBean2 legalMethodCall1() {
        TestBean1 testBean1 = new TestBean1();

        return new TestBean2(1, testBean1.call2());
    }

    public static TestBean2 legalMethodCall2() {
        return new TestBean2(1, new TestBean2());
    }

    public static TestBean2 illegalMethodCall1() {
        TestBean1 testBean1 = new TestBean1();

        return new TestBean2(1, testBean1.call1(1));
    }

    public static TestBean2 illegalMethodCall2() {
        return new TestBean2(1, new TestBean2(2));
    }

}
