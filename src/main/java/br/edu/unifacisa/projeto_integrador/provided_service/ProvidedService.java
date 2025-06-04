package br.edu.unifacisa.projeto_integrador.provided_service;

import br.edu.unifacisa.projeto_integrador.customer.Customer;
import br.edu.unifacisa.projeto_integrador.offered_service.OfferedService;
import br.edu.unifacisa.projeto_integrador.offered_service.OfferedServiceEnum;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "tb_provided_services")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class ProvidedService {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "service_id", referencedColumnName = "id")
    private OfferedService service;

    @ManyToOne(optional = false)
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private Customer customer;

    @Column(name = "start_date", nullable = false)
    private LocalDateTime startDate;

    @Column(name = "end_date")
    private LocalDateTime endDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private OfferedServiceEnum status = OfferedServiceEnum.IN_PROGRESS;

    @Column(nullable = false)
    private Boolean paid = false;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public ProvidedService(OfferedService service, Customer customer, LocalDateTime startDate, LocalDateTime endDate) {
        this.service = service;
        this.customer = customer;
        this.startDate = startDate;
        this.endDate = endDate;
    }
}