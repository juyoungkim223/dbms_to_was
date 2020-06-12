package configuration;

import java.io.*;
import java.util.Set;

public class ContextGenerator {
    private ConfigEventListener listener;

    //contextloaderlistener create PropertyGenerator object
    public void registerConfigEventListener(ConfigEventListener listener) {
        this.listener = listener;
    }

    public void generate() throws IOException, InterruptedException {
        // perform any operation
        System.out.println("Performing callback before synchronous Task");
        // check if listener is registered.
        if (this.listener != null) {
            System.out.println("listener != null");
            listener.writeEvent();
        }
    }
}