package br.com.med.voll.api.controller;

import br.com.med.voll.api.infra.security.TokenService;
import br.com.med.voll.api.model.token.DadosTokenJWT;
import br.com.med.voll.api.model.usuario.DadosAutenticacao;
import br.com.med.voll.api.model.usuario.Usuario;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AutenticacaoController {
    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity autenticar(@RequestBody @Valid DadosAutenticacao dados) {
        var autenticationToken = new UsernamePasswordAuthenticationToken(dados.login(), dados.senha());
        var authenticate = manager.authenticate(autenticationToken);

        String tokenJWT = tokenService.gerarToken((Usuario) authenticate.getPrincipal());
        return ResponseEntity.ok(new DadosTokenJWT(tokenJWT));
    }
}
