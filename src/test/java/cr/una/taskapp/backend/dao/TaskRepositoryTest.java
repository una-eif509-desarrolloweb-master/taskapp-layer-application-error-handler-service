/*
 * Universidad Nacional de Costa Rica  2020
 *
 * mike@guzmanalan.com
 */

package cr.una.taskapp.backend.dao;

import cr.una.taskapp.backend.model.Status;
import cr.una.taskapp.backend.model.Task;
import cr.una.taskapp.backend.model.User;
import cr.una.taskapp.backend.repository.PriorityRepository;
import cr.una.taskapp.backend.repository.TaskRepository;
import org.joda.time.DateTime;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Unit Test Class
 * Task DAO
 */
@Transactional
@SpringBootTest
@Sql({
        "/import-users.sql",
        "/import-priorities.sql",
        "/import-status.sql",
        "/import-tasks.sql",
        "/import-reminders.sql"
})
public class TaskRepositoryTest {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private PriorityRepository priorityRepository;

    @Test
    public void testFindById() {
        Optional<Task> task = taskRepository.findById(1L);

        assertEquals(task.get().getTitle().toString(), "Evaluate Students");
    }

    @Test
    public void testFindAll() {
        List<Task> taskList = taskRepository.findAll();

        assertTrue(taskList.size() == 2);
    }

    @Test
    public void testCreate() {
        DateTime dt = new DateTime();
        Task task = new Task();
        Status status = new Status();
        User user = new User();

        user.setIdUser(1L);

        status.setIdStatus(1L);

        task.setTitle("Test Task");
        task.setNotes("Some notes");
        task.setCreateDate(dt.toDate());
        task.setDueDate(dt.toDate());
        task.setUser(user);

        task.setPriority(priorityRepository.findById(1L).get());
        task.setStatus(status);

        Task taskCreated = taskRepository.save(task);

        assertEquals(taskCreated.getIdTask(), 1L);
    }

    @Test
    public void testUpdate() {
        DateTime dt = new DateTime();
        Task task = taskRepository.findById(1L).get();

        task.setTitle("Test Update");

        taskRepository.save(task);
        assertEquals(task.getTitle().toString(), "Test Update");
    }

    @Test
    public void testDelete() {
        Task task = taskRepository.findAll().get(0);
        Long idTask = task.getIdTask();
        taskRepository.delete(task);
        assertTrue(taskRepository.findById(idTask).isEmpty());
    }

    @Test
    public void testDeleteById() {
        Task task = taskRepository.findAll().get(0);
        taskRepository.deleteById(task.getIdTask());

        assertTrue(taskRepository.findById(task.getIdTask()).isEmpty());
    }
}
