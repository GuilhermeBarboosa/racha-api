package br.com.gui.racha.api.controller;

import br.com.gui.racha.api.service.JogadorService;
import br.com.gui.racha.model.entity.Jogador;
import br.com.gui.racha.model.input.JogadorInput;
import br.com.gui.racha.model.output.JogadorOutput;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
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
    public ResponseEntity<JogadorOutput> save(@Valid @RequestBody JogadorInput jogadorInput) {
        Jogador createdJogador = jogadorService.save(jogadorInput);
        JogadorOutput rachaOutput = modelMapper.map(createdJogador, JogadorOutput.class);
        return ResponseEntity.ok(rachaOutput);
    }

    @GetMapping
    public ResponseEntity<List<JogadorOutput>> listAll(){
        List<Jogador> users = jogadorService.listAll();
        List<JogadorOutput> responseDTOS = users.stream()
                .map(jogador -> modelMapper.map(jogador, JogadorOutput.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(responseDTOS);
    }

    @GetMapping("/all")
    public ResponseEntity<List<JogadorOutput>> listAllPosicao(){
        List<Jogador> users = jogadorService.listAllPosicao();
        List<JogadorOutput> responseDTOS = users.stream()
                .map(jogador -> modelMapper.map(jogador, JogadorOutput.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(responseDTOS);
    }

    @GetMapping("/{id}")
    public ResponseEntity<JogadorOutput> getById(@PathVariable Long id) {
        Jogador jogador = jogadorService.findById(id);
        JogadorOutput rachaOutput = modelMapper.map(jogador, JogadorOutput.class);
        return ResponseEntity.ok(rachaOutput);
    }

    @GetMapping("/desativado/{id}")
    public ResponseEntity<JogadorOutput> getByIdDesactived(@PathVariable Long id) {
        Jogador jogador = jogadorService.findByIdDesactived(id);
        JogadorOutput rachaOutput = modelMapper.map(jogador, JogadorOutput.class);
        return ResponseEntity.ok(rachaOutput);
    }

    @PutMapping("/{id}")
    public ResponseEntity<JogadorOutput> updateById(@PathVariable Long id, @RequestBody @Valid JogadorInput jogadorInput) {
        Jogador updatedPosicao = jogadorService.updateById(id, jogadorInput);
        JogadorOutput rachaOutput = modelMapper.map(updatedPosicao, JogadorOutput.class);
        return ResponseEntity.ok(rachaOutput);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<JogadorOutput> deactivateById(@PathVariable Long id) {
        Jogador deactivatedPosicao = jogadorService.deactivateById(id);
        JogadorOutput rachaOutput = modelMapper.map(deactivatedPosicao, JogadorOutput.class);
        return ResponseEntity.ok(rachaOutput);
    }
}
