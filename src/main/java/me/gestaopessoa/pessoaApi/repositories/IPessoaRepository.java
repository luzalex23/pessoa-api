package me.gestaopessoa.pessoaApi.repositories;

import me.gestaopessoa.pessoaApi.entities.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPessoaRepository extends JpaRepository<Pessoa, Long> {
    Pessoa findByNome(String nomePessoa);
}
