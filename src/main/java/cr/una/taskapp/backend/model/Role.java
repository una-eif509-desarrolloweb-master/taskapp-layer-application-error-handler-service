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
 * Entity for the role
 */
@Entity
@Table( name = "role" )
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Role {
    // Properties
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_role")
    private Long idRole;
    private String name;

    // Relationship definition
    @ManyToMany(mappedBy = "roles")
    private List<User> users;

    @ManyToMany
    @JoinTable(
            name = "roles_privileges",
            joinColumns = @JoinColumn(name = "id_role"),
            inverseJoinColumns = @JoinColumn(name = "id_privilege"))
    private List<Privilege> privileges;
}
