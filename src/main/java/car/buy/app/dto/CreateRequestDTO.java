package car.buy.app.dto;

import car.buy.app.enums.InspectionCompany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateRequestDTO {

    @NotNull(message = "Customer ID is required")
    private Long customerId;

    @NotBlank(message = "Description is required")
    private String description;

    @NotNull(message = "Inspection company is required")
    private InspectionCompany checkedByCompany;
}