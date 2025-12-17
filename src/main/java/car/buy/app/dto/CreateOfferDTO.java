package car.buy.app.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateOfferDTO {
    @NotNull(message = "Supplier ID is required")
    private Long supplierId;

    @NotNull(message = "Customer Request ID is required")
    private Long customerRequestId;

    @NotBlank(message = "Car details are required")
    private String carDetails;

    @NotNull(message = "Price is required")
    @DecimalMin(value = "0.01", message = "Price must be greater than 0")
    private BigDecimal price;
}
