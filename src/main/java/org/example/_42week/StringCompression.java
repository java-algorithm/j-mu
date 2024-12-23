package org.example._42week;

public class StringCompression {

    public static void main(String[] args) {
        char[] chars = {'a','a','b','b','c','c','c'};

        compress(chars);
    }

    public static int compress(char[] chars) {
        if (chars.length == 1) {
            return 1;
        }

//        StringBuilder sb = new StringBuilder();
        String sb = "";
        char prev = chars[0];
        int count = 1;
        for (int i = 1; i < chars.length; i++) {
            if (chars[i] == prev) {
                count++;
            } else {
                sb +=prev+count;
//                sb.append(prev).append(count!=1? count : "");

                prev = chars[i];
                count =1;
            }
        }
        sb +=prev+count;
//        sb.append(prev).append(count==1? count : "");

        for (int i = 0; i < sb.length(); i++) {
            chars[i] = sb.charAt(i);
        }

        return sb.length();
    }
}
