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
import java.util.Scanner;
public class CalculadoraEscrita {

    /**
     * @param args the command line arguments
     */
    // três = tr�s
    //ver conteudo do Array -> Arrays.toString(Nome da String)
    //converter String para Int -> Integer.parseInt(nome da String)
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Interprete i = new Interprete();
        
        System.out.print(" Digite um número usando algarismos ou por extenso. \n O maior número aceito é 999 (novecentos e noventa e nove) "
                         + "e o menor é -999 (menos novecentos e noventa e nove). \n Para o número três ser aceito, o digite sem acento \n"
                         + " e para fechar, digite 'parar'. \n");        
        String entrada = sc.nextLine();
        
        while(!"parar".equals(entrada)){
            try{
                String n = i.paraExtenso(Integer.parseInt(entrada)).substring(0,1).toUpperCase() + i.paraExtenso(Integer.parseInt(entrada)).substring(1);
                System.out.println(n + "\n");
                entrada = sc.nextLine();                
            }catch(Exception e){
            
            System.out.println(i.paraAlgarismo(entrada));
            System.out.println("");
            entrada = sc.nextLine();
                
            }
            
        }
    }
    
}
