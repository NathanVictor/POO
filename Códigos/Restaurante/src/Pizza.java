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

/** Classe pizza simples para demonstração de fundamentos de POO e teste unitário */
public class Pizza{
    //#region Atributos
    //constantes
    private static final int MAX_INGRED = 8;
    private static final double VALOR_BASICO = 25.0;
    private static final double VALOR_ADICIONAL = 4.0;
    private static final String DESCRICAO = "Pizza simples";
    
    private boolean vendaFechada;
    private int qtAdicionais;
    //#endregion

    //#region Métodos privados (validação interna)

    /**
     * Inicializador privado. Valida a quantidade de adicionais e coloca 0 se inválido.
     * @param quantosAdicionais Quantidade de adicionais na criação da pizza. 
     */
    private void init(int quantosAdicionais){
        if(this.validarAdicionais(quantosAdicionais))
            this.qtAdicionais = quantosAdicionais;
        else 
            this.qtAdicionais = 0;
        this.vendaFechada = false;
    }

    /**
     * Validação do máximo de adicionais. Protegido para venda fechada e valores negativos. Não adiciona os adicionais, apenas valida a quantidade.
     * @param quantos Quantidade de adicionais para validar.
     * @return TRUE se válido, FALSE se inválido.
     */
    private boolean validarAdicionais(int quantos){
        if (!this.vendaFechada && quantos>0 && quantos<=MAX_INGRED)
            return true;
        else
            return false;
    }
    //#endregion

    //#region Construtores
    
    /**
     * Construtor simples: cria uma pizza sem adicionais.
     */
    public Pizza(){
        this.init(0);
    }

    /**
     * Construtor para pizza com quantidade inicial de adicionais. O valor máximo é 8 adicionais.
     * @param quantosAdicionais A quantidade de adicionais no início do pedido. Valor volta para 0 se for inválido. 
     */
    public Pizza(int quantosAdicionais){
        this.init(quantosAdicionais);
    }
    //#endregion

    //#region Métodos de negócio
    
    /**
     * Adiciona ingredientes extras na pizza, além dos já incluídos. Método validado para valores negativos ou acima do limite (8 adicionais). Caso inválido, ignora a operação.
     * @param quantos Quantos ingredientes a adicionar aos que já existem na pizza. 
     * @return TRUE se operação com sucesso, FALSE caso contrário.
     */
    public boolean addIngredientes(int quantos){
        if(validarAdicionais(this.qtAdicionais+quantos)){
            this.qtAdicionais+=quantos;
            return true;
        }
        else
            return false;
    }

    /**
     * Fecha a venda da pizza. Depois deste método, não se pode mais adicionar ingredientes. Sempre retorna a nota atual de venda.
     * @return A nota simplificada de venda (string formatada).
     */
    public String fecharVenda(){
        this.vendaFechada = true;
        return gerarNota();
    }
    
    /**
     * Cria a nota de venda. Em caso de pedido aberto, retorna somente o aviso. A nota tem o formato simplificado de
     * "Pizza simples com XX adicionais. Preço: R$XX.XX".
     * @return A nota simplificada ou mensagem de pedido em aberto (string em qualquer caso).
     */
    public String gerarNota(){
        return DESCRICAO+" com "+this.qtAdicionais+
                " adicionais. Preço: R$"+String.format("%.2f", this.calcPreco())+".";
    }

    /**
     * Calcula o preco a ser cobrado pela pizza (valor básico + valor dos adicionais). Perceba que, se considerado adequado,
     * o método para o preço dos adicionais poderia ser um método à parte - talvez privado.
     * @return Valor atual a ser cobrado pela pizza.
     */
    public double calcPreco(){
        return VALOR_BASICO + this.qtAdicionais*VALOR_ADICIONAL;
    }
    //#endregion
}