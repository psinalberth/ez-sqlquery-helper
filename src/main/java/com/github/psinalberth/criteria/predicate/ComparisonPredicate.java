package com.github.psinalberth.criteria.predicate;

import com.github.psinalberth.criteria.Predicate;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

@Getter
@RequiredArgsConstructor
public class ComparisonPredicate implements Predicate, Serializable {

    private final Operator operator;
    private final Object leftHandOperand;
    private final Object rightHandOperand;

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

    private String getFormattedValue(Object value) {

        if (value instanceof String)
            return String.format("'%s'", value);

        return String.valueOf(value);
    }

    public String render(boolean isNegated) {
        return getLeftHandOperand() + " " + getOperator().rendered() + " " + getFormattedValue(getRightHandOperand());
    }
}
