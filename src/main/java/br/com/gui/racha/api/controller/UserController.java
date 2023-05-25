package br.com.gui.racha.api.controller;

import br.com.gui.racha.api.service.UserService;
import br.com.gui.racha.model.entity.User;
import br.com.gui.racha.model.input.UserInput;
import br.com.gui.racha.model.output.UserOutput;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<UserOutput> save(@Valid @RequestBody UserInput userInput) {
        User createdUser = userService.save(userInput);
        UserOutput userOutput = modelMapper.map(createdUser, UserOutput.class);
        return ResponseEntity.ok(userOutput);
    }

    @GetMapping
    public ResponseEntity<List<UserOutput>> listAll(){
        List<User> users = userService.listAll();
        List<UserOutput> responseDTOS = users.stream()
                .map(user -> modelMapper.map(user, UserOutput.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(responseDTOS);
    }

    @GetMapping("/all")
    public ResponseEntity<List<UserOutput>> listAllUser(){
        List<User> users = userService.listAllUser();
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

    @PutMapping("/{id}")
    public ResponseEntity<UserOutput> updateById(@PathVariable Long id, @RequestBody @Valid UserInput userInput) {
        User updatedUser = userService.updateById(id, userInput);
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
