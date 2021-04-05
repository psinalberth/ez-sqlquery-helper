package com.github.psinalberth.criteria.predicate;

import com.github.psinalberth.criteria.domain.base.Predicate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Test suite for \"BooleanPredicate\"")
class BooleanPredicateTest extends BaseTest {

    @Test
    @DisplayName("Should render AND predicate")
    void shouldRenderAndPredicate() {

        Predicate ageEqual40 = builder.equal("age", 40);
        Predicate salaryGreaterThan10000 = builder.greaterThan("salary", new BigDecimal("10000.00"));
        Predicate companyIdIsNull = builder.isNull("companyId");
        Predicate departmentIdLessThanOrEqualTo15 = builder.lessThanOrEqualTo("departmentId", 15);

        predicates.add(builder.and(ageEqual40, salaryGreaterThan10000, companyIdIsNull, departmentIdLessThanOrEqualTo15));
        String result = builder.generateSql(predicates);

        assertThat(result)
                .isNotEmpty()
                .containsSubsequence("age = 40", "and", "salary > 10000.00", "and", "companyId is null", "and", "departmentId <= 15");
    }

    @Test
    @DisplayName("Should render OR predicate")
    void shouldRenderOrPredicate() {

        Predicate departmentIdNotEqual6 = builder.notEqual("departmentId", 6L);
        Predicate companyIdGreaterThanOrEqualTo20 = builder.greaterThanOrEqualTo("companyId", 20);
        Predicate employeedIdLessThan4 = builder.lessThan("employeeId", 4);

        predicates.add(builder.or(departmentIdNotEqual6, companyIdGreaterThanOrEqualTo20, employeedIdLessThan4));
        String result = builder.generateSql(predicates);

        assertThat(result)
                .isNotEmpty()
                .containsSubsequence("departmentId", "<>", "6", "companyId", ">=", "20", "employeeId", "<", "4");
    }
}