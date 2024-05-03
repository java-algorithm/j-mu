package org.example._12week;


import java.io.*;
import java.util.*;

public class BindingNumbersBom {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        Integer[] arr = new Integer[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(arr);
        int max1 = getMaxBindSum(arr);
// 음수가 있는 케이스를 처리하기 위해 내림차순으로 정렬해서 다시 함수 호출
        Arrays.sort(arr, Collections.reverseOrder());
        int max2 = getMaxBindSum(arr);
        System.out.println(Math.max(max1, max2));
    }

    public static int getMaxBindSum(Integer[] arr) {
        int max = 0;
        int N = arr.length;
        // 숫자가 정렬된 상태에서 뒤에서부터 숫자 묶기
        for (int i = N - 1; i >= 0; ) {
            // 마지막 남은 숫자가 한 개면 값을 더해주기
            if (i == 0) {
                max += arr[i];
                break;
            }
            // 이거는 숫자 0 이 있을 때 음수랑 엮였을 때의 케이스를 처리
            // 이런 케이스를 처리하기 위한 로직
            // (-2 -1) 0 (2 3) (4 5) = 28
            // (-2 0) 1 (2 3) (4 5) = 27
            // -2 0 0 1 (2 3) (4 5) = 27
            if (arr[i] == 0) {
                // 0 이 더해도 같은 값이기 때문에 0이 여러 개일 경우에는 1개만 남기기
                while (i > 1 && arr[i - 1] != 0) {
                    i--;
                }
                if (i % 2 == 0) {
                    i--;
                }
            }

            int num = arr[i] * arr[i - 1];
            // 한 개의 수를 묶지 않고 처리
            if (num < arr[i]) {
                max += arr[i--];
            } else if (num < arr[i] + arr[i - 1]) { // 두 개의 수를 묶지 않고 더하기 처리
                max += arr[i] + arr[i - 1];
                i -= 2;
            } else { // 수를 묶어서 곱하기 처리
                max += num;
                i -= 2;
            }
        }
        return max;
    }
}
