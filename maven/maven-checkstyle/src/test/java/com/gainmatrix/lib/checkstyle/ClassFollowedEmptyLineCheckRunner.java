package com.gainmatrix.lib.checkstyle;

public class ClassFollowedEmptyLineCheckRunner {

    public static void main(String[] arguments) throws Exception {
        String configurationFile = getResourceFile("/test/checkstyle/ClassFollowedEmptyLine/config/checkstyle.xml");
        String javaFolder = getResourceFile("/test/checkstyle/ClassFollowedEmptyLine/java/");

        String[] checkstyleArguments = { "-c", configurationFile, "-r", javaFolder };

        com.puppycrawl.tools.checkstyle.Main.main(checkstyleArguments);
    }

    private static String getResourceFile(String classpath) {
        return ClassFollowedEmptyLineCheckRunner.class.getResource(classpath).getFile();
    }

}
