package test.checkstyle.DeprecatedWithoutJavadoc.java;

/**
 * This class is deprecated - use other one
 */
@Deprecated
public class TestBean1 {

    /**
     * This property is deprecated - use other one
     */
    private String value1;

    /**
     * This property is deprecated - use other one
     */
    @Deprecated
    public void method1() {

    }

    @SuppressWarnings("other annotation")
    public void method2() {

    }

}
