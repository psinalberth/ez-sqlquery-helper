package com.github.psinalberth.criteria.domain.predicate;

import lombok.RequiredArgsConstructor;

/**
 * Like predicate implementation.
 * @see com.github.psinalberth.criteria.domain.base.Predicate
 */
@RequiredArgsConstructor
public class LikePredicate extends AbstractPredicate<CharSequence> {

    private final String expression;
    private final CharSequence y;

    public String render(boolean isNegated) {

        final String operationClause = isNegated ? " not like " : " like ";
        return expression + operationClause + convertParam(y);
    }
}