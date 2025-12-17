package car.buy.app.repository;

import car.buy.app.entity.CustomerRequest;
import car.buy.app.enums.RequestStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRequestRepository extends JpaRepository<CustomerRequest, Long> {

    Page<CustomerRequest> findByStatus(RequestStatus status, Pageable pageable);

    Page<CustomerRequest> findByCustomerId(Long customerId, Pageable pageable);

    @Query("SELECT cr FROM CustomerRequest cr WHERE cr.status = :status AND cr.customerId = :customerId")
    Page<CustomerRequest> findByStatusAndCustomerId(RequestStatus status, Long customerId, Pageable pageable);
}
