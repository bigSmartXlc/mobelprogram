package com.hbtcsrzzx.utils;


import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@SuppressWarnings("all")
public class ExportSQLUtils {

    //获取配置文件属性
    public  static Map<String, String> proRead(String proName) {

        Properties pro = new Properties();
        Map<String, String> mpro = new HashMap<String, String>();
        InputStream isr = null;
        try {

            isr = ExportSQLUtils.class.getClassLoader().getResourceAsStream(proName);
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

    public static void exportSql(Map<String, String> map) throws ParseException {
        String user =map.get("jdbc.username");
        String password = map.get("jdbc.password");
        String host =map.get("jdbc.port");
        String exportDatabaseName = map.get("jdbc.exportDatabaseName");

        String exportPath = map.get("jdbc.exportPath");
        String newDateStr = DateUtils.getDateConvertString(new Date(), "yyyy-MM-dd")+".sql";
        String prot = map.get("jdbc.port");
        exportPath+=newDateStr;
//使用拼接的方式来完成dos命令
        //mysqldump -uroot -p123456 binweb> D:\Test\exsql\db02.sql
        String command = new String("cmd /c "+map.get("jdbc.exportPath") +"mysqldump -P"+prot+" -u" + user + " -p" + password + " " + exportDatabaseName + ">" + exportPath);
//执行命令行
        Runtime runtime = Runtime.getRuntime();
        try {
//cmd /k在执行命令后不关掉命令行窗口  cmd /c在执行完命令行后关掉命令行窗口   \\表示转译符也可使用/替代，linux使用/
            Process process = runtime.exec(command);
//D:\\Test\\exsql\\mysqldump -uroot -p123456 testbinweb>D:\\Test\\exsql\\sql01.sql

        } catch (IOException e) {
// TODO Auto-generated catch block
            e.printStackTrace();
        }


    }

}
