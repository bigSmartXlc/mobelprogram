package com.hbtcsrzzx.ssm.task;

import com.hbtcsrzzx.utils.ExportSQLUtils;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Map;

@Component
public class ExportSQLTask {


    //cron = "0 0 4 * * ?  "
    @Scheduled(cron = "0 0 3 * * ?   ")
    public void backupSQL() {
        try {
            Map<String, String> map = ExportSQLUtils.proRead("db.properties");
            ExportSQLUtils.exportSql(map);
            System.out.println("备份数据库sql成功");
        } catch (ParseException e) {
            e.printStackTrace();
            System.out.println("备份数据库sql失败功");
        }
    }
}
