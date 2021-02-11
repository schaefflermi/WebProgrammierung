package webuildit.myStartup.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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

    List<Creditcardtransaction> findAllByTdateLikeAndVendor(LocalDate date, UUID vUuid);

  @Query("Select c from Creditcardtransaction c where year(c.tdate) = ?1 and month(c.tdate) = ?2")
    List<Creditcardtransaction> getByYearandMonth(int year, int month);
  //Aufagbe 1 :Bei Eingabe der Id eines Verkäufers, der abzurechnende Betrag für diesen für den aktuellen Monat zurückgegeben wird.
    //Nochmal überprüfen bei daten status = false.... kam zu einem Fehler
    @Query("Select c from Creditcardtransaction c where year(c.tdate) = year(CURRENT_DATE) AND month(c.tdate) = month(CURRENT_DATE) and c.status=true and c.vendor.vUuid = ?1")
    List<Creditcardtransaction> findAllByVendorandCurrentMonth(UUID vUuid);

    //aufgabe 1: die aktuellen Einnahmen des StartUps für diesen Monat zurückgibt
    @Query("Select c from Creditcardtransaction c where month(c.tdate) = :param1 and c.status=true")
    List<Creditcardtransaction> findAllByVendorandCurrentMonth(@Param("param1")int month);
    //   @Param("param1") String series);
}
