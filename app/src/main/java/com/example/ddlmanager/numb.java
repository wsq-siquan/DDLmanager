package com.example.ddlmanager;

/**
 * Created by 王思全 on 2016/12/20.
 */
public class numb {
    public static int i=1;
    public static int geti() {
        return i;
    }
    public static void addi() {
       i++;
        i%=7;
    }

}
