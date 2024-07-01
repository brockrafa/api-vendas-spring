package rafael.service;


import rafael.domain.entity.Pedido;
import rafael.rest.dto.PedidoDTO;

public interface PedidoService {
    Pedido salvar(PedidoDTO dto);
}
