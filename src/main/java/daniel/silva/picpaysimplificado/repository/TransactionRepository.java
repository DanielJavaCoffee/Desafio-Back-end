package daniel.silva.picpaysimplificado.repository;

import daniel.silva.picpaysimplificado.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
