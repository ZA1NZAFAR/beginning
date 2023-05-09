//---------------------------------------------------- Zain Zafar ----------------------------------------------------//
public class z {
//afficher une tableaux-------------------------------------------------------------------------------------------------
  public static void afficherTab(char[] tab){
    for(int i=0;i<tab.length;i++){
      System.out.print(tab[i]+" ");
    }
    System.out.println(" ");
  }
//----------------------------------------------------------------------------------------------------------------------
  
//convertir en majuscul-------------------------------------------------------------------------------------------------
  public static char[] upperCase(char[] a){
    for(int i=0;i<a.length;i++){
    a[i]= Character.toUpperCase(a[i]);
    }
    return a;
  }
//----------------------------------------------------------------------------------------------------------------------
  
//TIRAGE----------------------------------------------------------------------------------------------------------------
  public static char[] donnerTirage() {
    double[] frequences = { 0.084641116045612, 0.097550346533667, 0.134264256131715, 0.175931501299866,
      0.339787563705305, 0.352412789645191, 0.366356662429924, 0.37898710353533, 0.454338255487703,
      0.458247915903501, 0.461526843221907, 0.517821090574744, 0.547529673593674, 0.620002807215,
      0.677444355180383, 0.70566493929644, 0.71299279223788, 0.781799811286431, 0.855650833407438,
      0.922800098125308, 0.974199670376239, 0.986838818540602, 0.98881203071521, 0.993095137557963,
      0.998288765757996, 1 };
    char[] res = new char[10];
    for (int i = 0; i < res.length; i++) {
      double ausort = Math.random();
      int j = 0;
      while (frequences[j] < ausort)
        j++;
      res[i] = (char) ('A' + j);
    }
    java.util.Arrays.sort(res);
    return res;
  }  
//----------------------------------------------------------------------------------------------------------------------
  
//DICTIONAIRE-----------------------------------------------------------------------------------------------------------
  public static char[][] getDictionnaire() {
    char[][] res;
    char[][] temp = new char[500000][];
    int nbMots = 0;
    try {
      java.io.FileReader fr = new java.io.FileReader("dico_nfa031.txt");
      java.io.BufferedReader br = new java.io.BufferedReader(fr);
      String ligne = br.readLine();
      while (ligne != null) {
        temp[nbMots] = ligne.trim().toCharArray();
        nbMots++;
        ligne = br.readLine();
      }
      br.close();
    } catch (java.io.FileNotFoundException e) {
      throw new RuntimeException("Fichier dico_nfa031.txt non trouvé");
    } catch (java.io.IOException e) {
      throw new RuntimeException("Problème �  la lecture du fichier");
    }
    res = new char[nbMots][];
    for (int i = 0; i < nbMots; i = i + 1) {
      res[i] = temp[i];
    }
    return res;
  }  
//----------------------------------------------------------------------------------------------------------------------
  
//COMPARAISON AVEC DICTIONAIRE------------------------------------------------------------------------------------------
  public static boolean compareInputDic(char[] input, char[][] dic) {
    int counter = 0,l=0;
    boolean result = false;
    while(!result&&l<dic.length){
      if (input.length == dic[l].length) {
        for (int i = 0; i < input.length; i++) {
          if (dic[l][i] == input[i]) {
            counter++;
          }
          if (counter == input.length && counter == dic[l].length) {
            i = input.length + 1;
            l = dic.length;
            result = true;
          }
        }
      }
      counter=0;
      l++;
    }
    return result;
  }  
//----------------------------------------------------------------------------------------------------------------------
  
//Comparaison avec le Tirage--------------------------------------------------------------------------------------------
  public static char[] compareAvecTirage(char[] input, char[] Tirage) {
    char[] temp = new char[input.length];
    char[] tirage = new char[Tirage.length];
    for (int i = 0; i < Tirage.length; i++) {
      tirage[i] = Tirage[i];
    }
    for (int i = 0; i < input.length; i++) {
      boolean match = false;
      int x = 0;
      while (!match && x < tirage.length) {
        if (input[i] == tirage[x]) {
          tirage[x] = '~';
          match = true;
          temp[i] = (char) (x + 48);
        } else {
          temp[i] = 'X';
        }
        x++;
      }
    }    
    return temp;
  }  
//----------------------------------------------------------------------------------------------------------------------

//Affichage Input/Tirage------------------------------------------------------------------------------------------------
  public static void afficherComparaison(char[] input,char[] tirage){
    char[] temp = compareAvecTirage(input,tirage);    
    afficherTab(input);
    afficherTab(temp);
    System.out.println("0 1 2 3 4 5 6 7 8 9");
    afficherTab(tirage); 
  }
//----------------------------------------------------------------------------------------------------------------------

//Affichage Input/Tirage------------------------------------------------------------------------------------------------
  public static boolean compareInputTirage(char[] input,char[] tirage){
    char[] temp=compareAvecTirage(input,tirage);
    boolean egal=true;
    for(int i=0;i<temp.length;i++) {if (temp[i]=='X') {egal=false;}}
    return egal;
  }
//----------------------------------------------------------------------------------------------------------------------
 
  
//Tous les mots possible------------------------------------------------------------------------------------------------
  public static char[][] tousLesMotsPossible(char[] Tirage, char[][] dic) {
    char[][] temp = new char[dic.length][];
    int num = 0;
    for (int i = 0; i < dic.length; i++) {
      char[] tirage = new char[Tirage.length];
      for (int k = 0; k < Tirage.length; k++) {tirage[k] = Tirage[k];}
      int counter = 0;
      for (int j = 0; j < dic[i].length; j++) {
        int boucleTirage = 0;
        boolean match = false;
        while (!match && boucleTirage < tirage.length) {
          if (dic[i][j] == tirage[boucleTirage]) {
            tirage[boucleTirage] = '~';
            match = true;
            counter++;
          }
          boucleTirage++;
        }
        if (counter == dic[i].length) {
          temp[num] = dic[i];
          num++;
        }
      }
    }
    char[][] result = new char[num][];
    for (int i = 0; i < num; i++) {
      result[i] = temp[i];
    }
    return result;
  }  
//----------------------------------------------------------------------------------------------------------------------
  
//Mot plus long---------------------------------------------------------------------------------------------------------
  public static void motsPlusLong(char[] Tirage, char[][] dic) {
    int max = 0,x=0;
    char[][] tousMots=tousLesMotsPossible(Tirage, dic);
    for (int i = 0; i < tousMots.length; i++) {
      max = Math.max(max, tousMots[i].length);
    }
    char[][] temp = new char[1000][];
    for (int i = 0; i < tousMots.length; i++) {
      if(max==tousMots[i].length) {
        temp[x]=tousMots[i];
        x++;
      }
    }
    char[][] result= new char[x][];
    for(int i=0;i<x;i++) {
      result[i]=temp[i];
    }
    System.out.print("--> Plus long mot(s) possible: [ ");
    for (int i = 0; i < result.length; i++) {
      System.out.print(result[i]);
      System.out.print(" ");
    }
    System.out.println("]");
  }
//----------------------------------------------------------------------------------------------------------------------
}