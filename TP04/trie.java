/*--------- TP04 ------------
 *@Author Ana Laura Fernandes de Oliveira
 *Matricula: 680715
 *Professor: Max do Val Machado
 *---------------------------*/

import java.util.*;
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
					n = 1;
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
class No extends Personagem
{
	//atributos
	protected char elemento;
	int tamanho = 255;
	protected No[] prox;
	protected boolean folha;

	//construtores
	public No()
	{
		this(' ');
	}
	public No(char x)
	{
		this.elemento = x;
		this.prox = new No[tamanho];
		for(int i = 0; i < tamanho; prox[i++] = null);
		folha = false;
	}
	public int hash(char x)
	{
		return (int)x;
	}
}

class Arvore extends No
{
	//atributo
	private No raiz;
	//construtor
	public Arvore()
	{
		this.raiz = new No();
	}
	//metodo para inserir na arvore
	public void inserir(String s) throws Exception
	{
		inserir(s, this.raiz, 0);
	}
	public void inserir(String s, No no, int i) throws Exception
	{
		if(no.prox[s.charAt(i)] == null) //se nao tiver o filho
		{
			//MyIO.println("criando filho: " + s.charAt(i));
			no.prox[s.charAt(i)] = new No(s.charAt(i));

			//verificar se o char e o ultimo da palavra
			if(i == s.length() - 1)
			{
				no.prox[s.charAt(i)].folha = true;
			}
			else //caso nao for, inserir proximo char da palavra
			{
				inserir(s, no.prox[s.charAt(i)], ++i);
			}

		}
		//verificar se o no nao é folha e se i nao é o ultimo char da string
		else if(no.prox[s.charAt(i)].folha == false && i < s.length() - 1)
		{
			inserir(s, no.prox[s.charAt(i)], ++i);
		}
		//caso o no seja folha ou a palavra seja um prefixo da outra, apontar erro na insercao
		else
		{
			throw new Exception("Erro ao inserir");
		}
	}		
	//metodo para pesquisar um personagem na arvore pelo nome
	public boolean pesquisar(String s, int[] cont) throws Exception
	{
		return pesquisar(s, raiz, 0, cont);
	}
	public boolean pesquisar(String s, No no, int i, int[] cont) throws Exception
	{
		boolean resp;
		if(no.prox[s.charAt(i)] == null)
		{
			cont[0]++;
			resp = false;
		}
		//se chegar ao final da palavra
		else if(i == s.length() - 1)
		{
			//verificar se chegou ao final da palavra buscada, ou se encontrou apenas um prefixo 
			cont[0] += 2;
			resp = (no.prox[s.charAt(i)].folha == true);
		}
		else if(i < s.length() - 1)
		{
			cont[0] += 3;
			//continuar pesquisando
			resp = pesquisar(s, no.prox[s.charAt(i)], ++i, cont);
		}
		//else if(i != null && nome.compareTo(i.elemento.getNome()) > 0)
		else
		{
			cont[0] += 3;
			throw new Exception("ERRO ao pesquisar");
		}
		return resp;
	}
	//metodo para obter as strings de uma arvore
	public void  merge(Arvore dois, int[] cont) throws Exception
	{
		merge("", dois.raiz, cont);
	}
	public void merge(String s, No no, int[] cont) throws Exception
	{
		if(no.folha == true)
		{
			boolean resp = pesquisar(s + no.elemento, cont);
			if(!resp)
			{
				String S = "";
				for(int i = 1; i < s.length(); i++)
				{
					S += s.charAt(i);
				}
				inserir(S + no.elemento);
			}
		}
		else
		{
			for(int i = 0; i < no.prox.length; i++)
			{
				//verificar se tem filho
				if(no.prox[i] != null)
				{
					merge(s + no.elemento, no.prox[i], cont);
				}
			}
		}
	}
	public void mostrar()
	{
		mostrar("", raiz);
	}
	public void mostrar(String s, No no)
	{
		if(no.folha == true)
		{
			MyIO.println(s + no.elemento);
		}
		else
		{
			for(int i = 0; i < no.prox.length; i++)
			{
				if(no.prox[i] != null)
				{
					mostrar(s + no.elemento, no.prox[i]);
			
				}
			}
		}
	}
}
public class trie
{
	//obter tempo em instante de execucao
	public static long now()
	{
		return new Date().getTime();
	}
	public static boolean isFim(String x)
	{
		return(x.charAt(0) == 'F' && x.charAt(1) == 'I' && x.charAt(2) == 'M');
	}
	public static void main(String[] args) throws Exception
	{
		//variaveis e estrutura
		Arvore arvore = new Arvore();
		String entrada = MyIO.readLine();
		//ler entradas e inserir personagens na primeira arvore
		while(!isFim(entrada))
		{
			Personagem sw = new Personagem();
			sw.ler(entrada); 
			arvore.inserir(sw.getNome());
			entrada = MyIO.readLine();
		}
		//ler entradas e inserir personagens na segunda arvore
		Arvore dois = new Arvore();
		entrada = MyIO.readLine();
		while(!isFim(entrada))
		{
			Personagem sw = new Personagem();
			sw.ler(entrada);
			dois.inserir(sw.getNome());
			entrada = MyIO.readLine();
		}
		//fazer o merge das arvores
		int[] cont = new int[2];
		arvore.merge(dois, cont);
		//ler personagens a serem buscados na arvore
		entrada = MyIO.readLine();
		
		long inicio = now();
		while(!isFim(entrada))
		{
			boolean resp = arvore.pesquisar(entrada, cont);
			if(resp)
			{
				MyIO.println(entrada + " SIM");
			}
			else
			{
				MyIO.println(entrada + " NÃO");
			}
			entrada = MyIO.readLine();
		}
		long fim = now();
		//gravar arquivo de log
		Arq.openWrite("680715_arvoreTrie.txt");
		Arq.print("Matricula: 680715\t" + cont[0] + " comparacoes\t" + "tempo de execucao total para todas as buscas: " + (fim - inicio) + "ms\t");
		Arq.close();

	}
}
