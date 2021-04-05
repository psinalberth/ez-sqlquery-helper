package com.github.psinalberth.criteria.converter.spi;

import com.github.psinalberth.criteria.domain.base.ParamTypeConverter;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.regex.Pattern;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Test suite for \"CalendarParamConverter\"")
class CalendarParamConverterTest {

    @Test
    @DisplayName("Should convert Calendar type to String")
    void shouldConvertCalendarParam() {

        ParamTypeConverter<Calendar> typeConverter = new CalendarParamConverter();
        Calendar calendar = Calendar.getInstance();
        Pattern pattern = Pattern.compile("'\\d{4}-\\d{2}-\\d{2}\\s\\d{2}:\\d{2}:\\d{2}'");

        String result = typeConverter.convert(calendar);
        assertThat(result)
                .isNotEmpty()
                .matches(pattern);
    }
}