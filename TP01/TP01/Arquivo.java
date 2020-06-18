/*--------------------TP01---------------------
Aluna: Ana Laura Fernandes de Oliveira
Matricula: 680715
Disciplina: Algoritmos e Estrutura de dados II
Professor: Max do Val Machado
----------------------------------------------- */
import java.io.*;
public class Arquivo
{
    public static void main(String[] args) throws Exception
    {
        //declarar variaveis
        RandomAccessFile leitor = new RandomAccessFile("file.txt", "rw");
        int numEntradas = 0;
        double aux = 0.0;
        //ler o numero de entradas a serem gravadas no arquivo
        numEntradas = MyIO.readInt();
        //ler entradas e grava-las em arquivo
        for(int i = 0; i < numEntradas; i++)
        {
            aux = MyIO.readDouble();
            leitor.writeDouble(aux);
        }
        //fechar o arquivo
        leitor.close();
        //reabrir o arquivo para ler de tras para a frente
        RandomAccessFile raf = new RandomAccessFile("file.txt", "rw");
        //posicionar o cabeçote para iniciar a leitura pelo ultimo double do arquivo
        raf.seek((numEntradas * 8) - 8); //cada double tem 8bytes, logo o ultimo double inicia no byte (numEntradas * 8) - 8bytes
        for(int i = 0; i < numEntradas; i++)
        {
            aux = raf.readDouble();
            if(aux % 1 == 0) // caso o numero lido nao tenha casas decimais, sera mostrado como inteiro
            {
                MyIO.println((int)aux);
            }
            else//caso contrario, sera mostrado com as casa decimais
            {
                MyIO.println(aux);
            }
            if(raf.getFilePointer() >= 16) //limite para andar com o cabecote, para nao tentar posicionar em posicoes negativas (16 - 16 = 0 //parada)
            {
                raf.seek(raf.getFilePointer() - 16); //-8 do double ja lido, - 8 para posicionar no inicio do proximo double a ser lido
            }
            
        }
        //fechar o arquivo
        raf.close();

    }
}