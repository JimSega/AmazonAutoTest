package org.autotest.example.amazon.properties;

import org.aeonbits.owner.Config;

@Config.Sources({
        "classpath:general.properties"
})
public interface GeneralConfig extends Config {
    @Config.Key("url.base")
    String url();

    String userName();
}
