package rafael.rest.dto;

import lombok.*;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PedidoDTO {
    @NotNull(message = "Informe o codigo do cliente")
    private Integer cliente;
    @NotNull(message = "Informe o total do pedido")
    private BigDecimal total;
    private List<ItemPedidoDTO> itens;
}
