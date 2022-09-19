
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

 /** Classe relógio para demonstração de testes unitários */
public class Relogio {
	//#region atributos
    private static final int[] DIAS_DO_MES = {0,31,28,31,30,31,30,
                                                31,31,30,31,30,31};   
	private int hora;
    private int minuto;
    private int segundo;
	private int dia;
	private int mes;
	//#endregion

   //#region construtores e inicializadores 
   /**
    * Inicializador privado. Qualquer dado inválido reverte para 00:00:00.
    * @param hora Hora para ajustar/criar.
    * @param min Minuto para ajustar/criar.
    * @param seg Segundo para ajustar/criar.
    */
    private void init(int hora, int min, int seg){
			this.validarHora(hora, min, seg);
			this.dia = this.mes = 1;
        
    }
    
    /**
     * Construtor com segundos padronizados para 00. Valores validados por método inicializador: qualquer dado inválido reverte para 00:00
     * @param hora Hora para ajustar/criar.
     * @param min Minuto para ajustar/criar.
     */
    public Relogio(int hora, int min){
        init(hora, min, 0);
    }

    /**
     * Construtor completo. Valores validados por método inicializador: qualquer dado inválido reverte para 00:00:00.
     * @param hora Hora para ajustar/criar.
     * @param min Minuto para ajustar/criar.
     * @param seg Segundo para ajustar/criar.
     */
    public Relogio(int hora, int min, int seg){
        init(hora, min, seg);
    }
    //#endregion

    //#region métodos de negócio

    /**
     * Ajuste de hora. Valores validados: qualquer dado inválido reverte para 00:00:00.
     * @param h Hora para ajustar.
     * @param m Minuto para ajustar.
     * @param s Segundo para ajustar.
     */
	public void ajustarHora(int h, int m, int s){
		this.validarHora(h,m,s);
	}
	
    /**
     * Ajuste de data: somente dia e mês (o relógio não marca o ano).
     * @param d Dia para ajustar.
     * @param m Mês para ajustar.
     */
	public void ajustarData(int d, int m){
		if(m<1||m>12){
           this.reiniciar();
        }
        else{
            if(d<= DIAS_DO_MES[m]){
                this.dia = d;
		        this.mes = m;
            }
            else{
                this.reiniciar();
            }
        }
	}

    /**
     * Reinicia o relógio para 00:00:00, dia 01/01.
     */
	public void reiniciar(){
		this.ajustarHora(0, 0, 0);
        this.dia = this.mes = 1;
	}
	
    /**
     * Retorna a hora completa, no formato 'HH:MM:SS de DD/MM.'.
     * @return String com os dados formatados como 'HH:MM:SS de DD/MM.'.
     */
    public String horaCompleta() {
        return 	String.format("%02d", this.hora)+":"+ 
				String.format("%02d", this.minuto)+":"+
				String.format("%02d", this.segundo)+" de "+
				String.format("%02d", this.dia)+"/"+
				String.format("%02d", this.mes)+".";
    }

    /**
     * Sinaliza ao relógio a passagem de um segundo para os ajustes necessários nos dados. 
     */
    public void passarTempo() {
        this.segundo++;
        if(this.segundo==60){
            this.segundo=0;
            this.minuto++;
            if(this.minuto==60){
                this.minuto=0;
                this.hora++;
                if(this.hora==24){
                    this.hora = 0;
                    this.dia++;
                }
            }
        }
    }
	
    /**
     * Validação interna de hora. Em caso de qualquer dado inválido, a hora reverte para 00:00:00.
     * @param hora Hora para ajustar/criar.
     * @param min Minuto para ajustar/criar.
     * @param seg Segundo para ajustar/criar.
     */
	private void validarHora(int hora, int min, int seg){
			if(hora<0 || hora > 23 || min <0 || min > 59
               || seg<0 || seg > 59){
                this.hora = this.minuto = this.segundo = 0;
            }
            else{
                this.hora = hora;
                this.minuto = min;
                this.segundo = seg;
            }
	}		
    //#endregion
    
}
