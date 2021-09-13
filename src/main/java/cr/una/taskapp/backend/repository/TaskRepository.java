package cr.una.taskapp.backend.repository;

import cr.una.taskapp.backend.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Interface for Task
 *
 * @author Maikol Guzman <mike@guzmanalan.com>
 * @create 9/3/21
 */
@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
}
