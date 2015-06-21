package com.stylingandroid.prism.setter;

import android.util.Log;

import java.lang.ref.WeakReference;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

class MethodInvoker<TYPE> {
    private static final String TAG = MethodInvoker.class.getSimpleName();
    private final WeakReference<Object> viewWeakReference;
    private final Method method;

    public MethodInvoker(Object object, String name, Class<TYPE> clazz) {
        this.viewWeakReference = new WeakReference<>(object);
        Class<?>[] params = new Class<?>[]{clazz};
        ReflectiveCache reflectiveCache = ReflectiveCache.getInstance();
        method = reflectiveCache.getMethod(object.getClass(), name, params);
    }

    public void invoke(TYPE value) {
        Object object = viewWeakReference.get();
        if (object != null) {
            try {
                method.invoke(object, value);
            } catch (IllegalAccessException e) {
                Log.w(TAG, "Illegal access", e);
            } catch (InvocationTargetException e) {
                Log.w(TAG, "Invocation target", e);
            }
        }
    }
}
