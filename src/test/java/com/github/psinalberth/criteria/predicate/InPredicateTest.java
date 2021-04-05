package com.github.psinalberth.criteria.predicate;

import com.github.psinalberth.criteria.domain.base.Predicate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Test suite for \"InPredicate\"")
class InPredicateTest extends BaseTest {

    @Test
    @DisplayName("Should render Numeric type predicate.")
    void shouldRenderInNumericPredicate() {

        predicates.add(builder.in("worldCupYear", 1994, 1998, 2002, 2006));
        String result = builder.generateSql(predicates);

        assertThat(result)
                .isNotEmpty()
                .containsSubsequence("worldCupYear", "in", "(", "1994, ", "1998", "2002", "2006", ")");
    }

    @Test
    @DisplayName("Should render CharSequence type predicate.")
    void shouldRenderInCharSequencePredicate() {

        List<Predicate> predicates = new ArrayList<>();
        predicates.add(builder.in("carBrand", Arrays.asList("Ford", "Ferrari", "Mercedes")));
        String result = builder.generateSql(predicates);

        assertThat(result)
                .isNotEmpty()
                .containsSubsequence("carBrand", "in", "(", "Ford", "Ferrari", "Mercedes", ")");
    }
}