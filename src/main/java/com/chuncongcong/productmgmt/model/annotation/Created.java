package com.chuncongcong.productmgmt.model.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author HU
 * @date 2019/12/19 19:50
 */

@Retention(RetentionPolicy.RUNTIME)
@Target(value = {ElementType.FIELD})
public @interface Created {
}
