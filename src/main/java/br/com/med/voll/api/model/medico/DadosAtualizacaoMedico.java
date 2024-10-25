package br.com.med.voll.api.model.medico;

import br.com.med.voll.api.model.endereco.DadosEndereco;
import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoMedico(@NotNull Long id,
                                     String nome,
                                     String telefone,
                                     DadosEndereco endereco) {
}
