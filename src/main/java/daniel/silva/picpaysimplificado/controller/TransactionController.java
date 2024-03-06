package daniel.silva.picpaysimplificado.controller;

import daniel.silva.picpaysimplificado.dtos.transaction.TransactionCreateRecord;
import daniel.silva.picpaysimplificado.service.TransactionService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/transaction")
public class TransactionController {

    private final TransactionService transactionService;

    @PostMapping
    public ResponseEntity<TransactionCreateRecord> createTransaction(@RequestBody @Valid TransactionCreateRecord transactionCreateRecord) throws Exception {
        var transaction = transactionService.createTransaction(transactionCreateRecord);
        return ResponseEntity.status(HttpStatus.CREATED).body(transaction);
    }
}
