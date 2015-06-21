package com.stylingandroid.prism.setter;

import android.util.Log;

import java.lang.ref.WeakReference;
import java.lang.reflect.Field;

public class FieldAccessor<TYPE> {
    private static final String TAG = FieldAccessor.class.getSimpleName();
    private final WeakReference<Object> viewWeakReference;
    private final Field field;
    private final Class<TYPE> clazz;

    public FieldAccessor(Object object, String name, Class<TYPE> clazz) {
        this.viewWeakReference = new WeakReference<>(object);
        ReflectiveCache reflectiveCache = ReflectiveCache.getInstance();
        field = reflectiveCache.getField(object.getClass(), name, clazz);
        this.clazz = clazz;
    }

    public void set(TYPE value) {
        Object object = viewWeakReference.get();
        if (object != null) {
            field.setAccessible(true);
            try {
                field.set(object, value);
            } catch (IllegalAccessException e) {
                Log.w(TAG, "Illegal access", e);
            }
        }
    }

    public TYPE get() {
        TYPE ret = null;
        Object object = viewWeakReference.get();
        if (object != null) {
            field.setAccessible(true);
            try {
                Object returnedObject = field.get(object);
                if (clazz.isInstance(returnedObject)) {
                    ret = clazz.cast(returnedObject);
                }
            } catch (IllegalAccessException e) {
                Log.w(TAG, "Illegal access", e);
            }
        }
        return ret;
    }
}
