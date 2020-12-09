package com.github.psinalberth.criteria.predicate;

import com.github.psinalberth.criteria.Predicate;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class InPredicate implements Predicate {

    private final String expression;
    private final List<Object> values;

    public InPredicate(String expression, Object... values) {
        this.expression = expression;
        this.values = Arrays.asList(values);
    }

    @Override
    public String render(boolean isNegated) {
        return getExpression() +
                (isNegated ? " not" : "") +
                " in (" +
                getValues().stream()
                        .map(value -> {
                            if (value instanceof String)
                                return String.format("'%s'", value);
                            return String.valueOf(value);
                        })
                    .collect(Collectors.joining(", ")) + ")";
    }
}