package org.autotest.example.amazon.properties;

import org.aeonbits.owner.ConfigCache;

public class ReadProperties {
    public void readProperties() {
        GeneralConfig generalConfig = ConfigCache.getOrCreate(GeneralConfig.class);
        String url = generalConfig.url();
    }
}
