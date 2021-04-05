package com.github.psinalberth.criteria.converter.spi;

import com.github.psinalberth.criteria.domain.base.ParamTypeConverter;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Pattern;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Test suite for \"InstantParamConverter\"")
class InstantParamConverterTest {

    @Test
    @DisplayName("Should convert Instant type to String")
    void shouldConvertCalendarParam() {

        ParamTypeConverter<Date> typeConverter = new InstantParamConverter();
        Calendar calendar = Calendar.getInstance();
        Pattern pattern = Pattern.compile("'\\d{4}-\\d{2}-\\d{2}\\s\\d{2}:\\d{2}:\\d{2}'");

        String result = typeConverter.convert(calendar.getTime());
        assertThat(result)
                .isNotEmpty()
                .matches(pattern);
    }
}