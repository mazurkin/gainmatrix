package com.gainmatrix.lib.checkstyle;

public class PropertyFollowedEmptyLineCheckRunner {

    public static void main(String[] arguments) throws Exception {
        String configurationFile = getResourceFile("/test/checkstyle/PropertyFollowedEmptyLine/config/checkstyle.xml");
        String javaFolder = getResourceFile("/test/checkstyle/PropertyFollowedEmptyLine/java/");

        String[] checkstyleArguments = { "-c", configurationFile, "-r", javaFolder };

        com.puppycrawl.tools.checkstyle.Main.main(checkstyleArguments);
    }

    private static String getResourceFile(String classpath) {
        return PropertyFollowedEmptyLineCheckRunner.class.getResource(classpath).getFile();
    }

}
