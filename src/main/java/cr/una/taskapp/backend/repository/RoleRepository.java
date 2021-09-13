package cr.una.taskapp.backend.repository;

import cr.una.taskapp.backend.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Interface for Role Repository
 *
 * @author Maikol Guzman <mike@guzmanalan.com>
 * @create 7/29/21
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    /**
     * Find all roles by User Id
     * @param id
     * @return a list of Roles of User
     */
    @Query("SELECT r FROM Role r INNER JOIN r.users u WHERE u.idUser = :id")
    public List<Role> findAllByUserId(@Param("id") Long id);

    /**
     * Find all roles by Username
     * @param userName
     * @return a list of Roles of User
     */
    @Query("SELECT r FROM Role r INNER JOIN r.users u WHERE u.email = :userName")
    public List<Role> findAllByUsername(@Param("userName") String userName);

    /**
     * Find a Role by the name
     * @param name the name of the role in the DB
     * @return the Role found
     */
    @Query("SELECT r FROM Role r WHERE r.name = :name")
    public Role findByName(@Param("name") String name);
}
