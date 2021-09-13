package cr.una.taskapp.backend.service;

import cr.una.taskapp.backend.dto.PriorityDetails;
import cr.una.taskapp.backend.mapper.PriorityMapper;
import cr.una.taskapp.backend.model.Priority;
import cr.una.taskapp.backend.repository.PriorityRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Implementation of PriorityService
 *
 * @author Maikol Guzman <mike@guzmanalan.com>
 * @create 9/3/21
 */
@Service
public class AbstractPriorityService implements PriorityService {

    @Autowired
    private PriorityRepository priorityRepository;

    /**
     * Find all Priority
     *
     * @return a list of Users
     */
    @Override
    public List<PriorityDetails> findAll() {
        return PriorityMapper.MAPPER.priorityDetailsFromPriorityList(priorityRepository.findAll());
    }

    /**
     * Get one Priority by id
     *
     * @param id of the Priority
     * @return the Priority found
     */
    @Override
    public PriorityDetails findById(Long id) throws NotFoundException {
        Optional<Priority> priority = priorityRepository.findById(id);
        if (priority.isEmpty()) {
            throw new NotFoundException(String.format("The Priority with the id: %s not found!", id));
        }
        return PriorityMapper.MAPPER.priorityDetailsFromPriority(priority.get());
    }

    /**
     * Save and flush a Priority entity in the database
     *
     * @param priorityDetails
     * @return the user created
     */
    @Override
    public PriorityDetails create(PriorityDetails priorityDetails) {
        Priority priority = PriorityMapper.MAPPER.priorityFromPriorityDetails(priorityDetails);
        return PriorityMapper.MAPPER.priorityDetailsFromPriority(priorityRepository.saveAndFlush(priority));
    }

    /**
     * Update a Priority entity in the database
     *
     * @param priorityDetails the dto input for Priority
     * @return the new Priority created
     */
    @Override
    public PriorityDetails update(PriorityDetails priorityDetails) throws NotFoundException {
        Optional<Priority> priority = priorityRepository.findById(priorityDetails.getIdPriority());
        if (priority.isEmpty()) {
            throw new NotFoundException(String.format("The Priority with the id: %s not found!", priorityDetails.getIdPriority()));
        }
        Priority priorityUpdated = priority.get();
        PriorityMapper.MAPPER.priorityFromPriorityDetails(priorityDetails, priorityUpdated);
        return PriorityMapper.MAPPER.priorityDetailsFromPriority(priorityRepository.save(priorityUpdated));
    }

    /**
     * Delete a Priority by id from Database
     *
     * @param id of the Priority
     */
    @Override
    public void deleteById(Long id) throws NotFoundException {
        if (priorityRepository.findById(id).isEmpty()) {
            throw new NotFoundException(String.format("The PriorityRepository with the id: %s not found!", id));
        } else {
            priorityRepository.deleteById(id);
        }
    }
}
