package com.shoesclick.api.order.aspect;


import com.shoesclick.api.order.utils.Constants;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ReturnRepositoryElement implements Constants {

	@Around("@annotation(ReturnNullObject)")
	public Object getNullReturn(ProceedingJoinPoint joinPoint) throws Throwable {
		Object proceed = joinPoint.proceed();
	    MethodSignature signature = (MethodSignature) joinPoint.getSignature();	    
	    ReturnNullObject retNullObjAnnotation =  signature.getMethod().getAnnotation(ReturnNullObject.class);
	    if (retNullObjAnnotation.value().isElementNotFound(proceed)) {
			throw retNullObjAnnotation.value().getException();
	    }
	    return proceed;
	}	

}
