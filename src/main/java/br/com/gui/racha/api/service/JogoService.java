package br.com.gui.racha.api.service;

import br.com.gui.racha.model.entity.Jogo;
import br.com.gui.racha.model.input.JogoInput;
import br.com.gui.racha.model.repository.JogoRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JogoService {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private final JogoRepository jogoRepository;
    @Autowired
    private final RachaService rachaService;

    public Jogo save(JogoInput jogoInput) {
        Jogo jogo = modelMapper.map(jogoInput, Jogo.class);
        jogo.setRacha(rachaService.findById(jogoInput.getRacha()));
        return jogoRepository.save(jogo);
    }

    public List<Jogo> listAll() {
        return jogoRepository.findAll();
    }

    public Jogo findById(Long id) {
        return jogoRepository.findById(id).orElseThrow(() -> new RuntimeException("Jogo não encontrada"));
    }

    public Jogo updateById(Long id, JogoInput jogoInput) {
        Jogo jogo = findById(id);
        jogo.setData(jogoInput.getData());
        jogo.setRacha(rachaService.findById(jogoInput.getRacha()));
        jogo.setValorPago(jogoInput.getValorPago());
        return jogoRepository.save(jogo);
    }

    public Jogo deactivateById(Long id) {
        Jogo jogo = findById(id);
        jogo.setActived(false);
        return jogoRepository.save(jogo);
    }

    public List<Jogo> listAllJogo() {
        return jogoRepository.findAll();
    }

    public Jogo findByIdDesactived(Long id) {
        return jogoRepository.findByIdDesactived(id).orElseThrow(() -> new RuntimeException("Jogo não encontrada"));
    }
}
