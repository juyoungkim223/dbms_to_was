package com.common.configuration;

import java.io.IOException;

public interface ConfigEventListener {
    void writeEvent() throws IOException, InterruptedException;
}
