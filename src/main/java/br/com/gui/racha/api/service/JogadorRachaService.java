package br.com.gui.racha.api.service;

import br.com.gui.racha.model.entity.JogadorRacha;
import br.com.gui.racha.model.input.JogadorRachaInput;
import br.com.gui.racha.model.repository.JogadorRachaRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JogadorRachaService {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private final JogadorRachaRepository jogadorRachaRepository;

    @Autowired
    private final RachaService rachaService;

    @Autowired
    private final JogadorService jogadorService;

    public JogadorRacha save(JogadorRachaInput jogadorRachaInput) {
        JogadorRacha jogadorRacha = modelMapper.map(jogadorRachaInput, JogadorRacha.class);
        jogadorRacha.setRacha(rachaService.findById(jogadorRachaInput.getRacha()));
        jogadorRacha.setJogador(jogadorService.findById(jogadorRachaInput.getJogador()));
        return jogadorRachaRepository.save(jogadorRacha);
    }

    public List<JogadorRacha> listAll() {
        return jogadorRachaRepository.findAll();
    }

    public JogadorRacha findById(Long id) {
        return jogadorRachaRepository.findById(id).orElseThrow(() -> new RuntimeException("Jogador Racha não encontrada"));
    }

    public JogadorRacha updateById(Long id, JogadorRachaInput jogadorRachaInput) {
        JogadorRacha jogadorRacha = findById(id);
        jogadorRacha.setJogador(jogadorService.findById(jogadorRachaInput.getJogador()));
        jogadorRacha.setRacha(rachaService.findById(jogadorRachaInput.getRacha()));
        return jogadorRachaRepository.save(jogadorRacha);
    }

    public JogadorRacha deactivateById(Long id) {
        JogadorRacha jogadorRacha = findById(id);
        jogadorRacha.setActived(false);
        return jogadorRachaRepository.save(jogadorRacha);
    }

    public List<JogadorRacha> listAllJogadorRacha() {
        return jogadorRachaRepository.findAllJogadorRacha();
    }

    public JogadorRacha findByIdDesactived(Long id) {
        return jogadorRachaRepository.findByIdDesactived(id).orElseThrow(() -> new RuntimeException("Jogador Racha não encontrada"));
    }

    public List<JogadorRacha> findByUser(Long id) {
        return jogadorRachaRepository.findByJogadorId(id);
    }
}
