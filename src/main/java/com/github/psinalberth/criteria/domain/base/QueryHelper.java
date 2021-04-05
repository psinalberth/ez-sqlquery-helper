package com.github.psinalberth.criteria.domain.base;

import java.util.Collection;
import java.util.List;

/**
 * Used to help SQL-based queries rendering.
 *
 * @author Inalberth Pinheiro
 * @since 0.0.1
 */
public interface QueryHelper {

    /**
     * Create predicate for testing equality.
     *
     * @param expression Expression for evaluation.
     * @param y Value for comparison.
     * @param <Y> Return type.
     * @return Predicate for equality.
     */
    <Y extends Comparable<? super Y>> Predicate equal(String expression, Y y);

    /**
     * Create predicate for testing inequality.
     *
     * @param expression Expression for evaluation.
     * @param y Value for comparison.
     * @param <Y> Return type.
     * @return Predicate for inequality.
     */
    <Y extends Comparable<? super Y>> Predicate notEqual(String expression, Y y);

    /**
     * Sugar syntax for {@link QueryHelper#greaterThan(String, Number)}
     *
     * @param expression Expression for evaluation.
     * @param y Value for comparison.
     * @return Greater-than predicate.
     */
    Predicate gt(String expression, Number y);

    /**
     * Create predicate for testing whether expression is greater than argument.
     *
     * @param expression expression Expression for evaluation.
     * @param y Value for comparison.
     * @return Greater-than predicate.
     */
    Predicate greaterThan(String expression, Number y);

    /**
     * Create predicate for testing whether expression is greater than or equal to argument.
     *
     * @param expression Expression for evaluation.
     * @param y Value for comparison.
     * @return Greater-than-or-equal-to predicate.
     */
    Predicate greaterThanOrEqualTo(String expression, Number y);

    /**
     * Sugar syntax for {@link QueryHelper#greaterThanOrEqualTo(String, Number)}
     *
     * @param expression Expression for evaluation.
     * @param y Value for comparison.
     * @return Greater-than-or-equal-to predicate.
     */
    Predicate ge(String expression, Number y);

    /**
     * Create predicate for testing whether expression is less than to argument.
     *
     * @param expression Expression for evaluation.
     * @param y Value for comparison.
     * @return Less-than predicate.
     */
    Predicate lessThan(String expression, Number y);

    /**
     * Sugar syntax for {@link QueryHelper#lessThan(String, Number)}
     *
     * @param expression Expression for evaluation.
     * @param y Value for comparison.
     * @return Less-than predicate.
     */
    Predicate lt(String expression, Number y);

    /**
     * Create predicate for testing whether expression is less than or equal to argument.
     * @param expression Expression for evaluation.
     * @param y Value for comparison.
     * @return Less-than-or-equal-to predicate.
     */
    Predicate lessThanOrEqualTo(String expression, Number y);

    /**
     * Sugar syntax for {@link QueryHelper#lessThanOrEqualTo(String, Number)}
     *
     * @param expression Expression for evaluation.
     * @param y Value for comparison.
     * @return Less-than-or-equal-to predicate.
     */
    Predicate le(String expression, Number y);

    /**
     * Create conjunction predicate of restrictions.
     *
     * @param restrictions Restriction predicates.
     * @return And predicate.
     */
    Predicate and(Predicate... restrictions);

    /**
     * Create disjunction predicate of restrictions.
     *
     * @param restrictions Restriction predicates.
     * @return Or predicate.
     */
    Predicate or(Predicate... restrictions);

    /**
     * Create predicate for testing whether expression is null.
     *
     * @param expression Expression for evaluation.
     * @return Is-null predicate.
     */
    Predicate isNull(String expression);

    /**
     * Create negation for predicate.
     *
     * @param predicate Restrictition.
     * @return Not predicate.
     */
    Predicate not(Predicate predicate);

    /**
     * Create predicate for testing whether expression between lower and upper bound values.
     *
     * @param expression Expression for evaluation.
     * @param lowerBound Lower-bound value.
     * @param upperBound Upper-bound value.
     * @param <Y> Return type.
     * @return Between predicate.
     */
    <Y extends Comparable<? super Y>> Predicate between(String expression, Y lowerBound, Y upperBound);

    /**
     * Create predicate for testing whether expression satisfies some pattern.
     *
     * @param expression Expression for evaluation.
     * @param value Pattern for comparison.
     * @return Like predicate.
     */
    Predicate like(String expression, CharSequence value);

    /**
     * Create predicate for testing whether expression is contained in a list of values.
     *
     * @param expression Expression for evaluation.
     * @param values Possible values.
     * @param <Y> Return type..
     * @return In predicate.
     */
    <Y extends Comparable<? super Y>> Predicate in(String expression, List<Y> values);

    /**
     * Create predicate for testing whether expression is contained in a list of values.
     *
     * @param expression Expression for evaluation.
     * @param values Possible values.
     * @param <Y> Return type..
     * @return In predicate.
     */
    <Y extends Comparable<? super Y>> Predicate in(String expression, Y... values);

    /**
     * Create SQL String of given predicates.
     *
     * @param predicates Restriction predicates.
     * @return SQL string query;
     */
    String generateSql(Collection<Predicate> predicates);
}
