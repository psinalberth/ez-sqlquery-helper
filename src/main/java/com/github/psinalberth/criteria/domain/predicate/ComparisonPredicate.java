package com.github.psinalberth.criteria.domain.predicate;

import lombok.RequiredArgsConstructor;

/**
 * Comparison predicate implementation.
 * @see com.github.psinalberth.criteria.domain.base.Predicate
 */
@RequiredArgsConstructor
public class ComparisonPredicate<Y> extends AbstractPredicate<Y> {

    private final Operator operator;
    private final String expression;
    private final Y rightHandOperand;

    public enum Operator {
        EQUAL {
            public String rendered() {
                return "=";
            }
        },
        NOT_EQUAL {
            public String rendered() {
                return "<>";
            }
        },
        GREATER_THAN {
            public String rendered() {
                return ">";
            }
        },
        LESS_THAN {
            public String rendered() {
                return "<";
            }
        },
        GREATER_THAN_OR_EQUAL {
            public String rendered() {
                return ">=";
            }
        },
        LESS_THAN_OR_EQUAL {
            public String rendered() {
                return "<=";
            }
        };

        public abstract String rendered();
    }

    public String render(boolean isNegated) {
        final String operationClause = isNegated ? " not " : " ";
        return expression + operationClause + operator.rendered() + " " + convertParam(rightHandOperand);
    }
}
