package br.com.gui.racha.api.service;

import br.com.gui.racha.model.entity.Posicao;
import br.com.gui.racha.model.input.PosicaoInput;
import br.com.gui.racha.model.repository.PosicaoRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PosicaoService {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private final PosicaoRepository posicaoRepository;

    public Posicao save(PosicaoInput posicaoInput) {
        Posicao posicao = modelMapper.map(posicaoInput, Posicao.class);
        return posicaoRepository.save(posicao);
    }

    public List<Posicao> listAll() {
        return posicaoRepository.findAll();
    }

    public Posicao findById(Long id) {
        return posicaoRepository.findById(id).orElseThrow(() -> new RuntimeException("Posição não encontrada"));
    }

    public Posicao updateById(Long id, PosicaoInput posicaoInput) {
        Posicao posicao = findById(id);
        posicao.setPosicao(posicaoInput.getPosicao());
        return posicaoRepository.save(posicao);
    }

    public Posicao deactivateById(Long id) {
        Posicao posicao = findById(id);
        posicao.setActived(false);
        return posicaoRepository.save(posicao);
    }

    public List<Posicao> listAllPosicao() {
        return posicaoRepository.findAll();
    }

    public Posicao findByIdDesactived(Long id) {
        return posicaoRepository.findByIdDesactived(id).orElseThrow(() -> new RuntimeException("Posição não encontrada"));
    }

    public List<Posicao> getAllPosicoes(String posicao) {
        return posicaoRepository.findAllDesc(posicao);
    }
}
