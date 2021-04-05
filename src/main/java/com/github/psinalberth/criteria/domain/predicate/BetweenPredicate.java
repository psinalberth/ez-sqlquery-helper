package com.github.psinalberth.criteria.domain.predicate;

import lombok.RequiredArgsConstructor;

/**
 * Between predicate implementation.
 * @see com.github.psinalberth.criteria.domain.base.Predicate
 */
@RequiredArgsConstructor
public class BetweenPredicate<Y> extends AbstractPredicate<Y> {

    private final String expression;
    private final Y lowerBound;
    private final Y upperBound;

    @Override
    public String render(boolean isNegated) {

        final String operationClause = isNegated ? " not between " : " between ";
        return expression + operationClause + convertParam(lowerBound) + " and " + convertParam(upperBound);
    }
}