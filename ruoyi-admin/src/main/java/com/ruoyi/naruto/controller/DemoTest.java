package com.ruoyi.naruto.controller;

/**
 * <p>
 *
 * </p>
 *
 * @author red-velvet
 * @since 2024/7/8
 */
public class DemoTest {
    public static void main(String[] args) {
        int n = 0;
        int fibo = getFibo(10);
        System.out.println("n = " + fibo);

    }

    public static int getFibo(int n) {
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        return getFibo(n - 1) + getFibo(n - 2);
    }
}
