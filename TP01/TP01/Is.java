/*----------------------TP1---------------------
 * Aluna: Ana Laura Fernandes de Oliveira
 * Matricula: 680715
 * Disciplina: Algoritmos e Estrutura de Dados II
 * Professor: Max do Val Machado
 * ---------------------------------------------*/
public class Is
{
    //funcao logica para vericar se o char e uma letra
    public static boolean isVogal(char letra)
    {
        return(letra == 'A' || letra == 'a' || letra == 'E' || letra == 'e' || letra == 'I'
        || letra == 'i' || letra == 'O' || letra == 'o' || letra == 'U' || letra == 'u');
    }

    //funcao logica para verificar se o char e um digito que pode compor um numero inteiro
    public static boolean isDigit(char num)
    {
		return('0' <= num && num <= '9');
    }

    //funcao logica para verificar se o char pertence a um numero real
    public static boolean isReal(char num)
    {
		return((num == ',') || (num == '.'));
    }
    
    //funcao para verificar se a string contem apenas vogais    
    public static boolean onlyVogal(String frase)
    {
        int tamanho = frase.length();
		boolean resposta = true;
        for(int i = 0; i < frase.length(); i++)
        {
            if((isVogal(frase.charAt(i)) == false) || isReal(frase.charAt(i)) == true)
            {
                resposta = false;
                i = tamanho;
            }
        }
        return resposta;
    }
    
    //funcao para verificar se a string contem apenas consoantes
    public static boolean onlyConsonant(String frase)
    {
		int tamanho = frase.length();
		boolean resposta = true;
			
		//caso encontre alguma vogal, numero ou caracter diferente de letra na frase, atribui-se falso a resposta
		for(int i = 0; i < tamanho; i++)
		{
			if(isVogal(frase.charAt(i)) || (isReal(frase.charAt(i))) || (isDigit(frase.charAt(i))))
			{
				resposta = false;
				i = tamanho;
			}
		}
		return resposta;
   }
   
   //funcao para verificar se a string e um numero inteiro
   public static boolean isInteger(String frase)
   {
		int tamanho = frase.length();
     	boolean resposta = true;
	
	//caso encontre caracter diferente de digito, atribui falso a resposta
	for(int i = 0; i < tamanho; i++)
	{
	   	if(!(isDigit(frase.charAt(i))))
		{
			resposta = false;
		}
	}
	return resposta;
   }
   
   //funcao para verificar se a string e um numero real
   public static boolean realNum(String frase)
   {
		int tamanho = frase.length();//tamanho da string
        boolean resposta = true;
		int cont = 0; //contador para pontos e virgulas
		
		//processo de validacao da string como num real
		for(int i = 0; i < tamanho; i++)
		{
			if(isReal(frase.charAt(i))) //caso a string tenha mais de um ponto ou virgula, nao sera considerado num real
			{
				cont++;
				if(cont >= 2)
				{
					resposta = false;
					i = tamanho;
				}
			}
			else if(!(isDigit(frase.charAt(i))) && !(isReal(frase.charAt(i)))) //caso encontra caracter diferente de digito, ponto ou virgula, atribui falso a resposta
			{
				resposta = false;
				i = tamanho;
			}
		}

		return resposta;
   }
   //procedimento para printar a resposta
   public static void print(boolean resposta)
   {
	   if(resposta)
	   {
		   MyIO.print("SIM ");
	   }
	   else
	   {
		   MyIO.print("NAO ");
	   }
   }
   //funcao para verificar se chegou ao fim
   public static boolean isFim(String frase)
   {
		return(frase.length()==3 && frase.charAt(0) == 'F' && frase.charAt(1) == 'I' && frase.charAt(2) == 'M');
   }
   public static void main(String[] args)
   {
		//declarar variaveis
		String[] entradas = new String[1000];
		boolean resposta = false;
		int numEntradas = 0;
		//receber entradas enquanto nao chegar na palavra FIM
		entradas[numEntradas] = MyIO.readLine();
		while(!(isFim(entradas[numEntradas])))
		{
			numEntradas++;
			entradas[numEntradas] = MyIO.readLine();
		}
		//fazer as verificacoes e imprimir as respostas
		for(int i = 0; i < numEntradas; i++)
		{
			resposta = onlyVogal(entradas[i]);
			print(resposta);
			resposta = onlyConsonant(entradas[i]);
			print(resposta);
			resposta = isInteger(entradas[i]);
			print(resposta);
			resposta = realNum(entradas[i]);
			print(resposta);
			MyIO.println("");
		}
	}
}

/*--------------------TESTES------------------
 * ENTRADA                    SAIDA
 * banana                     NAO NAO NAO NAO 
 * aeiou                      SIM NAO NAO NAO
 * bnms                       NAO SIM NAO NAO
 * 12034                      NAO NAO SIM NAO
 * ,142                       NAO NAO NAO SIM
 * ,124,145                   NAO NAO NAO NAO
 * 0,124                      NAO NAO NAO SIM
 * FIM
 * */
