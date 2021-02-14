package webuildit.myStartup.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import webuildit.myStartup.model.Classification;
import webuildit.myStartup.model.Creditcardtransaction;
import webuildit.myStartup.model.Vendor;

import javax.persistence.NamedNativeQuery;
import java.util.List;
import java.util.UUID;

@Repository
public interface VendorRepository extends JpaRepository<Vendor, UUID> {
    List<Vendor> findByClassification(Classification classification);
    List<Vendor> findAll();
   // List<Vendor>findCreditcardtransactionsByvUuid(UUID vUuid);
    @Query("SELECT v FROM Creditcardtransaction c, Vendor v where  c.vendor.vUuid = v.vUuid")
    List<Vendor> findAllDistinctByClassification();

    //Aufgabe 1.3: 3 Gewerbe mit höchsten Umsätzen
    //!!!!!!!!Limiertung auf 3 Ergebnisse noch nicht implementiert!!!!!!!! -> limitierung mit rownum funktioniert (siehe unten), aber wird vor "desc" ausgeführt, daher falsches Ergebnis
    @Query("SELECT v.classification, sum(c.sum) as Summe FROM Creditcardtransaction c, Vendor v where  c.vendor = v.vUuid and c.status = true group by v.classification order by Summe desc ")
    List<String> findTop3Desc();

    //Aufgabe 1.3: 3 Gewerbe mit niedrigsten Umsätzen
    @Query("SELECT v.classification, sum(c.sum) as Summe FROM Creditcardtransaction c, Vendor v where  c.vendor = v.vUuid and c.status = true AND rownum <=3 group by v.classification order by Summe asc")
    List<String> findTop3Asc();
}
