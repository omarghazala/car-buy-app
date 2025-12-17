package car.buy.app.service.implementation;

import car.buy.app.dto.CreateOfferDTO;
import car.buy.app.dto.SupplierOfferResponseDTO;
import car.buy.app.entity.CustomerRequest;
import car.buy.app.entity.SupplierOffer;
import car.buy.app.enums.OfferStatus;
import car.buy.app.exceptions.BusinessException;
import car.buy.app.exceptions.ResourceNotFoundException;
import car.buy.app.repository.SupplierOfferRepository;
import car.buy.app.service.CustomerRequestService;
import car.buy.app.service.SupplierOfferService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
@Transactional
public class SupplierOfferServiceImpl implements SupplierOfferService {

    private final SupplierOfferRepository supplierOfferRepository;
    private final CustomerRequestService customerRequestService;
//    private final InspectionAdapterFactory inspectionAdapterFactory;

    public SupplierOfferResponseDTO submitOffer(CreateOfferDTO dto) {
        log.info("Submitting offer for supplier ID: {} on request ID: {}",
                dto.getSupplierId(), dto.getCustomerRequestId());

        // Fetch and validate customer request
        CustomerRequest customerRequest = customerRequestService.findRequestById(dto.getCustomerRequestId());

        // Validate request is active
        if (!customerRequest.isActive()) {
            throw new BusinessException("Cannot submit offer. Request status is: " + customerRequest.getStatus());
        }

        // Check if supplier already submitted an offer for this request
        if (supplierOfferRepository.existsBySupplierIdAndCustomerRequestId(
                dto.getSupplierId(), dto.getCustomerRequestId())) {
            throw new BusinessException("Supplier has already submitted an offer for this request");
        }

        // Create the offer
        SupplierOffer offer = SupplierOffer.builder()
                .supplierId(dto.getSupplierId())
                .customerRequest(customerRequest)
                .carDetails(dto.getCarDetails())
                .price(dto.getPrice())
                .status(OfferStatus.PENDING)
                .build();

        SupplierOffer savedOffer = supplierOfferRepository.save(offer);
        log.info("Offer created with ID: {}", savedOffer.getId());

        return mapToResponseDTO(savedOffer);
    }


    @Transactional(readOnly = true)
    public List<SupplierOfferResponseDTO> getOffersByRequestId(Long requestId) {
        log.info("Fetching all offers for request ID: {}", requestId);

        // Verify request exists
        customerRequestService.findRequestById(requestId);

        List<SupplierOffer> offers = supplierOfferRepository.findByCustomerRequestId(requestId);
        return offers.stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<SupplierOfferResponseDTO> getOffersBySupplierId(Long supplierId) {
        log.info("Fetching all offers for supplier ID: {}", supplierId);

        List<SupplierOffer> offers = supplierOfferRepository.findBySupplierId(supplierId);
        return offers.stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public SupplierOfferResponseDTO getOfferById(Long id) {
        log.info("Fetching offer with ID: {}", id);
        SupplierOffer offer = supplierOfferRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Supplier offer not found with ID: " + id));
        return mapToResponseDTO(offer);
    }

    public SupplierOfferResponseDTO mapToResponseDTO(SupplierOffer offer) {
        return SupplierOfferResponseDTO.builder()
                .id(offer.getId())
                .supplierId(offer.getSupplierId())
                .customerRequestId(offer.getCustomerRequest().getId())
                .status(offer.getStatus())
                .inspectionScore(offer.getInspectionScore())
                .carDetails(offer.getCarDetails())
                .price(offer.getPrice())
                .createdAt(offer.getCreatedAt())
                .updatedAt(offer.getUpdatedAt())
                .build();
    }
}
