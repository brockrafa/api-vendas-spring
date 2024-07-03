package rafael.service;


import rafael.domain.entity.Pedido;
import rafael.domain.entity.StatusPedido;
import rafael.rest.dto.PedidoDTO;

import java.util.Optional;

public interface PedidoService {
    Pedido salvar(PedidoDTO dto);
    void atualizaStatus(Integer id, StatusPedido status);
    Optional<Pedido> obterPedido(Integer id);
}
