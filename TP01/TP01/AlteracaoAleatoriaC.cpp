/*--------------------TP01---------------------
Aluna: Ana Laura Fernandes de Oliveira
Matricula: 680715
Disciplina: Algoritmos e Estrutura de dados II
Professor: Max do Val Machado
----------------------------------------------- */
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
//Funcao para trocar as letras na cadeia de caracteres
char* trocaletras(char letraOriginal, char letraMudar, char *frase, int i)
{
    //declarar variaveis
    int tamanho = 0;
    //verificar o tamanho da frase a ser alterada
    tamanho = strlen(frase);
    //verificar se o char na posicao i e igual a letra a ser trocada
    if(i < tamanho && frase[i] == letraOriginal)
    {
        frase[i] = letraMudar;
        trocaletras(letraOriginal, letraMudar, frase, i + 1);
    }
    else if( i < tamanho && frase[i] != letraOriginal)
    {
        trocaletras(letraOriginal, letraMudar, frase, i + 1);
    }
    
    return frase;
}
//testar se cadeia de caracteres e FIM
bool isFim(char *frase)
{
    return(frase[0] == 'F' && frase[1] == 'I' && frase[2] == 'M');
}
int main()
{
    //declarar variaveis
    char letraOriginal = '-';
    char letraMudar = '-';
    char *entrada;
    entrada = (char*) malloc(1000 * sizeof(char));
    srand(4);
    //ler primeira entrada
    fgets(entrada, 1000, stdin);
    while(!isFim(entrada))
    {
        //sortear letras
        letraOriginal = (char)(97 + (rand() % 26));
        letraMudar = (char)(97 + (rand() % 26));
        //trocar letras na entrada
        entrada = trocaletras(letraOriginal, letraMudar, entrada, 0);
        printf("%s", entrada);
        //receber nova entrada
        fgets(entrada, 1000, stdin);
    }
    //liberar memoria alocada
    free(entrada);
    //fim do programa
    return 0;
}