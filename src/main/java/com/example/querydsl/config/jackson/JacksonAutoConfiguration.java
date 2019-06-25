package com.example.querydsl.config.jackson;

import ch.mfrey.jackson.antpathfilter.AntPathFilterMixin;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import java.util.TimeZone;

/**
 * Created by hanxiao on 2017/4/18.
 */
@Configuration
@ConditionalOnClass({ObjectMapper.class,
        Jackson2ObjectMapperBuilder.class,
        AntPathFilterMixin.class,
        CustomModule.class})
@EnableConfigurationProperties({JacksonMixProperties.class})
public class JacksonAutoConfiguration {

    @ConditionalOnMissingBean
    @Bean
    public Jackson2ObjectMapperBuilder jackson2ObjectMapperBuilder(JacksonMixProperties jacksonMixProperties) {
        return new Jackson2ObjectMapperBuilder()
                .modules(new CustomModule())
                .timeZone(TimeZone.getDefault())
                .failOnEmptyBeans(false)
                .failOnUnknownProperties(false)
                .mixIns(jacksonMixProperties.mixMap())
                .featuresToDisable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
                .propertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
    }

    @ConditionalOnMissingBean
    @Bean
    public ObjectMapper objectMapper(JacksonMixProperties jacksonMixProperties) {
        return jackson2ObjectMapperBuilder(jacksonMixProperties).build();
    }

}
