package com.cuubez.core.resource;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import javax.ws.rs.*;

public class ResourceMetaDataScanner {
	
	public ClassMetaData scanClass(Class<?> clazz) {

        ClassMetaData classMetaData = new ClassMetaData();
		
		scanPath(clazz, classMetaData);
        scanConsume(clazz, classMetaData);
		scanProduce(clazz, classMetaData);
        classMetaData.setClazz(clazz);
        return classMetaData;
		
	}


    public MethodMetaData scanMethods(Class<?> clazz, Method method) {

        int modifier = method.getModifiers();

        if (Modifier.isStatic(modifier) || !Modifier.isPublic(modifier)) {
            return null;
        }

        MethodMetaData methodMetaData = new MethodMetaData();
        methodMetaData.setReflectionMethod(method);

        if (!scanHttpMethod(methodMetaData, method)) {
            return null;
        }
        scanPath(methodMetaData, method);
        scanConsume(methodMetaData, method);
        scanProduce(methodMetaData, method);
        methodMetaData.setClazz(clazz);
        return methodMetaData;
    }


	public static boolean isResource(Class<?> cls) {
        
		if (Modifier.isInterface(cls.getModifiers()) || Modifier.isAbstract(cls.getModifiers())) {
           // logger.trace("isResource() exit returning false because interface or abstract");

            return false;
        }

        if (cls.getAnnotation(Path.class) != null) {
          //  logger.trace("isResource() exit returning true");
            return true;
        }

        Class<?> declaringClass = cls;

        while (!declaringClass.equals(Object.class)) {
            // try a superclass
            Class<?> superclass = declaringClass.getSuperclass();
            if (superclass.getAnnotation(Path.class) != null) {
               /* if (logger.isWarnEnabled()) {
                    logger.warn(Messages
                        .getMessage("rootResourceShouldBeAnnotatedDirectly", cls, superclass)); //$NON-NLS-1$
                }
                logger.trace("isResource() exit returning true because {} has @Path",
                             superclass);*/
                return true;
            }

            // try interfaces
            Class<?>[] interfaces = declaringClass.getInterfaces();
            for (Class<?> interfaceClass : interfaces) {
                if (interfaceClass.getAnnotation(Path.class) != null) {
                   /* if (logger.isWarnEnabled()) {
                        logger.warn(Messages.getMessage("rootResourceShouldBeAnnotatedDirectly", //$NON-NLS-1$
                                                        cls,
                                                        interfaceClass));
                    }
                    logger.trace("isResource() exit returning true because {} has @Path",
                                 interfaceClass);*/
                    return true;
                }
            }
            declaringClass = declaringClass.getSuperclass();
        }
      //  logger.trace("isResource() exit returning false");
        return false;
    }

    public static boolean isSubResource(Method method) {


        if (method.getAnnotation(GET.class) != null ||
                method.getAnnotation(POST.class) != null || method.getAnnotation(PUT.class) != null || method.getAnnotation(DELETE.class) != null) {

            //  logger.trace("isResource() exit returning true");
            return true;
        }

        return false;

    }


    private boolean scanPath(MethodMetaData methodMetaData, Method method) {

        Path path = method.getAnnotation(Path.class);

        if (path != null) {
            methodMetaData.setPath(path.value());
            return true;
        }

        return false;
    }

    private boolean scanPath(Class<?> clazz, ClassMetaData classMetaData) {

		Path path = clazz.getAnnotation(Path.class);

		if (path != null) {
            classMetaData.setPath(path.value());
			return true;
		}
		
		if (!clazz.equals(Object.class)) {
			
			Class<?> superClass = clazz.getSuperclass();
			Path superClassPath = superClass.getAnnotation(Path.class);
			
			if(superClassPath != null) {
                classMetaData.setPath(superClassPath.value());
				return true;
			}
			
			Class<?>[] interfaces = clazz.getInterfaces();
			
			for(Class<?> intface : interfaces) {
				
				Path interfacePath = intface.getAnnotation(Path.class);
				
				if(interfacePath != null) {
					
                    classMetaData.setPath(interfacePath.value());
					return true;
					
				}
			}
			
		}

		return false;
	}
	
	private boolean scanProduce(MethodMetaData methodMetaData, Method method) {

		Produces produce = method.getAnnotation(Produces.class);

		if (produce != null) {
			methodMetaData.setProduce(produce.value());

			return true;
		}

		return false;
	}


    private boolean scanHttpMethod(MethodMetaData methodMetaData, Method method) {

        GET get = method.getAnnotation(GET.class);

        if (get != null) {
            methodMetaData.setHttpMethod(HttpMethod.GET);
            return true;
        }

        POST post = method.getAnnotation(POST.class);

        if (post != null) {
            methodMetaData.setHttpMethod(HttpMethod.POST);
            return true;
        }

        PUT put = method.getAnnotation(PUT.class);

        if (put != null) {
            methodMetaData.setHttpMethod(HttpMethod.PUT);
            return true;
        }

        DELETE delete = method.getAnnotation(DELETE.class);

        if (delete != null) {
            methodMetaData.setHttpMethod(HttpMethod.DELETE);
            return true;
        }

        return false;
    }


    private boolean scanProduce(Class<?> clazz, ClassMetaData classMetaData) {
		
		Produces produce = clazz.getAnnotation(Produces.class);
		
		if(produce != null) {
            classMetaData.setProduce(produce.value());
			
			return true;
		}
		
		return false;
	}
	
	private boolean scanConsume(MethodMetaData methodMetaData, Method method) {

		Consumes consume = method.getAnnotation(Consumes.class);

		if (consume != null) {
			methodMetaData.setConsume(consume.value());

			return true;
		}

		return false;
	}
	
	private boolean scanConsume(Class<?> clazz, ClassMetaData classMetaData) {

		Consumes consume = clazz.getAnnotation(Consumes.class);

		if (consume != null) {
            classMetaData.setConsume(consume.value());

			return true;
		}

		return false;
	}
	
	/*private void scanPathParameter(MethodMetaData methodMetaData, Method method) {

       Annotation[][] parameterAnnotations = method.getParameterAnnotations();
       Type[] paramTypes = getParamTypesFilterByXmlElementAnnotation(method);
               boolean entityParamExists = false;
               for (int pos = 0, limit = paramTypes.length; pos < limit; pos++) {
                   Injectable fp =
                       InjectableFactory.getInstance().create(paramTypes[pos],
                                                              parameterAnnotations[pos],
                                                              method,
                                                              getMetadata().isEncoded() || methodMetadata
                                                                  .isEncoded(),
                                                              methodMetadata.getDefaultValue());
                   if (fp.getParamType() == Injectable.ParamType.ENTITY) {
                       if (entityParamExists) {
                           // we are allowed to have only one entity parameter
                           String methodName =
                               method.getDeclaringClass().getName() + "." + method.getName(); //$NON-NLS-1$
                           throw new IllegalStateException(Messages
                               .getMessage("resourceMethodMoreThanOneEntityParam", methodName)); //$NON-NLS-1$
                       }
                       entityParamExists = true;
                   }
                   methodMetadata.getFormalParameters().add(fp);
                   logger.trace("Adding formal parameter {}", fp);
               }
               logger.trace("parseMethodParameters(), exit");
		
	}   */
	 
}
