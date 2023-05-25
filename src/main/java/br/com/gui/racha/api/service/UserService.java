package br.com.gui.racha.api.service;

import br.com.gui.racha.model.entity.User;
import br.com.gui.racha.model.input.UserInput;
import br.com.gui.racha.model.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final RoleService roleService;

    public User save(UserInput userInput) {
        User user = modelMapper.map(userInput, User.class);
        user.setRole(roleService.findById(userInput.getRole()));
        return userRepository.save(user);
    }

    public List<User> listAll() {
        return userRepository.findAll();
    }

    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("User não encontrada"));
    }

    public User updateById(Long id, UserInput userInput) {
        User user = findById(id);
        user.setNome(userInput.getNome());
        user.setEmail(userInput.getEmail());
        user.setUsername(userInput.getUsername());
        user.setPassword(userInput.getPassword());
        user.setIdade(userInput.getIdade());
        user.setRole(roleService.findById(userInput.getRole()));
        user.setTelefone(userInput.getTelefone());
        return userRepository.save(user);
    }

    public User deactivateById(Long id) {
        User user = findById(id);
        user.setActived(false);
        return userRepository.save(user);
    }

    public List<User> listAllUser() {
        return userRepository.findAllUser();
    }

    public User findByIdDesactived(Long id) {
        return userRepository.findByIdDesactived(id).orElseThrow(() -> new RuntimeException("User não encontrada"));
    }
}
