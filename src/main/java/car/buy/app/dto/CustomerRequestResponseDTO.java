package car.buy.app.dto;


import car.buy.app.enums.InspectionCompany;
import car.buy.app.enums.RequestStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerRequestResponseDTO {

    private Long id;
    private Long customerId;
    private RequestStatus status;
    private String description;
    private InspectionCompany checkedByCompany;
    private Integer offerCount;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
