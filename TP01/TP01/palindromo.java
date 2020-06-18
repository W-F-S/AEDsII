/*----------------------TP01---------------------
 *Aluna: Ana Laura Fernandes de Oliveira
 *Disciplina: Algoritmos e Estrutura de Dados II
 *Professor: Max do Val Machado
------------------------------------------------ */

public class palindromo
{
    public static boolean isPalindromo(String palavra)
    {
	//declaracao de variaveis    
        String palindromo = palavra;
        int j = 0;
        boolean resposta = false;
	
	//passos de verificacao
        for(int i = palavra.length() - 1; i >= 0 ; i--)
        {
            //verificando se a palavra, se lida da forma invertida eh a mesma que lida da esquerda pra direita
            if(palindromo.charAt(j) == palavra.charAt(i))
            {
                resposta = true;
            }
            else
            {
                resposta = false;
                i = -1;
            }
            j++;
        }

        return(resposta);
    }//end isPalindromo
    
    public static boolean isFim (String entrada)
    {
        return(entrada.charAt(0) == 'F' && entrada.charAt(1) == 'I' && entrada.charAt(2) == 'M');
    }
    public static void main(String[] args)
    {
        //declarar variaveis
        String[] palavras = new String[1000];
	int i = 0;
        
        
	    //receber entradas
            palavras[i] = MyIO.readLine();
	while(isFim(palavras[i]) == false)
	{
	    
	    //testar mediante uso de funcao se a string recebida e um palindromo e imprimir na tela o resultado
            if(isPalindromo(palavras[i]))
            {
                MyIO.println ("SIM");
            }
            else
            {
                MyIO.println("NAO");
            }
	    i++;
	    palavras[i] = MyIO.readLine();
        }
    }
}
/*---------------------Testes----------------------
 * ENTRADA                   SAIDA
 * ana                       SIM
 * hannah                    SIM
 * banana                    NAO
 * ciencia da comp           NAO
 * ama                       SIM
 * abacaxi                   NAO
 * ferias                    NAO
 * ------------------------------------------------*/
