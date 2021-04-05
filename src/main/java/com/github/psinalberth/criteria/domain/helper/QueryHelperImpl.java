package com.github.psinalberth.criteria.domain.helper;

import com.github.psinalberth.criteria.domain.base.QueryHelper;
import com.github.psinalberth.criteria.domain.base.Predicate;
import com.github.psinalberth.criteria.domain.predicate.*;
import lombok.NonNull;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class QueryHelperImpl implements QueryHelper {

    public <Y extends Comparable<? super Y>> Predicate equal(String expression, Y y) {
        return new ComparisonPredicate<>(ComparisonPredicate.Operator.EQUAL, expression, y);
    }

    public <Y extends Comparable<? super Y>> Predicate notEqual(String expression, Y y) {
        return new ComparisonPredicate<>(ComparisonPredicate.Operator.NOT_EQUAL, expression, y);
    }

    @Override
    public Predicate greaterThan(String expression, Number number) {
        return gt(expression, number);
    }

    @Override
    public Predicate gt(String expression, Number number) {
        return new ComparisonPredicate<>(ComparisonPredicate.Operator.GREATER_THAN, expression, number);
    }

    @Override
    public Predicate greaterThanOrEqualTo(String expression, Number number) {
        return ge(expression, number);
    }

    @Override
    public Predicate ge(String expression, Number number) {
        return new ComparisonPredicate<>(ComparisonPredicate.Operator.GREATER_THAN_OR_EQUAL, expression, number);
    }

    @Override
    public Predicate lessThan(String expression, Number number) {
        return lt(expression, number);
    }

    @Override
    public Predicate lt(String expression, Number number) {
        return new ComparisonPredicate<>(ComparisonPredicate.Operator.LESS_THAN, expression, number);
    }

    @Override
    public Predicate lessThanOrEqualTo(String expression, Number number) {
        return le(expression, number);
    }

    @Override
    public Predicate le(String expression, Number number) {
        return new ComparisonPredicate<>(ComparisonPredicate.Operator.LESS_THAN_OR_EQUAL, expression, number);
    }

    @Override
    public Predicate and(Predicate... predicates) {
        return new BooleanPredicate<>(BooleanPredicate.Operator.AND, predicates);
    }

    @Override
    public Predicate or(Predicate... predicates) {
        return new BooleanPredicate<>(BooleanPredicate.Operator.OR, predicates);
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
    public <Y extends Comparable<? super Y>> Predicate between(String expression, Y lowerBound, Y upperBound) {
        return new BetweenPredicate<>(expression, lowerBound, upperBound);
    }

    @Override
    public Predicate like(String expression, CharSequence value) {
        return new LikePredicate(expression, value);
    }

    @Override
    public <Y extends Comparable<? super Y>> Predicate in(String expression, List<Y> values) {
        return new InPredicate<>(expression, values);
    }

    @Override
    public <Y extends Comparable<? super Y>> Predicate in(String expression, Y... values) {
        return new InPredicate<>(expression, values);
    }

    public String generateSql(@NonNull Collection<Predicate> predicates) {
        return predicates.stream()
                .map(predicate -> predicate.render(false))
                .collect(Collectors.joining(" and \n"));
    }
}