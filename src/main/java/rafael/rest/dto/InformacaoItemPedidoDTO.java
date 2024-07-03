package rafael.rest.dto;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InformacaoItemPedidoDTO {
    private String descricao;
    private BigDecimal precoUnitario;
    private Integer quantidade;
}
