package br.com.gui.racha.api.service;

import br.com.gui.racha.model.entity.Quadra;
import br.com.gui.racha.model.entity.Racha;
import br.com.gui.racha.model.input.RachaInput;
import br.com.gui.racha.model.input.RachaInput;
import br.com.gui.racha.model.repository.RachaRepository;
import ch.qos.logback.core.net.SyslogOutputStream;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RachaService {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private final RachaRepository rachaRepository;

    @Autowired
    private final QuadraService quadraService;

    public Racha save(RachaInput rachaInput) {
        Racha racha = modelMapper.map(rachaInput, Racha.class);
        Quadra quadra = quadraService.findById(rachaInput.getQuadra());
        racha.setQuadra(quadra);
        System.out.println(racha);
        return rachaRepository.save(racha);
    }

    public List<Racha> listAll() {
        return rachaRepository.findAll();
    }

    public Racha findById(Long id) {
        return rachaRepository.findById(id).orElseThrow(() -> new RuntimeException("Racha não encontrada"));
    }

    public Racha updateById(Long id, RachaInput rachaInput) {
        Racha racha = findById(id);
        racha.setNome(rachaInput.getNome());
        Quadra quadra = quadraService.findById(rachaInput.getQuadra());
        racha.setQuadra(quadra);
        racha.setCaixa(rachaInput.getCaixa());
        return rachaRepository.save(racha);
    }

    public Racha deactivateById(Long id) {
        Racha racha = findById(id);
        racha.setActived(false);
        return rachaRepository.save(racha);
    }

    public List<Racha> listAllRacha() {
        return rachaRepository.findAllQuadra();
    }

    public Racha findByIdDesactived(Long id) {
        return rachaRepository.findByIdDesactived(id).orElseThrow(() -> new RuntimeException("Racha não encontrada"));
    }
}
