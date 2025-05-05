package com.example.utils;

public class CurrentHolder {
    private static final ThreadLocal<Integer> THREAD_LOCAL = new ThreadLocal<>();

    public static void set(Integer empId){
        THREAD_LOCAL.set(empId);
    }

    public static Integer get(){
        return THREAD_LOCAL.get();
    }
    public static void remove(){
        THREAD_LOCAL.remove();
    }
}
