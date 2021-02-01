package webuildit.myStartup.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import webuildit.myStartup.model.CreditCardTransaction;

import java.util.List;
import java.util.UUID;

@Repository
public interface TransactionRepository extends JpaRepository<CreditCardTransaction, Long> {
    List<TransactionRepository> findByCNameContaining(String substring);
    List<TransactionRepository> findAllById(long vId);

}
