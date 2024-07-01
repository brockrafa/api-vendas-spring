package rafael.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rafael.domain.entity.Produto;

public interface ProdutoRepository extends JpaRepository<Produto,Integer> {
}
