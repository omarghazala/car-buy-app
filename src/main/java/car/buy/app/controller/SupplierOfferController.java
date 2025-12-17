package car.buy.app.controller;

import car.buy.app.dto.CreateOfferDTO;
import car.buy.app.dto.SupplierOfferResponseDTO;
import car.buy.app.service.SupplierOfferService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/offers")
@AllArgsConstructor
@Tag(name = "Supplier Offers", description = "Supplier Offer Management APIs")
public class SupplierOfferController {

    private final SupplierOfferService supplierOfferService;

    @PostMapping
    @Operation(summary = "Submit a new supplier offer")
    public ResponseEntity<SupplierOfferResponseDTO> submitOffer(@Valid @RequestBody CreateOfferDTO dto) {
        SupplierOfferResponseDTO response = supplierOfferService.submitOffer(dto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get supplier offer by ID")
    public ResponseEntity<SupplierOfferResponseDTO> getOfferById(@PathVariable Long id) {
        SupplierOfferResponseDTO response = supplierOfferService.getOfferById(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/request/{requestId}")
    @Operation(summary = "Get all offers for a specific customer request")
    public ResponseEntity<List<SupplierOfferResponseDTO>> getOffersByRequestId(@PathVariable Long requestId) {
        List<SupplierOfferResponseDTO> response = supplierOfferService.getOffersByRequestId(requestId);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/supplier/{supplierId}")
    @Operation(summary = "Get all offers by a specific supplier")
    public ResponseEntity<List<SupplierOfferResponseDTO>> getOffersBySupplierId(@PathVariable Long supplierId) {
        List<SupplierOfferResponseDTO> response = supplierOfferService.getOffersBySupplierId(supplierId);
        return ResponseEntity.ok(response);
    }
}
