package edu.nikitazubov.shortli.config;

import nz.net.ultraq.thymeleaf.layoutdialect.LayoutDialect;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ThymeleafConfig {
    @Value("${shortli.domain.name}")
    private String domainName;

    @Value("${shortli.ads.enabled}")
    private boolean adsEnabled;

    @Bean
    public String domainName() {
        return domainName;
    }

    @Bean
    public boolean adsEnabled() {
        return adsEnabled;
    }

    @Bean
    public LayoutDialect layoutDialect() {
        return new LayoutDialect();
    }
}
