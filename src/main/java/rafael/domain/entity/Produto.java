package rafael.domain.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity
@Table(name = "produto")
@Getter
@Setter
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @Column(name = "descricao")
    @NotEmpty(message = "Campo descrição é obrigatório")
    private String descricao;

    @Column(name = "preco_unitario")
    @NotNull(message = "O campo preço é obrigatório")
    private BigDecimal preco;

    public Produto(String descricao,BigDecimal preco){
        this.descricao = descricao;
        this.preco = preco;
    }

    public Produto(){}

}
