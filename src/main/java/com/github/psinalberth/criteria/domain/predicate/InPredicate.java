package com.github.psinalberth.criteria.domain.predicate;

import com.github.psinalberth.criteria.domain.base.Predicate;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * In predicate implementation.
 * @see com.github.psinalberth.criteria.domain.base.Predicate
 */
@RequiredArgsConstructor
public class InPredicate<Y> extends AbstractPredicate<Y> implements Predicate {

    private final String expression;
    private final List<Y> values;

    public InPredicate(String expression, Y... values) {
        this(expression, Arrays.asList(values));
    }

    @Override
    public String render(boolean isNegated) {

        final String operationClause = isNegated ? " not in " : " in ";
        String stringPredicates = values.stream()
                .map(this::convertParam)
                .collect(Collectors.joining(", "));

        return expression + operationClause + "(" + stringPredicates + ")";
    }
}