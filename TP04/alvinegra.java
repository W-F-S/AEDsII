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
		caminho = "." + caminho;
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
	protected Personagem elemento;
	protected No esq;
	protected No dir;
	protected boolean cor;

	//construtores
	public No()
	{
		this(null);
	}
	public No(Personagem x)
	{
		this(x, false);
	}
	public No(Personagem x, boolean cor)
	{
		this.elemento = x;
		this.cor = cor;
		this.esq = this.dir = null;
	}
	public No(Personagem x, No e, No d, boolean cor)
	{
		this.elemento = x.clone(x);
		this.dir = d;
		this.esq = e;
		this.cor = cor;
	}
}

class Arvore extends No
{
	//atributo
	public No raiz;
	//construtor
	public Arvore()
	{
		this.raiz = null;
	}
	//metodo para inserir na arvore
	public void balancear(No bisavo, No avo, No pai, No i)
	{
		//se o pai e preto tambem, rotacionar avo
		if(pai.cor == true)
		{
			if(pai.elemento.getNome().compareTo(avo.elemento.getNome()) > 0)
			{
				if(i.elemento.getNome().compareTo(pai.elemento.getNome()) > 0)
				{
					avo = rotacaoEsq(avo);
				}
				else
				{
					avo = rotacaoDirEsq(avo);
				}
			}
			else
			{
				if(i.elemento.getNome().compareTo(pai.elemento.getNome()) < 0)
				{
					avo = rotacaoDir(avo);
				}
				else
				{
					avo = rotacaoEsqDir(avo);
				}
			}
			if(bisavo == null)
			{
				raiz = avo;
			}
			else
			{
				if(avo.elemento.getNome().compareTo(bisavo.elemento.getNome()) < 0)
				{
					bisavo.esq = avo;
				}
				else
				{
					bisavo.dir = avo;
				}
			}
			//apos a rotacao, o avo fica com cor branca e os seus filhos com cor preta
			avo.cor = false;
			avo.esq.cor = avo.dir.cor = true;
		}
	}
	private No rotacaoEsq(No no)
	{
		No noDir = no.dir;
		No noDirEsq = noDir.esq;
		//rotacionar
		noDir.esq = no;
		no.dir = noDirEsq;
		return noDir;
	}
	private No rotacaoDir(No no)
	{
		No noEsq = no.esq;
		No noEsqDir = noEsq.dir;
		//rotacionar
		noEsq.dir = no;
		no.esq = noEsqDir;
		return noEsq;
	}
	private No rotacaoDirEsq(No no)
	{
		no.dir = rotacaoDir(no.dir);
		return rotacaoEsq(no);
	}
	private No rotacaoEsqDir(No no)
	{
		no.esq = rotacaoEsq(no.esq);
		return rotacaoDir(no);
	}
	public void inserir(Personagem x) throws Exception
	{
		//se a arvore estiver vazia
		if(raiz == null)
		{
			raiz = new No(x, false);
		}
		//se a arvore tiver um elemento
		else if(raiz.dir == null && raiz.esq == null)
		{
			if(raiz.elemento.getNome().compareTo(x.getNome()) > 0)
			{
				raiz.esq = new No(x, true); //insere com cor preta
			}
			else
			{
				raiz.dir = new No(x, true);
			}
		}
		//se a arvore tiver raiz e dir
		else if(raiz.esq == null)
		{
			if(raiz.elemento.getNome().compareTo(x.getNome()) > 0)
			{
				raiz.esq = new No(x, true);
			}
			else if(raiz.dir.elemento.getNome().compareTo(x.getNome()) > 0)
			{
				//ja realiza o balanceamento para evitar nós pretos consecutivos
				raiz.esq = new No(raiz.elemento);
				raiz.elemento = x;
			}
			else
			{
				//ja realiza o balanceamento tambem
				raiz.esq = new No(raiz.elemento);
				raiz.elemento = raiz.dir.elemento;
				raiz.dir.elemento = x;
			}
			//antecipando a operacao de balanceamento, os tres primeiros elementos da arvore alvinegra tem cor branca, uma vez que a proxima insercao forcaria a alteracao das cores
			raiz.esq.cor = raiz.dir.cor = false;
		}
		//se a arvore tiver os elementos da raiz e da esquerda, e nao tiver o da raiz.dir
		else if(raiz.dir == null)
		{
			if(raiz.elemento.getNome().compareTo(x.getNome()) < 0)
			{
				raiz.dir = new No(x);
			}
			else if(raiz.esq.elemento.getNome().compareTo(x.getNome()) < 0)
			{
				raiz.dir = new No(raiz.elemento);
				raiz.elemento = x;
			}
			else
			{
				raiz.dir = new No(raiz.elemento);
				raiz.elemento = raiz.esq.elemento;
				raiz.esq.elemento = x;
			}
			raiz.esq.cor = raiz.dir.cor = false;
		}
		//caso em que a arvore tem tres ou mais elementos
		else
		{
			inserir(x, null, null, null, raiz);
		}

		raiz.cor = false;
	}		
	public void inserir(Personagem x, No bisavo, No avo, No pai, No i) throws Exception
	{
		if(i == null)
		{
			if(x.getNome().compareTo(pai.elemento.getNome()) < 0)
			{
				i = pai.esq = new No(x, true);
			}
			else
			{
				i = pai.dir = new No(x, true);
			}
			if(pai.cor == true)
			{
				balancear(bisavo, avo, pai, i);
			}
		}
		else 
		{
			//se o no for do tipo 4
			if(i.esq != null && i.dir != null && i.esq.cor == true && i.dir.cor == true)
			{
				i.cor = true;
				i.esq.cor = i.dir.cor = false;
				if(i == raiz)
				{
					i.cor = false;
				}
				else if(pai.cor == true)
				{
					balancear(bisavo, avo, pai, i);
				}
			}
			if(x.getNome().compareTo(i.elemento.getNome()) < 0)
			{
				inserir(x, avo, pai, i, i.esq);
			}
			else if(x.getNome().compareTo(i.elemento.getNome()) > 0)
			{
				inserir(x, avo, pai, i, i.dir);
			}
			else
			{
				throw new Exception("elemento repetido");
			}
		}
	}
	//metodo para mostrar a arvore (mostrar Pre)
	public void mostrarPre()
	{
		mostrarPre(raiz);
	}
	public void mostrarPre(No i)
	{
		if(i != null)
		{
			i.elemento.imprimir();
			mostrarPre(i.esq);
			mostrarPre(i.dir);
		}
	}
	//metodo para pesquisar um personagem na arvore pelo nome
	public boolean pesquisa(String nome, int[] cont)	
	{
		MyIO.print(nome + " raiz ");
		return pesquisa(nome, raiz, cont);
	}
	public boolean pesquisa(String nome, No i, int[] cont)
	{
		boolean resp;
		if(i == null)
		{
			cont[0]++;
			MyIO.println(" NÃO");
			resp = false;
		}
		else if(i.elemento.getNome().compareTo(nome) == 0)
		{
			cont[0] += 2;
			MyIO.println("SIM");
			resp = true;
		}
		else if(nome.compareTo(i.elemento.getNome()) < 0)
		{
			cont[0] += 3;
			//caminhar para a esquerda
			MyIO.print("esq ");
			resp = pesquisa(nome, i.esq, cont);
		}
		else
		{
			cont[0] += 3;
			//caminhar para a direita
			MyIO.print("dir ");
			resp = pesquisa(nome, i.dir, cont);
		}
		return resp;
	}
}
public class alvinegra
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
		//ler entradas e inserir personagens na arvore
		while(!isFim(entrada))
		{
			Personagem sw = new Personagem();
			sw.ler(entrada);
			arvore.inserir(sw);
			entrada = MyIO.readLine();
		}
		//ler personagens a serem buscados na arvore
		entrada = MyIO.readLine();
		
		int[] cont = new int[2];
		long inicio = now();
		while(!isFim(entrada))
		{
			boolean resp = arvore.pesquisa(entrada, cont);
			entrada = MyIO.readLine();
		}
		long fim = now();
		//gravar arquivo de log
		Arq.openWrite("680715_alvinegra.txt");
		Arq.print("Matricula: 680715\t" + cont[0] + " comparacoes\t" + "tempo de execucao total para todas as buscas: " + (fim - inicio) + "ms\t");
		Arq.close();

	}
}
