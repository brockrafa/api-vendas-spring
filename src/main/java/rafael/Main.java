package rafael;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import rafael.domain.entity.Cliente;
import rafael.domain.entity.Produto;
import rafael.domain.repository.ClientesRepository;
import rafael.domain.repository.PedidosRepository;
import rafael.domain.repository.ProdutoRepository;

import java.math.BigDecimal;

@SpringBootApplication
public class Main {

    @Bean
    public CommandLineRunner init(@Autowired ClientesRepository clientes, @Autowired ProdutoRepository produtos){
        return args -> {

            Cliente cliente1 = new Cliente("Rafael","17957032702");
            clientes.save(cliente1);

            Produto produto = new Produto("Mouse gamer",new BigDecimal("10.00"));
            produtos.save(produto);

            Produto produto2 = new Produto("Teclado gamer",new BigDecimal("10.00"));
            produtos.save(produto2);
        };
    }
    public static void main(String[] args) {
        SpringApplication.run(Main.class,args);
    }
}