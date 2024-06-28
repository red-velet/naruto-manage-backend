package com.ruoyi.naruto.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author red-velvet
 * @since 2024/6/20
 */
public class PowerIntervals {

    public static void main(String[] args) {
        int[] powers = {155, 333, 467, 552};
        List<String> intervals = generateIntervals(powers);
        for (String interval : intervals) {
            System.out.println(interval);
        }
    }

    private static List<String> generateIntervals(int[] powers) {
        // 找到最小和最大的战力值
        int minPower = Arrays.stream(powers).min().orElse(0);
        int maxPower = Arrays.stream(powers).max().orElse(0);

        // 向下取整最小战力值到最接近的50或100的倍数
        int roundedMinPower = roundDown(minPower);

        // 向上取整最大战力值到最接近的50或100的倍数
        int roundedMaxPower = roundUp(maxPower);

        // 根据取整后的最小和最大战力值，以50为间隔生成区间
        List<String> intervals = new ArrayList<>();
        for (int i = roundedMinPower; i < roundedMaxPower; i += 50) {
            intervals.add(i + "-" + (i + 50));
        }

        return intervals;
    }

    private static int roundUp(int power) {
        return ((power + 49) / 50) * 50; // 向上取整到最接近的50的倍数
    }

    private static int roundDown(int power) {
        return (power / 50) * 50; // 向下取整到最接近的50的倍数
    }
}
