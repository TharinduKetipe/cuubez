package com.cuubez.core.util;


import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;

public class ConverterUtil {

    private static Map<Class, Class> primitiveMap = new HashMap<java.lang.Class, java.lang.Class>();

    static {
        primitiveMap.put(boolean.class, Boolean.class);
        primitiveMap.put(byte.class, Byte.class);
        primitiveMap.put(char.class, Character.class);
        primitiveMap.put(short.class, Short.class);
        primitiveMap.put(int.class, Integer.class);
        primitiveMap.put(long.class, Long.class);
        primitiveMap.put(float.class, Float.class);
        primitiveMap.put(double.class, Double.class);
    }


    public static Object convert(String value, Class<?> destClass) {
        if ((value == null) || "".equals(value)) {
            return value;
        }

        if (destClass.isPrimitive()) {
            destClass = primitiveMap.get(destClass);
        }

        try {
            Method m = destClass.getMethod("valueOf", String.class);
            int mods = m.getModifiers();
            if (Modifier.isStatic(mods) && Modifier.isPublic(mods)) {
                return m.invoke(null, value);
            }
        } catch (NoSuchMethodException e) {
            if (destClass == Character.class) {
                return Character.valueOf(value.charAt(0));
            }
        } catch (IllegalAccessException e) {
            // this won't happen
        } catch (InvocationTargetException e) {
            // when this happens, the string cannot be converted to the intended type
            // we are ignoring it here - the original string will be returned.
            // But it can be re-thrown if desired!
        }

        return value;
    }


}
