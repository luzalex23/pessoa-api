package me.gestaopessoa.pessoaApi.reources;

import me.gestaopessoa.pessoaApi.dtos.PessoaDto;
import me.gestaopessoa.pessoaApi.entities.Pessoa;
import me.gestaopessoa.pessoaApi.sevices.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/pessoa")
public class PessoaResource {

    @Autowired
    private PessoaService pessoaService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<Pessoa> findByid(@PathVariable Long id) {
        Pessoa pessoa = pessoaService.findById(id);
        return ResponseEntity.ok().body(pessoa);
    }

    @GetMapping
    public ResponseEntity<List<PessoaDto>> findAll() {
        List<PessoaDto> pessoaDtos = new ArrayList<PessoaDto>();
        pessoaDtos = pessoaService.findAll().stream().map(pessoa -> new PessoaDto(pessoa)).collect(Collectors.toList());
        return ResponseEntity.ok().body(pessoaDtos);
    }

    @PostMapping
    public ResponseEntity<Pessoa> create(@RequestBody Pessoa pessoa) {
        pessoa = pessoaService.create(pessoa);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(pessoa.getIdPessoa()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<PessoaDto> update(@PathVariable Long id, @RequestBody PessoaDto pessoaDto) {
        Pessoa newPessoa = pessoaService.update(id, pessoaDto);
        return ResponseEntity.ok().body(new PessoaDto(newPessoa));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        pessoaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
