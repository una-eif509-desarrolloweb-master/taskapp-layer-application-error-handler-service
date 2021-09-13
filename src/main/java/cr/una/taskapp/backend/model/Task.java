/*
 *
 * Universidad Nacional de Costa Rica  2020
 *
 * mike@guzmanalan.com
 *
 */

package cr.una.taskapp.backend.model;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

/**
 * Entity for Task
 */
@Entity
@Table( name = "task" )
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Task {

    // Properties
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_task")
    private Long idTask;
    private String title;
    private String notes;
    @Temporal(TemporalType.DATE)
    private Date createDate;
    @Temporal(TemporalType.DATE)
    private Date dueDate;

    // Relationship definition
    @ManyToOne
    @JoinColumn(name = "id_priority", nullable = false)
    private Priority priority;

    @ManyToOne
    @JoinColumn(name = "id_status", nullable = false)
    private Status status;

    @ManyToOne
    @JoinColumn(name = "id_user", nullable = false)
    private User user;
}