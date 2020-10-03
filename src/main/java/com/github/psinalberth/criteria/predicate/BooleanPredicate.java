package com.github.psinalberth.criteria.predicate;

import com.github.psinalberth.criteria.Predicate;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Getter
@RequiredArgsConstructor
public class BooleanPredicate implements Predicate, Serializable {

    private final Operator operator;
    private final List<Predicate> predicates;

    public BooleanPredicate(Operator operator, Predicate... predicates) {
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

        Objects.requireNonNull(getPredicates(), "Predicates cannot be null");

        return  (isNegated ? "not " : "") +
                "(" + this.getPredicates()
                .stream()
                .map(predicate -> predicate.render(false))
                .collect(Collectors.joining(" " + getOperator().rendered() + " ")) + ")";
    }
}