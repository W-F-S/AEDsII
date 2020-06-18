/*----------------------TP1---------------------
 * Aluna: Ana Laura Fernandes de Oliveira
 * Matricula: 680715
 * Disciplina: Algoritmos e Estrutura de Dados II
 * Professor: Max do Val Machado
 * ---------------------------------------------*/
/*import java.net.*;
import java.io.*;

 public class PaginaHTML3
{
    public static void contadorHTML(String url, String nomePagina) throws Exception
    {
        //declarar variaveis
        int a = 0, e = 0, i = 0, o = 0, u = 0, � = 0, � = 0, � = 0, � = 0, � = 0, � = 0, � = 0, � = 0, � = 0, � = 0, � = 0, � = 0, � = 0, � = 0, � = 0, � = 0, � = 0;
        int consoantes = 0;
        int table = 0;
        int br = 0;
        int j = 0; 
        int tamanho = 0;
        int aux = 0;
        String html = "";
        //ler de pagina html
        URL site = new URL(url);
        URLConnection web = site.openConnection();
        BufferedReader leitor = new BufferedReader(new InputStreamReader(web.getInputStream()));
        while((html = leitor.readLine()) != null)
        { 
            //String html = new String(receptora.getBytes(), "ISO-8859-1");
            j = 0;
            tamanho = html.length();
            while(j < tamanho)
            {
                //primeiro verificar e contar vogais, em seguida consoantes e por ulttmo as tags <table> e <br>
                if(html.charAt(j) == 'a')
                {
                    a++;
                }
                else if(html.charAt(j) == 'e')
                {
                    e++;
                }
                else if(html.charAt(j) == 'i')
                {
                    i++;
                }
                else if(html.charAt(j) == 'o')
                {
                    o++;
                }
                else if(html.charAt(j) == 'u')
                {
                    u++;
                }
                else if(html.charAt(j) == '�')
                {
                    �++;
                }
                else if(html.charAt(j) == '�')
                {
                    �++;
                }
                else if(html.charAt(j) == '�')
                {
                    �++;
                }
                else if(html.charAt(j) == '�')
                {
                    �++;
                }
                else if(html.charAt(j) == '�')
                {
                    �++;
                }
                else if(html.charAt(j) == '�')
                {
                    �++;
                }
                else if(html.charAt(j) == '�')
                {
                    �++;
                }
                else if(html.charAt(j) == '�')
                {
                    �++;
                }
                else if(html.charAt(j) == '�')
                {
                    �++;
                }
                else if(html.charAt(j) == '�')
                {
                    �++;
                }
                else if(html.charAt(j) == '�')
                {
                    �++;
                }
                else if(html.charAt(j) == '�')
                {
                    �++;
                }
                else if(html.charAt(j) == '�')
                {
                    �++;
                }
                else if(html.charAt(j) == '�')
                {
                    �++;
                }
                else if(html.charAt(j) == '�')
                {
                    �++;
                }
                else if(html.charAt(j) == '�')
                {
                    �++;
                }
                else if(html.charAt(j) == '�')
                {
                    �++;
                }
                else if(('a' <= html.charAt(j) && html.charAt(j) <= 'z'))
                {
                    consoantes++; //se tiver vogal ela foi contada antes de chegar aqui, entao as letras remanescentes nao contadas serao consoantes
                }
                else if(html.charAt(j) == '<')
                {
                    aux = j + 1; // para nao interferir na variavel j, vou utilizar uma variavel auxiliar para fazer essa checagem das tags
                    if(j < tamanho && html.charAt(aux) == '/')
                    {
                        aux = 0;
                        //se for tag de fechamento, contar letras normalmente
                    }
                    else
                    {
                        if(html.charAt(aux) == 't')
                        {
                            aux++;
                            if(j < tamanho && html.charAt(aux) == 'a' &&  html.charAt(aux + 1) == 'b' && html.charAt(aux + 4) == '>') //se testar somente o 't' pode ser que confunda com outra tag como a title
                            {
                                table++;
                                consoantes -= 3;
                                a--;
                                e--;
                                aux = 0;
                            }
                        }
                        else if(html.charAt(aux) == 'b')//testar se e tag de abertura br (paragrafo)
                        {
                            aux++;
                            if(j < tamanho && html.charAt(aux) == 'r' && html.charAt(aux + 1) == '>')
                            {
                                br++;
                                consoantes -= 2;
                                aux = 0;
                            }
                        }//end else if
                    }//end else
                }//end else if

                j++;
            }
        }
        //fechar o leitor
        leitor.close();

        //printar resultados
        String contadores = ("a(" + a + ") " +  "e(" + e + ") " + "i(" + i + ") " + "o(" + o + ") " + "u(" + u + ") " + "�(" + � + ") " + "�(" + � + ") " 
			+ "�(" + � + ") " + "�(" + � + ") " + "�(" + � + ") " + "�(" + � + ") " + "�(" + � + ") "
        		+ "�(" + � + ") " + "�(" + � + ") " + "�(" + � + ") " + "�(" + � + ") " + "�(" + � + ") " + "�(" + � + ") "
        		+ "�(" + � + ") " + "�(" + � + ") " + "�(" + � + ") " + "�(" + � + ") " + "consoante(" + consoantes + ") " + "<br>(" + br + ") "
        		+ "<table>(" + table + ") " + nomePagina);
	String converter = new String(contadores.getBytes(), "ISO-8859-1"); //muda o encoding para ISO antes de printar
	MyIO.println(converter);
    }
    public static boolean isFim(String entrada)
    {
        return(entrada.charAt(0) == 'F' && entrada.charAt(1) == 'I' && entrada.charAt(2) == 'M');
    }
    public static void main(String[] args) throws Exception
    {
        String nomeSite;
        String url;
        //ler entradas
        nomeSite = MyIO.readLine();
        while(!isFim(nomeSite))
        { 
            url = MyIO.readLine();
            //chamar procedimento para contar as letras e tags pedidas no enunciado
            contadorHTML(url, nomeSite);
            nomeSite = MyIO.readLine();
        }
        
    }
}
*/