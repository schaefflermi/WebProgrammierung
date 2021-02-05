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

import static org.hibernate.loader.Loader.SELECT;


@Repository
public interface TransactionRepository extends JpaRepository<Creditcardtransaction, UUID> {
   // List<TransactionRepository> findByCNameContaining(String substring);
    List<Creditcardtransaction> findAllCreditcardtransactionBytUuid(UUID tUuid);
    List<Creditcardtransaction> findBytUuid(UUID tUuid);
    List<Creditcardtransaction> findByTdateGreaterThanAndTdateLessThan(LocalDate start, LocalDate end);
  /* @Query("SELECT distinct c FROM Creditcardtransaction c  where  c.status = false and 4 < " +
           "(select count(status) from Creditcardtransaction where status = false)")*/
  @Query("SELECT distinct c FROM Creditcardtransaction c  where  c.status = false ")
    List<Creditcardtransaction> findDistinctByStatus(Boolean status);

    @Query("SELECT distinct c FROM Creditcardtransaction c  where  c.status = false ")
    List<Creditcardtransaction> findDistinctByCustomer();


}
