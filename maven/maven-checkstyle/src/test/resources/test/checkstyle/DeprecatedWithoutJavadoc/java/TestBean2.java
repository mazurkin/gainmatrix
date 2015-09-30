package test.checkstyle.DeprecatedWithoutJavadoc.java;

@Deprecated
public class TestBean2 {

    @Deprecated
    private String value1;

    @Deprecated
    public void method1() {

    }

    // Simple comment is not enough
    @Deprecated
    public void method2() {

    }

    /* Simple comment is not enough */
    @Deprecated
    public void method3() {

    }

    /**
     * That's good
     */
    @Deprecated
    public void method4() {

    }

}
