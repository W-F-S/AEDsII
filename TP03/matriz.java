/*---------------------------------------
 * Aluna: Ana Laura Fernandes de Oliveira
 * Matricula: 680715
 * --------------------------------------*/
class Celula
{
	//atributos
	protected Celula prox;
	protected Celula ant;
	protected Celula sup;
	protected Celula inf;
	protected int elemento;
	//construtor
	public Celula(int x)
	{
		this.sup = this.ant = this.prox = this.inf = null;
		this.elemento = x;
	}
	public Celula()
	{
		this(0);
	}
}
class Matriz extends Celula
{
	//atributos
	private Celula inicio;
	private int linhas;
	private int colunas;

	//construtores
	public Matriz()
	{
		this(3, 3);
	}
	public Matriz(int linha, int coluna)
	{
		this.linhas = linha;
		this.colunas = coluna;
		
		//criar primeira celula
		this.inicio = new Celula();
		Celula i, j;
		//criar colunas da primeira linha
		int l, c;
		for(i = inicio, c = 1; c < this.colunas; i = i.prox, c++)
		{
			i.prox = new Celula();
			i.prox.ant = i;
		}
		//criar linhas
		for(i = inicio, l = 1; l < this.linhas; i = i.inf, l++)
		{
			i.inf = new Celula();
			i.inf.sup = i;
			for(j = i.inf, c = 1; c < this.colunas; j = j.prox, c++)
			{
				j.prox = new Celula();
				j.prox.ant = j;
				j.prox.sup = j.sup.prox;
				j.sup.prox.inf = j.prox;
			}
		}
	}
	//metodo para preencher a matriz com valores recebido por parametro
	public void setElementos(int[][] array)
	{
		//definir variaveis
		int x, y;
		Celula i, j;
		//percorrer a matriz inserindo elementos do array nas celulas
		for(i = inicio, x = 0; i != null && x < this.linhas; i = i.inf, x++)
		{
			for(j = i, y = 0; j != null && y < this.colunas; j = j.prox, y++)
			{
				j.elemento = array[x][y];
			}
		}
	}
	//metodo para imprimir a diagonal principal de uma matriz
	public void diagPrincipal() throws Exception
	{
		//somente matrizes quadradas apresentam diagonal 
		if(this.linhas != this.colunas)
		{
			throw new Exception("Dimensoes da matriz invalidas para executar o metodo");
		}
		else
		{
			//for que simula a logica do for duplo em estruturas sequenciais, em que é impresso o elemento na posicao em que i == j
			//no caso, por ser estrutura dinamica e nao sequencial, caminho na estrutura usando os ponteiros de prox e inf
			for(Celula i = inicio; i != null; i = i.prox)
			{
				
				MyIO.print(i.elemento + " ");
				if(i.inf != null)
				{
					i = i.inf;
				}
			}
			MyIO.println("");
		}
	}
	//metodo para imprimir a diagonal secundaria de uma matriz quadrada
	public void diagSecundaria() throws Exception
	{
		//somente matrizes quadradas apresentam diagonal
		if(this.linhas != this.colunas)
		{
			throw new Exception("Dimensoes da matriz invalidas para executar o metodo");
		}
		else
		{
			//como o primeiro elemento da diagonal secundaria se localiza no ultimo elemento da primeira linha, caminho 
			//com o ponteiro i ate este elemento
			Celula i;
			for(i = inicio; i.prox != null; i = i.prox);
			//a partir de entao, faco o trajeto da mesma forma que foi feito para diag principal, porem ao inves de prox, caminho com o ponteiro ant
			for(Celula j = i; j != null; j = j.ant)
			{
				MyIO.print(j.elemento + " ");
				if(j.inf != null)
				{
					j = j.inf;
				}
			}
			MyIO.println("");
		}
	}
	//metodo para somar duas matrizes recebidas como parametro e retornar a matriz resultado da operacao
	public Matriz soma(Matriz a, Matriz b) throws Exception
	{
		//verificar se as dimensoes sao validas
		if(a.linhas != b.linhas || a.colunas != b.colunas)
		{
			throw new Exception("Dimensoes invalidas para a operacao de soma.");
		}
		//criar matriz resultado
		Matriz c = new Matriz(a.linhas, a.colunas);
		//ponteiros auxiliares para caminhar na matriz somando seus elementos
		Celula ic = c.inicio;
		Celula ia = a.inicio;
		Celula ib = b.inicio;
		for(int i = 0; i < c.linhas; i++)
		{
			Celula jc = ic;
			Celula ja = ia;
			Celula jb = ib;
			for(int j = 0; j < c.colunas; j++)
			{
				jc.elemento = ja.elemento + jb.elemento;
				//muda de coluna
				jc = jc.prox;
				ja = ja.prox;
				jb = jb.prox;
			}
			//muda de linha
			ic = ic.inf;
			ia = ia.inf;
			ib = ib.inf;
		}
		return c;
	}
	public Matriz produto(Matriz a, Matriz b) throws Exception
	{
		//o produto de matrizes so e possivel se o numero de colunas de a for igual ao numero de linhas de b
		if(a.colunas != b.linhas)
		{
			throw new Exception("Dimensoes invalidas para realizar o produto");
		}
		//a matriz resultante tera o mesmo numero de linhas da primeira matriz e o mesmo numero de colunas da segunda matriz;
		Matriz c = new Matriz(a.linhas, b.colunas);
		//ponteiros e variaveis auxiliares para efetuar o produto
		Celula ka = a.inicio;
		Celula ia = a.inicio;
		Celula jb = b.inicio;
		int produto = 0;
		int i, j, k;
		int x = 0;
		int y = 0;
		int[][] array = new int[a.linhas][b.colunas];
		//percorrer a matriz efetuando as operacoes
		for(ia = a.inicio, i = 0; i < a.linhas; i++, ia = ia.inf)
		{
			for(jb = b.inicio, j = 0; j < b.colunas; j++, jb = jb.prox)
			{
				Celula kb = jb;
				for(ka = ia, k = 0; k < a.colunas; k++, ka = ka.prox)
				{
					produto += (ka.elemento * kb.elemento);
					kb = jb.inf;
				}
				array[x][y] = produto;	
				y++;
				produto = 0;
			}
			x++;
			y = 0;
			
		}
		//armazenar os valores obtidos na matriz resultado e retorna-la	
		c.setElementos(array);
		return c;
	}
	//metodo para printar os elementos de uma matriz
	public void mostrar()
	{
		int x = 0;
		for(Celula i = inicio; i != null; i = i.inf)
		{
			for(Celula j = i; j != null; j = j.prox)
			{
				MyIO.print(j.elemento + " ");
			}
			MyIO.println("");
		}
	}
}
public class matriz
{
	public static void main(String[] args) throws Exception 
	{
		//declarar variaveis
		int testes = MyIO.readInt();
		int linhas, colunas;
		for(int i = 0; i < testes; i++)
		{
			//ler componentes e elementos de uma matriz
			linhas = MyIO.readInt();
			colunas = MyIO.readInt();
			int[][] array = new int[linhas][colunas];
			for(int j = 0; j < linhas; j++)
			{
				for(int k = 0; k < colunas; k++)
				{
					array[j][k] = MyIO.readInt();
				}
			}
			Matriz a = new Matriz(linhas, colunas);
			a.setElementos(array);
			//ler segunda matriz
			linhas = MyIO.readInt();
			colunas = MyIO.readInt();
			int[][] array2 = new int[linhas][colunas];
			for(int j = 0; j < linhas; j++)
			{
				for(int k = 0; k < colunas; k++)
				{
					array2[j][k] = MyIO.readInt();
				}
			}
			Matriz b = new Matriz(linhas, colunas);
			b.setElementos(array2);
			//mostrar diagonais da primeira matriz
			a.diagPrincipal();
			a.diagSecundaria();
			//somar matrizes
			Matriz c = a.soma(a, b);
			c.mostrar();
			//multiplicar matrizes
			Matriz d = a.produto(a,b);
			d.mostrar();
		}
	}
}
