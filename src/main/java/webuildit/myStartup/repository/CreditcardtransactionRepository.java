package webuildit.myStartup.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import webuildit.myStartup.model.Creditcardtransaction;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Repository
public interface CreditcardtransactionRepository extends JpaRepository<Creditcardtransaction, UUID> {

    //Aufgabe 1.1 :Bei Eingabe der Id eines Verkäufers, der abzurechnende Betrag für diesen für den aktuellen Monat zurückgegeben wird.
    List<Creditcardtransaction> findSumByVendor_vUuidAndStatusIsTrueAndTdateBetween(UUID uuid, LocalDate date1, LocalDate date2);

}
