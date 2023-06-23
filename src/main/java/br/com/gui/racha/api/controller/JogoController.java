package br.com.gui.racha.api.controller;

import br.com.gui.racha.api.service.JogoService;
import br.com.gui.racha.model.entity.Jogo;
import br.com.gui.racha.model.input.JogoInput;
import br.com.gui.racha.model.output.JogoOutput;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/jogo")
public class JogoController {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private final JogoService jogoService;

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    @PostMapping
    public ResponseEntity<JogoOutput> save(@Valid @RequestBody JogoInput jogoInput) {
        Jogo createdJogo = jogoService.save(jogoInput);
        JogoOutput jogoOutput = new JogoOutput(createdJogo);
        return ResponseEntity.ok(jogoOutput);
    }

    @GetMapping
    public ResponseEntity<List<JogoOutput>> listAll() {
        List<Jogo> arrayJogos = jogoService.listAll();
        List<JogoOutput> responseDTOS = arrayJogos.stream()
                .map(JogoOutput::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responseDTOS);
    }

    @GetMapping("/desativado")
    public ResponseEntity<List<JogoOutput>> listAllPosicao() {
        List<Jogo> arrayJogos = jogoService.listAllJogo();
        List<JogoOutput> responseDTOS = arrayJogos.stream()
                .map(JogoOutput::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responseDTOS);
    }

    @GetMapping("/racha/{id}")
    public ResponseEntity<List<JogoOutput>> getByIdRacha(@PathVariable Long id) {
        List<Jogo> arrayJogos = jogoService.listAllByRachas(id);
        List<JogoOutput> responseDTOS = arrayJogos.stream()
                .map(JogoOutput::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responseDTOS);
    }


    @GetMapping("/{id}")
    public ResponseEntity<JogoOutput> getById(@PathVariable Long id) {
        Jogo jogo = jogoService.findById(id);
        JogoOutput quadraOutput = new JogoOutput(jogo);
        return ResponseEntity.ok(quadraOutput);
    }

    @GetMapping("/desativado/{id}")
    public ResponseEntity<JogoOutput> getByIdDesactived(@PathVariable Long id) {
        Jogo jogo = jogoService.findByIdDesactived(id);
        JogoOutput quadraOutput = new JogoOutput(jogo);
        return ResponseEntity.ok(quadraOutput);
    }

    @PutMapping("/{id}")
    public ResponseEntity<JogoOutput> updateById(@PathVariable Long id, @RequestBody @Valid JogoInput jogoInput) {
        Jogo updatedJogo = jogoService.updateById(id, jogoInput);
        JogoOutput quadraOutput = new JogoOutput(updatedJogo);
        return ResponseEntity.ok(quadraOutput);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<JogoOutput> deactivateById(@PathVariable Long id) {
        Jogo deactivatedjogo = jogoService.deactivateById(id);
        JogoOutput quadraOutput = new JogoOutput(deactivatedjogo);
        return ResponseEntity.ok(quadraOutput);
    }
}
