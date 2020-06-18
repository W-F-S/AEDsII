
public class LAB01AquecimentoR
{
	//funcao para saber se caaracter e letra maiusculas
	public static boolean isMaiuscula(char letra) 
	{
		return ('A' <= letra && letra <= 'Z');
	}

	// conferir se a string lida corresponde a FIM
	public static boolean isFim(String frase) 
	{
		return (frase.length() == 3 && frase.charAt(0) == 'F' && frase.charAt(1) == 'I' && frase.charAt(2) == 'M');
	}

	//contar recursivamente quantas letras maiusculas a string tem
	public static int quantasMaiusculas(String frase)
	{
		return(quantasMaiusculas(frase, 0));
	}
	public static int quantasMaiusculas(String frase, int i) 
	{
		int tamanho = frase.length(); //tamanho da string
		int cont = 0;
		if(i < tamanho) //no caso estou contanto de tras pra frente, mas o resultado e o mesmo
		{
				if (isMaiuscula(frase.charAt(tamanho)) == true)
				{
					cont = 1;
				}
				cont = cont + quantasMaiusculas(frase, i + 1); //a cada char, verifica-se se ele e maiusculo ou nao. Se for, o cont e 1, senao, o cont e 0
		}	//soma-se os resultados para cada char

		return (cont); //retorna o resultado final
	}
	public static void main(String[] args)
	{
		//definir variaveis
		int numEntradas = 0;
		String[] entradas = new String[1000]; //valor escolhido de forma arbitraria
		int cont = 0;
		//ler entradas
		entradas[numEntradas] = MyIO.readLine();
		while(!(isFim(entradas[numEntradas])))
		{
			numEntradas++;
			entradas[numEntradas] = MyIO.readLine();
		}

		for(int i = 0; i < numEntradas; i++)
		{
			cont = quantasMaiusculas(entradas[i]);
			System.out.println(cont); //imprimir quantas maiusculas tem em cada frase
		}
	}

}