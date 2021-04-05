package com.github.psinalberth.criteria.predicate;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Month;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Test suite for \"BetweenPredicate\"")
class BetweenPredicateTest extends BaseTest {

    @Test
    @DisplayName("Should render Temporal type predicate.")
    void shouldRenderTemporalTypePredicate() {

        LocalDate checkInDate = LocalDate.of(2021, Month.APRIL, 2);
        LocalDate checkOutDate = checkInDate.plusDays(2);

        predicates.add(builder.between("reservationDate", checkInDate, checkOutDate));

        String result = builder.generateSql(predicates);

        assertThat(result)
                .isNotEmpty()
                .containsSubsequence("reservationDate", "between", "'2021-04-02'", "and", "'2021-04-04'");
    }

    @Test
    @DisplayName("Should render Numeric type predicate.")
    void shouldRenderNumericTypePredicate() {

        Integer initialOlympicsYear = 1984;
        Integer finalOlympicsYear = 1992;

        predicates.add(builder.between("olympicsYear", initialOlympicsYear, finalOlympicsYear));

        String result = builder.generateSql(predicates);

        assertThat(result)
                .isNotEmpty()
                .containsSubsequence("olympicsYear", "between", "1984", "and", "1992");
    }
}