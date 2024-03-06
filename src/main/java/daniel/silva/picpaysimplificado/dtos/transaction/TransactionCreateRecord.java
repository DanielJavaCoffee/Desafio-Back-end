package daniel.silva.picpaysimplificado.dtos.transaction;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record TransactionCreateRecord(

        @NotNull(message = "Não pode está em branco.")
        Long id_payer,

        @NotNull(message = "Não pode está em branco.")
        Long id_payee,

        @NotNull(message = "Informe o valor")
        BigDecimal value
) {
}
