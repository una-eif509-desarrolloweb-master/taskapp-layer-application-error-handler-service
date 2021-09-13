package cr.una.taskapp.backend.dao;


import cr.una.taskapp.backend.model.Priority;
import cr.una.taskapp.backend.repository.PriorityRepository;
import javassist.NotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Unit Test Class
 * Priority DAO
 */
@Transactional
@SpringBootTest
@Sql({
        "/import-priorities.sql"
})
// TODO: This code is to persist the information in the database
// @Sql(scripts = {"/import-priorities.sql"},
//        config = @SqlConfig(encoding = "utf-8", transactionMode = SqlConfig.TransactionMode.ISOLATED))
public class PriorityRepositoryTest {

    @Autowired
    private PriorityRepository priorityRepository;

    @Test
    public void testFindById() {
        Priority priority = priorityRepository.findById(2L).get();

        assertEquals(priority.getLabel(), "Medium");
    }

    @Test
    public void testFindAll() {
        List<Priority> priorityList = priorityRepository.findAll();

        assertTrue(priorityList.size() == 3);
    }

    @Test
    public void testCreate() {
        Priority priority = new Priority();

        priority.setLabel("Trivial");

        Priority priorityCreated = priorityRepository.save(priority);

        assertEquals(priorityCreated.getIdPriority(), 1L);
    }

    @Test
    public void testUpdate() {
        Priority priorityUpdated = priorityRepository.findAll().get(0);

        priorityUpdated.setLabel("Medium - Updated");

        priorityRepository.save(priorityUpdated);

        Priority priority = priorityRepository.findById(priorityUpdated.getIdPriority()).get();
        assertEquals(priority.getLabel().toString(), "Medium - Updated");
    }

    @Test
    public void testDelete() {
        Priority priorityDelete = priorityRepository.findAll().get(0);
        Long idPriority = priorityDelete.getIdPriority();
        priorityRepository.delete(priorityDelete);

        priorityRepository.findById(idPriority);
        assertTrue(priorityRepository.findById(idPriority).isEmpty());
    }

    @Test
    public void testDeleteById() {
        Priority priority = priorityRepository.findAll().get(0);
        priorityRepository.deleteById(priority.getIdPriority());

        assertTrue(priorityRepository.findById(priority.getIdPriority()).isEmpty());
    }
}
