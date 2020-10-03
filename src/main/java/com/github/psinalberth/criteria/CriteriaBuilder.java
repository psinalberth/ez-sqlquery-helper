package com.github.psinalberth.criteria;

import java.util.Collection;
import java.util.List;

public interface CriteriaBuilder {

    // Arithmetical predicates

    Predicate equal(String expression, Object y);

    Predicate notEqual(String expression, Object y);

    Predicate gt(String expression, Number y);

    Predicate greaterThan(String expression, Number y);

    Predicate greaterThanOrEqualTo(String expression, Number y);

    Predicate ge(String expression, Number y);

    Predicate lt(String expression, Number y);

    Predicate lessThan(String expression, Number y);

    Predicate le(String expression, Number y);

    Predicate lessThanOrEqualTo(String expression, Number y);

    // Boolean predicates

    Predicate and(Predicate... predicates);

    Predicate or(Predicate... predicates);

    Predicate isNull(String expression);

    Predicate not(Predicate predicate);

    // String predicates

    Predicate between(String expression, Object lowerBound, Object upperBound);

    Predicate between(String expression, Object lowerBound, Object upperBound, boolean useTimestamp);

    Predicate like(String expression, String value);

    Predicate in(String expression, List<Object> values);

    Predicate in(String expression, Object... values);

    String generateSql(Collection<Predicate> predicates);
}