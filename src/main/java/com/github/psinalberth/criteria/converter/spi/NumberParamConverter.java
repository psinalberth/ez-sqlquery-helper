package com.github.psinalberth.criteria.converter.spi;

import com.github.psinalberth.criteria.domain.base.ParamTypeConverter;

public class NumberParamConverter implements ParamTypeConverter<Number> {

    @Override
    public String convert(Number param) {
        return param.toString();
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Number.class.isAssignableFrom(clazz);
    }
}
