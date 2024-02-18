package org.autotest.example.amazon;

import org.aeonbits.owner.ConfigCache;
import org.autotest.example.amazon.properties.GeneralConfig;
import org.junit.jupiter.api.BeforeAll;

public class BaseTest {
    private static String url;
    private static String userName;
    private static String password;

    @BeforeAll
    public static void read() {
        GeneralConfig generalConfig = ConfigCache.getOrCreate(GeneralConfig.class);
        url = generalConfig.url();
        userName = generalConfig.userName();
        password = System.getProperty("password");
    }

    public static String getUserName() {
        return userName;
    }
}
