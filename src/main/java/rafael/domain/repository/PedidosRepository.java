package rafael.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rafael.domain.entity.Cliente;
import rafael.domain.entity.Pedido;

import java.util.List;

public interface PedidosRepository extends JpaRepository<Pedido,Integer> {

    List<Pedido> findByCliente(Cliente cliente);
}
