package com.ruoyi.naruto.controller;

/**
 * <p>
 *
 * </p>
 *
 * @author red-velvet
 * @since 2024/6/20
 */

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        // 假设获取最高战力成员列表，这里用一个简单的数组来模拟
        int[] memberPowers = {200, 300, 440};

        // 找到最高战力
        int maxPower = findMaxPower(memberPowers);

        // 向上取整
        int roundedPower = roundUp(maxPower);

        // 根据向上取整后的战力值，生成区间
        List<String> intervals = generateIntervals(roundedPower);

        // 输出结果
        System.out.println("最高战力成员列表:");
        for (int power : memberPowers) {
            System.out.println("战力: " + power);
        }
        System.out.println("最高战力: " + maxPower);
        System.out.println("向上取整后的战力: " + roundedPower);
        System.out.println("生成的区间:");
        for (String interval : intervals) {
            System.out.println(interval);
        }
        System.out.println("100-200w".split("-")[1].replace("w", ""));
    }

    // 找到最高战力
    private static int findMaxPower(int[] powers) {
        int maxPower = Integer.MIN_VALUE;
        for (int power : powers) {
            if (power > maxPower) {
                maxPower = power;
            }
        }
        return maxPower;
    }

    // 向上取整
    private static int roundUp(int power) {
        return (int) Math.ceil(power / 100.0) * 100;
    }

    // 根据向上取整后的战力值，生成区间
    private static List<String> generateIntervals(int roundedPower) {
        List<String> intervals = new ArrayList<>();
        for (int start = 100; start < roundedPower; start += 100) {
            int end = start + 100;
            intervals.add(start + "-" + end + "w");
        }
        return intervals;
    }
}

