package com.github.psinalberth.criteria.converter.spi;

import com.github.psinalberth.criteria.domain.base.ParamTypeConverter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class CalendarParamConverter implements ParamTypeConverter<Calendar> {

    final DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Override
    public String convert(Calendar param) {
        return "'" + formatter.format(param.getTime()) + "'";
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Calendar.class.isAssignableFrom(clazz);
    }
}
