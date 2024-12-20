package com.lc.demostarterspringbootstarter.conf;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "digest")
public class Setting {
    private String type;

    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
}
