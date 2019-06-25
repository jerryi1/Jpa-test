package com.example.querydsl.config.jackson.ant;

import ch.mfrey.jackson.antpathfilter.AntPathPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.http.converter.json.MappingJacksonValue;

/**
 * Created by hanxiao on 2017/5/12.
 */
public class AntPathFilterMappingJacksonValue extends MappingJacksonValue {

    public AntPathFilterMappingJacksonValue(final Object value) {
        super(value);
        setFilters(new SimpleFilterProvider().addFilter("antPathFilter", new AntPathPropertyFilter("**")));
    }

    public AntPathFilterMappingJacksonValue(final Object value, final String... filters) {
        super(value);
        setFilters(new SimpleFilterProvider().addFilter("antPathFilter", new AntPathPropertyFilter(filters)));
    }
}
