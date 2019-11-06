package com.hbtcsrzzx.test;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import sun.nio.fs.WindowsFileSystemProvider;

import javax.xml.crypto.Data;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.DosFileAttributes;
import java.nio.file.attribute.FileTime;
import java.text.SimpleDateFormat;
import java.util.*;

@Component
@PropertySource("classpath:db.properties")
public class BasicFileAttributesTest {


    @Test
    public void testBasicFileAttributes() throws IOException {
        Path path = Paths.get("D:\\下载\\图片素材\\92c3dfc48516ea39bfb2c7ab410bbb31.jpg");
        DosFileAttributes bfa = Files.readAttributes(path, DosFileAttributes.class);
        System.out.println(bfa.lastAccessTime().toString());
        System.out.println(bfa.lastModifiedTime());
        System.out.println(bfa.size());

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        long millis = bfa.lastAccessTime().toMillis();


        System.out.println(millis);
        Date date = new Date(millis);
        String format = sdf.format(date);
        System.out.println(format);
    }

    @Test
    public void testRegex() {

        String regex = "^/user/.*";

        System.out.println("user/284r34u9togdf".matches(regex));
    }

    @Test
    public void testExportSQL() {

        Map<String, String> map = proRead("db.properties");
        System.out.println(map);
        exportSql(map);


    }

    public static void main(String[] args) {
        Map<String, String> map = proRead("db.properties");

        exportSql(map);
    }
    //获取配置文件属性
    public  static  Map<String, String> proRead(String proName) {

        Properties pro = new Properties();
        Map<String, String> mpro = new HashMap<String, String>();
        InputStream isr = null;
        try {

            isr = BasicFileAttributesTest.class.getClassLoader().getResourceAsStream("db.properties");
            pro.load(isr);
            @SuppressWarnings("rawtypes")
            Enumeration en = pro.propertyNames();
            while (en.hasMoreElements()) {
                String key = (String) en.nextElement();
                String value = pro.getProperty(key);
                mpro.put(key, value);

            }
            return mpro;
        } catch (Exception e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }


        return null;
    }

    public static void exportSql(Map<String, String> map) {
        String user ="root";
        String password = "123456";
        String host = "3306";
        String exportDatabaseName = "binweb";

        String exportPath = "D:\\Test\\exsql\\db6.sql";

//使用拼接的方式来完成dos命令
        //mysqldump -uroot -p123456 binweb> D:\Test\exsql\db02.sql
        String command = new String("cmd /c D:\\Test\\exsql\\mysqldump -u" + user + " -p" + password + " " + exportDatabaseName + ">" + exportPath);
//执行命令行
        Runtime runtime = Runtime.getRuntime();
        try {
//cmd /k在执行命令后不关掉命令行窗口  cmd /c在执行完命令行后关掉命令行窗口   \\表示转译符也可使用/替代，linux使用/
            Process process = runtime.exec(command);


        } catch (IOException e) {
// TODO Auto-generated catch block
            e.printStackTrace();
        }


    }

}
