package rafael.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rafael.domain.entity.ItemPedido;

public interface ItemPedidoRepository extends JpaRepository<ItemPedido,Integer> {
}
