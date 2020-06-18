/*--------------------TP01---------------------
Aluna: Ana Laura Fernandes de Oliveira 
Matricula: 680715
Disciplina: Algoritmos e Estrutura de dados II
Professor: Max do Val Machado
----------------------------------------------- */
public class IsR
{
    //funcao logica para vericar se o char e uma letra vogal
    public static boolean isVogal(char letra)
    {
        return(letra == 'A' || letra == 'a' || letra == 'E' || letra == 'e' || letra == 'I'
        || letra == 'i' || letra == 'O' || letra == 'o' || letra == 'U' || letra == 'u');
    }

    //funcao logica para verificar se o char e um digito 
    public static boolean isDigit(char num)
    {
        return('0' <= num && num <= '9');
    }

    //funcao logica para verificar se o char e um que pertence somente a um numero real (ponto ou virgula)
    public static boolean isReal(char num)
    {
        return((num == ',') || (num == '.'));
    }
    
    //inicializador da funcao recursiva onlyVogal
    public static boolean onlyVogal(String frase)
    {
        return(onlyVogal(frase, 0));
    }
    //funcao recursiva para verificar se a string contem apenas vogais    
    public static boolean onlyVogal(String frase, int i)
    {
        int tamanho = frase.length();
        boolean resposta;
        if(i == tamanho)
        {
            resposta = true;
        }
        else if((isVogal(frase.charAt(i)) == false) || isReal(frase.charAt(i)) == true)
        {
            resposta = false;
        }
        else
        {
            resposta = onlyVogal(frase, i + 1);
        }
        return resposta;
    }

    //inicializador da funcao recursiva onlyConsonant
    public static boolean onlyConsonant(String frase)
    {
        return(onlyConsonant(frase, 0));
    }

    //funcao recursiva para verificar se a string contem apenas consoantes
    public static boolean onlyConsonant(String frase, int i)
    {
		int tamanho = frase.length();
		boolean resposta;
			
		//caso encontre alguma vogal, numero ou caracter diferente de letra na frase, atribui-se falso a resposta
        if(i == tamanho)
        {
            resposta = true;
        }
        else if(isVogal(frase.charAt(i)) || (isReal(frase.charAt(i))) || (isDigit(frase.charAt(i))))
        {
            resposta = false;
        }
        else
        {
            resposta = onlyConsonant(frase, i + 1);
        }
		return resposta;
   }
   
   //inicializador da funcao recursiva isInteger
   public static boolean isInteger(String frase)
   {
       return(isInteger(frase, 0));
   }

   //funcao recursiva para verificar se a string e um numero inteiro
   public static boolean isInteger(String frase, int i)
   {
		int tamanho = frase.length();
     	boolean resposta;
	
	//caso encontre caracter diferente de digito, atribui falso a resposta

        if(i == tamanho)
        {
            resposta = true;
        }
        else if(!(isDigit(frase.charAt(i))))
        {
            resposta = false;
        }
        else
        {
            resposta = isInteger(frase, i + 1);
        }
        
	
	return resposta;
   }
   
   //inicializador da funcao recursiva isInteger
   public static boolean realNum(String frase)
   {
       return(realNum(frase, 0, 0));
   }
   //funcao para verificar se a string e um numero real
   public static boolean realNum(String frase, int ponto, int i) //contador para pontos e virgulas e para percorrer a string
   {
		int tamanho = frase.length();//tamanho da string
        boolean resposta;
		 
		//processo de validacao da string como num real
        if(i == tamanho && ponto <= 1)
        {
            resposta = true;
        }
        else if((i >= tamanho && ponto > 1) || (isDigit(frase.charAt(i)) == false && isReal(frase.charAt(i)) == false) )
        {
            resposta = false;
        }
        else
        {
            if(isReal(frase.charAt(i)))
            {
                ponto++;
            }
            resposta = realNum(frase, ponto, i + 1);
            
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
		String[] entradas = new String[1000]; //numero de entradas provavel e inferior a 1000
        int numEntradas = 0;
        boolean resposta = false;

		//receber entradas enquanto nao chegar na string de parada (FIM)
		entradas[numEntradas] = MyIO.readLine();
		while(!(isFim(entradas[numEntradas])))
		{
            //realizar testes necessarios e imprimir as respostas
            resposta = onlyVogal(entradas[numEntradas]);
			print(resposta);
			resposta = onlyConsonant(entradas[numEntradas]);
			print(resposta);
			resposta = isInteger(entradas[numEntradas]);
			print(resposta);
			resposta = realNum(entradas[numEntradas]);
			print(resposta);
            MyIO.println("");
            //obter proxima entrada
            numEntradas++;
			entradas[numEntradas] = MyIO.readLine();
		}
	}
}
