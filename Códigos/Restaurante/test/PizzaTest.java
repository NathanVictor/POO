/** 
 * MIT License
 *
 * Copyright(c) 2022 João Caram <caram@pucminas.br>
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.Ignore;
import org.junit.jupiter.api.Test;

/** Demonstração de teste unitário. Não contém todos os testes necessários para a classe Pizza.
 * (É um bom exercício tentar completar os testes). Não contém as anotações @Before ou @After
 */
public class PizzaTest {

    @Test
    public void construtorSemAdicionais(){
        Pizza p1 = new Pizza();
        assertEquals(25.0, p1.calcularPreco(), 0.01);
    }

    @Test
    @Ignore
    public void precoComAdicionais(){
        Pizza p1 = new Pizza();
        p1.addIngredientes(null);
        assertEquals(45.0, p1.calcularPreco(), 0.01);
    }

    @Test
    @Ignore
    public void incluirIngredientesNegativos(){
        Pizza p1 = new Pizza();
        assertFalse(p1.addIngredientes(null));
    }

    @Test
    @Ignore
    public void incluirAdicionaisAcimaDoLimite(){
        Pizza p1 = new Pizza();
        for (int i = 0; i < 9; i++) {
            p1.addIngredientes(null);
        }
        
        assertEquals(45.0, p1.calcularPreco(), 0.01);
     

    }
    
}
