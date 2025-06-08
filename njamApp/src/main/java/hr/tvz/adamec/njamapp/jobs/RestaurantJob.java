package hr.tvz.adamec.njamapp.jobs;

import hr.tvz.adamec.njamapp.services.RestaurantService;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RestaurantJob implements Job {
    @Autowired
    private RestaurantService restaurantService;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println("Open restaurants: ");
        restaurantService.findAll().forEach(restaurant -> {
            if(restaurant.getCurrentlyOpen()) {
                System.out.println(restaurant.getName() + " is open. Location:" + restaurant.getAddress());
            }
        });
    }
}
