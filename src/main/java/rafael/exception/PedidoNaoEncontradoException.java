package rafael.exception;

public class PedidoNaoEncontradoException extends RuntimeException {
    public PedidoNaoEncontradoException(){
        super("Pedido não encotrado");
    }
}
