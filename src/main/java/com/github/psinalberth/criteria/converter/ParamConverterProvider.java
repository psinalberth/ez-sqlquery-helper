package com.github.psinalberth.criteria.converter;

import com.github.psinalberth.criteria.converter.spi.CharSequenceParamConverter;
import com.github.psinalberth.criteria.domain.base.ParamTypeConverter;

import java.util.ArrayList;
import java.util.List;
import java.util.ServiceLoader;

public final class ParamConverterProvider {

    private ParamConverterProvider() {

    }

    public static List<ParamTypeConverter<?>> availableProviders() {

        ServiceLoader<ParamTypeConverter> loader = ServiceLoader.load(ParamTypeConverter.class);
        List<ParamTypeConverter<?>> types = new ArrayList<>();
        loader.forEach(types::add);

        return types;
    }

    public static ParamTypeConverter<?> defaultProvider() {
        return new CharSequenceParamConverter();
    }

    public static ParamTypeConverter<?> of(final Class<?> clazz) {

        List<ParamTypeConverter<?>> types = availableProviders();

        return types.stream().filter(type -> type.supports(clazz))
                .findAny()
                .orElseThrow(() -> new IllegalStateException("No support for " + clazz));
    }
}