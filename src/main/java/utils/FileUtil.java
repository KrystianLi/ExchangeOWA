package utils;


import java.io.*;

public class FileUtil {

    public static void writeFile(String content,String path) throws IOException {
        String dir = path;
        File file = new File(dir);
        //如果文件不存在，创建文件
        if (!file.exists())
            file.createNewFile();
        //创建FileWriter对象
        FileWriter writer = new FileWriter(file);
        //向文件中写入内容
        writer.write(content);
        writer.flush();
        writer.close();
    }

    public static String readFile(String path) throws IOException {
        String filePath = path;
        FileInputStream fin = new FileInputStream(filePath);
        InputStreamReader reader = new InputStreamReader(fin);
        BufferedReader buffReader = new BufferedReader(reader);
        StringBuilder stringBuilder = new StringBuilder();
        String strTmp = "";
        while((strTmp = buffReader.readLine())!=null){
//            System.out.println(strTmp);
            stringBuilder.append(strTmp);
        }
        String s = stringBuilder.toString();
        buffReader.close();
        return s;

    }
}
