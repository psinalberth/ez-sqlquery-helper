package com.github.psinalberth.criteria.predicate;

import com.github.psinalberth.criteria.domain.base.Predicate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Test suite for \"LikePredicate\"")
class LikePredicateTest extends BaseTest {

    @Test
    @DisplayName("Should render LIKE predicate.")
    void shouldRenderLikePredicate() {

        Predicate nameLikeSmith = builder.like("name", "%" + "Smith" + "%");
        Predicate fullNameNotLikeJohnSmith = builder.not(builder.like("fullName", "%" + "John Smith" + "%"));

        predicates.add(builder.and(nameLikeSmith,fullNameNotLikeJohnSmith));

        String result = builder.generateSql(predicates);

        assertThat(result)
                .isNotEmpty()
                .containsSubsequence("name", "like", "'%Smith%'", "fullName", "not like", "'%John Smith%'");
    }
}