package br.com.gui.racha.api.controller;

import br.com.gui.racha.api.service.QuadraService;
import br.com.gui.racha.model.entity.Quadra;
import br.com.gui.racha.model.input.QuadraInput;
import br.com.gui.racha.model.output.QuadraOutput;
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
@RequestMapping("/quadra")
public class QuadraController {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private final QuadraService quadraService;

    @PostMapping
    public ResponseEntity<QuadraOutput> save(@Valid @RequestBody QuadraInput quadraInput) {
        Quadra createdQuadra = quadraService.save(quadraInput);
        QuadraOutput quadraOutput = new QuadraOutput(createdQuadra);
        return ResponseEntity.ok(quadraOutput);
    }

    @GetMapping
    public ResponseEntity<List<QuadraOutput>> listAll() {
        List<Quadra> quadras = quadraService.listAll();
        List<QuadraOutput> responseDTOS = quadras.stream()
                .map(QuadraOutput::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responseDTOS);
    }

    @GetMapping("/desativado")
    public ResponseEntity<List<QuadraOutput>> listAllQuadra() {
        List<Quadra> quadras = quadraService.listAllQuadra();
        List<QuadraOutput> responseDTOS = quadras.stream()
                .map(QuadraOutput::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responseDTOS);
    }

    @GetMapping("/{id}")
    public ResponseEntity<QuadraOutput> getById(@PathVariable Long id) {
        Quadra quadra = quadraService.findById(id);
        QuadraOutput quadraOutput = new QuadraOutput(quadra);
        return ResponseEntity.ok(quadraOutput);
    }

    @GetMapping("/desativado/{id}")
    public ResponseEntity<QuadraOutput> getByIdDesactived(@PathVariable Long id) {
        Quadra quadra = quadraService.findByIdDesactived(id);
        QuadraOutput quadraOutput = new QuadraOutput(quadra);
        return ResponseEntity.ok(quadraOutput);
    }

    @PutMapping("/{id}")
    public ResponseEntity<QuadraOutput> updateById(@PathVariable Long id, @RequestBody @Valid QuadraInput quadraInput) {
        Quadra updatedQuadra = quadraService.updateById(id, quadraInput);
        QuadraOutput quadraOutput = new QuadraOutput(updatedQuadra);
        return ResponseEntity.ok(quadraOutput);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<QuadraOutput> deactivateById(@PathVariable Long id) {
        Quadra deactivatedQuadra = quadraService.deactivateById(id);
        QuadraOutput quadraOutput = new QuadraOutput(deactivatedQuadra);
        return ResponseEntity.ok(quadraOutput);
    }
}
