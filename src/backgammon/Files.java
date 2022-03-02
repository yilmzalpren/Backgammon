/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backgammon;
import java.io.File ; 
import java.io.FileNotFoundException ; 
import java.io.FileWriter ; 
import java.io.FileReader ; 
import java.io.BufferedWriter ; 
import java.io.BufferedReader  ;
import java.io.IOException ; 
import java.util.StringTokenizer;

/**
 *
 * @author YILMAZ
 */
// this Files class provide write and read functions for backgammon game.
public class Files {
    static boolean possResumeGame = false   ;
    
    static boolean resumeGame = false ; 
    
    static void printTable(){
        File tableFile = null ; 
        BufferedWriter writeToFile = null ; 
        try{
            tableFile = new File("src/file_organization_project/Files/Table.dat") ; 
            writeToFile = new BufferedWriter(new FileWriter(tableFile));
            writeToFile.write("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n");
            writeToFile.write("    A     B     C     D     E     F      G     H     I     J     K     L  \n");
            writeToFile.write(" _________________________________________________________________________\n");
            writeToFile.write("1|");
            //-------------------------------------------------------------------------------------------------
            if(A1.numberOfX!=0){
                if(A1.numberOfX >=10){
                    writeToFile.write(String.valueOf(A1.numberOfX)+"X  |");
                }
                else{
                    writeToFile.write(String.valueOf(A1.numberOfX)+"X   |");                    
                }
            }
            else if(A1.numberOfY != 0){
                if(A1.numberOfY >= 10){
                    writeToFile.write(String.valueOf(A1.numberOfY)+"Y  |");
                }
                else{
                    writeToFile.write(String.valueOf(A1.numberOfY)+"Y   |");                    
                }
            }
            else{
                writeToFile.write("     |");
            }
            //----------------------------------------------------------------------------------------------------
            if(B1.numberOfX!=0){
                if(B1.numberOfX >=10){
                    writeToFile.write(String.valueOf(B1.numberOfX)+"X  |");
                }
                else{
                    writeToFile.write(String.valueOf(B1.numberOfX)+"X   |");                    
                }
            }
            else if(B1.numberOfY != 0){
                if(B1.numberOfY >= 10){
                    writeToFile.write(String.valueOf(B1.numberOfY)+"Y  |");
                }
                else{
                    writeToFile.write(String.valueOf(B1.numberOfY)+"Y   |");                    
                }
            }
            else{
                writeToFile.write("     |");
            }            
            //----------------------------------------------------------------------------------------------------
            if(C1.numberOfX!=0){
                if(C1.numberOfX >=10){
                    writeToFile.write(String.valueOf(C1.numberOfX)+"X  |");
                }
                else{
                    writeToFile.write(String.valueOf(C1.numberOfX)+"X   |");                    
                }
            }
            else if(C1.numberOfY != 0){
                if(C1.numberOfY >= 10){
                    writeToFile.write(String.valueOf(C1.numberOfY)+"Y  |");
                }
                else{
                    writeToFile.write(String.valueOf(C1.numberOfY)+"Y   |");                    
                }
            }
            else{
                writeToFile.write("     |");
            }            
            //----------------------------------------------------------------------------------------------------
            if(D1.numberOfX!=0){
                if(D1.numberOfX >=10){
                    writeToFile.write(String.valueOf(D1.numberOfX)+"X  |");
                }
                else{
                    writeToFile.write(String.valueOf(D1.numberOfX)+"X   |");                    
                }
            }
            else if(D1.numberOfY != 0){
                if(D1.numberOfY >= 10){
                    writeToFile.write(String.valueOf(D1.numberOfY)+"Y  |");
                }
                else{
                    writeToFile.write(String.valueOf(D1.numberOfY)+"Y   |");                    
                }
            }
            else{
                writeToFile.write("     |");
            }            
            //----------------------------------------------------------------------------------------------------
            if(E1.numberOfX!=0){
                if(E1.numberOfX >=10){
                    writeToFile.write(String.valueOf(E1.numberOfX)+"X  |");
                }
                else{
                    writeToFile.write(String.valueOf(E1.numberOfX)+"X   |");                    
                }
            }
            else if(E1.numberOfY != 0){
                if(E1.numberOfY >= 10){
                    writeToFile.write(String.valueOf(E1.numberOfY)+"Y  |");
                }
                else{
                    writeToFile.write(String.valueOf(E1.numberOfY)+"Y   |");                    
                }
            }
            else{
                writeToFile.write("     |");
            }            
            //----------------------------------------------------------------------------------------------------
            if(F1.numberOfX!=0){
                if(F1.numberOfX >=10){
                    writeToFile.write(String.valueOf(F1.numberOfX)+"X  ++");
                }
                else{
                    writeToFile.write(String.valueOf(F1.numberOfX)+"X   ++");                    
                }
            }
            else if(F1.numberOfY != 0){
                if(F1.numberOfY >= 10){
                    writeToFile.write(String.valueOf(F1.numberOfY)+"Y  ++");
                }
                else{
                    writeToFile.write(String.valueOf(F1.numberOfY)+"Y   ++");                    
                }
            }
            else{
                writeToFile.write("     ++");
            }  
            //----------------------------------------------------------------------------------------------------
            if(G1.numberOfX!=0){
                if(G1.numberOfX >=10){
                    writeToFile.write(String.valueOf(G1.numberOfX)+"X  |");
                }
                else{
                    writeToFile.write(String.valueOf(G1.numberOfX)+"X   |");                    
                }
            }
            else if(G1.numberOfY != 0){
                if(G1.numberOfY >= 10){
                    writeToFile.write(String.valueOf(G1.numberOfY)+"Y  |");
                }
                else{
                    writeToFile.write(String.valueOf(G1.numberOfY)+"Y   |");                    
                }
            }
            else{
                writeToFile.write("     |");
            }            
            //----------------------------------------------------------------------------------------------------        
            if(H1.numberOfX!=0){
                if(H1.numberOfX >=10){
                    writeToFile.write(String.valueOf(H1.numberOfX)+"X  |");
                }
                else{
                    writeToFile.write(String.valueOf(H1.numberOfX)+"X   |");                    
                }
            }
            else if(H1.numberOfY != 0){
                if(H1.numberOfY >= 10){
                    writeToFile.write(String.valueOf(H1.numberOfY)+"Y  |");
                }
                else{
                    writeToFile.write(String.valueOf(H1.numberOfY)+"Y   |");                    
                }
            }
            else{
                writeToFile.write("     |");
            }            
            //----------------------------------------------------------------------------------------------------   
            if(I1.numberOfX!=0){
                if(I1.numberOfX >=10){
                    writeToFile.write(String.valueOf(I1.numberOfX)+"X  |");
                }
                else{
                    writeToFile.write(String.valueOf(I1.numberOfX)+"X   |");                    
                }
            }
            else if(I1.numberOfY != 0){
                if(I1.numberOfY >= 10){
                    writeToFile.write(String.valueOf(I1.numberOfY)+"Y  |");
                }
                else{
                    writeToFile.write(String.valueOf(I1.numberOfY)+"Y   |");                    
                }
            }
            else{
                writeToFile.write("     |");
            }            
            //----------------------------------------------------------------------------------------------------
            if(J1.numberOfX!=0){
                if(J1.numberOfX >=10){
                    writeToFile.write(String.valueOf(J1.numberOfX)+"X  |");
                }
                else{
                    writeToFile.write(String.valueOf(J1.numberOfX)+"X   |");                    
                }
            }
            else if(J1.numberOfY != 0){
                if(J1.numberOfY >= 10){
                    writeToFile.write(String.valueOf(J1.numberOfY)+"Y  |");
                }
                else{
                    writeToFile.write(String.valueOf(J1.numberOfY)+"Y   |");                    
                }
            }
            else{
                writeToFile.write("     |");
            }            
            //----------------------------------------------------------------------------------------------------
            if(K1.numberOfX!=0){
                if(K1.numberOfX >=10){
                    writeToFile.write(String.valueOf(K1.numberOfX)+"X  |");
                }
                else{
                    writeToFile.write(String.valueOf(K1.numberOfX)+"X   |");                    
                }
            }
            else if(K1.numberOfY != 0){
                if(K1.numberOfY >= 10){
                    writeToFile.write(String.valueOf(K1.numberOfY)+"Y  |");
                }
                else{
                    writeToFile.write(String.valueOf(K1.numberOfY)+"Y   |");                    
                }
            }
            else{
                writeToFile.write("     |");
            }            
            //----------------------------------------------------------------------------------------------------
            if(L1.numberOfX!=0){
                if(L1.numberOfX >=10){
                    writeToFile.write(String.valueOf(L1.numberOfX)+"X  |\n");
                }
                else{
                    writeToFile.write(String.valueOf(L1.numberOfX)+"X   |\n");                    
                }
            }
            else if(L1.numberOfY != 0){
                if(L1.numberOfY >= 10){
                    writeToFile.write(String.valueOf(L1.numberOfY)+"Y  |\n");
                }
                else{
                    writeToFile.write(String.valueOf(L1.numberOfY)+"Y   |\n");                    
                }
            }
            else{
                writeToFile.write("     |\n");
            }            
            //----------------------------------------------------------------------------------------------------
            writeToFile.write(" --------------------------------------------------------------------------\n");
            writeToFile.write("2|     |     |     |     |     |     ++     |     |     |     |     |     |\n");
            writeToFile.write(" --------------------------------------------------------------------------\n");
            //----------------------------------------------------------------------------------------------------
            writeToFile.write("3|     |     |     |     |");
            if(E3.numberOfX != 0){
                if(E3.numberOfX >= 10){
                    writeToFile.write(String.valueOf(E3.numberOfX)+"X  |");
                } 
                else{
                    writeToFile.write(String.valueOf(E3.numberOfX)+"X   |");
                    
                }
            }
            else {
                writeToFile.write("     |");
                
            }
            //------------------------------------------------------------------
            if(Dice.dice1 == 0 || Dice.dice2 == 0 ){
                writeToFile.write("     ++");
                writeToFile.write("     |");
            }
            else{
            writeToFile.write(Dice.dice1+"    ++");
            writeToFile.write(Dice.dice2+"    |");
            }
            //---------------------------------------------------------------
            if(H3.numberOfY != 0){
                if(H3.numberOfY >= 10){
                    writeToFile.write(String.valueOf(H3.numberOfY)+"Y  |");
                } 
                else{
                    writeToFile.write(String.valueOf(H3.numberOfY)+"Y   |");
                    
                }
            }
            else {
                writeToFile.write("     |");
                
            }
            //------------------------------------------------------------------
            writeToFile.write("     |     |     |     |\n");
            //------------------------------------------------------------------
            writeToFile.write(" --------------------------------------------------------------------------\n");   
            writeToFile.write("4|     |     |     |     |     |     ++     |     |     |     |     |     |\n");
            writeToFile.write(" --------------------------------------------------------------------------\n");   
            //-------------------------------------------------------------------------------------------------
            writeToFile.write("5|");
            //-------------------------------------------------------------------------------------------------
            if(A5.numberOfX!=0){
                if(A5.numberOfX >=10){
                    writeToFile.write(String.valueOf(A5.numberOfX)+"X  |");
                }
                else{
                    writeToFile.write(String.valueOf(A5.numberOfX)+"X   |");                    
                }
            }
            else if(A5.numberOfY != 0){
                if(A5.numberOfY >= 10){
                    writeToFile.write(String.valueOf(A5.numberOfY)+"Y  |");
                }
                else{
                    writeToFile.write(String.valueOf(A5.numberOfY)+"Y   |");                    
                }
            }
            else{
                writeToFile.write("     |");
            }
            //----------------------------------------------------------------------------------------------------
            if(B5.numberOfX!=0){
                if(B5.numberOfX >=10){
                    writeToFile.write(String.valueOf(B5.numberOfX)+"X  |");
                }
                else{
                    writeToFile.write(String.valueOf(B5.numberOfX)+"X   |");                    
                }
            }
            else if(B5.numberOfY != 0){
                if(B5.numberOfY >= 10){
                    writeToFile.write(String.valueOf(B5.numberOfY)+"Y  |");
                }
                else{
                    writeToFile.write(String.valueOf(B5.numberOfY)+"Y   |");                    
                }
            }
            else{
                writeToFile.write("     |");
            }            
            //----------------------------------------------------------------------------------------------------
            if(C5.numberOfX!=0){
                if(C5.numberOfX >=10){
                    writeToFile.write(String.valueOf(C5.numberOfX)+"X  |");
                }
                else{
                    writeToFile.write(String.valueOf(C5.numberOfX)+"X   |");                    
                }
            }
            else if(C5.numberOfY != 0){
                if(C5.numberOfY >= 10){
                    writeToFile.write(String.valueOf(C5.numberOfY)+"Y  |");
                }
                else{
                    writeToFile.write(String.valueOf(C5.numberOfY)+"Y   |");                    
                }
            }
            else{
                writeToFile.write("     |");
            }            
            //----------------------------------------------------------------------------------------------------
            if(D5.numberOfX!=0){
                if(D5.numberOfX >=10){
                    writeToFile.write(String.valueOf(D5.numberOfX)+"X  |");
                }
                else{
                    writeToFile.write(String.valueOf(D5.numberOfX)+"X   |");                    
                }
            }
            else if(D5.numberOfY != 0){
                if(D5.numberOfY >= 10){
                    writeToFile.write(String.valueOf(D5.numberOfY)+"Y  |");
                }
                else{
                    writeToFile.write(String.valueOf(D5.numberOfY)+"Y   |");                    
                }
            }
            else{
                writeToFile.write("     |");
            }            
            //----------------------------------------------------------------------------------------------------
            if(E5.numberOfX!=0){
                if(E5.numberOfX >=10){
                    writeToFile.write(String.valueOf(E5.numberOfX)+"X  |");
                }
                else{
                    writeToFile.write(String.valueOf(E5.numberOfX)+"X   |");                    
                }
            }
            else if(E5.numberOfY != 0){
                if(E5.numberOfY >= 10){
                    writeToFile.write(String.valueOf(E5.numberOfY)+"Y  |");
                }
                else{
                    writeToFile.write(String.valueOf(E5.numberOfY)+"Y   |");                    
                }
            }
            else{
                writeToFile.write("     |");
            }            
            //----------------------------------------------------------------------------------------------------
            if(F5.numberOfX!=0){
                if(F5.numberOfX >=10){
                    writeToFile.write(String.valueOf(F5.numberOfX)+"X  ++");
                }
                else{
                    writeToFile.write(String.valueOf(F5.numberOfX)+"X   ++");                    
                }
            }
            else if(F5.numberOfY != 0){
                if(F5.numberOfY >= 10){
                    writeToFile.write(String.valueOf(F5.numberOfY)+"Y  ++");
                }
                else{
                    writeToFile.write(String.valueOf(F5.numberOfY)+"Y   ++");                    
                }
            }
            else{
                writeToFile.write("     ++");
            }  
            //----------------------------------------------------------------------------------------------------
            if(G5.numberOfX!=0){
                if(G5.numberOfX >=10){
                    writeToFile.write(String.valueOf(G5.numberOfX)+"X  |");
                }
                else{
                    writeToFile.write(String.valueOf(G5.numberOfX)+"X   |");                    
                }
            }
            else if(G5.numberOfY != 0){
                if(G5.numberOfY >= 10){
                    writeToFile.write(String.valueOf(G5.numberOfY)+"Y  |");
                }
                else{
                    writeToFile.write(String.valueOf(G5.numberOfY)+"Y   |");                    
                }
            }
            else{
                writeToFile.write("     |");
            }            
            //----------------------------------------------------------------------------------------------------        
            if(H5.numberOfX!=0){
                if(H5.numberOfX >=10){
                    writeToFile.write(String.valueOf(H5.numberOfX)+"X  |");
                }
                else{
                    writeToFile.write(String.valueOf(H5.numberOfX)+"X   |");                    
                }
            }
            else if(H5.numberOfY != 0){
                if(H5.numberOfY >= 10){
                    writeToFile.write(String.valueOf(H5.numberOfY)+"Y  |");
                }
                else{
                    writeToFile.write(String.valueOf(H5.numberOfY)+"Y   |");                    
                }
            }
            else{
                writeToFile.write("     |");
            }            
            //----------------------------------------------------------------------------------------------------   
            if(I5.numberOfX!=0){
                if(I5.numberOfX >=10){
                    writeToFile.write(String.valueOf(I5.numberOfX)+"X  |");
                }
                else{
                    writeToFile.write(String.valueOf(I5.numberOfX)+"X   |");                    
                }
            }
            else if(I5.numberOfY != 0){
                if(I5.numberOfY >= 10){
                    writeToFile.write(String.valueOf(I5.numberOfY)+"Y  |");
                }
                else{
                    writeToFile.write(String.valueOf(I5.numberOfY)+"Y   |");                    
                }
            }
            else{
                writeToFile.write("     |");
            }            
            //----------------------------------------------------------------------------------------------------
            if(J5.numberOfX!=0){
                if(J5.numberOfX >=10){
                    writeToFile.write(String.valueOf(J5.numberOfX)+"X  |");
                }
                else{
                    writeToFile.write(String.valueOf(J5.numberOfX)+"X   |");                    
                }
            }
            else if(J5.numberOfY != 0){
                if(J5.numberOfY >= 10){
                    writeToFile.write(String.valueOf(J5.numberOfY)+"Y  |");
                }
                else{
                    writeToFile.write(String.valueOf(J5.numberOfY)+"Y   |");                    
                }
            }
            else{
                writeToFile.write("     |");
            }            
            //----------------------------------------------------------------------------------------------------
            if(K5.numberOfX!=0){
                if(K5.numberOfX >=10){
                    writeToFile.write(String.valueOf(K5.numberOfX)+"X  |");
                }
                else{
                    writeToFile.write(String.valueOf(K5.numberOfX)+"X   |");                    
                }
            }
            else if(K5.numberOfY != 0){
                if(K5.numberOfY >= 10){
                    writeToFile.write(String.valueOf(K5.numberOfY)+"Y  |");
                }
                else{
                    writeToFile.write(String.valueOf(K5.numberOfY)+"Y   |");                    
                }
            }
            else{
                writeToFile.write("     |");
            }            
            //----------------------------------------------------------------------------------------------------
            if(L5.numberOfX!=0){
                if(L5.numberOfX >=10){
                    writeToFile.write(String.valueOf(L5.numberOfX)+"X  |\n");
                }
                else{
                    writeToFile.write(String.valueOf(L5.numberOfX)+"X   |\n");                    
                }
            }
            else if(L5.numberOfY != 0){
                if(L5.numberOfY >= 10){
                    writeToFile.write(String.valueOf(L5.numberOfY)+"Y  |\n");
                }
                else{
                    writeToFile.write(String.valueOf(L5.numberOfY)+"Y   |\n");                    
                }
            }
            else{
                writeToFile.write("     |\n");
            }   
            //-----------------------------------------------------------------
            writeToFile.write("__________________________________________________________________________\n");
            writeToFile.write("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++ \n");
            writeToFile.close();
            
        }catch(FileNotFoundException e1){
            System.out.println("Dosya yolu hatali.");
        }catch(IOException e2){
            System.out.println("Dosya yolu hatali degil ama yazma isleminde hata oldu.");
        }catch(Exception e3){
            System.out.println("Bir sey oldu.");
        }
    }
    
    static void printVersus(){
        File logFile = null ; 
        BufferedWriter writeToFile = null ; 
        try{
            logFile = new File("src/file_organization_project/Files/Log.dat");
            writeToFile = new BufferedWriter(new FileWriter(logFile,true));
            writeToFile.write("X "+String.valueOf(Dice.dice1)+"\n");
            writeToFile.write("Y "+String.valueOf(Dice.dice2)+"\n");
            
            writeToFile.close();
            
        }catch(FileNotFoundException e1){
            
        }catch(IOException e2){
            
        }catch(Exception e3){
            
        }
    }
    
    static void dicePrint(){
       File logFile = null ; 
        BufferedWriter writeToFile = null ; 
        try{
            logFile = new File("src/file_organization_project/Files/Log.dat");
            writeToFile = new BufferedWriter(new FileWriter(logFile,true));
            if(Board.user_info=='x'){
                writeToFile.write("-X ");
            }
            else{
                writeToFile.write("-Y ");
            }
            writeToFile.write(String.valueOf(Dice.dice1)+" "+String.valueOf(Dice.dice2)+"\n");
            writeToFile.close();
            
        }catch(FileNotFoundException e1){
            
        }catch(IOException e2){
            
        }catch(Exception e3){
            
        }        
    }
    
    static void hamlePrint(String from , String to){
        File logFile = null ; 
        BufferedWriter writeToFile = null ; 
        try{
            logFile = new File("src/file_organization_project/Files/Log.dat");
            writeToFile = new BufferedWriter(new FileWriter(logFile,true));
            if(Board.user_info=='x'){
                writeToFile.write("X;");
            }
            else{
                writeToFile.write("Y;");                
            }
            writeToFile.write(from+"->"+to+"\n");
            writeToFile.close();
            
        }catch(FileNotFoundException e1){
            
        }catch(IOException e2){
            
        }catch(Exception e3){
            
        }
    }
    
    static void checkResumeGame(){
        File checkFile = null ; 
        BufferedReader readFromFile = null ; 
        File tableFile = null ; 
        BufferedReader readFromFile2 = null ; 
        String keeper = null  ; 
        try{
            checkFile = new File("src/file_organization_project/Files/Check.dat");
            tableFile = new File("src/file_organization_project/Files/Table.dat");
            readFromFile = new BufferedReader(new FileReader(checkFile));
            if(readFromFile.readLine().charAt(0)=='u'){
                readFromFile2 = new BufferedReader(new FileReader(tableFile));
                keeper = readFromFile2.readLine();
                if(keeper == null){
                    possResumeGame = false ;
                    
                }
                else{
                    possResumeGame = true ; 
                }
                readFromFile2.close();
            }
            else{
                possResumeGame = false ; 
            }
            readFromFile.close();
        }catch(FileNotFoundException e1){
            
        }catch(NullPointerException e2){
            
        }catch(Exception e3){
            
        }
        
    }
    
    static void resetFiles(){
        File logFile = null ; 
        File tableFile = null ;
        BufferedWriter writeToLog = null ; 
        BufferedWriter writeToTable = null ; 
        try{
            logFile = new File("src/file_organization_project/Files/Log.dat");
            tableFile = new File("src/file_organization_project/Files/Table.dat");            
            writeToLog = new BufferedWriter(new FileWriter(logFile));
            writeToTable = new BufferedWriter(new FileWriter(tableFile));
            writeToLog.write("");
            writeToTable.write("");
            writeToLog.close();
            writeToTable.close();

        }catch(FileNotFoundException e1){
            
        }catch(IOException e2){
            
        }catch(Exception e3){
            
        }
                
    }
    
    static void makeFinished(boolean check){
        // check -> true  it means make finished otherwise make unfinished.
        File checkFile = null ; 
        BufferedWriter writeToCheck = null ; 
        try{
            checkFile = new File("src/file_organization_project/Files/Check.dat");
            writeToCheck = new BufferedWriter(new FileWriter(checkFile));
            if(check){
                writeToCheck.write("finished");
            }
            else{
                writeToCheck.write("unfinished");
            }
            writeToCheck.close();
            
        }catch(FileNotFoundException e1){
            
        }catch(IOException e2){
            
        }catch(Exception e3){
            
        }
    }
    
    static void getInfoFromTable(){
        File tableFile = null ; 
        String keeper = null ; 
        StringTokenizer token = null  ; 
        String temp, temp2 = null ; 
        String tempElement = null ; 
        
        BufferedReader readFromFile = null ; 
        try{
            tableFile = new File("src/file_organization_project/Files/Table.dat");
            readFromFile = new BufferedReader(new FileReader(tableFile));
            while((keeper=readFromFile.readLine())!=null){
                if(keeper.charAt(0)=='+'){
                    keeper=readFromFile.readLine();  keeper=readFromFile.readLine(); keeper=readFromFile.readLine();
                    token = new StringTokenizer(keeper,"++");
                    temp = token.nextToken();
                    temp2 = token.nextToken();
                    token = new StringTokenizer(temp,"|"); token.nextToken();
                    tempElement = token.nextToken() ; 
                    //---------------------------------------------------------------------------------
                    if(tempElement.charAt(0)==' '){
                        A1.numberOfX  = 0 ; A1.numberOfY = 0 ; 
                    }
                    else if(tempElement.charAt(2)==' '){
                        if(tempElement.charAt(1)=='X'){
                            A1.numberOfX = Integer.valueOf(tempElement.charAt(0)+"");
                            A1.numberOfY = 0 ; 
                        }
                        else{
                            A1.numberOfY = Integer.valueOf(tempElement.charAt(0)+"");
                            A1.numberOfX = 0 ; 
                        }
                    }
                    else{
                        if(tempElement.charAt(2)=='X'){
                            A1.numberOfX = Integer.valueOf(tempElement.charAt(0)+tempElement.charAt(1)+"");
                            A1.numberOfY = 0 ; 
                        }
                        else{
                            A1.numberOfY = Integer.valueOf(tempElement.charAt(0)+tempElement.charAt(1)+"");   
                            A1.numberOfX = 0 ; 
                        }
                    }
                    
                    //----------------------------------------------------------------------------------------
                    tempElement = token.nextToken();
                    //---------------------------------------------------------------------------------
                    if(tempElement.charAt(0)==' '){
                        B1.numberOfX  = 0 ; B1.numberOfY = 0 ; 
                    }
                    else if(tempElement.charAt(2)==' '){
                        if(tempElement.charAt(1)=='X'){
                            B1.numberOfX = Integer.valueOf(tempElement.charAt(0)+"");
                            B1.numberOfY = 0 ; 
                        }
                        else{
                            B1.numberOfY = Integer.valueOf(tempElement.charAt(0)+"");
                            B1.numberOfX = 0 ; 
                        }
                    }
                    else{
                        if(tempElement.charAt(2)=='X'){
                            B1.numberOfX = Integer.valueOf(tempElement.charAt(0)+tempElement.charAt(1)+"");
                            B1.numberOfY = 0 ; 
                        }
                        else{
                            B1.numberOfY = Integer.valueOf(tempElement.charAt(0)+tempElement.charAt(1)+"");     
                            B1.numberOfX = 0 ; 
                        }
                    }
                    
                    //----------------------------------------------------------------------------------------
                    tempElement = token.nextToken();
                    //---------------------------------------------------------------------------------
                    if(tempElement.charAt(0)==' '){
                        C1.numberOfX  = 0 ; C1.numberOfY = 0 ; 
                    }
                    else if(tempElement.charAt(2)==' '){
                        if(tempElement.charAt(1)=='X'){
                            C1.numberOfX = Integer.valueOf(tempElement.charAt(0)+"");
                            C1.numberOfY = 0 ; 
                        }
                        else{
                            C1.numberOfY = Integer.valueOf(tempElement.charAt(0)+"");
                            C1.numberOfX = 0 ; 
                        }
                    }
                    else{
                        if(tempElement.charAt(2)=='X'){
                            C1.numberOfX = Integer.valueOf(tempElement.charAt(0)+tempElement.charAt(1)+"");
                            C1.numberOfY = 0 ; 
                        }
                        else{
                            C1.numberOfY = Integer.valueOf(tempElement.charAt(0)+tempElement.charAt(1)+"");     
                            C1.numberOfX = 0 ; 
                        }
                    }
                    
                    //----------------------------------------------------------------------------------------
                     tempElement = token.nextToken();
                    //---------------------------------------------------------------------------------
                    if(tempElement.charAt(0)==' '){
                        D1.numberOfX  = 0 ; D1.numberOfY = 0 ; 
                    }
                    else if(tempElement.charAt(2)==' '){
                        if(tempElement.charAt(1)=='X'){
                            D1.numberOfX = Integer.valueOf(tempElement.charAt(0)+"");
                            D1.numberOfY = 0 ; 
                        }
                        else{
                            D1.numberOfY = Integer.valueOf(tempElement.charAt(0)+"");
                            D1.numberOfX = 0 ; 
                        }
                    }
                    else{
                        if(tempElement.charAt(2)=='X'){
                            D1.numberOfX = Integer.valueOf(tempElement.charAt(0)+tempElement.charAt(1)+"");
                            D1.numberOfY = 0 ; 
                        }
                        else{
                            D1.numberOfY = Integer.valueOf(tempElement.charAt(0)+tempElement.charAt(1)+"");      
                            D1.numberOfX = 0 ; 
                        }
                    }
                    
                    //----------------------------------------------------------------------------------------
                    tempElement = token.nextToken();
                    //---------------------------------------------------------------------------------
                    if(tempElement.charAt(0)==' '){
                        E1.numberOfX  = 0 ; E1.numberOfY = 0 ; 
                    }
                    else if(tempElement.charAt(2)==' '){
                        if(tempElement.charAt(1)=='X'){
                            E1.numberOfX = Integer.valueOf(tempElement.charAt(0)+"");
                            E1.numberOfY = 0 ; 
                        }
                        else{
                            E1.numberOfY = Integer.valueOf(tempElement.charAt(0)+"");
                            E1.numberOfX = 0 ; 
                        }
                    }
                    else{
                        if(tempElement.charAt(2)=='X'){
                            E1.numberOfX = Integer.valueOf(tempElement.charAt(0)+tempElement.charAt(1)+"");
                            E1.numberOfY = 0  ;
                        }
                        else{
                            E1.numberOfY = Integer.valueOf(tempElement.charAt(0)+tempElement.charAt(1)+"");   
                            E1.numberOfX = 0 ; 
                        }
                    }
                    
                    //----------------------------------------------------------------------------------------
                    tempElement = token.nextToken();
                    //---------------------------------------------------------------------------------
                    if(tempElement.charAt(0)==' '){
                        F1.numberOfX  = 0 ; F1.numberOfY = 0 ; 
                    }
                    else if(tempElement.charAt(2)==' '){
                        if(tempElement.charAt(1)=='X'){
                            F1.numberOfX = Integer.valueOf(tempElement.charAt(0)+"");
                            F1.numberOfY  =0 ; 
                        }
                        else{
                            F1.numberOfY = Integer.valueOf(tempElement.charAt(0)+"");
                            F1.numberOfX = 0 ;
                        }
                    }
                    else{
                        if(tempElement.charAt(2)=='X'){
                            F1.numberOfX = Integer.valueOf(tempElement.charAt(0)+tempElement.charAt(1)+"");
                            F1.numberOfY  = 0; 
                        }
                        else{
                            F1.numberOfY = Integer.valueOf(tempElement.charAt(0)+tempElement.charAt(1)+"");   
                            F1.numberOfX = 0  ;
                        }
                    }
                    
                    //----------------------------------------------------------------------------------------
                    token = new StringTokenizer(temp2,"|");
                    tempElement = token.nextToken();
                    //---------------------------------------------------------------------------------
                    if(tempElement.charAt(0)==' '){
                        G1.numberOfX  = 0 ; G1.numberOfY = 0 ; 
                    }
                    else if(tempElement.charAt(2)==' '){
                        if(tempElement.charAt(1)=='X'){
                            G1.numberOfX = Integer.valueOf(tempElement.charAt(0)+"");
                            G1.numberOfY = 0 ; 
                        }
                        else{
                            G1.numberOfY = Integer.valueOf(tempElement.charAt(0)+"");
                            G1.numberOfX = 0 ; 
                        }
                    }
                    else{
                        if(tempElement.charAt(2)=='X'){
                            G1.numberOfX = Integer.valueOf(tempElement.charAt(0)+tempElement.charAt(1)+"");
                            G1.numberOfY = 0 ; 
                        }
                        else{
                            G1.numberOfY = Integer.valueOf(tempElement.charAt(0)+tempElement.charAt(1)+"");     
                            G1.numberOfX = 0 ; 
                        }
                    }
                    
                    //----------------------------------------------------------------------------------------
                     tempElement = token.nextToken();
                    //---------------------------------------------------------------------------------
                    if(tempElement.charAt(0)==' '){
                        H1.numberOfX  = 0 ; H1.numberOfY = 0 ; 
                    }
                    else if(tempElement.charAt(2)==' '){
                        if(tempElement.charAt(1)=='X'){
                            H1.numberOfX = Integer.valueOf(tempElement.charAt(0)+"");
                            H1.numberOfY = 0 ; 
                        }
                        else{
                            H1.numberOfY = Integer.valueOf(tempElement.charAt(0)+"");
                            H1.numberOfX = 0 ; 
                        }
                    }
                    else{
                        if(tempElement.charAt(2)=='X'){
                            H1.numberOfX = Integer.valueOf(tempElement.charAt(0)+tempElement.charAt(1)+"");
                            H1.numberOfY = 0 ;
                        }
                        else{
                            H1.numberOfY = Integer.valueOf(tempElement.charAt(0)+tempElement.charAt(1)+"");       
                            H1.numberOfX = 0 ; 
                        }
                    }
                    
                    //----------------------------------------------------------------------------------------
                    tempElement = token.nextToken();
                    //---------------------------------------------------------------------------------
                    if(tempElement.charAt(0)==' '){
                        I1.numberOfX  = 0 ; I1.numberOfY = 0 ; 
                    }
                    else if(tempElement.charAt(2)==' '){
                        if(tempElement.charAt(1)=='X'){
                            I1.numberOfX = Integer.valueOf(tempElement.charAt(0)+"");
                            I1.numberOfY = 0 ; 
                        }
                        else{
                            I1.numberOfY = Integer.valueOf(tempElement.charAt(0)+"");
                            I1.numberOfX = 0 ; 
                        }
                    }
                    else{
                        if(tempElement.charAt(2)=='X'){
                            I1.numberOfX = Integer.valueOf(tempElement.charAt(0)+tempElement.charAt(1)+"");
                            I1.numberOfY = 0 ; 
                        }
                        else{
                            I1.numberOfY = Integer.valueOf(tempElement.charAt(0)+tempElement.charAt(1)+"");    
                            I1.numberOfX = 0 ;
                        }
                    }
                    
                    //----------------------------------------------------------------------------------------
                    tempElement = token.nextToken();
                    //---------------------------------------------------------------------------------
                    if(tempElement.charAt(0)==' '){
                        J1.numberOfX  = 0 ; J1.numberOfY = 0 ; 
                    }
                    else if(tempElement.charAt(2)==' '){
                        if(tempElement.charAt(1)=='X'){
                            J1.numberOfX = Integer.valueOf(tempElement.charAt(0)+"");
                            J1.numberOfY = 0 ;
                        }
                        else{
                            J1.numberOfY = Integer.valueOf(tempElement.charAt(0)+"");
                            J1.numberOfX = 0 ;
                        }
                    }
                    else{
                        if(tempElement.charAt(2)=='X'){
                            J1.numberOfX = Integer.valueOf(tempElement.charAt(0)+tempElement.charAt(1)+"");
                            J1.numberOfY = 0 ; 
                        }
                        else{
                            J1.numberOfY = Integer.valueOf(tempElement.charAt(0)+tempElement.charAt(1)+"");    
                            J1.numberOfX = 0; 
                        }
                    }
                    
                    //----------------------------------------------------------------------------------------
                    tempElement = token.nextToken();
                    //---------------------------------------------------------------------------------
                    if(tempElement.charAt(0)==' '){
                        K1.numberOfX  = 0 ; K1.numberOfY = 0 ; 
                    }
                    else if(tempElement.charAt(2)==' '){
                        if(tempElement.charAt(1)=='X'){
                            K1.numberOfX = Integer.valueOf(tempElement.charAt(0)+"");
                            K1.numberOfY = 0 ; 
                        }
                        else{
                            K1.numberOfY = Integer.valueOf(tempElement.charAt(0)+"");
                            K1.numberOfX = 0  ;
                        }
                    }
                    else{
                        if(tempElement.charAt(2)=='X'){
                            K1.numberOfX = Integer.valueOf(tempElement.charAt(0)+tempElement.charAt(1)+"");
                            K1.numberOfY = 0  ;
                        }
                        else{
                            K1.numberOfY = Integer.valueOf(tempElement.charAt(0)+tempElement.charAt(1)+"");     
                            K1.numberOfX = 0 ; 
                        }
                    }
                    
                    //----------------------------------------------------------------------------------------
                    tempElement = token.nextToken();
                    //---------------------------------------------------------------------------------
                    if(tempElement.charAt(0)==' '){
                        L1.numberOfX  = 0 ; L1.numberOfY = 0 ; 
                    }
                    else if(tempElement.charAt(2)==' '){
                        if(tempElement.charAt(1)=='X'){
                            L1.numberOfX = Integer.valueOf(tempElement.charAt(0)+"");
                            L1.numberOfY = 0 ; 
                        }
                        else{
                            L1.numberOfY = Integer.valueOf(tempElement.charAt(0)+"");
                            L1.numberOfX = 0 ; 
                        }
                    }
                    else{
                        if(tempElement.charAt(2)=='X'){
                            L1.numberOfX = Integer.valueOf(tempElement.charAt(0)+tempElement.charAt(1)+"");
                            L1.numberOfY = 0  ; 
                        }
                        else{
                            L1.numberOfY = Integer.valueOf(tempElement.charAt(0)+tempElement.charAt(1)+"");       
                            L1.numberOfX = 0 ; 
                        }
                    }
                    
            
                    //----------------------------------------------------------------------------------------
                    keeper=readFromFile.readLine(); keeper=readFromFile.readLine(); keeper=readFromFile.readLine(); keeper=readFromFile.readLine();
                    
                    token = new StringTokenizer(keeper,"++");
                    temp = token.nextToken();
                    temp2 = token.nextToken();
                    token = new StringTokenizer(temp,"|");token.nextToken();
                    token.nextToken();token.nextToken();token.nextToken();token.nextToken();
                    tempElement = token.nextToken();
                    //---------------------------------------------------------------------------------
                    if(tempElement.charAt(0)==' '){
                        E3.numberOfX  = 0 ;  
                    }
                    else if(tempElement.charAt(2)==' '){
                        
                        E3.numberOfX = Integer.valueOf(tempElement.charAt(0)+"");
                        
                        
                    }
                    else{
                        E3.numberOfX = Integer.valueOf(tempElement.charAt(0)+tempElement.charAt(1)+"");
                    }
                    
            
                    //----------------------------------------------------------------------------------------
                     token = new StringTokenizer(temp2,"|");token.nextToken();
                     tempElement = token.nextToken();                    
                    //---------------------------------------------------------------------------------
                    if(tempElement.charAt(0)==' '){
                        H3.numberOfY  = 0 ;  
                    }
                    else if(tempElement.charAt(2)==' '){
                        
                        H3.numberOfY = Integer.valueOf(tempElement.charAt(0)+"");
                        
                        
                    }
                    else{
                        H3.numberOfY = Integer.valueOf(tempElement.charAt(0)+tempElement.charAt(1)+"");
                    }
                    
            
                    //----------------------------------------------------------------------------------------
                    keeper=readFromFile.readLine();keeper=readFromFile.readLine();keeper=readFromFile.readLine();keeper =readFromFile.readLine();
                    token = new StringTokenizer(keeper,"++");
                    temp = token.nextToken();
                    temp2 = token.nextToken();
                    token = new StringTokenizer(temp,"|"); token.nextToken();
                    tempElement = token.nextToken() ; 
                    //---------------------------------------------------------------------------------
                    if(tempElement.charAt(0)==' '){
                        A5.numberOfX  = 0 ; A5.numberOfY = 0 ; 
                    }
                    else if(tempElement.charAt(2)==' '){
                        if(tempElement.charAt(1)=='X'){
                            A5.numberOfX = Integer.valueOf(tempElement.charAt(0)+"");
                            A5.numberOfY = 0  ;
                        }
                        else{
                            A5.numberOfY = Integer.valueOf(tempElement.charAt(0)+"");
                            A5.numberOfX = 0 ; 
                        }
                    }
                    else{
                        if(tempElement.charAt(2)=='X'){
                            A5.numberOfX = Integer.valueOf(tempElement.charAt(0)+tempElement.charAt(1)+"");
                            A5.numberOfY = 0 ; 
                        }
                        else{
                            A5.numberOfY = Integer.valueOf(tempElement.charAt(0)+tempElement.charAt(1)+"");     
                            A5.numberOfX = 0  ;
                        }
                    }
                    
                    //----------------------------------------------------------------------------------------
                    tempElement = token.nextToken();
                    //---------------------------------------------------------------------------------
                    if(tempElement.charAt(0)==' '){
                        B5.numberOfX  = 0 ; B5.numberOfY = 0 ; 
                    }
                    else if(tempElement.charAt(2)==' '){
                        if(tempElement.charAt(1)=='X'){
                            B5.numberOfX = Integer.valueOf(tempElement.charAt(0)+"");
                            B5.numberOfY = 0 ; 
                        }
                        else{
                            B5.numberOfY = Integer.valueOf(tempElement.charAt(0)+"");
                            B5.numberOfX = 0 ;
                        }
                    }
                    else{
                        if(tempElement.charAt(2)=='X'){
                            B5.numberOfX = Integer.valueOf(tempElement.charAt(0)+tempElement.charAt(1)+"");
                            B5.numberOfY  = 0 ; 
                        }
                        else{
                            B5.numberOfY = Integer.valueOf(tempElement.charAt(0)+tempElement.charAt(1)+"");      
                            B5.numberOfX = 0 ; 
                        }
                    }
                    
                    //----------------------------------------------------------------------------------------
                    tempElement = token.nextToken();
                    //---------------------------------------------------------------------------------
                    if(tempElement.charAt(0)==' '){
                        C5.numberOfX  = 0 ; C5.numberOfY = 0 ; 
                    }
                    else if(tempElement.charAt(2)==' '){
                        if(tempElement.charAt(1)=='X'){
                            C5.numberOfX = Integer.valueOf(tempElement.charAt(0)+"");
                            C5.numberOfY = 0 ; 
                        }
                        else{
                            C5.numberOfY = Integer.valueOf(tempElement.charAt(0)+"");
                            C5.numberOfX = 0 ; 
                        }
                    }
                    else{
                        if(tempElement.charAt(2)=='X'){
                            C5.numberOfX = Integer.valueOf(tempElement.charAt(0)+tempElement.charAt(1)+"");
                            C5.numberOfY = 0 ; 
                        }
                        else{
                            C5.numberOfY = Integer.valueOf(tempElement.charAt(0)+tempElement.charAt(1)+""); 
                            C5.numberOfX = 0 ; 
                        }
                    }
                    
                    //----------------------------------------------------------------------------------------
                     tempElement = token.nextToken();
                    //---------------------------------------------------------------------------------
                    if(tempElement.charAt(0)==' '){
                        D5.numberOfX  = 0 ; D5.numberOfY = 0 ; 
                    }
                    else if(tempElement.charAt(2)==' '){
                        if(tempElement.charAt(1)=='X'){
                            D5.numberOfX = Integer.valueOf(tempElement.charAt(0)+"");
                            D5.numberOfY = 0 ; 
                        }
                        else{
                            D5.numberOfY = Integer.valueOf(tempElement.charAt(0)+"");
                            D5.numberOfX =  0 ; 
                        }
                    }
                    else{
                        if(tempElement.charAt(2)=='X'){
                            D5.numberOfX = Integer.valueOf(tempElement.charAt(0)+tempElement.charAt(1)+"");
                            D5.numberOfY = 0 ; 
                        }
                        else{
                            D5.numberOfY = Integer.valueOf(tempElement.charAt(0)+tempElement.charAt(1)+"");      
                            D5.numberOfX = 0 ; 
                        }
                    }
                    
                    //----------------------------------------------------------------------------------------
                    tempElement = token.nextToken();
                    //---------------------------------------------------------------------------------
                    if(tempElement.charAt(0)==' '){
                        E5.numberOfX  = 0 ; E5.numberOfY = 0 ; 
                    }
                    else if(tempElement.charAt(2)==' '){
                        if(tempElement.charAt(1)=='X'){
                            E5.numberOfX = Integer.valueOf(tempElement.charAt(0)+"");
                            E5.numberOfY = 0 ; 
                        }
                        else{
                            E5.numberOfY = Integer.valueOf(tempElement.charAt(0)+"");
                            E5.numberOfX = 0 ; 
                        }
                    }
                    else{
                        if(tempElement.charAt(2)=='X'){
                            E5.numberOfX = Integer.valueOf(tempElement.charAt(0)+tempElement.charAt(1)+"");
                            E5.numberOfY = 0 ; 
                        }
                        else{
                            E5.numberOfY = Integer.valueOf(tempElement.charAt(0)+tempElement.charAt(1)+"");  
                            E5.numberOfX = 0 ; 
                        }
                    }
                    
                    //----------------------------------------------------------------------------------------
                    tempElement = token.nextToken();
                    //---------------------------------------------------------------------------------
                    if(tempElement.charAt(0)==' '){
                        F5.numberOfX  = 0 ; F5.numberOfY = 0 ; 
                    }
                    else if(tempElement.charAt(2)==' '){
                        if(tempElement.charAt(1)=='X'){
                            F5.numberOfX = Integer.valueOf(tempElement.charAt(0)+"");
                            F5.numberOfY = 0 ; 
                        }
                        else{
                            F5.numberOfY = Integer.valueOf(tempElement.charAt(0)+"");
                            F5.numberOfX = 0 ; 
                        }
                    }
                    else{
                        if(tempElement.charAt(0)=='X'){
                            F5.numberOfX = Integer.valueOf(tempElement.charAt(0)+tempElement.charAt(1)+"");
                            F5.numberOfY = 0 ; 
                        }
                        else{
                            F5.numberOfY = Integer.valueOf(tempElement.charAt(0)+tempElement.charAt(1)+"");     
                            F5.numberOfX = 0 ; 
                        }
                    }
                    
                    //----------------------------------------------------------------------------------------
                    token = new StringTokenizer(temp2,"|"); 
                    tempElement = token.nextToken();
                    //---------------------------------------------------------------------------------
                    if(tempElement.charAt(0)==' '){
                        G5.numberOfX  = 0 ; G5.numberOfY = 0 ; 
                    }
                    else if(tempElement.charAt(2)==' '){
                        if(tempElement.charAt(1)=='X'){
                            G5.numberOfX = Integer.valueOf(tempElement.charAt(0)+"");
                            G5.numberOfY = 0 ; 
                        }
                        else{
                            G5.numberOfY = Integer.valueOf(tempElement.charAt(0)+"");
                            G5.numberOfX = 0 ; 
                        }
                    }
                    else{
                        if(tempElement.charAt(2)=='X'){
                            G5.numberOfX = Integer.valueOf(tempElement.charAt(0)+tempElement.charAt(1)+"");
                            G5.numberOfY = 0 ; 
                        }
                        else{
                            G5.numberOfY = Integer.valueOf(tempElement.charAt(0)+tempElement.charAt(1)+"");      
                            G5.numberOfX = 0 ; 
                        }
                    }
                    
                    //----------------------------------------------------------------------------------------
                     tempElement = token.nextToken();
                    //---------------------------------------------------------------------------------
                    if(tempElement.charAt(0)==' '){
                        H5.numberOfX  = 0 ; H5.numberOfY = 0 ; 
                    }
                    else if(tempElement.charAt(2)==' '){
                        if(tempElement.charAt(1)=='X'){
                            H5.numberOfX = Integer.valueOf(tempElement.charAt(0)+"");
                            H5.numberOfY = 0 ; 
                        }
                        else{
                            H5.numberOfY = Integer.valueOf(tempElement.charAt(0)+"");
                            H5.numberOfX = 0 ; 
                        }
                    }
                    else{
                        if(tempElement.charAt(2)=='X'){
                            H5.numberOfX = Integer.valueOf(tempElement.charAt(0)+tempElement.charAt(1)+"");
                            H5.numberOfY = 0 ; 
                        }
                        else{
                            H5.numberOfY = Integer.valueOf(tempElement.charAt(0)+tempElement.charAt(1)+"");      
                            H5.numberOfX = 0 ; 
                        }
                    }
                    
                    //----------------------------------------------------------------------------------------
                    tempElement = token.nextToken();
                    //---------------------------------------------------------------------------------
                    if(tempElement.charAt(0)==' '){
                        I5.numberOfX  = 0 ; I5.numberOfY = 0 ; 
                    }
                    else if(tempElement.charAt(2)==' '){
                        if(tempElement.charAt(1)=='X'){
                            I5.numberOfX = Integer.valueOf(tempElement.charAt(0)+"");
                            I5.numberOfY = 0  ; 
                        }
                        else{
                            I5.numberOfY = Integer.valueOf(tempElement.charAt(0)+"");
                            I5.numberOfX = 0 ; 
                        }
                    }
                    else{
                        if(tempElement.charAt(2)=='X'){
                            I5.numberOfX = Integer.valueOf(tempElement.charAt(0)+tempElement.charAt(1)+"");
                            I5.numberOfY = 0 ; 
                        }
                        else{
                            I5.numberOfY = Integer.valueOf(tempElement.charAt(0)+tempElement.charAt(1)+"");    
                            I5.numberOfX = 0 ; 
                        }
                    }
                    
                    //----------------------------------------------------------------------------------------
                    tempElement = token.nextToken();
                    //---------------------------------------------------------------------------------
                    if(tempElement.charAt(0)==' '){
                        J5.numberOfX  = 0 ; J5.numberOfY = 0 ; 
                    }
                    else if(tempElement.charAt(2)==' '){
                        if(tempElement.charAt(1)=='X'){
                            J5.numberOfX = Integer.valueOf(tempElement.charAt(0)+"");
                            J5.numberOfY = 0 ; 
                        }
                        else{
                            J5.numberOfY = Integer.valueOf(tempElement.charAt(0)+"");
                            J5.numberOfX = 0 ; 
                        }
                    }
                    else{
                        if(tempElement.charAt(2)=='X'){
                            J5.numberOfX = Integer.valueOf(tempElement.charAt(0)+tempElement.charAt(1)+"");
                            J5.numberOfY =  0 ; 
                        }
                        else{
                            J5.numberOfY = Integer.valueOf(tempElement.charAt(0)+tempElement.charAt(1)+"");   
                            J5.numberOfX = 0 ; 
                        }
                    }
                    
                    //----------------------------------------------------------------------------------------
                    tempElement = token.nextToken();
                    //---------------------------------------------------------------------------------
                    if(tempElement.charAt(0)==' '){
                        K5.numberOfX  = 0 ; K5.numberOfY = 0 ; 
                    }
                    else if(tempElement.charAt(2)==' '){
                        if(tempElement.charAt(1)=='X'){
                            K5.numberOfX = Integer.valueOf(tempElement.charAt(0)+"");
                            K5.numberOfY = 0 ; 
                        }
                        else{
                            K5.numberOfY = Integer.valueOf(tempElement.charAt(0)+"");
                            K5.numberOfX = 0 ; 
                        }
                    }
                    else{
                        if(tempElement.charAt(2)=='X'){
                            K5.numberOfX = Integer.valueOf(tempElement.charAt(0)+tempElement.charAt(1)+"");
                            K5.numberOfY = 0 ; 
                        }
                        else{
                            K5.numberOfY = Integer.valueOf(tempElement.charAt(0)+tempElement.charAt(1)+"");  
                            K5.numberOfX = 0 ; 
                        }
                    }
                    
                    //----------------------------------------------------------------------------------------
                    tempElement = token.nextToken();
                    //---------------------------------------------------------------------------------
                    if(tempElement.charAt(0)==' '){
                        L5.numberOfX  = 0 ; L5.numberOfY = 0 ; 
                    }
                    else if(tempElement.charAt(2)==' '){
                        if(tempElement.charAt(1)=='X'){
                            L5.numberOfX = Integer.valueOf(tempElement.charAt(0)+"");
                            L5.numberOfY = 0 ; 
                        }
                        else{
                            L5.numberOfY = Integer.valueOf(tempElement.charAt(0)+"");
                            L5.numberOfX = 0 ; 
                        }
                    }
                    else{
                        if(tempElement.charAt(2)=='X'){
                            L5.numberOfX = Integer.valueOf(tempElement.charAt(0)+tempElement.charAt(1)+"");
                            L5.numberOfY = 0  ; 
                        }
                        else{
                            L5.numberOfY = Integer.valueOf(tempElement.charAt(0)+tempElement.charAt(1)+"");  
                            L5.numberOfX = 0 ; 
                        }
                    }
                    keeper=readFromFile.readLine(); keeper=readFromFile.readLine();
                    int toplamX = A5.numberOfX+B5.numberOfX+C5.numberOfX+D5.numberOfX+E5.numberOfX+F5.numberOfX+G5.numberOfX+H5.numberOfX+
                            I5.numberOfX+J5.numberOfX+K5.numberOfX+L5.numberOfX+A1.numberOfX+B1.numberOfX+C1.numberOfX+D1.numberOfX+E1.numberOfX+
                            F1.numberOfX+G1.numberOfX+H1.numberOfX+
                            I1.numberOfX+J1.numberOfX+K1.numberOfX+L1.numberOfX+E3.numberOfX ; 
                    int toplamY = A5.numberOfY+B5.numberOfY+C5.numberOfY+D5.numberOfY+E5.numberOfY+F5.numberOfY+G5.numberOfY+H5.numberOfY+
                            I5.numberOfY+J5.numberOfY+K5.numberOfY+L5.numberOfY+A1.numberOfY+B1.numberOfY+C1.numberOfY+D1.numberOfY+E1.numberOfY+
                            F1.numberOfY+G1.numberOfY+H1.numberOfY+
                            I1.numberOfY+J1.numberOfY+K1.numberOfY+L1.numberOfY+H3.numberOfY ; 
                    TopX.numberOfX = 15-toplamX  ;
                    TopY.numberOfY = 15- toplamY;
                    
                }
            }
            
            readFromFile.close();
            
        }catch(FileNotFoundException e1){
            System.out.println("hata 1");
        }catch(IOException e2){
            System.out.println("hata 2 ");
            
        }catch(Exception e3){
            e3.printStackTrace();
            
        }
    }
    
    static void getUserInfo(){
        Board.user_info = 'x';
        File logFile = null ; 
        BufferedReader readFromFile = null ; 
        String keeper = null ; 
        try{
            logFile = new File("src/file_organization_project/Files/Log.dat");
            readFromFile = new BufferedReader(new FileReader(logFile));
            while((keeper=readFromFile.readLine())!=null){
                if(keeper.charAt(0)=='-'){
                    if(keeper.charAt(1)=='X' || keeper.charAt(1)=='x'){
                        Board.user_info = 'y' ; 
                    }
                    else{
                        Board.user_info = 'x' ; 
                    }
                }
            }
            readFromFile.close();
            
        }catch(FileNotFoundException e1){
            
        }catch(IOException e2){
            
        }catch(Exception e3){
            
        }
    }
}
