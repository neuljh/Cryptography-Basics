import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class App {
    private static HashMap<Character, Integer> alphabetForm;
    private static ArrayList<Character> alphabets;

    public static void Init_maps() {
        alphabetForm = new HashMap<>();
        alphabetForm.put('a', 97);
        alphabetForm.put('b', 98);
        alphabetForm.put('c', 99);
        alphabetForm.put('d', 100);
        alphabetForm.put('e', 101);
        alphabetForm.put('f', 102);
        alphabetForm.put('g', 103);
        alphabetForm.put('h', 104);
        alphabetForm.put('i', 105);
        alphabetForm.put('j', 106);
        alphabetForm.put('k', 107);
        alphabetForm.put('l', 108);
        alphabetForm.put('m', 109);
        alphabetForm.put('n', 110);
        alphabetForm.put('o', 111);
        alphabetForm.put('p', 112);
        alphabetForm.put('q', 113);
        alphabetForm.put('r', 114);
        alphabetForm.put('s', 115);
        alphabetForm.put('t', 116);
        alphabetForm.put('u', 117);
        alphabetForm.put('v', 118);
        alphabetForm.put('w', 119);
        alphabetForm.put('x', 120);
        alphabetForm.put('y', 121);
        alphabetForm.put('z', 122);
        alphabetForm.put('A', 65);
        alphabetForm.put('B', 66);
        alphabetForm.put('C', 67);
        alphabetForm.put('D', 68);
        alphabetForm.put('E', 69);
        alphabetForm.put('F', 70);
        alphabetForm.put('G', 71);
        alphabetForm.put('H', 72);
        alphabetForm.put('I', 73);
        alphabetForm.put('J', 74);
        alphabetForm.put('K', 75);
        alphabetForm.put('L', 76);
        alphabetForm.put('M', 77);
        alphabetForm.put('N', 78);
        alphabetForm.put('O', 79);
        alphabetForm.put('P', 80);
        alphabetForm.put('Q', 81);
        alphabetForm.put('R', 82);
        alphabetForm.put('S', 83);
        alphabetForm.put('T', 84);
        alphabetForm.put('U', 85);
        alphabetForm.put('V', 86);
        alphabetForm.put('W', 87);
        alphabetForm.put('X', 88);
        alphabetForm.put('Y', 89);
        alphabetForm.put('Z', 90);
    }//// 提前加载好一个哈希表，里面存放了26个大小写字母及ASCII码值

    public static void Init_lists() {
        alphabets = new ArrayList<>();
        alphabets.add('a');
        alphabets.add('b');
        alphabets.add('c');
        alphabets.add('d');
        alphabets.add('e');
        alphabets.add('f');
        alphabets.add('g');
        alphabets.add('h');
        alphabets.add('i');
        alphabets.add('j');
        alphabets.add('k');
        alphabets.add('l');
        alphabets.add('m');
        alphabets.add('n');
        alphabets.add('o');
        alphabets.add('p');
        alphabets.add('q');
        alphabets.add('r');
        alphabets.add('s');
        alphabets.add('t');
        alphabets.add('u');
        alphabets.add('v');
        alphabets.add('w');
        alphabets.add('x');
        alphabets.add('y');
        alphabets.add('z');

        alphabets.add('A');
        alphabets.add('B');
        alphabets.add('C');
        alphabets.add('D');
        alphabets.add('E');
        alphabets.add('F');
        alphabets.add('G');
        alphabets.add('H');
        alphabets.add('I');
        alphabets.add('J');
        alphabets.add('K');
        alphabets.add('L');
        alphabets.add('M');
        alphabets.add('N');
        alphabets.add('O');
        alphabets.add('P');
        alphabets.add('Q');
        alphabets.add('R');
        alphabets.add('S');
        alphabets.add('T');
        alphabets.add('U');
        alphabets.add('V');
        alphabets.add('W');
        alphabets.add('X');
        alphabets.add('Y');
        alphabets.add('Z');

    }//// 提前加载好一个动态数组，里面存放了26个大小写字母

    public static String Column_permutation_Decryption(String keyword, String ciphertext) {
        Init_maps();
        Init_lists();// 初始化提前存好的哈希表和动态数组

        String plaintext = new String();
        int keyword_length = keyword.length();
        int ciphertext_length = ciphertext.length();// 得到密钥和密文的长度

        HashMap<Character, Integer> maps1 = new HashMap<>();
        HashMap<Character, Integer> maps2 = new HashMap<>();// 创建若干哈希表备用
        char[] chars_keyword = keyword.toCharArray();

        for (int i = 0; i < chars_keyword.length; i++) {
            if (chars_keyword[i] >= 'A' && chars_keyword[i] <= 'Z') {
                int value = alphabetForm.get(chars_keyword[i]) + 32;
                for (int j = 0; j < alphabets.size(); j++) {
                    if (alphabetForm.get(alphabets.get(j)) == value) {
                        chars_keyword[i] = alphabets.get(j);
                    }
                }
            }
        } // 将密钥中的单词全部转换为小写

        for (int i = 0; i < chars_keyword.length; i++) {
            maps1.put(chars_keyword[i], alphabetForm.get(chars_keyword[i]));
        } // 哈希表分别存入密钥对应的字母和ASCII码值

        int order = 0;
        for (int k = 0; k < alphabets.size(); k++) {
            if (maps1.containsKey(alphabets.get(k))) {
                maps1.put(alphabets.get(k), order);
                order++;
            }
        } // 新的哈希表存入密钥中字母根据ASCII码值的大小的出现顺序

        int[] order_keyword = new int[keyword_length];
        for (int i = 0; i < chars_keyword.length; i++) {
            order_keyword[i] = maps1.get(chars_keyword[i]);
        } // 创建一个数组，获取密钥中的字母的顺序

        int rows = keyword_length;
        int columns = (int) Math.ceil((double) (ciphertext_length) / keyword_length);// 创建二维数组

        char[][] res = new char[rows][columns];
        int sign = 0;
        while (sign < ciphertext_length) {
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < columns; j++) {
                    int rows_i = order_keyword[i];
                    res[rows_i][j] = ciphertext.charAt(sign);
                    sign++;
                }
            }
        } // 将密文写入二维数组中

        for (int j = 0; j < columns; j++) {
            for (int i = 0; i < rows; i++) {
                plaintext = plaintext + res[i][j];
            }
        }
        return plaintext;// 按照密钥字母顺序取出对应字母，得到明文

    }

    public static String Column_permutation_Encryption(String keyword, String plaintext, char alter) {// 此函数入口不仅需要传入密钥和明文，还需要传入双方约定好的补全字符
        Init_maps();
        Init_lists();// 初始化提前存好的哈希表和动态数组

        int keyword_length = keyword.length();
        int plaintext_length = plaintext.length();// 得到密钥和明文的长度

        HashMap<Character, Integer> maps1 = new HashMap<>();
        HashMap<Integer, Integer> maps2 = new HashMap<>();
        HashMap<Character, Integer> maps3 = new HashMap<>();// 创建若干哈希表备用

        char[] chars_keyword = keyword.toCharArray();
        for (int i = 0; i < chars_keyword.length; i++) {
            if (chars_keyword[i] >= 'A' && chars_keyword[i] <= 'Z') {
                int value = alphabetForm.get(chars_keyword[i]) + 32;
                for (int j = 0; j < alphabets.size(); j++) {
                    if (alphabetForm.get(alphabets.get(j)) == value) {
                        chars_keyword[i] = alphabets.get(j);
                    }
                }
            }
        } // 将密钥中的单词全部转换为小写

        int[] position_char_keyword = new int[keyword_length];
        for (int i = 0; i < keyword_length; i++) {
            position_char_keyword[i] = i;
        }
        for (int i = 0; i < chars_keyword.length; i++) {
            maps2.put(alphabetForm.get(chars_keyword[i]), position_char_keyword[i]);
            maps3.put(chars_keyword[i], alphabetForm.get(chars_keyword[i]));
        } // 哈希表分别存入密钥对应的字母和ASCII码值，ASCII码值和顺序

        int order = 0;
        // for(int i=0;i<keyword_length;i++){
        // for(int k=0;k<alphabets.size();k++){
        // if(maps3.containsKey(alphabets.get(k))){
        // maps1.put(alphabets.get(k), order);
        // order++;
        // }
        // }
        // }
        for (int k = 0; k < alphabets.size(); k++) {
            if (maps3.containsKey(alphabets.get(k))) {
                maps1.put(alphabets.get(k), order);
                order++;
            }
        } // 新的哈希表存入密钥中字母根据ASCII码值的大小的出现顺序

        int[] order_keyword = new int[keyword_length];
        for (int i = 0; i < chars_keyword.length; i++) {
            order_keyword[i] = maps1.get(chars_keyword[i]);
        } // 创建一个数组，获取密钥中的字母的顺序
          // System.out.println( Math.ceil((double)(plaintext_length+5)/keyword_length));

        char[][] res = new char[keyword_length][(int) Math.ceil((double) (plaintext_length) / keyword_length)];
        // 创建一个二维数组，存放明文或者密文
        // char[][] ss=new char[4][];
        // ss[0][0]='a';
        int sign = 0;
        int j = 0;
        while (sign < plaintext_length) {
            for (int i = 0; i < keyword_length; i++) {
                if (sign < plaintext_length) {
                    res[i][j] = plaintext.charAt(sign);
                } else {
                    res[i][j] = alter;
                }
                sign++;
            }
            j++;
        } // 将明文写入二维数组中
          // for(int row=0;row<keyword_length;row++){
          // for(int
          // column=0;column<(int)Math.ceil((double)(plaintext_length)/keyword_length);column++){
          // if(res[row][column]=='\0'){
          // res[row][column]=alter;
          // }
          // }
          // }
        ArrayList<Character> list = new ArrayList<>();
        for (int i = 0; i < keyword_length; i++) {
            int orders = order_keyword[i];
            for (int s = 0; s < res[orders].length; s++) {
                list.add(res[orders][s]);
            }
        } // 按照密钥字母的顺序，从二维数组中取出密文

        String ciphertext = new String();
        for (int i = 0; i < list.size(); i++) {
            ciphertext = ciphertext + list.get(i);
        }
        return ciphertext;// 最终得到密文

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
        // String keyword = "what";
        // String plaintext =
        // "usingaciphertwicemayimprovethestrengthofthecipherefficientlyy";
        // char alter = '!';
        // System.out.println(Column_permutation_Encryption(keyword, plaintext, alter));
        // String ciphertext = Column_permutation_Encryption(keyword, plaintext, alter);
        // System.out.println(Column_permutation_Decryption(keyword, ciphertext));
        Scanner input = new Scanner(System.in);
        System.out.println("欢迎来到列置换密码加解密系统！");
        System.out.println("1.字符串加密");
        System.out.println("2.字符串解密");
        System.out.println("3.文件加密");
        System.out.println("4.文件解密");
        System.out.println("5.退出系统");
        String plaintext;
        String keyword;
        String ciphertext;
        char alter;
        String infilename;
        String outfilename;
        int choice;
        while ((choice = Integer.parseInt(input.nextLine())) != 5) {
            switch (choice) {
                case 1:
                    System.out.println("你选择的是字符串加密选项");
                    System.out.println("请输入明文：");
                    plaintext = input.nextLine();
                    System.out.println("请输入加密密钥：");
                    keyword = input.nextLine();
                    System.out.println("请输入双方约定的补全字符：");
                    alter = input.nextLine().charAt(0);
                    System.out.println("加密结果为：");
                    ciphertext = Column_permutation_Encryption(keyword, plaintext, alter);
                    System.out.println(ciphertext);
                    break;
                case 2:
                    System.out.println("你选择的是字符串解密选项");
                    System.out.println("请输入密文：");
                    ciphertext = input.nextLine();
                    System.out.println("请输入加密密钥：");
                    keyword = input.nextLine();
                    System.out.println("解密结果为：");
                    plaintext = Column_permutation_Decryption(keyword, ciphertext);
                    System.out.println(plaintext);
                    break;
                case 3:
                    System.out.println("你选择的是文件加密选项");
                    System.out.println("请输入需要加密文件的文件名");
                    infilename = input.nextLine();
                    System.out.println("请输入加密密钥：");
                    keyword = input.nextLine();
                    System.out.println("请输入双方约定的补全字符：");
                    alter = input.nextLine().charAt(0);
                    System.out.println("系统正在读取明文.......");
                    plaintext = readUsingBufferedReader(infilename);
                    // System.out.println("加密结果为：");
                    ciphertext = Column_permutation_Encryption(keyword, plaintext, alter);
                    System.out.println("请输入需要输出的加密文件的文件名");
                    outfilename = input.nextLine();
                    writeCiphertext(outfilename, ciphertext);
                    System.out.println("系统正在写密文.......");
                    // System.out.println(plaintext);
                    break;
                case 4:
                    System.out.println("你选择的是文件解密选项");
                    System.out.println("请输入需要解密文件的文件名");
                    infilename = input.nextLine();
                    System.out.println("请输入加密密钥：");
                    keyword = input.nextLine();
                    System.out.println("系统正在读取密文.......");
                    ciphertext = readUsingBufferedReader(infilename);
                    // System.out.println("加密结果为：");
                    plaintext = Column_permutation_Decryption(keyword, ciphertext);
                    System.out.println("请输入需要输出的解密文件的文件名");
                    outfilename = input.nextLine();
                    writeCiphertext(outfilename, plaintext);
                    System.out.println("系统正在写明文.......");
                    // System.out.println(plaintext);
                    break;
                case 5:
                    System.out.println("欢迎你的下次使用！");
                    break;
            }

        }
        input.close();
    }
}
