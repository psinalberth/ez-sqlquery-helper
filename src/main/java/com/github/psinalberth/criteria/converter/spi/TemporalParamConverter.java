package com.github.psinalberth.criteria.converter.spi;

import com.github.psinalberth.criteria.domain.base.ParamTypeConverter;

import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.Temporal;
import java.util.Locale;

public class TemporalParamConverter implements ParamTypeConverter<Temporal> {

    final DateTimeFormatter formatter = DateTimeFormatter
            .ofPattern("yyyy-MM-dd", Locale.getDefault()).withZone(ZoneId.systemDefault());

    @Override
    public String convert(Temporal param) {
        return "'" + formatter.format(param) + "'";
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Temporal.class.isAssignableFrom(clazz);
    }
}
