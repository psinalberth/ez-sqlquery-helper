package com.github.psinalberth.criteria.domain.predicate;

import com.github.psinalberth.criteria.domain.base.Predicate;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Is-null predicate implementation.
 * @see com.github.psinalberth.criteria.domain.base.Predicate
 */
@RequiredArgsConstructor
public class IsNullPredicate implements Predicate {

    private final String expression;

    @Override
    public String render(boolean isNegated) {

        final String operationClause = isNegated ? " is not null" : " is null";
        return expression + operationClause;
    }
}