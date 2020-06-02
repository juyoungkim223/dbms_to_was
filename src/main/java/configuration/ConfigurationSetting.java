package configuration;

import org.springframework.core.io.ClassPathResource;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.Set;

//external jar need properties file
public class ConfigurationSetting implements Configuration {
    static final String dbConfig = "dbConfigFile";
    @Override
    public ConfigFileType getFile() throws IOException {
        //TODO class path
        try {
            ClassPathResource configFile = new ClassPathResource("application.properties");
            File file = configFile.getFile();
            Scanner reader = new Scanner(file);
            String dbConfigFile = "";
            while (reader.hasNextLine()) {
                String s = reader.nextLine();
                if(getConfigFileKey(s, dbConfig).equals(dbConfig)) {
                   dbConfigFile = getConfigFileValue(s);
                   break;
                }
            }
            fileRead(dbConfigFile);
        } catch (FileNotFoundException e) {
            System.out.println("application.properties file not found!");
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ConfigFileType.Properties;
    }

    @Override
    public String getKey(String text) {
        return "";
    }

    @Override
    public String getValue(String key) {
        //key.su
        return "";
    }

    @Override
    public Set<String> getKeys() {
        return null;
    }

    public String getConfigFileKey(String text, String findText) {
        for(char c : text.toCharArray()) {
            if(c == ' ') text = text.replaceFirst(" ", "");
            else break;
        }
        return text.substring(0, findText.length());
    }

    public String getConfigFileValue(String text) {
        int delimiterIdx = 0;
        for(int i = 0; i < text.length(); i++) {
            if(text.charAt(i) == '=') {
                delimiterIdx = i;
                break;
            }
        }
        return text.substring(delimiterIdx + 1);
    }

    public void fileRead(String fileName) {
        try {
            ClassPathResource configFile = new ClassPathResource(fileName);
            File file = configFile.getFile();
            Scanner myReader = new Scanner(file);
            while (myReader.hasNextLine()) {
                System.out.println(myReader.nextLine());
            }
        } catch (FileNotFoundException e) {
            System.out.println(fileName + "file not found!!!!!!!!");
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
