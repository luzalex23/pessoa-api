package me.gestaopessoa.pessoaApi.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class Endereco  implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEndereco;
    private String logradouro;
    private String cep;
    private String numero;
    private String cidade;
    private boolean enderecoPrincipal;


    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "pessoa_id")
    private Pessoa pessoa;


    @Override
    public int hashCode() {
        return Objects.hash(idEndereco);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Endereco other = (Endereco) obj;
        return Objects.equals(idEndereco, other.idEndereco);
    }

}
