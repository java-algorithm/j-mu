package org.example._1week;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SearchingDocument {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        String document = br.readLine();
        String word = br.readLine();
        if (word.equals("")) {
            System.out.println(0);
            br.close();
            return;
        }

        int index = 0;
        int indexBound = document.length() - word.length();

        int count = 0;
        while (index <= indexBound) {
            if (document.charAt(index) != word.charAt(0)) {
                index++;
                continue;
            }

            int temp = index + 1;
            for (int i = 0; i < word.length(); i++) {
                if (document.charAt(index) != word.charAt(i)) {
                    index = temp;
                    break;
                }
                if (i == word.length() - 1) {
                    count++;
                }
                index++;
            }
        }

        System.out.println(count);
        br.close();
    }
}


