import java.util.Random;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class conway{
   public static boolean [][] game = new boolean[32][32];
   public static boolean[][] solution = new boolean[32][32];
   public static int count = 0;
   public static boolean[][] csol = new boolean[8][8];
   public static boolean[][] bestsol = new boolean[8][8];
   public static int fit = 0;
   public static int bestfit = 0;
   public static int rand = 0;
   public static Random random = new Random();

    
   public static void main(String[] args) {
   
      System.out.println("Check 00,00,00,00,FF,00,00,00: ");
      for(int i = 12; i <= 19; i++){
         game[16][i] = true;
      }
      findfit();
      System.out.println("Check 00,00,20,70,50,20,00,00: ");
      game = new boolean[32][32];
      bestfit = 0;
     
      game[14][14] = true;
      game[15][13] = true;
      game[15][14] = true;
      game[15][15] = true;
      game[16][13] = true;
      game[16][15] = true;
      game[17][14] = true;
     
      findfit();
     
      game = new boolean[32][32];
      System.out.println("Optimizer: ");
      Timer timer = new Timer();
      timer.schedule(
         new TimerTask() { 
            @Override  
            public void run() {
            
            
               for(int i = 0; i < 8; i++){
                  for(int j = 0; j <8 ; j++){
                     if(j != 7){
                        if(bestsol[i][j]){
                           game[i+12][j+12] = true;
                           System.out.print("[*]");
                        }else{
                           System.out.print("[ ]");
                           game[i+12][j+12] = false;
                        }
                     }else{
                        if(bestsol[i][j]){
                           game[i+12][j+12] = true;
                           System.out.println("[*]");
                        }else{
                           game[i+12][j+12] = false;
                           System.out.println("[ ]");
                        }
                     }
                  }
               }
              
            }
         },  720000);
      
      while( bestfit < 800){
         randomize();
                  
         findfit();
      
              
         game = new boolean[32][32];
         
      
      
      
      }
            
   }
   public static void randomize(){
      
      for(int i = 12; i <= 19; i++){
      
         
         for(int j = 12; j <= 19; j++){
         
            rand = random.nextInt(2);
            if(rand == 1){
               game[i][j] = true;
               csol[i-12][j-12] = true;
            }else{
               game[i][j] = false;
               csol[i-12][j-12] = false;
            }
           // b++;
         }
         //a++;
         //b = 0;
      }
   }
   public static void findfit(){
         
      for(int i = 0; i < 1000; i++){
         run();
         game = solution;
         solution  =  new boolean[32][32];
      }
      for(int i = 0; i < 31; i++){
      
         for(int j = 0; j < 31; j++){
            if(game[i][j]){
               fit++;
            }
         }
      }
      if(fit > bestfit){
         bestfit = fit;
         bestsol = csol;
         game = new boolean[32][32];
         dumphex();
         
         System.out.println(bestfit);
      }
      fit = 0;
      csol = new boolean[8][8];
      
   
   
   }
   public static void dumpgame(){
   
      for(int i = 0; i <= 31; i++){
         for(int j = 0; j <= 31; j++){
            if(j != 31){
               if(game[i][j]){
                  System.out.print("[*]");
               }else{
                  System.out.print("[ ]");
               }
            }else{System.out.println("[ ]");}
         }
      }
   
   }
   public static void dumphex(){
      for(int i = 0; i < 8; i++){
         for(int j = 0; j <8 ; j++){
            if(j != 7){
               if(bestsol[i][j]){
                  game[i+12][j+12] = true;
                  System.out.print("[*]");
               }else{
                  System.out.print("[ ]");
                  game[i+12][j+12] = false;
               }
            }else{
               if(bestsol[i][j]){
                  game[i+12][j+12] = true;
                  System.out.println("[*]");
               }else{
                  game[i+12][j+12] = false;
                  System.out.println("[ ]");
               }
            }
         }
      }
   
      int counter = 3;
      int number = 0;
      int location = 0;
      String  hex = "";
      for(int i = 0; i <= 31; i++){
         for(int j = 0; j <= 31; j++){
           
            if(counter == 0){
               if(game[i][j]){
                  number = number + 1;
               }
            
               
               if(number == 15){
                  hex  = hex + "f";
               }else  if(number == 14){
                  hex = hex + "e";
               }else  if(number == 13){
                  hex  = hex + "d";
               }else  if(number == 12){
                  hex  = hex + "c";
               }else  if(number == 11){
                  hex  = hex  + "b";
               }else  if(number == 10){
                  hex  = hex + "a";
               }else{
                  hex  = hex + number;
               
               }
            
            
               counter = 3;
               number = 0;
            
            
            }else if(counter == 3){
               if(game[i][j]){
                  number = number + 8;
               }
               counter--;
               
            }else if(counter == 2){
               if(game[i][j]){
                  number = number + 4;
               }
               counter--;
               
            }else if(counter == 1){
               if(game[i][j]){
                  number = number + 2;
               }
               counter--;
               
            }
            if( j == 31){
               System.out.println(hex);
               hex = "";
            }
         }
            
      }
   }
   
   
   public static void run(){
      
      
      for(int i = 0; i < 31; i++){
      
         for(int j = 0; j < 31; j++){
            count = 0;
            if(j !=0 && i != 0){
               if(game[i-1][j]){
                 
                  count++;
               }
               if(game[i+1][j]){
                  count++;
               }
               if(game[i][j+1]){
                  
                  count++;
               }
               if(game[i][j-1]){
                  count++;
               }
               if(game[i+1][j+1]){
                  
                  count++;
               }
               if(game[i+1][j-1]){
                  
                  count++;
               }
               if(game[i-1][j+1]){
                  count++;
               }
               if(game[i-1][j-1]){
                  
                  count++;
               }
               
               if(game[i][j]){
                  if(count == 2 || count == 3){
                     solution[i][j] = true;
                  }else{
                     solution[i][j] = false;
                  }
               }else{
                  if(count == 3){
                     solution[i][j] = true;
                  }else{
                     solution[i][j] = false;
                  }
                  
               }
            }
            count = 0;
         }
      }
      
   }

}
