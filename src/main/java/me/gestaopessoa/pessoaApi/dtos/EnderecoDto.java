package me.gestaopessoa.pessoaApi.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.gestaopessoa.pessoaApi.entities.Endereco;

import java.io.Serializable;
@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class EnderecoDto implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long idEndereco;
    private String logradouro;
    private String cep;
    private String numero;
    private String cidade;
    private boolean enderecoPrincipal;

    public EnderecoDto(Endereco endereco) {
        super();
        this.idEndereco = endereco.getIdEndereco();
        this.logradouro = endereco.getLogradouro();
        this.cep = endereco.getCep();
        this.numero = endereco.getNumero();
        this.cidade = endereco.getCidade();
        this.enderecoPrincipal = endereco.isEnderecoPrincipal();
    }

}
