package br.com.gui.racha.api.service;

import br.com.gui.racha.api.exception.SenhaInvalidaException;
import br.com.gui.racha.model.entity.Role;
import br.com.gui.racha.model.entity.User;
import br.com.gui.racha.model.input.UserInput;
import br.com.gui.racha.model.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private final UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private final RoleService roleService;

    public User save(UserInput userInput) {
        userInput.setSenha(passwordEncoder.encode(userInput.getSenha()));
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
        if (userInput.getSenha().equals(user.getSenha())) {
            return null;
        } else {
            user.setNome(userInput.getNome());
            user.setEmail(userInput.getEmail());
            user.setSenha(passwordEncoder.encode(userInput.getSenha()));
            user.setIdade(userInput.getIdade());
            user.setRole(roleService.findById(userInput.getRole()));
            user.setTelefone(userInput.getTelefone());
            return userRepository.save(user);
        }
    }

    public User deactivateById(Long id) {
        User user = findById(id);
        user.setActived(false);
        return userRepository.save(user);
    }

    public List<User> listAllUserDesactived() {
        return userRepository.findAllUserDesactived();
    }

    public User findByIdDesactived(Long id) {
        return userRepository.findByIdDesactived(id).orElseThrow(() -> new RuntimeException("User não encontrada"));
    }

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User ativarById(Long id) {
        User user = findByIdDesactived(id);
        user.setActived(true);
        return userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        User user = userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        Role role = roleService.findById(user.getRole().getId());

        return org.springframework.security.core.userdetails.User
                .builder()
                .username(user.getEmail())
                .password(user.getSenha())
                .roles(role.getRole())
                .build();
    }

    public User autenticar(User user){
        UserDetails userDetails = loadUserByUsername(user.getEmail());
        boolean senha = passwordEncoder.matches(user.getSenha(), userDetails.getPassword());
        if(senha){
           return findByEmail(user.getEmail()).get();
        }
        throw new SenhaInvalidaException();
    }
}
