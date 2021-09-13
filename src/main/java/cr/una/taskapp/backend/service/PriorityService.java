package cr.una.taskapp.backend.service;

import cr.una.taskapp.backend.dto.PriorityDetails;
import javassist.NotFoundException;

import java.util.List;

/**
 * Interface definition for Priority Service
 *
 * @author Maikol Guzman <mike@guzmanalan.com>
 * @create 9/3/21
 */
public interface PriorityService {

    /**
     * Find all Priority
     * @return a list of Users
     */
    public List<PriorityDetails> findAll();

    /**
     * Get one Priority by id
     * @param id of the Priority
     * @return the Priority found
     */
    public PriorityDetails findById(Long id) throws NotFoundException;

    /**
     * Save and flush a Priority entity in the database
     * @param priorityDetails
     * @return the user created
     */
    public PriorityDetails create(PriorityDetails priorityDetails);

    /**
     * Update a Priority entity in the database
     * @param priorityDetails the dto input for Priority
     * @return the new Priority created
     */
    public PriorityDetails update(PriorityDetails priorityDetails) throws NotFoundException;

    /**
     * Delete a Priority by id from Database
     * @param id of the Priority
     */
    public void deleteById(Long id) throws NotFoundException;
}
