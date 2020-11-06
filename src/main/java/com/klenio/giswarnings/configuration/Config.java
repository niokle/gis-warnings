package com.klenio.giswarnings.configuration;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
@NoArgsConstructor
public class Config {
    @Value("${base.url}")
    private String baseUrl;
}
