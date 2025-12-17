package car.buy.app.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InspectionRequest {
    private Long offerId;
    private Long requestId;
    private String carDetails;
    private BigDecimal price;
}
