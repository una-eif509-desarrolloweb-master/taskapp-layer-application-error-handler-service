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
import java.util.Date;
import java.util.List;

/**
 * Entity for Reminder
 */
@Entity
@Table( name = "reminder" )
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Reminder {

    // Properties
    @Id
    private Long idReminder;
    @Column(name = "reminder_date")
    private Date reminderDate;

    // Relationship definition
    @OneToOne(fetch = FetchType.LAZY)
    @MapsId // Share the same primary key between 2 tables
    private Task task;
}
