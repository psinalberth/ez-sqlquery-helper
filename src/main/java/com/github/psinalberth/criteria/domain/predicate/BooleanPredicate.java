package com.github.psinalberth.criteria.domain.predicate;

import com.github.psinalberth.criteria.domain.base.Predicate;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.List;

import java.util.stream.Collectors;

/**
 * Boolean predicate implementation.
 * @see com.github.psinalberth.criteria.domain.base.Predicate
 */
@RequiredArgsConstructor
public class BooleanPredicate<Y> extends AbstractPredicate<Y> {

    private final Operator operator;
    private final List<Predicate> predicates;

    public BooleanPredicate(Operator operator, @NonNull Predicate... predicates) {
       this(operator, Arrays.asList(predicates));
    }

    public enum Operator {
        AND {
            public String rendered() {
                return "and";
            }
        },
        OR {
            public String rendered() {
                return "or";
            }
        };

        public abstract String rendered();
    }

    @Override
    public String render(boolean isNegated) {

        final String operationClause = isNegated ? "not " : "";
        String stringPredicates = predicates.stream()
                .map(predicate -> predicate.render(false))
                .collect(Collectors.joining(" " + operator.rendered() + " "));

        return operationClause + "(" + stringPredicates + ")";
    }
}