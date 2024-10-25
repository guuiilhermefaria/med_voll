package br.com.med.voll.api.service;

import br.com.med.voll.api.model.medico.DadosAtualizacaoMedico;
import br.com.med.voll.api.model.medico.DadosCadastroMedico;
import br.com.med.voll.api.model.medico.DadosListagemMedico;
import br.com.med.voll.api.model.medico.Medico;
import br.com.med.voll.api.repository.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class MedicoService {
    @Autowired
    private MedicoRepository repository;

    public Medico cadastrar(DadosCadastroMedico dados) {
        var Medico = new Medico(dados);
        return repository.save(Medico);
    }

    public Page<DadosListagemMedico> listarTodosMedicos(Pageable pageable) {
        return repository.findAllByAtivoTrue(pageable).map(DadosListagemMedico::new);
    }

    public Medico atualizar(DadosAtualizacaoMedico dados) {
        var medico = repository.getReferenceById(dados.id());
        medico.atualizarInformacoes(dados);

        return medico;
    }

    public void excluir(Long id) {
        var medico = repository.getReferenceById(id);
        medico.excluir();
    }

    public Medico detalhar(Long id) {
        return repository.getReferenceById(id);
    }
}
