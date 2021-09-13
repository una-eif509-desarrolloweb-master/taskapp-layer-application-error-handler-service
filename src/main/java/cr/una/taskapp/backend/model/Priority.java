/*
 *
 *
 * Universidad Nacional de Costa Rica  2020
 *
 * mike@guzmanalan.com
 *
 *
 */

package cr.una.taskapp.backend.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

/**
 * Entity for the priority
 */
@Entity
@Table( name = "priority" )
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Priority {

    // Properties
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_priority")
    private Long idPriority;
    @Column(name = "label")
    private String label;

    // Relationship definition
    @OneToMany(mappedBy = "priority")
    private List<Task> taskList;
}
