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
    @Query("SELECT distinct d FROM Creditcardtransaction c, Customer d where c.status = false and c.customer = d.cUuid group by d.cName having count(c.status) > 4")
    List<Customer> findAllWhereStatusFalse2();
}
