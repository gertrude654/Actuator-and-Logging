package com.example.LoggingAndActuator.logging;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Main {

    public static void main(String args[]) {
        log.info("Info level");
        log.error("Error level");
    }
}
