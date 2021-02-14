package webuildit.myStartup.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import webuildit.myStartup.model.Creditcardtransaction;
import webuildit.myStartup.model.Customer;

import java.util.List;
import java.util.UUID;

public interface CustomerRepository extends JpaRepository<Customer, UUID> {
    @Query("SELECT distinct c FROM Creditcardtransaction c, Customer d  where  c.status = false ")
    List<Customer> findDistinctBycUuid();
    //Aufgabe 1.4: alle Kunden mit mindestens 5 erfolglosen Transaktionen in dem Monat
    @Query("SELECT distinct d.cName FROM Creditcardtransaction c, Customer d where c.status = false and c.customer = d.cUuid  and month(c.tdate) = ?1 group by d.cName having count(c.status) > 4")
    List<String> findAllCustomerWithFiveFailedTransaction(int month);
}
