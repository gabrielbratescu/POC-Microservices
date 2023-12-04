package com.example.service2.infrastructure.quartz;

import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@Configuration
public class QuartzService {

    @Bean
    @Primary
    public Scheduler Scheduler(Scheduler quartzScheduler){
        try {
            JobDetail jobDetail = buildJobDetails();
            Trigger trigger = buildTrigger(jobDetail);

            if (! quartzScheduler.checkExists(trigger.getKey()) && ! quartzScheduler.checkExists(jobDetail.getKey())) {
                quartzScheduler.scheduleJob(jobDetail,trigger);
            }

            quartzScheduler.start();
        } catch (SchedulerException e) {
            throw new RuntimeException(e);
        }
        return quartzScheduler;
    }

    public JobDetail buildJobDetails(){

        return  JobBuilder.newJob()
                .withIdentity("coin_base_job","coinbase-jobs")
                .withDescription("Coinbase Job Description")
                .storeDurably(true)
                .ofType(SampleJob.class)
                .build();
    }

    public Trigger buildTrigger(JobDetail jobDetail){
        ZonedDateTime zonedDateTime = ZonedDateTime.of(LocalDate.parse("2023-04-01"),
                LocalTime.of(8,30), ZoneId.of("Asia/Kolkata"));

        return TriggerBuilder.newTrigger()
                .forJob(jobDetail)
                .withIdentity(jobDetail.getKey().getName(),"records-tigger")
                .withIdentity("Trigger 4 coinbase job")
                .withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInMinutes(1).repeatForever())
                .build();

    }

}
