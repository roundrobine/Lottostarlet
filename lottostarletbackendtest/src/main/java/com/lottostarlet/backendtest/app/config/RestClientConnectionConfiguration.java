package com.lottostarlet.backendtest.app.config;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.web.client.RestTemplateCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;


@Configuration
class RestClientConnectionConfiguration implements RestTemplateCustomizer {

    private static final Logger LOG = LogManager.getLogger();

    @Override
    public void customize(RestTemplate restTemplate) {
        LOG.info("Customizing rest template {}");
        restTemplate.setRequestFactory(clientHttpRequestFactory());
    }

    @Bean
    HttpClient httpClient() {
        return HttpClientBuilder.create().build();
    }

    @Bean
    ClientHttpRequestFactory clientHttpRequestFactory() {
        return new HttpComponentsClientHttpRequestFactory(httpClient());
    }

}
