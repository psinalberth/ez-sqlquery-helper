package com.github.psinalberth.criteria.domain.base;

import java.io.Serializable;

/**
 * Conjunction to be tested and rendered.
 *
 * @author Inalberth Pinheiro
 * @since 0.0.1
 */
public interface Predicate extends Serializable {

    /**
     * Render predicate into plain SQL expression.
     * @param isNegated Checks whether negation is needed.
     * @return Plain SQL-style predicate.
     */
    String render(boolean isNegated);
}
