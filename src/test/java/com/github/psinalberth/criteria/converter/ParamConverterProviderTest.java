package com.github.psinalberth.criteria.converter;

import com.github.psinalberth.criteria.converter.spi.CharSequenceParamConverter;
import com.github.psinalberth.criteria.converter.spi.NumberParamConverter;
import com.github.psinalberth.criteria.domain.base.ParamTypeConverter;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("Test suite for \"ParamConverterProvider\"")
class ParamConverterProviderTest {

    @Test
    @DisplayName("Should get all available converter providers")
    void shouldGetAvailableProviders() {

        List<ParamTypeConverter<?>> converters = ParamConverterProvider.availableProviders();

        assertThat(converters)
                .isNotEmpty()
                .hasSizeGreaterThan(4)
                .hasOnlyElementsOfType(ParamTypeConverter.class);
    }

    @Test
    @DisplayName("Should get default provider")
    void shouldGetDefaultProvider() {

        ParamTypeConverter<?> converter = ParamConverterProvider.defaultProvider();

        assertThat(converter)
                .isNotNull()
                .isInstanceOf(CharSequenceParamConverter.class);
    }

    @Test
    @DisplayName("Should get NumberParamConverterProvider provider")
    void shouldGetNumberParamConverterProvider() {

        ParamTypeConverter<?> converter = ParamConverterProvider.of(BigDecimal.class);

        assertThat(converter)
                .isNotNull()
                .isInstanceOf(NumberParamConverter.class);
    }

    @Test
    @DisplayName("Should throw error when get Void type provider.")
    void shouldThrowErrorWhenGetVoidConverterProvider() {

        assertThatThrownBy(() -> ParamConverterProvider.of(Void.class))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("No support for " + Void.class);
    }
}