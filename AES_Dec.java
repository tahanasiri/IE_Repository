package Cryptography;

import java.util.Scanner;

public class Question_2 {
    public static String[][] keyMatrix = new String[4][44];
    public static char[] equivalent = {'0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f'};
    public static String[] rC = {"01","02","04","08","10","20","40","80","1b","36"};
    public static int ctr=0;
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

    public static String[][] InvLookupTable = {
            {"52","09","6a","d5","30","36","a5","38","bf","40","a3","9e","81","f3","d7","fb"},
            {"7c","e3","39","82","9b","2f","ff","87","34","8e","43","44","c4","de","e9","cb"},
            {"54","7b","94","32","a6","c2","23","3d","ee","4c","95","0b","42","fa","c3","4e"},
            {"08","2e","a1","66","28","d9","24","b2","76","5b","a2","49","6d","8b","d1","25"},
            {"72","f8","f6","64","86","68","98","16","d4","a4","5c","cc","5d","65","b6","92"},
            {"6c","70","48","50","fd","ed","b9","da","5e","15","46","57","a7","8d","9d","84"},
            {"90","d8","ab","00","8c","bc","d3","0a","f7","e4","58","05","b8","b3","45","06"},
            {"d0","2c","1e","8f","ca","3f","0f","02","c1","af","bd","03","01","13","8a","6b"},
            {"3a","91","11","41","4f","67","dc","ea","97","f2","cf","ce","f0","b4","e6","73"},
            {"96","ac","74","22","e7","ad","35","85","e2","f9","37","e8","1c","75","df","6e"},
            {"47","f1","1a","71","1d","29","c5","89","6f","b7","62","0e","aa","18","be","1b"},
            {"fc","56","3e","4b","c6","d2","79","20","9a","db","c0","fe","78","cd","5a","f4"},
            {"1f","dd","a8","33","88","07","c7","31","b1","12","10","59","27","80","ec","5f"},
            {"60","51","7f","a9","19","b5","4a","0d","2d","e5","7a","9f","93","c9","9c","ef"},
            {"a0","e0","3b","4d","ae","2a","f5","b0","c8","eb","bb","3c","83","53","99","61"},
            {"17","2b","04","7e","ba","77","d6","26","e1","69","14","63","55","21","0c","7d"}};
    public static int[][] mixColumn = {
            {14, 11 , 13 ,  9},
            {9 , 14 , 11 , 13},
            {13,  9 , 14 , 11},
            {11, 13 ,  9 , 14}};

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        /*String[][] cipherText = {
                {"47","40","a3","4c"},
                {"37","d4","70","9f"},
                {"94","e4","3a","42"},
                {"ed","a5","a6","bc"}};*/

        /*String[][] initialKey = {
                {"2b","28","ab","09"},
                {"7e","ae","f7","cf"},
                {"15","d2","15","4f"},
                {"16","a6","88","3c"}};*/

        /*String[][] cipherText = {
                {"0e","c6","45","14"},
                {"dd","21","5b","18"},
                {"33","e5","d8","be"},
                {"d3","46","ba","c8"}};
        String[][] initialKey = {
                {"80","00","00","00"},
                {"00","00","00","00"},
                {"00","00","00","00"},
                {"00","00","00","00"}};*/

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

        String[][] cipherText = new String[4][4];
        String text = input.next();
        // this part separates  the inputPlainText 2 characters by 2 characters
        p = 0 ;
        q = 2;
        for (int j = 0; j < 4; j++) {
            for (int i = 0; i < 4; i++) {
                cipherText[i][j] = text.substring(p,q);
                p = q;
                q = p + 2;
            }
        }
        keySchedule(initialKey);
        int num1 = 40;
        int num2 = 43;


        AddRoundKeyStep(cipherText,cutMatrix(num1,num2));
        InverseLinearShiftStep(cipherText);
        InverseS_BoxStep(cipherText);

        for (int i = 2; i <=10 ; i++) {
            num1 -=4;
            num2 -=4;
            AddRoundKeyStep(cipherText,cutMatrix(num1,num2));
            InverseMixColumnStep(cipherText);
            InverseLinearShiftStep(cipherText);
            InverseS_BoxStep(cipherText);
        }
        num1 -=4;
        num2 -=4;
        AddRoundKeyStep(cipherText,cutMatrix(num1,num2));

        for (int j = 0; j < 4; j++) {
            for (int i = 0; i < 4 ; i++) {
                System.out.print(cipherText[i][j]);
            }
        }
        //showPlainText(cipherText);

    }
    public static void showPlainText(String[][] cipherText){
        for (int i = 0; i <cipherText.length ; i++) {
            for (int j = 0; j <cipherText[0].length ; j++) {
                System.out.print(cipherText[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("__________________________________________________________");
    }

    public static void InverseS_BoxStep(String[][] cipherText){
        for (int i = 0; i < 4 ; i++) {
            for (int j = 0; j < 4 ; j++) {
                cipherText[i][j] = findInS_BOX2(cipherText[i][j]);
            }
        }
    }

    public static void InverseLinearShiftStep(String[][] cipherText){
        String[] helpArray = new String[4];
        String[] endWork;
        for (int i = 1; i < 4 ; i++) {
            for (int j = 0; j < 4 ; j++) {
                helpArray[j] = cipherText[i][j];
            }
            if (i == 1)
                endWork = rightShift_1(helpArray);
            else if (i == 2)
                endWork = rightShift_2(helpArray);
            else
                endWork = rightShift_3(helpArray);

            for (int j = 0; j < 4; j++) {
                cipherText[i][j] = endWork[j];
            }
        }

    }

    public static String[] rightShift_1(String[] helpArray_1){
        String temp=helpArray_1[3];
        for (int i = 3; i >0 ; i--)
            helpArray_1[i] = helpArray_1[(i-1)];
        helpArray_1[0] = temp;

        return helpArray_1;
    }
    public static String[] rightShift_2(String[] helpArray){
        String[] helpArray_2 = rightShift_1(helpArray);
        String temp=helpArray_2[3];
        for (int i = 3; i >0 ; i--)
            helpArray_2[i] = helpArray_2[(i-1)];
        helpArray_2[0] = temp;

        return helpArray_2;
    }
    public static String[] rightShift_3(String[] helpArray){
        String[] helpArray_3 = rightShift_2(helpArray);
        String temp=helpArray_3[3];
        for (int i = 3; i >0 ; i--)
            helpArray_3[i] = helpArray_3[(i-1)];
        helpArray_3[0] = temp;

        return helpArray_3;
    }

    public static void InverseMixColumnStep(String[][] cipherText){
        int[][] decCipherText = hexToDec(cipherText);
        String sum ="00";
        for (int i = 0; i < 4 ; i++) {
            for (int j = 0; j < 4 ; j++) {
                for (int k = 0; k < 4 ; k++) {
                    sum = xorHex(sum, forInvMixColumn(mixColumn[i][k], decCipherText[k][j]));

                }
                cipherText[i][j] = sum;
                sum = "00";
            }
        }

    }

    public static String forInvMixColumn(int num_1, int num_2){
        if (num_1 == 9){
            return xorHex(modularMultiply(hexToDec_2(modularMultiply(hexToDec_2(modularMultiply(num_2))))), decToHex(num_2));
        }
        else if (num_1 == 11){
            return xorHex(modularMultiply(hexToDec_2(xorHex(modularMultiply(hexToDec_2(modularMultiply(num_2))), decToHex(num_2)))), decToHex(num_2));
        }
        else if (num_1 == 13){
            return xorHex(modularMultiply(hexToDec_2(modularMultiply(hexToDec_2(xorHex(modularMultiply(num_2), decToHex(num_2)))))), decToHex(num_2));
        }
        else {
            return modularMultiply(hexToDec_2(xorHex(modularMultiply(hexToDec_2(xorHex(modularMultiply(num_2), decToHex(num_2)))), decToHex(num_2))));
        }
    }

    public static String modularMultiply(int num){

        if (num < 128)
            return decToHex((2*num));
        else
            return xorHex(decToHex((2*num)), ("1b"));

    }

    public static void AddRoundKeyStep(String[][] cipherText, String[][] cipherKey){
        for (int i = 0; i < 4 ; i++) {
            for (int j = 0; j < 4 ; j++) {
                cipherText[i][j] = xorHex(cipherText[i][j], cipherKey[i][j]);
            }
        }

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

    public static int[][] hexToDec(String[][] cipherText){
        int[][] decCipherText = new int[4][4];
        for (int i = 0; i < 4 ; i++) {
            for (int j = 0; j < 4 ; j++) {
                int x = findIndex(cipherText[i][j].charAt(0));
                int y = findIndex(cipherText[i][j].charAt(1));
                decCipherText[i][j] = y + (x * 16);
            }
        }

        return decCipherText;
    }
    public static int hexToDec_2(String cipherText){
        int x = findIndex(cipherText.charAt(0));
        int y = findIndex(cipherText.charAt(1));

        return y + (x * 16);
    }





    public static void keySchedule(String[][] initialKey){
        int column_1 = 0, column_2 = 3;
        fillMat(initialKey,column_1,column_2);
        for (int round = 1; round <= 10 ; round++) {
            column_1 +=4;
            column_2 +=4;
            subKeyGenerator(initialKey);
            fillMat(initialKey, column_1, column_2);
        }

    }

    public static void fillMat(String[][] initialKey, int column_1, int column_2){

            for (int j = 0, k = column_1; j < 4 && k <= column_2; j++, k++) {
                for (int i = 0; i < 4; i++) {
                    keyMatrix[i][k] = initialKey[i][j];
                }
            }
    }

    public static String[][] cutMatrix(int num1, int num2){
        String[][] keyHelp = new String[4][4];
        for (int j = 0, k =num1; j < 4 && k <= num2 ; j++, k++) {
            for (int i = 0; i < 4 ; i++) {
                keyHelp[i][j] = keyMatrix[i][k];
            }
        }
        return keyHelp;
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
    public static String findInS_BOX2(String value){

        int row = findIndex(value.charAt(0));
        int column= findIndex(value.charAt(1));
        return InvLookupTable[row][column];
    }
    public static int findIndex(char s){
        for (int i = 0; i <equivalent.length ; i++) {
            if (equivalent[i] == s)
                return i;
        }

        return -1;
    }












}
