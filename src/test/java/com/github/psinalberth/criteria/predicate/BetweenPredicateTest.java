package com.github.psinalberth.criteria.predicate;

import com.github.psinalberth.criteria.CriteriaBuilder;
import com.github.psinalberth.criteria.Predicate;
import com.github.psinalberth.criteria.impl.CriteriaBuilderImpl;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BetweenPredicateTest {

    private CriteriaBuilder builder = new CriteriaBuilderImpl();

    @Test
    public void shouldRenderBetweenNumbersPredicate() {

        List<Predicate> predicates = new ArrayList<>();
        predicates.add(builder.between("age", 18, 25));
        String result = builder.generateSql(predicates);

        Assert.assertNotNull(result);
        Assert.assertTrue(result.contains("age between 18 and 25"));
    }

    @Test
    public void shouldRenderBetweenDatesPredicate() {

        LocalDateTime startTime = LocalDateTime
                .of(2020, Month.DECEMBER, 1, 8, 30, 0);

        LocalDateTime endTime = LocalDateTime
                .of(2020, Month.DECEMBER, 31, 23, 59, 59);

        List<Predicate> predicates = new ArrayList<>();
        predicates.add(builder.between("reservationDate",
                Date.from(startTime.atZone(ZoneId.systemDefault()).toInstant()),
                Date.from(endTime.atZone(ZoneId.systemDefault()).toInstant()),
                true));

        String result = builder.generateSql(predicates);

        System.out.println(result);

        Assert.assertNotNull(result);
        Assert.assertTrue(result.contains("reservationDate between '2020-12-01 08:30:00' and '2020-12-31 23:59:59'"));
    }
}