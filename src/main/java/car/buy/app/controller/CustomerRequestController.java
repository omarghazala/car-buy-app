package car.buy.app.controller;


import car.buy.app.dto.CreateRequestDTO;
import car.buy.app.dto.CustomerRequestResponseDTO;
import car.buy.app.dto.UpdateRequestStatusDTO;
import car.buy.app.enums.RequestStatus;
import car.buy.app.service.CustomerRequestService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/requests")
@AllArgsConstructor
@Tag(name = "Customer Requests", description = "Customer Request Management APIs")
public class CustomerRequestController {

    private final CustomerRequestService customerRequestService;

    @PostMapping
    @Operation(summary = "Create a new customer request")
    public ResponseEntity<CustomerRequestResponseDTO> createRequest(@Valid @RequestBody CreateRequestDTO dto) {
        CustomerRequestResponseDTO response = customerRequestService.createRequest(dto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get customer request by ID")
    public ResponseEntity<CustomerRequestResponseDTO> getRequestById(@PathVariable Long id) {
        CustomerRequestResponseDTO response = customerRequestService.getRequestById(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    @Operation(summary = "Get all customer requests with optional filtering and pagination")
    public ResponseEntity<Page<CustomerRequestResponseDTO>> getAllRequests(
            @RequestParam(required = false) RequestStatus status,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "createdAt") String sortBy,
            @RequestParam(defaultValue = "DESC") String sortDirection) {

        Sort.Direction direction = sortDirection.equalsIgnoreCase("ASC")
                ? Sort.Direction.ASC
                : Sort.Direction.DESC;

        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortBy));
        Page<CustomerRequestResponseDTO> response = customerRequestService.getAllRequests(status, pageable);

        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{id}/status")
    @Operation(summary = "Update customer request status")
    public ResponseEntity<CustomerRequestResponseDTO> updateRequestStatus(
            @PathVariable Long id,
            @Valid @RequestBody UpdateRequestStatusDTO dto) {
        CustomerRequestResponseDTO response = customerRequestService.updateRequestStatus(id, dto);
        return ResponseEntity.ok(response);
    }
}
