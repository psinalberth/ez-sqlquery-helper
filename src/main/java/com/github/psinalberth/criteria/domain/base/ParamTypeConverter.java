package com.github.psinalberth.criteria.domain.base;

/**
 * Define converter from some JavaType to plain String text.
 *
 * @author Inalberth Pinheiro
 * @since 0.0.1
 */
public interface ParamTypeConverter<Y> {

   /**
    * Convert value from its original type to String text.
    *
    * @param param Value to be converted.
    * @return Plain string text.
    */
   String convert(Y param);

   /**
    * Checks whether converter support given type.
    *
    * @param clazz {@link Class} asked to be converted.
    * @return {@code true} if this {@link ParamTypeConverter} can convert instances of supplied {@code clazz}.
    */
   boolean supports(Class<?> clazz);
}