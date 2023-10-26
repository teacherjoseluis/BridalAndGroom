package com.sibme.testUtilities;

// import org.junit.Assert;

import junit.framework.Assert;
import org.yaml.snakeyaml.Yaml;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;
import java.util.Properties;

public class FileOperations {

    /**
     * //	 * @param filepath  file location of the properties file
     *
     * @param key send specific key to get that value
     * @return keyValue value of the key
     */
    public String getValueFromPropertyFile(String filePath, String key) {
        String keyValue = null;
        Properties prop = new Properties();
        try {
            prop.load(new FileInputStream(filePath));
            keyValue = prop.getProperty(key);
        } catch (IOException e) {
            e.printStackTrace();
            Assert.fail();
        }
        return keyValue;
    }

    public String getDataFromYaml(String mainKey, String subKey, String fileName) {
        try {
            // Create a YAML object
            Yaml yaml = new Yaml();

            // Load the YAML data from a file
            FileInputStream file = new FileInputStream("src/resources/TestData/" + fileName + ".yml");
            Map<String, Object> data = (Map<String, Object>) yaml.load(file);

            // Extract the values from the YAML data
            Map<String, Object> yaml_data = (Map<String, Object>) data.get(mainKey);
            String value = yaml_data.get(subKey).toString();
            return value;
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

}
