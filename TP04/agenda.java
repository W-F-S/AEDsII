import java.util.*;
import java.lang.*;
//classe contato da agenda, cujos atributos sao nome, telefone, email e cpf
class Contato
{
	//atributos
	public String nome;
	public String telefone;
	public String email;
	public int cpf;

	//construtores
	public Contato()
	{
		this("", "", "", 9999999);
	}
	public Contato(String nome, String telefone, String email, int cpf)
	{
		this.nome = nome;
		this.telefone = telefone;
		this.email = email;
		this.cpf = cpf;
	}
}
//classe celula. Cada celula contem um contato da agenda, e a agenda é uma arvore de listas, cada lista sendo uma lista de contatos cujo a primeira letra do nome é a letra correspondente à letra do nó da arvore
class Celula extends Contato
{
	//atributos
	Contato contato;
	Celula prox;

	//construtores
	public Celula()
	{
		this.contato = null;
		this.prox = null;
	}
	public Celula(Contato x)
	{
		this.contato = x;
		this.prox = null;
	}
}
//classe nó da arvore. Cada nó da árvore contém uma letra do alfabeto, sendo a árvore uma árvore dos índices dos nomes dos contatos
class No extends Celula
{
	//atributos
	public Celula primeiro;
	public Celula ultimo;
	public No esq;
	public No dir;
	public char letra;

	//construtores
	public No()
	{
		primeiro = ultimo = null;
		esq = dir = null;
		letra = ' ';
	}
	public No(Celula pri, Celula ul, No esq, No dir, char letra)
	{
		this.primeiro = pri;
		this.ultimo = ul;
		this.esq = esq;
		this.dir = dir;
		this.letra = letra;
	}
	public No(char letra)
	{
		this.primeiro = new Celula();
		this.ultimo = this.primeiro;
		this.letra = letra;
		this.dir = this.esq = null;
	}
}
//classe agenda
class Agenda extends No
{
	//atributo
	private No raiz;
	//construtor
	public Agenda() throws Exception
	{
		//inserir todas as letras do alfabeto na arvore
		this.raiz = null;
		char[] letras = {'M', 'D', 'R', 'E', 'S', 'A', 'C', 'F', 'B', 'V', 'T', 'Y', 'U', 'G', 'H', 'I', 'J', 'K', 'L', 'N', 'O', 'P', 'Q', 'W', 'X', 'Z'};
		for(int i = 0; i < 26; inserirletra(letras[i]), i++);
	}
	//metodo para inserir todas as letras do alfabeto na agenda
	public void inserirletra(char x) throws Exception
	{
		raiz = inserirletra(x, raiz);
	}
	public No inserirletra(char x, No i) throws Exception
	{
		if(i == null)
		{
			i = new No(x);
		}
		else if(x < i.letra)
		{
			i.esq = inserirletra(x, i.esq);
		}
		else if(x > i.letra)
		{
			i.dir = inserirletra(x, i.dir);
		}
		else
		{
			throw new Exception("Erro");
		}
		return i;
	}
	//metodo para pesquisar um contato na agenda pelo nome. retorna true se encontrar e false se nao encontrar
	public boolean pesquisarNome(String nome)
	{
		return pesquisarNome(nome, raiz);
	}
	public boolean pesquisarNome(String nome, No i)
	{
		boolean resp = false;
		//se i for null significa que nao encontrou o contato
		if(i == null)
		{
			resp = false;
		}
		//percorre a arvore ate chegar no nõ cujo a letra é correspondente à primeira letra do nome do contato
		else if(i.letra == Character.toUpperCase(nome.charAt(0)))
		{
			Celula tmp = i.primeiro.prox;
			resp = false;
			//se encontrar a letra, percorre a lista atrelada àquele nó verificando se encontra o contato
			while(tmp != null)
			{
				if(tmp.contato.nome.compareTo(nome) == 0)
				{
					resp = true;
					tmp = null;
				}
				else
				{
					tmp = tmp.prox;
				}
			}

		}
		else if(Character.toUpperCase(nome.charAt(0)) < i.letra)
		{
			resp = pesquisarNome(nome, i.esq);
		}
		else if(Character.toUpperCase(nome.charAt(0)) > i.letra)
		{
			resp = pesquisarNome(nome, i.dir);
		}
		return resp;
	}
	//metodo void para inserir um novo contato na arvore. 
	public void inserirContato(Contato x) throws Exception
	{
		inserirContato(x, raiz);
	}
	public void inserirContato(Contato x, No i) throws Exception
	{
		if(i == null)
		{
			throw new Exception("ERRO");
		}
		//busca pelo no da arvore cujo a letra é correspondente à primeira letra do nome do contato  a ser inserido
		else if(i.letra == Character.toUpperCase(x.nome.charAt(0)))
		{
			//se encontrar o nó, insere o novo contato no fim da lista atrelada àquele nó
			i.ultimo.prox = new Celula(x);
			i.ultimo = i.ultimo.prox;
		}
		else if(Character.toUpperCase(x.nome.charAt(0)) < i.letra)
		{
			inserirContato(x, i.esq);
		}
		else
		{
			inserirContato(x, i.dir);
		}
	}
	//funcao para buscar um contato pelo numero de CPF. caso encontre retorna true, caso contrario retorna false.
	public boolean pesquisarcpf(int cpf)
	{
		return pesquisarcpf(cpf, raiz);
	}
	public boolean pesquisarcpf(int cpf, No i)
	{
		boolean resp;
		//percorre a arvore enquanto i nao for null. como a arvore esta organizada por letras e nao por cpf, a busca tera que ser feita em todos os nós ate que o contato seja encontrado ou que a arvore acabe
		if(i != null)
		{
			resp = false;
			//acessar a lista de cada nó e percorre-la ate o fim ou ate encontrar o contato
			if(i.primeiro.prox != null)
			{
				Celula j = i.primeiro.prox;
				while(j != null)
				{
					if(j.contato.cpf == cpf)
					{
						resp = true;
						j = null;
					}
					else
					{
						j = j.prox;
					}
				}
			}
			if(resp == false)
			{
				resp = pesquisarcpf(cpf, i.dir);
				resp = pesquisarcpf(cpf, i.esq);
			}
		}
		else
		{
			resp = false;
		}
		return resp;
	}
	//metodo void para remover um contato da lista, utilizando como metodo de busca o nome do contato
	public void remover(String nome) throws Exception
	{
		raiz = remover(nome, raiz);
	}
	public No remover(String x, No i) throws Exception
	{
		if(i == null)
		{
			throw new Exception("ERRO");
		}
		else if(Character.toUpperCase(x.charAt(0)) < i.letra)
		{
			i.esq = remover(x, i.esq);
		}
		else if(Character.toUpperCase(x.charAt(0)) > i.letra)
		{
			i.dir = remover(x, i.dir);
		}
		//chegou no nó cujo a letra é a mesma do nome do contato a ser removido
		else if(Character.toUpperCase(x.charAt(0)) == i.letra)
		{
			//se a lista do nó nao estiver vazia
			if(i.primeiro.prox != null)
			{
				Celula temp = i.primeiro.prox;
				//percorrer a lista ate encontrar o contato a remover ou ate que a lista termine
				while(temp != null)
				{
					if(temp.contato.nome.compareTo(x) == 0)
					{
						Celula j = i.primeiro;
						//posicionar um ponteiro na posicao anterior à ser removida da lista
						for(/**/; j.prox != temp; j = j.prox);
						//remover o contato
						j.prox = temp.prox;
						temp = null;
						j = null;
					}
					else
					{
						temp = temp.prox;
					}
				}
			}
			else
			{
				throw new Exception("O contato nao esta na agenda, logo nao pode ser removido");
			}
		}
		return i;
	}
	//metodo void para mostrar letras na arvore
	public void mostrar()	
	{
		mostrar(raiz);
	}
	public void mostrar(No i)
	{
		if(i != null)
		{
			mostrar(i.esq);
			MyIO.println(i.letra + " ");
			mostrar(i.dir);
		}
	}
}		
public class agenda
{
	public static void main(String[] args) throws Exception
	{
		//criar agenda
		Agenda agenda = new Agenda();
		int cpf = 718305663;
		//inserir um contato
		Contato contato1 = new Contato("Ana Oliveira", "973097282", "anaoliveira@gmail.com", cpf);
		agenda.inserirContato(contato1);
		//pesquisar contato
		MyIO.println("A agenda tem o contato -Ana Oliveira-? " + agenda.pesquisarNome("Ana Oliveira"));
		MyIO.println("A agenda contem um contato cujo cpf é 718305663? " + agenda.pesquisarcpf(cpf));
		//remover contato
		MyIO.println("apos remover o contato:");
		agenda.remover("Ana Oliveira");
		MyIO.println("A agenda tem o contato -Ana Oliveira-? " + agenda.pesquisarNome("Ana Oliveira"));
		MyIO.println("A agenda contem um contato cujo cpf é 718305663? " + agenda.pesquisarcpf(cpf));
	}
}

