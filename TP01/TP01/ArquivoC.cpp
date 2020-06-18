/*--------------------TP01---------------------
Aluna: Ana Laura Fernandes de Oliveira
Matricula: 680715
Disciplina: Algoritmos e Estrutura de dados II
Professor: Max do Val Machado
----------------------------------------------- */
#include <stdio.h>

int main(int argc, char* argv[])
{
    //declarar variaveis
    FILE *file = fopen("arquivo.dat", "wb");
    int numEntradas = 0;
    double aux = 0.0;
    //ler numero de entradas a serem lidas
    scanf("%d", &numEntradas);

    //ler entradas e grava-las no arquivo
    for(int i = 0; i < numEntradas; i++)
    {
        scanf("%lf", &aux);
        fwrite(&aux, 1, sizeof(double), file);
    }
    //fechar o arquivo
    fclose(file);
    //reabrir o arquivo para leitura
    FILE *arquivo = fopen("arquivo.dat", "rb");
    //posicionar o cursor -8 bytes do final do arquivo para ler o primeiro double
    fseek(arquivo, -8L, SEEK_END);
    //percorrer o arquivo de tras pra frente lendo os numeros
    for(int i = 0; i < numEntradas; i++)
    {
        fread(&aux, 1, sizeof(double), arquivo);
        printf("%g\n", aux); //printar sem mostrar casas decimais vazias
        fseek(arquivo, -16L, SEEK_CUR); //variar a posicao do cursor em -16 (8 do double anterior + 8 pra posicionar no inicio do outro double) a partir da posicao atual
    }
    //fechar o arquivo
    fclose(arquivo);
}