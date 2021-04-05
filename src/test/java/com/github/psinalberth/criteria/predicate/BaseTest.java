package com.github.psinalberth.criteria.predicate;

import com.github.psinalberth.criteria.domain.base.Predicate;
import com.github.psinalberth.criteria.domain.base.QueryHelper;
import com.github.psinalberth.criteria.domain.helper.QueryHelperImpl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import java.util.ArrayList;
import java.util.List;

abstract class BaseTest {

    protected static QueryHelper builder;
    protected List<Predicate> predicates;

    @BeforeAll
    static void setup() {
        builder = new QueryHelperImpl();
    }

    @BeforeEach
    void beforeEach() {
        predicates = new ArrayList<>();
    }
}
