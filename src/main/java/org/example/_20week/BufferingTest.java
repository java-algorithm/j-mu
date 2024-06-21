package org.example._20week;

import org.junit.jupiter.api.Test;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class BufferingTest {

    public static final int TEN_THOUSAND = 100_000;

    @Test
    public void bufferedReaderTest() throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.flush();

        long startTime = System.nanoTime();
        for (int i = 0; i < TEN_THOUSAND; i++) {
            bw.write(i);
        }
        bw.flush();
        long endTime = System.nanoTime();
        long elapsedTime = endTime - startTime;


        long startTime2 = System.nanoTime();
        for (int i = 0; i < TEN_THOUSAND; i++) {
            System.out.println(i);
        }
        long endTime2 = System.nanoTime();
        long elapsedTime2 = endTime2 - startTime2;

        System.out.println("total elapsed time (bw)   = " + elapsedTime);
        System.out.println("total elapsed time (sout) = " + elapsedTime2);

        System.out.printf("bw가 sout보다 %d배 빠릅니다. ", elapsedTime2 / elapsedTime);
    }
}
