package me.gestaopessoa.pessoaApi.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.gestaopessoa.pessoaApi.entities.Pessoa;

import java.io.Serializable;
import java.time.LocalDate;
@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class PessoaDto implements Serializable {

    private static final long serialVersionUID = 1L;
    private Long idPessoa;
    private String nome;
    private LocalDate dtNascimento;
    public PessoaDto(Pessoa pessoa) {
        super();
        this.idPessoa = pessoa.getIdPessoa();
        this.nome = pessoa.getNome();
        this.dtNascimento = pessoa.getDtNascimento();
    }

}
