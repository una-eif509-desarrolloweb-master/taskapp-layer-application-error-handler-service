package cr.una.taskapp.backend.mapper;

import cr.una.taskapp.backend.dto.PriorityDetails;
import cr.una.taskapp.backend.model.Priority;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * DTO Mapper for Priority
 *
 * @author Maikol Guzman <mike@guzmanalan.com>
 * @create 9/3/21
 */
@Mapper
public interface PriorityMapper {
    PriorityMapper MAPPER = Mappers.getMapper(PriorityMapper.class);

    PriorityDetails priorityDetailsFromPriority(Priority priority);

    List<PriorityDetails> priorityDetailsFromPriorityList(List<Priority> priority);

    Priority priorityFromPriorityDetails(PriorityDetails priorityDetails);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void priorityFromPriorityDetails(PriorityDetails dto, @MappingTarget Priority priority);
}
