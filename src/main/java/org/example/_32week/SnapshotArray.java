package org.example._32week;

public class SnapshotArray {

    private int snapId;
    int[][] snapshots;
    int[] array;

    public static void main(String[] args) {
        SnapshotArray snapArr = new SnapshotArray(3); // 크기가 3인 SnapshotArray를 선언합니다
        snapArr.set(0, 5); // array[0] = 5로 할당합니다
        snapArr.snap(); // 현재 배열의 상태로 snapshot을 생성하고 snap_id = 0을 리턴합니다

        snapArr.set(0, 6); // array[0] = 6으로 할당합니다
        snapArr.snap(); // 현재 배열의 상태로 snapshot을 생성하고 snap_id = 1을 리턴합니다

        snapArr.get(0, 0); // snap_id = 0일 때의 array[0]의 값 5를 리턴합니다
        snapArr.get(0, 1); // snap_id = 1일 때의 array[0]의 값 6을 리턴합니다

    }

    public SnapshotArray() {
        this(0);
    }

    public SnapshotArray(int length) {
        snapId = -1;
        array = new int[length];
        snapshots = new int[5000][length];
    }

    public void set(int index, int val) {
        array[index] = val;
    }

    public int snap() {
        snapshots[++snapId] = array.clone();

        return snapId;
    }

    public int get(int index, int snapId) {
        return snapshots[snapId][index];
    }
}
