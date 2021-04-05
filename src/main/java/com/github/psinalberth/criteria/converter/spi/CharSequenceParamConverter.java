package com.github.psinalberth.criteria.converter.spi;

import com.github.psinalberth.criteria.domain.base.ParamTypeConverter;

public class CharSequenceParamConverter implements ParamTypeConverter<CharSequence> {

    @Override
    public String convert(CharSequence param) {
        return "'" + param + "'";
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return CharSequence.class.isAssignableFrom(clazz);
    }
}