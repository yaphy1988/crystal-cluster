package com.ai.mine.crystal.config;

import com.ai.mine.crystal.jobs.CaseInformationJob;
import com.ai.mine.crystal.jobs.DownloadVideoJob;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QuartzJobAutoConfig {

    // 案件信息获取时间间隔
    @Value("${application.quartz.caseinfo.interval:3600}")
    private Integer caseInfoInterval;

    // 视频下载检查时间间隔
    @Value("${application.quartz.video.interval:7200}")
    private Integer videoInterval;

    @Bean
    public JobDetail caseInformationJobDetail() {
        return JobBuilder.newJob(CaseInformationJob.class).withIdentity("caseInformationJob").usingJobData("name", "World").storeDurably()
                .build();
    }

    @Bean
    public Trigger caseInformationJobTrigger() {
        SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(caseInfoInterval)
                .repeatForever();

        return TriggerBuilder.newTrigger().forJob(caseInformationJobDetail()).withIdentity("caseInformationTrigger")
                .withSchedule(scheduleBuilder).build();
    }

    @Bean
    public JobDetail downloadVideoJobDetail() {
        return JobBuilder.newJob(DownloadVideoJob.class).withIdentity("downloadVideoJob").usingJobData("name", "World").storeDurably()
                .build();
    }

    @Bean
    public Trigger downloadVideoJobTrigger() {
        SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(caseInfoInterval)
                .repeatForever();

        return TriggerBuilder.newTrigger().forJob(downloadVideoJobDetail()).withIdentity("downloadVideoTrigger")
                .withSchedule(scheduleBuilder).build();
    }


}
