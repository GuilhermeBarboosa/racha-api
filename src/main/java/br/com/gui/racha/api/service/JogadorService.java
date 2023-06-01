package br.com.gui.racha.api.service;

import br.com.gui.racha.model.entity.Jogador;
import br.com.gui.racha.model.input.JogadorInput;
import br.com.gui.racha.model.repository.JogadorRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JogadorService {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private final JogadorRepository jogadorRepository;

    @Autowired
    private final PosicaoService posicaoService;

    @Autowired
    private final UserService userService;

    public Jogador save(JogadorInput jogadorInput) {
        Jogador jogador = modelMapper.map(jogadorInput, Jogador.class);
        return jogadorRepository.save(jogador);
    }

    public List<Jogador> listAll() {
        return jogadorRepository.findAll();
    }

    public Jogador findById(Long id) {
        return jogadorRepository.findById(id).orElseThrow(() -> new RuntimeException("Jogador não encontrada"));
    }

    public Jogador updateById(Long id, JogadorInput jogadorInput) {
        Jogador jogador = findById(id);
        jogador.setAssitencias(jogadorInput.getAssitencias());
        jogador.setGols(jogadorInput.getGols());
        jogador.setPosicao(posicaoService.findById(jogadorInput.getPosicao()));
        jogador.setUser(userService.findById(jogadorInput.getUser()));

        return jogadorRepository.save(jogador);
    }

    public Jogador deactivateById(Long id) {
        Jogador jogador = findById(id);
        jogador.setActived(false);
        return jogadorRepository.save(jogador);
    }

    public List<Jogador> listAllJogador() {
        return jogadorRepository.findAllJogador();
    }

    public Jogador findByIdDesactived(Long id) {
        return jogadorRepository.findByIdDesactived(id).orElseThrow(() -> new RuntimeException("Jogador não encontrada"));
    }

    public Jogador findByUser(Long id) {
        return jogadorRepository.findByUserId(id).orElseThrow(() -> new RuntimeException("Jogador não encontrada"));
    }
}
