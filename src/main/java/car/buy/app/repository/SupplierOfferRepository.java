package car.buy.app.repository;

import car.buy.app.entity.SupplierOffer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SupplierOfferRepository extends JpaRepository<SupplierOffer, Long> {

    List<SupplierOffer> findByCustomerRequestId(Long customerRequestId);

    List<SupplierOffer> findBySupplierId(Long supplierId);

    @Query("SELECT so FROM SupplierOffer so WHERE so.supplierId = :supplierId AND so.customerRequest.id = :requestId")
    Optional<SupplierOffer> findBySupplierIdAndCustomerRequestId(Long supplierId, Long requestId);

    boolean existsBySupplierIdAndCustomerRequestId(Long supplierId, Long customerRequestId);
}