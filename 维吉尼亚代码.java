import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class App {

    private static HashMap<Character,Integer> alphabetForm;
    private static ArrayList<Character> alphabets;
    // private Scanner input=new Scanner(System.in);
    
    public static void Init_maps(){
        alphabetForm=new HashMap<>();
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
    }//提前加载好一个哈希表，里面存放了26个大小写字母及ASCII码值

    public static void Init_lists(){
        alphabets=new ArrayList<>();
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

    }//提前加载好一个动态数组，里面存放了26个大小写字母
    
    public static String Vigenere_Decryption(String keyword,String ciphertext){
        Init_maps();
        Init_lists();//初始化提前存好的哈希表和动态数组

        String plaintext=new String();
        int keyword_length=keyword.length();
        int ciphertext_length=ciphertext.length();//获取一些必要数据，如密钥长度和密文长度

        String key=new String();
        int key_length=0;
        int index=0;
        while(key_length<ciphertext_length){
            key=key+keyword.charAt(index);
            index=(index+1)%keyword_length;
            key_length++;
        }//根据维吉尼亚密码原理，提前构造好一个与密文等长的key

        for(int i=0;i<ciphertext_length;i++){
            char char_key=key.charAt(i);
            char char_ciphertext=ciphertext.charAt(i);
            int value_key=alphabetForm.get(char_key);
            int value_ciphertext=alphabetForm.get(char_ciphertext);//获取密文和key一一对应的字母极其ASCII码值

            if(value_key>=97&&value_key<=122&&value_ciphertext>=65&&value_ciphertext<=90){
                value_key=value_key-32;
            }
            if(value_key>=65&&value_key<=90&&value_ciphertext>=97&&value_ciphertext<=122){
                value_key=value_key+32;
            }//判断一一对应的字母大小写是否相同，如果不相同则根据密文的大小写状态变更key的大小写

            int targetvalue=0;
            if(value_key>=97&&value_key<=122&&value_ciphertext>=97&&value_ciphertext<=122){
                targetvalue=value_ciphertext-value_key;
                if(targetvalue<0){
                    targetvalue=targetvalue+26+97;
                }else{
                    targetvalue=targetvalue+97;
                }
            }

            if(value_key>=65&&value_key<=90&&value_ciphertext>=65&&value_ciphertext<=90){
                targetvalue=value_ciphertext-value_key;
                if(targetvalue<0){
                    targetvalue=targetvalue+26+65;
                }else{
                    targetvalue=targetvalue+65;
                }
            }//根据维吉尼亚密码原理中的table，进行数学运算得到对应的明文字母的ASCII码值

            for(index=0;index<alphabets.size();index++){
                if(alphabetForm.get(alphabets.get(index))==targetvalue){
                    plaintext=plaintext+alphabets.get(index);
                }
            }//遍历字母表，得到对应ASCII码值的字母，并将其加入到字符串plaintext中

        }
        return plaintext;//返回明文字符串


    }
    public static String Vigenere_Encryption(String keyword,String plaintext){
        Init_maps();
        Init_lists();//初始化提前存好的哈希表和动态数组

        String ciphertext=new String();
        int ciphertext_length=0;
        int plaintext_length=plaintext.length();
        int keyword_length=keyword.length();//获取一些必要数据，如密钥长度和明文长度

        String key="";
        int key_length=0;
        int index=0;
        while(key_length<plaintext_length){
            key=key+keyword.charAt(index);
            index=(index+1)%keyword_length;
            key_length++;
        }//根据维吉尼亚密码原理，提前构造好一个与明文等长的字符串key

        for(int i=0;i<plaintext_length;i++){
            char char_key=key.charAt(i);
            char char_plaintext=plaintext.charAt(i);
            int value_key=alphabetForm.get(char_key);
            int value_plaintext=alphabetForm.get(char_plaintext);//获取明文和key一一对应的字母极其ASCII码值

            if(value_key>=97&&value_key<=122&&value_plaintext>=65&&value_plaintext<=90){
                value_key=value_key-32;
            }
            if(value_key>=65&&value_key<=90&&value_plaintext>=97&&value_plaintext<=122){
                value_key=value_key+32;
            }//判断一一对应的字母大小写是否相同，如果不相同则根据明文的大小写状态变更key的大小写

            int targetvalue=0;
            if(value_key>=97&&value_key<=122&&value_plaintext>=97&&value_plaintext<=122){
                targetvalue=(value_key+value_plaintext-1-96-96)%26+96;
            }
            if(value_key>=65&&value_key<=90&&value_plaintext>=65&&value_plaintext<=90){
                targetvalue=(value_key+value_plaintext-1-64-64)%26+64;
            }//根据维吉尼亚密码原理中的table，进行数学运算得到对应的密文字母的ASCII码值

            
            for(index=0;index<alphabets.size();index++){
                if(alphabetForm.get(alphabets.get(index))==targetvalue){
                    ciphertext=ciphertext+alphabets.get(index);
                }
            }//遍历字母表，得到对应ASCII码值的字母，并将其加入到字符串ciphertext中
        }
        return ciphertext;//返回密文字符串
    }

    public static String readUsingBufferedReader(String infilename) throws IOException{
        //读取文件函数，输入为文件的相对路径和文件名
        File file=new File(infilename);
        FileReader fr=new FileReader(file);
        BufferedReader br=new BufferedReader(fr);
        String text=new String();
        String line=new String();
        System.out.println("系统正在读取文件......");
        while((line=br.readLine())!=null){
            text+=line;
        }
        br.close();
        fr.close();
        return text;//返回从文件读取到的字符串
    }

    public static void writeCiphertext(String outfilename,String ciphertext){
        //写文件函数，输入为需要写入的字符串内容和文件的相对路径和文件名
        File writefile;
        BufferedWriter bw;
        boolean append=true;

        writefile=new File(outfilename);
        if(writefile.exists()==false){
            try {
                writefile.createNewFile();
                writefile=new File(outfilename);
            } catch (IOException e) {
                //TODO: handle exception
                e.printStackTrace();
            }
            
        }else{
            writefile.delete();
            try {
                writefile.createNewFile();
                writefile=new File(outfilename);
            } catch (IOException e) {
                //TODO: handle exception
                e.printStackTrace();
            }
        }
        try {
            FileWriter fw=new FileWriter(writefile,append);
            bw=new BufferedWriter(fw);
            fw.write(ciphertext);
            fw.flush();
            fw.close();
        } catch (IOException e) {
            //TODO: handle exception
            e.printStackTrace();
        }
    }

    // public static void writeText(String outfilename,String text){
    //     FileWriter writer;
    //     try {
    //         writer = new FileWriter(outfilename, true);
    //         writer.write(text);
    //         writer.flush();
    //         writer.close();
    //     } catch (IOException e) {
    //         e.printStackTrace();
    //     }

    // }

    public static void main(String[] args) throws Exception {
        Scanner input=new Scanner(System.in);
        System.out.println("欢迎来到维吉尼亚密码加解密系统！");
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
                System.out.println("加密结果为：");
                ciphertext=Vigenere_Encryption(keyword, plaintext);
                System.out.println(ciphertext);
                break;
            case 2:
                System.out.println("你选择的是字符串解密选项");
                System.out.println("请输入密文：");
                ciphertext=input.nextLine();
                System.out.println("请输入加密密钥：");
                keyword=input.nextLine();
                System.out.println("解密结果为：");
                plaintext=Vigenere_Decryption(keyword, ciphertext);
                System.out.println(plaintext);
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
                ciphertext=Vigenere_Encryption(keyword, plaintext);
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
                plaintext=Vigenere_Decryption(keyword, ciphertext);
                System.out.println("请输入需要输出的解密文件的文件名");
                outfilename=input.nextLine();
                writeCiphertext(outfilename, plaintext);
                System.out.println("系统正在写明文.......");
                //System.out.println(plaintext);
                break;
            case 5:
                System.out.println("欢迎你的下次使用！");
                break;
        }

        }
        input.close();
        
        // String keyword="hold";
        // String plaintext="thisistheplaintext";
        // System.out.println(Vigenere_Encryption(keyword, plaintext));
        // String ciphertext=Vigenere_Encryption(keyword, plaintext);
        // System.out.println(Vigenere_Decryption(keyword, ciphertext));

        // char [] a=new char[2];
        // System.out.println(a[0]=='\0');
        // String res=new String();
        //System.out.println((-28)%26);
        
    }
}
