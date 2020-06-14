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
	Personagem *elemento;
}Celula;
typedef struct Lista
{
	Celula *primeiro;
	Celula *ultimo;
}Lista;
typedef struct Hash
{
	Personagem **tabela;
	int tamTab;
	Lista *lista;
}Hash;	
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
				n++;
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
//----------------------------HASH COM LISTA------------------------------
//metodo 'construtor' da celula
Celula *ConstCelula()
{
	Celula *celula = (Celula*) malloc (sizeof(Celula));
	celula->prox = NULL;
	celula->elemento = (Personagem*) malloc (sizeof(Personagem));
	ConstVazio(celula->elemento);
	return celula;
}
//metodo 'construtor' da lista
Lista *ConstLista()
{
	//celula inicial
	Lista *lista = (Lista*) malloc (sizeof(Lista));
	lista->primeiro = ConstCelula();
	lista->ultimo = lista->primeiro;
	return lista;
}
//metodo 'construtor' da tabela hash
Hash *ConstHash()
{
	Hash *hash = (Hash*) malloc (sizeof(Hash));
	hash->tamTab = 25;
	hash->tabela = (Personagem**) malloc (sizeof(Personagem*));
	for(int i = 0; i < hash->tamTab; i++)
	{
		hash->tabela[i] = (Personagem*) malloc (sizeof(Personagem));
		hash->tabela[i] = NULL;
	}
	hash->lista = ConstLista();
	return hash;
}
int hash(Hash *hash, Personagem *x)
{
	return x->altura%hash->tamTab;
}
void inserir(Hash *h, Personagem *x)
{
	int i = hash(h, x);

	if(h->tabela[i] == NULL)
	{
		h->tabela[i] = x;
	}
	else
	{
		//insere na lista
		h->lista->ultimo->prox = ConstCelula();
		h->lista->ultimo->elemento = x;
		h->lista->ultimo = h->lista->ultimo->prox;
	}
}
bool pesquisar(Hash *h, char *entrada, int *cont)
{
	bool resp = false;
	for(int i = 0; i < h->tamTab; i++)
	{
		if(h->tabela[i] != NULL && strcmp(h->tabela[i]->nome, entrada) == 0)
		{
			resp = true;
			i = h->tamTab;
		}
		*cont += 2;
	}
	if(!resp)
	{
		for(Celula *i = h->lista->primeiro->prox; i != NULL; i = i->prox)
		{
			if(strcmp(i->elemento->nome, entrada) == 0)
			{
				resp = true;
				i = h->lista->ultimo;
			}
			*cont++;
		}
	}
	return resp;
}
//--------------------------------------------------------------------
//verificar se a string de entrada corresponde a "FIM" 
bool isFim (char *entrada)
{
	return(entrada[0] == 'F' && entrada[1] == 'I' && entrada[2] == 'M');
}
int main()
{
	//declarar variaveis
	char *entrada = (char*)malloc (1000 * sizeof(char));
	//declarar e alocar espaco de memoria para a tabela hash
	Hash *hash = ConstHash();

	//ler entradas e armazenar na arvore
	fgets(entrada, 100, stdin);
	entrada[strlen(entrada)-1] = '\0';
	while(!isFim(entrada))
	{
		//alocar e construir o personagem
		Personagem *sw = (Personagem*) malloc (sizeof(Personagem));
		ConstVazio(sw);
		ler(sw, entrada);
		inserir(hash, sw);	
		fgets(entrada, 100, stdin);
		entrada[strlen(entrada)-1] = '\0';
	}
	//ler a segunda etapa da entrada
	int *cont = (int*) malloc (sizeof(int));
        *cont = 0;
        clock_t inicio = clock();

	fgets(entrada, 100, stdin);
	entrada[strlen(entrada)-1] = '\0';
	while(!isFim(entrada))
	{
		bool resp = pesquisar(hash, entrada, cont);
		if(resp)
		{
			printf("%s SIM\n", entrada);
		}
		else
		{
			printf("%s NÃO\n", entrada);
		}
		fgets(entrada, 100, stdin);
		entrada[strlen(entrada)-1] = '\0';
	}
	clock_t fim = clock();
        double total = (fim - inicio)/(double)CLOCKS_PER_SEC;
	
	//gravar arquivo de log
        FILE *file = fopen("680715_hashIndireta.txt", "wt");
        fprintf(file, "Matricula: 680715;\t %d comparacoes;\t Tempo de execução: %lfs;\t", *cont, total);
        fclose(file);

	return 0;
}

