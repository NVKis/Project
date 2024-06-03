package utils;
import lombok.experimental.UtilityClass;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;
import java.util.Objects;

import static java.nio.charset.StandardCharsets.UTF_8;

@UtilityClass
public class PropertyLoader {

    private static final String PROPERTIES_FILE = "/application.properties";
    private static final Properties PROPERTIES = getPropertiesInstance();

    public static Properties getPropertiesInstance(){
        var instance = new Properties();

        try (
                var resourceStream = PropertyLoader.class.getResourceAsStream(PROPERTIES_FILE);
                var inputStream = new InputStreamReader(Objects.requireNonNull(resourceStream), UTF_8);
        ) {
            instance.load(inputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return instance;
    }

    public static String loadSystemProperty(String propertyName, String defaultValue) {
        String value = PROPERTIES.getProperty(propertyName);
        return value != null ? value : defaultValue;
    }

    public static String loadSystemProperty(String propertyName) {
        return PROPERTIES.getProperty(propertyName);
    }

}
