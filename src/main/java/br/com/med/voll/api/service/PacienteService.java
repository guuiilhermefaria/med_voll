package br.com.med.voll.api.service;

import br.com.med.voll.api.model.paciente.DadosAtualizacaoPaciente;
import br.com.med.voll.api.model.paciente.DadosCadastroPaciente;
import br.com.med.voll.api.model.paciente.DadosListagemPaciente;
import br.com.med.voll.api.model.paciente.Paciente;
import br.com.med.voll.api.repository.PacienteRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PacienteService {
    @Autowired
    private PacienteRepository repository;

    public Paciente cadastrar(@Valid DadosCadastroPaciente dados) {
        return repository.save(new Paciente(dados));
    }

    public Page<DadosListagemPaciente> listarTodosPacientes(Pageable pageable) {
        return repository.findAllByAtivoTrue(pageable).map(DadosListagemPaciente::new);
    }

    public Paciente atualizar(DadosAtualizacaoPaciente dados) {
        var paciente = repository.getReferenceById(dados.id());
        paciente.atualizarInformacoes(dados);

        return paciente;
    }

    public void remover(Long id) {
        var paciente = repository.getReferenceById(id);
        paciente.inativar();
    }

    public Paciente readById(Long id) {
        return repository.getReferenceById(id);
    }
}
