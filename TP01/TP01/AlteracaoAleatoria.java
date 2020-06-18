/*--------------------TP01---------------------
Aluna: Ana Laura Fernandes de Oliveira
Matricula: 680715
Disciplina: Algoritmos e Estrutura de dados II
Professor: Max do Val Machado
----------------------------------------------- */
import java.util.*;
public class AlteracaoAleatoria
{
    public static char[] letraAleatoria(Random geradorLetra)
    {
        //declaracao de variaveis
        char[] letras = new char[2];
       
        //gerar letra aleatoria
        for(int i = 0; i < 2; i++)
        {
            letras[i] =((char)('a' + (Math.abs(geradorLetra.nextInt()) % 26)));
        }
        
        return letras;
    }
    public static String trocaLetras(String frase, Random gerador)
    {
        char[] temp = new char[frase.length()];
        //gerar letras aleatorias para realizar a substituicao
        char[] letras = letraAleatoria(gerador);    
    
        //efetuar a troca dos caracteres. Quando o primeiro char sorteado for localizado, ele devera ser trocado pelo segundo sorteado
        for(int i = 0; i < frase.length(); i++)
        {
            if(frase.charAt(i) != letras[0])
            {
                temp[i] = frase.charAt(i);
            }
            else
            {
                temp[i] = letras[1];
            }
        }
        String resposta = new String(temp);
        return resposta; //retornar a string modificada
    }
    // conferir se a string lida corresponde a FIM
	public static boolean isFim(String frase) 
	{
		return (frase.length() == 3 && frase.charAt(0) == 'F' && frase.charAt(1) == 'I' && frase.charAt(2) == 'M');
	}
    public static void main(String[] args)
    {
        Random gerador = new Random();
        gerador.setSeed(4);	
	    //receber entradas
        String[] entrada = new String[1000]; //numero escolhido de forma arbitraria
        int numEntradas = 0;
        entrada[numEntradas] = MyIO.readLine();
        while(isFim(entrada[numEntradas]) == false)
        {   
            numEntradas++;
            entrada[numEntradas] = MyIO.readLine();
        }
        //modificar a string
        for(int i = 0; i < numEntradas; i++)
        {
            entrada[i] = trocaLetras(entrada[i], gerador);
            //imprimir string modificada
            MyIO.println(entrada[i]);
        }
        
    }
}
