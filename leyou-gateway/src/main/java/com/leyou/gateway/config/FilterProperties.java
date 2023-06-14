package com.leyou.gateway.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author: Administrator
 * @Date: 2020/2/13 14:12
 * @Description:
 */
@Component
@ConfigurationProperties(prefix = "leyou.filter")
public class FilterProperties {

    @Autowired
    private JwtProperties jwtProperties;

    private List<String> allowPaths;

    public List<String> getAllowPaths() {
        return allowPaths;
    }

    public void setAllowPaths(List<String> allowPaths) {
        this.allowPaths = allowPaths;
    }
}
