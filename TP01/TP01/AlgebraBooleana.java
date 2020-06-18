/*--------------------TP01---------------------
Aluna: Ana Laura Fernandes de Oliveira
Matricula: 680715
Disciplina: Algoritmos e Estrutura de dados II
Professor: Max do Val Machado
----------------------------------------------- */
public class AlgebraBooleana3
{
	//funcao responsavel por realizar a troca entre letras e valores e posteriormente remover os caracteres considerados
	//desnecessarios para facilitar a operacao da string
	public static String replace (String original, String substring, String alterar)
	{
        	//declarar variaveis
        	char[] aux = new char[original.length()];
        	int n = 0, y = 0;
        	//copiar a string original para o arranjo de char
        	for(int g = 0; g < original.length(); g++)
        	{
            		aux[g] = original.charAt(g);
        	}
        	//substituir os valores no arranjo de char
			for(int i = 0; i < substring.length(); i++)
			{
				for(int j = 0; j < original.length(); j++)
				{
					if(aux[j] == substring.charAt(i))
					{
						aux[j] = alterar.charAt(n);
					}
				}
				if(n < alterar.length())
				{
					n++;
				}
			}
			//limpar o arranjo removendo os espacos em branco, virgulas e substituindo And por a, Or por r, Not por t;
			char[] aux2 = new char[original.length()];
			for(int x = 0; x < original.length(); x++)
			{
				if(aux[x] == 'a' || aux[x] == 'r' || aux[x] == 't' || aux[x] == '(' || aux[x] == ')' || aux[x] == '1' || aux[x] == '0')
				{
					aux2[y] = aux[x];
					y++;
				}
			}
        	//criar a string a partir do arranjo e retornar a mesma
			String resposta = new String(aux2);
			return resposta;
	}
	//funcao que realiza a operacao AND e retorna um valor logico para a operacao
	public static boolean and(String operacao)
	{
		boolean resposta = true;
		for(int i = 0; i < operacao.length(); i++)
		{
			if(operacao.charAt(i) == '0')
			{
				resposta = false;
				i = operacao.length();
			}
		}
		return resposta;
	}
	//funcao que realiza a operacao OR e retorna um valor logico para a operacao
	public static boolean or(String operacao)
	{
		boolean resposta = false;
		for(int i = 0; i < operacao.length(); i++)
		{
			if(operacao.charAt(i) == '1')
			{
				resposta = true;
				MyIO.println("RESPOSTA TRUE OR: " + resposta);
				i = operacao.length();
			}
		}
		MyIO.println("RESPOSTA OR: " + resposta);
		return resposta;
	}
	//funcao que realiza a operacao NOT e retorna um valor logico para a operacao
	public static boolean not(String operacao)
	{
		boolean resposta = false;
		for(int i = 0; i < operacao.length(); i++)
		{
			if(operacao.charAt(i) == '1')
			{
				resposta = false;
				i = operacao.length();
			}
			else if(operacao.charAt(i) == '0')
			{
				resposta = true;
				i = operacao.length();
			}
		}
		return resposta;
	}
	//funcao que gerencia todas as operacoes a serem feitas. Analisa qual e a operacao no momento, chama a funcao que a realiza
	//gera a string modificada com o valor da operacao realizada e chama a funcao que remove espacos vazios para entregar a nova 
	//string pronta para continuar sendo operada, ate se obter a resposta final
	public static String erase (String frase, int pinicial, int pfinal, char operacao)
	{
		//declarar variaveis
		char[] aux = new char[frase.length()];
		String substring = "";
		boolean resp = false;
		//verificar se as posicoes passadas por parametro sao validas
		if(pinicial >= 0 && pfinal < frase.length())
		{
			//copiar a parte da string a ser operada para uma string auxiliar
			for(int i = pinicial; i <= pfinal; i++)
			{
				substring += frase.charAt(i);
			}
			//apagar as posicoes que serao operadas
			for(int i = 0; i < frase.length(); i++)
			{
				if(pinicial <= i && i <= pfinal)
				{
					aux[i] = ' ';
				}
				else
				{
					aux[i] = frase.charAt(i);
				}
			}
			//operar
			if(operacao == 'a')
			{
				resp = and(substring);
			}
			else if(operacao == 'r')
			{
				resp = or(substring);
			}
			else if(operacao == 't')
			{
				resp = not(substring);
			}
			//registrar a resposta da operacao feita
			if(resp)
			{
				aux[pinicial] = '1';
			}
			else
			{
				aux[pinicial] = '0';
			}
		}
		String erase = new String(aux);
		//chamar a funcao que ira remover espacos vazios da string
		return(limpar(erase));
	}
	public static String limpar (String frase)
	{
		//declarar variaveis
		char[] aux = new char[frase.length()];
		int j = 0;
		//buscar espacos vazios e remove-los da string
		for(int i = 0; i < frase.length(); i++)
		{
			if(frase.charAt(i) != ' ')
			{
				aux[j] = frase.charAt(i);
				j++;
			}
		}
		String limpa = new String(aux);
		//retornar a string apos a 'limpeza' para voltar a ser operada
		return limpa;
	}
	//verifica a resposta da operacao final e retorna uma resposta logica
	public static boolean Resposta(String resp)
	{
		boolean resposta = false;
		if(resp.charAt(0) == '1')
		{
			resposta = true;
		}
		return resposta;
	}
	public static boolean operar(String frase)
	{
		//declarar variaveis
		int tamanho = frase.length();
		String algebrica = frase;
		int n = tamanho - 1;
		int i = 0;
		char operacao = '-';
		//percorrer a string de tras pra frente realizando as operacoes do parentese mais interno para o mais externo
		//lembrando: and = a; or = r; not = t;
		if(algebrica.length() > 1)
		{	
			i = algebrica.length() - 1;
			while(algebrica.charAt(0) != '1' && algebrica.charAt(0) != '0')
			{
				if(algebrica.charAt(i) == '(')
				{
					//olhar a operacao que deve ser feita
					if(algebrica.charAt(i - 1) == 't')
					{
						operacao = 't';
					}
					else if(algebrica.charAt(i - 1) == 'r')
					{
						operacao = 'r';
					}
					else if(algebrica.charAt(i - 1) == 'a')
					{
						operacao = 'a';
					}
					//olhar a posicao do parentese que fecha a operacao
					for(int j = i; j < tamanho; j++)
					{
						if(algebrica.charAt(j) == ')')
						{
							n = j;
							j = tamanho;
						}
					}
					//chamar a funcao responsavel por gerenciar as operacoes a serem feitas
					algebrica = erase(algebrica, i - 1 , n, operacao); // i - 1 para substituir a operacao que ja sera realizada
					tamanho = algebrica.length();  
				}
				else
				{
					i--;
				}
			}
		}

		return(Resposta(algebrica));
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
				resposta = operar(frase);
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
