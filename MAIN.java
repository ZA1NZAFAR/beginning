//---------------------------------------------------- Zain Zafar ----------------------------------------------------//
import java.util.Scanner;
public class MAIN{
  public static void main(String[] args){
    Scanner sknr = new Scanner(System.in);
    char rep;
    do{
      char[][] dic = z.getDictionnaire();
      char[] tirage = z.donnerTirage();
      System.out.println(tirage);
      System.out.print("Proposez un mot: ");
      char[] input = sknr.next().toCharArray();
      input= z.upperCase(input);
      z.afficherComparaison(input,tirage);
      System.out.println("Votre mot fait "+input.length+" lettres.");
      if(!z.compareInputTirage
           (input,tirage)){
        System.out.println("--> Votre mot ne correspond pas au tirage.");
      }
      if(!z.compareInputDic(input, dic)){
        System.out.println("--> Votre mot n'existe pas dans le dictionnaire.");
      }
      z.motsPlusLong(tirage, dic);
      System.out.println("Rejouer?  O/N");
      rep = sknr.next().charAt(0);
    }while (rep != 'n' && rep != 'N');
    sknr.close();
  }
}