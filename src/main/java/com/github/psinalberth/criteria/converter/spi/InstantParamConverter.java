package com.github.psinalberth.criteria.converter.spi;

import com.github.psinalberth.criteria.domain.base.ParamTypeConverter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class InstantParamConverter implements ParamTypeConverter<Date> {

    final DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Override
    public String convert(Date param) {
        return "'" + formatter.format(param) + "'";
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Date.class.isAssignableFrom(clazz);
    }
}