package daniel.silva.picpaysimplificado.dtos.user;

import daniel.silva.picpaysimplificado.enuns.UserType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.br.CPF;
import java.math.BigDecimal;

public record UserCreateRecord(

        @NotBlank(message = "Não pode está em branco.")
        @Size(min = 15, max = 50, message = "Tamanho min = 15 e max = 50")
        String username,

        @NotBlank(message = "Não pode está em branco.")
        @CPF
        String CPF,

        @NotBlank
        @Email(message = "Formato do Email inválido!", regexp = "^[a-z0-9.+-]+@[a-z0-9.-]+\\.[a-z]{2,}$")
        String email,

        @NotBlank(message = "Não pode está em branco.")
        @Size(min = 6, max = 6, message = "Tamanho min = 6 e max = 6")
        String password,

        @Enumerated
        UserType userType,

        @NotNull
        BigDecimal balance
) {
}
