package rafael.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import rafael.domain.entity.Pedido;
import rafael.rest.dto.PedidoDTO;
import rafael.service.PedidoService;
import rafael.service.impl.PedidoServiceImpl;

@RestController
@RequestMapping("api/pedidos")
public class PedidoController {

    private PedidoServiceImpl servico;

    public PedidoController(PedidoServiceImpl repository){
        this.servico = repository;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Integer save(@RequestBody PedidoDTO dto){
        Pedido pedido = servico.salvar(dto);
        return pedido.getId();
    }

}
