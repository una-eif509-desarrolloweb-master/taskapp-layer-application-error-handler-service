/*
 * Universidad Nacional de Costa Rica  2020
 *
 * mike@guzmanalan.com
 *
 */

package cr.una.taskapp.backend.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

/**
 * Entity for Status
 */
@Entity
@Table( name = "status" )
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Status {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_status")
    private Long idStatus;
    private String label;

    // Relationship definition
    @OneToMany(mappedBy = "status")
    private List<Task> taskList;
}
