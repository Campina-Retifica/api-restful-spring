package br.edu.unifacisa.projeto_integrador.client;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "tb_clients")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name", nullable = false, length = 30)
    private String firstName;
    @Column(name = "middle_name", nullable = false, length = 50)
    private String middleName;
    @Column(name = "last_name", nullable = false, length = 65)
    private String lastName;
    @Column(name = "document", nullable = false, unique = true, length = 40)
    private String document;
    @Column(name = "email", nullable = false, unique = true, length = 40)
    private String email;
    @Column(name = "telephone", nullable = false, unique = true, length = 35)
    private String telephone;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

}
