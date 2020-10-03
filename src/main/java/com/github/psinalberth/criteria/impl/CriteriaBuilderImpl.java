package com.github.psinalberth.criteria.impl;

import com.github.psinalberth.criteria.CriteriaBuilder;
import com.github.psinalberth.criteria.Predicate;
import com.github.psinalberth.criteria.predicate.*;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class CriteriaBuilderImpl implements CriteriaBuilder {

    public Predicate equal(String expression, Object y) {
        return new ComparisonPredicate(ComparisonPredicate.Operator.EQUAL, expression, y);
    }

    public Predicate notEqual(String expression, Object y) {
        return new ComparisonPredicate(ComparisonPredicate.Operator.NOT_EQUAL, expression, y);
    }

    @Override
    public Predicate greaterThan(String expression, Number number) {
        return new ComparisonPredicate(ComparisonPredicate.Operator.GREATER_THAN, expression, number);
    }

    @Override
    public Predicate gt(String expression, Number number) {
        return new ComparisonPredicate(ComparisonPredicate.Operator.GREATER_THAN, expression, number);
    }

    @Override
    public Predicate ge(String expression, Number number) {
        return new ComparisonPredicate(ComparisonPredicate.Operator.GREATER_THAN_OR_EQUAL, expression, number);
    }

    @Override
    public Predicate greaterThanOrEqualTo(String expression, Number number) {
        return ge(expression, number);
    }

    @Override
    public Predicate lt(String expression, Number number) {
        return new ComparisonPredicate(ComparisonPredicate.Operator.LESS_THAN, expression, number);
    }

    @Override
    public Predicate lessThan(String expression, Number number) {
        return lt(expression, number);
    }

    @Override
    public Predicate le(String expression, Number number) {
        return new ComparisonPredicate(ComparisonPredicate.Operator.LESS_THAN_OR_EQUAL, expression, number);
    }

    @Override
    public Predicate lessThanOrEqualTo(String expression, Number number) {
        return le(expression, number);
    }

    @Override
    public Predicate and(Predicate... predicates) {
        return new BooleanPredicate(BooleanPredicate.Operator.AND, predicates);
    }

    @Override
    public Predicate or(Predicate... predicates) {
        return new BooleanPredicate(BooleanPredicate.Operator.OR, predicates);
    }

    @Override
    public Predicate isNull(String expression) {
        return new IsNullPredicate(expression);
    }

    @Override
    public Predicate not(Predicate predicate) {
        return new NotPredicate(predicate);
    }

    @Override
    public Predicate between(String expression, Object lowerBound, Object upperBound) {
        return new BetweenPredicate(expression, lowerBound, upperBound);
    }

    @Override
    public Predicate between(String expression, Object lowerBound, Object upperBound, boolean useTimestamp) {
        return new BetweenPredicate(expression, lowerBound, upperBound, useTimestamp);
    }

    @Override
    public Predicate like(String expression, String value) {
        return new LikePredicate(expression, value);
    }

    @Override
    public Predicate in(String expression, List<Object> values) {
        return new InPredicate(expression, values);
    }

    @Override
    public Predicate in(String expression, Object... values) {
        return new InPredicate(expression, values);
    }

    public String generateSql(Collection<Predicate> predicates) {

        Objects.requireNonNull(predicates, "Predicates cannot be null");

        if (predicates.isEmpty())
            throw new IllegalArgumentException("Predicates cannot be empty");

        return predicates.stream()
                .map(predicate -> predicate.render(false))
                .collect(Collectors.joining(" and \n"));
    }
}