package Cryptography;

import java.util.Scanner;

public class Question_1 {
    public static char[] equivalent = {'0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f'};
    public static String[] rC = {"01","02","04","08","10","20","40","80","1b","36"};
    public static int ctr=0;
    // mixColumn for third step except the last round
    public static int[][] mixColumn = {
            {2,3,1,1},
            {1,2,3,1},
            {1,1,2,3},
            {3,1,1,2}};
    // lookupTable for Substitution Box
    public static String[][] lookupTable = {
            {"63","7c","77","7b","f2","6b","6f","c5","30","01","67","2b","fe","d7","ab","76"},
            {"ca","82","c9","7d","fa","59","47","f0","ad","d4","a2","af","9c","a4","72","c0"},
            {"b7","fd","93","26","36","3f","f7","cc","34","a5","e5","f1","71","d8","31","15"},
            {"04","c7","23","c3","18","96","05","9a","07","12","80","e2","eb","27","b2","75"},
            {"09","83","2c","1a","1b","6e","5a","a0","52","3b","d6","b3","29","e3","2f","84"},
            {"53","d1","00","ed","20","fc","b1","5b","6a","cb","be","39","4a","4c","58","cf"},
            {"d0","ef","aa","fb","43","4d","33","85","45","f9","02","7f","50","3c","9f","a8"},
            {"51","a3","40","8f","92","9d","38","f5","bc","b6","da","21","10","ff","f3","d2"},
            {"cd","0c","13","ec","5f","97","44","17","c4","a7","7e","3d","64","5d","19","73"},
            {"60","81","4f","dc","22","2a","90","88","46","ee","b8","14","de","5e","0b","db"},
            {"e0","32","3a","0a","49","06","24","5c","c2","d3","ac","62","91","95","e4","79"},
            {"e7","c8","37","6d","8d","d5","4e","a9","6c","56","f4","ea","65","7a","ae","08"},
            {"ba","78","25","2e","1c","a6","b4","c6","e8","dd","74","1f","4b","bd","8b","8a"},
            {"70","3e","b5","66","48","03","f6","0e","61","35","57","b9","86","c1","1d","9e"},
            {"e1","f8","98","11","69","d9","8e","94","9b","1e","87","e9","ce","55","28","df"},
            {"8c","a1","89","0d","bf","e6","42","68","41","99","2d","0f","b0","54","bb","16"} };
    public static void main(String[] args) {
        Scanner input=new Scanner(System.in);
        /*String[][] initialKey = {
                {"2b","28","ab","09"},
                {"7e","ae","f7","cf"},
                {"15","d2","15","4f"},
                {"16","a6","88","3c"}};
        String[][] plainText = {
                {"32","88","31","e0"},
                {"43","5a","31","37"},
                {"f6","30","98","07"},
                {"a8","8d","a2","34"}};*/


        /*String[][] initialKey = {
                {"54","73","20","67"},
                {"68","20","4b","20"},
                {"61","6d","75","46"},
                {"74","79","6e","75"}};
        String[][] plainText = {
                {"54","4f","4e","20"},
                {"77","6e","69","54"},
                {"6f","65","6e","77"},
                {"20","20","65","6f"}};*/


        /*String[][] initialKey = {
                {"c0","00","00","00"},
                {"00","00","00","00"},
                {"00","00","00","00"},
                {"00","00","00","00"}};
        String[][] plainText = {
                {"00","00","00","00"},
                {"00","00","00","00"},
                {"00","00","00","00"},
                {"00","00","00","00"}};*/

        /*for (int rnd = 1; rnd <= 10 ; rnd++) {
            subKeyGenerator(initialKey);
            System.out.print("Round " + rnd + ":  ");
            System.out.println();
            showPlainText(initialKey);
        }*/


        String[][] initialKey = new String[4][4];
        String key = input.next();
        // this part separates  the inputKey 2 characters by 2 characters
        int p = 0;
        int q = 2;
        for (int j = 0; j < 4; j++) {
            for (int i = 0; i < 4; i++) {
                initialKey[i][j] = key.substring(p,q);
                p = q;
                q = p + 2;
            }
        }

        String[][] plainText = new String[4][4];
        String text = input.next();
        // this part separates  the inputPlainText 2 characters by 2 characters
        p = 0 ;
        q = 2;
        for (int j = 0; j < 4; j++) {
            for (int i = 0; i < 4; i++) {
                plainText[i][j] = text.substring(p,q);
                p = q;
                q = p + 2;
            }
        }
        // Our plainText for round 1 to 10 is the XOR Operation of inputKey Matrix and inputPlainText Matrix
        forthStep(plainText,initialKey);

        for (int rnd = 1; rnd < 10 ; rnd++) {
            firstStep(plainText);
            secondStep(plainText);
            thirdStep(plainText);
            subKeyGenerator(initialKey);
            forthStep(plainText, initialKey);
            //showPlainText(plainText);
        }
        // last round (round 10) doesn't have MixColumn step
        firstStep(plainText);
        secondStep(plainText);
        subKeyGenerator(initialKey);
        forthStep(plainText, initialKey);
        //showPlainText(plainText);

        for (int j = 0; j < 4; j++) {
            for (int i = 0; i < 4 ; i++) {
                System.out.print(plainText[i][j]);
            }
        }

    }

    /*public static void showPlainText(String[][] plainText){
        for (int i = 0; i <4 ; i++) {
            for (int j = 0; j <4 ; j++) {
                System.out.print(plainText[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("__________________________________________________________");
    }*/
    public static void firstStep(String[][] plainText){
        for (int i = 0; i < 4 ; i++) {
            for (int j = 0; j < 4 ; j++) {
                plainText[i][j] = findInS_BOX(plainText[i][j]);
            }
        }
    }

    public static void secondStep(String[][] plainText){
        String[] helpArray = new String[4];
        String[] endWork;
        for (int i = 1; i < 4 ; i++) {
            for (int j = 0; j < 4 ; j++) {
                helpArray[j] = plainText[i][j];
            }
            if (i == 1)
                endWork = lineShift_1(helpArray);
            else if (i == 2)
                endWork = lineShift_2(helpArray);
            else
                endWork = lineShift_3(helpArray);

            for (int j = 0; j < 4; j++) {
                plainText[i][j] = endWork[j];
            }
        }

    }
    public static String[] lineShift_1(String[] helpArray_1){
        String temp=helpArray_1[0];
        for (int i = 0; i <3 ; i++)
            helpArray_1[i] = helpArray_1[(i+1)];
        helpArray_1[3] = temp;

        return helpArray_1;
    }
    public static String[] lineShift_2(String[] helpArray){
        String[] helpArray_2 = lineShift_1(helpArray);
        String temp=helpArray_2[0];
        for (int i = 0; i <3 ; i++)
            helpArray_2[i] = helpArray_2[(i+1)];
        helpArray_2[3] = temp;

        return helpArray_2;
    }
    public static String[] lineShift_3(String[] helpArray){
        String[] helpArray_3 = lineShift_2(helpArray);
        String temp=helpArray_3[0];
        for (int i = 0; i <3 ; i++)
            helpArray_3[i] = helpArray_3[(i+1)];
        helpArray_3[3] = temp;

        return helpArray_3;
    }

    public static void thirdStep(String[][] plainText){
        int[][] decPlaintext = hexToDec(plainText);
        String sum ="00";
        for (int i = 0; i < 4 ; i++) {
            for (int j = 0; j < 4 ; j++) {
                for (int k = 0; k < 4 ; k++) {
                    sum = xorHex(sum, modularMultiply(mixColumn[i][k], decPlaintext[k][j]));

                }
                plainText[i][j] = sum;
                sum = "00";
            }
        }

    }
    public static String modularMultiply(int num_1, int num_2){
        if (num_1 == 1)
            return decToHex(num_2);
        else if (num_1 == 2){
            if (num_2 < 128)
                return decToHex((num_1*num_2));
            else
                return xorHex(decToHex((num_1*num_2)), ("1b"));
        }
        else {
            if (num_2 < 128)
                return xorHex(decToHex((2*num_2)), decToHex(num_2));
            else {
                return xorHex(xorHex(decToHex((2*num_2)), ("1b")), decToHex(num_2));
            }
        }
    }


    public static int[][] hexToDec(String[][] plainText){
        int[][] decPlaintext = new int[4][4];
        for (int i = 0; i < 4 ; i++) {
            for (int j = 0; j < 4 ; j++) {
                int x = findIndex(plainText[i][j].charAt(0));
                int y = findIndex(plainText[i][j].charAt(1));
                decPlaintext[i][j] = y + (x * 16);
            }
        }

        return decPlaintext;
    }

    public static String decToHex(int nybble) {
        String str_1 = "";
        for (int i = 0; i < 2 ; i++) {
            int mod = nybble % 16;
            str_1 = str_1.concat(String.valueOf(equivalent[mod]));
            nybble = nybble / 16;
        }
        String str_2 = "";
        for (int i = 1; i >=0 ; i--) {
            str_2 = str_2.concat(String.valueOf(str_1.charAt(i)));
        }

        return str_2;
    }

    public static void forthStep(String[][] plainText, String[][] roundSubKey){
        for (int i = 0; i < 4 ; i++) {
            for (int j = 0; j < 4 ; j++) {
                plainText[i][j] = xorHex(plainText[i][j], roundSubKey[i][j]);
            }
        }

    }
    public static void subKeyGenerator(String[][] lastSubKey){
        String[] helpArr_1 =new String[4];

        for (int j = 0; j < 4 ; j++) {
            if (j % 4 == 0){
                for (int i = 0; i < 4 ; i++) {
                    helpArr_1[i] = lastSubKey[i][(j + 3)];

                }
                String[] end = funC(helpArr_1);

                for (int i = 0; i < 4 ; i++) {
                    lastSubKey[i][j] = xorHex(lastSubKey[i][j] , end[i]);
                }

            }
            else {
                for (int i = 0; i < 4 ; i++) {
                    helpArr_1[i] = lastSubKey[i][(j-1)];
                }

                for (int i = 0; i < 4 ; i++) {
                    lastSubKey[i][j] = xorHex(lastSubKey[i][j] , helpArr_1[i]);
                }
            }

        }

    }
    public static String[] funC(String[] helpArr){
        String temp=helpArr[0];
        for (int i = 0; i <3 ; i++)
            helpArr[i] = helpArr[(i+1)];
        helpArr[3] = temp;


        for (int i = 0; i <helpArr.length ; i++) {
            helpArr[i] = findInS_BOX(helpArr[i]);
        }
        helpArr[0] = xorHex(helpArr[0], rC[ctr]);

        ctr++;

        return helpArr;

    }


    public static String findInS_BOX(String value){

        int row = findIndex(value.charAt(0));
        int column= findIndex(value.charAt(1));
        return lookupTable[row][column];
    }

    public static int findIndex(char s){
        for (int i = 0; i <equivalent.length ; i++) {
            if (equivalent[i] == s)
                return i;
        }

        return -1;
    }

    public static String xorHex(String a, String b) {
        // TODO: Validation
        char[] chars = new char[a.length()];
        for (int i = 0; i < chars.length; i++) {
            chars[i] = toHex(fromHex(a.charAt(i)) ^ fromHex(b.charAt(i)));
        }
        return new String(chars);
    }

    public static int fromHex(char c) {
        if (c >= '0' && c <= '9') {
            return c - '0';
        }
        if (c >= 'A' && c <= 'F') {
            return c - 'A' + 10;
        }
        if (c >= 'a' && c <= 'f') {
            return c - 'a' + 10;
        }
        throw new IllegalArgumentException();
    }
    
    public static char toHex(int nybble) {
        if (nybble < 0 || nybble > 15) {
            throw new IllegalArgumentException();
        }
        return "0123456789abcdef".charAt(nybble);
    }

}
