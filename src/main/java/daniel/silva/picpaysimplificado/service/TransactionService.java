package daniel.silva.picpaysimplificado.service;

import daniel.silva.picpaysimplificado.dtos.transaction.TransactionCreateRecord;
import daniel.silva.picpaysimplificado.entity.Transaction;
import daniel.silva.picpaysimplificado.entity.User;
import daniel.silva.picpaysimplificado.enuns.UserType;
import daniel.silva.picpaysimplificado.repository.TransactionRepository;
import daniel.silva.picpaysimplificado.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final UserRepository userRepository;

    private final RestTemplate restTemplate;

    @Transactional
    public TransactionCreateRecord createTransaction(TransactionCreateRecord transactionCreateRecord) throws Exception {
        try {
            Optional<User> userPayer = Optional.ofNullable(userRepository.findById(transactionCreateRecord.id_payer()).orElseThrow(() -> new Exception("User Payer não encontrado")));
            Optional<User> userPayee = Optional.ofNullable(userRepository.findById(transactionCreateRecord.id_payee()).orElseThrow(() -> new Exception("User Payee não encontrado")));

            var user_payer = userPayer.get();
            var user_payee = userPayee.get();

            if (user_payer.getUserType().equals(UserType.MERCHANT)) {
                throw new Exception("Pagador do tipo logista não pode fazer operações");
            }
            if (user_payer.getBalance().compareTo(transactionCreateRecord.value()) < 0) {
                throw new Exception("O Valor da transação é maior que o saldo atual da conta.");
            }

            boolean isAuthorize = authorizeTransaction();
            if (!isAuthorize){
                throw new Exception("Transação não autorizada!");
            }

            var transaction = new Transaction();
            transaction.setDateTransaction(LocalDateTime.now());
            transaction.setPayer(user_payer);
            transaction.setPayee(user_payee);
            transaction.setAmount(transactionCreateRecord.value());
            user_payer.setBalance(user_payer.getBalance().subtract(transactionCreateRecord.value()));
            user_payee.setBalance(user_payee.getBalance().add(transactionCreateRecord.value()));

            userRepository.save(user_payer);
            userRepository.save(user_payee);
            transactionRepository.save(transaction);
            return transactionCreateRecord;
        } catch (RuntimeException exception) {
        throw new RuntimeException("Erro durante a criação da transação", exception);
    }
}

    public boolean authorizeTransaction() {
        var response = restTemplate.getForEntity("https://run.mocky.io/v3/5794d450-d2e2-4412-8131-73d0293ac1cc", Map.class);
        if(response.getStatusCode().equals(HttpStatus.OK)){
            String message = (String) response.getBody().get("message");
            return "Autorizado".equalsIgnoreCase(message);
        }
        return false;
    }
}

