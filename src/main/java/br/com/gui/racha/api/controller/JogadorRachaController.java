package br.com.gui.racha.api.controller;

import br.com.gui.racha.api.service.JogadorRachaService;
import br.com.gui.racha.model.entity.Jogador;
import br.com.gui.racha.model.entity.JogadorRacha;
import br.com.gui.racha.model.input.JogadorRachaInput;
import br.com.gui.racha.model.output.JogadorOutput;
import br.com.gui.racha.model.output.JogadorRachaOutput;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/jogadorRacha")
public class JogadorRachaController {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private final JogadorRachaService jogadorRachaService;

    @PostMapping
    public ResponseEntity<JogadorRachaOutput> save(@Valid @RequestBody JogadorRachaInput jogadorRachaInput) {
        JogadorRacha createdJogadorRacha = jogadorRachaService.save(jogadorRachaInput);
        JogadorRachaOutput jogadorRachaOutput = new JogadorRachaOutput(createdJogadorRacha);
        return ResponseEntity.ok(jogadorRachaOutput);
    }

    @GetMapping
    public ResponseEntity<List<JogadorRachaOutput>> listAll() {
        List<JogadorRacha> jogadores = jogadorRachaService.listAll();
        List<JogadorRachaOutput> responseDTOS = jogadores.stream()
                .map(JogadorRachaOutput::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responseDTOS);
    }

    @GetMapping("/desativado")
    public ResponseEntity<List<JogadorRachaOutput>> listAllPosicao() {
        List<JogadorRacha> jogadores = jogadorRachaService.listAllJogadorRacha();
        List<JogadorRachaOutput> responseDTOS = jogadores.stream()
                .map(JogadorRachaOutput::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responseDTOS);
    }

    @GetMapping("/{id}")
    public ResponseEntity<JogadorRachaOutput> getById(@PathVariable Long id) {
        JogadorRacha jogadoracha = jogadorRachaService.findById(id);
        JogadorRachaOutput jogadorRachaOutput = new JogadorRachaOutput(jogadoracha);
        return ResponseEntity.ok(jogadorRachaOutput);
    }

    @GetMapping("/usuario/{id}")
    public ResponseEntity<List<?>> getByUsuario(@PathVariable Long id) {
        List<JogadorRacha> jogadores = jogadorRachaService.findByUser(id);

        List<JogadorRachaOutput> responseDTOS = jogadores.stream()
                .map(JogadorRachaOutput::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responseDTOS);
    }


    @GetMapping("/desativado/{id}")
    public ResponseEntity<JogadorRachaOutput> getByIdDesactived(@PathVariable Long id) {
        JogadorRacha jogadoracha = jogadorRachaService.findByIdDesactived(id);
        JogadorRachaOutput jogadorRachaOutput = new JogadorRachaOutput(jogadoracha);
        return ResponseEntity.ok(jogadorRachaOutput);
    }

    @PutMapping("/{id}")
    public ResponseEntity<JogadorRachaOutput> updateById(@PathVariable Long id, @RequestBody @Valid JogadorRachaInput jogadorRachaInput) {
        JogadorRacha updatedJogadorRacha = jogadorRachaService.updateById(id, jogadorRachaInput);
        JogadorRachaOutput jogadorRachaOutput = new JogadorRachaOutput(updatedJogadorRacha);
        return ResponseEntity.ok(jogadorRachaOutput);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<JogadorRachaOutput> deactivateById(@PathVariable Long id) {
        JogadorRacha deactivatedJogadorRacha = jogadorRachaService.deactivateById(id);
        JogadorRachaOutput jogadorRachaOutput = new JogadorRachaOutput(deactivatedJogadorRacha);
        return ResponseEntity.ok(jogadorRachaOutput);
    }
}
