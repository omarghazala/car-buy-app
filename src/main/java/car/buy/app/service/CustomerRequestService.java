package car.buy.app.service;

import car.buy.app.dto.CreateRequestDTO;
import car.buy.app.dto.CustomerRequestResponseDTO;
import car.buy.app.dto.UpdateRequestStatusDTO;
import car.buy.app.entity.CustomerRequest;
import car.buy.app.enums.RequestStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


public interface CustomerRequestService {

    CustomerRequestResponseDTO createRequest(CreateRequestDTO dto);

    CustomerRequestResponseDTO getRequestById(Long id) ;


    Page<CustomerRequestResponseDTO> getAllRequests(RequestStatus status, Pageable pageable);

    CustomerRequestResponseDTO updateRequestStatus(Long id, UpdateRequestStatusDTO dto);

    CustomerRequest findRequestById(Long id);

    CustomerRequestResponseDTO mapToResponseDTO(CustomerRequest request);
}