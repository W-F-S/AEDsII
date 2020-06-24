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
	printf(" ##%s ## %d ## %g", sw->nome, sw->altura, sw->peso);
        printf(" ##%s ##%s ##%s", sw->corDoCabelo, sw->corDaPele, sw->corDosOlhos);
        printf(" ##%s ##%s ##%s ##\n", sw->anoNascimento, sw->genero, sw->homeworld);
}
//metodo para obter informacoes do arquivo de entrada e atribuir os valores as variaveis da struct
void ler(Personagem *sw, char *fileName)
{
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

//verificar se a string de entrada corresponde a "FIM" 
bool isFim (char *entrada)
{
	return(entrada[0] == 'F' && entrada[1] == 'I' && entrada[2] == 'M');
}
int main()
{
	//declarar variaveis
	char *entrada = (char*)malloc (1000 * sizeof(char));
	Personagem *sw = (Personagem*) malloc( sizeof(Personagem));
	ConstVazio(sw);

	//ler entrada
	scanf("%s", entrada);
	//printf("ENTRADA = %s", entrada);
	while(!isFim(entrada))
	{
		ler(sw, entrada);
	    	imprimir(sw);
		scanf("%s", entrada);
	}
	//liberar memoria alocada
	free(sw);
	return 0;
}

