import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;



public class PedidoTest {
    public Pedido pedido;
    public Pizza pizza;
   
    @BeforeEach
    public void setUp(){
        pedido = new Pedido("03/09/2022");
        pizza = new Pizza();
    }
  
    @Test
    public void testarPedidoCriadoVazio(){
            assertEquals(0.0, pedido.calcularPreco(), 0.01);
    }

    @Test
    public void testarInclusaoDePizza(){
            pedido.addPizza(pizza);
            assertEquals(25.0, pedido.calcularPreco(), 0.01);
    }


    @Test
    public void testarLimitePizzasNoPedido(){
                
        for(int i=0; i<15; i++){
            pedido.addPizza(pizza);
        }
        assertEquals(250.0, pedido.calcularPreco(),0.01);
    }

    @Test
    public void recusarPedidoVazio(){
        pedido.encerrar();
        assertTrue(pedido.addPizza(pizza));
    }

    @Test
    public void recusarMudancaEmPedidoEncerrado(){
        pedido.addPizza(pizza);
        pedido.encerrar();
        assertFalse(pedido.addPizza(pizza));
    }
    
    @Test
    public void calcularPrecoFinal(){
        Pizza pizza1 = new Pizza();
        pedido.addPizza(pizza);
        pedido.addPizza(pizza1);
        assertEquals(50.0, pedido.calcularPreco(), 0.01);
    }

    @Test
    public void relatorioComDetalhesDasPizzas(){
        pedido.addPizza(pizza);
        pedido.encerrar();   

        String relatorio = pedido.toString();
      
        
        assertTrue(relatorio.contains("Pizza"));
    }
}
