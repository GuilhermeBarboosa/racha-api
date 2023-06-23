package br.com.gui.racha.api.controller;

import br.com.gui.racha.api.service.JogadorRachaService;
import br.com.gui.racha.model.entity.JogadorRacha;
import br.com.gui.racha.model.input.JogadorRachaInput;
import br.com.gui.racha.model.output.JogadorRachaOutput;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
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
    public HttpEntity<? extends Object> save(@Valid @RequestBody List<JogadorRachaInput> jogadorRachaInput) {
        for(int i=0; i<jogadorRachaInput.size(); i++){
            System.out.println(jogadorRachaInput.get(i).getJogador());
            Optional<JogadorRacha> findJogador = jogadorRachaService.findByIdJogador(jogadorRachaInput.get(i).getJogador());
            if(findJogador.isPresent() && findJogador.get().getActived()){
                return new ResponseEntity<String>("Jogador " + findJogador.get().getJogador().getUser().getNome() + " já registrado", HttpStatus.BAD_REQUEST);
            }else if(findJogador.isPresent() && !findJogador.get().getActived()){
                jogadorRachaService.updateById(findJogador.get().getId(),  jogadorRachaInput.get(i));
            }
        }

        List<JogadorRacha> createdJogadorRacha = jogadorRachaService.save(jogadorRachaInput);
        List<JogadorRachaOutput> jogadorRachaOutput = createdJogadorRacha.stream()
                .map(JogadorRachaOutput::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(jogadorRachaOutput);
    }

    @GetMapping
    public ResponseEntity<List<JogadorRachaOutput>> listAll() {
        List<JogadorRacha> arrayJogadorRacha = jogadorRachaService.listAll();
        List<JogadorRachaOutput> responseDTOS = arrayJogadorRacha.stream()
                .map(JogadorRachaOutput::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responseDTOS);
    }

    @GetMapping("/desativado")
    public ResponseEntity<List<JogadorRachaOutput>> listAllPosicao() {
        List<JogadorRacha> arrayJogadorRacha = jogadorRachaService.listAllJogadorRacha();
        List<JogadorRachaOutput> responseDTOS = arrayJogadorRacha.stream()
                .map(JogadorRachaOutput::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responseDTOS);
    }

    @GetMapping("/{id}")
    public ResponseEntity<JogadorRachaOutput> getById(@PathVariable Long id) {
        JogadorRacha findJogadoRacha = jogadorRachaService.findById(id);
        JogadorRachaOutput jogadorRachaOutput = new JogadorRachaOutput(findJogadoRacha);
        return ResponseEntity.ok(jogadorRachaOutput);
    }

    @GetMapping("/usuario/{id}")
    public ResponseEntity<List<?>> getByUsuario(@PathVariable Long id) {
        List<JogadorRacha> arrayJogadorRacha = jogadorRachaService.findByUser(id);

        List<JogadorRachaOutput> responseDTOS = arrayJogadorRacha.stream()
                .map(JogadorRachaOutput::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responseDTOS);
    }

    @GetMapping("/racha/{id}")
    public ResponseEntity<List<?>> getByQuadra(@PathVariable Long id) {
        List<JogadorRacha> arrayJogadorRacha = jogadorRachaService.findByIdRacha(id);

        List<JogadorRachaOutput> responseDTOS = arrayJogadorRacha.stream()
                .map(JogadorRachaOutput::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responseDTOS);
    }


    @GetMapping("/desativado/{id}")
    public ResponseEntity<JogadorRachaOutput> getByIdDesactived(@PathVariable Long id) {
        JogadorRacha desactivedJogadoRacha = jogadorRachaService.findByIdDesactived(id);
        JogadorRachaOutput jogadorRachaOutput = new JogadorRachaOutput(desactivedJogadoRacha);
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
