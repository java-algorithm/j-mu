package org.example._45week;

public class MinimumFlipsToMakeAORBEqualToC {

    public static void main(String[] args) {
        int answer = minFlips(8, 3, 5);
        System.out.println(answer);
    }

    public static int minFlips(int a, int b, int c) {
        String binaryA = Integer.toBinaryString(a);
        String binaryB = Integer.toBinaryString(b);
        String binaryC = Integer.toBinaryString(c);
        String binaryAOrB = Integer.toBinaryString(a|b);

        int maxLength = Math.max(Math.max(binaryA.length(),binaryB.length()),Math.max(binaryC.length(),binaryAOrB.length()));
        binaryA = convertBinaryString(binaryA,maxLength);
        binaryB = convertBinaryString(binaryB,maxLength);
        binaryC = convertBinaryString(binaryC,maxLength);
        binaryAOrB = convertBinaryString(binaryAOrB,maxLength);

        System.out.println(binaryA);
        System.out.println(binaryB);
        System.out.println(binaryAOrB);
        System.out.println(binaryC);
        int count =0;
        for(int i = 0; i<maxLength; i++){
            if(binaryC.charAt(i) !=binaryAOrB.charAt(i)){
                if(binaryC.charAt(i)=='0'){
                    if(binaryA.charAt(i)=='1'){
                        count++;
                    }
                    if(binaryB.charAt(i)=='1'){
                        count++;
                    }
                }else{
                    count++;
                }
            }
        }

        return count;
    }

    public static String convertBinaryString(String a, int maxLength){
        StringBuilder sb = new StringBuilder();
        for(int i=0; i< maxLength-a.length(); i++){
            sb.append(0);
        }

        sb.append(a);

        return sb.toString();
    }
}
