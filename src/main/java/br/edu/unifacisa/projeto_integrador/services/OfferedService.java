package br.edu.unifacisa.projeto_integrador.services;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "tb_services")
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@EqualsAndHashCode(of = "id")
public class OfferedService {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 50, name = "name")
    private String name;
    @Column(nullable = false, name = "description")
    private String description;
    @Column(name = "service_value", nullable = false)
    private BigDecimal value;
    private OfferedServiceEnum status = OfferedServiceEnum.PENDENTE;
    @CreationTimestamp
    private LocalDateTime created;
    @UpdateTimestamp
    private LocalDateTime updated;


    public OfferedService (OfferedServiceRequest request) {
        this.name = request.name();
        this.description = request.description();
        this.value = request.value();
    }
}
