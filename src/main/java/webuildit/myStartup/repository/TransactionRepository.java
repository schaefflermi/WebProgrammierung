package webuildit.myStartup.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import webuildit.myStartup.model.CreditCardTransaction;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<CreditCardTransaction, Long> {
    //List<TransactionRepository> findByCNameContaining(String substring);

}
