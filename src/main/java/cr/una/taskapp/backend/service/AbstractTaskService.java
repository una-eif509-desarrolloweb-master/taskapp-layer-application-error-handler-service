package cr.una.taskapp.backend.service;

import cr.una.taskapp.backend.dto.TaskInputResult;
import javassist.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implementation of AbstractService
 *
 * @author Maikol Guzman <mike@guzmanalan.com>
 * @create 9/3/21
 */
@Service
public class AbstractTaskService implements TaskService {

    /**
     * Find all Task
     *
     * @return a list of Users
     */
    @Override
    public List<TaskInputResult> findAll() {
        return null;
    }

    /**
     * Get one Task by id
     *
     * @param id of the Task
     * @return the Task found
     */
    @Override
    public TaskInputResult findById(Long id) throws NotFoundException {
        return null;
    }

    /**
     * Save and flush a Task entity in the database
     *
     * @param taskInputResult
     * @return the user created
     */
    @Override
    public TaskInputResult create(TaskInputResult taskInputResult) {
        return null;
    }

    /**
     * Update a Task entity in the database
     *
     * @param taskInputResult the dto input for Task
     * @return the new Task created
     */
    @Override
    public TaskInputResult update(TaskInputResult taskInputResult) throws NotFoundException {
        return null;
    }

    /**
     * Delete a Task by id from Database
     *
     * @param id of the Task
     */
    @Override
    public void deleteById(Long id) throws NotFoundException {

    }
}
