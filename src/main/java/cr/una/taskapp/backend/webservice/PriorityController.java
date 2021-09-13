package cr.una.taskapp.backend.webservice;

import cr.una.taskapp.backend.dto.PriorityDetails;
import cr.una.taskapp.backend.service.PriorityService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Web service controller for Priority
 *
 * @author Maikol Guzman <mike@guzmanalan.com>
 * @create 9/12/21
 */
@RestController
@RequestMapping("${url.priorities}")
public class PriorityController {

    @Autowired
    PriorityService priorityService;

    /**
     * WS to find all elements of type Priority
     * @return A list of elements of type Priority
     */
    @GetMapping()
    @ResponseBody
    public List<PriorityDetails> findAll() {
        return priorityService.findAll();
    }

    /**
     * WS to find one Priority by the id
     * @param id to find Priority
     * @return the Priority found
     * @throws NotFoundException
     */
    @GetMapping("{id}")
    @ResponseBody
    public PriorityDetails findById(@PathVariable Long id) throws NotFoundException {
        return priorityService.findById(id);
    }

    /**
     * WS to create a new Priority in the database
     * @param priorityDetails the necessary information to create a new Priority
     * @return Priority updated
     */
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public PriorityDetails create(@RequestBody PriorityDetails priorityDetails) {
        return priorityService.create(priorityDetails);
    }

    /**
     * WS to update a Priority in the database
     * @param priorityDetails the necessary information to update an existing Priority
     * @return the priority updated
     * @throws NotFoundException
     */
    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public PriorityDetails update(@RequestBody PriorityDetails priorityDetails) throws NotFoundException {
        return priorityService.update(priorityDetails);
    }

    /**
     * WS to delete a Priority in the database by the id
     * @param id
     * @throws NotFoundException
     */
    @DeleteMapping("{id}")
    @ResponseBody
    public void deleteById(@PathVariable Long id) throws NotFoundException {
        priorityService.deleteById(id);
    }
}
