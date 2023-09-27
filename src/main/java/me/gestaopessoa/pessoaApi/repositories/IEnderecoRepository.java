package me.gestaopessoa.pessoaApi.repositories;

import me.gestaopessoa.pessoaApi.entities.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IEnderecoRepository extends JpaRepository<Endereco, Long> {
    @Query("SELECT obj FROM Endereco obj WHERE obj.pessoa.id = :id_pessoa ORDER BY id")

    List<Endereco> findAllByPessoa(@Param(value = "id_pessoa") Long id_pessoa);

}
