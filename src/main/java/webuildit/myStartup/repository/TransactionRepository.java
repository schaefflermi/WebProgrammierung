package webuildit.myStartup.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import webuildit.myStartup.model.Classification;
import webuildit.myStartup.model.Creditcardtransaction;
import webuildit.myStartup.model.Vendor;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Repository
public interface TransactionRepository extends JpaRepository<Creditcardtransaction, UUID> {
   // List<TransactionRepository> findByCNameContaining(String substring);
    List<Creditcardtransaction> findAllCreditcardtransactionBytUuid(UUID tUuid);
    List<Creditcardtransaction> findBytUuid(UUID tUuid);
    List<Creditcardtransaction> findByTdateGreaterThanAndTdateLessThan(LocalDate start, LocalDate end);
   /* @Query("SELECT distinct CUSTOMER_ID FROM CREDITCARDTRANSACTION where status = false and (select count(status) from CREDITCARDTRANSACTION where status = false) > 5")
    List<Creditcardtransaction> findAllCreditcradtransactions();
*/
   // List<Creditcardtransaction> findByVendorUuid(UUID vendorUuid);

}
