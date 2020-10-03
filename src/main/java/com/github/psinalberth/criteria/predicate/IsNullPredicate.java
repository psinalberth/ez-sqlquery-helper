package com.github.psinalberth.criteria.predicate;

import com.github.psinalberth.criteria.Predicate;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class IsNullPredicate implements Predicate {

    private final String expression;

    @Override
    public String render(boolean isNegated) {
        return getExpression() + String.format(" is %s null", isNegated ? "not": "");
    }
}