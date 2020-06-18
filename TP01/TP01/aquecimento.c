#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>


#define NUMENTRADAS 1000
#define LINHA 1000

bool isUpper(char letra)
{
    return('A' <= letra && letra <= 'Z');
}//end isUpper
bool isFim (char *FIM)
{
    bool resultado = false;
    int tamanhoString = strlen(FIM);
    if(FIM != NULL && tamanhoString >= 3)
    {
       if(FIM[0] == 'F' && FIM[1] == 'I' && FIM[2] == 'M')
       {
           resultado = true;
       }//end if
    }//end if
    
    return (resultado);
}//end isFim
int quantasMaiusculas(char *palavra)
{
    int contador = 0;
    int tamanhoPalavra = 0;
    if(palavra != NULL && palavra != "")
    {
        tamanhoPalavra = strlen(palavra);
        for(int i = 0; i < tamanhoPalavra; i++)
        {
            if(isUpper(palavra[i]))
            {
                contador++;
            }//end if
        }//end for
    }//end if

    return (contador);
}//end quantasMaiusculas

int main (int argc, char** argv)
{
    //declarar variaveis
    char palavra[NUMENTRADAS][LINHA];
    int numEntradas = 0; //contador para as entradas
    int numMaiusculas = 0;

    //receber a primeira string como input
    fgets(palavra[numEntradas], LINHA, stdin);

    //receber as demais strings como input
    while(!(isFim(palavra[numEntradas])))
    {
        numEntradas++;
        fgets(palavra[numEntradas], LINHA, stdin);
    }

    //contar as maiusculas presentes em cada linha de entrada
    for(int i = 0; i < numEntradas; i++)
    {
        numMaiusculas = quantasMaiusculas(palavra[i]);
        printf("%d\n", numMaiusculas);
    }

    //liberar memoria alocada dinamicamente
    free(palavra);

    //fim do programa
    return 0;
}