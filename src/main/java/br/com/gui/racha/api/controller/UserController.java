package br.com.gui.racha.api.controller;

import br.com.gui.racha.api.service.UserService;
import br.com.gui.racha.model.entity.User;
import br.com.gui.racha.model.input.UserInput;
import br.com.gui.racha.model.output.UserOutput;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private final UserService userService;

    @PostMapping
    public ResponseEntity<?> save(@Valid @RequestBody UserInput userInput) {
        if (userService.findByEmail(userInput.getEmail()).isPresent()) {
            return new ResponseEntity<String>("Email já cadastrado", HttpStatus.BAD_REQUEST);
        } else {
            User createdUser = userService.save(userInput);
            UserOutput userOutput = modelMapper.map(createdUser, UserOutput.class);
            return ResponseEntity.ok(userOutput);
        }
    }

    @GetMapping
    public ResponseEntity<List<UserOutput>> listAll() {
        List<User> users = userService.listAll();
        List<UserOutput> responseDTOS = users.stream()
                .map(user -> modelMapper.map(user, UserOutput.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(responseDTOS);
    }

    @GetMapping("/desativado")
    public ResponseEntity<List<UserOutput>> listAllUser() {
        List<User> users = userService.listAllUserDesactived();
        List<UserOutput> responseDTOS = users.stream()
                .map(user -> modelMapper.map(user, UserOutput.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(responseDTOS);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserOutput> getById(@PathVariable Long id) {
        User user = userService.findById(id);
        UserOutput userOutput = modelMapper.map(user, UserOutput.class);
        return ResponseEntity.ok(userOutput);
    }

    @GetMapping("/desativado/{id}")
    public ResponseEntity<UserOutput> getByIdDesactived(@PathVariable Long id) {
        User user = userService.findByIdDesactived(id);
        UserOutput userOutput = modelMapper.map(user, UserOutput.class);
        return ResponseEntity.ok(userOutput);
    }
    @GetMapping("/cpf/{cpf}")
    public ResponseEntity<List<UserOutput>> getByCpf(@PathVariable String cpf) {
        List<User> users = userService.findByCpf(cpf);
        List<UserOutput> responseDTOS = users.stream()
                .map(user -> modelMapper.map(user, UserOutput.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(responseDTOS);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateById(@PathVariable Long id, @RequestBody @Valid UserInput userInput) {
        User updatedUser = userService.updateById(id, userInput);
        if (updatedUser == null) {
            return new ResponseEntity<String>("Senha igual a anterior", HttpStatus.BAD_REQUEST);
        } else {
            UserOutput userOutput = modelMapper.map(updatedUser, UserOutput.class);
            return ResponseEntity.ok(userOutput);
        }
    }

    @PutMapping("/ativar/{id}")
    public ResponseEntity<?> ativarById(@PathVariable Long id) {
        User updatedUser = userService.ativarById(id);
        UserOutput userOutput = modelMapper.map(updatedUser, UserOutput.class);
        return ResponseEntity.ok(userOutput);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<UserOutput> deactivateById(@PathVariable Long id) {
        User deactivatedUser = userService.deactivateById(id);
        UserOutput userOutput = modelMapper.map(deactivatedUser, UserOutput.class);
        return ResponseEntity.ok(userOutput);
    }
}
