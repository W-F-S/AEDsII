/*--------------------TP01---------------------
Aluna: Ana Laura Fernandes de Oliveira
Matricula: 680715
Disciplina: Algoritmos e Estrutura de dados II
Professor: Max do Val Machado
----------------------------------------------- */
public class PalindromoR
{
    //verificar se chegou ao fim da leitura de entradas (quando recebe o input FIM)
    public static boolean isFim(String frase)
    {
        return(frase.charAt(0) == 'F' && frase.charAt(1) == 'I' && frase.charAt(2) == 'M');
    }

    //inicializador da recursividade
    public static boolean isPalindromo(String frase)
    {
        return(isPalindromo(frase, 0, frase.length() - 1));
    }
    //funcao recursiva para verificar se string e palindromo
    public static boolean isPalindromo(String frase, int i, int j)
    {
        //declarar variavel
        boolean resposta;
            //se percorrer toda a frase sem encontrar nenhum caracter diferente, a resposta e true
            if(j == -1 || i == frase.length())
            {
                resposta = true;
            }
            //se encontrar char diferente entre si, atribui falso a resposta
            else if((int)frase.charAt(j) != (int)frase.charAt(i))
            {
                resposta = false;
            }
            //enquanto a resposta nao for definida como true ou false, a recursividade se mantem
            else
            {
                resposta = isPalindromo(frase, i + 1, j - 1); //motor da recursividade
            }
        return(resposta);
    }

    public static void main(String[] args)
    {
        //declarar variaveis
        String[] entradas = new String[1000]; //numero de strings escolhido de forma arbitraria
        int numEntradas = 0;                  //numero de entradas
        boolean resposta = false;
        //receber entradas
        entradas[numEntradas] = MyIO.readLine();
        //testar se a entrada lida corresponde a FIM (codigo de parada da leitura)
        while(!isFim(entradas[numEntradas]))
        {
            //verificar se a string lida e palindromo
            resposta = isPalindromo(entradas[numEntradas]);
            if(resposta)
            {
                MyIO.println("SIM");
            }
            else
            {
                MyIO.println("NAO");
            }
            //ler proxima entrada
            numEntradas++;
            entradas[numEntradas] = MyIO.readLine();
        }
    }
}

/*----------------Testes-----------------
Input                                     /Output
ana                                       SIM
Algoritmos e Estrutura de Dados II        NAO
teste do algoritmo                        NAO
ciencia da computacao                     NAO
abacaxi ixacaba                           SIM
*/ 