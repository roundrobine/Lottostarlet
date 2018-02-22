package com.lottostarlet.backendtest.app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.xml.parsers.SAXParserFactory;

@Configuration
class SAXConfig {

    /**
     * @return A SAXParserFactory that is not namespace aware.
     */
    @Bean
    SAXParserFactory saxParserFactory() {
        return SAXParserFactory.newInstance();
    }

}
