package br.com.gui.racha.api.controller;

import br.com.gui.racha.api.service.RachaService;
import br.com.gui.racha.model.entity.Racha;
import br.com.gui.racha.model.input.RachaInput;
import br.com.gui.racha.model.output.RachaOutput;
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
@RequestMapping("/racha")
public class RachaController {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private final RachaService rachaService;

    @PostMapping
    public ResponseEntity<RachaOutput> save(@Valid @RequestBody RachaInput rachaInput) {
        Racha createdRacha = rachaService.save(rachaInput);
        RachaOutput rachaOutput = modelMapper.map(createdRacha, RachaOutput.class);
        return ResponseEntity.ok(rachaOutput);
    }

    @GetMapping
    public ResponseEntity<List<RachaOutput>> listAll(){
        List<Racha> rachas = rachaService.listAll();
        List<RachaOutput> responseDTOS = rachas.stream()
                .map(racha -> modelMapper.map(racha, RachaOutput.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(responseDTOS);
    }

    @GetMapping("/all")
    public ResponseEntity<List<RachaOutput>> listAllRacha(){
        List<Racha> rachas = rachaService.listAllRacha();
        List<RachaOutput> responseDTOS = rachas.stream()
                .map(racha -> modelMapper.map(racha, RachaOutput.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(responseDTOS);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RachaOutput> getById(@PathVariable Long id) {
        Racha racha = rachaService.findById(id);
        RachaOutput rachaOutput = modelMapper.map(racha, RachaOutput.class);
        return ResponseEntity.ok(rachaOutput);
    }

    @GetMapping("/desativado/{id}")
    public ResponseEntity<RachaOutput> getByIdDesactived(@PathVariable Long id) {
        Racha racha = rachaService.findByIdDesactived(id);
        RachaOutput rachaOutput = modelMapper.map(racha, RachaOutput.class);
        return ResponseEntity.ok(rachaOutput);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RachaOutput> updateById(@PathVariable Long id, @RequestBody @Valid RachaInput rachaInput) {
        Racha updatedRacha = rachaService.updateById(id, rachaInput);
        RachaOutput rachaOutput = modelMapper.map(updatedRacha, RachaOutput.class);
        return ResponseEntity.ok(rachaOutput);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<RachaOutput> deactivateById(@PathVariable Long id) {
        Racha deactivatedRacha = rachaService.deactivateById(id);
        RachaOutput rachaOutput = modelMapper.map(deactivatedRacha, RachaOutput.class);
        return ResponseEntity.ok(rachaOutput);
    }
}
