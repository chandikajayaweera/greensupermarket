package com.greensupermarket.util;

// EmailConfiguration.java
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class EmailConfiguration {

    private Properties emailProperties;

    public EmailConfiguration() throws IOException {
        this.emailProperties = loadEmailProperties();
    }

    public Properties getEmailProperties() {
        return emailProperties;
    }

    private Properties loadEmailProperties() throws IOException {
        Properties properties = new Properties();
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("config/email.properties");
        properties.load(inputStream);
        return properties;
    }
}
