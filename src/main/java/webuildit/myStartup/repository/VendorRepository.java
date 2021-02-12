package webuildit.myStartup.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import webuildit.myStartup.model.Classification;
import webuildit.myStartup.model.Creditcardtransaction;
import webuildit.myStartup.model.Vendor;

import java.util.List;
import java.util.UUID;

@Repository
public interface VendorRepository extends JpaRepository<Vendor, UUID> {
    List<Vendor> findByClassification(Classification classification);
    List<Vendor> findAll();
   // List<Vendor>findCreditcardtransactionsByvUuid(UUID vUuid);
    @Query("SELECT v FROM Creditcardtransaction c, Vendor v where  c.vendor.vUuid = v.vUuid")
    List<Vendor> findAllDistinctByClassification();

    //Aufgabe 1.3 Alle gewerbe mit Summe sortiert DESC
    //!!!!!!!!Limiertung auf 3 Ergebnisse noch nicht implementiert!!!!!!!!
    @Query("SELECT v.classification, sum(c.sum) as Summe FROM Creditcardtransaction c, Vendor v where  c.vendor = v.vUuid and c.status = true group by v.classification order by Summe desc")
    List<String> findTop1Desc();
    //Aufgabe 1.3 Alle gewerbe mit Summe sortiert ASC
    @Query("SELECT v.classification, sum(c.sum) as Summe FROM Creditcardtransaction c, Vendor v where  c.vendor = v.vUuid and c.status = true group by v.classification order by Summe asc")
    List<String> findTop1Asc();
}
