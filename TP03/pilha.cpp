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
typedef struct Celula
{
	struct Celula *prox;
	Personagem *elemento;
}Celula;
typedef struct Pilha
{
	Celula *topo;
}Pilha;
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
/*-------------------PILHA FLEXIVEL-------------------*/
//definicao dos metodos
void ConstPilha(Pilha *pilha);
void ConstCelula(Celula *celula);
void inserir(Pilha *pilha, Personagem *personagem);
Personagem* remover(Pilha *pilha);
void mostrar(Pilha *pilha);
void mostrarContrario(Pilha *pilha);

//construtor celula
void ConstCelula(Celula *celula)
{
	celula->prox = (Celula*) malloc(sizeof(Celula));
	celula->prox = NULL;
	celula->elemento = (Personagem*) malloc(sizeof(Personagem));
	ConstVazio(celula->elemento);
}
//construtor padrao da pilha
void ConstPilha(Pilha *pilha)
{
	//celula topo da pilha
	pilha->topo = (Celula*) malloc (sizeof(Celula));
	ConstCelula(pilha->topo);
}
//metodo para verificar o tamanho da pilha
int tamanhoPilha(Pilha *pilha)
{
	int tamanho = 0;
	if(pilha->topo->prox != NULL)
	{
		for(Celula *i = pilha->topo; i != NULL; i = i->prox)
		{
			tamanho++;
		}
	}
	return tamanho;
}
//INSERIR ELEMENTOS NA PILHA
void inserir(Pilha *pilha, Personagem *personagem)
{
	if(personagem != NULL)
	{
		//inserir elemento
		Celula *temp = (Celula*) malloc (sizeof(Celula));
		ConstCelula(temp);
		temp->elemento = clone(personagem);
		temp->prox = pilha->topo;
		pilha->topo = temp;
		temp = NULL;
		free(temp);
	}
}

//REMOVER ELEMENTOS DA PILHA
Personagem *remover(Pilha *pilha)
{
	Personagem *removido = (Personagem*) malloc (sizeof(Personagem));
	//verificar se a pilha nao esta vazia
	if(pilha->topo == NULL)
	{
		printf("%s\n", "Nao foi possivel remover o elemento. A fila esta vazia.");
	}
	else
	{
		//remover elemento
		Celula *temp = pilha->topo;
		removido = clone(temp->elemento);
		pilha->topo = pilha->topo->prox;
		temp = NULL;
		free(temp);
	}
	//retornar o elemento removido
	return removido;
}
//metodo para printar a pilha do topo para a base
void mostrar(Pilha *pilha)
{	
	int j = 0;
	//percorrer da posicao onde esta o primeiro personagem na fila ate a que esta o ultimo printando-os
	for(Celula *i = pilha->topo; i->prox != NULL; i = i->prox)
	{
		printf("[%d]", j);
		j++;
		imprimir(i->elemento);
	}
}
//metodo para printar a pilha da base para o topo
void mostrarContrario(Pilha *pilha)
{
	//declarar variaveis
	int k = 0;
	Celula *i = (Celula*) malloc (sizeof(Celula));
	Celula *j = (Celula*) malloc (sizeof(Celula));
	i = pilha->topo;
	j = i->prox;
	//andar com o ponteiro j ate o final da pilha
	while(j->prox != NULL)
	{
		j = j->prox;
	}
	while(j != pilha->topo)
	{
		for(/**/; i->prox != j; i = i->prox);
		printf("[%d]", k);
		imprimir(i->elemento);
		k++;
		j =  i;
		i = pilha->topo;
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
	//declarar e alocar espaco de memoria para a pilha
	Pilha *pilha = (Pilha*) malloc (sizeof(Pilha));
	ConstPilha(pilha);
	//ler entradas e armazenar na pilha
	scanf("%s", entrada);
	while(!isFim(entrada))
	{
		//alocar e construir o personagem
		Personagem *sw = (Personagem*) malloc (sizeof(Personagem));
		ConstVazio(sw);
		ler(sw, entrada);
		//inserir na pilha
		inserir(pilha, sw);
		scanf("%s", entrada);
	}
	
	//ler a segunda etapa da entrada
	scanf("%d", &nOperacoes);

	for(int j = 0; j < nOperacoes; j++)
	{
		scanf("%s", entrada);

		if(entrada[0] == 'I') //todas as operacoes de inserir terao duas informacoes na linha de entrada
		{
			scanf("%s", aux);
		}
		
		//criar e alocar o personagem a ser inserido ou que recebera o personagem removido da pilha
		Personagem *sw = (Personagem*) malloc (sizeof(Personagem));
		ConstVazio(sw);
		if(entrada[0] == 'I')
		{
			ler(sw, aux);
			inserir(pilha, sw);
			free(sw);
			
		}
		else if(entrada[0] == 'R')
		{	
			sw = remover(pilha);
			removidos = sw->nome;
			printf("(R) %s\n", removidos);
			free(sw);
		}
	}
	//mostrar a pilha se ela nao estiver vazia
	if(pilha->topo != NULL)
	{
		mostrarContrario(pilha);
	}
	return 0;
}

