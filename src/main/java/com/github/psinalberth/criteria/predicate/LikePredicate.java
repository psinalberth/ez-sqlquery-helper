package com.github.psinalberth.criteria.predicate;

import com.github.psinalberth.criteria.Predicate;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class LikePredicate implements Predicate {

    private final String expression;
    private final String y;

    public String render(boolean isNegated) {
        return String.format("%s %s %s '%s'",
                getExpression(), isNegated ? "not" : "", "like", getY());
    }
}