package com.stylingandroid.prism.setter;

import android.support.annotation.NonNull;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

final class ReflectiveCache {
    private static ReflectiveCache instance;
    private final Map<Signature, Method> methodCache;
    private final Map<Signature, Field> fieldCache;

    public static ReflectiveCache getInstance() {
        if (instance == null) {
            Map<Signature, Method> methodMap = new HashMap<>();
            Map<Signature, Field> fieldMap = new HashMap<>();
            instance = new ReflectiveCache(methodMap, fieldMap);
        }
        return instance;
    }

    ReflectiveCache(Map<Signature, Method> methodCache, Map<Signature, Field> fieldCache) {
        this.methodCache = methodCache;
        this.fieldCache = fieldCache;
    }

    public boolean hasMethod(Class<?> clazz, String name, Class<?>... params) {
        return getMethod(clazz, name, params) != null;
    }

    public Method getMethod(Class<?> clazz, String name, Class<?>... params) {
        Signature signature = new Signature(clazz, name, params);
        Method method = methodCache.get(signature);
        if (method == null) {
            try {
                method = clazz.getMethod(name, params);
            } catch (NoSuchMethodException e) {
                method = null;
            }
            methodCache.put(signature, method);
        }
        return method;
    }

    public boolean hasField(Class<?> clazz, String name, Class<?> param) {
        return getField(clazz, name, param) != null;
    }

    public Field getField(Class<?> clazz, String name, Class<?> param) {
        Signature signature = new Signature(clazz, name, param);
        Field field = fieldCache.get(signature);
        if (field == null) {
            try {
                field = clazz.getDeclaredField(name);
            } catch (NoSuchFieldException e) {
                field = null;
            }
            fieldCache.put(signature, field);
        }
        return field;
    }

    private static final class Signature {
        private static final int HASH_MULTIPLIER = 31;
        private final Class<?> clazz;
        private final String name;
        private final Class<?>[] params;

        private Signature(@NonNull Class<?> clazz, @NonNull String name, @NonNull Class<?>... params) {
            this.clazz = clazz;
            this.name = name;
            this.params = params;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }

            Signature signature = (Signature) o;

            if (!clazz.equals(signature.clazz)) {
                return false;
            }
            if (!name.equals(signature.name)) {
                return false;
            }
            // Probably incorrect - comparing Object[] arrays with Arrays.equals
            return Arrays.equals(params, signature.params);

        }

        @Override
        public int hashCode() {
            int result = clazz.hashCode();
            result = HASH_MULTIPLIER * result + name.hashCode();
            result = HASH_MULTIPLIER * result + Arrays.hashCode(params);
            return result;
        }
    }
}
