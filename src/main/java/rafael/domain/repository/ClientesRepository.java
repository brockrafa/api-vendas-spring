package rafael.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import rafael.domain.entity.Cliente;

import java.util.List;

public interface ClientesRepository extends JpaRepository<Cliente,Integer> {

    List<Cliente> findByNomeLike(String nome);

    @Query(" select c from Cliente c left join fetch c.pedidos where c.id = :id")
    Cliente findClienteFetchPedidos(@Param("id") Integer id);
    /*@Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private EntityManager entityManager;

    private static String INSERT = "insert into CLIENTE (nome) values (?)";
    private static String SELECT_ALL = "select * from cliente";

    @Transactional
    public  Cliente salvar(Cliente cliente){
        entityManager.persist(cliente);
        return cliente;
    }

    @Transactional
    public Cliente atualizar(Cliente cliente){
        entityManager.merge(cliente);
        return cliente;
    }

    @Transactional
    public void deletarPorId(Integer id){
        Cliente cliente = entityManager.find(Cliente.class,id);
        deletar(cliente);
    }

    @Transactional
    public void deletar(Cliente cliente){
        if(!entityManager.contains(cliente)){
            cliente = entityManager.merge(cliente);
        }
        entityManager.remove(cliente);
    }

    @Transactional(readOnly = true)
    public List<Cliente> buscaPorNome(String nome){
        String jpql = " select c from Cliente c where c.nome like :nome";
        TypedQuery<Cliente> query = entityManager.createQuery(jpql,Cliente.class);
        query.setParameter("nome","%"+nome+"%");
        return query.getResultList();
    }

    @Transactional(readOnly = true)
    public List<Cliente> obterTodos(){
        return entityManager.createQuery("from Cliente",Cliente.class).getResultList();
    }*/

}
