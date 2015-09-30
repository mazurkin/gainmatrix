package com.gainmatrix.lib.checkstyle;

public class NestedMethodCallCheckRunner {

    public static void main(String[] arguments) throws Exception {
        String configurationFile = getResourceFile("/test/checkstyle/NestedMethodCall/config/checkstyle.xml");
        String javaFolder = getResourceFile("/test/checkstyle/NestedMethodCall/java/");

        String[] checkstyleArguments = { "-c", configurationFile, "-r", javaFolder };

        com.puppycrawl.tools.checkstyle.Main.main(checkstyleArguments);
    }

    private static String getResourceFile(String classpath) {
        return NestedMethodCallCheckRunner.class.getResource(classpath).getFile();
    }

}
