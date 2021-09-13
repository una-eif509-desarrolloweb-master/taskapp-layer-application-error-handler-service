package cr.una.taskapp.backend.init;

import cr.una.taskapp.backend.dto.PriorityDetails;
import cr.una.taskapp.backend.service.PriorityService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Description
 *
 * @author Maikol Guzman <maikol@guzmanalan.com>
 * @create 9/12/21
 */
@SpringBootTest
public class LoadInitData {
    @Autowired
    private PriorityService priorityService;

    @Test
    void loadPriorityData() {
        PriorityDetails priority1 = new PriorityDetails();
        PriorityDetails priority2 = new PriorityDetails();
        PriorityDetails priority3 = new PriorityDetails();

        priority1.setLabel("High");
        priority2.setLabel("Medium");
        priority3.setLabel("Low");

        priorityService.create(priority1);
        priorityService.create(priority2);
        priorityService.create(priority3);

        assertEquals(3, priorityService.findAll().size());
    }
}
