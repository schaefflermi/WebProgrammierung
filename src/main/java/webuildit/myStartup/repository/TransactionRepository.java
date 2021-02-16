package webuildit.myStartup.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import webuildit.myStartup.model.Classification;
import webuildit.myStartup.model.Creditcardtransaction;
import webuildit.myStartup.model.Vendor;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import static org.hibernate.loader.Loader.SELECT;


@Repository
public interface TransactionRepository extends JpaRepository<Creditcardtransaction, UUID> {

  //Aufgabe 1.1 :Bei Eingabe der Id eines Verkäufers, der abzurechnende Betrag für diesen für den aktuellen Monat zurückgegeben wird.
    //Nochmal überprüfen bei daten status = false.... kam zu einem Fehler -> hat bei mir funktioniert
    @Query("Select SUM(c.sum) *0.02 from Creditcardtransaction c where year(c.tdate) = year(CURRENT_DATE) AND month(c.tdate) = month(CURRENT_DATE) and c.status=true and c.vendor.vUuid = ?1")
    Double findAllByVendorandCurrentMonth(UUID vUuid);

    //Aufgabe 1.2 neu
    @Query("Select sum(c.sum)* 0.02 from Creditcardtransaction c where month(c.tdate) = :param1 and year(c.tdate) = :param2 and c.status=true")
    Double findSumOfAllTransactionsByDay(@Param("param1")int month, @Param("param2")int year);

    //Aufgabe 1.2 als abgeleitete Abfrage
 //   List<Creditcardtransaction> findSumByTdateBetweenAndVendorvuuidAndStatusIsTrue(LocalDate date1, LocalDate date2, UUID uuid);

    // für Aufgabe 1.5 - Einnahmen des aktuellen Monats
    @Query("Select SUM(c.sum)*0.02 from Creditcardtransaction c where month(c.tdate) = :param1 and year(c.tdate) = :param2 and c.status=true and c.tdate <= CURRENT_TIMESTAMP ")
    Double findSumOfAllTransactionsByMonth(@Param("param1")int month, @Param("param2")int year);

    // für Aufgabe 1.5 - Einnahmen des Vormonats
    @Query("Select SUM(c.sum)*0.02 from Creditcardtransaction c where month(c.tdate) = :param1 and year(c.tdate) = :param2 and c.status=true and c.tdate <= :param3")
    Double findSumOfAllTransactionsByPreviousMonth3(@Param("param1")int month, @Param("param2")int year, @Param("param3") LocalDate date);

   // List<Creditcardtransaction>findTUuidByVendor_id(UUID uuid);
    List<Creditcardtransaction> findSumByVendor_vUuidAndStatusIsTrueAndTdateBetween(UUID uuid, LocalDate date1, LocalDate date2);
}
