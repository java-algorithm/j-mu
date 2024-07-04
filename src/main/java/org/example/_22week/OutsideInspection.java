package org.example._22week;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class OutsideInspection {

    private static int[] dist;
    private static int answer = -1;

    public static void main(String[] args) {
//        int n = 12;
//        int[] weak = {1, 5, 6, 10};
//        int[] adist = {1, 2, 3, 4};

//        int n = 12;
//        int[] weak = {1, 3, 4, 9, 10};
//        int[] adist = {3, 5, 7};

        int n = 16;
        int[] weak = {1, 2, 3, 4, 5, 7, 8, 10, 11, 12, 14, 15};
        int[] adist = {4, 2, 1, 1};

        final int answer = solution(n, weak, adist);
        System.out.println(answer);
    }

    public static int solution(int n, int[] weak, int[] adist) {
        Arrays.sort(adist);

        dist = new int[adist.length];
        for (int i = 0; i < adist.length; i++) {
            dist[i] = adist[adist.length - 1 - i];
        }

        int[][] tempWeak = new int[weak.length][weak.length];
        for (int startIndex = 0; startIndex < weak.length; startIndex++) {
            // startIndex를 통해 새로운 weak만들기.
            int[] newWeak = new int[weak.length];
            int tempStartIndex = startIndex;
            for (int j = 0; j < weak.length; j++) {
                if (tempStartIndex < weak.length) {
                    newWeak[j] = weak[tempStartIndex];
                    tempStartIndex++;
                } else {
                    newWeak[j] = weak[(tempStartIndex++ % weak.length)] + n;
                }
            }

            tempWeak[startIndex] = newWeak;
        }

        for (int j = 0; j < weak.length; j++) {
            System.out.println("startIndex : " + j);
            for (int i = 0; i < weak.length; i++) {
                System.out.print(tempWeak[j][i] + " ");
            }
            System.out.println();
            System.out.println();
        }


        findAnswer(dist, tempWeak);

        return answer;
    }

    public static void findAnswer(int[] dist, int[][] tempWeak) {
        // 몇명?이 필요한지 세알리기 위한 for
        for (int workerCount = 1; workerCount <= dist.length; workerCount++) {
            // 0 ~ workerCount까지가 작업자.

            // 해당 작업자로 작업하는게 가능한지 확인해야함.
            // startIndex별로 다 확인해야함.
            for (int j = 0; j < tempWeak.length; j++) {
                final int[] targetWeak = tempWeak[j];
                final int[] targetSizeDist = Arrays.copyOf(dist, workerCount);
                final boolean[] visied = new boolean[workerCount];

                List<List<Integer>> distSequences = new ArrayList<>();
                getAllWorkerSequences(targetSizeDist, 0, visied, new ArrayList<>(), distSequences);

                for (final List<Integer> distSequence : distSequences) {
                    // 0 ~ workerCount까지가 작업자.
                    int currentIndex = 0;
                    for (int workerNo = 0; workerNo < workerCount; workerNo++) {
                        final int endValue = targetWeak[currentIndex] + distSequence.get(workerNo);

                        boolean isLack = false;
                        for (int l = currentIndex; l < tempWeak.length; l++) {
                            if (targetWeak[l] > endValue) {
                                currentIndex = l;
                                isLack = true;
                                break;
                            }
                        }

                        // 다 커버 했다는 뜻.
                        if (!isLack) {
                            answer = workerCount;
                            return;
                        }
                    }
                }
            }
        }
    }

    public static void getAllWorkerSequences(int[] dist, int index, boolean[] visited, List<Integer> temp, List<List<Integer>> sequences) {
        if (dist.length == index) {
            final ArrayList<Integer> sequence = new ArrayList<>(temp);
            sequences.add(sequence);
            return;
        }

        for (int i = 0; i < dist.length; i++) {
            if (visited[i]) {
                continue;
            }

            final boolean[] newVisited = visited.clone();
            newVisited[i] = true;
            temp.add(dist[i]);
            getAllWorkerSequences(dist, index + 1, newVisited, temp, sequences);
            temp.remove(temp.size() - 1);
        }
    }
}
