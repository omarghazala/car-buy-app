package car.buy.app.dto;

import car.buy.app.enums.RequestStatus;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateRequestStatusDTO {

    @NotNull(message = "Status is required")
    private RequestStatus status;
}
