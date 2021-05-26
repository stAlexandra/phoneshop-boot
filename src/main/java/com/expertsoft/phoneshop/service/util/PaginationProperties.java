package com.expertsoft.phoneshop.service.util;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "phoneshop.pagination")
public class PaginationProperties {
    private Integer plpMaxPages;

    public Integer getPlpMaxPages() {
        return plpMaxPages;
    }

    public void setPlpMaxPages(Integer plpMaxPages) {
        this.plpMaxPages = plpMaxPages;
    }
}
