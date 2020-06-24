#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <time.h>
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
typedef struct Celula
{
	struct Celula *prox;
	struct Celula *ant;
	Personagem *elemento;
}Celula;
typedef struct Lista
{
	Celula *primeiro;
	Celula *ultimo;
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
	//sw->peso = 0;
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
/*-------------------LISTA DUPLAMENTE ENCADEADA-------------------*/
//definicao dos metodos
void ConstLista(Lista *lista);
void ConstCelula(Celula *celula);
void inserirInicio(Lista *lista, Personagem *personagem);
void inserirFim(Lista *lista, Personagem *personagem);
void inserir(Lista *lista, int posicao, Personagem *personagem);
Personagem* removerInicio(Lista *lista);
Personagem* removerFim(Lista *lista);
Personagem* remover(Lista *lista, int posicao);
void mostrar(Lista *lista);

//construtor celula
Celula* ConstCelula()
{
	Celula *celula = (Celula*) malloc(sizeof(Celula));
	celula->prox = NULL;
	celula->ant = NULL;
	celula->elemento = (Personagem*) malloc(sizeof(Personagem));
	ConstVazio(celula->elemento);
	return celula;
}
//construtor padrao da lista
void ConstLista(Lista *lista)
{
	//celula inicial
	lista->primeiro = ConstCelula();
	lista->ultimo = lista->primeiro; //lista vazia
}
//metodo para verificar o tamanho da lista
int tamanhoLista(Lista *lista)
{
	int tamanho = 0;
	if(lista->primeiro->prox != NULL)
	{
		for(Celula *i = lista->primeiro; i != NULL; i = i->prox)
		{
			tamanho++;
		}
	}
	return tamanho;
}
//INSERIR ELEMENTOS NA LISTA
void inserirInicio(Lista *lista, Personagem *personagem)
{
	if(personagem != NULL)
	{
		//inserir elemento
		Celula *temp = ConstCelula();
		temp->elemento = clone(personagem);
		temp->prox = lista->primeiro->prox;
		lista->primeiro->prox = temp;
		lista->primeiro->prox->ant = lista->primeiro;
		if(lista->primeiro = lista->ultimo)
		{
			lista->ultimo = temp;
		}
		else
		{
			temp->prox->ant = temp;
		}
		temp = NULL;
		free(temp);
	}
}

void inserirFim(Lista *lista, Personagem *personagem)
{
	if(personagem != NULL)
	{
		//criar nova celula
		lista->ultimo->prox = ConstCelula();
		//armazenar o elemento na celula criada
		lista->ultimo->prox->elemento = personagem;
		//linkar o ponteiro anterior a celula apontada por ultimo
		lista->ultimo->prox->ant = lista->ultimo;
		//movimentar o ponteiro do ultimo para a nova celula inserida
		lista->ultimo = lista->ultimo->prox;
	}
}
void inserir(Lista *lista, int posicao, Personagem *personagem)
{
	//verificar se a posicao e valida
	if(posicao < 0 || posicao > tamanhoLista(lista))
	{
		printf("%s\n", "Posicao invalida para insercao");
	}
	else if(posicao == 0)
	{
		inserirInicio(lista, personagem);
	}
	else if(posicao == tamanhoLista(lista))
	{
		inserirFim(lista, personagem);
	}
	else
	{
		//caminhar ate a celula anterior a posicao a ser inserido o elemento
		Celula *i = lista->primeiro;
		for(int I = 0; I < posicao; I++, i = i->prox);
	       	//criar celula temporaria
		Celula *temp = ConstCelula();
		temp->elemento = personagem;
		//linkar a celula temporaria na posicao desejada da lista
		temp->ant = i;
		temp->prox = i->prox;
		temp->ant->prox = temp;
		temp->prox ->ant = temp;
		//liberar ponteiros temporarios alocados
		temp = NULL;
		i = NULL;
		free(temp);
		free(i);
	}
}

//REMOVER ELEMENTOS DA PILHA
Personagem *removerInicio(Lista *lista)
{
	Personagem *removido = (Personagem*) malloc (sizeof(Personagem));
	//verificar se a lista nao esta vazia
	if(lista->primeiro == lista->ultimo)
	{
		printf("%s\n", "Nao foi possivel remover o elemento. A lista esta vazia.");
	}
	else
	{
		//remover elemento
		Celula *temp = lista->primeiro;
		removido = clone(temp->elemento);
		lista->primeiro = temp->prox;
		temp->prox->ant = lista->primeiro;
		temp = NULL;
		free(temp);
	}
	//retornar o elemento removido
	return removido;
}
Personagem *removerFim(Lista *lista)
{
	Personagem *removido = (Personagem*) malloc (sizeof(Personagem));
	//verificar se a lista esta vazia
	if(lista->primeiro == lista->ultimo)
	{
		printf("%s\n", "Nao foi possivel remover, a lista esta vazia.");
	}
	else
	{
		removido = lista->ultimo->elemento;
		lista->ultimo = lista->ultimo->ant;
		lista->ultimo->prox->ant = NULL;
		free(lista->ultimo->prox);
		lista->ultimo->prox = NULL;
	}
	return removido;
}
Personagem *remover(Lista *lista, int posicao)
{
	Personagem *removido = (Personagem*) malloc (sizeof(Personagem));
	//verificar se a lista esta vazia
	if(lista->primeiro == lista->ultimo)
	{
		printf("%s\n", "Nao foi possivel remover, a lista esta vazia");
	}
	//verificar se a posicao e valida
	else if(posicao < 0 || posicao >= tamanhoLista(lista))
	{
		printf("%s\n", "Posicao invalida");
	}
	else
	{
		if(posicao == 0)
		{
			removido = removerInicio(lista);
		}
		else if(posicao == tamanhoLista(lista) - 1)
		{
			removido = removerFim(lista);
		}
		else
		{
			//caminhar ate a posicao de remocao
			Celula *i = lista->primeiro;
			for(int j = 0; j <= posicao; i = i->prox, j++);
			//remover elemento
			removido = i->elemento;
			i->ant->prox = i->prox;
			i->prox->ant = i->ant;
			free(i);
		}
	}
}
//metodos para ordenar os elementos da lista
void swap(Lista *lista, int i, int j)
{
	//localizar celulas a terem elementos trocados
	Celula *I = lista->primeiro;
	Celula *J = lista->primeiro;
       for(int x = 0; x <= i; I = I->prox, x++);
       for(int y = 0; y <= j; J = J->prox, y++);
	//realizar trocas
	Personagem *tmp = (Personagem*) malloc (sizeof(Personagem));
 	ConstVazio(tmp);
	tmp = clone(I->elemento);
	I->elemento = J->elemento;
	J->elemento = clone(tmp);
	free(tmp);	
}
void ordenar(Lista *lista, int esq, int dir, int *cont)
{
	int posicao = 0;
	posicao = (esq + dir)/2;
	//localizar pivo na lista
	Celula *temp;
        temp = lista->primeiro;
	cont[1]++; //contador de movimentacoes de elementos da lista
	for(int k = 0; k <= posicao; temp = temp->prox, k++);
	Personagem *pivo;
        pivo = clone(temp->elemento);
	temp = NULL;
	//iniciar ordenacao
	//posicionar inicialmente as celulas nos extremos direita e esquerda
	Celula *i = lista->primeiro;
	cont[1]++;
	int I = esq;
	int J = dir;
	for(int x = 0; x <= I; i = i->prox, x++);
	Celula *j = lista->primeiro;
	cont[1]++;
	for(int y = 0; y <= J; j = j->prox, y++);
	while(I <= J)
	{
		//comparacoes entre as cores do cabelo dos personagens. em caso de empate, o desempate e feito pelo nome em ordem alfabetica
		while((strcmp(i->elemento->corDoCabelo, pivo->corDoCabelo) < 0 || (strcmp(i->elemento->corDoCabelo, pivo->corDoCabelo) == 0 && strcmp(i->elemento->nome, pivo->nome) < 0))) 
		{
			i = i->prox;
			cont[1]++;
			I++;
			cont[0] += 3; //no pior caso paga 3 comparacoes
		}
		while((strcmp(j->elemento->corDoCabelo, pivo->corDoCabelo) > 0 || (strcmp(j->elemento->corDoCabelo, pivo->corDoCabelo) == 0 && strcmp(j->elemento->nome, pivo->nome) > 0))) 
		{
			j = j->ant;
			cont[1]++;
			J--;
			cont[0] += 3;
		}
		if(I <= J)
		{
			//troca dos elementos entre as celulas nas posicoes I e J
			swap(lista, I, J);
			cont[1] += 3; //swap faz 3 movimentacoes
			I++;
			i = i->prox;
			cont[1]++;
			J--;
			j = j->ant;
			cont[1]++;
		}
	}
	//chamar a funcao recursivamente para ordenar os subconjuntos
	if(esq < J)
	{
		ordenar(lista, esq, J, cont);
	}
	if(I < dir)
	{
		ordenar(lista, I, dir, cont);
	}
}
//metodo para printar a pilha do topo para a base
void mostrar(Lista *lista)
{	
	int j = 0;
	//percorrer da posicao onde esta o primeiro personagem na lista ate a que esta o ultimo printando-os
	for(Celula *i = lista->primeiro->prox; i != lista->ultimo->prox; i = i->prox)
	{
		j++;
		imprimir(i->elemento);
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
	int nOperacoes = 0;
	char *entrada = (char*)malloc (1000 * sizeof(char));
	char *aux = (char*) malloc (1000 * sizeof(char));
	char *removidos = (char*) malloc (200 * sizeof(char));
	//declarar e alocar espaco de memoria para a lista
	Lista *lista = (Lista*) malloc (sizeof(Lista));
	ConstLista(lista);
	//ler entradas e armazenar na lista
	scanf("%s", entrada);
	int i = 0;
	while(!isFim(entrada))
	{
		//alocar e construir o personagem
		Personagem *sw = (Personagem*) malloc (sizeof(Personagem));
		ConstVazio(sw);
		ler(sw, entrada);
		//inserir na lista
		inserirFim(lista, sw);
		scanf("%s", entrada);
		i++;
	}
	int tamanho = tamanhoLista(lista) - 2;
	//ordenar a lista
	int *contadores = (int*) malloc (2 * sizeof(int));
        clock_t inicio = clock();
	ordenar(lista, 0, tamanho, contadores);
	clock_t fim = clock();
        double total = (fim - inicio)/(double)CLOCKS_PER_SEC;

	//mostrar a lista se ela nao estiver vazia
	if(lista->primeiro != lista->ultimo)
	{
		mostrar(lista);
	}
	//gravar arquivo de log
        FILE *file = fopen("680715_quicksort.txt", "wt");
        fprintf(file, "Matricula: 680715;\t %d comparacoes;\t %d movimentacoes;\t Tempo de ordenacao: %lfs;\t", contadores[0], contadores[1], total);
        fclose(file);
	free (contadores);
	return 0;
}

