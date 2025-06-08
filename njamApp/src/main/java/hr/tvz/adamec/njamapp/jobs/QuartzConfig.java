package hr.tvz.adamec.njamapp.jobs;

import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QuartzConfig {

    @Bean
    public JobDetail restaurantJobDetail() {
        return JobBuilder.newJob(RestaurantJob.class)
                .withIdentity("restaurantJob")
                .storeDurably()
                .build();
    }

    @Bean
    public Trigger restaurantJobTrigger() {
        SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder
                .simpleSchedule()
                .withIntervalInSeconds(10)
                .repeatForever();

        return TriggerBuilder.newTrigger()
                .forJob(restaurantJobDetail())
                .withIdentity("restaurantTrigger")
                .withSchedule(scheduleBuilder)
                .build();
    }
}

