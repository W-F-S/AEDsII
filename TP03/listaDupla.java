/*--------- TP03 ------------
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
			//this.setPeso(0.0); //nesta atividade, os pesos dos personagens sao todos impressos como 0
			int aux = (int)this.peso;
			MyIO.print(aux);
		}
		else
		{
			//this.setPeso(0.0);
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

class Celula
{
	//atributos
	protected Personagem elemento;
	protected Celula prox;
	protected Celula ant;

	//construtor padrao
	public Celula()
	{
		this.elemento = new Personagem();
		this.prox = this.ant = null;
	}
	//construtor alternativo
	public Celula(Personagem sw)
	{
		this.elemento = new Personagem();
		this.elemento = elemento.clone(sw);
		this.prox = this.ant = null;
	}
}

class Lista
{
	//atributos
	Celula primeiro;
	Celula ultimo;

	//construtor
	public Lista()
	{
		this.primeiro = new Celula(); //celula cabeça
		this.ultimo = primeiro; //caracteriza lista vazia
	}

	//metodo para saber o tamanho da lista
	public int tamanhoLista()
	{
		int tamanho = 0;
		if(primeiro != ultimo)
		{
			for(Celula i = primeiro.prox; i != null; tamanho++, i = i.prox);
		}
		return tamanho;
	}
	//METODOS PARA INSERIR NA LISTA
	//inserir no inicio
	public void inserirInicio(Personagem sw)
	{
		//criar celula temporaria com o elemento a ser inserido na lista
		Celula tmp = new Celula(sw);
		//inserir celula na lista
		tmp.prox = primeiro.prox;
		tmp.ant = primeiro;
		primeiro.prox = tmp;
		primeiro.prox.ant = primeiro;
		//se a lista estiver vazia, mover o ponteiro do ultimo para o elemento inserido
		if(primeiro == ultimo)
		{
			ultimo = tmp;
		}
		else
		{
			tmp.prox.ant = tmp;
		}
		//liberar celula temporaria
		tmp = null;
	}
	public void inserirFim(Personagem sw)
	{
		ultimo.prox = new Celula(sw);
		ultimo.prox.ant = ultimo;
		ultimo = ultimo.prox;
	}
	public void inserir(Personagem sw, int posicao) throws Exception
	{
		//obter tamanho da lista
		int tamanho = tamanhoLista();
		//testar se posicao desejada e valida
		if(posicao < 0 || posicao > tamanho)
		{
			throw new Exception("ERRO. Posicao para insercao invalida.");
		}
		else if(posicao == 0)
		{
			inserirInicio(sw);
		}
		else if(posicao == tamanho)
		{
			inserirFim(sw);
		}
		else
		{
			Celula i = primeiro;
			//caminhar ate a posicao de insercao
			for( int j = 0; j < posicao; i = i.prox, j++);
			Celula temp = new Celula();
			temp.prox = i.prox;
			temp.ant = i;
			temp.ant.prox = temp;
			temp.prox.ant = temp;

			temp = null;
			i = null;
		}
	}	
	//METODOS PARA REMOVER ELEMENTO DA LISTA
	//Remover do fim
	public Personagem removerFim() throws Exception
	{
		//verificar se lista esta vazia
		if(primeiro == ultimo)
		{
			throw new Exception("Lista vazia. Não foi possivel remover.");
		}
		//remover o ultimo elemento da lista
		Personagem removido = new Personagem();
		removido = removido.clone(ultimo.elemento);
		//encontrar posicao anterior ao ultimo e mover o ponteiro do ultimo	
		ultimo = ultimo.ant;
		ultimo.prox.ant = null;
		ultimo.prox = null;
		return removido;
	}
	//Remover do inicio
	public Personagem removerInicio() throws Exception
	{
		//verificar se lista esta vazia
		if(primeiro == ultimo)
		{
			throw new Exception("Lista vazia. Não foi possivel remover o elemento");
		}
		//remover elemento
		Celula temp = new Celula();
		temp = primeiro;
		Personagem removido = temp.elemento;
		primeiro = primeiro.prox;
		temp.prox = primeiro.ant = temp = null;
		return removido;
	}
	//remover de uma posicao desejada
	public Personagem remover(int posicao) throws Exception
	{
		//obter tamanho da lista
		int tamanho = tamanhoLista();
		Personagem removido = new Personagem();
		//verificar se a lista esta vazia
		if(primeiro == ultimo)
		{
			throw new Exception("Lista vazia. Nao foi possivel remover o elemento");
		}
		//verificar se a posicao e valida
		else if(posicao < 0 || posicao >= tamanho)
		{
			throw new Exception("Posicao invalida para remocao.");
		}
		else if(posicao == 0)
		{
			removido = removerInicio();
		}
		else if(posicao == tamanho - 1)
		{
			removido = removerFim();
		}
		else
		{
			Celula i = primeiro.prox;
			//alcancar a posicao a remover
			for(int j = 0; j < posicao; i = i.prox, j++);
		        //remover elemento
			i.ant.prox = i.prox;
			i.prox.ant = i.ant;
			removido = i.elemento;
			i.prox = null;
			i.ant = null;
			i = null;
		}
		return removido;
	}
	//metodo para mostrar elementos da lista
	public void imprimirLista()
	{
		for(Celula i = primeiro.prox; i != null; i = i.prox)
		{
			i.elemento.imprimir();
		}
	}
	//metodo para imprimir ao contrario a lista
	public void imprimirContrario()
	{
		int j = 0;
		for(Celula i = ultimo; i != primeiro; i = i.ant)
		{
			MyIO.print("[" + j + "]");
			i.elemento.imprimir();
			j++;
		}
	}
	public void swap(int i, int j)
	{
		//localizar celulas a sofrerem trocas
		Celula I = primeiro;
		for(int x = 0; x <= i; I = I.prox, x++);
		Celula J = primeiro;
		for(int y = 0; y <= j; J = J.prox, y++);
		//realizar troca de elementos entre as celulas
		Personagem temp = I.elemento.clone(I.elemento);
		I.elemento = J.elemento.clone(J.elemento);
		J.elemento = temp;
		temp = null;
		I = J = null;
	}
	public void quickSort(int esq, int dir, int[] cont)
	{
		Personagem pivo = new Personagem();
		int posicao = (esq + dir)/2;
		//localizar pivo na lista
		Celula tmp = primeiro;
		for(int k = 0; k <= posicao; tmp = tmp.prox, k++);
		pivo = tmp.elemento;
		cont[1]++; //contador de movimentacoes de elementos da lista
		tmp = null;
		//iniciar ordenacao
		
		//posicionar inicialmente as celulas nos extremos direita e esquerda
		Celula i = primeiro;
		cont[1]++;
		int I = esq; 
		int J = dir;
		for(int x = 0; x <= I; i = i.prox, x++);
		Celula j = primeiro;
		cont[1]++;
		for(int y = 0; y <= J; j = j.prox, y++);
		while(I <= J)
		{
			//comparacoes entre as cores do cabelo de i com pivo e de j com pivo. É feito o desempate por nome caso as cores de cabelo sejam iguais
			while((i.elemento.getCorDoCabelo().compareTo(pivo.getCorDoCabelo()) < 0) || (i.elemento.getCorDoCabelo().compareTo(pivo.getCorDoCabelo()) == 0 && i.elemento.getNome().compareTo(pivo.getNome()) < 0))
			{
				i = i.prox;
				cont[1]++;
				I++;
				cont[0] += 3; //paga 3 comparacoes no pior caso
			}
			while((j.elemento.getCorDoCabelo().compareTo(pivo.getCorDoCabelo()) > 0) || (j.elemento.getCorDoCabelo().compareTo(pivo.getCorDoCabelo()) == 0) && j.elemento.getNome().compareTo(pivo.getNome()) > 0)
			{
				j = j.ant;
				cont[1]++;
				J--;
				cont[0] += 3;
			}
			if(I <= J)
			{
				//realiza a troca dos elementos entre as celulasnas posicoes I e J
				swap(I, J);
				cont[1] += 3; //swap faz tres movimentacoes
				I++;
				i = i.prox;
				cont[1]++;
				J--;
				j = j.ant;
				cont[1]++;
			}
		}

		//chamar a funcao recursivamente para ordenar os subconjuntos
		if(esq < J)
		{
			quickSort(esq, J, cont);
		}
		if(I < dir)
		{
			quickSort(I, dir, cont);
		}
	}

}
public class listaDupla
{
	
	//obter tempo em instante de execucao
	public static long now()
	{
		return new Date().getTime();
	}
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
		Lista lista = new Lista();
		int i = 0;

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
		//ordenar a lista usando o algoritmo do quick sort
		int[] cont = new int[2];
		long inicio = now();
		lista.quickSort(0, i - 1, cont);
		long fim = now();
		//imprimir lista ordenada
		lista.imprimirLista();
		//gravar arquivo de log
		Arq.openWrite("680715_quickSort.txt");
		Arq.print("Matricula: 680715\t" + cont[0] + " comparacoes\t" + cont[1] + " movimentacoes\t" + "tempo de ordenacao: " + (fim - inicio) + "ms\t");
		Arq.close();
	}
}
