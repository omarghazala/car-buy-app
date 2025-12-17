package car.buy.app.service;

import car.buy.app.dto.CreateOfferDTO;
import car.buy.app.dto.SupplierOfferResponseDTO;
import car.buy.app.entity.CustomerRequest;
import car.buy.app.entity.SupplierOffer;

import java.util.List;

public interface SupplierOfferService {

    SupplierOfferResponseDTO submitOffer(CreateOfferDTO dto);
    List<SupplierOfferResponseDTO> getOffersByRequestId(Long requestId);
    List<SupplierOfferResponseDTO> getOffersBySupplierId(Long supplierId);
    SupplierOfferResponseDTO getOfferById(Long id);
    SupplierOfferResponseDTO mapToResponseDTO(SupplierOffer offer);

}
