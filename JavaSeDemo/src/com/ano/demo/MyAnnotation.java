package com.ano.demo;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD,ElementType.FIELD})
public @interface MyAnnotation {

    /**
     * Returns the retention policy.
     * @return the retention policy
     */
    int lengthMax() default 10;

}
