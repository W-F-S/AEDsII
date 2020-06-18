/*--------------------TP01---------------------
Aluna: Ana Laura Fernandes de Oliveira
Matricula: 680715
Disciplina: Algoritmos e Estrutura de dados II
Professor: Max do Val Machado
----------------------------------------------- */
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>

bool isPalindromo(char* frase)
{
    //declaracao de variaveis
    bool resposta = false;
    int j = 0;
    int tamanho = 0;

    if(frase != NULL)
    {
        //obter o tamanho da string
        tamanho = strlen(frase);

        //verificar se palavra lida e um palindromo
        for(int i = tamanho - 2; i >= 0; i--) // o -2 e para eliminar o \n lido pelo fgets e para chegar na ultima posicao valida
        {
            if(frase[i] != frase[j]) //ao encontrar um caracter diferente, ja atribui o valor de falso e sai do loop
            {
                resposta = false;
                i = -1;
            }
            else
            {
                resposta = true;
            }
            j++;
        }
    }
    
    return resposta;
}
bool isFim(char* frase)
{
    return(frase[0] == 'F' && frase[1] == 'I' && frase[2] == 'M');
}
int main(int argc, char* argv[])
{
    //declaracao de variaveis
    char* entrada;
    int numEntradas = 0;
    bool resposta = false;
    
    //alocar memoria dinamicamente para receber as entradas
    entrada = (char*) malloc (1000 * sizeof(char));
    
    //receber entradas
    fgets(entrada, 1000, stdin);

    while(!(isFim(entrada)))
    {
        //testar se 'string' lida e um palindromo
        resposta = isPalindromo(entrada);
        
        if(resposta)
        {
            printf("%s\n", "SIM");
        }
        else
        {
            printf("%s\n", "NAO");
        }
        fgets ( entrada, 1000, stdin );
    }
    
    //liberar memoria alocada
    free(entrada);

    //fim do programa
    fflush(stdin);
    return 0;
}

/*----------------Testes-----------------
Input                                     /Output
ana                                       SIM
Algoritmos e Estrutura de Dados II        NAO
teste do algoritmo                        NAO
ciencia da computacao                     NAO
abacaxi ixacaba                           SIM
*/ 