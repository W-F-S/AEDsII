import java.util.*;
public class aquecimento
{
    public static boolean isUpper(final char letra) {
        return ('A' <= letra && letra <= 'Z');
        // retorna true se o caracter for uma letra maiusculas e false se nao for
    }

    public static boolean FIMentrada(final String FIM) {
        boolean resultado = false;
        if (FIM.length() >= 3) {
            if (FIM.charAt(0) == 'F' && FIM.charAt(1) == 'I' && FIM.charAt(2) == 'M') {
                resultado = true;
            }
        }
        return (resultado);

    }

    public static int QuantasMaiusculas(final String palavra) {
        // declaracao de variavel
        int nMaiusculas = 0;

        if (palavra != "" && !(FIMentrada(palavra))) // se a palavra nao estiver vazia, prosseguir
        {
            final int tamanhoPalavra = palavra.length();
            /*
             * atribui o tamanho da palavra a uma variavel para evitar a repeticao da mesma
             * funcao varias vezes no for
             */

            for (int i = 0; i < tamanhoPalavra; i++) {
                if (isUpper(palavra.charAt(i))) // chama a funcao que testa se o char e letra maiuscula
                {
                    nMaiusculas++;
                }
            }
        }
        return (nMaiusculas);
    }

    public static void main(final String[] args) {
        // declarar variaveis
        int numeroEntradas = 0;
        int resposta = 0;
        final String[] entradas = new String[1000];

        // LEMBRAR DE MODIFICAR!!!!!!!!!!!!!!
        final Scanner leitor = new Scanner(System.in);
        
        //ler primeira entrada
        entradas[numeroEntradas] = leitor.nextLine();
        
        //contar quantas linhas de entrada e armazena-las no array de strings
        while(!(FIMentrada(entradas[numeroEntradas])))
        {
            numeroEntradas++;
            entradas[numeroEntradas] = leitor.nextLine();
        }

        //contar as letras maiusculas de cada linha de entrada valida
        for(int i = 0; i < numeroEntradas; i++)
        {
            resposta = QuantasMaiusculas(entradas[i]);
            System.out.println(resposta);
        }
    }
}