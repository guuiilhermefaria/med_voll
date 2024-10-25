package br.com.med.voll.api.model.paciente;

import br.com.med.voll.api.model.endereco.DadosEndereco;
import jakarta.validation.Valid;

public record DadosAtualizacaoPaciente(Long id,
                                       String nome,
                                       String telefone,
                                       @Valid DadosEndereco endereco) {
}
