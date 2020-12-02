package com.practice.demo.config;

import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.concurrent.TimeUnit;

public class Constants {

    public static final String NONCE_GROUP_KEY = "request:nonce:{0}";
    public static final String EMPTY_NONCE_VALUE = "";

    public static final int REQUEST_TIMEOUT_DURATION = 60;
    public static final TemporalUnit REQUEST_TIMEOUT_UNIT = ChronoUnit.SECONDS;

    private Constants() {}


}
