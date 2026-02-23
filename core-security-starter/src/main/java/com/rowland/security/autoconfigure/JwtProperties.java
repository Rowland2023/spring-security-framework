package com.rowland.security.autoconfigure;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "rowland.security.jwt")
public record JwtProperties(
    String secretKey,
    long expiration
) {}