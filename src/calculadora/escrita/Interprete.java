/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculadora.escrita;

/**
 *
 * @author franc
 */
public class Interprete {
    
    private static final String[][] extenso = {
                                       {"zero","um","dois","três","quatro","cinco","seis","sete","oito","nove","dez",
                                        "onze","doze","treze","quatorze","quinze","dezesseis","dezessete","dezoito","dezenove"},
                                       {"","","vinte","trinta","quarenta","cinquenta","sessenta","setenta","oitenta","noventa"},
                                       {"cem","cento","duzentos","trezentos","quatrocentos","quinhentos","seiscentos", "setecentos","oitocentos","novecentos"}
                                      };
                                      //coloquei até o dezenove nas unidades pois todos números até ele são formados de uma só palavra
    public static int paraAlgarismo(String numero){
        numero = numero.toLowerCase();
        String n[] = numero.split(" ");//isso separa as palavras da String recebida palos espaços entre elas
        
        int sinal = 1;
        int soma = 0;
        boolean erro = false;
        
        for(int i=0; i < n.length; i++){ //esse for percorre o vetor "n" que tem as palavras separadas
            if("-".equals(Index(n[i]))){ //já esse if envia a palavra que vai ser analizada pro Index e, se ele retornar "-", muda o valor da variavel "sinal"
                sinal = -1;
                i++; // e faz o resto do primeiro "for" analizar a próxima palavra
            }
            
            if("tres".equals(n[i]))n[i] = "três"; // fiz isso pois o Scanner não consegue "entender" caracteres especiais como o assento (^)
            
            String indice[] = Index(n[i]).split(" "); // esse vetor vai receber o retorno do Index e vai separá-lo a partir dos espaços
            
            for(int x = 0; x<indice.length; x++){ //esse "for" percorre o vetor "indice"
                String ind[] = indice[x].split(","); //e esse if vai separá-lo a partir das vírgulas
                int y = Integer.parseInt(ind[0]);//Linha
                int z = Integer.parseInt(ind[1]);//Coluna
                
                if(extenso[y][z].equals(n[i]) || n[i].equals("e")){ //esse "ou (||)" serve para ele ignorar o fato de que "e" é diferente de "zero"
                    if(y == 2 && z == 0)z = 1; // e eu coloquei isso pois coloquei a palavra "cem" na posição "2,0" e isso faria ela ter valor "0"
                    
                    soma += (int) (z * (Math.pow(10, y)));
                    break;
                }
                if(x == indice.length - 1){//se estiver na última repetição desse "for" e não encontrar o número, o "erro" mudará para true
                    erro = true;
                    System.out.println("Erro: '"+n[i]+"'.");
                    
                    break;
                }                
            }
            if(erro == true){soma = 0; break;} //e isso fará o erro parar o "for" e zerar a soma
        }
                        
        return soma * sinal; // se a variável sinal tiver mudado para "-1", isso faz o número retornado ser negativo
    }
    
    private static String Index(String palavra){
        
        String inicio = palavra.substring(0,1);//pega a primeira letra da palavra analizada
               
        if("e".equals(palavra))return "0,0"; // na hora de somar, o valor do "e" é considerado "0"
        if("menos".equals(palavra))return "-";
        
        if("z".equals(inicio))return "0,0";
        if("u".equals(inicio))return "0,1";
        if("d".equals(inicio))return "0,2 0,10 0,12 0,16 0,17 0,18 0,19 2,2";
        if("c".equals(inicio))return "0,5 1,5 2,0 2,1";
        if("v".equals(inicio))return "1,2";
        if("t".equals(inicio))return "0,3 0,13 1,3 2,3";
        if("q".equals(inicio))return "0,4 0,14 0,15 1,4 2,4 2,5";
        if("s".equals(inicio))return "0,6 0,7 1,6 1,7 2,6 2,7";
        if("o".equals(inicio))return "0,8 0,11 1,8 2,8";
        if("n".equals(inicio))return "0,9 1,9 2,9";
        
        
        return "0,0"; // já isso serve para o "paraAlgarismo" comparar a palavra não encontrada pelos "if"s 
                      //com a palavra "zero" e isso cair no if da linha 50      
        
    }
    public static String paraExtenso(int x){
        
        if(x > 999 || x < -999)return "Não são aceitos números maiores que 999 ou menores que -999";        
        String palavra = "";
        
        if(x < 0){palavra = "menos "; x = x * -1;}
        
        if(x == 100) return palavra + "cem";
        if(x > 100 && x % 100 == 0) return palavra + extenso[2][x/100];
        if(x > 100 && x % 100 > 0){palavra += extenso[2][x/100] + " e "; x = x % 100;}
        
        if(x > 10 && x % 10 == 0){palavra += extenso[1][x /10]; return palavra;}
        if(x % 10 > 0 && x > 19){palavra += extenso[1][x / 10]+ " e "+ extenso[0][x % 10]; return palavra;}
        if(x <= 19){palavra += extenso[0][x]; return palavra;}
        return extenso[1][x];        
    }
    /*
    
    0 zero
    1 um, dez, onze, cem
    2 dois, doze, vinte, duzentos
    3 três, treze, trinta, trezentos
    4 quatro, quatorze, quarenta, quatrocentos
    5 cinco, quinze, cinquenta, quinhentos
    6 seis, dezesseis, sessenta, seiscentos
    7 sete, dezessete, setenta, setecentos
    8 oito, dezoito, oitenta, oitocentos
    9 nove, dezenove, noventa, novecentos
    
    
    */
    
}