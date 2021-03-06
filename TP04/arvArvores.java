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
	protected int chave;
	protected No esq;
	protected No dir;
	protected No2 arv; //ponteiro para nova arvore
	//construtores
	public No()
	{
		this.esq = this.dir = this.arv = null;
		this.chave = 0;
	}
	public No(int x)
	{
		this.chave = x;
		this.esq = this.dir = this.arv = null;
	}
	public No(int x, No e, No d)
	{
		this.chave = x;
		this.dir = d;
		this.esq = e;
		this.arv = null;
	}
}
class No2 extends No
{
	//atributo
	protected String nome;
	protected No2 dir;
	protected No2 esq;

	//construtores
	public No2()
	{
		this(" ");
	}
	public No2(String x)
	{
		this.nome = x;
		this.dir = this.esq = null;
	}
}
class Arvore extends No2
{
	//atributo
	public No raiz;
	//construtor
	public Arvore()
	{
		this.raiz = null;
	}
	//metodo para criar primeira arvore
	public void inicio(int x)
	{
		raiz = inicio(x, raiz);
	}
	public No inicio(int x, No i)
	{
		if(i == null)
		{
			i = new No(x);
		}
		else if(x < i.chave)
		{
			i.esq = inicio(x, i.esq);
		}
		else if(x > i.chave)
		{
			i.dir = inicio(x, i.dir);
		}
		return i;
	}
	//metodo para inserir personagens na arvore
	public void inserir(Personagem x) throws Exception
	{
		raiz = inserir(x, raiz);
	}
	public No inserir(Personagem x, No i) throws Exception
	{
		if(x.getAltura()%15 < i.chave)
		{
			i.esq = inserir(x, i.esq);
		}
		else if(x.getAltura()%15 > i.chave)
		{
			i.dir = inserir(x, i.dir);
		}
		else //se o elemento de i for igual ao x
		{
			inserir2(x, i);
		}
		return i;
	}
	//inserir na arvore atrelada ao no da primeira arvore
	public void  inserir2(Personagem x, No i)
	{
		i.arv = inserir2(x, i.arv);
	}
	public No2 inserir2(Personagem x, No2 i)
	{

		if(i == null)
		{
			i = new No2(x.getNome());
		}
		else if(x.getNome().compareTo(i.nome) < 0)
		{
			i.esq = inserir2(x, i.esq);
		}
		else if(x.getNome().compareTo(i.nome) > 0)
		{
			i.dir = inserir2(x, i.dir);
		}
		return i;
	}
	//metodo para mostrar a arvore (mostrar Pre)
	public void mostrarPre()
	{
		mostrarPre(raiz);
		MyIO.println("");
	}
	public void mostrarPre(No i)
	{
		if(i != null)
		{
			MyIO.print(i.chave + " ");
			mostrarPre(i.esq);
			mostrarPre(i.dir);
		}
	}
	//metodo para pesquisar um personagem na arvore pela altura e entao pelo nome
	public boolean pesquisa(String x, int[] cont)
	{
		MyIO.print(x + " raiz ");
		return pesquisa(x, raiz, cont);
	}
	public boolean pesquisa(String x, No i, int[] cont)
	{
		boolean resp;
	
		if(i != null)
		{
			//busca na arvore do no
			resp = buscar(x, i.arv, cont);
			if(!resp)
			{
				MyIO.print("esq ");
				resp = resp || pesquisa(x, i.esq, cont);
				
				if(!resp)
				{
					MyIO.print("dir ");
					resp = resp || pesquisa(x, i.dir, cont);
				}
				
			}
			
			
		}
		else
		{
			resp = false;
		}
	
		return resp;
	}
	//busca na arvore do no passado por parametro
	public boolean buscar(String nome, No2 i, int[] cont)
	{
		boolean resp;
		if(i != null)
		{
			cont[0]++;
			if(nome.compareTo(i.nome) == 0)
			{
				cont[0]++;				
				resp = true;
			}
			else
			{
				cont[0]++;				
				resp = false;
			}

			if(!resp)
			{
				MyIO.print("ESQ ");
				resp = resp || buscar(nome, i.esq, cont);
				if(!resp)
				{
					MyIO.print("DIR ");
					resp = resp || buscar(nome, i.dir, cont);
				}
			}
		}
		else
		{
			cont[0]++;
			resp = false;
		}	
		return resp;
	}	
}	
public class arvArvores
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
		int[] primArvore = {7,3,11,1,5,9,12,0,2,4,6,8,10,13,14};
		//criar a primeira arvore com valores de 0 a 15
		for(int i = 0; i < 15; i++)
		{
			arvore.inicio(primArvore[i]);
		}
		while(!isFim(entrada))
		{
			Personagem sw = new Personagem();
			sw.ler(entrada);
			arvore.inserir(sw);
			entrada = MyIO.readLine();
		}
		//ler personagens a serem buscados na arvore
		int[] cont = new int[2];
		long inicio = now();			
		entrada = MyIO.readLine();
		while(!isFim(entrada))
		{
			boolean resp = arvore.pesquisa(entrada, cont);
			if(resp)
			{
				MyIO.println("SIM");
			}
			else
			{
				MyIO.println("NÃO");
			}
			entrada = MyIO.readLine();
		}
		long fim = now();
		//gravar arquivo de log
		Arq.openWrite("680715_arvoreArvore.txt");
		Arq.print("Matricula: 680715\t" + cont[0] + " comparacoes\t" + "tempo de execução: " + (fim - inicio) + "ms\t");
		Arq.close();

	}
}
