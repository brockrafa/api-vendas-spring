package rafael.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import rafael.domain.entity.ItemPedido;
import rafael.domain.entity.Pedido;
import rafael.domain.entity.StatusPedido;
import rafael.rest.dto.AtualizacaoStatusPedidoDTO;
import rafael.rest.dto.InformacaoItemPedidoDTO;
import rafael.rest.dto.InformacoesPedidoDTO;
import rafael.rest.dto.PedidoDTO;
import rafael.service.PedidoService;
import rafael.service.impl.PedidoServiceImpl;

import javax.validation.Valid;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/pedidos")
public class PedidoController {

    private PedidoServiceImpl servico;

    public PedidoController(PedidoServiceImpl repository){
        this.servico = repository;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Integer save(@Valid @RequestBody PedidoDTO dto){
        Pedido pedido = servico.salvar(dto);
        return pedido.getId();
    }

    @GetMapping("{id}")
    public InformacoesPedidoDTO getById(@PathVariable Integer id){
        return servico.obterPedido(id)
                .map(p -> converter(p))
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"Pedido não encontrado"));
    }

    private InformacoesPedidoDTO converter(Pedido pedido){
        return InformacoesPedidoDTO
                .builder()
                .id(pedido.getId())
                .dataPedido(pedido.getDataPedido().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")))
                .cpf(pedido.getCliente().getCpf())
                .nomeCliente(pedido.getCliente().getNome())
                .total(pedido.getTotal())
                .status(pedido.getStatus().name())
                .itens(converterItem(pedido.getItens()))
                .build();
    }

    private List<InformacaoItemPedidoDTO> converterItem(List<ItemPedido> itens){
        if (CollectionUtils.isEmpty(itens)){
            return Collections.emptyList();
        }
        return itens.stream()
                .map(item -> InformacaoItemPedidoDTO
                        .builder()
                        .descricao(item.getProduto().getDescricao())
                        .precoUnitario(item.getProduto().getPreco())
                        .quantidade(item.getQuantidade())
                        .build()
                ).collect(Collectors.toList());
    }

    @PatchMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    private void updateStatus(@PathVariable Integer id,@RequestBody AtualizacaoStatusPedidoDTO dto){
        String novoStatus = dto.getNovoStatus();
        servico.atualizaStatus(id, StatusPedido.valueOf(novoStatus));
    }
}
