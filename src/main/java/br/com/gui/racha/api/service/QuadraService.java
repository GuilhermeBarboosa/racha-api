package br.com.gui.racha.api.service;

import br.com.gui.racha.model.entity.Quadra;
import br.com.gui.racha.model.input.QuadraInput;
import br.com.gui.racha.model.repository.QuadraRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QuadraService {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private final QuadraRepository quadraRepository;

    public Quadra save(QuadraInput quadraInput) {
        Quadra quadra = modelMapper.map(quadraInput, Quadra.class);
        return quadraRepository.save(quadra);
    }

    public List<Quadra> listAll() {
        return quadraRepository.findAll();
    }

    public Quadra findById(Long id) {
        return quadraRepository.findById(id).orElseThrow(() -> new RuntimeException("Quadra não encontrada"));
    }

    public Quadra updateById(Long id, QuadraInput quadraInput) {
        Quadra quadra = findById(id);
        quadra.setNome(quadraInput.getNome());
        return quadraRepository.save(quadra);
    }

    public Quadra deactivateById(Long id) {
        Quadra quadra = findById(id);
        quadra.setActived(false);
        return quadraRepository.save(quadra);
    }

    public List<Quadra> listAllQuadra() {
        return quadraRepository.findAll();
    }

    public Quadra findByIdDesactived(Long id) {
        return quadraRepository.findByIdDesactived(id).orElseThrow(() -> new RuntimeException("Quadra não encontrada"));
    }
}
