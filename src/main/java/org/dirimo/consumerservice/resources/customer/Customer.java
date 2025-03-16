package org.dirimo.consumerservice.resources.customer;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.dirimo.consumerservice.resources.reservationHistory.ReservationHistory;

import java.util.List;


@Entity
@Table(name = "Customers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Customer{

    @Id
    @Column(name = "ID")
    private Long id;

    @Column(name="FIRST_NAME",nullable = false)
    private String firstName;

    @Column(name="LAST_NAME",nullable = false)
    private String lastName;

    @Column(name = "EMAIL", nullable = false)
    private String email;

    @OneToMany(mappedBy = "customer")
    @JsonIgnore
    private List<ReservationHistory> reservation;

    public Customer(Long customerId, String firstName, String lastName, String email) {
        this.id = customerId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }
}
