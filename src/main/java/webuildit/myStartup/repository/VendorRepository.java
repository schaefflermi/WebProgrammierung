package webuildit.myStartup.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import webuildit.myStartup.model.Classification;
import webuildit.myStartup.model.Creditcardtransaction;
import webuildit.myStartup.model.Vendor;

import javax.persistence.NamedNativeQuery;
import java.util.List;
import java.util.UUID;

@Repository
public interface VendorRepository extends JpaRepository<Vendor, UUID> {
   //Aufgabe 1.3: 3 Gewerbe mit höchsten Umsätzen
    @Query(nativeQuery = true, value ="SELECT v.classification, sum(c.sum) as Summe FROM Creditcardtransaction c, Vendor v where c.vendor_id = v.Uuid and month(c.tdate) = :param1 and year(c.tdate) = :param2 and c.status = true group by v.classification order by Summe desc LIMIT 3")
    List<String> findTop3Desc(@Param("param1")int month, @Param("param2")int year);

    //Aufgabe 1.3: 3 Gewerbe mit niedrigsten Umsätzen
    @Query(nativeQuery = true, value ="SELECT v.classification, sum(c.sum) as Summe FROM Creditcardtransaction c, Vendor v where  c.vendor_id = v.Uuid and month(c.tdate) = :param1 and year(c.tdate) = :param2  and c.status = true  group by v.classification order by Summe asc LIMIT 3 ")
    List<String> findAllTop3Asc(@Param("param1")int month, @Param("param2")int year);

}
