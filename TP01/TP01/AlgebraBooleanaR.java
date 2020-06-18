/*--------------------TP01---------------------
Aluna: Ana Laura Fernandes de Oliveira
Matricula: 680715
Disciplina: Algoritmos e Estrutura de dados II
Professor: Max do Val Machado
----------------------------------------------- */
public class AlgebraBooleanaR
{
    //realizar substituicao dos valores na string e remover caracteres "desnecessarios"
    public static String replace (String original, String substring, String alterar)
    {
        return(replace(original, substring, alterar, 0));
    }
    private static String replace (String original, String substring, String alterar, int i)
    {
        String resposta = "";
        int j = 0;
        if(i < original.length())
        {
            //se for para substituir duas letras na string
            if(substring.length() == 2)
            {	
		//ao encontrar um caracter igual ao que deve ser substituido, faze-lo
                if(original.charAt(i) == substring.charAt(j))
                {
                    resposta += alterar.charAt(j);
                }
                else if(original.charAt(i) == substring.charAt(j + 1))
                {
                    resposta += alterar.charAt(j + 1);
                }
                //remover os caracteres desnecessarios da string
                else if(original.charAt(i) != ' ' && original.charAt(i) != 'n' && original.charAt(i) != ','
                && original.charAt(i) != 'o' && original.charAt(i) != 'd' && original.charAt(i) != 'A' && original.charAt(i) != 'B'
                && original.charAt(i) != 'C')
                {
                    resposta += original.charAt(i);
                }
                i++;
            }
            //se for para substituir tres letras na string
            else if(substring.length() == 3)
            {
                if(original.charAt(i) == substring.charAt(j))
                {
                    resposta += alterar.charAt(j);
                }
                else if(original.charAt(i) == substring.charAt(j + 1))
                {
                    resposta += alterar.charAt(j + 1);
                }
                else if(original.charAt(i) == substring.charAt(j + 2))
                {
                    resposta += alterar.charAt(j + 2);
                }
                else if(original.charAt(i) != ' ' && original.charAt(i) != 'n' && original.charAt(i) != ','
                && original.charAt(i) != 'o' && original.charAt(i) != 'd' && original.charAt(i) != 'A' && original.charAt(i) != 'B'
                && original.charAt(i) != 'C')
                {
                    resposta += original.charAt(i);
                }
                i++;
            }
	    //percorrer toda a string buscando os caracteres a serem substituidos ou excluidos e por fim retornar a nova string
            resposta += replace(original, substring, alterar, i);
        }
        return(resposta);
    }
    //funcao para descobrir a posicao do parenteses que encerra a operacao a ser realizada
    public static int posicaoparenteses(String frase, int i)
    {
        int parentese;
        if(frase.charAt(i) == ')')
        {
            parentese = i;
        }
        else
        {	
	    //enquanto nao encontrar o parentese, percorrer a string
            parentese = posicaoparenteses(frase, i + 1);
        }
        return parentese;
    }
    //funcao logica que verifica se a resposta final da frase operada e true ou false
    public static boolean Resposta(String resp)
	{
		boolean resposta = false;
		if(resp.charAt(0) == '1')
		{
			resposta = true;
		}
		return resposta;
    }
    //funcao que realiza a operacao AND
    public static boolean and(String frase, int i)
    {
        boolean resposta;
        if(i == (frase.length() - 1)) //se percorrer toda a string e nao encontrar nenhum 0, atribui verdadeiro
        {
            resposta = true;
        }
        else if(frase.charAt(i) == '0') //ao encontrar um caracter 0 ja finaliza a recursividade e atribui falso
        {
            resposta = false;
        }
        else
        {
            resposta = and(frase, i + 1);
        }

        //MyIO.println("RESPOSTA AND FINAL: " + resposta);
        return resposta;
    }
    //funcao que realiza a operacao OR
    public static boolean or(String frase, int i)
    {
        boolean resposta;

        if(i == frase.length() - 1) //se nao encontrar nenhum 1, a resposta da operacao e dada como falsa
        {
            resposta = false;
        }
        else if(frase.charAt(i) == '1') //ao encontrar um caracter == 1, sabe-se que a resposta da OR e verdadeira
        {
            resposta = true;
        }
        else
        {
            resposta = or(frase, i + 1);
        }
        return resposta;
    }
    //funcao que realiza a operacao NOT
    public static boolean not(String frase, int i)
    {
        boolean resposta;
        if(i < frase.length() && frase.charAt(i) == '0') //se encontrar 0, torna-lo 1
        {
            resposta = true;
            i = frase.length();
        }
        else if(i < frase.length() && frase.charAt(i) == '1') //se encontrar 1, torna-lo 0
        {
            resposta = false;
            i = frase.length();
        }
        else
        {
            resposta = not(frase, i + 1);
        }
        return resposta;
    }
    //funcao que cria uma copia da parte da string a ser operada
    public static String substring(String frase, int pinicial, int pfinal)
    {
        return(substring(frase, pinicial, pfinal, 0));
    }
    private static String substring(String frase, int pinicial, int pfinal, int i)
    {
        //declarar variaveis
        String substring = "";
        //verificar se posicoes sao validas
        if(i < frase.length() && pinicial >= 0 && pfinal < frase.length())
        {
	    //criar a substring com os caracteres da string original localizados entre e nas posicoes inicial e final passadas como parametro
            if(pinicial <= i && i <= pfinal)
            {
                substring += frase.charAt(i) + substring(frase, pinicial, pfinal, i + 1);
            }
            else
            {
                substring += substring(frase, pinicial, pfinal, i + 1);
            }
        }
        return(substring);
    }
    //funcao que ira chamar as operacoes a serem feitas e substituir a substring operada pelo seu resultado na string original passada como parametro
    public static String erase(String frase, char operacao, int pinicial, int pfinal)
    {
        return(erase(frase, operacao, pinicial, pfinal, 0));
    }
    private static String erase (String frase, char operacao, int pinicial, int pfinal, int i)
    {
        //declarar variaveis
	String operada = "";
        String substring = "";
        //testar se posicoes sao validas
        if(i < frase.length() && 0 <= pinicial && pfinal < frase.length())
        {
            //obter copia da parte da string a ser operada
            substring = substring(frase, pinicial, pfinal);
            //apagar na string original a parte que sera operada para substituir apenas pelo resultado dessa operacao
            if(pinicial < i && i <= pfinal)
            {
                operada += erase(frase, operacao, pinicial, pfinal, i + 1);
            }
            else if(i == pinicial)
            {
		//realizar a operacao da vez e colocar o seu resultado no lugar da substring apagada na string original
                if(operacao == 'a') //operacao AND
                {
                    if(and(substring, 0))
                    {
                        operada += "1" + erase(frase, operacao, pinicial, pfinal, i + 1);
                    }
                    else
                    {
                        operada += "0" + erase(frase, operacao, pinicial, pfinal, i + 1);
                    }
                }
                else if(operacao == 'r') //operacao OR
                {
                    if(or(substring, 0))
                    {
                        operada += "1" + erase(frase, operacao, pinicial, pfinal, i + 1);
                    }
                    else
                    {
                        operada += "0" + erase (frase, operacao, pinicial, pfinal, i + 1);
                    }
                }
                else if(operacao == 't') //operacao NOT
                {
                    if(not(substring, 0))
                    {
                        operada += "1" + erase(frase, operacao, pinicial, pfinal, i + 1);
                    }
                    else
                    {
                        operada += "0" + erase(frase, operacao, pinicial, pfinal, i + 1);
                    }
                }
            } //caso o i nao corresponda a nenhum index da operacao realizada, copiar o restante da string original para a string operada
            else 
            {
                operada += frase.charAt(i) + erase(frase, operacao, pinicial, pfinal, i + 1);
            }
        }
        //retornar a string apos a operacao ser feita
        return operada;
    }
    //funcao que determina as operacoes a serem realizadas e realiza a chamada das demais funcoes ate obter o resultado final
    public static String operar(String frase)
    {
        return(operar(frase, frase.length() - 1)); //a leitura da string sera feita ao contrario
    }
    private static String operar(String frase, int i)
    {
        //declarar variaveis
	char operacao = '-';
        int posicao = 0;
        String booleana = frase;
	//enquanto nao restar somente o caracter correspondente a resposta da operacao da frase inteira
        if(booleana.charAt(0) != '1' && booleana.charAt(0) != '0')
        {
            //encontrar o parentese que abre a proxima operacao a ser feita
            if(booleana.charAt(i) == '(')
            {
                //descobrir a operacao a ser realizada
                if(booleana.charAt(i - 1) == 'a')
                {
                    operacao = 'a'; //operacao AND
                }
                else if(booleana.charAt(i - 1) == 't')
                {
                    operacao = 't'; //operacao NOT
                }
                else if(booleana.charAt(i - 1) == 'r')
                {
                    operacao = 'r'; //operacao OR
                }
                posicao = posicaoparenteses(booleana, i); //descobrir em qual posicao esta o parenteses que fecha a operacao
                //receber a string booleana apos realizar a operacao da vez
                booleana = erase(booleana, operacao, i - 1, posicao);
                //chamada recursiva para continuar as operacoes
                booleana = operar(booleana, i - 1);
            }
            else
            {
		//chamada recursiva enquanto nao encontrar o parentese da proxima operacao
                booleana = operar(booleana, i - 1);
            }
        }
        return(booleana);
    }
    public static void main(String[] args)
    {
        int tamanhoSubstring = 1;
		while(tamanhoSubstring != 0)
		{
			//declarar variaveis
			String entrada = MyIO.readLine();
			char[] aux = new char[3];
			char[] aux2 = new char[entrada.length()];
            		int j = 0, k = 0;
            		String resp = "";
			boolean resposta = false;
			//verificar o numero de variaveis na expressao algebrica
			tamanhoSubstring = entrada.charAt(0) - 48;
			String substring = "";
			if(tamanhoSubstring != 0)
			{
				//montar substring com as variaveis
				if(tamanhoSubstring == 2)
				{
					substring = "AB";
				}
				else if(tamanhoSubstring == 3)
				{	
					substring = "ABC";
				}
				//receber valores correspondentes as variaveis
				for(int i = 0; i < entrada.length(); i++)
				{
					if(entrada.charAt(i) == '0' || entrada.charAt(i) == '1')
					{
						aux[j] = entrada.charAt(i);
						j++;
					}
					else if(!('0' <= entrada.charAt(i) && entrada.charAt(i) <= '9'))
					{
						aux2[k] = entrada.charAt(i);
						k++;
					}
				}
				String valores = new String(aux);
				String algebrica = new String(aux2);
				//substituir valores na string de entrada
                		String frase = replace(algebrica, substring, valores);
				//verificar a resposta da operacao e imprimi-la como 1 (se TRUE) ou 0 (se FALSE)
                		resp = operar(frase);
                		resposta = Resposta(resp);
				if(resposta)
				{
                    			MyIO.println("1");
				}
				else
				{
                    			MyIO.println("0");
				}
			}
		}
	}
}
