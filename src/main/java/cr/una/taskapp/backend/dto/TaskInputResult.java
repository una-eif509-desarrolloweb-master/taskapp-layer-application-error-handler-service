package cr.una.taskapp.backend.dto;

import lombok.Data;

import java.util.Date;

/**
 * DTO Result for Task
 *
 * @author Maikol Guzman <maikol@guzmanalan.com>
 * @create 9/3/21
 */
@Data
public class TaskInputResult {
    private Long idTask;
    private String title;
    private String notes;
    private Date createDate;
    private Date dueDate;
    private PriorityDetails priority;

}
