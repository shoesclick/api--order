package com.shoesclick.api.order.aspect;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Anotação para tratar o retorno do dao
 * 
 * OBS: esta anotação deve ser utilizada somente em classes DAO
 * 
 * @author Clayton Morais de Oliveira
 *
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ReturnNullObject {
	ObjectReturnType value() default ObjectReturnType.OBJECT;
}