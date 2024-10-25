package br.com.med.voll.api.model.medico;

import br.com.med.voll.api.model.endereco.DadosEndereco;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record DadosCadastroMedico(@NotBlank(message = "O nome é obrigatório") String nome,
                                  @NotBlank(message = "Email é obrigatório") @Email(message = "Formato do email é inválido") String email,
                                  @NotBlank(message = "Telefone é obrigatório") String telefone,
                                  @NotBlank(message = "CRM é obrigatório") @Pattern(regexp = "\\d{4,6}", message = "Formato do CRM é inválido") String crm,
                                  @NotNull(message = "Especialidade é obrigatória") Especialidade especialidade,
                                  @NotNull(message = "Dados do endereço são obrigatórios") @Valid DadosEndereco endereco) {
}
