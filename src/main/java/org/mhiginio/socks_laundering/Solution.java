package org.mhiginio.socks_laundering;

import java.util.Arrays;

public class Solution {
    private final int[] cleanFrequencies = new int[50];
    private final int[] dirtyFrequencies = new int[50];

    public int solution(int capability, int[] clean, int[] dirty) {
        Arrays.stream(clean).forEach(i -> cleanFrequencies[i - 1]++);
        Arrays.stream(dirty).forEach(i -> dirtyFrequencies[i - 1]++);
        int remainingCapability = capability - matchSingleCandidates(capability);
        return countPairs(cleanFrequencies) + Math.min(countPairs(dirtyFrequencies), remainingCapability / 2);
    }

    private int countPairs(int[] socks) {
        return Arrays.stream(socks).map(i -> i / 2).sum();
    }


    private int matchSingleCandidates(int capability) {
        int count = 0;
        for (int index = 0; count < capability && index < cleanFrequencies.length; index++) {
            if (cleanFrequencies[index] % 2 == 1 && dirtyFrequencies[index] > 0) {
                cleanFrequencies[index]++;
                dirtyFrequencies[index]--;
                count++;
            }
        }
        return count;
    }
}