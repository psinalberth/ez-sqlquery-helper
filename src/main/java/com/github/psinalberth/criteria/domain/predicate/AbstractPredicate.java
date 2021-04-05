package com.github.psinalberth.criteria.domain.predicate;

import com.github.psinalberth.criteria.converter.ParamConverterProvider;
import com.github.psinalberth.criteria.domain.base.ParamTypeConverter;
import com.github.psinalberth.criteria.domain.base.Predicate;

/**
 * Abstract predicate.
 */
public abstract class AbstractPredicate<Y> implements Predicate {

    /**
     * Converts given param from its original type to plain string text.
     *
     * @param param Value to be converted.
     * @return Converted value.
     */
    protected String convertParam(Y param) {
        ParamTypeConverter render = ParamConverterProvider.of(param.getClass());
        return render.convert(param);
    }
}