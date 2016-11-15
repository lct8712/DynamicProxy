package com.chentian.proxy.factory;

import com.chentian.proxy.annotation.MyGet;
import com.chentian.proxy.annotation.MyPost;
import com.chentian.proxy.annotation.MyQuery;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.lang.reflect.Type;

/**
 * @author chentian
 */
public class ServiceFactory {

    @SuppressWarnings("unchecked")
    public static <T> T create(Class<T> service) {
        return (T) Proxy.newProxyInstance(service.getClassLoader(), new Class<?>[] { service },
            new SimpleInvocationHandler());
    }

    private static class SimpleInvocationHandler implements InvocationHandler {
        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

            // Method annotations
            for (Annotation annotation : method.getAnnotations()) {
                if (annotation instanceof MyGet) {
                    System.out.println("MyGet");
                    System.out.println("Url: " + ((MyGet) annotation).value());
                } else if (annotation instanceof MyPost) {
                    System.out.println("MyPost");
                    System.out.println("Url: " + ((MyPost) annotation).value());
                }
            }

            // Param annotations
            System.out.println("Parameters: ");
            Class<?>[] parameterTypes = method.getParameterTypes();
            Annotation[][] parameterAnnotationsArray = method.getParameterAnnotations();
            for (int i = 0; i < parameterAnnotationsArray.length; i++) {
                Annotation[] parameterAnnotations = parameterAnnotationsArray[i];
                for (Annotation parameterAnnotation : parameterAnnotations) {
                    if (parameterAnnotation instanceof MyQuery) {
                        System.out.print("\t" + ((MyQuery) parameterAnnotation).value());
                    }
                }
                System.out.print(" = " + args[i].toString());

                Type parameterType = parameterTypes[i];
                System.out.println(" (" + parameterType.toString() + ")");
            }

            return null;
        }
    }

}
