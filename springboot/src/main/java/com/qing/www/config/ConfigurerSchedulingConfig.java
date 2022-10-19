package com.qing.www.config;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.qing.www.component.ScheduleTaskList;
import com.qing.www.service.IExamManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Date;



@Configuration
@EnableScheduling
public class ConfigurerSchedulingConfig implements SchedulingConfigurer {

    @Resource
    private ScheduleTaskList scheduleTaskList;

    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        taskRegistrar.addTriggerTask(
            //1.添加任务内容(Runnable)
            ()->{
                System.out.println( "["+DateUtil.date()+"]执行动态定时任务:");
                scheduleTaskList.checkExamManage();
            }
            //2.设置执行周期(Trigger)
            ,triggerContext -> {
                //获取执行周期
                String cron = "0 0/1 * * * ?";
                //返回执行周期(Date)
                return new CronTrigger(cron).nextExecutionTime(triggerContext);
            }
        );
    }
}
