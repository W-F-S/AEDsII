/*--------- TP02 ------------
 *@Author Ana Laura Fernandes de Oliveira
 *Matricula: 680715
 *Professor: Max do Val Machado
 *---------------------------*/

class Personagem
{
	//declaracao de atributos
	private String nome;
	private int altura;
	private double peso;
	private String corDoCabelo;
	private String corDaPele;
	private String corDosOlhos;
	private String anoNascimento;
	private String genero;
	private String homeworld;

	//construtor vazio
	public Personagem()
	{
		this.nome = this.corDoCabelo = this.corDaPele = this.corDosOlhos = this.anoNascimento = this.genero = this.homeworld = "";
	       	this.altura = 0;
		this.peso = 0.0;
	}

	//construtor que recebe parametros
	public Personagem(String nome, int altura, double peso, String corDoCabelo, String corDaPele, String corDosOlhos, 
			String anoNascimento, String genero, String homeworld)
	{
		this.nome = nome;
		this.altura = altura;
		this.peso = peso;
		this.corDoCabelo = corDoCabelo;
		this.corDaPele = corDaPele;
		this.corDosOlhos = corDosOlhos;
		this.anoNascimento = anoNascimento;
		this.genero = genero;
		this.homeworld = homeworld;
	}
	
	//metodos gets para verificar os atributos do objeto da classe
	public String getNome()
	{
		return this.nome;
	}
	public int getAltura()
	{
		return this.altura;
	}
	public double getPeso()
	{
		return this.peso;
	}
	public String getCorDoCabelo()
	{
		return this.corDoCabelo;
	}
	public String getCorDaPele()
	{
		return this.corDaPele;
	}
	public String getCorDosOlhos()
	{
		return this.corDosOlhos;
	}
	public String getAnoNascimento()
	{
		return this.anoNascimento;
	}
	public String getGenero()
	{
		return this.genero;
	}
	public String getHomeworld()
	{
		return this.homeworld;
	}
	//metodos sets para atribuir valores aos atributos do objeto
	public void setNome(String nome)
	{
		this.nome = nome;
	}
	public void setAltura(int altura)
	{
		this.altura = altura;
	}
	public void setPeso(double peso)
	{
		this.peso = peso;
	}
	public void setCorDoCabelo(String corDoCabelo)
	{
		this.corDoCabelo = corDoCabelo;
	}
	public void setCorDaPele(String corDaPele)
	{
		this.corDaPele = corDaPele;
	}
	public void setCorDosOlhos(String corDosOlhos)
	{
		this.corDosOlhos = corDosOlhos;
	}
	public void setAnoNascimento(String anoNascimento)
	{
		this.anoNascimento = anoNascimento;
	}
	public void setGenero(String genero)
	{
		this.genero = genero;
	}
	public void setHomeworld(String homeworld)
	{
		this.homeworld = homeworld;
	}
	//clonar objeto Personagem
	public Personagem clone(Personagem other)
	{
		Personagem clone = new Personagem(other.nome, other.altura, other.peso, other.corDoCabelo, other.corDaPele, other.corDosOlhos,other.anoNascimento, other.genero, other.homeworld);
		return clone;
	}
	//imprimir os atributos de um objeto 
	public void imprimir()
	{
		MyIO.print(" ##" + this.nome + " ## " + this.altura + " ## " );
		if(this.peso % 1 == 0) //ex: se 180.0, mostrar 180
		{
			this.setPeso(0.0); //nesta atividade, os pesos dos personagens sao todos impressos como 0
			int aux = (int)this.peso;
			MyIO.print(aux);
		}
		else
		{
			this.setPeso(0.0);
			MyIO.print(this.peso);
		}
	        MyIO.println(" ##" + this.corDoCabelo + " ##" + this.corDaPele + " ##" + this.corDosOlhos + " ##" 
				+ this.anoNascimento + " ##" +  this.genero +  " ##" + this.homeworld + " ##");	
	}
	public void ler(String caminho) throws Exception
	{
		//caminho = "." + caminho;
		//declarar variaveis
		String info = "";
		String aux = "";
		String aux2 = "";
		String aux3 = "";
		byte[] isoBytes;
		int i = 0;
		int j = 0;
		int n = 0;
		boolean cont = false;
		//abrir o arquivo
		Arq.openRead(caminho);
		//ler a string com o conteudo do arquivo
		info = Arq.readAll();
		//fechar o arquivo
		Arq.close();
		//copiar somente as informacoes essenciais para definir os atributos da string obtida a partir do arquivo para uma string auxiliar
		while(i <= 8) //i comecando em 0 e indo ate 8, pois no total sao 9 atributos a serem preenchidos
		{
			//percorrer a string ate achar ':' (minha referencia para segmentar as informacoes na string)
			if(info.charAt(j) == ':')
			{
				//a variavel n funciona como um cursor. ela devera percorrer do ponto inicial ao valor de j correspondente ao index em que os ':' foi encontrado. 
				n = j + 1;
				cont = false;
				while(cont == false)
				{	
					//copiar o que for diferente de ' e de , para uma string auxiliar. quando encontrar (',) sabe-se que as informacoes de um atributo ja foram coletadas. entao os delimitadores que eu utilizei foram : e ',
					//a mesma logica se extende para o resto do codigo deste metodo
					if(info.charAt(n) == (char)(39) && info.charAt(n + 1) == ',')
					{
						
						aux += info.charAt(n);
						aux += info.charAt(n + 1);
						cont = true;
					}
					else
					{
						aux += info.charAt(n);
						n++;
					}
				}
				j++;
				i++;
			}
			else //enquanto nao encontrar ':' variar o index e continuar a busca
			{
				j++;
			}
		}
		n = i = 0; //zerar variaveis auxiliares
		int tamanho = aux.length(); //atribuir o tamanho da string auxiliar a uma variavel para evitar multiplas chamadas
	       				    //da funcao .length dentro do for

		//percorrer a string aux e atribuir os valores correspondentes aos atributos
		for(j = 0; j < tamanho; j++)
		{
			//a mesma logica explicitada anteriormente será utilizada aqui, so que dessa vez ja atribuindo os 'valores' aos dados atributos seguindo sua ordem
			if(aux.charAt(j) == (char)(39) && aux.charAt(j + 1) == ',')
			{
				if(i == 0) //atributo nome
				{
					while(n < j)
					{
						if(aux.charAt(n) != (char)39)
						{
							this.nome += aux.charAt(n);
						}
						n++;
					}
					n = j + 2;
					i++;
				}
				else if(i == 1) //atributo altura
				{
					while(n < j)
					{
						if('0' <= aux.charAt(n) && aux.charAt(n) <= '9')
						{
							aux2 += aux.charAt(n);				
						}
						n++;
					}
					if(aux2 != "") //quando nao houver um valor especificado, atribuir 0
					{
						this.altura = Integer.parseInt(aux2);
					}
					else
					{
						this.altura = 0;
					}
					n = j + 2;
					i++;
				}
				else if(i == 2) //atributo peso
				{
					while(n < j)
					{
						if(('0' <= aux.charAt(n) && aux.charAt(n) <= '9') || aux.charAt(n) == '.')
						{
							aux3 += aux.charAt(n);			 
						}
						n++;
					}
					if(aux3 != "") //quando nao houver um valor especificado, atribuir 0
					{
						this.peso = Double.parseDouble(aux3);
					}
					else
					{
						this.peso = 0;
					}
					n = j + 2;
					i++;
				} 
				else if(i == 3) //atributo corDoCabelo
				{
					while(n < j)
					{
						if(aux.charAt(n) != (char)39)
						{
							this.corDoCabelo += aux.charAt(n);
						}
						n++;
					}
					n = j + 2;
					i++;
				}
				else if(i == 4) //atributo corDaPele
				{
					while(n < j)
					{
						if(aux.charAt(n) != (char)39)
						{
							this.corDaPele += aux.charAt(n);
						}
						n++;
					}
					n = j + 2;
					i++;
				}
				else if(i == 5) //atributo corDosOlhos
				{
					while(n < j)
					{
						if(aux.charAt(n) != (char)39)
						{
							this.corDosOlhos += aux.charAt(n);
						}
						n++;
					}
					n = j + 2;
					i++;
				}
				else if(i == 6) //atributo anoNascimento
				{
					while(n < j)
					{
						if(aux.charAt(n) != (char)39)
						{
							this.anoNascimento += aux.charAt(n);
						}
						n++;
					}
					n = j + 2;
					i++;
				}
				else if(i == 7) //atributo genero
				{
					while(n < j)
					{
						if(aux.charAt(n) != (char)39)
						{
							this.genero += aux.charAt(n);
						}
						n++;
					}
					n = j + 2;
					i++;
				}
				else if(i == 8) //atributo homeworld
				{
					while(n < j)
					{
						if(aux.charAt(n) != (char)39)
						{
							this.homeworld += aux.charAt(n);
						}
						n++;
					}
					n = j;
					i++;
				}
			}
		}
	}

	//mudar o encoding de string de ISO para UTF
	public String changeEncodingISOtoUTF(String strISO) throws Exception
	{
		byte[] isoBytes = strISO.getBytes("ISO-8859-1");
		String strUTF = new String(isoBytes, "UTF-8");
		return strUTF;
	}
	//mudar o encoding da string de UTF para ISO
	public String changeEncodingUTFtoISO(String strUTF) throws Exception
	{
		byte[] isoBytes = strUTF.getBytes("UTF-8");
		String strISO = new String(isoBytes, "ISO-8859-1");
		return strISO;
	}
	
}

class ListaSequencial extends Personagem
{
	private Personagem[] lista;
	private int n; //o n aponta para a proxima posicao vaga da lista, e indica quantos elementos a lista ja possui

	//construtor
	public ListaSequencial()
	{
		this.n = 0;
	}

	//construtor alternativo
	public ListaSequencial(int tamanho)
	{
		this.lista = new Personagem[tamanho];
		this.n = 0;
	}
	//para acessar um determinado elemento da lista
	public Personagem getLista(int posicao) throws Exception
	{
		if(posicao >= n || posicao < 0)
		{
			throw new Exception("Posicao invalida");
		}
		return lista[posicao];
	}
	//------------------METODOS PARA INSERIR ELEMENTO NA LISTA--------------
	//metodo para inserir um elemento na primeira posicao da lista e desloca os demais elementos
	public void inserirInicio(Personagem personagem) throws Exception
	{
		//verificar se e possivel inserir o elemento
		if(n >= lista.length)
		{
			throw new Exception("A lista esta cheia, nao foi possivel inserir o elemento");
		}
		else
		{
			//deslocar os elementos em direcao ao fim da lista
			for(int i = n; i > 0; i--)
			{
				lista[i] = lista[i - 1];
			}

			//insere o personagem na primeira posicao da lista
			lista[0] = personagem;
			n++;
		}
	}
	//metodo para inserir um elemento na ultima posicao disponivel da lista
	public void inserirFim(Personagem personagem) throws Exception
	{
		//verificar se a lista nao esta cheia
		if(n >= lista.length)
		{
			throw new Exception("A lista esta cheia. Nao foi possivel inserir o elemento");
	
		}
		else
		{
			//inserir o personagem no fim da lista
			lista[n] = personagem;
			n++;
		}	
	}
	//metodo para inserir um elemento em uma posicao dada como parametro
	public void inserir(Personagem personagem, int posicao) throws Exception
	{
		//verificar se a posicao e valida e se a lista esta cheia
		if(n >= lista.length || posicao > n || posicao < 0)
		{
			throw new Exception("Nao foi possivel inserir o elemento.");
		}
		else
		{
			//deslocar elementos a partida posicao desejada para o final
			for(int i = n; i > posicao; i--)
			{
				lista[i] = lista[i - 1];
			}

			//inserir elemento na posicao desejada
			lista[posicao] = personagem;
			n++;
		}
	}
	//------------------METODOS PARA REMOVER ELEMENTO--------------------
	public Personagem removerInicio() throws Exception
	{
		Personagem sw = new Personagem();
		//verificar se a lista nao esta vazia
		if(n == 0)
		{
			throw new Exception("A lista esta vazia.");
		}
		else
		{
			sw = clone(lista[0]);
			//deslocar elementos da lista do final para o inicio
			for(int i = 0; i < n; i++)
			{
				lista[i] = lista[i + 1];
			}
			n--;
		}
		return sw;
	}
	//remover de uma dada posicao e remanejar os demais elementos da lista
	public Personagem remover(int posicao) throws Exception
	{
		Personagem sw = new Personagem();
		//verificar se a posicao e valida
		if(posicao >= n || posicao < 0)
		{
			throw new Exception("Posicao invalida para remocao.");
		}
		else
		{
			sw = clone(lista[posicao]);
			
			//remanejar elementos;
			for(int i = posicao; i < n; i++)
			{
				lista[i] = lista[i + 1];
			}
			n--;
		}
		return sw;
	}

	//remocao de elemento no fim
	public Personagem removerFim() throws Exception
	{
		if(n == 0)
		{
			throw new Exception("Lista vazia.");
		}
		Personagem sw = clone(lista[n - 1]);
		n--;
		return(sw);
	}

	//imprimir lista
	public void imprimirLista() throws Exception
	{
		if(n == 0)
		{
			throw new Exception("A lista esta vazia");
		}
		for(int i = 0; i < n; i++)
		{
			MyIO.print("[" + i + "]" + " ");
			lista[i].imprimir();
		}
	}
}

public class PersonagemQuestao3
{
	//testar se chegou ao fim das entradas
	public static boolean isFim(String entrada)
	{
		return(entrada.charAt(0) == 'F' && entrada.charAt(1) == 'I' && entrada.charAt(2) == 'M');
	}
	//mudar o encoding de string de ISO para UTF
	public String changeEncodingISOtoUTF(String strISO) throws Exception
	{
		byte[] isoBytes = strISO.getBytes("ISO-8859-1");
		String strUTF = new String(isoBytes, "UTF-8");
		return strUTF;
	}
	//mudar o encoding da string de UTF para ISO
	public String changeEncodingUTFtoISO(String strUTF) throws Exception
	{
		byte[] isoBytes = strUTF.getBytes("UTF-8");
		String strISO = new String(isoBytes, "ISO-8859-1");
		return strISO;
	}
	
	public static void main(String[] args) throws Exception
	{
		//declarar variaveis
		String entrada = "";
		ListaSequencial lista = new ListaSequencial(100);
		int i = 0;
		int x = 0;
		int nOperacoes = 0;
		int posicao = 0;
		String aux = "";
		String[] operacao = new String[3];
		String[] removidos = new String[30];		
		//ler primeira entrada
		entrada = MyIO.readLine();
		//montar a lista inicial	
		while(!isFim(entrada))
		{
			Personagem starWars = new Personagem();
			entrada = starWars.changeEncodingISOtoUTF(entrada);
			starWars.ler(entrada);
			lista.inserir(starWars, i);
			i++;
			entrada = MyIO.readLine();
		}
		//receber o restante da entrada
			//ler numero de operacoes a serem feitas
			nOperacoes = MyIO.readInt();
			//ler operacoes
			for(int j = 0; j < nOperacoes; j++)
			{
				Personagem starWars = new Personagem();
				aux = MyIO.readLine();
				aux = starWars.changeEncodingISOtoUTF(aux);
				operacao = aux.split(" "); //separar a string de entrada 
				
				
				//determinar qual operacao devera ser feita e realiza-la
				if(operacao[0].charAt(0) == 'I')
				{
					if(operacao[0].charAt(1) == 'I')//inserir no inicio
					{
						starWars.ler(operacao[1]);
						lista.inserirInicio(starWars);
					}
					else if(operacao[0].charAt(1) == 'F')//inserir no fim
					{
						starWars.ler(operacao[1]);
						lista.inserirFim(starWars);
					}
					else	//inserir em uma posicao desejada
					{
						starWars.ler(operacao[2]);
						posicao = Integer.parseInt(operacao[1]);
						lista.inserir(starWars, posicao);
					}
				}
				else if(operacao[0].charAt(0) == 'R')
				{
					if(operacao[0].charAt(1) == 'I')//remover do inicio
					{
						starWars = lista.removerInicio();
						removidos[x] = starWars.getNome();
						x++;
					}
					else if(operacao[0].charAt(1) == 'F')//remover do fim
					{
						starWars = lista.removerFim();
						removidos[x] = starWars.getNome();
						x++;
					}
					else //remover de uma posicao desejada
					{
						posicao = Integer.parseInt(operacao[1]);
						starWars = lista.remover(posicao);
						removidos[x] = starWars.getNome();
						x++;
					}
				}
			}
			//imprimir lista final
			for(int l = 0; l < x; l++)
			{
				MyIO.println("(R)" + removidos[l]);
			}
			lista.imprimirLista();
	}
}
