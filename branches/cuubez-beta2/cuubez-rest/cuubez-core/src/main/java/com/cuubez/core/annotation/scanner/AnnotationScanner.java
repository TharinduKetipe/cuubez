package com.cuubez.core.annotation.scanner;

import com.cuubez.core.context.ServiceRepository;
import com.cuubez.core.exception.CuubezException;

public interface AnnotationScanner {
	
	public void scan(Class<?> clazz, ServiceRepository serviceRepository) throws CuubezException;
	    
}
