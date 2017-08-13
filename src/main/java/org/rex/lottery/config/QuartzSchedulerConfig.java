package org.rex.lottery.config;

import org.quartz.Trigger;
import org.rex.lottery.job.AutowiringSpringBeanJobFactory;
import org.rex.lottery.job.ExtractSSQInfoDetailEveryPeriodJob;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

/**
 * Created by RexSong on 2017/8/12.
 */
@Configuration
public class QuartzSchedulerConfig {

    @Autowired
    private ApplicationContext applicationContext;

    @Bean(name = "schedulerFactoryBean")
    public SchedulerFactoryBean schedulerFactoryBean() {
        SchedulerFactoryBean schedulerFactoryBean = new SchedulerFactoryBean();
        AutowiringSpringBeanJobFactory jobFactory = new AutowiringSpringBeanJobFactory();
        jobFactory.setApplicationContext(applicationContext);
        schedulerFactoryBean.setJobFactory(jobFactory);

        Trigger[] triggers = {ssqInfoDetailEveryPeriodJobTrigger().getObject()};
        schedulerFactoryBean.setTriggers(triggers);
        return schedulerFactoryBean;
    }

    @Bean(name = "ssqInfoDetailEveryPeriodJobDetail")
    public JobDetailFactoryBean ssqInfoDetailEveryPeriodJobDetail() {
        JobDetailFactoryBean jobDetailFactory = new JobDetailFactoryBean();
        jobDetailFactory.setJobClass(ExtractSSQInfoDetailEveryPeriodJob.class);
        jobDetailFactory.setGroup("ssqJobs");
        return jobDetailFactory;
    }

    @Bean
    public CronTriggerFactoryBean ssqInfoDetailEveryPeriodJobTrigger() {
        CronTriggerFactoryBean cronTriggerFactoryBean = new CronTriggerFactoryBean();
        cronTriggerFactoryBean.setJobDetail(ssqInfoDetailEveryPeriodJobDetail().getObject());
//        cronTriggerFactoryBean.setCronExpression("0/20 * * ? * MON,WED,FRI,SUN");
        cronTriggerFactoryBean.setCronExpression("0 30 0/2 ? * MON,WED,FRI");
        cronTriggerFactoryBean.setGroup("ssqTriggers");
        return cronTriggerFactoryBean;
    }
}
