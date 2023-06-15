package br.com.gui.racha.api.controller;

import br.com.gui.racha.api.service.PosicaoService;
import br.com.gui.racha.model.entity.Posicao;
import br.com.gui.racha.model.input.PosicaoInput;
import br.com.gui.racha.model.output.PosicaoOutput;
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
@RequestMapping("/posicao")
public class PosicaoController {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private final PosicaoService posicaoService;

    @PostMapping
    public ResponseEntity<PosicaoOutput> save(@Valid @RequestBody PosicaoInput posicaoInput) {
        Posicao createdPosicao = posicaoService.save(posicaoInput);
        PosicaoOutput posicaoOutput = modelMapper.map(createdPosicao, PosicaoOutput.class);
        return ResponseEntity.ok(posicaoOutput);
    }

    @GetMapping
    public ResponseEntity<List<PosicaoOutput>> listAll() {
        List<Posicao> posicoes = posicaoService.listAll();
        List<PosicaoOutput> responseDTOS = posicoes.stream()
                .map(posicao -> modelMapper.map(posicao, PosicaoOutput.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(responseDTOS);
    }

    @GetMapping("/desc/{posicao}")
    public ResponseEntity<List<PosicaoOutput>> getAllPosicoes(@PathVariable String posicao) {
        List<Posicao> posicoes = posicaoService.getAllPosicoes(posicao);
        List<PosicaoOutput> responseDTOS = posicoes.stream()
                .map(pos -> modelMapper.map(pos, PosicaoOutput.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(responseDTOS);
    }


    @GetMapping("/desativado")
    public ResponseEntity<List<PosicaoOutput>> listAllPosicao() {
        List<Posicao> posicoes = posicaoService.listAllPosicao();
        List<PosicaoOutput> responseDTOS = posicoes.stream()
                .map(posicao -> modelMapper.map(posicao, PosicaoOutput.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(responseDTOS);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PosicaoOutput> getById(@PathVariable Long id) {
        Posicao posicao = posicaoService.findById(id);
        PosicaoOutput posicaoOutput = modelMapper.map(posicao, PosicaoOutput.class);
        return ResponseEntity.ok(posicaoOutput);
    }

    @GetMapping("/desativado/{id}")
    public ResponseEntity<PosicaoOutput> getByIdDesactived(@PathVariable Long id) {
        Posicao posicao = posicaoService.findByIdDesactived(id);
        PosicaoOutput posicaoOutput = modelMapper.map(posicao, PosicaoOutput.class);
        return ResponseEntity.ok(posicaoOutput);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PosicaoOutput> updateById(@PathVariable Long id, @RequestBody @Valid PosicaoInput posicaoInput) {
        Posicao updatedPosicao = posicaoService.updateById(id, posicaoInput);
        PosicaoOutput posicaoOutput = modelMapper.map(updatedPosicao, PosicaoOutput.class);
        return ResponseEntity.ok(posicaoOutput);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<PosicaoOutput> deactivateById(@PathVariable Long id) {
        Posicao deactivatedPosicao = posicaoService.deactivateById(id);
        PosicaoOutput posicaoOutput = modelMapper.map(deactivatedPosicao, PosicaoOutput.class);
        return ResponseEntity.ok(posicaoOutput);
    }
}
