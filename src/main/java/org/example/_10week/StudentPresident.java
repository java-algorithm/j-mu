    package org.example._10week;

    import java.util.*;
    import java.util.stream.Collectors;

    public class StudentPresident {

        public static void main(String[] args) {
            final Scanner sc = new Scanner(System.in);
            final int albumSize = Integer.parseInt(sc.nextLine());
            final LinkedList<Student> albums = new LinkedList<>();
            final Set<Integer> albumStudentSet = new HashSet<>();

            final int recommendCount = Integer.parseInt(sc.nextLine());
            final int[] recommends = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            for (int i = 0; i < recommendCount; i++) {
                // 기존에 추천 목록에 있었던 경우
                if (albumStudentSet.contains(recommends[i])) {
                    for (final Student album : albums) {
                        if (album.num == recommends[i]) {
                            album.recommendCount += 1;
                            break;
                        }
                    }
                    continue;
                }

                // 기존에 추천 목록에 없었던 경우.
                if (albums.size() >= albumSize) {
                    Collections.sort(albums);
                    final Student removedStudent = albums.remove(albumSize - 1);
                    albumStudentSet.remove(removedStudent.num);
                }

                albums.add(new Student(recommends[i], i));
                albumStudentSet.add(recommends[i]);
            }

            final String answer = albums.stream()
                .map(student -> student.num)
                .sorted()
                .map(String::valueOf)
                .collect(Collectors.joining(" "));

            System.out.println(answer);
        }

        private static class Student implements Comparable<Student> {
            private int num;
            private int recommendCount;
            private int displayTime;

            public Student(final int num, final int displayTime) {
                this.num = num;
                this.recommendCount = 1;
                this.displayTime = displayTime;
            }

            // 추천 받은 횟수로 내림차순 정렬
            // 같은 경우 추천받은 시각 기준으로 내림차순 정렬
            @Override
            public int compareTo(final Student o) {
                if (this.recommendCount == o.recommendCount) {
                    return Integer.compare(o.displayTime, this.displayTime);
                }

                return Integer.compare(o.recommendCount, this.recommendCount);
            }
        }
    }
