package com.github.psinalberth.criteria.predicate;

import com.github.psinalberth.criteria.Predicate;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

@Getter
@RequiredArgsConstructor
public class NotPredicate implements Predicate, Serializable {

    private final Predicate restriction;

    @Override
    public String render(boolean isNegated) {
        return getRestriction().render(true);
    }
}