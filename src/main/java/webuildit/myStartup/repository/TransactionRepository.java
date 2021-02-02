package webuildit.myStartup.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import webuildit.myStartup.model.Creditcardtransaction;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Repository
public interface TransactionRepository extends JpaRepository<Creditcardtransaction, UUID> {
   // List<TransactionRepository> findByCNameContaining(String substring);
    List<Creditcardtransaction> findAllCreditcardtransactionBytUuid(UUID tUuid);
    List<Creditcardtransaction> findBytUuid(UUID tUuid);
    List<Creditcardtransaction> findByTdateGreaterThanAndTdateLessThan(LocalDate start, LocalDate end);


   // List<Creditcardtransaction> findByVendorUuid(UUID vendorUuid);

}
