package br.edu.unifacisa.projeto_integrador.customer;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "tb_customers")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Customer {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name", nullable = false, length = 30)
    private String firstName;

    @Column(name = "middle_name", nullable = false, length = 50)
    private String middleName;

    @Column(name = "last_name", nullable = false, length = 65)
    private String lastName;

    @Column(name = "document", nullable = false, length = 55)
    private String document;

    @Column(name = "email", nullable = false, length = 125)
    private String email;

    @Column(name = "telephone", nullable = false, length = 35)
    private String telephone;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private CustomerEnum status;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public Customer(CustomerDTO newCustomer) {
        this.firstName = newCustomer.firstName();
        this.middleName = newCustomer.middleName();
        this.lastName = newCustomer.lastName();
        this.document = newCustomer.document();
        this.email = newCustomer.email();
        this.telephone = newCustomer.telephone();
        this.status = CustomerEnum.ACTIVE;
    }
}