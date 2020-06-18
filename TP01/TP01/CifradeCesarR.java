/*--------------------TP01---------------------
Aluna: Ana Laura Fernandes de Oliveira
Matricula: 680715
Disciplina: Algoritmos e Estrutura de dados II
Professor: Max do Val Machado
----------------------------------------------- */
public class CifradeCesarR
{
    //inicializador da recursividade
    public static String codificar(String frase)
    {
        return(codificar(frase, 0));
        
    }
    public static String codificar (String copia, int i)
    {
        //declaracao de variaveis
        String resposta = "";
        char letra = ' ';
        
        //base da recursividade: i < tamanho da string;
        if(i < copia.length())
        {
            letra = (char)(copia.charAt(i) + 3); //codificando o char usando chave de 3 caracteres para a direita na tabela ascii
            resposta = letra + codificar(copia, i + 1); //motor da recursividade
        }
        return(resposta);
    }
    
    //funcao para verificar se string lida corresponde a FIM
    public static boolean isFim(String frase)
    {
        return(frase.charAt(0) == 'F' && frase.charAt(1) == 'I' && frase.charAt(2) == 'M');
    }
    public static void main (String[] args)
    {
       //declarar variaveis
       String[] entradas = new String[1000];
       int numEntradas = 0;

       //receber entradas
       entradas[numEntradas] = MyIO.readLine();
       while(!isFim(entradas[numEntradas]))
       {
           entradas[numEntradas] = codificar(entradas[numEntradas]); //codifica a mensagem recebida
           MyIO.println(entradas[numEntradas]);                      //printa a mensagem codificada
           numEntradas++;                                       
           entradas[numEntradas] = MyIO.readLine();                  //le proxima mensagem a codificar
       }
    }
}

/*----------------Testes-----------------
Input                                     /Output
cifra                                      fliud
Algoritmos e Estrutura de Dados II         Dojrulwprv#h#Hvwuxwxud#gh#Gdgrv#LL
teste do algoritmo                         whvwh#gr#dojrulwpr
ciencia da computacao                      flhqfld#gd#frpsxwdfdr
*/