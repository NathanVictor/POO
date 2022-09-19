
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
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**Demonstração de testes unitários com o Relógio. */
public class RelogioTest {

    static Relogio relogio;

    @BeforeAll 
    /* executa só 1 vez antes do primeiro teste */

    @AfterEach
    /* executa depois de cada teste, para 'zerar' modificações */

    @BeforeEach
    public void antesDeCadaTeste(){
        relogio = new Relogio(22, 45);
        relogio.ajustarData(24, 7);
    }
    /*
     * Criar relógios:
     *       24/07, 22:45:00
     */
    @Test
    public void criarRelogioComValoresValidos(){
        assertEquals("22:45:00 de 24/07.", relogio.horaCompleta());
    }
    /*
     * relógio inválido.
        hora
        data
     */

     @Test
     public void relogioComHoraInvalida(){
        Relogio relogio2 = new Relogio(32, 45);
        assertEquals("00:00:00 de 01/01.", relogio2.horaCompleta());
     }

     @Test
     public void relogioComDataInvalida(){
        relogio.ajustarData(32, 8);
        assertEquals("00:00:00 de 01/01.", relogio.horaCompleta());
     }

     /*
      * Ajustar:
            25/08, 20:15:00
      */
      @Test
      public void ajustarDataEHoraCorretas(){
        relogio.ajustarData(25, 8);
        relogio.ajustarHora(20, 15, 0);
        assertEquals("20:15:00 de 25/08.", relogio.horaCompleta());
        
      }

      /*
       * Reiniciar.
       */
      @Test
      public void reiniciarRelogio(){
        relogio.reiniciar();
        assertEquals("00:00:00 de 01/01.", relogio.horaCompleta());
      }

        /**
         * Passar tempo virando dia.
         */
        @Test
        public void passagemDeTempoComViradaDeDia(){
            Relogio relogio2 = new Relogio(23, 59, 59);
            relogio2.passarTempo();
            assertEquals("00:00:00 de 25/07.", relogio2.horaCompleta());
        }
}
