package fundingchain.schedulers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@EnableScheduling
public class ProjectsCompletion {

    private static final int finish_projects_frequency = 300000;

    @Scheduled(fixedRate = finish_projects_frequency)
    public void finishProjects(){
        Date now = new Date();
        System.out.println(now.toString());
    }
}
