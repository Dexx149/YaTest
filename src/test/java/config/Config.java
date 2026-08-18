package config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class Config {

    private static final Properties PROPERTIES = new Properties();

    static {
        try (InputStream inputStream = Config.class
                .getClassLoader()
                .getResourceAsStream("application.properties")) {

            if (inputStream == null) {
                throw new IllegalStateException(
                        "application.properties not found"
                );
            }

            PROPERTIES.load(inputStream);

        } catch (IOException e) {
            throw new RuntimeException(
                    "Failed to load application.properties", e
            );
        }
    }

    private Config() {
    }

    public static String getBaseUrl() {
        return getRequiredProperty("base.url");
    }

    public static String getToken() {
        return getRequiredProperty("token");
    }

    private static String getRequiredProperty(String key) {
        String value = PROPERTIES.getProperty(key);

        if (value == null || value.isBlank()) {
            throw new IllegalStateException(
                    "Property '" + key + "' is not specified"
            );
        }

        return value;
    }
}