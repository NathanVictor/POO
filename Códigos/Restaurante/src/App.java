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

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * App-demo para o restaurante. Versão 2: pedidos com pizzas.
 */
public class App {

    // #region utilidades
    /**
     * "Limpa" a tela (códigos de terminal VT-100)
     */
    public static void limparTela() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    /**
     * Pausa para leitura de mensagens em console
     * 
     * @param teclado Scanner de leitura
     */
    static void pausa(Scanner teclado) {
        System.out.println("\nEnter para continuar.");
        teclado.nextLine();
    }
    // #endregion

    // #region menus
    /**
     * Menu para o restaurante
     * 
     * @param teclado Scanner de leitura
     * @return Opção do usuário (int)
     */
    public static int menu(Scanner teclado) {

        System.out.println();
        System.out.println();

        System.out.println("XULAMBS PIZZA");
        System.out.println("==========================");
        System.out.println("1 - Novo pedido");
        System.out.println("2 - Incluir comida em pedido");
        System.out.println("3 - Detalhes do pedido");
        System.out.println("4 - Fechar pedido");
        // System.out.println("5 - Relatório de todos os pedidos (arquivo
        // 'pedidos.txt')");
        // System.out.println("6 - Maior pedido de hoje");
        System.out.println("0 - Sair");
        System.out.print("Digite sua opção: ");
        try {
            int opcao = teclado.nextInt();
            teclado.nextLine();
            return opcao;
        } catch (InputMismatchException ie) {
            return -1;
        }
    }
    // #endregion

    /**
     * Utilizado para perguntar, no momento da criação da pizza para inclusão,
     * quantos adicionais ela deve ter.
     * 
     * @param novaPizza A pizza criada que receberá os adicionais.
     * @param teclado   Scanner de leitura de teclado.
     */
    private static void adicionarIngredientes(Pizza novaPizza, Scanner teclado) {
        System.out.print("\n Deseja quantos adicionais (0-8)? ");
        int adicionais = teclado.nextInt();
        novaPizza.addIngredientes(adicionais);
        teclado.nextLine();
    }

    /**
     * Utilizado para conter a rotina de adicionar uma pizza, chamando em seguida a
     * inclusão de ingredientes.
     * 
     * @param pedido  O pedido atual.
     * @param pizza   A pizza que será incluída no pedido.
     * @param teclado Scanner de leitura de teclado.
     */
    public static void adicionarPizzaAoPedido(Pedido pedido, Pizza pizza, Scanner teclado) {
        if (pedido.addPizza(pizza)) {
            adicionarIngredientes(pizza, teclado);
            System.out.println("Pizza adicionada ao pedido.");
        } else
            System.out.println("Não foi possível adicionar a pizza: limite atingido ou pedido fechado.");
    }

    public static void main(String[] args) throws Exception {
        // testes feitos na classe de teste!!
        Scanner teclado = new Scanner(System.in);
        String hoje = "05/09/2022"; // fictício, para não precisar pegar data do sistema
        int opcao;
        Pedido novoPedido = null;

        do {
            limparTela();
            opcao = menu(teclado);
            switch (opcao) {
                case 1:
                    novoPedido = new Pedido(hoje);
                    System.out.println("Pedido criado.");
                    break;
                case 2:
                    if (novoPedido != null)
                        adicionarPizzaAoPedido(novoPedido, new Pizza(), teclado);
                    else
                        System.out.println("Pedido ainda não foi aberto.");

                    break;
                case 3:
                    limparTela();
                    System.out.println(novoPedido.relatorio());
                    break;
                case 4:
                    novoPedido.fecharPedido();
                    break;
            }
            pausa(teclado);
        } while (opcao != 0);

    }
}
