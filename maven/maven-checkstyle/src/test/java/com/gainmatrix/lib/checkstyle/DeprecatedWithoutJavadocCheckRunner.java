package com.gainmatrix.lib.checkstyle;

public class DeprecatedWithoutJavadocCheckRunner {

    public static void main(String[] arguments) throws Exception {
        String configurationFile = getResourceFile("/test/checkstyle/DeprecatedWithoutJavadoc/config/checkstyle.xml");
        String javaFolder = getResourceFile("/test/checkstyle/DeprecatedWithoutJavadoc/java/");

        String[] checkstyleArguments = { "-c", configurationFile, "-r", javaFolder };

        com.puppycrawl.tools.checkstyle.Main.main(checkstyleArguments);
    }

    private static String getResourceFile(String classpath) {
        return DeprecatedWithoutJavadocCheck.class.getResource(classpath).getFile();
    }

}
