package rafael.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rafael.domain.entity.Cliente;
import rafael.domain.entity.ItemPedido;
import rafael.domain.entity.Pedido;
import rafael.domain.entity.Produto;
import rafael.domain.repository.ClientesRepository;
import rafael.domain.repository.ItemPedidoRepository;
import rafael.domain.repository.PedidosRepository;
import rafael.domain.repository.ProdutoRepository;
import rafael.exception.RegraNegocioException;
import rafael.rest.dto.ItemPedidoDTO;
import rafael.rest.dto.PedidoDTO;
import rafael.service.PedidoService;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PedidoServiceImpl implements PedidoService {

    @Autowired
    private PedidosRepository repository;

    @Autowired
    private ClientesRepository clientes;

    @Autowired
    private ProdutoRepository produtos;

    @Autowired
    private ItemPedidoRepository itemPedidos;

    @Override
    @Transactional
    public Pedido salvar(PedidoDTO dto) {
        Integer idCliente = dto.getCliente();
        Cliente cliente = clientes.findById(idCliente).orElseThrow(()-> new RegraNegocioException("Codigo de cliente invalido"));

        Pedido pedido = new Pedido();
        pedido.setTotal(dto.getTotal());
        pedido.setDataPedido(LocalDate.now());
        pedido.setCliente(cliente);

        List<ItemPedido> itens = converterItems(pedido,dto.getItens());
        repository.save(pedido);
        itemPedidos.saveAll(itens);
        pedido.setItens(itens);
        return pedido;
    }

    private List<ItemPedido> converterItems(Pedido pedido,List<ItemPedidoDTO> dto){
        if(dto.isEmpty()){
            throw new RegraNegocioException("Não é possivel realizar os pedidos sem itens");
        }

        return dto.stream().map(item -> {
            Integer idProduto = item.getProduto();
            Produto produto = produtos.findById(idProduto).orElseThrow(()->new RegraNegocioException("O produto informado não existe : "+idProduto));
            ItemPedido itemP = new ItemPedido();
            itemP.setQuantidade(item.getQuantidade());
            itemP.setPedido(pedido);
            itemP.setProduto(produto);
            return itemP;
        }).collect(Collectors.toList());
    }
}
