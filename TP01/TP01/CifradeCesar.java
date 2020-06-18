/*--------------------TP01---------------------
Aluna: Ana Laura Fernandes de Oliveira
Matricula: 680715
Disciplina: Algoritmos e Estrutura de dados II
Professor: Max do Val Machado
----------------------------------------------- */

public class CifradeCesar
{
    public static String codificador(String frase)
    {
        char caracter = '-';
        char[] codificada = new char[frase.length()];
        //caso a string nao esteja vazia, cada caracter da mesma sera acrescido de 3 unidades na tabela ASCII
        if(frase != "" && frase != null)
        {
            int tamanho = frase.length();
            for(int i = 0; i < tamanho; i++)
            {
                caracter = (char)(frase.charAt(i) + 3);
                codificada[i] = caracter; 
            }
        }
        String resposta = new String(codificada);
        return(resposta);
    }
    public static boolean isFim(String frase) 
	{
		return (frase.length() == 3 && frase.charAt(0) == 'F' && frase.charAt(1) == 'I' && frase.charAt(2) == 'M');
	}
    public static void main(String[] args)
    {
        //declaracao de variaveis e ler entrada
        //receber entradas
        String[] entrada = new String[1000]; //numero escolhido de forma arbitraria
        int numEntradas = 0;
        entrada[numEntradas] = MyIO.readLine();
        while(isFim(entrada[numEntradas]) == false)
        {   
            numEntradas++;
            entrada[numEntradas] = MyIO.readLine();
        }
        //codificar a mensagem
        for(int i = 0; i < numEntradas; i++)
        {
            entrada[i] = codificador(entrada[i]);
            MyIO.println(entrada[i]);
            
        }
        
    }
}
/*----------------Testes-----------------
Input                                     /Output
cifra                                     /fliud
Algoritmos e Estrutura de Dados II        /Dojrulwprv#h#Hvwuxwxud#gh#Gdgrv#LL
teste do algoritmo                        /whvwh#gr#dojrulwpr
ciencia da computacao                     /flhqfld#gd#frpsxwdfdr
*/ 
