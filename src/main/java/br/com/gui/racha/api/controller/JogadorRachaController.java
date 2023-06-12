package br.com.gui.racha.api.controller;

import br.com.gui.racha.api.service.JogadorRachaService;
import br.com.gui.racha.model.entity.JogadorRacha;
import br.com.gui.racha.model.input.JogadorRachaInput;
import br.com.gui.racha.model.output.JogadorRachaOutput;
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
@RequestMapping("/jogadoracha")
public class JogadorRachaController {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private final JogadorRachaService jogadorRachaService;

    @PostMapping
    public ResponseEntity<JogadorRachaOutput> save(@Valid @RequestBody JogadorRachaInput jogadorRachaInput) {
        JogadorRacha createdJogadorRacha = jogadorRachaService.save(jogadorRachaInput);
        JogadorRachaOutput jogadorRachaOutput = modelMapper.map(createdJogadorRacha, JogadorRachaOutput.class);
        return ResponseEntity.ok(jogadorRachaOutput);
    }

    @GetMapping
    public ResponseEntity<List<JogadorRachaOutput>> listAll() {
        List<JogadorRacha> jogadores = jogadorRachaService.listAll();
        List<JogadorRachaOutput> responseDTOS = jogadores.stream()
                .map(jogadoracha -> modelMapper.map(jogadoracha, JogadorRachaOutput.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(responseDTOS);
    }

    @GetMapping("/desativado")
    public ResponseEntity<List<JogadorRachaOutput>> listAllPosicao() {
        List<JogadorRacha> jogadores = jogadorRachaService.listAllJogadorRacha();
        List<JogadorRachaOutput> responseDTOS = jogadores.stream()
                .map(jogadoracha -> modelMapper.map(jogadoracha, JogadorRachaOutput.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(responseDTOS);
    }

    @GetMapping("/{id}")
    public ResponseEntity<JogadorRachaOutput> getById(@PathVariable Long id) {
        JogadorRacha jogadoracha = jogadorRachaService.findById(id);
        JogadorRachaOutput jogadorRachaOutput = modelMapper.map(jogadoracha, JogadorRachaOutput.class);
        return ResponseEntity.ok(jogadorRachaOutput);
    }

    @GetMapping("/desativado/{id}")
    public ResponseEntity<JogadorRachaOutput> getByIdDesactived(@PathVariable Long id) {
        JogadorRacha jogadoracha = jogadorRachaService.findByIdDesactived(id);
        JogadorRachaOutput jogadorRachaOutput = modelMapper.map(jogadoracha, JogadorRachaOutput.class);
        return ResponseEntity.ok(jogadorRachaOutput);
    }

    @PutMapping("/{id}")
    public ResponseEntity<JogadorRachaOutput> updateById(@PathVariable Long id, @RequestBody @Valid JogadorRachaInput jogadorRachaInput) {
        JogadorRacha updatedJogadorRacha = jogadorRachaService.updateById(id, jogadorRachaInput);
        JogadorRachaOutput jogadorRachaOutput = modelMapper.map(updatedJogadorRacha, JogadorRachaOutput.class);
        return ResponseEntity.ok(jogadorRachaOutput);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<JogadorRachaOutput> deactivateById(@PathVariable Long id) {
        JogadorRacha deactivatedJogadorRacha = jogadorRachaService.deactivateById(id);
        JogadorRachaOutput jogadorRachaOutput = modelMapper.map(deactivatedJogadorRacha, JogadorRachaOutput.class);
        return ResponseEntity.ok(jogadorRachaOutput);
    }
}
