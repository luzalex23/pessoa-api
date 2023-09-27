package me.gestaopessoa.pessoaApi.reources;

import me.gestaopessoa.pessoaApi.dtos.EnderecoDto;
import me.gestaopessoa.pessoaApi.entities.Endereco;
import me.gestaopessoa.pessoaApi.sevices.EnderecoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/endereco")
public class EnderecoResource {

    @Autowired
    private EnderecoService enderecoService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<Endereco> findByid(@PathVariable Long id) {
        Endereco endereco = enderecoService.findById(id);
        return ResponseEntity.ok().body(endereco);
    }

    @GetMapping
    public ResponseEntity<List<EnderecoDto>> findAll(
            @RequestParam(value = "pessoa", defaultValue = "0") Long id_pessoa) {
        List<EnderecoDto> enderecoDto = new ArrayList<EnderecoDto>();
        enderecoDto = enderecoService.findAll(id_pessoa).stream().map(endereco -> new EnderecoDto(endereco))
                .collect(Collectors.toList());
        return ResponseEntity.ok().body(enderecoDto);

    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Endereco> update(@PathVariable Long id, @RequestBody Endereco endereco) {
        Endereco newEndereco = enderecoService.update(id, endereco);
        return ResponseEntity.ok().body(newEndereco);
    }

    @PatchMapping(value = "/{id}")
    public ResponseEntity<Endereco> updatePatch(@PathVariable Long id, @RequestBody Endereco endereco) {
        Endereco newEndereco = enderecoService.updatePrincipal(id, endereco);
        return ResponseEntity.ok().body(newEndereco);
    }

    @PostMapping
    public ResponseEntity<Endereco> create(@RequestParam(value = "pessoa", defaultValue = "0") Long id_pessoa,
                                           @RequestBody Endereco endereco) {
        Endereco newEndereco = enderecoService.create(id_pessoa, endereco);
        URI uri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/endereco/{id}")
                .buildAndExpand(newEndereco.getIdEndereco()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        enderecoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

