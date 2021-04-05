package com.github.psinalberth.criteria.domain.predicate;

import com.github.psinalberth.criteria.domain.base.Predicate;
import lombok.RequiredArgsConstructor;

/**
 * Not predicate implementation.
 * @see com.github.psinalberth.criteria.domain.base.Predicate
 */
@RequiredArgsConstructor
public class NotPredicate implements Predicate {

    private final Predicate restriction;

    @Override
    public String render(boolean isNegated) {
        return restriction.render(true);
    }
}