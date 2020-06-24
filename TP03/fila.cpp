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
typedef struct Fila
{
	Celula *ultimo;
	Celula *primeiro;
	int tamanho;
}Fila;
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
/*-------------------FILA CIRCULAR-------------------*/
#define TAM 5
//definicao dos metodos
void ConstFila(Fila *fila);
void ConstCelula(Celula *celula, int x);
void inserir(Fila *fila, Personagem *personagem);
Personagem* remover(Fila *fila);
void mostrar(Fila *fila);

//construtor celula
void ConstCelula(Celula *celula)
{
	celula->prox = (Celula*) malloc(sizeof(Celula));
	celula->prox = NULL;
	celula->elemento = (Personagem*) malloc(sizeof(Personagem));
	ConstVazio(celula->elemento);
}
//construtor padrao da fila
void ConstFila(Fila *fila)
{
	//celula cabeça
	fila->primeiro = (Celula*) malloc (sizeof(Celula));
	ConstCelula(fila->primeiro);
	fila->ultimo = fila->primeiro;
}
//metodo para verificar se a fila esta cheia
int tamanhoFila(Fila *fila)
{
	int tamanho = 0;
	if(fila->primeiro != fila->ultimo)
	{
		for(Celula *i = fila->primeiro->prox; i != fila->primeiro; i = i->prox)
		{
			tamanho++;
		}
	}
	return tamanho;
}
//INSERIR ELEMENTOS NA FILA (ENFILEIRAR)
void inserir(Fila *fila, Personagem *personagem)
{
	//verificar se lista esta cheia
	if(tamanhoFila(fila) < 5)
	{
		//inserir elemento
		fila->ultimo->prox = (Celula*) malloc (sizeof(Celula));
		ConstCelula(fila->ultimo->prox);
		fila->ultimo->prox->elemento = clone(personagem);
	       	fila->ultimo = fila->ultimo->prox;
		fila->ultimo->prox = fila->primeiro;	
		
	}
	//quando a fila estiver cheia e nao for mais possivel inserir personagens, remover um e entao inserir o novo personagem
	else
	{
		remover(fila);
		inserir(fila, personagem);
	}
}

//REMOVER ELEMENTOS DA FILA
Personagem *remover(Fila *fila)
{
	Personagem *removido = (Personagem*) malloc (sizeof(Personagem));
	//verificar se a lista nao esta vazia
	if(fila->primeiro == fila->ultimo)
	{
		printf("%s\n", "Nao foi possivel remover o elemento. A fila esta vazia.");
	}
	else
	{
		//remover elemento
		Celula *temp = fila->primeiro;
		fila->primeiro = temp->prox;
		removido = fila->primeiro->elemento;
		temp->elemento = NULL;
		temp->prox = NULL;
		temp = NULL;
		free(temp);
		fila->ultimo->prox = fila->primeiro;
		
	}
	//retornar o elemento removido
	return removido;
}
//metodo para printar a fila
void mostrar(Fila *fila)
{	
	int j = 0;
	if(tamanhoFila(fila) < 5)
	{
		//percorrer da posicao onde esta o primeiro personagem na fila ate a que esta o ultimo printando-os
		for(Celula *i = fila->primeiro->prox; i != fila->ultimo->prox; i = i->prox)
		{
			printf("[%d]", j);
			j++;
			imprimir(i->elemento);
		}
	}
}
//metodo para printar a media arredondada para cima da altura dos personagens contidos na fila 
void mostrarAltura(Fila *fila)
{
	//declarar variaveis
	double media = 0.0;
	int resp = 0;
	double cont = 0;
	//somar as alturas de todos os personagens na fila
	for(Celula *i = fila->primeiro->prox; i != fila->primeiro; i = i->prox) //comecando na posicao do primeiro personagem da fila, indo ate a do ultimo
	{
		media += i->elemento->altura;
		cont++;
	}
	media = media/cont; //dividir pelo numero de personagens na fila
	if(media - (int)media >= 0.5) //arredondar o valor para cima, quando necessario, e printar o resultado
	{
		resp = (int)media + 1;
	}
	else
	{
		resp = (int)media;	
	}
	printf("%d\n", resp);
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
	Fila *fila = (Fila*) malloc (sizeof(Fila));
	ConstFila(fila);
	//ler entradas e armazenar na fila
	scanf("%s", entrada);
	while(!isFim(entrada))
	{
		//alocar e construir o personagem
		Personagem *sw = (Personagem*) malloc (sizeof(Personagem));
		ConstVazio(sw);
		ler(sw, entrada);
		//inserir na fila e mostrar media das alturas dos personagens na fila
		inserir(fila, sw);
		//printf("passei\n");
		mostrarAltura(fila);
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
		
		//criar e alocar o personagem a ser inserido ou que recebera o personagem removido da fila
		Personagem *sw = (Personagem*) malloc (sizeof(Personagem));
		ConstVazio(sw);
		if(entrada[0] == 'I')
		{
			ler(sw, aux);
			inserir(fila, sw);
			mostrarAltura(fila);
			free(sw);
			
		}
		else if(entrada[0] == 'R')
		{	
			sw = remover(fila);
			removidos = sw->nome;
			printf("(R)%s\n", removidos);
			free(sw);
		}
	}
	//mostrar a fila se ela nao estiver vazia
	if(fila->primeiro != fila->ultimo)
	{
		mostrar(fila);
	}
	return 0;
}

