package cr.una.taskapp.backend.service;

import cr.una.taskapp.backend.dto.PriorityDetails;
import cr.una.taskapp.backend.mapper.PriorityMapper;
import cr.una.taskapp.backend.model.Priority;
import cr.una.taskapp.backend.model.Task;
import cr.una.taskapp.backend.repository.PriorityRepository;
import javassist.NotFoundException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

/**
 * Test for Priority Service
 *
 * @author Maikol Guzman <maikol@guzmanalan.com>
 * @create 9/4/21
 */
@RunWith(MockitoJUnitRunner.class)
public class PriorityServiceTest {

    @InjectMocks
    private AbstractPriorityService priorityService;

    @Mock
    private PriorityRepository priorityRepository;

    @Test
    public void testFindById() throws NotFoundException {
        // given
        final Long id = 1l;
        Task task = new Task();
        Priority priority = new Priority();
        PriorityDetails priorityExpected;

        task.setIdTask(id);
        task.setTitle("Test");

        priority.setIdPriority(id);
        priority.setLabel("Medium");
        priority.setTaskList(Arrays.asList(task));

        given(priorityRepository.findById(id)).willReturn(java.util.Optional.of(priority));

        // when
        priorityExpected = priorityService.findById(id);

        // then
        assertThat(priorityExpected).isNotNull();
    }

    @Test
    public void testFindAll() {
        // given
        final Long id = 1l;
        Task task = new Task();
        Priority priority = new Priority();

        task.setIdTask(id);
        task.setTitle("Test");

        priority.setIdPriority(id);
        priority.setLabel("Medium");
        priority.setTaskList(Arrays.asList(task));

        List<Priority> priorityList = Arrays.asList(priority);

        given(priorityRepository.findAll()).willReturn(priorityList);

        // when
        List<PriorityDetails> priorityListExpected = priorityService.findAll();

        // then
        assertThat(priorityListExpected.size()).isGreaterThan(0);
    }

    @Test
    public void testCreatePriority() {
        // given
        final Long id = 1l;
        Task task = new Task();
        Priority priority = new Priority();

        task.setIdTask(id);
        task.setTitle("Test");

        priority.setIdPriority(id);
        priority.setLabel("Medium");
        priority.setTaskList(Arrays.asList(task));

        given(priorityRepository.saveAndFlush(any(Priority.class))).willReturn(priority);

        // when
        PriorityDetails priorityDetails = PriorityMapper.MAPPER.priorityDetailsFromPriority(priority);
        PriorityDetails priorityCreated = priorityService.create(priorityDetails);

        // then
        assertThat(priorityCreated).isNotNull();
    }

    @Test
    public void testUpdatePriority() throws NotFoundException {
        // given
        final Long id = 1L;
        Task task = new Task();
        Priority priority = new Priority();

        task.setIdTask(id);
        task.setTitle("Test");

        priority.setIdPriority(id);
        priority.setLabel("Medium");
        priority.setTaskList(Arrays.asList(task));

        given(priorityRepository.findById(id)).willReturn(java.util.Optional.of(priority));
        given(priorityRepository.save(any(Priority.class))).willReturn(priority);

        // when
        PriorityDetails priorityDetails = PriorityMapper.MAPPER.priorityDetailsFromPriority(priority);
        PriorityDetails priorityUpdated = priorityService.update(priorityDetails);

        // then
        assertThat(priorityUpdated).isNotNull();
    }
}
