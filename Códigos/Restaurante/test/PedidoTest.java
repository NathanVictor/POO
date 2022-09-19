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
            assertEquals(0.0, pedido.calcularValor(), 0.01);
    }

    @Test
    public void testarInclusaoDePizza(){
            pedido.addPizza(pizza);
            assertEquals(25.0, pedido.calcularValor(), 0.01);
    }


    @Test
    public void testarLimitePizzasNoPedido(){
                
        for(int i=0; i<15; i++){
            pedido.addPizza(pizza);
        }
        assertEquals(250.0, pedido.calcularValor(),0.01);
    }

    @Test
    public void recusarPedidoVazio(){
        pedido.fecharPedido();
        assertTrue(pedido.addPizza(pizza));
    }

    @Test
    public void recusarMudancaEmPedidoEncerrado(){
        pedido.addPizza(pizza);
        pedido.fecharPedido();
        assertFalse(pedido.addPizza(pizza));
    }
    
    @Test
    public void calcularPrecoFinal(){
        Pizza pizza1 = new Pizza(1);
        pedido.addPizza(pizza);
        pedido.addPizza(pizza1);
        assertEquals(54.0, pedido.calcularValor(), 0.01);
    }

    @Test
    public void relatorioComDetalhesDasPizzas(){
        pedido.addPizza(pizza);
        pedido.fecharPedido();   

        String relatorio = pedido.relatorio();
      
        
        assertTrue(relatorio.contains("Pizza"));
    }
}
