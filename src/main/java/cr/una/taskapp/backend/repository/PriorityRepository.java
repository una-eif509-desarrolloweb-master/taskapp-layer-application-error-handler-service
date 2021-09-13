package cr.una.taskapp.backend.repository;

import cr.una.taskapp.backend.model.Priority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Interface for Priority
 *
 * @author Maikol Guzman <mike@guzmanalan.com>
 * @create 9/3/21
 */
@Repository
public interface PriorityRepository extends JpaRepository<Priority, Long> {
}
