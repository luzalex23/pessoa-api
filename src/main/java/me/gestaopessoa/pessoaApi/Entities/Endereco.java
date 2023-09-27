package me.gestaopessoa.pessoaApi.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.gestaopessoa.pessoaApi.Enumeration.TipoEndereco;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@Builder
@Data
@Embeddable
@NoArgsConstructor
public class Endereco {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String logradouro;
    private String cep;
    private String numero;
    private String cidade;

    @Enumerated
    private TipoEndereco tipoEndereco;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "pessoa_id")
    private Pessoa pessoa;
}
