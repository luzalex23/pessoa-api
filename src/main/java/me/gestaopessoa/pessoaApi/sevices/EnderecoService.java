package me.gestaopessoa.pessoaApi.sevices;

import me.gestaopessoa.pessoaApi.entities.Endereco;
import me.gestaopessoa.pessoaApi.entities.Pessoa;
import me.gestaopessoa.pessoaApi.repositories.IEnderecoRepository;
import me.gestaopessoa.pessoaApi.sevices.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EnderecoService {
    @Autowired
    private IEnderecoRepository endereco;

    @Autowired
    private PessoaService pessoaService;

    public Endereco findById(Long id) {
        Optional<Endereco> obj = endereco.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id:" + id + ", Tipo: " + Endereco.class.getName()));
    }

    public List<Endereco> findAll(Long id_pessoa){
        pessoaService.findById(id_pessoa);
        return endereco.findAllByPessoa(id_pessoa);


    }
    public Endereco update(Long id, Endereco andres) {
        Endereco newEndereco = findById(id);
        updateData(newEndereco, andres);
        return endereco.save(newEndereco);
    }

    public Endereco updatePrincipal(Long id, Endereco andres) {
        Endereco newEndereco = findById(id);
        updateIsPrincipal(newEndereco, andres);
        return endereco.save(newEndereco);
    }

    private void updateData(Endereco newEndereco, Endereco andres) {
        newEndereco.setLogradouro(andres.getLogradouro());
        newEndereco.setCidade(andres.getCidade());
        newEndereco.setCep(andres.getCep());
        newEndereco.setNumero(andres.getNumero());
        newEndereco.setEnderecoPrincipal(andres.isEnderecoPrincipal());
    }
    private void updateIsPrincipal(Endereco newEndereco, Endereco andres) {
        newEndereco.setEnderecoPrincipal(andres.isEnderecoPrincipal());
    }

    public Endereco create(Long id_pessoa, Endereco andres) {
        andres.setIdEndereco(null);
        Pessoa pessoa = pessoaService.findById(id_pessoa);
        andres.setPessoa(pessoa);
        return endereco.save(andres);
    }

    public void delete(Long id) {
        Endereco andres = findById(id);
        try {
            endereco.delete(andres);
        } catch (DataIntegrityViolationException e) {
            throw new me.gestaopessoa.pessoaApi.sevices.exception.DataIntegrityViolationException(
                    "Endereço não pode ser deletado! Este é o endereço principal.");
        }

    }


}
