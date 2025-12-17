package car.buy.app.entity;

import car.buy.app.enums.InspectionCompany;
import car.buy.app.enums.RequestStatus;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "customer_request")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "customer_id", nullable = false)
    private Long customerId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RequestStatus status;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "checked_by_company", nullable = false)
    private InspectionCompany checkedByCompany;

    @OneToMany(mappedBy = "customerRequest", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<SupplierOffer> offers = new ArrayList<>();

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @Transient
    public int getOfferCount() {
        return offers != null ? offers.size() : 0;
    }

    public boolean isActive() {
        return status == RequestStatus.ACTIVE;
    }
}
