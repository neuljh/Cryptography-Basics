


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

public class a {
    private static HashMap<Character, Integer> alphabetForm;
    private static int[] Init_trans;
    private static int[] PC_1;
    private static int[] PC_2;
    private static int[] EBox;
    private static int[] PBox;
    private static int[] FT;
    private static int[][][] SBox;
    private static int[][] L;
    private static int[][] R;
    private static String keyword_64;
    private static String plaintext_64;
    private static String ciphertext_64;
    private static int[][] LP;
    private static int[][] RP;
    private static String[] LP_string;
    private static String[] RP_string;

    public static void Init_all() {
        Init_trans = new int[] {
                58, 50, 42, 34, 26, 18, 10, 2,
                60, 52, 44, 36, 28, 20, 12, 4,
                62, 54, 46, 38, 30, 22, 14, 6,
                64, 56, 48, 40, 32, 24, 16, 8,
                57, 49, 41, 33, 25, 17, 9, 1,
                59, 51, 43, 35, 27, 19, 11, 3,
                61, 53, 45, 37, 29, 21, 13, 5,
                63, 55, 47, 39, 31, 23, 15, 7
        };

        PC_1 = new int[] {
                57, 49, 41, 33, 25, 17, 9, 1,
                58, 50, 42, 34, 26, 18, 10, 2,
                59, 51, 43, 35, 27, 19, 11, 3,
                60, 52, 44, 36, 63, 55, 47, 39,
                31, 23, 15, 7, 62, 54, 46, 38,
                30, 22, 14, 6, 61, 53, 45, 37,
                29, 21, 13, 5, 28, 20, 12, 4

        };

        PC_2 = new int[] {
        		14,17,11,24,1,5,
        		3,28,15,6,21,10,
        		23,19,12,4,26,8,
        		16,7,27,20,13,2,
        		41,52,31,37,47,55,
        		30,40,51,45,33,48,
        		44,49,39,56,34,53,
        		46,42,50,36,29,32
        };

        EBox = new int[] {
                32, 1, 2, 3, 4, 5,
                4, 5, 6, 7, 8, 9,
                8, 9, 10, 11, 12, 13,
                12, 13, 14, 15, 16, 17,
                16, 17, 18, 19, 20, 21,
                20, 21, 22, 23, 24, 25,
                24, 25, 26, 27, 28, 29,
                28, 29, 30, 31, 32, 1
        };

        PBox = new int[] {
                16, 7, 20, 21, 29, 12, 28, 17, 1, 15, 23, 26, 5, 18, 31, 10,
                2, 8, 24, 14, 32, 27, 3, 9, 19, 13, 30, 6, 22, 11, 4, 25
        };

        FT = new int[] {
                40, 8, 48, 16, 56, 24, 64, 32, 39, 7, 47, 15, 55, 23, 63, 31,
                38, 6, 46, 14, 54, 22, 62, 30, 37, 5, 45, 13, 53, 21, 61, 29,
                36, 4, 44, 12, 52, 20, 60, 28, 35, 3, 43, 11, 51, 19, 59, 27,
                34, 2, 42, 10, 50, 18, 58, 26, 33, 1, 41, 9, 49, 17, 57, 25
        };

        SBox = new int[][][] {
                {
                        { 14, 4, 13, 1, 2, 15, 11, 8, 3, 10, 6, 12, 5, 9, 0, 7 },
                        { 0, 15, 7, 4, 14, 2, 13, 1, 10, 6, 12, 11, 9, 5, 3, 8 },
                        { 4, 1, 14, 8, 13, 6, 2, 11, 15, 12, 9, 7, 3, 10, 5, 0 },
                        { 15, 12, 8, 2, 4, 9, 1, 7, 5, 11, 3, 14, 10, 0, 6, 13 }
                },
                {
                        { 15, 1, 8, 14, 6, 11, 3, 4, 9, 7, 2, 13, 12, 0, 5, 10 },
                        { 3, 13, 4, 7, 15, 2, 8, 14, 12, 0, 1, 10, 6, 9, 11, 5 },
                        { 0, 14, 7, 11, 10, 4, 13, 1, 5, 8, 12, 6, 9, 3, 2, 15 },
                        { 13, 8, 10, 1, 3, 15, 4, 2, 11, 6, 7, 12, 0, 5, 14, 9 }
                },
                {
                        { 10, 0, 9, 14, 6, 3, 15, 5, 1, 13, 12, 7, 11, 4, 2, 8 },
                        { 13, 7, 0, 9, 3, 4, 6, 10, 2, 8, 5, 14, 12, 11, 15, 1 },
                        { 13, 6, 4, 9, 8, 15, 3, 0, 11, 1, 2, 12, 5, 10, 14, 7 },
                        { 1, 10, 13, 0, 6, 9, 8, 7, 4, 15, 14, 3, 11, 5, 2, 12 }
                },
                {
                        { 7, 13, 14, 3, 0, 6, 9, 10, 1, 2, 8, 5, 11, 12, 4, 15 },
                        { 13, 8, 11, 5, 6, 15, 0, 3, 4, 7, 2, 12, 1, 10, 14, 9 },
                        { 10, 6, 9, 0, 12, 11, 7, 13, 15, 1, 3, 14, 5, 2, 8, 4 },
                        { 3, 15, 0, 6, 10, 1, 13, 8, 9, 4, 5, 11, 12, 7, 2, 14 }
                },
                {
                        { 2, 12, 4, 1, 7, 10, 11, 6, 8, 5, 3, 15, 13, 0, 14, 9 },
                        { 14, 11, 2, 12, 4, 7, 13, 1, 5, 0, 15, 10, 3, 9, 8, 6 },
                        { 4, 2, 1, 11, 10, 13, 7, 8, 15, 9, 12, 5, 6, 3, 0, 14 },
                        { 11, 8, 12, 7, 1, 14, 2, 13, 6, 15, 0, 9, 10, 4, 5, 3 }
                },
                {
                        { 12, 1, 10, 15, 9, 2, 6, 8, 0, 13, 3, 4, 14, 7, 5, 11 },
                        { 10, 15, 4, 2, 7, 12, 9, 5, 6, 1, 13, 14, 0, 11, 3, 8 },
                        { 9, 14, 15, 5, 2, 8, 12, 3, 7, 0, 4, 10, 1, 13, 11, 6 },
                        { 4, 3, 2, 12, 9, 5, 15, 10, 11, 14, 1, 7, 6, 0, 8, 13 }
                },
                {
                        { 4, 11, 2, 14, 15, 0, 8, 13, 3, 12, 9, 7, 5, 10, 6, 1 },
                        { 13, 0, 11, 7, 4, 9, 1, 10, 14, 3, 5, 12, 2, 15, 8, 6 },
                        { 1, 4, 11, 13, 12, 3, 7, 14, 10, 15, 6, 8, 0, 5, 9, 2 },
                        { 6, 11, 13, 8, 1, 4, 10, 7, 9, 5, 0, 15, 14, 2, 3, 12 }
                },
                {
                        { 13, 2, 8, 4, 6, 15, 11, 1, 10, 9, 3, 14, 5, 0, 12, 7 },
                        { 1, 15, 13, 8, 10, 3, 7, 4, 12, 5, 6, 11, 0, 14, 9, 2 },
                        { 7, 11, 4, 1, 9, 12, 14, 2, 0, 6, 10, 13, 15, 3, 5, 8 },
                        { 2, 1, 14, 7, 4, 10, 8, 13, 15, 12, 9, 0, 3, 5, 6, 11 }
                }
        };
        L = new int[17][28];
        R = new int[17][28];

        LP = new int[17][32];
        RP = new int[17][32];

        LP_string = new String[17];
        RP_string = new String[17];

    }// 初始化所有备用数据
    
    public static String getString_two(String str2){
        String[] str={"0","1","2","3","4","5","6","7","8","9","A","B","C","D","E","F"};
        String res=new String();
        String[] str16=new String[str2.length()/4];
        for(int i=0;i<str16.length;i++){
            str16[i]=str2.substring(i*4,(i+1)*4);
        }
        for(int i=0;i<str16.length;i++){
            if(str16[i].equals("0000")){
                res=res+str[0];
            }
            if(str16[i].equals("0001")){
                res=res+str[1];
            }
            if(str16[i].equals("0010")){
                res=res+str[2];
            }
            if(str16[i].equals("0011")){
                res=res+str[3];
            }
            if(str16[i].equals("0100")){
                res=res+str[4];
            }
            if(str16[i].equals("0101")){
                res=res+str[5];
            }
            if(str16[i].equals("0110")){
                res=res+str[6];
            }
            if(str16[i].equals("0111")){
                res=res+str[7];
            }
            if(str16[i].equals("1000")){
                res=res+str[8];
            }
            if(str16[i].equals("1001")){
                res=res+str[9];
            }
            if(str16[i].equals("1010")){
                res=res+str[10];
            }
            if(str16[i].equals("1011")){
                res=res+str[11];
            }
            if(str16[i].equals("1100")){
                res=res+str[12];
            }
            if(str16[i].equals("1101")){
                res=res+str[13];
            }
            if(str16[i].equals("1110")){
                res=res+str[14];
            }
            if(str16[i].equals("1111")){
                res=res+str[15];
            }


        }
        return res;
    }


    public static String getString_Hex(String str16){
        String[] str= {"0000","0001","0010","0011","0100","0101","0110","0111",
                "1000","1001","1010","1011","1100","1101","1110","1111"};
        String[] str2=new String[str16.length()];
        String res=new String();
        int j=0;
        for(int i=0;i<str16.length();i++) {
            char c=str16.charAt(i);
            int x;
            if(c<'0'||c>'9') {
                x=c-'A'+10;
            }else {
                x=c-'0';
            }
            str2[j]=str[x];
            j++;
        }
        for(int i=0;i<str.length;i++){
            res=res+str2[i];
        }
        return res;
    }//将十六进制转换成二进制

    public static String toBinary(String str) {
        char[] strChar = str.toCharArray();
        String result = new String();
        for (int i = 0; i < strChar.length; i++) {
            result += Integer.toBinaryString(strChar[i]) + " ";
        }
        return result;
    }

    public static String getPlaintext_64(String plaintext) {
        String res = new String();
        for (int index = 0; index < plaintext.length(); index++) {
            res = res + '0' + Integer.toString((int) plaintext.charAt(index), 2);
        }
        return res;
    }// 将输入的字母明文转换成二进制码

    public static String getKeyword_56(String keyword) {
        String res = new String();
        for (int index = 0; index < keyword.length(); index++) {
            res = res + Integer.toString((int) keyword.charAt(index), 2);
            // res=res+" ";
        }
        return res;

    }// 将输入的密钥转换成56位二进制码

    public static String getKeyword_64(String keyword) {
        String res = new String();
        for (int index = 0; index < keyword.length(); index++) {
            res = res + Integer.toString((int) keyword.charAt(index), 2);
            int times = 0;
            for (int i = 0; i < Integer.toString((int) keyword.charAt(index), 2).length(); i++) {
                if (Integer.toString((int) keyword.charAt(index), 2).charAt(i) == '0') {
                    times++;
                }
            }
            if (times % 2 == 0) {
                res = res + '0';
            } else {
                res = res + '1';
            }
            // res=res+" ";
        }
        return res;

    }// 将输入的密钥转换成64位二进制码

    public static String get_Kn(int n, String keyword_64) {
        int index = 0;
        String keyword_56_trans = new String();

        for (int i = 0; i < PC_1.length; i++) {
            keyword_56_trans = keyword_56_trans + keyword_64.charAt(PC_1[i]-1);
        } // 得到初始置换后的密钥

        for (int i = 0; i < 28; i++) {
            L[0][i] = (keyword_56_trans.charAt(i) - '0');
        } // 将初始置换后的密钥分成两部分，此处为左边的28位L0
        for (int i = 28; i < 56; i++) {
            R[0][i - 28] = (keyword_56_trans.charAt(i) - '0');
        } // 此处为右边的28位R0

        for (int i = 0; i < 28; i++) {
            if (i == 27) {
                L[1][i] = L[0][0];
            } else {
                L[1][i] = L[0][i + 1];
            }

        } // 根据密钥变换规则，此处循环左移位数为1，得到L1

        for (int i = 28; i < 56; i++) {
            if (i == 55) {
                R[1][i - 28] = R[0][0];
            } else {
                R[1][i - 28] = R[0][i + 1 - 28];
            }
        } // 根据密钥变换规则，此处循环左移位数为1，得到R1

        index++;// 索引加1

        while (index != n) {
            if (index+1== 1 || index+1 == 2 || index+1 == 9 || index+1 == 16) {
                for (int i = 0; i < 28; i++) {
                    if (i == 27) {
                        L[index + 1][i] = L[index][0];
                    } else {
                        L[index + 1][i] = L[index][i + 1];
                    }

                }
                for (int i = 28; i < 56; i++) {
                    if (i == 55) {
                        R[index + 1][i - 28] = R[index][0];
                    } else {
                        R[index + 1][i - 28] = R[index][i + 1 - 28];
                    }
                }
            } // 根据密钥变换规则，第1，2，9，16循环左移1位

            if (index+1 == 3 || index+1 == 4 || index+1 == 5 || index+1 == 6 || index+1 == 7 || index+1 == 8
                    || index+1 == 10 || index+1 == 11 || index+1 == 12 || index+1 == 13 || index+1 == 14 || index+1 == 15) {
                for (int i = 0; i < 28; i++) {
                    if (i == 27) {
                        L[index + 1][i] = L[index][1];
                    } else if (i == 26) {
                        L[index + 1][i] = L[index][0];
                    } else {
                        L[index + 1][i] = L[index][i + 2];
                    }

                }
                for (int i = 28; i < 56; i++) {
                    if (i == 55) {
                        R[index + 1][i - 28] = R[index][1];
                    } else if (i == 54) {
                        R[index + 1][i - 28] = R[index][0];
                    } else {
                        R[index + 1][i - 28] = R[index][i + 2 - 28];
                    }
                }
            } //// 根据密钥变换规则，第3，4，5，6，7，8，10，11，12，13，14，15循环左移2位
            index++;
        } // 循环加密n次后，输出的Ln和Rn

        String Kn = new String();
        String Kn_trans = new String();
        for (int i = 0; i < 28; i++) {
            Kn = Kn + L[index][i];
        }
        for (int i = 0; i < 28; i++) {
            Kn = Kn + R[index][i];
        }
        for (int i = 0; i < PC_2.length; i++) {
            Kn_trans = Kn_trans + Kn.charAt(PC_2[i]-1);
        }
        return Kn_trans;// 得到最终的Kn
    }

    public static String xor(String s1, String s2) {
        int length = s1.length();
        String res = new String();
        for (int index = 0; index < length; index++) {
            if (s1.charAt(index) == s2.charAt(index)) {
                res = res + "0";
            } else {
                res = res + "1";
            }
        }
        return res;
    }

    public static String DES_encryption(String keyword, String plaintext) {
        Init_all();// 提前加载备用数据
        // if (check_keyword(keyword)) {
        int index = 0;
//        keyword_64 = getKeyword_64(keyword);// 得到64位的密钥
//        plaintext_64 = getPlaintext_64(plaintext);// 得到64位的明文
        keyword_64=getString_Hex(keyword);
        plaintext_64=getString_Hex(plaintext);

        String plaintext_trans1 = new String();

        for (int i = 0; i < Init_trans.length; i++) {
            plaintext_trans1 = plaintext_trans1 + plaintext_64.charAt(Init_trans[i]-1);
        } // 得到初始置换的明文
        
        LP_string[index]=new String();
        RP_string[index]=new String();
        for (int i = 0; i < 32; i++) {
            LP[index][i] = (plaintext_trans1.charAt(i) - '0');
            
            LP_string[index] = LP_string[index] + plaintext_trans1.charAt(i);
        } // 得到左半32位的明文部分

        for (int i = 32; i < 64; i++) {
            RP[index][i - 32] = (plaintext_trans1.charAt(i) - '0');
            
            RP_string[index] = RP_string[index] + plaintext_trans1.charAt(i);
        } // 得到右半32位的明文部分

        // index++;

        for (int i = 1; i <= 16; i++) {
            String str_rp_EBox = new String();
            for (int j = 0; j < EBox.length; j++) {
                str_rp_EBox = str_rp_EBox + RP[index][EBox[j]-1];
            } // 扩展置换得到48位的字符串

            String in_SBox = xor(str_rp_EBox, get_Kn(index + 1, keyword_64));// 将上一步得到的字符串和子密钥K(index+1)异或得到SBox的输入值

            String out_SBox = new String();//初始化SBox的输出值
            String str_sbox[] = new String[8];//根据SBox规则，上一步得到的48位字符串被分成8组进入

            for (int j = 0; j < 8; j++) {
                str_sbox[j] = in_SBox.substring(j * 6, (j + 1) * 6);//得到8组子字符串
            }
            for (int j = 0; j < 8; j++) {
                String row = new String();
                String col = new String();

                row = row + str_sbox[j].charAt(0) + str_sbox[j].charAt(5);//行数
                col = col + str_sbox[j].charAt(1) + str_sbox[j].charAt(2) + str_sbox[j].charAt(3)//列数
                        + str_sbox[j].charAt(4);

                int int_row = Integer.parseInt(row, 2);
                int int_col = Integer.parseInt(col, 2);//将二进制转换为10进制

                int len_zero = Integer.toString(SBox[j][int_row][int_col], 2).length();
                if (len_zero != 4) {
                    for (int k = 0; k < 4 - len_zero; k++) {
                        out_SBox = out_SBox + "0";
                    }
                }
                out_SBox = out_SBox + Integer.toString(SBox[j][int_row][int_col], 2);//得到32位SBox输出
            }

            String out_PBox = new String();
            for (int j = 0; j < PBox.length; j++) {
                out_PBox = out_PBox + out_SBox.charAt(PBox[j]-1);
            }//根据PBox移位操作，得到字符串

            String R_indexplus1 = xor(out_PBox, LP_string[index]);//将左半部分明文和PBox输出的字符串异或
            RP_string[index + 1] = R_indexplus1;//将异或得到的结果赋给下一次的右半部分明文

            LP_string[index + 1] = RP_string[index];//将右半部分明文直接赋给下一次的左半部分明文

            for (int j = 0; j < 32; j++) {
                LP[index + 1][j] = (LP_string[index + 1].charAt(j) - '0');
                RP[index + 1][j] = (R_indexplus1.charAt(j) - '0');
            }//初始化下一次的左右矩阵
            index++;
        }

        String ciphertext_pre = RP_string[index] + LP_string[index];//交换顺序后合并左右部分

        String ciphertext = new String();
        for (int i = 0; i < FT.length; i++) {
            ciphertext = ciphertext + ciphertext_pre.charAt(FT[i]-1);
        }

        return ciphertext;//经过移位操作后得到密文信息
    }


    public static String DES_decryption(String keyword, String ciphertext) {
        Init_all();// 提前加载备用数据
        // if (check_keyword(keyword)) {
        int index = 0;
//        keyword_64 = getKeyword_64(keyword);// 得到64位的密钥
//        plaintext_64 = getPlaintext_64(plaintext);// 得到64位的明文
        keyword_64=getString_Hex(keyword);
        ciphertext_64=getString_Hex(ciphertext);

        String ciphertext_trans1 = new String();

        for (int i = 0; i < Init_trans.length; i++) {
            ciphertext_trans1 = ciphertext_trans1 + ciphertext_64.charAt(Init_trans[i]-1);
        } // 得到初始置换的明文
        
        LP_string[index]=new String();
        RP_string[index]=new String();
        for (int i = 0; i < 32; i++) {
            LP[index][i] = (ciphertext_trans1.charAt(i) - '0');
            
            LP_string[index] = LP_string[index] + ciphertext_trans1.charAt(i);
        } // 得到左半32位的密文部分

        for (int i = 32; i < 64; i++) {
            RP[index][i - 32] = (ciphertext_trans1.charAt(i) - '0');
            
            RP_string[index] = RP_string[index] + ciphertext_trans1.charAt(i);
        } // 得到右半32位的密文部分

        // index++;

        for (int i = 1; i <= 16; i++) {
            String str_rp_EBox = new String();
            for (int j = 0; j < EBox.length; j++) {
                str_rp_EBox = str_rp_EBox + RP[index][EBox[j]-1];
            } // 扩展置换得到48位的字符串

            String in_SBox = xor(str_rp_EBox, get_Kn(16-index, keyword_64));// 将上一步得到的字符串和子密钥K(index+1)异或得到SBox的输入值

            String out_SBox = new String();//初始化SBox的输出值
            String str_sbox[] = new String[8];//根据SBox规则，上一步得到的48位字符串被分成8组进入

            for (int j = 0; j < 8; j++) {
                str_sbox[j] = in_SBox.substring(j * 6, (j + 1) * 6);//得到8组子字符串
            }
            for (int j = 0; j < 8; j++) {
                String row = new String();
                String col = new String();

                row = row + str_sbox[j].charAt(0) + str_sbox[j].charAt(5);//行数
                col = col + str_sbox[j].charAt(1) + str_sbox[j].charAt(2) + str_sbox[j].charAt(3)//列数
                        + str_sbox[j].charAt(4);

                int int_row = Integer.parseInt(row, 2);
                int int_col = Integer.parseInt(col, 2);//将二进制转换为10进制

                int len_zero = Integer.toString(SBox[j][int_row][int_col], 2).length();
                if (len_zero != 4) {
                    for (int k = 0; k < 4 - len_zero; k++) {
                        out_SBox = out_SBox + "0";
                    }
                }
                out_SBox = out_SBox + Integer.toString(SBox[j][int_row][int_col], 2);//得到32位SBox输出
            }

            String out_PBox = new String();
            for (int j = 0; j < PBox.length; j++) {
                out_PBox = out_PBox + out_SBox.charAt(PBox[j]-1);
            }//根据PBox移位操作，得到字符串

            String R_indexplus1 = xor(out_PBox, LP_string[index]);//将左半部分明文和PBox输出的字符串异或
            RP_string[index + 1] = R_indexplus1;//将异或得到的结果赋给下一次的右半部分密文

            LP_string[index + 1] = RP_string[index];//将右半部分明文直接赋给下一次的左半部分密文

            for (int j = 0; j < 32; j++) {
                LP[index + 1][j] = (LP_string[index + 1].charAt(j) - '0');
                RP[index + 1][j] = (R_indexplus1.charAt(j) - '0');
            }//初始化下一次的左右矩阵
            index++;
        }

        String plaintext_pre = RP_string[index] + LP_string[index];//交换顺序后合并左右部分

        String plaintext = new String();
        for (int i = 0; i < FT.length; i++) {
            plaintext = plaintext + plaintext_pre.charAt(FT[i]-1);
        }

        return plaintext;//经过移位操作后得到明文信息
    }





    public static boolean check_keyword(String keyword) {
        boolean sign = true;
        for (int index = 0; index < keyword.length(); index++) {
            if (!((keyword.charAt(index) > 'a' && keyword.charAt(index) < 'z')
                    || (keyword.charAt(index) > 'A' && keyword.charAt(index) < 'Z'))) {
                sign = false;
            }
        }
        return sign;

    }

    public static String readUsingBufferedReader(String infilename) throws IOException {
        // 读取文件函数，输入为文件的相对路径和文件名
        File file = new File(infilename);
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        String text = new String();
        String line = new String();
        System.out.println("系统正在读取文件......");
        while ((line = br.readLine()) != null) {
            text += line;
        }
        br.close();
        fr.close();
        return text;// 返回从文件读取到的字符串
    }

    public static void writeCiphertext(String outfilename, String ciphertext) {
        // 写文件函数，输入为需要写入的字符串内容和文件的相对路径和文件名
        File writefile;
        BufferedWriter bw;
        boolean append = true;

        writefile = new File(outfilename);
        if (writefile.exists() == false) {
            try {
                writefile.createNewFile();
                writefile = new File(outfilename);
            } catch (IOException e) {
                // TODO: handle exception
                e.printStackTrace();
            }

        } else {
            writefile.delete();
            try {
                writefile.createNewFile();
                writefile = new File(outfilename);
            } catch (IOException e) {
                // TODO: handle exception
                e.printStackTrace();
            }
        }
        try {
            FileWriter fw = new FileWriter(writefile, append);
            bw = new BufferedWriter(fw);
            fw.write(ciphertext);
            fw.flush();
            fw.close();
        } catch (IOException e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }



    public static void main(String[] args) throws Exception {
        Scanner input=new Scanner(System.in);
        System.out.println("欢迎来到DES密码加解密系统!");
        System.out.println("1.字符串加密");
        System.out.println("2.字符串解密");
        System.out.println("3.文件加密");
        System.out.println("4.文件解密");
        System.out.println("5.退出系统");
        String plaintext;
        String keyword;
        String ciphertext;
        String infilename;
        String outfilename;
        int choice;
        while((choice=Integer.parseInt(input.nextLine()))!=5) {
        	switch(choice){
            case 1:
                System.out.println("你选择的是字符串加密选项");
                System.out.println("请输入明文：");
                plaintext=input.nextLine();
                System.out.println("请输入加密密钥：");
                keyword=input.nextLine();
                System.out.println("加密结果为(二进制):");
                ciphertext=DES_encryption(keyword, plaintext);
                System.out.println(ciphertext);
                System.out.println("加密结果为(十六进制):");
                System.out.println(getString_two(ciphertext));
                break;
            case 2:
                System.out.println("你选择的是字符串解密选项");
                System.out.println("请输入密文：");
                ciphertext=input.nextLine();
                System.out.println("请输入加密密钥：");
                keyword=input.nextLine();
                System.out.println("解密结果为(二进制):");
                plaintext=DES_decryption(keyword, ciphertext);
                System.out.println(plaintext);
                System.out.println("解密结果为(十六进制):");
                System.out.println(getString_two(plaintext));
                break;
            case 3:
                System.out.println("你选择的是文件加密选项");
                System.out.println("请输入需要加密文件的文件名");
                infilename=input.nextLine();
                System.out.println("请输入加密密钥：");
                keyword=input.nextLine();
                System.out.println("系统正在读取明文.......");
                plaintext=readUsingBufferedReader(infilename);
                //System.out.println("加密结果为：");
                ciphertext=DES_encryption(keyword, plaintext);
                System.out.println("请输入需要输出的加密文件的文件名");
                outfilename=input.nextLine();
                writeCiphertext(outfilename, ciphertext);
                System.out.println("系统正在写密文.......");
                //System.out.println(plaintext);
                break;
            case 4:
                System.out.println("你选择的是文件解密选项");
                System.out.println("请输入需要解密文件的文件名");
                infilename=input.nextLine();
                System.out.println("请输入加密密钥：");
                keyword=input.nextLine();
                System.out.println("系统正在读取密文.......");
                ciphertext=readUsingBufferedReader(infilename);
                //System.out.println("加密结果为：");
                plaintext=DES_decryption(keyword, getString_two(ciphertext));
                System.out.println("请输入需要输出的解密文件的文件名");
                outfilename=input.nextLine();
                writeCiphertext(outfilename, getString_two(plaintext));
                System.out.println("系统正在写明文.......");
                //System.out.println(plaintext);
                break;
            case 5:
                System.out.println("欢迎你的下次使用！");
                break;
        }

        }
        input.close();
    }
}
//         Scanner input=new Scanner(System.in);
// //        Init_all();// 提前加载备用数据
// //        String keyword=input.nextLine();
// //        keyword_64=getString_Hex(keyword);
// //        String[] subkey=new String[16];
// //        for(int i=1;i<=16;i++) {
// //        	subkey[i-1]=get_Kn(i, keyword_64);
// //        }
// //        
        
//         // String[] s=new String[4];
//         // s[0]=new String();
//         // //  String keyword=input.nextLine();
//         // //  String plaintext=input.nextLine();
//         // //  System.out.println(DES_encryption(keyword, plaintext));
//         // // String ciphertext=input.nextLine();
//         // // System.out.println(toBinary(ciphertext));


//         // String keyword=input.nextLine();
//         //  String ciphertext=input.nextLine();
//         //  System.out.println(DES_decryption(keyword,ciphertext));
        
//         // String k = "abcd";
//         // char ch = 'a';
//         // System.out.println((int) ch);
//         // System.out.println(Integer.toString((int) ch, 2));
//         // System.out.println(toBinary(k));
//         // System.out.println(getKeyword_56(k));
//         // System.out.println(getKeyword_64(k));
//         // System.out.println(toBinary(k));
//         // System.out.println(getPlaintext_64(k));
//         // System.out.println(k + 1);
//         // String s = "1101";
//         // String ss = "6";
//         // System.out.println(Integer.parseInt(s, 2));
//         // System.out.println(Integer.toString(Integer.parseInt(ss), 2));

