#include <stdio.h>
#include <stdlib.h>
#include <string.h>

typedef struct Personagem
{
	char *nome;
	int altura;
	double peso;
	char *corDoCabelo;
	char *corDaPele;
	char *corDosOlhos;
	char *anoNascimento;
	char *genero;
	char *homeworld;
}Personagem;
typedef struct Lista
{
	Personagem **lista;
	int n;
}Lista;
//construtor padrao
void ConstVazio(Personagem *sw)
{
	sw->nome = (char*) malloc (200 * sizeof(char));
	sw->altura = 0;
	sw->peso = 0.0;
	sw->corDoCabelo = (char*) malloc (200 * sizeof(char));
	sw->corDaPele = (char*) malloc (200 * sizeof(char));
	sw->corDosOlhos = (char*) malloc (200 * sizeof(char));
	sw->anoNascimento = (char*) malloc (200 * sizeof(char));
	sw->genero = (char*) malloc (200 * sizeof(char));
	sw->homeworld = (char*) malloc (200 * sizeof(char));
}
//construtor secundario que inicializa as variaveis da estrutura a partir dos valores recebidos como parametro
void Const(Personagem *sw, char *name, int height, double mass, char *hair, char *skin, char *eyes, char *date, char *gender, char *world)
{	
	sw->nome = (char*) malloc (200 * sizeof(char));
	sw->altura = 0;
	sw->peso = 0.0;
	sw->corDoCabelo = (char*) malloc (200 * sizeof(char));
	sw->corDaPele = (char*) malloc (200 * sizeof(char));
	sw->corDosOlhos = (char*) malloc (200 * sizeof(char));
	sw->anoNascimento = (char*) malloc (200 * sizeof(char));
	sw->genero = (char*) malloc (200 * sizeof(char));
	sw->homeworld = (char*) malloc (200 * sizeof(char));
	sw->nome = name;
	sw->altura = height;
	sw->peso = mass;
	sw->corDoCabelo = hair;
	sw->corDaPele = skin;
	sw->corDosOlhos = eyes;
	sw->anoNascimento = date;
	sw->genero = gender;
	sw->homeworld = world;
}
//funcao para clonar a estrutura (personagem)
Personagem* clone(Personagem* original)
{
        Personagem *clone = (Personagem*) malloc (sizeof(Personagem));
        Const(clone, original->nome, original->altura, original->peso, original->corDoCabelo, original->corDaPele, original->corDosOlhos, original->anoNascimento, original->genero, original->homeworld);
        return clone;
}
//metodo para imprimir os valores das variaveis da estrutura
void imprimir(Personagem *sw)
{
	//nesse exercicio, todos os personagens recebem peso zero
	sw->peso = 0;
	printf(" ##%s ## %d ## %g", sw->nome, sw->altura, sw->peso);
        printf(" ##%s ##%s ##%s", sw->corDoCabelo, sw->corDaPele, sw->corDosOlhos);
        printf(" ##%s ##%s ##%s ##\n", sw->anoNascimento, sw->genero, sw->homeworld);
}
//metodo para obter informacoes do arquivo de entrada e atribuir os valores as variaveis da struct
void ler(Personagem *sw, char *fileName)
{
	/*char str[200];	
	strcpy(str, ".");
	strcat(str, fileName);*/

	//declarar variaveis
	FILE *file = fopen(fileName, "rt");
	char *info = (char*) malloc (4000 * sizeof(char));
	char *aux = (char*) malloc (4000 * sizeof(char));
	bool cont = false;
	int x = 0;
	int i = 0;
	int j = 0;
	int n = 0;
	
	//ler arquivo
	while(!feof(file))
	{
		fgets(info, 4000, file);
	}
	//fechar arquivo
	fclose(file);

	//copiar somente as informacoes essenciais da cadeia de char contendo o conteudo do arquivo original para uma nova cadeia auxiliar
	while(i <= 8)
	{
		//copiar o conteudo entre : e ', para uma string auxiliar
		if(info[j] == ':')
		{
			n = j + 1;
			cont = false;
			while(cont == false)
			{
				if(info[n] == (char)(39) && info[n + 1] == ',')
				{
					aux[x] = info[n];
					x++;
					aux[x] = info[n + 1];
					x++;
					cont = true;
				}
				else
				{
					aux[x] = info[n];
					x++;
					n++;
				}
			}
			j++;
			i++;
		}
		else
		{
			j++;
		}
	}
	n = i = 0;
	int tamanho = strlen(aux);
	char aux2[tamanho];

	//atribuir as informacoes as variaveis da struct
	for(j = 0; j < tamanho; j++)
	{
		if(aux[j] == (char)(39) && aux[j + 1] == ',')
		{
			x = 0;
			//'limpar' a string auxiliar
			for(int o = 0; o < tamanho; o++)
			{
				aux2[o] = '\0';
			}
			if(i == 0) //nome
			{
				
				while(n < j)
				{
					
					if(aux[n] != (char)39)
					{
						aux2[x] = aux[n];
						x++;
					}
					n++;
				}
				//copiar a string para a variavel nome da estrutura 
				strcpy(sw->nome, aux2);
				n = j + 2;
				i++;
			}
			else if(i == 1) //altura
			{
				while(n < j)
				{
					
					if('0' <= aux[n] && aux[n] <= '9')
					{
						aux2[x] = aux[n];
						x++;
					}
					n++;
				}
				if(aux2 != NULL)
				{
					sw->altura = atoi(aux2);
				}
				else
				{
					sw->altura = 0;
				}
				n = j + 2;
				i++;
			}
			else if(i == 2) //peso
			{
				while(n < j)
				{
					if(('0' <= aux[n] && aux[n] <= '9') || aux[n] == '.')
					{
						aux2[x] = aux[n];
						x++;
					}
					n++;
				}
				if(aux2 != NULL)
				{
					char *ptr;
					sw->peso = strtod(aux2, &ptr);
				}
				else
				{
					sw->peso = 0;
				}
				n = j + 2;
				i++;
			}
			else if(i == 3) //cor do cabelo
			{
				while(n < j)
				{
					
					if(aux[n] != (char)39)
					{
						aux2[x] = aux[n];
						x++;
					}
					n++;
				}
				strcpy(sw->corDoCabelo, aux2);
				n = j + 2;
				i++;
			}
			else if(i == 4) //cor da pele
			{
				while(n < j)
				{
					if(aux[n] != (char)39)
					{
						aux2[x] = aux[n];
						x++;
					}
					n++;
				}
				strcpy(sw->corDaPele, aux2);
				n = j + 2;
				i++;
			}
			else if(i == 5) //cor dos olhos
			{
				while(n < j)
				{
					if(aux[n] != (char)39)
					{
						aux2[x] = aux[n];
						x++;
					}
					n++;
				}
				strcpy(sw->corDosOlhos, aux2);
				n = j + 2;
				i++;
			}
			else if(i == 6) //ano nascimento
			{
				while(n < j)
				{
					if(aux[n] != (char)39)
					{
						aux2[x] = aux[n];
						x++;
					}
					n++;
				}
				strcpy(sw->anoNascimento, aux2);
				n = j + 2;
				i++;
			}
			else if(i == 7) //genero
			{
				while(n < j)
				{
					if(aux[n] != (char)39)
					{
						aux2[x] = aux[n];
						x++;
					}
					strcpy(sw->genero, aux2);
					n++;
				}
				n = j + 2;
				i++;
			}
			else if(i == 8) //homeworld
			{
				while(n < j)
				{
					if(aux[n] != (char)39)
					{
						aux2[x] = aux[n];
						x++;
					}
					n++;
				}
				strcpy(sw->homeworld, aux2);
				n = j;
				i++;
			}
		}
	}
}	
/*-------------------LISTA SEQUENCIAL-------------------*/
#define TAM 100
//construtor padrao da lista
void ConstLista(Lista *lista)
{
	lista->lista = (Personagem**) malloc (TAM * sizeof(Personagem*));
	for(int i = 0; i < TAM; i++)
	{
		lista->lista[i] = (Personagem*) malloc (sizeof(Personagem));
	}
	lista->n = 0;
}
//METODOS PARA INSERIR ELEMENTOS NA LISTA
//Inserir no Inicio:
void inserirInicio(Lista *lista, Personagem *personagem)
{
	//verificar se lista esta cheia
	if(lista->n >= TAM)
	{
		printf("%s\n", "A lista esta cheia. Nao foi possivel inserir o elemento no inicio");
	}
	else
	{
		//deslocar elementos
		for(int i = lista->n; i > 0; i--)
		{
			lista->lista[i] = lista->lista[i - 1];
		}
		//inserir elemento
		lista->lista[0] = clone(personagem);
		lista->n++; 
	}
}
//Inserir no fim
void inserirFim(Lista *lista, Personagem *personagem)
{
	//verificar se lista esta cheia
	if(lista->n >= TAM)
	{
		printf("%s\n", "A lista esta cheia. Nao foi possivel inserir o elemento no fim");
	}
	else
	{
		//inserir elemento no fim da lista
		lista->lista[lista->n] = clone(personagem);
		lista->n++;
	}
}

//Inserir em uma posicao passada como parametro
void inserir(Lista *lista, Personagem *personagem, int posicao)
{
	//verificar se a posicao para inserir e valida ou se a lista esta cheia
	if(posicao < 0 || posicao > lista->n || lista->n >= TAM)
	{
		printf("%s\n", "Nao foi possivel inserir o elemento na posicao desejada");
	}
	else
	{
		//deslocar elementos
		for(int i = lista->n; i > posicao; i--)
		{
			lista->lista[i] = clone(lista->lista[i - 1]);
		}

		//inserir elemento
		lista->lista[posicao] = clone(personagem);

		lista->n++;
	}
}

//METODOS PARA REMOVER ELEMENTOS DA LISTA
//Remover do inicio
Personagem *removerInicio(Lista *lista)
{
	Personagem *removido = (Personagem*) malloc (sizeof(Personagem));
	//verificar se a lista nao esta vazia
	if(lista->n <= 0)
	{
		printf("%s\n", "Nao foi possivel remover o elemento. A lista esta vazia.");
	}
	else
	{
		//remover elemento
		removido = clone(lista->lista[0]);
		lista->n--;
		//deslocar demais elementos
		for(int i = 0; i < lista->n; i++)
		{
			lista->lista[i] = clone(lista->lista[i + 1]);
		}
	}
	return removido;
}

//Remover do fim
Personagem *removerFim(Lista *lista)
{
	Personagem *removido = (Personagem*) malloc (sizeof(Personagem));
	//verificar se a lista esta vazia
	if(lista->n <= 0)
	{
		printf("%s\n", "Nao foi possivel remover o elemento. A lista esta vazia.");
	}
	else
	{
		//remover elemento
		removido = clone(lista->lista[lista->n - 1]);
		lista->n--;
	}
	return removido;
}

//Remover de uma posicao passada como paramentro
Personagem *remover(Lista *lista, int posicao)
{
	Personagem *removido = (Personagem*) malloc (sizeof(Personagem));
	//testar se a posicao e valida ou se a lista esta vazia
	if(lista->n <= 0 || posicao < 0 || posicao >= lista->n)
	{
		printf("%s\n", "Nao foi possivel remover o elemento da posicao desejada.");
	}
	else
	{
		//remover elemento
		removido = clone(lista->lista[posicao]);
		lista->n--;
		//deslocar elementos
		for(int i = posicao; i < lista->n; i++)
		{
			lista->lista[i] = clone(lista->lista[i + 1]);
		}
	}
	return removido;
}
//metodo para printar a lista
void mostrar(Lista *lista)
{
	for(int i = 0; i < lista->n; i++)
	{
		printf("[%d]", i);
		imprimir(lista->lista[i]);
	}
}
//----------------------------------------------------------
//verificar se a string de entrada corresponde a "FIM" 
bool isFim (char *entrada)
{
	return(entrada[0] == 'F' && entrada[1] == 'I' && entrada[2] == 'M');
}
int main()
{
	//declarar variaveis
	int i = 0;
	int k = 0;
	int nOperacoes = 0;
	int posicao = 0;
	char *entrada = (char*)malloc (1000 * sizeof(char));
	char *aux = (char*) malloc (1000 * sizeof(char));
	char *removidos = (char*) malloc (200 * sizeof(char));
	//declarar e alocar espaco de memoria para a lista
	Lista *lista = (Lista*) malloc (sizeof(Lista));
	ConstLista(lista);

	//ler entradas e armazenar na lista
	scanf("%s", entrada);
	while(!isFim(entrada))
	{
		Personagem *sw = (Personagem*) malloc (sizeof(Personagem));
		ConstVazio(sw);
		ler(sw, entrada);
		inserir(lista, sw, i);
		i++;
		scanf("%s", entrada);
	}
	

	//ler a segunda etapa da entrada
	scanf("%d", &nOperacoes);

	for(int j = 0; j < nOperacoes; j++)
	{
		char *aux1 = (char*) malloc (100*sizeof(char));
		scanf("%s", entrada);
		if(entrada[0] == 'R' &&  entrada[1] == '*') //o unico caso de remocao que tera duas informacoes na entrada e o R*
		{
			scanf("%s", aux);
		}
		else if(entrada[0] == 'I') //todas as operacoes de inserir terao PELO MENOS duas informacoes na linha de entrada
		{
			scanf("%s", aux);
		}
		if(entrada[0] == 'I' && entrada[1] == '*') //a unica operacao que tera tres informacoes na entrada e a I*
		{
			scanf("%s", aux1);
		}
		//criar e alocar o personagem a ser inserido ou que recebera o personagem removido da lista
		Personagem *sw = (Personagem*) malloc (sizeof(Personagem));
		ConstVazio(sw);
		if(entrada[0] == 'I')
		{
			if(entrada[1] == 'I')//inserir no inicio
			{
				ler(sw, aux);
				inserirInicio(lista, sw);
				free(sw);
			}
			else if(entrada[1] == 'F')//inserir no final
			{
				ler(sw, aux);
				inserirFim(lista, sw);
			}
			else//inserir na posicao desejada
			{
				posicao = atoi(aux);
				ler(sw, aux1);
				inserir(lista, sw, posicao);

			}
		}
		else if(entrada[0] == 'R')
		{	
			if(entrada[1] == 'I')//remover do inicio
			{
				sw = removerInicio(lista);
				removidos = sw->nome;
				printf("(R)%s\n", removidos);
			}
			else if(entrada[1] == 'F')//remover do final
			{
				sw = removerFim(lista);
				removidos = sw->nome;
				printf("(R)%s\n", removidos);
			}
			else//remover da posicao desejada
			{
				posicao = atoi(aux);
				sw = remover(lista, posicao);
				removidos = sw->nome;
				printf("(R)%s\n", removidos);
			}
		}
		//liberar memoria de aux
		free(aux1);
	}

	//mostrar a lista apos as operacoes terem sido realizadas
	mostrar(lista);

	
	return 0;
}

