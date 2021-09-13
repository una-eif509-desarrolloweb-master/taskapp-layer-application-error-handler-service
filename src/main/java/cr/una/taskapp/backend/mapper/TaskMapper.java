package cr.una.taskapp.backend.mapper;

import cr.una.taskapp.backend.dto.TaskInputResult;
import cr.una.taskapp.backend.model.Task;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * DTO Mapper for Task
 *
 * @author Maikol Guzman <maikol@guzmanalan.com>
 * @create 9/3/21
 */
@Mapper(
        unmappedTargetPolicy = ReportingPolicy.ERROR,
        uses = {PriorityMapper.class})
public interface TaskMapper {
    TaskMapper MAPPER = Mappers.getMapper(TaskMapper.class);

    TaskInputResult taskInputResultFromTask(Task task);

    List<TaskInputResult> taskInputResultFromTaskList(List<Task> task);

    @Mapping(target = "status", ignore = true)
    @Mapping(target = "user", ignore = true)
    Task taskFromTaskInputResult(TaskInputResult taskInputResult);

    @Mapping(target = "status", ignore = true)
    @Mapping(target = "user", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void taskFromTaskInputResult(TaskInputResult dto, @MappingTarget Task task);
}
