package com.gainmatrix.lib.checkstyle;

public class AnnotationSeparateLineCheckRunner {

    public static void main(String[] arguments) throws Exception {
        String configurationFile = getResourceFile("/test/checkstyle/AnnotationSeparateLine/config/checkstyle.xml");
        String javaFolder = getResourceFile("/test/checkstyle/AnnotationSeparateLine/java/");

        String[] checkstyleArguments = { "-c", configurationFile, "-r", javaFolder };

        com.puppycrawl.tools.checkstyle.Main.main(checkstyleArguments);
    }

    private static String getResourceFile(String classpath) {
        return AnnotationSeparateLineCheckRunner.class.getResource(classpath).getFile();
    }

}
