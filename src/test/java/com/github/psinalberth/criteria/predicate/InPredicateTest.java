package com.github.psinalberth.criteria.predicate;

import com.github.psinalberth.criteria.CriteriaBuilder;
import com.github.psinalberth.criteria.Predicate;
import com.github.psinalberth.criteria.impl.CriteriaBuilderImpl;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class InPredicateTest {

    private CriteriaBuilder builder = new CriteriaBuilderImpl();

    @Test
    public void shouldRenderInNumbersPredicate() {

        List<Predicate> predicates = new ArrayList<>();
        predicates.add(builder.in("worldCupYear", 1994, 1998, 2002, 2006));
        String result = builder.generateSql(predicates);

        System.out.println(result);

        Assert.assertNotNull(result);
        Assert.assertTrue(result.contains("worldCupYear in (1994, 1998, 2002, 2006)"));
    }

    @Test
    public void shouldRenderInStringssPredicate() {

        List<Predicate> predicates = new ArrayList<>();
        predicates.add(builder.in("carBrand", "Ford", "Ferrari", "Mercedes"));
        String result = builder.generateSql(predicates);

        System.out.println(result);

        Assert.assertNotNull(result);
        Assert.assertTrue(result.contains("carBrand in ('Ford', 'Ferrari', 'Mercedes')"));
    }
}