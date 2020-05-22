package configuration;

import java.io.IOException;
import java.util.Properties;
import java.util.Set;

public interface Configuration {
    enum ConfigFileType {
        Properties
    }
    ConfigFileType getFile() throws IOException;

    String getKey(String text);

    String getValue(String key);

    Set<String> getKeys();


}
