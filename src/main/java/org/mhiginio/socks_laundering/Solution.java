package org.mhiginio.socks_laundering;

import java.util.Arrays;

public class Solution {
    private final int[] cleanFrequencies = new int[50];
    private final int[] dirtyFrequencies = new int[50];

    public int solution(int capability, int[] clean, int[] dirty) {
        Arrays.stream(clean).forEach(i -> cleanFrequencies[i - 1]++);
        Arrays.stream(dirty).forEach(i -> dirtyFrequencies[i - 1]++);
        int count = matchSingleCandidates(capability);
        if (count < capability) {
            int pairs = (capability - count) / 2;
            getPairsFromDirtyPool(pairs);
        }
        return Arrays.stream(cleanFrequencies).map(i -> i / 2).sum();
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

    private void getPairsFromDirtyPool(int pairs) {
        int count = 0;
        for (int index = 0; count < pairs && index < dirtyFrequencies.length; index++) {
            int possiblePairs = dirtyFrequencies[index] / 2;
            int increment = Math.min(pairs - count, possiblePairs);
            count += increment;
            cleanFrequencies[index] += (increment * 2);
        }
    }
}