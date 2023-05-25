package br.com.gui.racha.api.controller;

import br.com.gui.racha.api.service.RoleService;
import br.com.gui.racha.model.entity.Role;
import br.com.gui.racha.model.output.RoleOutput;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/role")
public class RoleController {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private final RoleService roleService;


    @GetMapping
    public ResponseEntity<List<RoleOutput>> listAll() {
        List<Role> roles = roleService.listAllRole();
        List<RoleOutput> responseDTOS = roles.stream()
                .map(role -> modelMapper.map(role, RoleOutput.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(responseDTOS);
    }


    @GetMapping("/{id}")
    public ResponseEntity<RoleOutput> getById(@PathVariable Long id) {
        Role role = roleService.findById(id);
        RoleOutput roleOutput = modelMapper.map(role, RoleOutput.class);
        return ResponseEntity.ok(roleOutput);
    }

}
