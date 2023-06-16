package br.com.gui.racha.api.controller;

import br.com.gui.racha.api.service.JogadorService;
import br.com.gui.racha.model.entity.Jogador;
import br.com.gui.racha.model.input.JogadorInput;
import br.com.gui.racha.model.output.JogadorOutput;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/jogador")
public class JogadorController {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private final JogadorService jogadorService;

    @PostMapping
    public ResponseEntity<?> save(@Valid @RequestBody JogadorInput jogadorInput) {
        System.out.println(jogadorInput);
        Optional<Jogador> findJogador = jogadorService.findByUser(jogadorInput.getUser());
        System.out.println(findJogador);
        if(findJogador.isPresent()){
            if(jogadorService.findByUser(jogadorInput.getUser()).get().getActived()){
                return new ResponseEntity<String>("Jogador já cadastrado", HttpStatus.BAD_REQUEST);
            }else{
                jogadorService.updateById(findJogador.get().getId(), jogadorInput);
                return ResponseEntity.ok(new JogadorOutput(findJogador.get()));
            }
        }else{
            Jogador createdJogador = jogadorService.save(jogadorInput);
            JogadorOutput jogadorOutput = new JogadorOutput(createdJogador);
            return ResponseEntity.ok(jogadorOutput);
        }
    }

    @GetMapping
    public ResponseEntity<List<JogadorOutput>> listAll() {
        List<Jogador> users = jogadorService.listAll();
        List<JogadorOutput> responseDTOS =
                users.stream()
                        .map(JogadorOutput::new)
                        .collect(Collectors.toList());
        return ResponseEntity.ok(responseDTOS);
    }

    @GetMapping("/desativado")
    public ResponseEntity<List<JogadorOutput>> listAllJogador() {
        List<Jogador> users = jogadorService.listAllJogador();
        List<JogadorOutput> responseDTOS = users.stream()
                .map(JogadorOutput::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responseDTOS);
    }

    @GetMapping("/{id}")
    public ResponseEntity<JogadorOutput> getById(@PathVariable Long id) {
        Jogador jogador = jogadorService.findById(id);
        JogadorOutput jogadorOutput = new JogadorOutput(jogador);
        return ResponseEntity.ok(jogadorOutput);
    }

    @GetMapping("/usuario/{id}")
    public ResponseEntity<?> getByUsuario(@PathVariable Long id) {
        Jogador jogador = jogadorService.findByUser(id).get();
        if (jogador == null) {
            return new ResponseEntity<String>("Jogador não encontrado", HttpStatus.BAD_REQUEST);
        } else {
            JogadorOutput jogadorOutput = new JogadorOutput(jogador);
            return ResponseEntity.ok(jogadorOutput);
        }
    }

    @GetMapping("/desativado/{id}")
    public ResponseEntity<JogadorOutput> getByIdDesactived(@PathVariable Long id) {
        Jogador jogador = jogadorService.findByIdDesactived(id);
        JogadorOutput jogadorOutput = new JogadorOutput(jogador);
        return ResponseEntity.ok(jogadorOutput);
    }

    @PutMapping("/{id}")
    public ResponseEntity<JogadorOutput> updateById(@PathVariable Long id, @RequestBody @Valid JogadorInput jogadorInput) {
        Jogador updatedJogador = jogadorService.updateById(id, jogadorInput);
        JogadorOutput jogadorOutput = new JogadorOutput(updatedJogador);
        return ResponseEntity.ok(jogadorOutput);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<JogadorOutput> deactivateById(@PathVariable Long id) {
        Jogador deactivatedJogador = jogadorService.deactivateById(id);
        JogadorOutput jogadorOutput = new JogadorOutput(deactivatedJogador);
        return ResponseEntity.ok(jogadorOutput);
    }
}
