/**
 * 
 */
package com.cuubez.auth.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author ruwan
 *
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface RestSecurity {

    String[] userId() default "[unassigned]"; 

    String[] role() default "[unassigned]"; 

    boolean isSecure() default true;
	
}
