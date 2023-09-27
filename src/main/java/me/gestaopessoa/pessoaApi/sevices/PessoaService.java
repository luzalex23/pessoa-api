package me.gestaopessoa.pessoaApi.sevices;

import lombok.RequiredArgsConstructor;
import me.gestaopessoa.pessoaApi.dtos.PessoaDto;
import me.gestaopessoa.pessoaApi.entities.Endereco;
import me.gestaopessoa.pessoaApi.entities.Pessoa;
import me.gestaopessoa.pessoaApi.repositories.IEnderecoRepository;
import me.gestaopessoa.pessoaApi.repositories.IPessoaRepository;
import me.gestaopessoa.pessoaApi.sevices.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PessoaService {
    @Autowired
    private IPessoaRepository pessoa;
    //private  IEnderecoRepository endereco;

    public List<Pessoa> findAll(){
        return pessoa.findAll();
    }
    public Pessoa findById(Long idPessoa){
        Optional<Pessoa> obj = pessoa.findById(idPessoa);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id:" + idPessoa + ", Tipo: " + Pessoa.class.getName()));
    }

    public Pessoa create(Pessoa person){
        return pessoa.save(person);

    }

    public Pessoa update(Long id, PessoaDto pessoaDto) {
        Pessoa obj = findById(id);
        obj.setNome(pessoaDto.getNome());
        obj.setDtNascimento(pessoaDto.getDtNascimento());
        return pessoa.save(obj);
    }

    public void delete(Long id) {
        findById(id);
        try {
            pessoa.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new me.gestaopessoa.pessoaApi.sevices.exception.DataIntegrityViolationException(
                    "Pessoa não pode ser deletada! Pois possui enderços associados.");
        }

    }



}
