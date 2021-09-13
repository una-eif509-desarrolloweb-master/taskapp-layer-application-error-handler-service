/*
 * Universidad Nacional de Costa Rica  2020
 *
 * mike@guzmanalan.com
 */

package cr.una.taskapp.backend.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

/**
 * Entity for the user
 */
@Entity
@Table( name = "privilege" )
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Privilege {
    // Properties
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_privilege")
    private Long idPrivilege;
    private String name;

    // Relationship definition
    @ManyToMany(mappedBy = "privileges")
    private List<Role> roleList;
}
