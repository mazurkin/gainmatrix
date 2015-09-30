package com.gainmatrix.lib.benchmark;

import com.google.caliper.Runner;
import com.google.caliper.SimpleBenchmark;
import org.apache.commons.lang.SystemUtils;

/**
 * Sample benchmark based on Google's Caliper framework
 * @see <a href="http://code.google.com/p/caliper/wiki/JavaMicrobenchmarks">What is Java micro-benchmark</a>
 * @see <a href="http://code.google.com/p/caliper/wiki/BestPractices">Best practices to write a micro-benchmark</a>
 * @see <a href="http://code.google.com/p/caliper/wiki/CommandLineOptions">Command line options</a>
 */
public class SampleBenchmark extends SimpleBenchmark {

    public void timeNanoTime(int reps) {
        for (int i = 0; i < reps; i++) {
            System.nanoTime();
        }
    }

    public void timeCurrentTimeMillis(int reps) {
        for (int i = 0; i < reps; i++) {
            System.currentTimeMillis();
        }
    }

    public static void main(String[] arguments) throws Exception {
        StringBuilder java = new StringBuilder();
        java.append(SystemUtils.JAVA_HOME);
        java.append(SystemUtils.FILE_SEPARATOR);
        java.append("bin");
        java.append(SystemUtils.FILE_SEPARATOR);
        java.append(SystemUtils.IS_OS_WINDOWS ? "java.exe" : "java");

        String[] effectiveArguments = {
            "--warmupMillis", "3000",
            "--runMillis", "3000",
            "--vm", java.toString(),
            "--timeUnit", "us",
            "--trials", "3",
            SampleBenchmark.class.getCanonicalName()
        };

        Runner.main(effectiveArguments);
    }

}
