package car.buy.app.entity;

import car.buy.app.enums.OfferStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@Entity
@Table(name = "supplier_offer")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SupplierOffer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "supplier_id", nullable = false)
    private Long supplierId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_request_id", nullable = false)
    @JsonIgnore
    private CustomerRequest customerRequest;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @Builder.Default
    private OfferStatus status = OfferStatus.PENDING;

    @Column(name = "inspection_score")
    @Min(value = 0, message = "Inspection score must be at least 0")
    @Max(value = 100, message = "Inspection score must not exceed 100")
    private Integer inspectionScore;

    @Column(name = "car_details", nullable = false, columnDefinition = "TEXT")
    private String carDetails;

    @Column(nullable = false, precision = 15, scale = 2)
    private BigDecimal price;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;
}
