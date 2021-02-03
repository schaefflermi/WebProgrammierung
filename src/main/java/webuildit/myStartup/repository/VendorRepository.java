package webuildit.myStartup.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import webuildit.myStartup.model.Classification;
import webuildit.myStartup.model.Creditcardtransaction;
import webuildit.myStartup.model.Vendor;

import java.util.List;
import java.util.UUID;

@Repository
public interface VendorRepository extends JpaRepository<Vendor, UUID> {
    List<Vendor> findByClassification(Classification classification);
   // List<Vendor>findCreditcardtransactionsByvUuid(UUID vUuid);
}
