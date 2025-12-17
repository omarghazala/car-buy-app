package car.buy.app.service.implementation;


import car.buy.app.dto.CreateRequestDTO;
import car.buy.app.dto.CustomerRequestResponseDTO;
import car.buy.app.dto.UpdateRequestStatusDTO;
import car.buy.app.entity.CustomerRequest;
import car.buy.app.enums.RequestStatus;
import car.buy.app.exceptions.ResourceNotFoundException;
import car.buy.app.repository.CustomerRequestRepository;
import car.buy.app.service.CustomerRequestService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
@Slf4j
@Transactional
public class CustomerRequestServiceImpl implements CustomerRequestService {

    private CustomerRequestRepository customerRequestRepository;

    public CustomerRequestResponseDTO createRequest(CreateRequestDTO dto) {

        CustomerRequest request = CustomerRequest.builder()
                .customerId(dto.getCustomerId())
                .description(dto.getDescription())
                .checkedByCompany(dto.getCheckedByCompany())
                .status(RequestStatus.ACTIVE)
                .build();

        CustomerRequest savedRequest = customerRequestRepository.save(request);

        return mapToResponseDTO(savedRequest);
    }

    @Transactional(readOnly = true)
    public CustomerRequestResponseDTO getRequestById(Long id) {
        CustomerRequest request = findRequestById(id);
        return mapToResponseDTO(request);
    }

    @Transactional(readOnly = true)
    public Page<CustomerRequestResponseDTO> getAllRequests(RequestStatus status, Pageable pageable) {

        Page<CustomerRequest> requests;
        if (status != null) {
            requests = customerRequestRepository.findByStatus(status, pageable);
        } else {
            requests = customerRequestRepository.findAll(pageable);
        }

        return requests.map(this::mapToResponseDTO);
    }

    public CustomerRequestResponseDTO updateRequestStatus(Long id, UpdateRequestStatusDTO dto) {

        CustomerRequest request = findRequestById(id);
        request.setStatus(dto.getStatus());
        CustomerRequest updatedRequest = customerRequestRepository.save(request);

        log.info("Request status updated successfully");
        return mapToResponseDTO(updatedRequest);
    }

    public CustomerRequest findRequestById(Long id) {
        return customerRequestRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer request not found with ID: " + id));
    }

    public CustomerRequestResponseDTO mapToResponseDTO(CustomerRequest request) {
        return CustomerRequestResponseDTO.builder()
                .id(request.getId())
                .customerId(request.getCustomerId())
                .status(request.getStatus())
                .description(request.getDescription())
                .checkedByCompany(request.getCheckedByCompany())
                .offerCount(request.getOfferCount())
                .createdAt(request.getCreatedAt())
                .updatedAt(request.getUpdatedAt())
                .build();
    }


}
