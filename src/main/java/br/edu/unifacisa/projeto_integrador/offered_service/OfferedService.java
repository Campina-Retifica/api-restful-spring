package br.edu.unifacisa.projeto_integrador.offered_service;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "tb_services")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class OfferedService {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50, name = "name")
    private String name;

    @Column(nullable = false, name = "description", length = 500)
    private String description;

    @Column(name = "value", nullable = false, precision = 10, scale = 2)
    private BigDecimal value;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime created;

    @UpdateTimestamp
    private LocalDateTime updated;

    public OfferedService(OfferedServiceRequest request) {
        this.name = request.name();
        this.description = request.description();
        this.value = request.value();
    }
}