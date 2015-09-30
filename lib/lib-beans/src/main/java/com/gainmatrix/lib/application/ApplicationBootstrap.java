package com.gainmatrix.lib.application;

/**
 * A simple abstraction for application. The goal is to implement an application as a class -
 * not the static main() function.
 */
public interface ApplicationBootstrap {

    /**
     * Init application. Function is called once with application arguments when application instance is just created.
     * @param arguments Application arguments as a string array
     * @throws Exception Exception on any error
     */
    void init(String[] arguments) throws Exception;

    /**
     * Run application as a function. Can me called multiple times between init() and destroy() calls
     * @return Return code. Usually exit code 0 means success and any other code means error
     * @throws Exception Exception on any error
     */
    int run() throws Exception;

    /**
     * Destroy application. Function is called once at the end when the application instance is not required any more.
     */
    void destroy();

}
