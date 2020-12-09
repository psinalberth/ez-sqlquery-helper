package com.github.psinalberth.criteria.predicate;

import com.github.psinalberth.criteria.CriteriaBuilder;
import com.github.psinalberth.criteria.Predicate;
import com.github.psinalberth.criteria.impl.CriteriaBuilderImpl;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class BooleanPredicateTest {

    private CriteriaBuilder builder = new CriteriaBuilderImpl();

    @Test
    public void shouldRenderAndPredicate() {

        List<Predicate> predicates = new ArrayList<>();
        Predicate xEqual10 = builder.equal("x", 10);
        Predicate yGreaterThan20 = builder.greaterThan("y", 20);

        predicates.add(builder.and(xEqual10, yGreaterThan20));
        String result = builder.generateSql(predicates);

        Assert.assertNotNull(result);
        Assert.assertTrue(result.contains("x = 10 and y > 20"));
    }

    @Test
    public void shouldRenderOrPredicate() {

        List<Predicate> predicates = new ArrayList<>();
        Predicate xPlus2Equal18 = builder.equal("x + 2", 18);
        Predicate nameEqualJohn = builder.equal("name", "John");

        predicates.add(builder.or(xPlus2Equal18, nameEqualJohn));
        String result = builder.generateSql(predicates);

        Assert.assertNotNull(result);
        Assert.assertTrue(result.contains("x + 2 = 18 or name = 'John'"));
    }
}