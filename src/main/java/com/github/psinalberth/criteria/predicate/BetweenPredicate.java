package com.github.psinalberth.criteria.predicate;

import com.github.psinalberth.criteria.Predicate;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Getter
@RequiredArgsConstructor
public class BetweenPredicate implements Predicate, Serializable {

    private final String expression;
    private final Object lowerBound;
    private final Object upperBound;
    private boolean useTimestamp;

    public BetweenPredicate(String expression, Object lowerBound, Object upperBound, boolean useTimestamp) {
        this.expression = expression;
        this.lowerBound = lowerBound;
        this.upperBound = upperBound;
        this.useTimestamp = useTimestamp;
    }

    public String getFormattedValue(Object value) {

        String pattern = useTimestamp ? "yyyy-MM-dd HH:mm:ss" : "yyyy-MM-dd";
        LocalDateTime localDateTime = null;

        if (value instanceof Date) {
            localDateTime = ((Date) value).toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
            return "'" + DateTimeFormatter.ofPattern(pattern).format(localDateTime) + "'";
        }

        return String.valueOf(value);
    }

    @Override
    public String render(boolean isNegated) {
        return String.format("%s %s between %s and %s", getExpression(),
                isNegated ? "not": "",
                getFormattedValue(getLowerBound()),
                getFormattedValue(getUpperBound()));
    }
}