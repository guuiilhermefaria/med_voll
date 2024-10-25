package br.com.med.voll.api.controller;

import br.com.med.voll.api.model.medico.DadosDetalhamentoMedico;
import br.com.med.voll.api.model.paciente.*;
import br.com.med.voll.api.repository.PacienteRepository;
import br.com.med.voll.api.service.PacienteService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("pacientes")
public class PacienteController {

    @Autowired
    private PacienteRepository repository;

    @Autowired
    private PacienteService service;

    @PostMapping
    @Transactional
    public ResponseEntity<DadosDetalhamentoPaciente> cadastrar(@RequestBody @Valid DadosCadastroPaciente dados, UriComponentsBuilder uriBuilder) {
        var paciente = service.cadastrar(dados);

        var uri = uriBuilder.path("/pacientes/{id}").buildAndExpand(paciente.getId()).toUri();

        return ResponseEntity.created(uri).body(new DadosDetalhamentoPaciente(paciente));
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemPaciente>> listarTodosPacientes(@PageableDefault(page = 0, size = 10, sort = {"nome"}) Pageable paginacao) { // (17,4)-(19,5)Page<DadosListagemPaciente> listarTodosPacientes(@PageableDefault(page = 0, size = 10, sort = {"nome"}) Pageable paginacao) {
        Page<DadosListagemPaciente> dadosListagemPacientes = service.listarTodosPacientes(paginacao);

        return ResponseEntity.ok(dadosListagemPacientes);
    }

    @PutMapping
    @Transactional
    public ResponseEntity<DadosDetalhamentoPaciente> atualizar(@RequestBody @Valid DadosAtualizacaoPaciente dados) {
        Paciente paciente = service.atualizar(dados);

        return ResponseEntity.ok(new DadosDetalhamentoPaciente(paciente));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        service.remover(id);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DadosDetalhamentoPaciente> detalhar(@PathVariable Long id) {
        Paciente paciente = service.readById(id);

        return ResponseEntity.ok(new DadosDetalhamentoPaciente(paciente));
    }
}
