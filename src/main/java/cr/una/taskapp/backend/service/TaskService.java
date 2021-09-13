package cr.una.taskapp.backend.service;

import cr.una.taskapp.backend.dto.TaskInputResult;
import javassist.NotFoundException;

import java.util.List;

/**
 * Interface Definition for Task
 *
 * @author Maikol Guzman <mike@guzmanalan.com>
 * @create 9/3/21
 */
public interface TaskService {

    /**
     * Find all Task
     * @return a list of Users
     */
    public List<TaskInputResult> findAll();

    /**
     * Get one Task by id
     * @param id of the Task
     * @return the Task found
     */
    public TaskInputResult findById(Long id) throws NotFoundException;

    /**
     * Save and flush a Task entity in the database
     * @param taskInputResult
     * @return the user created
     */
    public TaskInputResult create(TaskInputResult taskInputResult);

    /**
     * Update a Task entity in the database
     * @param taskInputResult the dto input for Task
     * @return the new Task created
     */
    public TaskInputResult update(TaskInputResult taskInputResult) throws NotFoundException;

    /**
     * Delete a Task by id from Database
     * @param id of the Task
     */
    public void deleteById(Long id) throws NotFoundException;
}
