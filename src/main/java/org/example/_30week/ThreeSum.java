package org.example._30week;

import java.util.*;
import java.util.stream.Collectors;

class ThreeSum {
    public List<List<Integer>> threeSum(int[] nums) {
        Set<ThreeNums> answers = new HashSet<>();

        // nums를 정렬
        Arrays.sort(nums);

        int[] positiveNums = new int[0];
        int[] negativeNums = new int[0];
        int zeroCount = 0;

        boolean hasNegative = false;
        boolean hasPositive = false;
        int negativeStart = 0;
        int positiveStart = 0;
        int negativeEnd = 0;
        int positiveEnd = 0;

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] < 0) {
                hasNegative = true;
                negativeEnd++;
            } else if (nums[i] == 0) {
                zeroCount++;
            } else {
                hasPositive = true;
                positiveStart = i;
                break;
            }
        }
        positiveEnd = nums.length;

        if (hasPositive) {
            positiveNums = Arrays.copyOfRange(nums, positiveStart, positiveEnd);
        }

        if (hasNegative) {
            negativeNums = Arrays.copyOfRange(nums, negativeStart, negativeEnd);
        }

//
//        // 경우의 수
        // 1) zero 3
        if (zeroCount >= 3) {
            answers.add(new ThreeNums(0, 0, 0));
        }

//        // 2) positive 2, negative 1
        for (Integer negativeNum : negativeNums) {
            if (positiveNums.length == 0) {
                break;
            }

            List<ThreeNums> threeNums = twoPointer(positiveNums, negativeNum * -1);
            answers.addAll(threeNums);
        }
//
//        // 3) positive 1, negative 2
        for (Integer positiveNum : positiveNums) {
            if (negativeNums.length == 0) {
                break;
            }

            List<ThreeNums> threeNums = twoPointer(negativeNums, positiveNum * -1);
            answers.addAll(threeNums);
        }

//        // 4) positive 1, negative 1, zero 1
        if (zeroCount > 0) {
            for (Integer negativeNum : negativeNums) {
                int index = Arrays.binarySearch(positiveNums, negativeNum * -1);
                if (index >= 0) {
                    answers.add(new ThreeNums(negativeNum, negativeNum * -1, 0));
                }
            }
        }


        return answers.stream().map(ThreeNums::getNums).collect(Collectors.toList());
    }

    private List<ThreeNums> twoPointer(int[] nums, int target) {
        List<ThreeNums> answers = new ArrayList<>();
        int start = 0;
        int end = nums.length - 1;

        while (start < end) {
            int sum = nums[start] + nums[end];
            if (sum < target) {
                start++;
            } else if (sum > target) {
                end--;
            } else {
                answers.add(new ThreeNums(nums[start], nums[end], target * -1));
                start++;
            }
        }

        return answers;
    }

    private class ThreeNums {
        List<Integer> nums;

        public ThreeNums(int[] nums) {
            Arrays.sort(nums);
            this.nums = Arrays.stream(nums).boxed().collect(Collectors.toList());
        }

        public ThreeNums(int a1, int a2, int a3) {
            this.nums = List.of(a1, a2, a3);
        }

        public List<Integer> getNums() {
            return nums;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            ThreeNums threeNums = (ThreeNums) o;
            return Objects.equals(nums, threeNums.nums);
        }

        @Override
        public int hashCode() {
            return Objects.hashCode(nums);
        }
    }
}