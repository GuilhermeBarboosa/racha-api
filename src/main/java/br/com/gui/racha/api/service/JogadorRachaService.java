package br.com.gui.racha.api.service;

import br.com.gui.racha.model.entity.JogadorRacha;
import br.com.gui.racha.model.input.JogadorRachaInput;
import br.com.gui.racha.model.repository.JogadorRachaRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    public List<JogadorRacha> save(List<JogadorRachaInput> jogadorRachaInput) {
        List<JogadorRacha> saveJogadores = new ArrayList<>();
        for (int i=0; i < jogadorRachaInput.size(); i++) {
            System.out.println(jogadorRachaInput.get(i).getJogador());
            JogadorRacha jogadorRacha = modelMapper.map(jogadorRachaInput.get(i), JogadorRacha.class);
            jogadorRacha.setRacha(rachaService.findById(jogadorRachaInput.get(i).getRacha()));
            jogadorRacha.setJogador(jogadorService.findByUser(jogadorRachaInput.get(i).getJogador()).get());
            saveJogadores.add(jogadorRachaRepository.save(jogadorRacha));
        }
        return saveJogadores;
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
        jogadorRacha.setActived(true);
        return jogadorRachaRepository.save(jogadorRacha);
    }

    public JogadorRacha deactivateById(Long id) {
        JogadorRacha jogadorRacha = findById(id);
        jogadorRacha.setActived(false);
        return jogadorRachaRepository.save(jogadorRacha);
    }

    public List<JogadorRacha> listAllJogadorRacha() {
        return jogadorRachaRepository.findAll();
    }

    public JogadorRacha findByIdDesactived(Long id) {
        return jogadorRachaRepository.findByIdDesactived(id).orElseThrow(() -> new RuntimeException("Jogador Racha não encontrada"));
    }

    public List<JogadorRacha> findByUser(Long id) {
        return jogadorRachaRepository.findByJogadorId(id);
    }

    public Optional<JogadorRacha> findByIdJogador(Long id) {
        return jogadorRachaRepository.findByIdJogador(id);
    }
}
