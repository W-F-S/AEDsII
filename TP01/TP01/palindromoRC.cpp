/*--------------------TP01---------------------
Aluna: Ana Laura Fernandes de Oliveira
Matricula: 680715
Disciplina: Algoritmos e Estrutura de dados II
Professor: Max do Val Machado
----------------------------------------------- */
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

bool isPalindromo(char* frase, int i, int j)
{
    //declarar variaveis
    bool resposta;

    //ao verificar todos os chars da cadeia de caracteres e nao encontrar caracteres diferentes, a resposta e true
    if(i == strlen(frase) || j == -1)
    {
        resposta = true;
    }
    //se encontrar caracteres diferentes entre si, atribui falso para a resposta
    else if(frase[i] != frase[j])
    {
        resposta = false;
    }
    //enquanto a resposta nao estiver definida a recursividade e mantida
    else
    {
        resposta = isPalindromo(frase, i + 1, j - 1);
    }
    return resposta; 
}
bool isFim(char *entrada)
{
    return(entrada[0] == 'F' && entrada[1] == 'I' && entrada[2] == 'M');
}
int main()
{
    //declarar variaveis
    char *entrada = NULL;
    bool resposta = false;
    //alocar memoria
    entrada = (char*) malloc (1000 * sizeof(char));
    //ler entrada e conferir se e palindromo enquanto a palavra lida nao for FIM
    fgets(entrada, 1000, stdin);
    while(!isFim(entrada))
    {
        resposta = isPalindromo(entrada, 0, strlen(entrada) -2);
        if(resposta)
        {
            printf("SIM\n");
        }
        else
        {
            printf("NAO\n");
        }
        fgets(entrada, 1000, stdin); 
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