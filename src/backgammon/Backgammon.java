/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backgammon;

/**
 *
 * @author YILMAZ
 */


public class Backgammon {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        // First we have to adjust the display screen settings.
        StdDraw.setXscale(0.0,1000.0);       // these methods using for adjust coordinate x and y . 
        StdDraw.setYscale(0.0,1000.0);
        
                                            //   despite we' ll use double buffering , we need to this method 
                                           // display screen.
                                           
        StdDraw.enableDoubleBuffering();      // for more clean displaying performance and and apply double buffering.
        /*
            When enableDoubleBuffering() you have to call ****StdDraw.show()***** method to 
        flush buffer to screen (output device.)
            If you don't enable double buffering , it's no necessary to do this.
        */        
         //-----------------------------------------------------------------------------------------
        startingScreen();
        while(true){                        // First i wanna know the user click the play the game button.!
            if(StdDraw.isMousePressed()){
                double _x= StdDraw.mouseX(); 
                double _y = StdDraw.mouseY() ; 
                if((_x>=700 && _x<=900)&&(_y>=250 && _y<=300)){
                    Files.resetFiles();
                    Files.makeFinished(false);
                    break ; 
                }
                else if(Files.possResumeGame &&(_x>=420 && _x<=600)&&(_y>=250 && _y<=300) ){           
                    Files.getInfoFromTable();
                    Files.resumeGame = true ; 
                    // user_info'yu almam lazim.
                    Files.getUserInfo();
                    break ; 
                }
            }
        }
        
        //------------------------------------------------------------------------------------------
        // in here , we'll check is there a game that exit not properly.
        //-----------------------------------------------------------------------------------------
        // eger resume game var ise buraya girmeyecek. 
        while(true && !(Files.resumeGame)){                           // if statement eklencek.
            displayScreen();
            displayStone();
            versusDiceScreen();           
            try{
                   Thread.sleep(1000);
            }catch(InterruptedException e){
                   
            }
            if(Dice.dice1 == Dice.dice2) ; 
            else break ; 
        }
        if(!Files.resumeGame)
            Files.printVersus();      // it means print dice1 and dice2.      // if statement eklencek.
        
        //---------------------------------------------------
        if((Dice.dice1 > Dice.dice2)&&!(Files.resumeGame)) Board.user_info='x'  ;              // if statement eklencek.
        else if(!Files.resumeGame)  Board.user_info='y';                                  // determine which user starts.
        //----------------------------------------------------
        if(Files.resumeGame)
            Files.printTable();                // belki yeri degisebilir.
        
        while(true){                                                 // mother loop.
            if(Board.user_info=='x'){
                if(E3.numberOfX > 0){                               // Kirik tas durumu
                    Board.durum_info='k' ; 
                }
                else if(G1.numberOfX+H1.numberOfX+I1.numberOfX+J1.numberOfX+K1.numberOfX+L1.numberOfX+TopX.numberOfX == 15){    // Base durumu
                    Board.durum_info='b' ; 
                }
                else{                                                   // Normal durum.
                    Board.durum_info='n' ; 
                }
            }
            else{             // it means that Board.user_info =y 
                 if(H3.numberOfY > 0){                             // Kirik tas durumu
                     Board.durum_info='k' ; 
                 }
                 else if(G5.numberOfY+H5.numberOfY+I5.numberOfY+J5.numberOfY+K5.numberOfY+L5.numberOfY+TopY.numberOfY == 15){   //Base durumu
                     Board.durum_info='b' ; 
                 }
                 else{                                               // Normal durum.
                     Board.durum_info='n' ; 
                 }
            }

            
            displayScreen();
            displayStone();
            rollingDice() ; 
            // cancel isaretinin ustunu ciz. (_x>=100 && _x<=200)&&(_y>=60 && _y<=160)
            StdDraw.setPenColor(StdDraw.WHITE);
            StdDraw.filledRectangle(150,110, 50, 50);
            StdDraw.setPenColor();
            StdDraw.show();
            //------------------------------
            Files.dicePrint();
            for(int i = 0 ; i < Board.hamleSayisi ; ++i){        // nested loop.
                mainFunction();
                
                // display screen and stone and dice ...
                displayScreen();
                displayStone();
                displayDice();
                 Files.printTable();  
                try{
                    Thread.sleep(300);
                }catch(InterruptedException e1){
                    
                }
                //-----------------------------------------
            if(Board.user_info=='x'){
                if(E3.numberOfX > 0){                               // Kirik tas durumu
                    Board.durum_info='k' ; 
                }
                else if(G1.numberOfX+H1.numberOfX+I1.numberOfX+J1.numberOfX+K1.numberOfX+L1.numberOfX+TopX.numberOfX == 15){    // Base durumu
                    Board.durum_info='b' ; 
                }
                else{                                                   // Normal durum.
                    Board.durum_info='n' ; 
                }
            }
            else{             // it means that Board.user_info =y 
                 if(H3.numberOfY > 0){                             // Kirik tas durumu
                     Board.durum_info='k' ; 
                 }
                 else if(G5.numberOfY+H5.numberOfY+I5.numberOfY+J5.numberOfY+K5.numberOfY+L5.numberOfY+TopY.numberOfY == 15){   //Base durumu
                     Board.durum_info='b' ; 
                 }
                 else{                                               // Normal durum.
                     Board.durum_info='n' ; 
                 }
            }                
                
                //-----------------------------------------
                // has game finished ?? 
                if(hasGameFinished()) System.exit(1);
                
            }
           
            
            if(Board.user_info=='x'){
                Board.user_info='y' ; 
            }
            else{
                Board.user_info = 'x' ; 
            }
            
        }
        
        
        //------------------------------------------------------------------------------------------
    }
    static void startingScreen(){
        Files.checkResumeGame();
        
        StdDraw.clear();
        StdDraw.picture(300, 800, "./Images/cerrahpasa.png",150,200);
        StdDraw.picture(550, 800, "./Images/infoText.png");
        StdDraw.picture(200, 400, "./Images/backgammon.png");
        StdDraw.picture(800, 150, "./Images/rightText.png");
        StdDraw.picture(675, 550, "./Images/gameText.png");
        StdDraw.picture(655, 400, "./Images/ruleText.png");
        StdDraw.picture(750, 290, "./Images/playDice.png");
        StdDraw.picture(850, 290, "./Images/playText.png");
        if(Files.possResumeGame){
            StdDraw.picture(470,290,"./Images/resume.png");
            StdDraw.picture(550,290,"./Images/resumeText.png");
            
        }
        
        StdDraw.show();
        
    }

    static void displayScreen(){                     // by using StdDraw code, flush the screen.
        StdDraw.clear();
        double centerOfBoardX = 500.0 , centerOfBoardY=500.0 ; 
        double halfWidthOfRectangle = 450.0 , halfHeightOfRectangle = 300.0 ; 
        double vPositionOfFirstSectionX=50 ; 
        double vPositionOfFirstSectionY=200 ;
        //------------------------------
        // Naming .
        StdDraw.text(120, 800+20, "A");
        StdDraw.text(185, 800+20, "B");
        StdDraw.text(250, 800+20, "C");
        StdDraw.text(315, 800+20, "D");
        StdDraw.text(380, 800+20, "E");
        StdDraw.text(455, 800+20, "F");
        StdDraw.text(555, 800+20, "G");
        StdDraw.text(620, 800+20, "H");
        StdDraw.text(685, 800+20, "I");
        StdDraw.text(750, 800+20, "J");
        StdDraw.text(815, 800+20, "K");
        StdDraw.text(880, 800+20, "L");
        //............................
        StdDraw.text(30,260,"5");
        StdDraw.text(30,380,"4");
        StdDraw.text(30,500,"3");
        StdDraw.text(30,620,"2");
        StdDraw.text(30,740,"1");
        //-------------------------------
        //Picture of backgammon board!
        StdDraw.picture(centerOfBoardX, centerOfBoardY, "./Images/board.png",900,600);
        //----------------------------------------------------


         //----------------------------------------------------
         StdDraw.picture(500,900, "./Images/user.png");
         StdDraw.text(550, 900, "X");
        StdDraw.picture(500,120, "./Images/user.png");
         StdDraw.text(550, 125, "Y"); 
         //-----------------------------------------------------

        //------------------------------
        // vertical line.
        StdDraw.line(155,200 ,155 ,800 );
        StdDraw.line(220,200 ,220 ,800 );
        StdDraw.line(285,200,285,800) ; 
        StdDraw.line(350,200,350,800) ; 
        StdDraw.line(415,200,415,800) ; 
        StdDraw.line(590,200,590,800) ; 
        StdDraw.line(655,200,655,800) ; 
        StdDraw.line(720,200,720,800) ; 
        StdDraw.line(785,200,785,800) ; 
        StdDraw.line(850,200,850,800) ; 
        
       
              
        // horizontal line!. 
        StdDraw.line(50, 320, 950, 320);
        StdDraw.line(50, 450, 950, 450);
        StdDraw.line(50, 550, 950, 550);
        StdDraw.line(50, 680, 950, 680);
//        StdDraw.filledCircle(88, 225, 25);
//        StdDraw.filledCircle(88, 275, 25);
        
        
        
        StdDraw.show();             // we have to use this method because of we used double buffering.
        
    }                          // backgammon table.
    
    static void displayStone(){
        // for L5.
        if(L5.numberOfX!=0){
            for(int i = 0 ; i < L5.numberOfX ; ++i){
                if(i==7) break ; // because of display issues.
                StdDraw.picture(880, 250+(30*i), "./Images/blackStone.png");
            }
        }
        else if(L5.numberOfY!=0){
           for(int i = 0 ; i < L5.numberOfY ; ++i){
               if(i==7) break ; 
               StdDraw.picture(880, 250+(30*i),"./Images/whiteStone.png");
           } 
        }
        //for L1.
        if(L1.numberOfX!=0){
           for(int i = 0 ; i < L1.numberOfX ; ++i){
               if(i==7) break ; 
               StdDraw.picture(880, 750-(30*i), "./Images/blackStone.png");
           } 
        }
        else if(L1.numberOfY!=0){
            for(int i = 0 ; i < L1.numberOfY ; ++i){
                if(i==7) break ; 
                StdDraw.picture(880,750-(30*i), "./Images/whiteStone.png");
            }
        }
        // for K5
        if(K5.numberOfX!=0){
            for(int i = 0 ; i < K5.numberOfX ; ++i){
                if(i==7) break ; 
                StdDraw.picture(815, 250+(30*i), "./Images/blackStone.png");
            }
        }
        else if(K5.numberOfY!=0){
            for(int i = 0 ; i < K5.numberOfY ; ++i){
                if(i==7) break ; 
                StdDraw.picture(815, 250+(30*i), "./Images/whiteStone.png");
            }
        }
        //for K1
        if(K1.numberOfX!=0){
            for(int i = 0 ; i < K1.numberOfX ; ++i){
                if(i==7) break ; 
                StdDraw.picture(815, 750-(30*i), "./Images/blackStone.png");
            }
        }
        else if(K1.numberOfY!=0){
            for(int i = 0 ; i < K1.numberOfY ; ++i){
                if(i==7) break ; 
                StdDraw.picture(815, 750-(30*i), "./Images/whiteStone.png");
            }
        }
        // for J5.
        if(J5.numberOfX!=0){
            for(int i = 0 ; i < J5.numberOfX ; ++i){
                if(i==7) break ; 
                StdDraw.picture(750, 250+(30*i), "./Images/blackStone.png");
            }
        }
        else if(J5.numberOfY!=0){
            for(int i = 0 ; i < J5.numberOfY ; ++i){
                if(i==7) break ; 
                StdDraw.picture(750, 250+(30*i), "./Images/whiteStone.png");
            }
        }
        //for J1
        if(J1.numberOfX!=0){
            for(int i = 0 ; i < J1.numberOfX ; ++i){
                if(i==7) break ; 
                StdDraw.picture(750, 750-(30*i), "./Images/blackStone.png");
            }
        }
        else if(J1.numberOfY!=0){
            for(int i = 0 ; i < J1.numberOfY ; ++i){
                if(i==7) break ; 
                StdDraw.picture(750, 750-(30*i), "./Images/whiteStone.png");
            }
        }
        //for I5
        if(I5.numberOfX!=0){
            for(int i = 0 ; i < I5.numberOfX ; ++i){
                if(i==7) break ; 
                StdDraw.picture(685, 250+(30*i), "./Images/blackStone.png");
            }
        }
        else if(I5.numberOfY!=0){
            for(int i = 0 ; i < I5.numberOfY ; ++i){
                if(i==7) break ; 
                StdDraw.picture(685,250+(30*i), "./Images/whiteStone.png");
            }
        }
        //for I1
        if(I1.numberOfX!=0){
            for(int i = 0 ; i < I1.numberOfX ; ++i){
                if(i==7) break ; 
                StdDraw.picture(685, 750-(30*i), "./Images/blackStone.png");
            }
        }
        else if(I1.numberOfY!=0){
            for(int i = 0 ; i < I1.numberOfY ; ++i){
                if (i==7) break ; 
                StdDraw.picture(685, 750-(30*i), "./Images/whiteStone.png");
            }
        }
        //for H5
        if(H5.numberOfX!=0){
            for(int i = 0 ; i < H5.numberOfX ; ++i){
                if(i==7) break ; 
                StdDraw.picture(620, 250+(30*i), "./Images/blackStone.png");
            }
        }
        else if(H5.numberOfY!=0){
            for(int i = 0 ; i < H5.numberOfY ; ++i){
                if(i==7) break ; 
                StdDraw.picture(620,250+(30*i), "./Images/whiteStone.png");
            }
        }  
        // for H1
        if(H1.numberOfX!=0){
            for(int i = 0 ; i < H1.numberOfX ; ++i){
                if(i==7) break ; 
                StdDraw.picture(620, 750-(30*i), "./Images/blackStone.png");
            }
        }
        else if(H1.numberOfY!=0){
            for(int i = 0 ; i < H1.numberOfY ; ++i){
                if (i==7) break ; 
                StdDraw.picture(620, 750-(30*i), "./Images/whiteStone.png");
            }
        }
        // for G5
        if(G5.numberOfX!=0){
            for(int i = 0 ; i < G5.numberOfX ; ++i){
                if(i==7) break ; 
                StdDraw.picture(555, 250+(30*i), "./Images/blackStone.png");
            }
        }
        else if(G5.numberOfY!=0){
            for(int i = 0 ; i < G5.numberOfY ; ++i){
                if(i==7) break ; 
                StdDraw.picture(555,250+(30*i), "./Images/whiteStone.png");
            }
        }
        //for G1
        if(G1.numberOfX!=0){
            for(int i = 0 ; i < G1.numberOfX ; ++i){
                if(i==7) break ; 
                StdDraw.picture(555, 750-(30*i), "./Images/blackStone.png");
            }
        }
        else if(G1.numberOfY!=0){
            for(int i = 0 ; i < G1.numberOfY ; ++i){
                if (i==7) break ; 
                StdDraw.picture(555, 750-(30*i), "./Images/whiteStone.png");
            }
        }  
        // for F5
        if(F5.numberOfX!=0){
            for(int i = 0 ; i < F5.numberOfX ; ++i){
                if(i==7) break ; 
                StdDraw.picture(445, 250+(30*i), "./Images/blackStone.png");
            }
        }
        else if(F5.numberOfY!=0){
            for(int i = 0 ; i < F5.numberOfY ; ++i){
                if(i==7) break ; 
                StdDraw.picture(445,250+(30*i), "./Images/whiteStone.png");
            }
        } 
        // for F1
        if(F1.numberOfX!=0){
            for(int i = 0 ; i < F1.numberOfX ; ++i){
                if(i==7) break ; 
                StdDraw.picture(445, 750-(30*i), "./Images/blackStone.png");
            }
        }
        else if(F1.numberOfY!=0){
            for(int i = 0 ; i < F1.numberOfY ; ++i){
                if (i==7) break ; 
                StdDraw.picture(445, 750-(30*i), "./Images/whiteStone.png");
            }
        }
        // for E5.
         if(E5.numberOfX!=0){
            for(int i = 0 ; i < E5.numberOfX ; ++i){
                if(i==7) break ; 
                StdDraw.picture(380, 250+(30*i), "./Images/blackStone.png");
            }
        }
        else if(E5.numberOfY!=0){
            for(int i = 0 ; i < E5.numberOfY ; ++i){
                if(i==7) break ; 
                StdDraw.picture(380,250+(30*i), "./Images/whiteStone.png");
            }
        }
         // for E1.
        if(E1.numberOfX!=0){
            for(int i = 0 ; i < E1.numberOfX ; ++i){
                if(i==7) break ; 
                StdDraw.picture(380, 750-(30*i), "./Images/blackStone.png");
            }
        }
        else if(E1.numberOfY!=0){
            for(int i = 0 ; i < E1.numberOfY ; ++i){
                if (i==7) break ; 
                StdDraw.picture(380, 750-(30*i), "./Images/whiteStone.png");
            }
        }
        // for D5
         if(D5.numberOfX!=0){
            for(int i = 0 ; i < D5.numberOfX ; ++i){
                if(i==7) break ; 
                StdDraw.picture(315, 250+(30*i), "./Images/blackStone.png");
            }
        }
        else if(D5.numberOfY!=0){
            for(int i = 0 ; i < D5.numberOfY ; ++i){
                if(i==7) break ; 
                StdDraw.picture(315,250+(30*i), "./Images/whiteStone.png");
            }
        }
         // for D1
        if(D1.numberOfX!=0){
            for(int i = 0 ; i < D1.numberOfX ; ++i){
                if(i==7) break ; 
                StdDraw.picture(315, 750-(30*i), "./Images/blackStone.png");
            }
        }
        else if(D1.numberOfY!=0){
            for(int i = 0 ; i < D1.numberOfY ; ++i){
                if (i==7) break ; 
                StdDraw.picture(315, 750-(30*i), "./Images/whiteStone.png");
            }
        }
        // for C5
         if(C5.numberOfX!=0){
            for(int i = 0 ; i < C5.numberOfX ; ++i){
                if(i==7) break ; 
                StdDraw.picture(250, 250+(30*i), "./Images/blackStone.png");
            }
        }
        else if(C5.numberOfY!=0){
            for(int i = 0 ; i < C5.numberOfY ; ++i){
                if(i==7) break ; 
                StdDraw.picture(250,250+(30*i), "./Images/whiteStone.png");
            }
        }
         //for C1
        if(C1.numberOfX!=0){
            for(int i = 0 ; i < C1.numberOfX ; ++i){
                if(i==7) break ; 
                StdDraw.picture(250, 750-(30*i), "./Images/blackStone.png");
            }
        }
        else if(C1.numberOfY!=0){
            for(int i = 0 ; i < C1.numberOfY ; ++i){
                if (i==7) break ; 
                StdDraw.picture(250, 750-(30*i), "./Images/whiteStone.png");
            }
        }
        // for B5
         if(B5.numberOfX!=0){
            for(int i = 0 ; i < B5.numberOfX ; ++i){
                if(i==7) break ; 
                StdDraw.picture(185, 250+(30*i), "./Images/blackStone.png");
            }
        }
        else if(B5.numberOfY!=0){
            for(int i = 0 ; i < B5.numberOfY ; ++i){
                if(i==7) break ; 
                StdDraw.picture(185,250+(30*i), "./Images/whiteStone.png");
            }
        }
         // for B1
        if(B1.numberOfX!=0){
            for(int i = 0 ; i < B1.numberOfX ; ++i){
                if(i==7) break ; 
                StdDraw.picture(185, 750-(30*i), "./Images/blackStone.png");
            }
        }
        else if(B1.numberOfY!=0){
            for(int i = 0 ; i < B1.numberOfY ; ++i){
                if (i==7) break ; 
                StdDraw.picture(185, 750-(30*i), "./Images/whiteStone.png");
            }
        }
        // for A5
         if(A5.numberOfX!=0){
            for(int i = 0 ; i < A5.numberOfX ; ++i){
                if(i==7) break ; 
                StdDraw.picture(120, 250+(30*i), "./Images/blackStone.png");
            }
        }
        else if(A5.numberOfY!=0){
            for(int i = 0 ; i < A5.numberOfY ; ++i){
                if(i==7) break ; 
                StdDraw.picture(120,250+(30*i), "./Images/whiteStone.png");
            }
        } 
         // for A1
        if(A1.numberOfX!=0){
            for(int i = 0 ; i < A1.numberOfX ; ++i){
                if(i==7) break ; 
                StdDraw.picture(120, 750-(30*i), "./Images/blackStone.png");
            }
        }
        else if(A1.numberOfY!=0){
            for(int i = 0 ; i < A1.numberOfY ; ++i){
                if (i==7) break ; 
                StdDraw.picture(120, 750-(30*i), "./Images/whiteStone.png");
            }
        }
        // for TopX
        if(TopX.numberOfX!=0){
            for(int i = 0 ; i < TopX.numberOfX ; ++i){
                if(i==7) break ; 
                StdDraw.picture(975, 750-(30*i), "./Images/blackStone.png");
            }
        }
        // for TopY
        if(TopY.numberOfY!=0){
            for(int i = 0 ; i < TopY.numberOfY ; ++i){
                if(i==7) break ; 
                StdDraw.picture(975, 250+(30*i), "./Images/whiteStone.png");
            }
        }
        // for E3.
        if(E3.numberOfX!=0){
            for(int i = 0 ; i < E3.numberOfX ; ++i){
                if(i==3) break ; 
                StdDraw.picture(385, 467+(30*i), "./Images/blackStone.png");
            }
        }
        // for H3
        if(H3.numberOfY!=0){
            for(int i = 0 ; i < H3.numberOfY ; ++i){
                if(i==3) break ; 
                StdDraw.picture(623, 467+(30*i), "./Images/whiteStone.png");
            }
        }
        
        StdDraw.show();
        
    }
    
    static void versusDiceScreen(){

        StdDraw.picture(600, 900, "./Images/rollDice.png");
        StdDraw.show();        
        while(true){
            if(StdDraw.isMousePressed()){
                double _x = StdDraw.mouseX() ; 
                double _y = StdDraw.mouseY() ; 
                if((_x>=550 && _x<=650)&&(_y >=850 && _y<=950))
                                                                break ; 
                
                
            }
            
        }
        //----------------------------------------
        StdDraw.setPenColor(StdDraw.WHITE);
        StdDraw.filledRectangle(600, 900, 30, 50);      // to delete rolling dice icon.
        StdDraw.show();
        StdDraw.setPenColor(StdDraw.BLACK);
        //----------------------------------------
        //----------------------------------------
        java.util.Random rand = new java.util.Random();
        int rand_int1 = rand.nextInt(50) ; 
        Dice.dice1 = (rand_int1 % 6) + 1 ; 
        
        if(Dice.dice1==1){
            StdDraw.picture(450, 500, "./Images/dice1.png");
        }
        else if(Dice.dice1 == 2){
            StdDraw.picture(450, 500, "./Images/dice2.png");
        }
        else if(Dice.dice1==3){
            StdDraw.picture(450, 500, "./Images/dice3.png");
        }
        else if(Dice.dice1 == 4){                      // to display dice .
           StdDraw.picture(450, 500, "./Images/dice4.png"); 
        }
        else if(Dice.dice1==5){
            StdDraw.picture(450, 500, "./Images/dice5.png");
        }
        else{   // dice = 6 
            StdDraw.picture(450, 500, "./Images/dice6.png");
        }
        StdDraw.show();
        //---------------------------------------------------------------------
       StdDraw.picture(600, 110, "./Images/rollDice.png");
        StdDraw.show();
        while(true){
            if(StdDraw.isMousePressed()){
                double _x = StdDraw.mouseX() ; 
                double _y = StdDraw.mouseY() ; 
                if((_x>=550 && _x<=650)&&(_y >=60 && _y<=160))
                                                                break ; 
            }
        }       
        //----------------------------------------
        StdDraw.setPenColor(StdDraw.WHITE);
        StdDraw.filledRectangle(600, 110, 30, 50);      // to delete rolling dice icon.
        StdDraw.show();
        StdDraw.setPenColor(StdDraw.BLACK);
        //----------------------------------------
//----------------------------------------
        java.util.Random rand2 = new java.util.Random();
        int rand_int2 = rand2.nextInt(50) ; 
        Dice.dice2 = (rand_int2 % 6) + 1 ; 
        
        if(Dice.dice2==1){
            StdDraw.picture(555, 500, "./Images/dice1.png");
        }
        else if(Dice.dice2 == 2){
            StdDraw.picture(555, 500, "./Images/dice2.png");
        }
        else if(Dice.dice2==3){
            StdDraw.picture(555, 500, "./Images/dice3.png");
        }
        else if(Dice.dice2 == 4){                      // to display dice .
           StdDraw.picture(555, 500, "./Images/dice4.png"); 
        }
        else if(Dice.dice2==5){
            StdDraw.picture(555, 500, "./Images/dice5.png");
        }
        else{   // dice = 6 
            StdDraw.picture(555, 500, "./Images/dice6.png");
        }
        StdDraw.show();
        //---------------------------------------------------------------------        
    }
    
    static void rollingDice(){
        if(Board.user_info == 'x'){                                  // rolling dice for X .
           StdDraw.picture(600, 900, "./Images/rollDice.png");

        }
        else{                                                       // rolling dice for Y .
          StdDraw.picture(600, 110, "./Images/rollDice.png");
            
        }
        StdDraw.picture(150,110,"./Images/cancel.png");
        StdDraw.show();
        while(true){
            if(StdDraw.isMousePressed()){
                double _x = StdDraw.mouseX() ; 
                double _y = StdDraw.mouseY() ; 
                if((_x>=100 && _x<=200)&&(_y>=60 && _y<=160)){
                    System.exit(1);
                }
                if(Board.user_info == 'x'){                                              //sira X'de 

                    if((_x>=550 && _x<=650)&&(_y>=850&&_y<=950)){
                       StdDraw.setPenColor(StdDraw.WHITE);
                       StdDraw.filledRectangle(600, 900, 30, 50);      // to delete rolling dice icon.  
                       StdDraw.setPenColor();
                       break ; 
                    }
                     
                }
                else{                                            // sira Y'de
                    if((_x>=550&&_x<=650)&&(_y>=60&&_y<=160)){
                        StdDraw.setPenColor(StdDraw.WHITE);
                        StdDraw.filledRectangle(600,110,30,50);
                        StdDraw.setPenColor();
                        break ; 
                        
                    }
                    
                }
            }
             
        }
        
        java.util.Random rand = new java.util.Random();
        int rand_int1 = rand.nextInt(50) ; 
        Dice.dice1 = (rand_int1 % 6) + 1 ; 
        
        if(Dice.dice1==1){
            StdDraw.picture(450, 500, "./Images/dice1.png");
        }
        else if(Dice.dice1 == 2){
            StdDraw.picture(450, 500, "./Images/dice2.png");
        }
        else if(Dice.dice1==3){
            StdDraw.picture(450, 500, "./Images/dice3.png");
        }
        else if(Dice.dice1 == 4){                      // to display dice .
           StdDraw.picture(450, 500, "./Images/dice4.png"); 
        }
        else if(Dice.dice1==5){
            StdDraw.picture(450, 500, "./Images/dice5.png");
        }
        else{   // dice = 6 
            StdDraw.picture(450, 500, "./Images/dice6.png");
        }      
        
        java.util.Random rand2 = new java.util.Random();
        int rand_int2 = rand2.nextInt(50) ; 
        Dice.dice2 = (rand_int2 % 6) + 1 ; 
        
        if(Dice.dice2==1){
            StdDraw.picture(555, 500, "./Images/dice1.png");
        }
        else if(Dice.dice2 == 2){
            StdDraw.picture(555, 500, "./Images/dice2.png");
        }
        else if(Dice.dice2==3){
            StdDraw.picture(555, 500, "./Images/dice3.png");
        }
        else if(Dice.dice2 == 4){                      // to display dice .
           StdDraw.picture(555, 500, "./Images/dice4.png"); 
        }
        else if(Dice.dice2==5){
            StdDraw.picture(555, 500, "./Images/dice5.png");
        }
        else{   // dice = 6 
            StdDraw.picture(555, 500, "./Images/dice6.png");
        }
        
        Board.hamleler = new java.util.ArrayList<>() ; 
        
        if(Dice.dice1 == Dice.dice2){
            Dice.even = true ; 
            Board.hamleSayisi = 4 ; 
            Board.hamleler.add(Dice.dice1);
            Board.hamleler.add(Dice.dice1);
            Board.hamleler.add(Dice.dice1);
            Board.hamleler.add(Dice.dice1);

            
        }
        else{
            Dice.even = false ; 
            Board.hamleSayisi = 2 ; 
            Board.hamleler.add(Dice.dice1) ; 
            Board.hamleler.add(Dice.dice2) ; 
        }
        StdDraw.show();       
        
    }
    
    static void displayDice(){
        if(Dice.dice1==1){
            StdDraw.picture(450, 500, "./Images/dice1.png");
        }
        else if(Dice.dice1 == 2){
            StdDraw.picture(450, 500, "./Images/dice2.png");
        }
        else if(Dice.dice1==3){
            StdDraw.picture(450, 500, "./Images/dice3.png");
        }
        else if(Dice.dice1 == 4){                      // to display dice .
           StdDraw.picture(450, 500, "./Images/dice4.png"); 
        }
        else if(Dice.dice1==5){
            StdDraw.picture(450, 500, "./Images/dice5.png");
        }
        else{   // dice = 6 
            StdDraw.picture(450, 500, "./Images/dice6.png");
        }
         if(Dice.dice2==1){
            StdDraw.picture(555, 500, "./Images/dice1.png");
        }
        else if(Dice.dice2 == 2){
            StdDraw.picture(555, 500, "./Images/dice2.png");
        }
        else if(Dice.dice2==3){
            StdDraw.picture(555, 500, "./Images/dice3.png");
        }
        else if(Dice.dice2 == 4){                      // to display dice .
           StdDraw.picture(555, 500, "./Images/dice4.png"); 
        }
        else if(Dice.dice2==5){
            StdDraw.picture(555, 500, "./Images/dice5.png");
        }
        else{   // dice = 6 
            StdDraw.picture(555, 500, "./Images/dice6.png");
        }
         StdDraw.show();
        
    }
    
    static void mainFunction(){
        if(Board.user_info=='x'&&Board.durum_info=='k'){
            userXKirik();
        }
        else if(Board.user_info=='x'&&Board.durum_info=='b'){
            userXBase();
        }
        else if(Board.user_info=='x'&&Board.durum_info=='n'){
            userXNormal();
        }
        else if(Board.user_info=='y'&&Board.durum_info=='k'){
            userYKirik();
        }
        else if(Board.user_info=='y'&&Board.durum_info=='b'){
            userYBase();
        }
        else {
            userYNormal();
        }
    }
    
    static void userXKirik(){
        boolean checkE3 = false  ;
        for(int i = 0 ; i < Board.hamleler.size();++i){
            if(Board.hamleler.get(i)==1){
                if(L5.numberOfY < 2 ){
                    checkE3 = true ; break ; 
                }
            }
            else if(Board.hamleler.get(i)==2){
                if(K5.numberOfY < 2 ){
                    checkE3 = true ; break ; 
                }
            }
            else if(Board.hamleler.get(i)==3){
                if(J5.numberOfY < 2 ){
                    checkE3 = true ; break ; 
                }
            }
            else if(Board.hamleler.get(i)==4){
                if(I5.numberOfY < 2 ){
                    checkE3 = true ; break ; 
                }
            }
            else if(Board.hamleler.get(i)==5){
                if(H5.numberOfY < 2){
                    checkE3 = true ; break ; 
                }
            }
            else if(Board.hamleler.get(i)==6){
                if(G5.numberOfY < 2 ){
                    checkE3 = true ; break  ;
                }
            }
        }
        if(checkE3 == false){
            StdDraw.setPenColor(StdDraw.RED);
            StdDraw.text(650,900, "No valid Move");
            StdDraw.setPenColor();
            StdDraw.show();
            try{
                Thread.sleep(300);
            }catch(InterruptedException e){}
            return ; 
        }
         StdDraw.text(650,900,"E3 -> ? ");
                        StdDraw.show();
                        double _x2 ; 
                        double _y2 ; 
                        while(true){                                                    // destination loop.
                            if(StdDraw.isMousePressed()){
                                _x2 = StdDraw.mouseX() ; 
                                _y2 = StdDraw.mouseY() ; 
                                if(((_x2>=854&&_x2<=910)&&(_y2>=242&&_y2<=444))&&(L5.numberOfY < 2)){     // L5'e git.
                                    for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                                        if(Board.hamleler.get(i)==1){
                                            Board.hamleler.remove(i);
                                            E3.numberOfX -=1 ; 
                                            L5.numberOfX +=1 ; 
                                            if(L5.numberOfY ==1){
                                                L5.numberOfY = 0 ; 
                                                H3.numberOfY += 1 ; 
                                            }
                                            StdDraw.setPenColor(StdDraw.WHITE) ; 
                                            StdDraw.filledRectangle(650,900,30,50);
                                            StdDraw.setPenColor();
                                            StdDraw.show();
                                            StdDraw.text(650,900,"E3 -> L5");
                                            Files.hamlePrint("E3","L5");
                                            StdDraw.show();
                                            try{
                                                Thread.sleep(300);
                                            }catch(InterruptedException e){}
                                            return ; 
                                        }
                                        else{
                                            continue ; 
                                        }
                                    }
                                }
                                else if(((_x2>=788&&_x2<=842)&&(_y2>=242&&_y2<=444))&&(K5.numberOfY < 2)){   // K5'e git.
                                    
                                    for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                                        if(Board.hamleler.get(i)==2){
                                            Board.hamleler.remove(i);
                                            E3.numberOfX -=1 ; 
                                            K5.numberOfX +=1 ; 
                                            if(K5.numberOfY ==1){
                                                K5.numberOfY = 0 ; 
                                                H3.numberOfY += 1 ; 
                                            }
                                            StdDraw.setPenColor(StdDraw.WHITE) ; 
                                            StdDraw.filledRectangle(650,900,30,50);
                                            StdDraw.setPenColor();
                                            StdDraw.show();
                                            StdDraw.text(650,900,"E3 -> K5");
                                            Files.hamlePrint("E3","K5");
                                            StdDraw.show();
                                            try{
                                                Thread.sleep(300);
                                            }catch(InterruptedException e){}
                                            return ; 
                                        }
                                        else{
                                            continue ; 
                                        }
                                    }
                                }
                                else if(((_x2>=725&&_x2<=781)&&(_y2>=242&&_y2<=444))&&(J5.numberOfY < 2)){   // J5'e git
                                    
                                    for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                                        if(Board.hamleler.get(i)==3){
                                            Board.hamleler.remove(i);
                                            E3.numberOfX -=1 ; 
                                            J5.numberOfX +=1 ; 
                                            if(J5.numberOfY ==1){
                                                J5.numberOfY = 0 ; 
                                                H3.numberOfY += 1 ; 
                                            }
                                            StdDraw.setPenColor(StdDraw.WHITE) ; 
                                            StdDraw.filledRectangle(650,900,30,50);
                                            StdDraw.setPenColor();
                                            StdDraw.show();
                                            StdDraw.text(650,900,"E3 -> J5");
                                            Files.hamlePrint("E3","J5");
                                            StdDraw.show();
                                            try{
                                                Thread.sleep(300);
                                            }catch(InterruptedException e){}
                                            return ; 
                                        }
                                        else{
                                            continue ; 
                                        }
                                    }
                                }
                                else if(((_x2>=659&&_x2<=715)&&(_y2>=242&&_y2<=444))&&(I5.numberOfY < 2)){      //I5 icin.
                                    
                                    for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                                        if(Board.hamleler.get(i)==4){
                                            Board.hamleler.remove(i);
                                            E3.numberOfX -=1 ; 
                                            I5.numberOfX +=1 ; 
                                            if(I5.numberOfY ==1){
                                                I5.numberOfY = 0 ; 
                                                H3.numberOfY += 1 ; 
                                            }
                                            StdDraw.setPenColor(StdDraw.WHITE) ; 
                                            StdDraw.filledRectangle(650,900,30,50);
                                            StdDraw.setPenColor();
                                            StdDraw.show();
                                            StdDraw.text(650,900,"E3 -> I5");
                                            Files.hamlePrint("E3","I5");
                                            StdDraw.show();
                                            try{
                                                Thread.sleep(300);
                                            }catch(InterruptedException e){}
                                            return ; 
                                        }
                                        else{
                                            continue ; 
                                        }
                                    }
                                }
                                else if(((_x2>=594&&_x2<=647)&&(_y2>=242&&_y2<=444))&&(H5.numberOfY < 2)){    // H5 icin.
                                    for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                                        if(Board.hamleler.get(i)==5){
                                            Board.hamleler.remove(i);
                                            E3.numberOfX -=1 ; 
                                            H5.numberOfX +=1 ; 
                                            if(H5.numberOfY ==1){
                                                H5.numberOfY = 0 ; 
                                                H3.numberOfY += 1 ; 
                                            }
                                            StdDraw.setPenColor(StdDraw.WHITE) ; 
                                            StdDraw.filledRectangle(650,900,30,50);
                                            StdDraw.setPenColor();
                                            StdDraw.show();
                                            StdDraw.text(650,900,"E3 -> H5");
                                            Files.hamlePrint("E3","H5");
                                            StdDraw.show();
                                            try{
                                                Thread.sleep(300);
                                            }catch(InterruptedException e){}
                                            return ; 
                                        }
                                        else{
                                            continue ; 
                                        }
                                    }
                                }
                                else if(((_x2>=528&&_x2<=583)&&(_y2>=242&&_y2<=444))&&(G5.numberOfY < 2)){     // G5 icin
                                    for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                                        if(Board.hamleler.get(i)==6){
                                            Board.hamleler.remove(i);
                                            E3.numberOfX -=1 ; 
                                            G5.numberOfX +=1 ; 
                                            if(G5.numberOfY ==1){
                                                G5.numberOfY = 0 ; 
                                                H3.numberOfY += 1 ; 
                                            }
                                            StdDraw.setPenColor(StdDraw.WHITE) ; 
                                            StdDraw.filledRectangle(650,900,30,50);
                                            StdDraw.setPenColor();
                                            StdDraw.show();
                                            StdDraw.text(650,900,"E3 -> G5");
                                            Files.hamlePrint("E3","G5");
                                            StdDraw.show();
                                            try{
                                                Thread.sleep(300);
                                            }catch(InterruptedException e){}
                                            return ; 
                                        }
                                    }
                                }    
                            }
                        }
                    
        
    }
    
    static void userXBase(){
        boolean check = false ; 
        //----------------------------------------------------------------------         First Section - Control Section - 
        // for G1. 
        if(G1.numberOfX > 0 ){
            for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                if(Board.hamleler.get(i)==1){
                    if(H1.numberOfY < 2){
                        check = true ; 
                        break ; 
                    }
                }
                else if(Board.hamleler.get(i)==2){
                    if(I1.numberOfY < 2 ){
                        check = true  ;
                        break  ;
                    }
                }
                else if(Board.hamleler.get(i)==3){
                    if(J1.numberOfY< 2 ){
                        check = true ; 
                        break ; 
                    }
                }
                else if(Board.hamleler.get(i)==4){
                    if(K1.numberOfY < 2 ){
                        check = true  ;
                        break ; 
                    }
                }
                else if (Board.hamleler.get(i)==5){
                    if(L1.numberOfY < 2 ){
                        check = true ; 
                        break ; 
                    }
                }
                else if(Board.hamleler.get(i)==6){
                    check = true ; 
                    break ; 
                }
            }
        }
        // for H1
        if(H1.numberOfX > 0 ){
            for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                if(Board.hamleler.get(i)==1){
                    if(I1.numberOfY < 2){
                        check = true ; 
                        break ; 
                    }
                }
                else if(Board.hamleler.get(i)==2){
                    if(J1.numberOfY < 2 ){
                        check = true  ;
                        break  ;
                    }
                }
                else if(Board.hamleler.get(i)==3){
                    if(K1.numberOfY< 2 ){
                        check = true ; 
                        break ; 
                    }
                }
                else if(Board.hamleler.get(i)==4){
                    if(L1.numberOfY < 2 ){
                        check = true  ;
                        break ; 
                    }
                }
                else if(Board.hamleler.get(i)>=5){
                    check = true ; 
                    break ; 
                }
            }
        }
        // I1 icin.
        if(I1.numberOfX > 0 ){
            for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                if(Board.hamleler.get(i)==1){
                    if(J1.numberOfY < 2){
                        check = true ; 
                        break ; 
                    }
                }
                else if(Board.hamleler.get(i)==2){
                    if(K1.numberOfY < 2 ){
                        check = true  ;
                        break  ;
                    }
                }
                else if(Board.hamleler.get(i)==3){
                    if(L1.numberOfY< 2 ){
                        check = true ; 
                        break ; 
                    }
                }
                else if(Board.hamleler.get(i)>=4){
                    check = true ; 
                }
            }
        }
        // J1 icin.
        if(J1.numberOfX > 0 ){
            for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                if(Board.hamleler.get(i)==1){
                    if(K1.numberOfY < 2){
                        check = true ; 
                        break ; 
                    }
                }
                else if(Board.hamleler.get(i)==2){
                    if(L1.numberOfY < 2 ){
                        check = true  ;
                        break  ;
                    }
                }
                else if(Board.hamleler.get(i)>=3){
                    check = true ; 
                }
            }
        }
        // K1 icin.
        if(K1.numberOfX > 0 ){
            for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                
                if(Board.hamleler.get(i)==1){
                    if(L1.numberOfY < 2 ){
                        check = true ; break ; 
                    }
                }
                else if(Board.hamleler.get(i)>=2){
                    check = true ; 
                    break ; 
                }
            }
        }
        // L1 icin.
        if(L1.numberOfX > 0){
            check = true ; 
        }
        // control state has finished.
        
        //----------------------------------------------------------------------
        if(check == false ){
            StdDraw.setPenColor(StdDraw.RED);
            StdDraw.text(650,900, "No valid Move");
            StdDraw.setPenColor();
            StdDraw.show();
            try{
                Thread.sleep(300);
            }catch(InterruptedException e){}
            return ; 
        }
        StdDraw.text(650, 900, "Please select stone");
        StdDraw.show();
        try{
            Thread.sleep(2000);
        }catch(InterruptedException e){
            
        }        
        StdDraw.setPenColor(StdDraw.WHITE);
        StdDraw.filledRectangle(650, 900, 80, 50);
        StdDraw.show();

        StdDraw.setPenColor();
        //----------------------------------------------------------------------
        
        double _x ; 
        double _y ; 
        while(true){                        // select stone loop.

            if(StdDraw.isMousePressed()){
                _x = StdDraw.mouseX()  ;
                _y = StdDraw.mouseY() ;     
                if(((_x>=525 && _x<=585)&&(_y>=556 && _y<=761)) && G1.numberOfX > 0 ){   // g1 icin hamle var mi ? 
                    boolean checkG1 = false ; 
                    for(int i = 0 ; i < Board.hamleler.size(); ++i){
                        if(Board.hamleler.get(i)==1){
                            if(H1.numberOfY < 2 ){
                                checkG1 = true  ; 
                            }
                        }
                        else if(Board.hamleler.get(i)==2){
                            if(I1.numberOfY < 2 ){
                                checkG1  =true  ;
                            }
                        }
                        else if(Board.hamleler.get(i)==3){
                            if(J1.numberOfY < 2 ){
                                checkG1 = true  ;
                            }
                        }
                        else if(Board.hamleler.get(i)==4){
                            if(K1.numberOfY < 2 ){
                                checkG1 = true ; 
                            }
                        }
                        else if(Board.hamleler.get(i)==5){
                            if(L1.numberOfY < 2 ){
                                checkG1 = true  ;
                            }
                        }
                        else if(Board.hamleler.get(i)==6){
                            checkG1 = true  ;
                        }
                    }// end of for .
                    if(checkG1 == false){
                        StdDraw.setPenColor(StdDraw.RED) ; 
                        StdDraw.text(650,900,"No valid move from G1.");
                        StdDraw.show();
                        StdDraw.setPenColor();
                        try{
                            Thread.sleep(300);
                        }catch(InterruptedException e){}
                        StdDraw.setPenColor(StdDraw.WHITE);
                        StdDraw.filledRectangle(650, 900, 90,50);
                        StdDraw.setPenColor();
                        StdDraw.show();
                    }
                    else{
                        StdDraw.text(650,900,"G1 -> ? ");
                        StdDraw.show();
                        double _x2 ; 
                        double _y2 ; 
                        while(true){                                             // destination loop.
                            if(StdDraw.isMousePressed()){
                                _x2 = StdDraw.mouseX() ; 
                                _y2 = StdDraw.mouseY() ; 
                                if(((_x2>=595&&_x2<=652)&&(_y2>=558&&_y2<=762))&&(H1.numberOfY < 2)){     // H1'e git.
                                    for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                                        if(Board.hamleler.get(i)==1){
                                            Board.hamleler.remove(i);
                                            G1.numberOfX -=1 ; 
                                            H1.numberOfX +=1 ; 
                                            if(H1.numberOfY ==1){
                                                H1.numberOfY = 0 ; 
                                                H3.numberOfY += 1 ; 
                                            }
                                            StdDraw.setPenColor(StdDraw.WHITE) ; 
                                            StdDraw.filledRectangle(650,900,30,50);
                                            StdDraw.setPenColor();
                                            StdDraw.show();
                                            StdDraw.text(650,900,"G1 -> H1");
                                            Files.hamlePrint("G1","H1");
                                            StdDraw.show();
                                            try{
                                                Thread.sleep(300);
                                            }catch(InterruptedException e){}
                                            return ; 
                                        }
                                        else{
                                            continue ; 
                                        }
                                    }
                                }
                                else if(((_x2>=659&&_x2<=715)&&(_y2>=558&&_y2<=762))&&(I1.numberOfY < 2)){   // I1'e git.
                                    
                                    for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                                        if(Board.hamleler.get(i)==2){
                                            Board.hamleler.remove(i);
                                            G1.numberOfX -=1 ; 
                                            I1.numberOfX +=1 ; 
                                            if(I1.numberOfY ==1){
                                                I1.numberOfY = 0 ; 
                                                H3.numberOfY += 1 ; 
                                            }
                                            StdDraw.setPenColor(StdDraw.WHITE) ; 
                                            StdDraw.filledRectangle(650,900,30,50);
                                            StdDraw.setPenColor();
                                            StdDraw.show();
                                            StdDraw.text(650,900,"G1 -> I1");
                                            Files.hamlePrint("G1","I1");
                                            StdDraw.show();
                                            try{
                                                Thread.sleep(300);
                                            }catch(InterruptedException e){}
                                            return ; 
                                        }
                                        else{
                                            continue ; 
                                        }
                                    }
                                }
                                else if(((_x2>=722&&_x2<=780)&&(_y2>=558&&_y2<=762))&&(J1.numberOfY < 2)){   // J1'e git
                                    
                                    for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                                        if(Board.hamleler.get(i)==3){
                                            Board.hamleler.remove(i);
                                            G1.numberOfX -=1 ; 
                                            J1.numberOfX +=1 ; 
                                            if(J1.numberOfY ==1){
                                                J1.numberOfY = 0 ; 
                                                H3.numberOfY += 1 ; 
                                            }
                                            StdDraw.setPenColor(StdDraw.WHITE) ; 
                                            StdDraw.filledRectangle(650,900,30,50);
                                            StdDraw.setPenColor();
                                            StdDraw.show();
                                            StdDraw.text(650,900,"G1 -> J1");
                                            Files.hamlePrint("G1","J1");
                                            StdDraw.show();
                                            try{
                                                Thread.sleep(300);
                                            }catch(InterruptedException e){}
                                            return ; 
                                        }
                                        else{
                                            continue ; 
                                        }
                                    }
                                }
                                else if(((_x2>=790&&_x2<=842)&&(_y2>=558&&_y2<=762))&&(K1.numberOfY < 2)){      //K1 icin.
                                    
                                    for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                                        if(Board.hamleler.get(i)==4){
                                            Board.hamleler.remove(i);
                                            G1.numberOfX -=1 ; 
                                            K1.numberOfX +=1 ; 
                                            if(K1.numberOfY ==1){
                                                K1.numberOfY = 0 ; 
                                                H3.numberOfY += 1 ; 
                                            }
                                            StdDraw.setPenColor(StdDraw.WHITE) ; 
                                            StdDraw.filledRectangle(650,900,30,50);
                                            StdDraw.setPenColor();
                                            StdDraw.show();
                                            StdDraw.text(650,900,"G1 -> K1");
                                            Files.hamlePrint("G1","K1");
                                            StdDraw.show();
                                            try{
                                                Thread.sleep(300);
                                            }catch(InterruptedException e){}
                                            return ; 
                                        }
                                        else{
                                            continue ; 
                                        }
                                    }
                                }
                                else if(((_x2>=852&&_x2<=910)&&(_y2>=558&&_y2<=762))&&(L1.numberOfY < 2)){    // L1 icin.
                                    for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                                        if(Board.hamleler.get(i)==5){
                                            Board.hamleler.remove(i);
                                            G1.numberOfX -=1 ; 
                                            L1.numberOfX +=1 ; 
                                            if(L1.numberOfY ==1){
                                                L1.numberOfY = 0 ; 
                                                H3.numberOfY += 1 ; 
                                            }
                                            StdDraw.setPenColor(StdDraw.WHITE) ; 
                                            StdDraw.filledRectangle(650,900,30,50);
                                            StdDraw.setPenColor();
                                            StdDraw.show();
                                            StdDraw.text(650,900,"G1 -> L1");
                                            Files.hamlePrint("G1","L1");
                                            StdDraw.show();
                                            try{
                                                Thread.sleep(300);
                                            }catch(InterruptedException e){}
                                            return ; 
                                        }
                                        else{
                                            continue ; 
                                        }
                                    }
                                }
                                else if(((_x2>=952&&_x2<=994)&&(_y2>=558&&_y2<=762))){     // Topx
                                    for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                                        if(Board.hamleler.get(i)==6){
                                            Board.hamleler.remove(i);
                                            G1.numberOfX -=1 ; 
                                            TopX.numberOfX +=1 ; 
                                            
                                            StdDraw.setPenColor(StdDraw.WHITE) ; 
                                            StdDraw.filledRectangle(650,900,30,50);
                                            StdDraw.setPenColor();
                                            StdDraw.show();
                                            StdDraw.text(650,900,"G1 -> TopX");
                                            Files.hamlePrint("G1","TopX");
                                            StdDraw.show();
                                            try{
                                                Thread.sleep(300);
                                            }catch(InterruptedException e){}
                                            return ; 
                                        }
                                        else{
                                            continue ; 
                                        }
                                    }
                                }
                                else{
                                    
                                }
                                    
                            }
                        }
                      
                    }
                    
                    
                }//------------------------------------------------------------------------------------------------
                else if(((_x>=595 && _x<=652)&&(_y>=556 && _y<=761)) && H1.numberOfX > 0){     // from H1.
                    boolean checkH1 = false ; 
                    for(int i = 0 ; i < Board.hamleler.size(); ++i){
                        if(Board.hamleler.get(i)==1){
                            if(I1.numberOfY < 2 ){
                                checkH1 = true  ; 
                            }
                        }
                        else if(Board.hamleler.get(i)==2){
                            if(J1.numberOfY < 2 ){
                                checkH1  =true  ;
                            }
                        }
                        else if(Board.hamleler.get(i)==3){
                            if(K1.numberOfY < 2 ){
                                checkH1 = true  ;
                            }
                        }
                        else if(Board.hamleler.get(i)==4){
                            if(L1.numberOfY < 2 ){
                                checkH1 = true ; 
                            }
                        }
                        else if (Board.hamleler.get(i)>=5){
                            checkH1 = true  ;
                        }
                    }// end of for .
                    if(checkH1 == false){
                        StdDraw.setPenColor(StdDraw.RED) ; 
                        StdDraw.text(650,900,"No valid move from H1.");
                        StdDraw.show();
                        StdDraw.setPenColor();
                        try{
                            Thread.sleep(300);
                        }catch(InterruptedException e){}
                        StdDraw.setPenColor(StdDraw.WHITE);
                        StdDraw.filledRectangle(650, 900, 90,50);
                        StdDraw.setPenColor();
                        StdDraw.show();
                    }
                    else{
                        StdDraw.text(650,900,"H1 -> ? ");
                        StdDraw.show();
                        double _x2 ; 
                        double _y2 ; 
                        while(true){                                             // destination loop. from H1 to where ? 
                            if(StdDraw.isMousePressed()){
                                _x2 = StdDraw.mouseX() ; 
                                _y2 = StdDraw.mouseY() ; 
                                if(((_x2>=659&&_x2<=715)&&(_y2>=558&&_y2<=762))&&(I1.numberOfY < 2)){     // I1'e git.
                                    for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                                        if(Board.hamleler.get(i)==1){
                                            Board.hamleler.remove(i);
                                            H1.numberOfX -=1 ; 
                                            I1.numberOfX +=1 ; 
                                            if(I1.numberOfY ==1){
                                                I1.numberOfY = 0 ; 
                                                H3.numberOfY += 1 ; 
                                            }
                                            StdDraw.setPenColor(StdDraw.WHITE) ; 
                                            StdDraw.filledRectangle(650,900,30,50);
                                            StdDraw.setPenColor();
                                            StdDraw.show();
                                            StdDraw.text(650,900,"H1 -> I1");
                                            Files.hamlePrint("H1","I1");
                                            StdDraw.show();
                                            try{
                                                Thread.sleep(300);
                                            }catch(InterruptedException e){}
                                            return ; 
                                        }
                                        else{
                                            continue ; 
                                        }
                                    }
                                }
                                else if(((_x2>=722&&_x2<=780)&&(_y2>=558&&_y2<=762))&&(J1.numberOfY < 2)){   // J1'e git.
                                    
                                    for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                                        if(Board.hamleler.get(i)==2){
                                            Board.hamleler.remove(i);
                                            H1.numberOfX -=1 ; 
                                            J1.numberOfX +=1 ; 
                                            if(J1.numberOfY ==1){
                                                J1.numberOfY = 0 ; 
                                                H3.numberOfY += 1 ; 
                                            }
                                            StdDraw.setPenColor(StdDraw.WHITE) ; 
                                            StdDraw.filledRectangle(650,900,30,50);
                                            StdDraw.setPenColor();
                                            StdDraw.show();
                                            StdDraw.text(650,900,"H1 -> J1");
                                            Files.hamlePrint("H1","J1");
                                            StdDraw.show();
                                            try{
                                                Thread.sleep(300);
                                            }catch(InterruptedException e){}
                                            return ; 
                                        }
                                        else{
                                            continue ; 
                                        }
                                    }
                                }
                                else if(((_x2>=790&&_x2<=842)&&(_y2>=558&&_y2<=762))&&(K1.numberOfY < 2)){   // K1'e git
                                    
                                    for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                                        if(Board.hamleler.get(i)==3){
                                            Board.hamleler.remove(i);
                                            H1.numberOfX -=1 ; 
                                            K1.numberOfX +=1 ; 
                                            if(K1.numberOfY ==1){
                                                K1.numberOfY = 0 ; 
                                                H3.numberOfY += 1 ; 
                                            }
                                            StdDraw.setPenColor(StdDraw.WHITE) ; 
                                            StdDraw.filledRectangle(650,900,30,50);
                                            StdDraw.setPenColor();
                                            StdDraw.show();
                                            StdDraw.text(650,900,"H1 -> K1");
                                            Files.hamlePrint("H1","K1");
                                            StdDraw.show();
                                            try{
                                                Thread.sleep(300);
                                            }catch(InterruptedException e){}
                                            return ; 
                                        }
                                        else{
                                            continue ; 
                                        }
                                    }
                                }
                                else if(((_x2>=852&&_x2<=910)&&(_y2>=558&&_y2<=762))&&(L1.numberOfY < 2)){      //L1 icin.
                                    
                                    for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                                        if(Board.hamleler.get(i)==4){
                                            Board.hamleler.remove(i);
                                            H1.numberOfX -=1 ; 
                                            L1.numberOfX +=1 ; 
                                            if(L1.numberOfY ==1){
                                                L1.numberOfY = 0 ; 
                                                H3.numberOfY += 1 ; 
                                            }
                                            StdDraw.setPenColor(StdDraw.WHITE) ; 
                                            StdDraw.filledRectangle(650,900,30,50);
                                            StdDraw.setPenColor();
                                            StdDraw.show();
                                            StdDraw.text(650,900,"H1 -> L1");
                                            Files.hamlePrint("H1","L1");
                                            StdDraw.show();
                                            try{
                                                Thread.sleep(300);
                                            }catch(InterruptedException e){}
                                            return ; 
                                        }
                                        else{
                                            continue ; 
                                        }
                                    }
                                }
                                else if(((_x2>=952&&_x2<=994)&&(_y2>=558&&_y2<=762))){    // Topx icin.
                                    for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                                        if(Board.hamleler.get(i)>=5){
                                            Board.hamleler.remove(i);
                                            H1.numberOfX -=1 ; 
                                            TopX.numberOfX +=1 ; 
                                            
                                            StdDraw.setPenColor(StdDraw.WHITE) ; 
                                            StdDraw.filledRectangle(650,900,30,50);
                                            StdDraw.setPenColor();
                                            StdDraw.show();
                                            StdDraw.text(650,900,"H1 -> TopX");
                                            Files.hamlePrint("H1","TopX");
                                            StdDraw.show();
                                            try{
                                                Thread.sleep(300);
                                            }catch(InterruptedException e){}
                                            return ; 
                                        }
                                        else{
                                            continue ; 
                                        }
                                    }
                                }
                                else{
                                    
                                }
                                    
                            }
                        }
                      
                    }
                }
                else if(((_x>=659 && _x<=715)&&(_y>=556 && _y<=761)) && I1.numberOfX > 0){          // from I1.
                    boolean checkI1 = false ; 
                    for(int i = 0 ; i < Board.hamleler.size(); ++i){
                        if(Board.hamleler.get(i)==1){
                            if(J1.numberOfY < 2 ){
                                checkI1 = true  ; 
                            }
                        }
                        else if(Board.hamleler.get(i)==2){
                            if(K1.numberOfY < 2 ){
                                checkI1  =true  ;
                            }
                        }
                        else if(Board.hamleler.get(i)==3){
                            if(L1.numberOfY < 2 ){
                                checkI1 = true  ;
                            }
                        }
                        else if(Board.hamleler.get(i)>=4){
                            checkI1 = true  ;
                        }
                    }// end of for .
                    if(checkI1 == false){
                        StdDraw.setPenColor(StdDraw.RED) ; 
                        StdDraw.text(650,900,"No valid move from I1.");
                        StdDraw.show();
                        StdDraw.setPenColor();
                        try{
                            Thread.sleep(300);
                        }catch(InterruptedException e){}
                        StdDraw.setPenColor(StdDraw.WHITE);
                        StdDraw.filledRectangle(650, 900, 90,50);
                        StdDraw.setPenColor();
                        StdDraw.show();
                    }
                    else{
                        StdDraw.text(650,900,"I1 -> ? ");
                        StdDraw.show();
                        double _x2 ; 
                        double _y2 ; 
                        while(true){                                             // destination loop. from I1 to where ? 
                            if(StdDraw.isMousePressed()){
                                _x2 = StdDraw.mouseX() ; 
                                _y2 = StdDraw.mouseY() ; 
                                if(((_x2>=722&&_x2<=780)&&(_y2>=558&&_y2<=762))&&(J1.numberOfY < 2)){     // J1'e git.
                                    for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                                        if(Board.hamleler.get(i)==1){
                                            Board.hamleler.remove(i);
                                            I1.numberOfX -=1 ; 
                                            J1.numberOfX +=1 ; 
                                            if(J1.numberOfY ==1){
                                                J1.numberOfY = 0 ; 
                                                H3.numberOfY += 1 ; 
                                            }
                                            StdDraw.setPenColor(StdDraw.WHITE) ; 
                                            StdDraw.filledRectangle(650,900,30,50);
                                            StdDraw.setPenColor();
                                            StdDraw.show();
                                            StdDraw.text(650,900,"I1 -> J1");
                                            Files.hamlePrint("I1","J1");
                                            StdDraw.show();
                                            try{
                                                Thread.sleep(300);
                                            }catch(InterruptedException e){}
                                            return ; 
                                        }
                                        else{
                                            continue ; 
                                        }
                                    }
                                }
                                else if(((_x2>=790&&_x2<=842)&&(_y2>=558&&_y2<=762))&&(K1.numberOfY < 2)){   // K1'e git.
                                    
                                    for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                                        if(Board.hamleler.get(i)==2){
                                            Board.hamleler.remove(i);
                                            I1.numberOfX -=1 ; 
                                            K1.numberOfX +=1 ; 
                                            if(K1.numberOfY ==1){
                                                K1.numberOfY = 0 ; 
                                                H3.numberOfY += 1 ; 
                                            }
                                            StdDraw.setPenColor(StdDraw.WHITE) ; 
                                            StdDraw.filledRectangle(650,900,30,50);
                                            StdDraw.setPenColor();
                                            StdDraw.show();
                                            StdDraw.text(650,900,"I1 -> K1");
                                            Files.hamlePrint("I1","K1");
                                            StdDraw.show();
                                            try{
                                                Thread.sleep(300);
                                            }catch(InterruptedException e){}
                                            return ; 
                                        }
                                        else{
                                            continue ; 
                                        }
                                    }
                                }
                                else if(((_x2>=852&&_x2<=910)&&(_y2>=558&&_y2<=762))&&(L1.numberOfY < 2)){   // L1'e git
                                    
                                    for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                                        if(Board.hamleler.get(i)==3){
                                            Board.hamleler.remove(i);
                                            I1.numberOfX -=1 ; 
                                            L1.numberOfX +=1 ; 
                                            if(L1.numberOfY ==1){
                                                L1.numberOfY = 0 ; 
                                                H3.numberOfY += 1 ; 
                                            }
                                            StdDraw.setPenColor(StdDraw.WHITE) ; 
                                            StdDraw.filledRectangle(650,900,30,50);
                                            StdDraw.setPenColor();
                                            StdDraw.show();
                                            StdDraw.text(650,900,"I1 -> L1");
                                            Files.hamlePrint("I1","L1");
                                            StdDraw.show();
                                            try{
                                                Thread.sleep(300);
                                            }catch(InterruptedException e){}
                                            return ; 
                                        }
                                        else{
                                            continue ; 
                                        }
                                    }
                                }
                                else if(((_x2>=952&&_x2<=994)&&(_y2>=558&&_y2<=762))){      //Topx icin.
                                    
                                    for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                                        if(Board.hamleler.get(i)>=4){
                                            Board.hamleler.remove(i);
                                            I1.numberOfX -=1 ; 
                                            TopX.numberOfX +=1 ; 
                                            
                                            StdDraw.setPenColor(StdDraw.WHITE) ; 
                                            StdDraw.filledRectangle(650,900,30,50);
                                            StdDraw.setPenColor();
                                            StdDraw.show();
                                            StdDraw.text(650,900,"I1 -> TopX");
                                            Files.hamlePrint("I1","TopX");
                                            StdDraw.show();
                                            try{
                                                Thread.sleep(300);
                                            }catch(InterruptedException e){}
                                            return ; 
                                        }
                                        else{
                                            continue ; 
                                        }
                                    }
                                }
                                else{
                                    
                                }
                                    
                            }
                        }
                      
                    }
                }
                else if(((_x>=722 && _x<=780)&&(_y>=556 && _y<=761)) && J1.numberOfX > 0){         // from  J1
                    boolean checkJ1 = false ; 
                    for(int i = 0 ; i < Board.hamleler.size(); ++i){
                        if(Board.hamleler.get(i)==1){
                            if(K1.numberOfY < 2 ){
                                checkJ1 = true  ; 
                            }
                        }
                        else if(Board.hamleler.get(i)==2){
                            if(L1.numberOfY < 2 ){
                                checkJ1  =true  ;
                            }
                        }
                        else if(Board.hamleler.get(i)>=3){
                            checkJ1 = true  ;
                        }
                    }// end of for .
                    if(checkJ1 == false){
                        StdDraw.setPenColor(StdDraw.RED) ; 
                        StdDraw.text(650,900,"No valid move from J1.");
                        StdDraw.show();
                        StdDraw.setPenColor();
                        try{
                            Thread.sleep(300);
                        }catch(InterruptedException e){}
                        StdDraw.setPenColor(StdDraw.WHITE);
                        StdDraw.filledRectangle(650, 900, 90,50);
                        StdDraw.setPenColor();
                        StdDraw.show();
                    }
                    else{
                        StdDraw.text(650,900,"J1 -> ? ");
                        StdDraw.show();
                        double _x2 ; 
                        double _y2 ; 
                        while(true){                                             // destination loop. from J1 to where ? 
                            if(StdDraw.isMousePressed()){
                                _x2 = StdDraw.mouseX() ; 
                                _y2 = StdDraw.mouseY() ; 
                                if(((_x2>=790&&_x2<=842)&&(_y2>=558&&_y2<=762))&&(K1.numberOfY < 2)){     // K1'e git.
                                    for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                                        if(Board.hamleler.get(i)==1){
                                            Board.hamleler.remove(i);
                                            J1.numberOfX -=1 ; 
                                            K1.numberOfX +=1 ; 
                                            if(K1.numberOfY ==1){
                                                K1.numberOfY = 0 ; 
                                                H3.numberOfY += 1 ; 
                                            }
                                            StdDraw.setPenColor(StdDraw.WHITE) ; 
                                            StdDraw.filledRectangle(650,900,30,50);
                                            StdDraw.setPenColor();
                                            StdDraw.show();
                                            StdDraw.text(650,900,"J1 -> K1");
                                            Files.hamlePrint("J1","K1");
                                            StdDraw.show();
                                            try{
                                                Thread.sleep(300);
                                            }catch(InterruptedException e){}
                                            return ; 
                                        }
                                        else{
                                            continue ; 
                                        }
                                    }
                                }
                                else if(((_x2>=852&&_x2<=910)&&(_y2>=558&&_y2<=762))&&(L1.numberOfY < 2)){   // L1'e git.
                                    
                                    for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                                        if(Board.hamleler.get(i)==2){
                                            Board.hamleler.remove(i);
                                            J1.numberOfX -=1 ; 
                                            L1.numberOfX +=1 ; 
                                            if(L1.numberOfY ==1){
                                                L1.numberOfY = 0 ; 
                                                H3.numberOfY += 1 ; 
                                            }
                                            StdDraw.setPenColor(StdDraw.WHITE) ; 
                                            StdDraw.filledRectangle(650,900,30,50);
                                            StdDraw.setPenColor();
                                            StdDraw.show();
                                            StdDraw.text(650,900,"J1 -> L1");
                                            Files.hamlePrint("J1","L1");
                                            StdDraw.show();
                                            try{
                                                Thread.sleep(300);
                                            }catch(InterruptedException e){}
                                            return ; 
                                        }
                                        else{
                                            continue ; 
                                        }
                                    }
                                }
                                else if(((_x2>=952&&_x2<=994)&&(_y2>=558&&_y2<=762))){   // TopX'e git
                                    
                                    for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                                        if(Board.hamleler.get(i)>=3){
                                            Board.hamleler.remove(i);
                                            J1.numberOfX -=1 ; 
                                            TopX.numberOfX +=1 ; 
                                            
                                            StdDraw.setPenColor(StdDraw.WHITE) ; 
                                            StdDraw.filledRectangle(650,900,30,50);
                                            StdDraw.setPenColor();
                                            StdDraw.show();
                                            StdDraw.text(650,900,"J1 -> TopX");
                                            Files.hamlePrint("J1","TopX");
                                            StdDraw.show();
                                            try{
                                                Thread.sleep(300);
                                            }catch(InterruptedException e){}
                                            return ; 
                                        }
                                        else{
                                            continue ; 
                                        }
                                    }
                                }

                                else{
                                    
                                }
                                    
                            }
                        }
                      
                    }
                }
                else if(((_x>=790 && _x<=842)&&(_y>=556 && _y<=761)) && K1.numberOfX > 0){          // from K1.
                    boolean checkK1 = false ; 
                    for(int i = 0 ; i < Board.hamleler.size(); ++i){
                        if(Board.hamleler.get(i)==1){
                            if(L1.numberOfY < 2 ){
                                checkK1 = true  ; 
                            }
                        }
                        else if(Board.hamleler.get(i)>=2){
                            checkK1 = true  ;
                        }
                    }// end of for .
                    if(checkK1 == false){
                        StdDraw.setPenColor(StdDraw.RED) ; 
                        StdDraw.text(650,900,"No valid move from K1.");
                        StdDraw.show();
                        StdDraw.setPenColor();
                        try{
                            Thread.sleep(300);
                        }catch(InterruptedException e){}
                        StdDraw.setPenColor(StdDraw.WHITE);
                        StdDraw.filledRectangle(650, 900, 90,50);
                        StdDraw.setPenColor();
                        StdDraw.show();
                    }
                    else{
                        StdDraw.text(650,900,"K1 -> ? ");
                        StdDraw.show();
                        double _x2 ; 
                        double _y2 ; 
                        while(true){                                             // destination loop. from K1 to where ? 
                            if(StdDraw.isMousePressed()){
                                _x2 = StdDraw.mouseX() ; 
                                _y2 = StdDraw.mouseY() ; 
                                if(((_x2>=852&&_x2<=910)&&(_y2>=558&&_y2<=762))&&(L1.numberOfY < 2)){     // L1'e git.
                                    for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                                        if(Board.hamleler.get(i)==1){
                                            Board.hamleler.remove(i);
                                            K1.numberOfX -=1 ; 
                                            L1.numberOfX +=1 ; 
                                            if(L1.numberOfY ==1){
                                                L1.numberOfY = 0 ; 
                                                H3.numberOfY += 1 ; 
                                            }
                                            StdDraw.setPenColor(StdDraw.WHITE) ; 
                                            StdDraw.filledRectangle(650,900,30,50);
                                            StdDraw.setPenColor();
                                            StdDraw.show();
                                            StdDraw.text(650,900,"K1 -> L1");
                                            Files.hamlePrint("K1","L1");
                                            StdDraw.show();
                                            try{
                                                Thread.sleep(300);
                                            }catch(InterruptedException e){}
                                            return ; 
                                        }
                                        else{
                                            continue ; 
                                        }
                                    }
                                }
                                else if(((_x2>=952&&_x2<=994)&&(_y2>=558&&_y2<=762))){   // TopX'e git.
                                    
                                    for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                                        if(Board.hamleler.get(i)>=2){
                                            Board.hamleler.remove(i);
                                            K1.numberOfX -=1 ; 
                                            TopX.numberOfX +=1 ; 
                                            
                                            StdDraw.setPenColor(StdDraw.WHITE) ; 
                                            StdDraw.filledRectangle(650,900,30,50);
                                            StdDraw.setPenColor();
                                            StdDraw.show();
                                            StdDraw.text(650,900,"K1 -> TopX");
                                            Files.hamlePrint("K1","TopX");
                                            StdDraw.show();
                                            try{
                                                Thread.sleep(300);
                                            }catch(InterruptedException e){}
                                            return ; 
                                        }
                                        else{
                                            continue ; 
                                        }
                                    }
                                }


                                else{
                                    
                                }
                                    
                            }
                        }
                      
                    }
                }
                else if(((_x>=852 && _x<=910)&&(_y>=556 && _y<=761)) && L1.numberOfX > 0){   // from L1
                    

                    
                        StdDraw.text(650,900,"L1 ");
                        StdDraw.show();
                        double _x2 ; 
                        double _y2 ; 
                        while(true){                                             // destination loop. from L1 to where ? 
                            if(StdDraw.isMousePressed()){
                                _x2 = StdDraw.mouseX() ; 
                                _y2 = StdDraw.mouseY() ; 
                                if(((_x2>=952&&_x2<=994)&&(_y2>=558&&_y2<=762))&&(L1.numberOfY < 2)){     // TopX'e git.
                                    for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                                        if(Board.hamleler.get(i)>=1){
                                            Board.hamleler.remove(i);
                                            L1.numberOfX -=1 ; 
                                            TopX.numberOfX +=1 ; 
                                            
                                            StdDraw.setPenColor(StdDraw.WHITE) ; 
                                            StdDraw.filledRectangle(650,900,30,50);
                                            StdDraw.setPenColor();
                                            StdDraw.show();
                                            StdDraw.text(650,900,"L1 -> TopX");
                                            Files.hamlePrint("L1","TopX");
                                            StdDraw.show();
                                            try{
                                                Thread.sleep(300);
                                            }catch(InterruptedException e){}
                                            return ; 
                                        }
                                        else{
                                            continue ; 
                                        }
                                    }
                                }


                                else{
                                    
                                }
                                    
                            }
                        }
                      
                    
                }
                }
                else{
                
                }
            }
            
        }
    
    static void userXNormal(){
         
        boolean checkA1=false, checkB1 = false, checkC1 = false, checkD1 = false, checkE1=false, checkF1=false, checkG1=false, checkH1 = false ;  
        boolean checkI1=false, checkJ1=false, checkK1=false, checkL1=false ; 
        boolean checkA5=false, checkB5=false, checkC5=false, checkD5=false, checkE5=false, checkF5=false, checkG5 = false ; 
        boolean checkH5=false, checkI5=false, checkJ5=false, checkK5=false, checkL5=false ; 
        if(L5.numberOfX > 0){
            for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                if(Board.hamleler.get(i)==1){
                    if(K5.numberOfY < 2){
                        checkL5 = true ;  break ; 
                    }
                }
                else if(Board.hamleler.get(i)==2){
                    if(J5.numberOfY < 2 ){
                        checkL5 = true  ; break ; 
                    }
                }
                else if(Board.hamleler.get(i)==3){
                    if(I5.numberOfY< 2 ){
                        checkL5 = true ;  break ; 
                    }
                }
                else if(Board.hamleler.get(i)==4){
                    if(H5.numberOfY < 2 ){
                        checkL5 = true  ; break ; 
                    }
                }
                else if (Board.hamleler.get(i)==5){
                    if(G5.numberOfY < 2 ){
                        checkL5 = true ;  break ; 
                    }
                }
                else{                           // dice is 6 means .
                    if(F5.numberOfY < 2 ){
                        checkL5 = true  ;  break  ;
                    } 
                }
            }
        }
        if(K5.numberOfX >0){
           for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                if(Board.hamleler.get(i)==1){
                    if(J5.numberOfY < 2){
                        checkK5 = true ;  break ; 
                    }
                }
                else if(Board.hamleler.get(i)==2){
                    if(I5.numberOfY < 2 ){
                        checkK5 = true  ; break ; 
                    }
                }
                else if(Board.hamleler.get(i)==3){
                    if(H5.numberOfY< 2 ){
                        checkK5 = true ;  break ; 
                    }
                }
                else if(Board.hamleler.get(i)==4){
                    if(G5.numberOfY < 2 ){
                        checkK5 = true  ; break ; 
                    }
                }
                else if (Board.hamleler.get(i)==5){
                    if(F5.numberOfY < 2 ){
                        checkK5 = true ;  break ; 
                    }
                }
                else{                           // dice is 6 means .
                    if(E5.numberOfY < 2 ){
                        checkK5 = true  ;  break  ;
                    } 
                }
            } 
        }
        if(J5.numberOfX > 0){
            for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                if(Board.hamleler.get(i)==1){
                    if(I5.numberOfY < 2){
                        checkJ5 = true ;  break ; 
                    }
                }
                else if(Board.hamleler.get(i)==2){
                    if(H5.numberOfY < 2 ){
                        checkJ5 = true  ; break ; 
                    }
                }
                else if(Board.hamleler.get(i)==3){
                    if(G5.numberOfY< 2 ){
                        checkJ5 = true ;  break ; 
                    }
                }
                else if(Board.hamleler.get(i)==4){
                    if(F5.numberOfY < 2 ){
                        checkJ5 = true  ; break ; 
                    }
                }
                else if (Board.hamleler.get(i)==5){
                    if(E5.numberOfY < 2 ){
                        checkJ5 = true ;  break ; 
                    }
                }
                else{                           // dice is 6 means .
                    if(D5.numberOfY < 2 ){
                        checkJ5 = true  ;  break  ;
                    } 
                }
            } 
        }
        if(I5.numberOfX > 0){
            for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                if(Board.hamleler.get(i)==1){
                    if(H5.numberOfY < 2){
                        checkI5 = true ;  break ; 
                    }
                }
                else if(Board.hamleler.get(i)==2){
                    if(G5.numberOfY < 2 ){
                        checkI5 = true  ; break ; 
                    }
                }
                else if(Board.hamleler.get(i)==3){
                    if(F5.numberOfY< 2 ){
                        checkI5 = true ;  break ; 
                    }
                }
                else if(Board.hamleler.get(i)==4){
                    if(E5.numberOfY < 2 ){
                        checkI5 = true  ; break ; 
                    }
                }
                else if (Board.hamleler.get(i)==5){
                    if(D5.numberOfY < 2 ){
                        checkI5 = true ;  break ; 
                    }
                }
                else{                           // dice is 6 means .
                    if(C5.numberOfY < 2 ){
                        checkI5 = true  ;  break  ;
                    } 
                }
            } 
        }
        if(H5.numberOfX > 0){
           for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                if(Board.hamleler.get(i)==1){
                    if(G5.numberOfY < 2){
                        checkH5 = true ;  break ; 
                    }
                }
                else if(Board.hamleler.get(i)==2){
                    if(F5.numberOfY < 2 ){
                        checkH5 = true  ; break ; 
                    }
                }
                else if(Board.hamleler.get(i)==3){
                    if(E5.numberOfY< 2 ){
                        checkH5 = true ;  break ; 
                    }
                }
                else if(Board.hamleler.get(i)==4){
                    if(D5.numberOfY < 2 ){
                        checkH5 = true  ; break ; 
                    }
                }
                else if (Board.hamleler.get(i)==5){
                    if(C5.numberOfY < 2 ){
                        checkH5 = true ;  break ; 
                    }
                }
                else{                           // dice is 6 means .
                    if(B5.numberOfY < 2 ){
                        checkH5 = true  ;  break  ;
                    } 
                }
            }  
        }
        if(G5.numberOfX > 0){
            for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                if(Board.hamleler.get(i)==1){
                    if(F5.numberOfY < 2){
                        checkG5 = true ;  break ; 
                    }
                }
                else if(Board.hamleler.get(i)==2){
                    if(E5.numberOfY < 2 ){
                        checkG5 = true  ; break ; 
                    }
                }
                else if(Board.hamleler.get(i)==3){
                    if(D5.numberOfY< 2 ){
                        checkG5 = true ;  break ; 
                    }
                }
                else if(Board.hamleler.get(i)==4){
                    if(C5.numberOfY < 2 ){
                        checkG5 = true  ; break ; 
                    }
                }
                else if (Board.hamleler.get(i)==5){
                    if(B5.numberOfY < 2 ){
                        checkG5 = true ;  break ; 
                    }
                }
                else{                           // dice is 6 means .
                    if(A5.numberOfY < 2 ){
                        checkG5 = true  ;  break  ;
                    } 
                }
            } 
        }
        if(F5.numberOfX > 0){
            for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                if(Board.hamleler.get(i)==1){
                    if(E5.numberOfY < 2){
                        checkF5 = true ;  break ; 
                    }
                }
                else if(Board.hamleler.get(i)==2){
                    if(D5.numberOfY < 2 ){
                        checkF5 = true  ; break ; 
                    }
                }
                else if(Board.hamleler.get(i)==3){
                    if(C5.numberOfY< 2 ){
                        checkF5 = true ;  break ; 
                    }
                }
                else if(Board.hamleler.get(i)==4){
                    if(B5.numberOfY < 2 ){
                        checkF5 = true  ; break ; 
                    }
                }
                else if (Board.hamleler.get(i)==5){
                    if(A5.numberOfY < 2 ){
                        checkF5 = true ;  break ; 
                    }
                }
                else{                           // dice is 6 means .
                    if(A1.numberOfY < 2 ){
                        checkF5 = true  ;  break  ;
                    } 
                }
            } 
        }
        if(E5.numberOfX > 0){
             for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                if(Board.hamleler.get(i)==1){
                    if(D5.numberOfY < 2){
                        checkE5 = true ;  break ; 
                    }
                }
                else if(Board.hamleler.get(i)==2){
                    if(C5.numberOfY < 2 ){
                        checkE5 = true  ; break ; 
                    }
                }
                else if(Board.hamleler.get(i)==3){
                    if(B5.numberOfY< 2 ){
                        checkE5 = true ;  break ; 
                    }
                }
                else if(Board.hamleler.get(i)==4){
                    if(A5.numberOfY < 2 ){
                        checkE5 = true  ; break ; 
                    }
                }
                else if (Board.hamleler.get(i)==5){
                    if(A1.numberOfY < 2 ){
                        checkE5 = true ;  break ; 
                    }
                }
                else{                           // dice is 6 means .
                    if(B1.numberOfY < 2 ){
                        checkE5 = true  ;  break  ;
                    } 
                }
            }
        }
        if(D5.numberOfX > 0){
            for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                if(Board.hamleler.get(i)==1){
                    if(C5.numberOfY < 2){
                        checkD5 = true ;  break ; 
                    }
                }
                else if(Board.hamleler.get(i)==2){
                    if(B5.numberOfY < 2 ){
                        checkD5 = true  ; break ; 
                    }
                }
                else if(Board.hamleler.get(i)==3){
                    if(A5.numberOfY< 2 ){
                        checkD5 = true ;  break ; 
                    }
                }
                else if(Board.hamleler.get(i)==4){
                    if(A1.numberOfY < 2 ){
                        checkD5 = true  ; break ; 
                    }
                }
                else if (Board.hamleler.get(i)==5){
                    if(B1.numberOfY < 2 ){
                        checkD5 = true ;  break ; 
                    }
                }
                else{                           // dice is 6 means .
                    if(C1.numberOfY < 2 ){
                        checkD5 = true  ;  break  ;
                    } 
                }
            }
        }
        if(C5.numberOfX > 0){
           for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                if(Board.hamleler.get(i)==1){
                    if(B5.numberOfY < 2){
                        checkC5 = true ;  break ; 
                    }
                }
                else if(Board.hamleler.get(i)==2){
                    if(A5.numberOfY < 2 ){
                        checkC5 = true  ; break ; 
                    }
                }
                else if(Board.hamleler.get(i)==3){
                    if(A1.numberOfY< 2 ){
                        checkC5 = true ;  break ; 
                    }
                }
                else if(Board.hamleler.get(i)==4){
                    if(B1.numberOfY < 2 ){
                        checkC5 = true  ; break ; 
                    }
                }
                else if (Board.hamleler.get(i)==5){
                    if(C1.numberOfY < 2 ){
                        checkC5 = true ;  break ; 
                    }
                }
                else{                           // dice is 6 means .
                    if(D1.numberOfY < 2 ){
                        checkC5 = true  ;  break  ;
                    } 
                }
            } 
        }
        if(B5.numberOfX > 0){
            for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                if(Board.hamleler.get(i)==1){
                    if(A5.numberOfY < 2){
                        checkB5 = true ;  break ; 
                    }
                }
                else if(Board.hamleler.get(i)==2){
                    if(A1.numberOfY < 2 ){
                        checkB5 = true  ; break ; 
                    }
                }
                else if(Board.hamleler.get(i)==3){
                    if(B1.numberOfY< 2 ){
                        checkB5 = true ;  break ; 
                    }
                }
                else if(Board.hamleler.get(i)==4){
                    if(C1.numberOfY < 2 ){
                        checkB5 = true  ; break ; 
                    }
                }
                else if (Board.hamleler.get(i)==5){
                    if(D1.numberOfY < 2 ){
                        checkB5 = true ;  break ; 
                    }
                }
                else{                           // dice is 6 means .
                    if(E1.numberOfY < 2 ){
                        checkB5 = true  ;  break  ;
                    } 
                }
            }
        }
        if(A5.numberOfX > 0){
            for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                if(Board.hamleler.get(i)==1){
                    if(A1.numberOfY < 2){
                        checkA5 = true ;  break ; 
                    }
                }
                else if(Board.hamleler.get(i)==2){
                    if(B1.numberOfY < 2 ){
                        checkA5 = true  ; break ; 
                    }
                }
                else if(Board.hamleler.get(i)==3){
                    if(C1.numberOfY< 2 ){
                        checkA5 = true ;  break ; 
                    }
                }
                else if(Board.hamleler.get(i)==4){
                    if(D1.numberOfY < 2 ){
                        checkA5 = true  ; break ; 
                    }
                }
                else if (Board.hamleler.get(i)==5){
                    if(E1.numberOfY < 2 ){
                        checkA5 = true ;  break ; 
                    }
                }
                else{                           // dice is 6 means .
                    if(F1.numberOfY < 2 ){
                        checkA5 = true  ;  break  ;
                    } 
                }
            }
        }
        if(A1.numberOfX > 0){
            for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                if(Board.hamleler.get(i)==1){
                    if(B1.numberOfY < 2){
                        checkA1 = true ;  break ; 
                    }
                }
                else if(Board.hamleler.get(i)==2){
                    if(C1.numberOfY < 2 ){
                        checkA1 = true  ; break ; 
                    }
                }
                else if(Board.hamleler.get(i)==3){
                    if(D1.numberOfY< 2 ){
                        checkA1 = true ;  break ; 
                    }
                }
                else if(Board.hamleler.get(i)==4){
                    if(E1.numberOfY < 2 ){
                        checkA1 = true  ; break ; 
                    }
                }
                else if (Board.hamleler.get(i)==5){
                    if(F1.numberOfY < 2 ){
                        checkA1 = true ;  break ; 
                    }
                }
                else{                           // dice is 6 means .
                    if(G1.numberOfY < 2 ){
                        checkA1 = true  ;  break  ;
                    } 
                }
            }
        }
        if(B1.numberOfX >0){
            for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                if(Board.hamleler.get(i)==1){
                    if(C1.numberOfY < 2){
                        checkB1 = true ;  break ; 
                    }
                }
                else if(Board.hamleler.get(i)==2){
                    if(D1.numberOfY < 2 ){
                        checkB1 = true  ; break ; 
                    }
                }
                else if(Board.hamleler.get(i)==3){
                    if(E1.numberOfY< 2 ){
                        checkB1 = true ;  break ; 
                    }
                }
                else if(Board.hamleler.get(i)==4){
                    if(F1.numberOfY < 2 ){
                        checkB1 = true  ; break ; 
                    }
                }
                else if (Board.hamleler.get(i)==5){
                    if(G1.numberOfY < 2 ){
                        checkB1 = true ;  break ; 
                    }
                }
                else{                           // dice is 6 means .
                    if(H1.numberOfY < 2 ){
                        checkB1 = true  ;  break  ;
                    } 
                }
            }
        }
        if(C1.numberOfX > 0){
            for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                if(Board.hamleler.get(i)==1){
                    if(D1.numberOfY < 2){
                        checkC1 = true ;  break ; 
                    }
                }
                else if(Board.hamleler.get(i)==2){
                    if(E1.numberOfY < 2 ){
                        checkC1 = true  ; break ; 
                    }
                }
                else if(Board.hamleler.get(i)==3){
                    if(F1.numberOfY< 2 ){
                        checkC1 = true ;  break ; 
                    }
                }
                else if(Board.hamleler.get(i)==4){
                    if(G1.numberOfY < 2 ){
                        checkC1 = true  ; break ; 
                    }
                }
                else if (Board.hamleler.get(i)==5){
                    if(H1.numberOfY < 2 ){
                        checkC1 = true ;  break ; 
                    }
                }
                else{                           // dice is 6 means .
                    if(I1.numberOfY < 2 ){
                        checkC1 = true  ;  break  ;
                    } 
                }
            }
        }
        if(D1.numberOfX > 0){
            for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                if(Board.hamleler.get(i)==1){
                    if(E1.numberOfY < 2){
                        checkD1 = true ;  break ; 
                    }
                }
                else if(Board.hamleler.get(i)==2){
                    if(F1.numberOfY < 2 ){
                        checkD1 = true  ; break ; 
                    }
                }
                else if(Board.hamleler.get(i)==3){
                    if(G1.numberOfY< 2 ){
                        checkD1 = true ;  break ; 
                    }
                }
                else if(Board.hamleler.get(i)==4){
                    if(H1.numberOfY < 2 ){
                        checkD1 = true  ; break ; 
                    }
                }
                else if (Board.hamleler.get(i)==5){
                    if(I1.numberOfY < 2 ){
                        checkD1 = true ;  break ; 
                    }
                }
                else{                           // dice is 6 means .
                    if(J1.numberOfY < 2 ){
                        checkD1 = true  ;  break  ;
                    } 
                }
            }
        }
        if(E1.numberOfX > 0){
            for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                if(Board.hamleler.get(i)==1){
                    if(F1.numberOfY < 2){
                        checkE1 = true ;  break ; 
                    }
                }
                else if(Board.hamleler.get(i)==2){
                    if(G1.numberOfY < 2 ){
                        checkE1 = true  ; break ; 
                    }
                }
                else if(Board.hamleler.get(i)==3){
                    if(H1.numberOfY< 2 ){
                        checkE1 = true ;  break ; 
                    }
                }
                else if(Board.hamleler.get(i)==4){
                    if(I1.numberOfY < 2 ){
                        checkE1 = true  ; break ; 
                    }
                }
                else if (Board.hamleler.get(i)==5){
                    if(J1.numberOfY < 2 ){
                        checkE1 = true ;  break ; 
                    }
                }
                else{                           // dice is 6 means .
                    if(K1.numberOfY < 2 ){
                        checkE1 = true  ;  break  ;
                    } 
                }
            }
        }
        if(F1.numberOfX > 0){
            for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                if(Board.hamleler.get(i)==1){
                    if(G1.numberOfY < 2){
                        checkF1 = true ;  break ; 
                    }
                }
                else if(Board.hamleler.get(i)==2){
                    if(H1.numberOfY < 2 ){
                        checkF1 = true  ; break ; 
                    }
                }
                else if(Board.hamleler.get(i)==3){
                    if(I1.numberOfY< 2 ){
                        checkF1 = true ;  break ; 
                    }
                }
                else if(Board.hamleler.get(i)==4){
                    if(J1.numberOfY < 2 ){
                        checkF1 = true  ; break ; 
                    }
                }
                else if (Board.hamleler.get(i)==5){
                    if(K1.numberOfY < 2 ){
                        checkF1 = true ;  break ; 
                    }
                }
                else{                           // dice is 6 means .
                    if(L1.numberOfY < 2 ){
                        checkF1 = true  ;  break  ;
                    } 
                }
            }
        }
        if(G1.numberOfX > 0){
            for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                if(Board.hamleler.get(i)==1){
                    if(H1.numberOfY < 2){
                        checkG1 = true ;  break ; 
                    }
                }
                else if(Board.hamleler.get(i)==2){
                    if(I1.numberOfY < 2 ){
                        checkG1 = true  ; break ; 
                    }
                }
                else if(Board.hamleler.get(i)==3){
                    if(J1.numberOfY< 2 ){
                        checkG1 = true ;  break ; 
                    }
                }
                else if(Board.hamleler.get(i)==4){
                    if(K1.numberOfY < 2 ){
                        checkG1 = true  ; break ; 
                    }
                }
                else if (Board.hamleler.get(i)==5){
                    if(L1.numberOfY < 2 ){
                        checkG1 = true ;  break ; 
                    }
                }
                
            }
        }
        if(H1.numberOfX > 0){
           for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                if(Board.hamleler.get(i)==1){
                    if(I1.numberOfY < 2){
                        checkH1 = true ;  break ; 
                    }
                }
                else if(Board.hamleler.get(i)==2){
                    if(J1.numberOfY < 2 ){
                        checkH1 = true  ; break ; 
                    }
                }
                else if(Board.hamleler.get(i)==3){
                    if(K1.numberOfY< 2 ){
                        checkH1 = true ;  break ; 
                    }
                }
                else if(Board.hamleler.get(i)==4){
                    if(L1.numberOfY < 2 ){
                        checkH1 = true  ; break ; 
                    }
                }

            } 
        }
        if(I1.numberOfX > 0){
            for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                if(Board.hamleler.get(i)==1){
                    if(J1.numberOfY < 2){
                        checkI1 = true ;  break ; 
                    }
                }
                else if(Board.hamleler.get(i)==2){
                    if(K1.numberOfY < 2 ){
                        checkI1 = true  ; break ; 
                    }
                }
                else if(Board.hamleler.get(i)==3){
                    if(L1.numberOfY< 2 ){
                        checkI1 = true ;  break ; 
                    }
                }
            } 
        }
        if(J1.numberOfX > 0){
          for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                if(Board.hamleler.get(i)==1){
                    if(K1.numberOfY < 2){
                        checkJ1 = true ;  break ; 
                    }
                }
                else if(Board.hamleler.get(i)==2){
                    if(L1.numberOfY < 2 ){
                        checkJ1 = true  ; break ; 
                    }
                }
            }   
        }
        if(K1.numberOfX > 0){
            for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                if(Board.hamleler.get(i)==1){
                    if(L1.numberOfY < 2){
                        checkK1 = true ;  break ; 
                    }
                }
            }   
        }
        if(L1.numberOfX > 0){
            
        }
        if(!(checkL5 || checkK5 || checkJ5 || checkI5 || checkH5 || checkG5 || checkF5 ||checkE5 || checkD5 || checkC5 || checkB5 || checkA5
                || checkA1 || checkB1 || checkC1 || checkD1 || checkE1 || checkF1 || checkG1 || checkH1 || checkI1 || checkJ1 || checkK1 
                || checkL1)){
            StdDraw.setPenColor(StdDraw.RED);
            StdDraw.text(650,900, "No valid Move");
            StdDraw.setPenColor();
            StdDraw.show();
            try{
                Thread.sleep(300);
            }catch(InterruptedException e){}
            return ; 
        }
          
        StdDraw.text(650, 900, "Please select stone");
        StdDraw.show();
        try{
            Thread.sleep(2000);
        }catch(InterruptedException e){
            
        }        
        StdDraw.setPenColor(StdDraw.WHITE);
        StdDraw.filledRectangle(650, 900, 80, 50);
        StdDraw.show();

        StdDraw.setPenColor();
        //--------------------------------------------------------
        double _x, _y ; 
        while(true){                                  // selec stone loop.
            if(StdDraw.isMousePressed()){
                _x  =StdDraw.mouseX()  ;
                _y = StdDraw.mouseY()  ; 
                if(((_x>=854 && _x<=910)&&(_y>=242 && _y<=444)) && L5.numberOfX > 0){                         // from  L5
                    if(checkL5 == false){
                        StdDraw.setPenColor(StdDraw.RED) ; 
                        StdDraw.text(650,900,"No valid move from L5.");
                        StdDraw.show();
                        StdDraw.setPenColor();
                        try{
                            Thread.sleep(300);
                        }catch(InterruptedException e){}
                        StdDraw.setPenColor(StdDraw.WHITE);
                        StdDraw.filledRectangle(650, 900, 90,50);
                        StdDraw.setPenColor();
                        StdDraw.show();
                    }
                    else{
                        StdDraw.text(650,900,"L5 -> ? ");
                        StdDraw.show();
                        double _x2 ; 
                        double _y2 ; 
                        while(true){                                                    // destination loop.
                            if(StdDraw.isMousePressed()){
                                _x2 = StdDraw.mouseX() ; 
                                _y2 = StdDraw.mouseY() ; 
                                if(((_x2>=788&&_x2<=842)&&(_y2>=242&&_y2<=444))&&(K5.numberOfY < 2)){     // K5'e git.
                                    for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                                        if(Board.hamleler.get(i)==1){
                                            Board.hamleler.remove(i);
                                            L5.numberOfX -=1 ; 
                                            K5.numberOfX +=1 ; 
                                            if(K5.numberOfY ==1){
                                                K5.numberOfY = 0 ; 
                                                H3.numberOfY += 1 ; 
                                            }
                                            StdDraw.setPenColor(StdDraw.WHITE) ; 
                                            StdDraw.filledRectangle(650,900,30,50);
                                            StdDraw.setPenColor();
                                            StdDraw.show();
                                            StdDraw.text(650,900,"L5 -> K5");
                                            Files.hamlePrint("L5","K5");
                                            StdDraw.show();
                                            try{
                                                Thread.sleep(300);
                                            }catch(InterruptedException e){}
                                            return ; 
                                        }
                                        else{
                                            continue ; 
                                        }
                                    }
                                }
                                else if(((_x2>=725&&_x2<=781)&&(_y2>=242&&_y2<=444))&&(J5.numberOfY < 2)){   // J5'e git.
                                    
                                    for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                                        if(Board.hamleler.get(i)==2){
                                            Board.hamleler.remove(i);
                                            L5.numberOfX -=1 ; 
                                            J5.numberOfX +=1 ; 
                                            if(J5.numberOfY ==1){
                                                J5.numberOfY = 0 ; 
                                                H3.numberOfY += 1 ; 
                                            }
                                            StdDraw.setPenColor(StdDraw.WHITE) ; 
                                            StdDraw.filledRectangle(650,900,30,50);
                                            StdDraw.setPenColor();
                                            StdDraw.show();
                                            StdDraw.text(650,900,"L5 -> J5");
                                            Files.hamlePrint("L5","J5");
                                            StdDraw.show();
                                            try{
                                                Thread.sleep(300);
                                            }catch(InterruptedException e){}
                                            return ; 
                                        }
                                        else{
                                            continue ; 
                                        }
                                    }
                                }
                                else if(((_x2>=659&&_x2<=715)&&(_y2>=242&&_y2<=444))&&(I5.numberOfY < 2)){   // I5'e git
                                    
                                    for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                                        if(Board.hamleler.get(i)==3){
                                            Board.hamleler.remove(i);
                                            L5.numberOfX -=1 ; 
                                            I5.numberOfX +=1 ; 
                                            if(I5.numberOfY ==1){
                                                I5.numberOfY = 0 ; 
                                                H3.numberOfY += 1 ; 
                                            }
                                            StdDraw.setPenColor(StdDraw.WHITE) ; 
                                            StdDraw.filledRectangle(650,900,30,50);
                                            StdDraw.setPenColor();
                                            StdDraw.show();
                                            StdDraw.text(650,900,"L5 -> I5");
                                            Files.hamlePrint("L5","I5");
                                            StdDraw.show();
                                            try{
                                                Thread.sleep(300);
                                            }catch(InterruptedException e){}
                                            return ; 
                                        }
                                        else{
                                            continue ; 
                                        }
                                    }
                                }
                                else if(((_x2>=594&&_x2<=647)&&(_y2>=242&&_y2<=444))&&(H5.numberOfY < 2)){      //H5 icin.
                                    
                                    for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                                        if(Board.hamleler.get(i)==4){
                                            Board.hamleler.remove(i);
                                            L5.numberOfX -=1 ; 
                                            H5.numberOfX +=1 ; 
                                            if(H5.numberOfY ==1){
                                                H5.numberOfY = 0 ; 
                                                H3.numberOfY += 1 ; 
                                            }
                                            StdDraw.setPenColor(StdDraw.WHITE) ; 
                                            StdDraw.filledRectangle(650,900,30,50);
                                            StdDraw.setPenColor();
                                            StdDraw.show();
                                            StdDraw.text(650,900,"L5 -> H5");
                                            Files.hamlePrint("L5","H5");
                                            StdDraw.show();
                                            try{
                                                Thread.sleep(300);
                                            }catch(InterruptedException e){}
                                            return ; 
                                        }
                                        else{
                                            continue ; 
                                        }
                                    }
                                }
                                else if(((_x2>=528&&_x2<=583)&&(_y2>=242&&_y2<=444))&&(G5.numberOfY < 2)){    // G5 icin.
                                    for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                                        if(Board.hamleler.get(i)==5){
                                            Board.hamleler.remove(i);
                                            L5.numberOfX -=1 ; 
                                            G5.numberOfX +=1 ; 
                                            if(G5.numberOfY ==1){
                                                G5.numberOfY = 0 ; 
                                                H3.numberOfY += 1 ; 
                                            }
                                            StdDraw.setPenColor(StdDraw.WHITE) ; 
                                            StdDraw.filledRectangle(650,900,30,50);
                                            StdDraw.setPenColor();
                                            StdDraw.show();
                                            StdDraw.text(650,900,"L5 -> G5");
                                            Files.hamlePrint("L5","G5");
                                            StdDraw.show();
                                            try{
                                                Thread.sleep(300);
                                            }catch(InterruptedException e){}
                                            return ; 
                                        }
                                        else{
                                            continue ; 
                                        }
                                    }
                                }
                                else if(((_x2>=419&&_x2<=478)&&(_y2>=242&&_y2<=444))&&(F5.numberOfY < 2)){     // F5 icin
                                    for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                                        if(Board.hamleler.get(i)==6){
                                            Board.hamleler.remove(i);
                                            L5.numberOfX -=1 ; 
                                            F5.numberOfX +=1 ; 
                                            if(F5.numberOfY ==1){
                                                F5.numberOfY = 0 ; 
                                                H3.numberOfY += 1 ; 
                                            }
                                            StdDraw.setPenColor(StdDraw.WHITE) ; 
                                            StdDraw.filledRectangle(650,900,30,50);
                                            StdDraw.setPenColor();
                                            StdDraw.show();
                                            StdDraw.text(650,900,"L5 -> F5");
                                            Files.hamlePrint("L5","F5");
                                            StdDraw.show();
                                            try{
                                                Thread.sleep(300);
                                            }catch(InterruptedException e){}
                                            return ; 
                                        }
                                    }
                                }    
                            }
                        }
                    } 
                }
                else if(((_x>=788 && _x<=842)&&(_y>=242 && _y<=444)) && K5.numberOfX > 0){          // from K5
                    if(checkK5 == false){
                        StdDraw.setPenColor(StdDraw.RED) ; 
                        StdDraw.text(650,900,"No valid move from K5.");
                        StdDraw.show();
                        StdDraw.setPenColor();
                        try{
                            Thread.sleep(300);
                        }catch(InterruptedException e){}
                        StdDraw.setPenColor(StdDraw.WHITE);
                        StdDraw.filledRectangle(650, 900, 90,50);
                        StdDraw.setPenColor();
                        StdDraw.show();
                    }
                    else{
                        StdDraw.text(650,900,"K5 -> ? ");
                        StdDraw.show();
                        double _x2 ; 
                        double _y2 ; 
                        while(true){                                                                // destination loop.
                            if(StdDraw.isMousePressed()){
                                _x2 = StdDraw.mouseX() ; 
                                _y2 = StdDraw.mouseY() ; 
                                if(((_x2>=725&&_x2<=781)&&(_y2>=242&&_y2<=444))&&(J5.numberOfY < 2)){     // J5'e git.
                                    for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                                        if(Board.hamleler.get(i)==1){
                                            Board.hamleler.remove(i);
                                            K5.numberOfX -=1 ; 
                                            J5.numberOfX +=1 ; 
                                            if(J5.numberOfY ==1){
                                                J5.numberOfY = 0 ; 
                                                H3.numberOfY += 1 ; 
                                            }
                                            StdDraw.setPenColor(StdDraw.WHITE) ; 
                                            StdDraw.filledRectangle(650,900,30,50);
                                            StdDraw.setPenColor();
                                            StdDraw.show();
                                            StdDraw.text(650,900,"K5 -> J5");
                                            Files.hamlePrint("K5","J5");
                                            StdDraw.show();
                                            try{
                                                Thread.sleep(300);
                                            }catch(InterruptedException e){}
                                            return ; 
                                        }
                                        else{
                                            continue ; 
                                        }
                                    }
                                }
                                else if(((_x2>=659&&_x2<=715)&&(_y2>=242&&_y2<=444))&&(I5.numberOfY < 2)){   // I5'e git.
                                    
                                    for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                                        if(Board.hamleler.get(i)==2){
                                            Board.hamleler.remove(i);
                                            K5.numberOfX -=1 ; 
                                            I5.numberOfX +=1 ; 
                                            if(I5.numberOfY ==1){
                                                I5.numberOfY = 0 ; 
                                                H3.numberOfY += 1 ; 
                                            }
                                            StdDraw.setPenColor(StdDraw.WHITE) ; 
                                            StdDraw.filledRectangle(650,900,30,50);
                                            StdDraw.setPenColor();
                                            StdDraw.show();
                                            StdDraw.text(650,900,"K5 -> I5");
                                            Files.hamlePrint("K5","I5");
                                            StdDraw.show();
                                            try{
                                                Thread.sleep(300);
                                            }catch(InterruptedException e){}
                                            return ; 
                                        }
                                        else{
                                            continue ; 
                                        }
                                    }
                                }
                                else if(((_x2>=594&&_x2<=647)&&(_y2>=242&&_y2<=444))&&(H5.numberOfY < 2)){   // H5'e git
                                    
                                    for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                                        if(Board.hamleler.get(i)==3){
                                            Board.hamleler.remove(i);
                                            K5.numberOfX -=1 ; 
                                            H5.numberOfX +=1 ; 
                                            if(H5.numberOfY ==1){
                                                H5.numberOfY = 0 ; 
                                                H3.numberOfY += 1 ; 
                                            }
                                            StdDraw.setPenColor(StdDraw.WHITE) ; 
                                            StdDraw.filledRectangle(650,900,30,50);
                                            StdDraw.setPenColor();
                                            StdDraw.show();
                                            StdDraw.text(650,900,"K5 -> H5");
                                            Files.hamlePrint("K5","H5");
                                            StdDraw.show();
                                            try{
                                                Thread.sleep(300);
                                            }catch(InterruptedException e){}
                                            return ; 
                                        }
                                        else{
                                            continue ; 
                                        }
                                    }
                                }
                                else if(((_x2>=528&&_x2<=583)&&(_y2>=242&&_y2<=444))&&(G5.numberOfY < 2)){      //G5 icin.
                                    
                                    for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                                        if(Board.hamleler.get(i)==4){
                                            Board.hamleler.remove(i);
                                            K5.numberOfX -=1 ; 
                                            G5.numberOfX +=1 ; 
                                            if(G5.numberOfY ==1){
                                                G5.numberOfY = 0 ; 
                                                H3.numberOfY += 1 ; 
                                            }
                                            StdDraw.setPenColor(StdDraw.WHITE) ; 
                                            StdDraw.filledRectangle(650,900,30,50);
                                            StdDraw.setPenColor();
                                            StdDraw.show();
                                            StdDraw.text(650,900,"K5 -> G5");
                                            Files.hamlePrint("K5","G5");
                                            StdDraw.show();
                                            try{
                                                Thread.sleep(300);
                                            }catch(InterruptedException e){}
                                            return ; 
                                        }
                                        else{
                                            continue ; 
                                        }
                                    }
                                }
                                else if(((_x2>=419&&_x2<=478)&&(_y2>=242&&_y2<=444))&&(F5.numberOfY < 2)){    // F5 icin.
                                    for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                                        if(Board.hamleler.get(i)==5){
                                            Board.hamleler.remove(i);
                                            K5.numberOfX -=1 ; 
                                            F5.numberOfX +=1 ; 
                                            if(F5.numberOfY ==1){
                                                F5.numberOfY = 0 ; 
                                                H3.numberOfY += 1 ; 
                                            }
                                            StdDraw.setPenColor(StdDraw.WHITE) ; 
                                            StdDraw.filledRectangle(650,900,30,50);
                                            StdDraw.setPenColor();
                                            StdDraw.show();
                                            StdDraw.text(650,900,"K5 -> F5");
                                            Files.hamlePrint("K5","F5");
                                            StdDraw.show();
                                            try{
                                                Thread.sleep(300);
                                            }catch(InterruptedException e){}
                                            return ; 
                                        }
                                        else{
                                            continue ; 
                                        }
                                    }
                                }
                                else if(((_x2>=353&&_x2<=409)&&(_y2>=242&&_y2<=444))&&(E5.numberOfY < 2)){     // E5 icin
                                    for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                                        if(Board.hamleler.get(i)==6){
                                            Board.hamleler.remove(i);
                                            K5.numberOfX -=1 ; 
                                            E5.numberOfX +=1 ; 
                                            if(E5.numberOfY ==1){
                                                E5.numberOfY = 0 ; 
                                                H3.numberOfY += 1 ; 
                                            }
                                            StdDraw.setPenColor(StdDraw.WHITE) ; 
                                            StdDraw.filledRectangle(650,900,30,50);
                                            StdDraw.setPenColor();
                                            StdDraw.show();
                                            StdDraw.text(650,900,"K5 -> E5");
                                            Files.hamlePrint("K5","E5");
                                            StdDraw.show();
                                            try{
                                                Thread.sleep(300);
                                            }catch(InterruptedException e){}
                                            return ; 
                                        }
                                    }
                                }    
                            }
                        }
                    }
                }
                else if(((_x>=725 && _x<=781)&&(_y>=242 && _y<=444)) && J5.numberOfX > 0){         // from J5
                    if(checkJ5 == false){
                        StdDraw.setPenColor(StdDraw.RED) ; 
                        StdDraw.text(650,900,"No valid move from J5.");
                        StdDraw.show();
                        StdDraw.setPenColor();
                        try{
                            Thread.sleep(300);
                        }catch(InterruptedException e){}
                        StdDraw.setPenColor(StdDraw.WHITE);
                        StdDraw.filledRectangle(650, 900, 90,50);
                        StdDraw.setPenColor();
                        StdDraw.show();
                    }
                    else{
                        StdDraw.text(650,900,"J5 -> ? ");
                        StdDraw.show();
                        double _x2 ; 
                        double _y2 ; 
                        while(true){                                                                // destination loop.
                            if(StdDraw.isMousePressed()){
                                _x2 = StdDraw.mouseX() ; 
                                _y2 = StdDraw.mouseY() ; 
                                if(((_x2>=659&&_x2<=715)&&(_y2>=242&&_y2<=444))&&(I5.numberOfY < 2)){     // I5'e git.
                                    for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                                        if(Board.hamleler.get(i)==1){
                                            Board.hamleler.remove(i);
                                            J5.numberOfX -=1 ; 
                                            I5.numberOfX +=1 ; 
                                            if(I5.numberOfY ==1){
                                                I5.numberOfY = 0 ; 
                                                H3.numberOfY += 1 ; 
                                            }
                                            StdDraw.setPenColor(StdDraw.WHITE) ; 
                                            StdDraw.filledRectangle(650,900,30,50);
                                            StdDraw.setPenColor();
                                            StdDraw.show();
                                            StdDraw.text(650,900,"J5 -> I5");
                                            Files.hamlePrint("J5","I5");
                                            StdDraw.show();
                                            try{
                                                Thread.sleep(300);
                                            }catch(InterruptedException e){}
                                            return ; 
                                        }
                                        else{
                                            continue ; 
                                        }
                                    }
                                }
                                else if(((_x2>=594&&_x2<=647)&&(_y2>=242&&_y2<=444))&&(H5.numberOfY < 2)){   // H5'e git.
                                    
                                    for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                                        if(Board.hamleler.get(i)==2){
                                            Board.hamleler.remove(i);
                                            J5.numberOfX -=1 ; 
                                            H5.numberOfX +=1 ; 
                                            if(H5.numberOfY ==1){
                                                H5.numberOfY = 0 ; 
                                                H3.numberOfY += 1 ; 
                                            }
                                            StdDraw.setPenColor(StdDraw.WHITE) ; 
                                            StdDraw.filledRectangle(650,900,30,50);
                                            StdDraw.setPenColor();
                                            StdDraw.show();
                                            StdDraw.text(650,900,"J5 -> H5");
                                            Files.hamlePrint("J5","H5");
                                            StdDraw.show();
                                            try{
                                                Thread.sleep(300);
                                            }catch(InterruptedException e){}
                                            return ; 
                                        }
                                        else{
                                            continue ; 
                                        }
                                    }
                                }
                                else if(((_x2>=528&&_x2<=583)&&(_y2>=242&&_y2<=444))&&(G5.numberOfY < 2)){   // G5'e git
                                    
                                    for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                                        if(Board.hamleler.get(i)==3){
                                            Board.hamleler.remove(i);
                                            J5.numberOfX -=1 ; 
                                            G5.numberOfX +=1 ; 
                                            if(G5.numberOfY ==1){
                                                G5.numberOfY = 0 ; 
                                                H3.numberOfY += 1 ; 
                                            }
                                            StdDraw.setPenColor(StdDraw.WHITE) ; 
                                            StdDraw.filledRectangle(650,900,30,50);
                                            StdDraw.setPenColor();
                                            StdDraw.show();
                                            StdDraw.text(650,900,"J5 -> G5");
                                            Files.hamlePrint("J5","G5");
                                            StdDraw.show();
                                            try{
                                                Thread.sleep(300);
                                            }catch(InterruptedException e){}
                                            return ; 
                                        }
                                        else{
                                            continue ; 
                                        }
                                    }
                                }
                                else if(((_x2>=419&&_x2<=478)&&(_y2>=242&&_y2<=444))&&(F5.numberOfY < 2)){      //F5 icin.
                                    
                                    for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                                        if(Board.hamleler.get(i)==4){
                                            Board.hamleler.remove(i);
                                            J5.numberOfX -=1 ; 
                                            F5.numberOfX +=1 ; 
                                            if(F5.numberOfY ==1){
                                                F5.numberOfY = 0 ; 
                                                H3.numberOfY += 1 ; 
                                            }
                                            StdDraw.setPenColor(StdDraw.WHITE) ; 
                                            StdDraw.filledRectangle(650,900,30,50);
                                            StdDraw.setPenColor();
                                            StdDraw.show();
                                            StdDraw.text(650,900,"J5 -> F5");
                                            Files.hamlePrint("J5","F5");
                                            StdDraw.show();
                                            try{
                                                Thread.sleep(300);
                                            }catch(InterruptedException e){}
                                            return ; 
                                        }
                                        else{
                                            continue ; 
                                        }
                                    }
                                }
                                else if(((_x2>=353&&_x2<=409)&&(_y2>=242&&_y2<=444))&&(E5.numberOfY < 2)){    // E5 icin.
                                    for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                                        if(Board.hamleler.get(i)==5){
                                            Board.hamleler.remove(i);
                                            J5.numberOfX -=1 ; 
                                            E5.numberOfX +=1 ; 
                                            if(E5.numberOfY ==1){
                                                E5.numberOfY = 0 ; 
                                                H3.numberOfY += 1 ; 
                                            }
                                            StdDraw.setPenColor(StdDraw.WHITE) ; 
                                            StdDraw.filledRectangle(650,900,30,50);
                                            StdDraw.setPenColor();
                                            StdDraw.show();
                                            StdDraw.text(650,900,"J5 -> E5");
                                            Files.hamlePrint("J5","E5");
                                            StdDraw.show();
                                            try{
                                                Thread.sleep(300);
                                            }catch(InterruptedException e){}
                                            return ; 
                                        }
                                        else{
                                            continue ; 
                                        }
                                    }
                                }
                                else if(((_x2>=293&&_x2<=343)&&(_y2>=242&&_y2<=444))&&(D5.numberOfY < 2)){     // D5 icin
                                    for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                                        if(Board.hamleler.get(i)==6){
                                            Board.hamleler.remove(i);
                                            J5.numberOfX -=1 ; 
                                            D5.numberOfX +=1 ; 
                                            if(D5.numberOfY ==1){
                                                D5.numberOfY = 0 ; 
                                                H3.numberOfY += 1 ; 
                                            }
                                            StdDraw.setPenColor(StdDraw.WHITE) ; 
                                            StdDraw.filledRectangle(650,900,30,50);
                                            StdDraw.setPenColor();
                                            StdDraw.show();
                                            StdDraw.text(650,900,"J5 -> D5");
                                            Files.hamlePrint("J5","D5");
                                            StdDraw.show();
                                            try{
                                                Thread.sleep(300);
                                            }catch(InterruptedException e){}
                                            return ; 
                                        }
                                    }
                                }    
                            }
                        }
                    }
                }
                else if(((_x>=659 && _x<=715)&&(_y>=242 && _y<=444)) && I5.numberOfX > 0){     // from  I5
                    if(checkI5 == false){
                        StdDraw.setPenColor(StdDraw.RED) ; 
                        StdDraw.text(650,900,"No valid move from I5.");
                        StdDraw.show();
                        StdDraw.setPenColor();
                        try{
                            Thread.sleep(300);
                        }catch(InterruptedException e){}
                        StdDraw.setPenColor(StdDraw.WHITE);
                        StdDraw.filledRectangle(650, 900, 90,50);
                        StdDraw.setPenColor();
                        StdDraw.show();
                    }
                    else{
                        StdDraw.text(650,900,"I5 -> ? ");
                        StdDraw.show();
                        double _x2 ; 
                        double _y2 ; 
                        while(true){                                                                // destination loop.
                            if(StdDraw.isMousePressed()){
                                _x2 = StdDraw.mouseX() ; 
                                _y2 = StdDraw.mouseY() ; 
                                if(((_x2>=594&&_x2<=647)&&(_y2>=242&&_y2<=444))&&(H5.numberOfY < 2)){     // H5'e git.
                                    for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                                        if(Board.hamleler.get(i)==1){
                                            Board.hamleler.remove(i);
                                            I5.numberOfX -=1 ; 
                                            H5.numberOfX +=1 ; 
                                            if(H5.numberOfY ==1){
                                                H5.numberOfY = 0 ; 
                                                H3.numberOfY += 1 ; 
                                            }
                                            StdDraw.setPenColor(StdDraw.WHITE) ; 
                                            StdDraw.filledRectangle(650,900,30,50);
                                            StdDraw.setPenColor();
                                            StdDraw.show();
                                            StdDraw.text(650,900,"I5 -> H5");
                                            Files.hamlePrint("I5","H5");
                                            StdDraw.show();
                                            try{
                                                Thread.sleep(300);
                                            }catch(InterruptedException e){}
                                            return ; 
                                        }
                                        else{
                                            continue ; 
                                        }
                                    }
                                }
                                else if(((_x2>=528&&_x2<=583)&&(_y2>=242&&_y2<=444))&&(G5.numberOfY < 2)){   // G5'e git.
                                    
                                    for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                                        if(Board.hamleler.get(i)==2){
                                            Board.hamleler.remove(i);
                                            I5.numberOfX -=1 ; 
                                            G5.numberOfX +=1 ; 
                                            if(G5.numberOfY ==1){
                                                G5.numberOfY = 0 ; 
                                                H3.numberOfY += 1 ; 
                                            }
                                            StdDraw.setPenColor(StdDraw.WHITE) ; 
                                            StdDraw.filledRectangle(650,900,30,50);
                                            StdDraw.setPenColor();
                                            StdDraw.show();
                                            StdDraw.text(650,900,"I5 -> G5");
                                            Files.hamlePrint("I5","G5");
                                            StdDraw.show();
                                            try{
                                                Thread.sleep(300);
                                            }catch(InterruptedException e){}
                                            return ; 
                                        }
                                        else{
                                            continue ; 
                                        }
                                    }
                                }
                                else if(((_x2>=419&&_x2<=478)&&(_y2>=242&&_y2<=444))&&(F5.numberOfY < 2)){   // F5'e git
                                    
                                    for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                                        if(Board.hamleler.get(i)==3){
                                            Board.hamleler.remove(i);
                                            I5.numberOfX -=1 ; 
                                            F5.numberOfX +=1 ; 
                                            if(F5.numberOfY ==1){
                                                F5.numberOfY = 0 ; 
                                                H3.numberOfY += 1 ; 
                                            }
                                            StdDraw.setPenColor(StdDraw.WHITE) ; 
                                            StdDraw.filledRectangle(650,900,30,50);
                                            StdDraw.setPenColor();
                                            StdDraw.show();
                                            StdDraw.text(650,900,"I5 -> F5");
                                            Files.hamlePrint("I5","F5");
                                            StdDraw.show();
                                            try{
                                                Thread.sleep(300);
                                            }catch(InterruptedException e){}
                                            return ; 
                                        }
                                        else{
                                            continue ; 
                                        }
                                    }
                                }
                                else if(((_x2>=353&&_x2<=478)&&(_y2>=242&&_y2<=444))&&(E5.numberOfY < 2)){      //E5 icin.
                                    
                                    for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                                        if(Board.hamleler.get(i)==4){
                                            Board.hamleler.remove(i);
                                            I5.numberOfX -=1 ; 
                                            E5.numberOfX +=1 ; 
                                            if(E5.numberOfY ==1){
                                                E5.numberOfY = 0 ; 
                                                H3.numberOfY += 1 ; 
                                            }
                                            StdDraw.setPenColor(StdDraw.WHITE) ; 
                                            StdDraw.filledRectangle(650,900,30,50);
                                            StdDraw.setPenColor();
                                            StdDraw.show();
                                            StdDraw.text(650,900,"I5 -> E5");
                                            Files.hamlePrint("I5","E5");
                                            StdDraw.show();
                                            try{
                                                Thread.sleep(300);
                                            }catch(InterruptedException e){}
                                            return ; 
                                        }
                                        else{
                                            continue ; 
                                        }
                                    }
                                }
                                else if(((_x2>=293&&_x2<=343)&&(_y2>=242&&_y2<=444))&&(D5.numberOfY < 2)){    // D5 icin.
                                    for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                                        if(Board.hamleler.get(i)==5){
                                            Board.hamleler.remove(i);
                                            I5.numberOfX -=1 ; 
                                            D5.numberOfX +=1 ; 
                                            if(D5.numberOfY ==1){
                                                D5.numberOfY = 0 ; 
                                                H3.numberOfY += 1 ; 
                                            }
                                            StdDraw.setPenColor(StdDraw.WHITE) ; 
                                            StdDraw.filledRectangle(650,900,30,50);
                                            StdDraw.setPenColor();
                                            StdDraw.show();
                                            StdDraw.text(650,900,"I5 -> D5");
                                            Files.hamlePrint("I5","D5");
                                            StdDraw.show();
                                            try{
                                                Thread.sleep(300);
                                            }catch(InterruptedException e){}
                                            return ; 
                                        }
                                        else{
                                            continue ; 
                                        }
                                    }
                                }
                                else if(((_x2>=223&&_x2<=278)&&(_y2>=242&&_y2<=444))&&(C5.numberOfY < 2)){     // C5 icin
                                    for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                                        if(Board.hamleler.get(i)==6){
                                            Board.hamleler.remove(i);
                                            I5.numberOfX -=1 ; 
                                            C5.numberOfX +=1 ; 
                                            if(C5.numberOfY ==1){
                                                C5.numberOfY = 0 ; 
                                                H3.numberOfY += 1 ; 
                                            }
                                            StdDraw.setPenColor(StdDraw.WHITE) ; 
                                            StdDraw.filledRectangle(650,900,30,50);
                                            StdDraw.setPenColor();
                                            StdDraw.show();
                                            StdDraw.text(650,900,"I5 -> C5");
                                            Files.hamlePrint("I5","C5");
                                            StdDraw.show();
                                            try{
                                                Thread.sleep(300);
                                            }catch(InterruptedException e){}
                                            return ; 
                                        }
                                    }
                                }    
                            }
                        }
                    }
                }
                else if(((_x>=594 && _x<=647)&&(_y>=242 && _y<=444)) && H5.numberOfX > 0){            // from H5
                    if(checkH5 == false){
                        StdDraw.setPenColor(StdDraw.RED) ; 
                        StdDraw.text(650,900,"No valid move from H5.");
                        StdDraw.show();
                        StdDraw.setPenColor();
                        try{
                            Thread.sleep(300);
                        }catch(InterruptedException e){}
                        StdDraw.setPenColor(StdDraw.WHITE);
                        StdDraw.filledRectangle(650, 900, 90,50);
                        StdDraw.setPenColor();
                        StdDraw.show();
                    }
                    else{
                        StdDraw.text(650,900,"H5 -> ? ");
                        StdDraw.show();
                        double _x2 ; 
                        double _y2 ; 
                        while(true){                                                                // destination loop.
                            if(StdDraw.isMousePressed()){
                                _x2 = StdDraw.mouseX() ; 
                                _y2 = StdDraw.mouseY() ; 
                                if(((_x2>=528&&_x2<=583)&&(_y2>=242&&_y2<=444))&&(G5.numberOfY < 2)){     // G5'e git.
                                    for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                                        if(Board.hamleler.get(i)==1){
                                            Board.hamleler.remove(i);
                                            H5.numberOfX -=1 ; 
                                            G5.numberOfX +=1 ; 
                                            if(G5.numberOfY ==1){
                                                G5.numberOfY = 0 ; 
                                                H3.numberOfY += 1 ; 
                                            }
                                            StdDraw.setPenColor(StdDraw.WHITE) ; 
                                            StdDraw.filledRectangle(650,900,30,50);
                                            StdDraw.setPenColor();
                                            StdDraw.show();
                                            StdDraw.text(650,900,"H5 -> G5");
                                            Files.hamlePrint("H5","G5");
                                            StdDraw.show();
                                            try{
                                                Thread.sleep(300);
                                            }catch(InterruptedException e){}
                                            return ; 
                                        }
                                        else{
                                            continue ; 
                                        }
                                    }
                                }
                                else if(((_x2>=419&&_x2<=478)&&(_y2>=242&&_y2<=444))&&(F5.numberOfY < 2)){   // F5'e git.
                                    
                                    for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                                        if(Board.hamleler.get(i)==2){
                                            Board.hamleler.remove(i);
                                            H5.numberOfX -=1 ; 
                                            F5.numberOfX +=1 ; 
                                            if(F5.numberOfY ==1){
                                                F5.numberOfY = 0 ; 
                                                H3.numberOfY += 1 ; 
                                            }
                                            StdDraw.setPenColor(StdDraw.WHITE) ; 
                                            StdDraw.filledRectangle(650,900,30,50);
                                            StdDraw.setPenColor();
                                            StdDraw.show();
                                            StdDraw.text(650,900,"H5 -> F5");
                                            Files.hamlePrint("H5","F5");
                                            StdDraw.show();
                                            try{
                                                Thread.sleep(300);
                                            }catch(InterruptedException e){}
                                            return ; 
                                        }
                                        else{
                                            continue ; 
                                        }
                                    }
                                }
                                else if(((_x2>=353&&_x2<=409)&&(_y2>=242&&_y2<=444))&&(E5.numberOfY < 2)){   // E5'e git
                                    
                                    for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                                        if(Board.hamleler.get(i)==3){
                                            Board.hamleler.remove(i);
                                            H5.numberOfX -=1 ; 
                                            E5.numberOfX +=1 ; 
                                            if(E5.numberOfY ==1){
                                                E5.numberOfY = 0 ; 
                                                H3.numberOfY += 1 ; 
                                            }
                                            StdDraw.setPenColor(StdDraw.WHITE) ; 
                                            StdDraw.filledRectangle(650,900,30,50);
                                            StdDraw.setPenColor();
                                            StdDraw.show();
                                            StdDraw.text(650,900,"H5 -> E5");
                                            Files.hamlePrint("H5","E5");
                                            StdDraw.show();
                                            try{
                                                Thread.sleep(300);
                                            }catch(InterruptedException e){}
                                            return ; 
                                        }
                                        else{
                                            continue ; 
                                        }
                                    }
                                }
                                else if(((_x2>=293&&_x2<=343)&&(_y2>=242&&_y2<=444))&&(D5.numberOfY < 2)){      //D5 icin.
                                    
                                    for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                                        if(Board.hamleler.get(i)==4){
                                            Board.hamleler.remove(i);
                                            H5.numberOfX -=1 ; 
                                            D5.numberOfX +=1 ; 
                                            if(D5.numberOfY ==1){
                                                D5.numberOfY = 0 ; 
                                                H3.numberOfY += 1 ; 
                                            }
                                            StdDraw.setPenColor(StdDraw.WHITE) ; 
                                            StdDraw.filledRectangle(650,900,30,50);
                                            StdDraw.setPenColor();
                                            StdDraw.show();
                                            StdDraw.text(650,900,"H5 -> D5");
                                            Files.hamlePrint("H5","D5");
                                            StdDraw.show();
                                            try{
                                                Thread.sleep(300);
                                            }catch(InterruptedException e){}
                                            return ; 
                                        }
                                        else{
                                            continue ; 
                                        }
                                    }
                                }
                                else if(((_x2>=223&&_x2<=278)&&(_y2>=242&&_y2<=444))&&(C5.numberOfY < 2)){    // C5 icin.
                                    for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                                        if(Board.hamleler.get(i)==5){
                                            Board.hamleler.remove(i);
                                            H5.numberOfX -=1 ; 
                                            C5.numberOfX +=1 ; 
                                            if(C5.numberOfY ==1){
                                                C5.numberOfY = 0 ; 
                                                H3.numberOfY += 1 ; 
                                            }
                                            StdDraw.setPenColor(StdDraw.WHITE) ; 
                                            StdDraw.filledRectangle(650,900,30,50);
                                            StdDraw.setPenColor();
                                            StdDraw.show();
                                            StdDraw.text(650,900,"H5 -> C5");
                                            Files.hamlePrint("H5","C5");
                                            StdDraw.show();
                                            try{
                                                Thread.sleep(300);
                                            }catch(InterruptedException e){}
                                            return ; 
                                        }
                                        else{
                                            continue ; 
                                        }
                                    }
                                }
                                else if(((_x2>=158&&_x2<=215)&&(_y2>=242&&_y2<=444))&&(B5.numberOfY < 2)){     // B5 icin
                                    for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                                        if(Board.hamleler.get(i)==6){
                                            Board.hamleler.remove(i);
                                            H5.numberOfX -=1 ; 
                                            B5.numberOfX +=1 ; 
                                            if(B5.numberOfY ==1){
                                                B5.numberOfY = 0 ; 
                                                H3.numberOfY += 1 ; 
                                            }
                                            StdDraw.setPenColor(StdDraw.WHITE) ; 
                                            StdDraw.filledRectangle(650,900,30,50);
                                            StdDraw.setPenColor();
                                            StdDraw.show();
                                            StdDraw.text(650,900,"H5 -> B5");
                                            Files.hamlePrint("H5","B5");
                                            StdDraw.show();
                                            try{
                                                Thread.sleep(300);
                                            }catch(InterruptedException e){}
                                            return ; 
                                        }
                                    }
                                }    
                            }
                        }
                    }
                }
                else if(((_x>=528 && _x<=583)&&(_y>=242 && _y<=444)) && G5.numberOfX > 0){       // from  G5
                    if(checkG5 == false){
                        StdDraw.setPenColor(StdDraw.RED) ; 
                        StdDraw.text(650,900,"No valid move from G5.");
                        StdDraw.show();
                        StdDraw.setPenColor();
                        try{
                            Thread.sleep(300);
                        }catch(InterruptedException e){}
                        StdDraw.setPenColor(StdDraw.WHITE);
                        StdDraw.filledRectangle(650, 900, 90,50);
                        StdDraw.setPenColor();
                        StdDraw.show();
                    }
                    else{
                        StdDraw.text(650,900,"G5 -> ? ");
                        StdDraw.show();
                        double _x2 ; 
                        double _y2 ; 
                        while(true){                                                                // destination loop.
                            if(StdDraw.isMousePressed()){
                                _x2 = StdDraw.mouseX() ; 
                                _y2 = StdDraw.mouseY() ; 
                                if(((_x2>=419&&_x2<=478)&&(_y2>=242&&_y2<=444))&&(F5.numberOfY < 2)){     // F5'e git.
                                    for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                                        if(Board.hamleler.get(i)==1){
                                            Board.hamleler.remove(i);
                                            G5.numberOfX -=1 ; 
                                            F5.numberOfX +=1 ; 
                                            if(F5.numberOfY ==1){
                                                F5.numberOfY = 0 ; 
                                                H3.numberOfY += 1 ; 
                                            }
                                            StdDraw.setPenColor(StdDraw.WHITE) ; 
                                            StdDraw.filledRectangle(650,900,30,50);
                                            StdDraw.setPenColor();
                                            StdDraw.show();
                                            StdDraw.text(650,900,"G5 -> F5");
                                            Files.hamlePrint("G5","F5");
                                            StdDraw.show();
                                            try{
                                                Thread.sleep(300);
                                            }catch(InterruptedException e){}
                                            return ; 
                                        }
                                        else{
                                            continue ; 
                                        }
                                    }
                                }
                                else if(((_x2>=353&&_x2<=409)&&(_y2>=242&&_y2<=444))&&(E5.numberOfY < 2)){   // E5'e git.
                                    
                                    for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                                        if(Board.hamleler.get(i)==2){
                                            Board.hamleler.remove(i);
                                            G5.numberOfX -=1 ; 
                                            E5.numberOfX +=1 ; 
                                            if(E5.numberOfY ==1){
                                                E5.numberOfY = 0 ; 
                                                H3.numberOfY += 1 ; 
                                            }
                                            StdDraw.setPenColor(StdDraw.WHITE) ; 
                                            StdDraw.filledRectangle(650,900,30,50);
                                            StdDraw.setPenColor();
                                            StdDraw.show();
                                            StdDraw.text(650,900,"G5 -> E5");
                                            Files.hamlePrint("G5","E5");
                                            StdDraw.show();
                                            try{
                                                Thread.sleep(300);
                                            }catch(InterruptedException e){}
                                            return ; 
                                        }
                                        else{
                                            continue ; 
                                        }
                                    }
                                }
                                else if(((_x2>=293&&_x2<=343)&&(_y2>=242&&_y2<=444))&&(D5.numberOfY < 2)){   // D5'e git
                                    
                                    for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                                        if(Board.hamleler.get(i)==3){
                                            Board.hamleler.remove(i);
                                            G5.numberOfX -=1 ; 
                                            D5.numberOfX +=1 ; 
                                            if(D5.numberOfY ==1){
                                                D5.numberOfY = 0 ; 
                                                H3.numberOfY += 1 ; 
                                            }
                                            StdDraw.setPenColor(StdDraw.WHITE) ; 
                                            StdDraw.filledRectangle(650,900,30,50);
                                            StdDraw.setPenColor();
                                            StdDraw.show();
                                            StdDraw.text(650,900,"G5 -> D5");
                                            Files.hamlePrint("G5","D5");
                                            StdDraw.show();
                                            try{
                                                Thread.sleep(300);
                                            }catch(InterruptedException e){}
                                            return ; 
                                        }
                                        else{
                                            continue ; 
                                        }
                                    }
                                }
                                else if(((_x2>=223&&_x2<=278)&&(_y2>=242&&_y2<=444))&&(C5.numberOfY < 2)){      //C5 icin.
                                    
                                    for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                                        if(Board.hamleler.get(i)==4){
                                            Board.hamleler.remove(i);
                                            G5.numberOfX -=1 ; 
                                            C5.numberOfX +=1 ; 
                                            if(C5.numberOfY ==1){
                                                C5.numberOfY = 0 ; 
                                                H3.numberOfY += 1 ; 
                                            }
                                            StdDraw.setPenColor(StdDraw.WHITE) ; 
                                            StdDraw.filledRectangle(650,900,30,50);
                                            StdDraw.setPenColor();
                                            StdDraw.show();
                                            StdDraw.text(650,900,"G5 -> C5");
                                            Files.hamlePrint("G5","C5");
                                            StdDraw.show();
                                            try{
                                                Thread.sleep(300);
                                            }catch(InterruptedException e){}
                                            return ; 
                                        }
                                        else{
                                            continue ; 
                                        }
                                    }
                                }
                                else if(((_x2>=158&&_x2<=215)&&(_y2>=242&&_y2<=444))&&(B5.numberOfY < 2)){    // B5 icin.
                                    for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                                        if(Board.hamleler.get(i)==5){
                                            Board.hamleler.remove(i);
                                            G5.numberOfX -=1 ; 
                                            B5.numberOfX +=1 ; 
                                            if(B5.numberOfY ==1){
                                                B5.numberOfY = 0 ; 
                                                H3.numberOfY += 1 ; 
                                            }
                                            StdDraw.setPenColor(StdDraw.WHITE) ; 
                                            StdDraw.filledRectangle(650,900,30,50);
                                            StdDraw.setPenColor();
                                            StdDraw.show();
                                            StdDraw.text(650,900,"G5 -> B5");
                                            Files.hamlePrint("G5","B5");
                                            StdDraw.show();
                                            try{
                                                Thread.sleep(300);
                                            }catch(InterruptedException e){}
                                            return ; 
                                        }
                                        else{
                                            continue ; 
                                        }
                                    }
                                }
                                else if(((_x2>=92&&_x2<=150)&&(_y2>=242&&_y2<=444))&&(A5.numberOfY < 2)){     // A5 icin
                                    for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                                        if(Board.hamleler.get(i)==6){
                                            Board.hamleler.remove(i);
                                            G5.numberOfX -=1 ; 
                                            A5.numberOfX +=1 ; 
                                            if(A5.numberOfY ==1){
                                                A5.numberOfY = 0 ; 
                                                H3.numberOfY += 1 ; 
                                            }
                                            StdDraw.setPenColor(StdDraw.WHITE) ; 
                                            StdDraw.filledRectangle(650,900,30,50);
                                            StdDraw.setPenColor();
                                            StdDraw.show();
                                            StdDraw.text(650,900,"G5 -> A5");
                                            Files.hamlePrint("G5","A5");
                                            StdDraw.show();
                                            try{
                                                Thread.sleep(300);
                                            }catch(InterruptedException e){}
                                            return ; 
                                        }
                                    }
                                }    
                            }
                        }
                    }
                }
                else if(((_x>=419 && _x<=478)&&(_y>=242 && _y<=444)) && F5.numberOfX > 0){           // from F5
                    if(checkF5 == false){
                        StdDraw.setPenColor(StdDraw.RED) ; 
                        StdDraw.text(650,900,"No valid move from F5.");
                        StdDraw.show();
                        StdDraw.setPenColor();
                        try{
                            Thread.sleep(300);
                        }catch(InterruptedException e){}
                        StdDraw.setPenColor(StdDraw.WHITE);
                        StdDraw.filledRectangle(650, 900, 90,50);
                        StdDraw.setPenColor();
                        StdDraw.show();
                    }
                    else{
                        StdDraw.text(650,900,"F5 -> ? ");
                        StdDraw.show();
                        double _x2 ; 
                        double _y2 ; 
                        while(true){                                                                // destination loop.
                            if(StdDraw.isMousePressed()){
                                _x2 = StdDraw.mouseX() ; 
                                _y2 = StdDraw.mouseY() ; 
                                if(((_x2>=353&&_x2<=409)&&(_y2>=242&&_y2<=444))&&(E5.numberOfY < 2)){     // E5'e git.
                                    for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                                        if(Board.hamleler.get(i)==1){
                                            Board.hamleler.remove(i);
                                            F5.numberOfX -=1 ; 
                                            E5.numberOfX +=1 ; 
                                            if(E5.numberOfY ==1){
                                                E5.numberOfY = 0 ; 
                                                H3.numberOfY += 1 ; 
                                            }
                                            StdDraw.setPenColor(StdDraw.WHITE) ; 
                                            StdDraw.filledRectangle(650,900,30,50);
                                            StdDraw.setPenColor();
                                            StdDraw.show();
                                            StdDraw.text(650,900,"F5 -> E5");
                                            Files.hamlePrint("F5","E5");
                                            StdDraw.show();
                                            try{
                                                Thread.sleep(300);
                                            }catch(InterruptedException e){}
                                            return ; 
                                        }
                                        else{
                                            continue ; 
                                        }
                                    }
                                }
                                else if(((_x2>=293&&_x2<=343)&&(_y2>=242&&_y2<=444))&&(D5.numberOfY < 2)){   // D5'e git.
                                    
                                    for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                                        if(Board.hamleler.get(i)==2){
                                            Board.hamleler.remove(i);
                                            F5.numberOfX -=1 ; 
                                            D5.numberOfX +=1 ; 
                                            if(D5.numberOfY ==1){
                                                D5.numberOfY = 0 ; 
                                                H3.numberOfY += 1 ; 
                                            }
                                            StdDraw.setPenColor(StdDraw.WHITE) ; 
                                            StdDraw.filledRectangle(650,900,30,50);
                                            StdDraw.setPenColor();
                                            StdDraw.show();
                                            StdDraw.text(650,900,"F5 -> D5");
                                            Files.hamlePrint("F5","D5");
                                            StdDraw.show();
                                            try{
                                                Thread.sleep(300);
                                            }catch(InterruptedException e){}
                                            return ; 
                                        }
                                        else{
                                            continue ; 
                                        }
                                    }
                                }
                                else if(((_x2>=223&&_x2<=278)&&(_y2>=242&&_y2<=444))&&(C5.numberOfY < 2)){   // C5'e git
                                    
                                    for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                                        if(Board.hamleler.get(i)==3){
                                            Board.hamleler.remove(i);
                                            F5.numberOfX -=1 ; 
                                            C5.numberOfX +=1 ; 
                                            if(C5.numberOfY ==1){
                                                C5.numberOfY = 0 ; 
                                                H3.numberOfY += 1 ; 
                                            }
                                            StdDraw.setPenColor(StdDraw.WHITE) ; 
                                            StdDraw.filledRectangle(650,900,30,50);
                                            StdDraw.setPenColor();
                                            StdDraw.show();
                                            StdDraw.text(650,900,"F5 -> C5");
                                            Files.hamlePrint("F5","C5");
                                            StdDraw.show();
                                            try{
                                                Thread.sleep(300);
                                            }catch(InterruptedException e){}
                                            return ; 
                                        }
                                        else{
                                            continue ; 
                                        }
                                    }
                                }
                                else if(((_x2>=158&&_x2<=215)&&(_y2>=242&&_y2<=444))&&(B5.numberOfY < 2)){      //B5 icin.
                                    
                                    for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                                        if(Board.hamleler.get(i)==4){
                                            Board.hamleler.remove(i);
                                            F5.numberOfX -=1 ; 
                                            B5.numberOfX +=1 ; 
                                            if(B5.numberOfY ==1){
                                                B5.numberOfY = 0 ; 
                                                H3.numberOfY += 1 ; 
                                            }
                                            StdDraw.setPenColor(StdDraw.WHITE) ; 
                                            StdDraw.filledRectangle(650,900,30,50);
                                            StdDraw.setPenColor();
                                            StdDraw.show();
                                            StdDraw.text(650,900,"F5 -> B5");
                                            Files.hamlePrint("F5","B5");
                                            StdDraw.show();
                                            try{
                                                Thread.sleep(300);
                                            }catch(InterruptedException e){}
                                            return ; 
                                        }
                                        else{
                                            continue ; 
                                        }
                                    }
                                }
                                else if(((_x2>=92&&_x2<=150)&&(_y2>=242&&_y2<=444))&&(A5.numberOfY < 2)){    // A5 icin.
                                    for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                                        if(Board.hamleler.get(i)==5){
                                            Board.hamleler.remove(i);
                                            F5.numberOfX -=1 ; 
                                            A5.numberOfX +=1 ; 
                                            if(A5.numberOfY ==1){
                                                A5.numberOfY = 0 ; 
                                                H3.numberOfY += 1 ; 
                                            }
                                            StdDraw.setPenColor(StdDraw.WHITE) ; 
                                            StdDraw.filledRectangle(650,900,30,50);
                                            StdDraw.setPenColor();
                                            StdDraw.show();
                                            StdDraw.text(650,900,"F5 -> A5");
                                            Files.hamlePrint("F5","A5");
                                            StdDraw.show();
                                            try{
                                                Thread.sleep(300);
                                            }catch(InterruptedException e){}
                                            return ; 
                                        }
                                        else{
                                            continue ; 
                                        }
                                    }
                                }
                                else if(((_x2>=92&&_x2<=150)&&(_y2>=556&&_y2<=757))&&(A1.numberOfY < 2)){     // A1 icin
                                    for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                                        if(Board.hamleler.get(i)==6){
                                            Board.hamleler.remove(i);
                                            F5.numberOfX -=1 ; 
                                            A1.numberOfX +=1 ; 
                                            if(A1.numberOfY ==1){
                                                A1.numberOfY = 0 ; 
                                                H3.numberOfY += 1 ; 
                                            }
                                            StdDraw.setPenColor(StdDraw.WHITE) ; 
                                            StdDraw.filledRectangle(650,900,30,50);
                                            StdDraw.setPenColor();
                                            StdDraw.show();
                                            StdDraw.text(650,900,"F5 -> A1");
                                            Files.hamlePrint("F5","A1");
                                            StdDraw.show();
                                            try{
                                                Thread.sleep(300);
                                            }catch(InterruptedException e){}
                                            return ; 
                                        }
                                    }
                                }    
                            }
                        }
                    }
                }
                else if(((_x>=353 && _x<=409)&&(_y>=242 && _y<=444)) && E5.numberOfX > 0){          // from E5
                    if(checkE5 == false){
                        StdDraw.setPenColor(StdDraw.RED) ; 
                        StdDraw.text(650,900,"No valid move from E5.");
                        StdDraw.show();
                        StdDraw.setPenColor();
                        try{
                            Thread.sleep(300);
                        }catch(InterruptedException e){}
                        StdDraw.setPenColor(StdDraw.WHITE);
                        StdDraw.filledRectangle(650, 900, 90,50);
                        StdDraw.setPenColor();
                        StdDraw.show();
                    }
                    else{
                        StdDraw.text(650,900,"E5 -> ? ");
                        StdDraw.show();
                        double _x2 ; 
                        double _y2 ; 
                        while(true){                                                                // destination loop.
                            if(StdDraw.isMousePressed()){
                                _x2 = StdDraw.mouseX() ; 
                                _y2 = StdDraw.mouseY() ; 
                                if(((_x2>=293&&_x2<=343)&&(_y2>=242&&_y2<=444))&&(D5.numberOfY < 2)){     // D5'e git.
                                    for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                                        if(Board.hamleler.get(i)==1){
                                            Board.hamleler.remove(i);
                                            E5.numberOfX -=1 ; 
                                            D5.numberOfX +=1 ; 
                                            if(D5.numberOfY ==1){
                                                D5.numberOfY = 0 ; 
                                                H3.numberOfY += 1 ; 
                                            }
                                            StdDraw.setPenColor(StdDraw.WHITE) ; 
                                            StdDraw.filledRectangle(650,900,30,50);
                                            StdDraw.setPenColor();
                                            StdDraw.show();
                                            StdDraw.text(650,900,"E5 -> D5");
                                            Files.hamlePrint("E5","D5");
                                            StdDraw.show();
                                            try{
                                                Thread.sleep(300);
                                            }catch(InterruptedException e){}
                                            return ; 
                                        }
                                        else{
                                            continue ; 
                                        }
                                    }
                                }
                                else if(((_x2>=223&&_x2<=278)&&(_y2>=242&&_y2<=444))&&(C5.numberOfY < 2)){   // C5'e git.
                                    
                                    for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                                        if(Board.hamleler.get(i)==2){
                                            Board.hamleler.remove(i);
                                            E5.numberOfX -=1 ; 
                                            C5.numberOfX +=1 ; 
                                            if(C5.numberOfY ==1){
                                                C5.numberOfY = 0 ; 
                                                H3.numberOfY += 1 ; 
                                            }
                                            StdDraw.setPenColor(StdDraw.WHITE) ; 
                                            StdDraw.filledRectangle(650,900,30,50);
                                            StdDraw.setPenColor();
                                            StdDraw.show();
                                            StdDraw.text(650,900,"E5 -> C5");
                                            Files.hamlePrint("E5","C5");
                                            StdDraw.show();
                                            try{
                                                Thread.sleep(300);
                                            }catch(InterruptedException e){}
                                            return ; 
                                        }
                                        else{
                                            continue ; 
                                        }
                                    }
                                }
                                else if(((_x2>=158&&_x2<=215)&&(_y2>=242&&_y2<=444))&&(B5.numberOfY < 2)){   // B5'e git
                                    
                                    for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                                        if(Board.hamleler.get(i)==3){
                                            Board.hamleler.remove(i);
                                            E5.numberOfX -=1 ; 
                                            B5.numberOfX +=1 ; 
                                            if(B5.numberOfY ==1){
                                                B5.numberOfY = 0 ; 
                                                H3.numberOfY += 1 ; 
                                            }
                                            StdDraw.setPenColor(StdDraw.WHITE) ; 
                                            StdDraw.filledRectangle(650,900,30,50);
                                            StdDraw.setPenColor();
                                            StdDraw.show();
                                            StdDraw.text(650,900,"E5 -> B5");
                                            Files.hamlePrint("E5","B5");
                                            StdDraw.show();
                                            try{
                                                Thread.sleep(300);
                                            }catch(InterruptedException e){}
                                            return ; 
                                        }
                                        else{
                                            continue ; 
                                        }
                                    }
                                }
                                else if(((_x2>=92&&_x2<=150)&&(_y2>=242&&_y2<=444))&&(A5.numberOfY < 2)){      //A5 icin.
                                    
                                    for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                                        if(Board.hamleler.get(i)==4){
                                            Board.hamleler.remove(i);
                                            E5.numberOfX -=1 ; 
                                            A5.numberOfX +=1 ; 
                                            if(A5.numberOfY ==1){
                                                A5.numberOfY = 0 ; 
                                                H3.numberOfY += 1 ; 
                                            }
                                            StdDraw.setPenColor(StdDraw.WHITE) ; 
                                            StdDraw.filledRectangle(650,900,30,50);
                                            StdDraw.setPenColor();
                                            StdDraw.show();
                                            StdDraw.text(650,900,"E5 -> A5");
                                            Files.hamlePrint("E5","A5");
                                            StdDraw.show();
                                            try{
                                                Thread.sleep(300);
                                            }catch(InterruptedException e){}
                                            return ; 
                                        }
                                        else{
                                            continue ; 
                                        }
                                    }
                                }
                                else if(((_x2>=92&&_x2<=150)&&(_y2>=556&&_y2<=757))&&(A1.numberOfY < 2)){    // A1 icin.
                                    for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                                        if(Board.hamleler.get(i)==5){
                                            Board.hamleler.remove(i);
                                            E5.numberOfX -=1 ; 
                                            A1.numberOfX +=1 ; 
                                            if(A1.numberOfY ==1){
                                                A1.numberOfY = 0 ; 
                                                H3.numberOfY += 1 ; 
                                            }
                                            StdDraw.setPenColor(StdDraw.WHITE) ; 
                                            StdDraw.filledRectangle(650,900,30,50);
                                            StdDraw.setPenColor();
                                            StdDraw.show();
                                            StdDraw.text(650,900,"E5 -> A1");
                                            Files.hamlePrint("E5","A1");
                                            StdDraw.show();
                                            try{
                                                Thread.sleep(300);
                                            }catch(InterruptedException e){}
                                            return ; 
                                        }
                                        else{
                                            continue ; 
                                        }
                                    }
                                }
                                else if(((_x2>=158&&_x2<=215)&&(_y2>=556&&_y2<=757))&&(B1.numberOfY < 2)){     // B1 icin
                                    for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                                        if(Board.hamleler.get(i)==6){
                                            Board.hamleler.remove(i);
                                            E5.numberOfX -=1 ; 
                                            B1.numberOfX +=1 ; 
                                            if(B1.numberOfY ==1){
                                                B1.numberOfY = 0 ; 
                                                H3.numberOfY += 1 ; 
                                            }
                                            StdDraw.setPenColor(StdDraw.WHITE) ; 
                                            StdDraw.filledRectangle(650,900,30,50);
                                            StdDraw.setPenColor();
                                            StdDraw.show();
                                            StdDraw.text(650,900,"E5 -> B1");
                                            Files.hamlePrint("E5","B1");
                                            StdDraw.show();
                                            try{
                                                Thread.sleep(300);
                                            }catch(InterruptedException e){}
                                            return ; 
                                        }
                                    }
                                }    
                            }
                        }
                    }
                }
                else if(((_x>=293 && _x<=343)&&(_y>=242 && _y<=444)) && D5.numberOfX > 0){       // from D5
                    if(checkD5 == false){
                        StdDraw.setPenColor(StdDraw.RED) ; 
                        StdDraw.text(650,900,"No valid move from D5.");
                        StdDraw.show();
                        StdDraw.setPenColor();
                        try{
                            Thread.sleep(300);
                        }catch(InterruptedException e){}
                        StdDraw.setPenColor(StdDraw.WHITE);
                        StdDraw.filledRectangle(650, 900, 90,50);
                        StdDraw.setPenColor();
                        StdDraw.show();
                    }
                    else{
                        StdDraw.text(650,900,"D5 -> ? ");
                        StdDraw.show();
                        double _x2 ; 
                        double _y2 ; 
                        while(true){                                                                // destination loop.
                            if(StdDraw.isMousePressed()){
                                _x2 = StdDraw.mouseX() ; 
                                _y2 = StdDraw.mouseY() ; 
                                if(((_x2>=223&&_x2<=278)&&(_y2>=242&&_y2<=444))&&(C5.numberOfY < 2)){     // C5'e git.
                                    for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                                        if(Board.hamleler.get(i)==1){
                                            Board.hamleler.remove(i);
                                            D5.numberOfX -=1 ; 
                                            C5.numberOfX +=1 ; 
                                            if(C5.numberOfY ==1){
                                                C5.numberOfY = 0 ; 
                                                H3.numberOfY += 1 ; 
                                            }
                                            StdDraw.setPenColor(StdDraw.WHITE) ; 
                                            StdDraw.filledRectangle(650,900,30,50);
                                            StdDraw.setPenColor();
                                            StdDraw.show();
                                            StdDraw.text(650,900,"D5 -> C5");
                                            Files.hamlePrint("D5","C5");
                                            StdDraw.show();
                                            try{
                                                Thread.sleep(300);
                                            }catch(InterruptedException e){}
                                            return ; 
                                        }
                                        else{
                                            continue ; 
                                        }
                                    }
                                }
                                else if(((_x2>=158&&_x2<=215)&&(_y2>=242&&_y2<=444))&&(B5.numberOfY < 2)){   // B5'e git.
                                    
                                    for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                                        if(Board.hamleler.get(i)==2){
                                            Board.hamleler.remove(i);
                                            D5.numberOfX -=1 ; 
                                            B5.numberOfX +=1 ; 
                                            if(B5.numberOfY ==1){
                                                B5.numberOfY = 0 ; 
                                                H3.numberOfY += 1 ; 
                                            }
                                            StdDraw.setPenColor(StdDraw.WHITE) ; 
                                            StdDraw.filledRectangle(650,900,30,50);
                                            StdDraw.setPenColor();
                                            StdDraw.show();
                                            StdDraw.text(650,900,"D5 -> B5");
                                            Files.hamlePrint("D5","B5");
                                            StdDraw.show();
                                            try{
                                                Thread.sleep(300);
                                            }catch(InterruptedException e){}
                                            return ; 
                                        }
                                        else{
                                            continue ; 
                                        }
                                    }
                                }
                                else if(((_x2>=92&&_x2<=150)&&(_y2>=242&&_y2<=444))&&(A5.numberOfY < 2)){   // A5'e git
                                    
                                    for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                                        if(Board.hamleler.get(i)==3){
                                            Board.hamleler.remove(i);
                                            D5.numberOfX -=1 ; 
                                            A5.numberOfX +=1 ; 
                                            if(A5.numberOfY ==1){
                                                A5.numberOfY = 0 ; 
                                                H3.numberOfY += 1 ; 
                                            }
                                            StdDraw.setPenColor(StdDraw.WHITE) ; 
                                            StdDraw.filledRectangle(650,900,30,50);
                                            StdDraw.setPenColor();
                                            StdDraw.show();
                                            StdDraw.text(650,900,"D5 -> A5");
                                            Files.hamlePrint("D5","A5");
                                            StdDraw.show();
                                            try{
                                                Thread.sleep(300);
                                            }catch(InterruptedException e){}
                                            return ; 
                                        }
                                        else{
                                            continue ; 
                                        }
                                    }
                                }
                                else if(((_x2>=92&&_x2<=150)&&(_y2>=556&&_y2<=757))&&(A1.numberOfY < 2)){      //A1 icin.
                                    
                                    for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                                        if(Board.hamleler.get(i)==4){
                                            Board.hamleler.remove(i);
                                            D5.numberOfX -=1 ; 
                                            A1.numberOfX +=1 ; 
                                            if(A1.numberOfY ==1){
                                                A1.numberOfY = 0 ; 
                                                H3.numberOfY += 1 ; 
                                            }
                                            StdDraw.setPenColor(StdDraw.WHITE) ; 
                                            StdDraw.filledRectangle(650,900,30,50);
                                            StdDraw.setPenColor();
                                            StdDraw.show();
                                            StdDraw.text(650,900,"D5 -> A1");
                                            Files.hamlePrint("D5","A1");
                                            StdDraw.show();
                                            try{
                                                Thread.sleep(300);
                                            }catch(InterruptedException e){}
                                            return ; 
                                        }
                                        else{
                                            continue ; 
                                        }
                                    }
                                }
                                else if(((_x2>=158&&_x2<=215)&&(_y2>=556&&_y2<=757))&&(B1.numberOfY < 2)){    // B1 icin.
                                    for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                                        if(Board.hamleler.get(i)==5){
                                            Board.hamleler.remove(i);
                                            D5.numberOfX -=1 ; 
                                            B1.numberOfX +=1 ; 
                                            if(B1.numberOfY ==1){
                                                B1.numberOfY = 0 ; 
                                                H3.numberOfY += 1 ; 
                                            }
                                            StdDraw.setPenColor(StdDraw.WHITE) ; 
                                            StdDraw.filledRectangle(650,900,30,50);
                                            StdDraw.setPenColor();
                                            StdDraw.show();
                                            StdDraw.text(650,900,"D5 -> B1");
                                            Files.hamlePrint("D5","B1");
                                            StdDraw.show();
                                            try{
                                                Thread.sleep(300);
                                            }catch(InterruptedException e){}
                                            return ; 
                                        }
                                        else{
                                            continue ; 
                                        }
                                    }
                                }
                                else if(((_x2>=223&&_x2<=278)&&(_y2>=556&&_y2<=757))&&(C1.numberOfY < 2)){     // C1 icin
                                    for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                                        if(Board.hamleler.get(i)==6){
                                            Board.hamleler.remove(i);
                                            D5.numberOfX -=1 ; 
                                            C1.numberOfX +=1 ; 
                                            if(C1.numberOfY ==1){
                                                C1.numberOfY = 0 ; 
                                                H3.numberOfY += 1 ; 
                                            }
                                            StdDraw.setPenColor(StdDraw.WHITE) ; 
                                            StdDraw.filledRectangle(650,900,30,50);
                                            StdDraw.setPenColor();
                                            StdDraw.show();
                                            StdDraw.text(650,900,"D5 -> C1");
                                            Files.hamlePrint("D5","C1");
                                            StdDraw.show();
                                            try{
                                                Thread.sleep(300);
                                            }catch(InterruptedException e){}
                                            return ; 
                                        }
                                    }
                                }    
                            }
                        }
                    }
                }
                else if(((_x>=223 && _x<=278)&&(_y>=242 && _y<=444)) && C5.numberOfX > 0){        // from C5
                   if(checkC5 == false){
                        StdDraw.setPenColor(StdDraw.RED) ; 
                        StdDraw.text(650,900,"No valid move from C5.");
                        StdDraw.show();
                        StdDraw.setPenColor();
                        try{
                            Thread.sleep(300);
                        }catch(InterruptedException e){}
                        StdDraw.setPenColor(StdDraw.WHITE);
                        StdDraw.filledRectangle(650, 900, 90,50);
                        StdDraw.setPenColor();
                        StdDraw.show();
                    }
                    else{
                        StdDraw.text(650,900,"C5 -> ? ");
                        StdDraw.show();
                        double _x2 ; 
                        double _y2 ; 
                        while(true){                                                                // destination loop.
                            if(StdDraw.isMousePressed()){
                                _x2 = StdDraw.mouseX() ; 
                                _y2 = StdDraw.mouseY() ; 
                                if(((_x2>=158&&_x2<=215)&&(_y2>=242&&_y2<=444))&&(B5.numberOfY < 2)){     // B5'e git.
                                    for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                                        if(Board.hamleler.get(i)==1){
                                            Board.hamleler.remove(i);
                                            C5.numberOfX -=1 ; 
                                            B5.numberOfX +=1 ; 
                                            if(B5.numberOfY ==1){
                                                B5.numberOfY = 0 ; 
                                                H3.numberOfY += 1 ; 
                                            }
                                            StdDraw.setPenColor(StdDraw.WHITE) ; 
                                            StdDraw.filledRectangle(650,900,30,50);
                                            StdDraw.setPenColor();
                                            StdDraw.show();
                                            StdDraw.text(650,900,"C5 -> B5");
                                            Files.hamlePrint("C5","B5");
                                            StdDraw.show();
                                            try{
                                                Thread.sleep(300);
                                            }catch(InterruptedException e){}
                                            return ; 
                                        }
                                        else{
                                            continue ; 
                                        }
                                    }
                                }
                                else if(((_x2>=92&&_x2<=150)&&(_y2>=242&&_y2<=444))&&(A5.numberOfY < 2)){   // A5'e git.
                                    
                                    for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                                        if(Board.hamleler.get(i)==2){
                                            Board.hamleler.remove(i);
                                            C5.numberOfX -=1 ; 
                                            A5.numberOfX +=1 ; 
                                            if(A5.numberOfY ==1){
                                                A5.numberOfY = 0 ; 
                                                H3.numberOfY += 1 ; 
                                            }
                                            StdDraw.setPenColor(StdDraw.WHITE) ; 
                                            StdDraw.filledRectangle(650,900,30,50);
                                            StdDraw.setPenColor();
                                            StdDraw.show();
                                            StdDraw.text(650,900,"C5 -> A5");
                                            Files.hamlePrint("C5","A5");
                                            StdDraw.show();
                                            try{
                                                Thread.sleep(300);
                                            }catch(InterruptedException e){}
                                            return ; 
                                        }
                                        else{
                                            continue ; 
                                        }
                                    }
                                }
                                else if(((_x2>=92&&_x2<=150)&&(_y2>=556&&_y2<=757))&&(A1.numberOfY < 2)){   // A1'e git
                                    
                                    for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                                        if(Board.hamleler.get(i)==3){
                                            Board.hamleler.remove(i);
                                            C5.numberOfX -=1 ; 
                                            A1.numberOfX +=1 ; 
                                            if(A1.numberOfY ==1){
                                                A1.numberOfY = 0 ; 
                                                H3.numberOfY += 1 ; 
                                            }
                                            StdDraw.setPenColor(StdDraw.WHITE) ; 
                                            StdDraw.filledRectangle(650,900,30,50);
                                            StdDraw.setPenColor();
                                            StdDraw.show();
                                            StdDraw.text(650,900,"C5 -> A1");
                                            Files.hamlePrint("C5","A1");
                                            StdDraw.show();
                                            try{
                                                Thread.sleep(300);
                                            }catch(InterruptedException e){}
                                            return ; 
                                        }
                                        else{
                                            continue ; 
                                        }
                                    }
                                }
                                else if(((_x2>=158&&_x2<=215)&&(_y2>=556&&_y2<=757))&&(B1.numberOfY < 2)){      //B1 icin.
                                    
                                    for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                                        if(Board.hamleler.get(i)==4){
                                            Board.hamleler.remove(i);
                                            C5.numberOfX -=1 ; 
                                            B1.numberOfX +=1 ; 
                                            if(B1.numberOfY ==1){
                                                B1.numberOfY = 0 ; 
                                                H3.numberOfY += 1 ; 
                                            }
                                            StdDraw.setPenColor(StdDraw.WHITE) ; 
                                            StdDraw.filledRectangle(650,900,30,50);
                                            StdDraw.setPenColor();
                                            StdDraw.show();
                                            StdDraw.text(650,900,"C5 -> B1");
                                            Files.hamlePrint("C5","B1");
                                            StdDraw.show();
                                            try{
                                                Thread.sleep(300);
                                            }catch(InterruptedException e){}
                                            return ; 
                                        }
                                        else{
                                            continue ; 
                                        }
                                    }
                                }
                                else if(((_x2>=223&&_x2<=278)&&(_y2>=556&&_y2<=757))&&(C1.numberOfY < 2)){    // C1 icin.
                                    for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                                        if(Board.hamleler.get(i)==5){
                                            Board.hamleler.remove(i);
                                            C5.numberOfX -=1 ; 
                                            C1.numberOfX +=1 ; 
                                            if(C1.numberOfY ==1){
                                                C1.numberOfY = 0 ; 
                                                H3.numberOfY += 1 ; 
                                            }
                                            StdDraw.setPenColor(StdDraw.WHITE) ; 
                                            StdDraw.filledRectangle(650,900,30,50);
                                            StdDraw.setPenColor();
                                            StdDraw.show();
                                            StdDraw.text(650,900,"C5 -> C1");
                                            Files.hamlePrint("C5","C1");
                                            StdDraw.show();
                                            try{
                                                Thread.sleep(300);
                                            }catch(InterruptedException e){}
                                            return ; 
                                        }
                                        else{
                                            continue ; 
                                        }
                                    }
                                }
                                else if(((_x2>=293&&_x2<=343)&&(_y2>=556&&_y2<=757))&&(D1.numberOfY < 2)){     // D1 icin
                                    for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                                        if(Board.hamleler.get(i)==6){
                                            Board.hamleler.remove(i);
                                            C5.numberOfX -=1 ; 
                                            D1.numberOfX +=1 ; 
                                            if(D1.numberOfY ==1){
                                                D1.numberOfY = 0 ; 
                                                H3.numberOfY += 1 ; 
                                            }
                                            StdDraw.setPenColor(StdDraw.WHITE) ; 
                                            StdDraw.filledRectangle(650,900,30,50);
                                            StdDraw.setPenColor();
                                            StdDraw.show();
                                            StdDraw.text(650,900,"C5 -> D1");
                                            Files.hamlePrint("C5","D1");
                                            StdDraw.show();
                                            try{
                                                Thread.sleep(300);
                                            }catch(InterruptedException e){}
                                            return ; 
                                        }
                                    }
                                }    
                            }
                        }
                    } 
                }
                else if(((_x>=158 && _x<=215)&&(_y>=242 && _y<=444)) && B5.numberOfX > 0){       // from B5.
                    if(checkB5 == false){
                        StdDraw.setPenColor(StdDraw.RED) ; 
                        StdDraw.text(650,900,"No valid move from B5.");
                        StdDraw.show();
                        StdDraw.setPenColor();
                        try{
                            Thread.sleep(300);
                        }catch(InterruptedException e){}
                        StdDraw.setPenColor(StdDraw.WHITE);
                        StdDraw.filledRectangle(650, 900, 90,50);
                        StdDraw.setPenColor();
                        StdDraw.show();
                    }
                    else{
                        StdDraw.text(650,900,"B5 -> ? ");
                        StdDraw.show();
                        double _x2 ; 
                        double _y2 ; 
                        while(true){                                                                // destination loop.
                            if(StdDraw.isMousePressed()){
                                _x2 = StdDraw.mouseX() ; 
                                _y2 = StdDraw.mouseY() ; 
                                if(((_x2>=92&&_x2<=150)&&(_y2>=242&&_y2<=444))&&(A5.numberOfY < 2)){     // A5'e git.
                                    for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                                        if(Board.hamleler.get(i)==1){
                                            Board.hamleler.remove(i);
                                            B5.numberOfX -=1 ; 
                                            A5.numberOfX +=1 ; 
                                            if(A5.numberOfY ==1){
                                                A5.numberOfY = 0 ; 
                                                H3.numberOfY += 1 ; 
                                            }
                                            StdDraw.setPenColor(StdDraw.WHITE) ; 
                                            StdDraw.filledRectangle(650,900,30,50);
                                            StdDraw.setPenColor();
                                            StdDraw.show();
                                            StdDraw.text(650,900,"B5 -> A5");
                                            Files.hamlePrint("B5","A5");
                                            StdDraw.show();
                                            try{
                                                Thread.sleep(300);
                                            }catch(InterruptedException e){}
                                            return ; 
                                        }
                                        else{
                                            continue ; 
                                        }
                                    }
                                }
                                else if(((_x2>=92&&_x2<=150)&&(_y2>=556&&_y2<=757))&&(A1.numberOfY < 2)){   // A1'e git.
                                    
                                    for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                                        if(Board.hamleler.get(i)==2){
                                            Board.hamleler.remove(i);
                                            B5.numberOfX -=1 ; 
                                            A1.numberOfX +=1 ; 
                                            if(A1.numberOfY ==1){
                                                A1.numberOfY = 0 ; 
                                                H3.numberOfY += 1 ; 
                                            }
                                            StdDraw.setPenColor(StdDraw.WHITE) ; 
                                            StdDraw.filledRectangle(650,900,30,50);
                                            StdDraw.setPenColor();
                                            StdDraw.show();
                                            StdDraw.text(650,900,"B5 -> A1");
                                            Files.hamlePrint("B5","A1");
                                            StdDraw.show();
                                            try{
                                                Thread.sleep(300);
                                            }catch(InterruptedException e){}
                                            return ; 
                                        }
                                        else{
                                            continue ; 
                                        }
                                    }
                                }
                                else if(((_x2>=158&&_x2<=215)&&(_y2>=556&&_y2<=757))&&(B1.numberOfY < 2)){   // B1'e git
                                    
                                    for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                                        if(Board.hamleler.get(i)==3){
                                            Board.hamleler.remove(i);
                                            B5.numberOfX -=1 ; 
                                            B1.numberOfX +=1 ; 
                                            if(B1.numberOfY ==1){
                                                B1.numberOfY = 0 ; 
                                                H3.numberOfY += 1 ; 
                                            }
                                            StdDraw.setPenColor(StdDraw.WHITE) ; 
                                            StdDraw.filledRectangle(650,900,30,50);
                                            StdDraw.setPenColor();
                                            StdDraw.show();
                                            StdDraw.text(650,900,"B5 -> B1");
                                            Files.hamlePrint("B5","B1");
                                            StdDraw.show();
                                            try{
                                                Thread.sleep(300);
                                            }catch(InterruptedException e){}
                                            return ; 
                                        }
                                        else{
                                            continue ; 
                                        }
                                    }
                                }
                                else if(((_x2>=223&&_x2<=278)&&(_y2>=556&&_y2<=757))&&(C1.numberOfY < 2)){      //C1 icin.
                                    
                                    for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                                        if(Board.hamleler.get(i)==4){
                                            Board.hamleler.remove(i);
                                            B5.numberOfX -=1 ; 
                                            C1.numberOfX +=1 ; 
                                            if(C1.numberOfY ==1){
                                                C1.numberOfY = 0 ; 
                                                H3.numberOfY += 1 ; 
                                            }
                                            StdDraw.setPenColor(StdDraw.WHITE) ; 
                                            StdDraw.filledRectangle(650,900,30,50);
                                            StdDraw.setPenColor();
                                            StdDraw.show();
                                            StdDraw.text(650,900,"B5 -> C1");
                                            Files.hamlePrint("B5","C1");
                                            StdDraw.show();
                                            try{
                                                Thread.sleep(300);
                                            }catch(InterruptedException e){}
                                            return ; 
                                        }
                                        else{
                                            continue ; 
                                        }
                                    }
                                }
                                else if(((_x2>=293&&_x2<=343)&&(_y2>=556&&_y2<=757))&&(D1.numberOfY < 2)){    // D1 icin.
                                    for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                                        if(Board.hamleler.get(i)==5){
                                            Board.hamleler.remove(i);
                                            B5.numberOfX -=1 ; 
                                            D1.numberOfX +=1 ; 
                                            if(D1.numberOfY ==1){
                                                D1.numberOfY = 0 ; 
                                                H3.numberOfY += 1 ; 
                                            }
                                            StdDraw.setPenColor(StdDraw.WHITE) ; 
                                            StdDraw.filledRectangle(650,900,30,50);
                                            StdDraw.setPenColor();
                                            StdDraw.show();
                                            StdDraw.text(650,900,"B5 -> D1");
                                            Files.hamlePrint("B5","D1");
                                            StdDraw.show();
                                            try{
                                                Thread.sleep(300);
                                            }catch(InterruptedException e){}
                                            return ; 
                                        }
                                        else{
                                            continue ; 
                                        }
                                    }
                                }
                                else if(((_x2>=353&&_x2<=406)&&(_y2>=556&&_y2<=757))&&(E1.numberOfY < 2)){     // E1 icin
                                    for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                                        if(Board.hamleler.get(i)==6){
                                            Board.hamleler.remove(i);
                                            B5.numberOfX -=1 ; 
                                            E1.numberOfX +=1 ; 
                                            if(E1.numberOfY ==1){
                                                E1.numberOfY = 0 ; 
                                                H3.numberOfY += 1 ; 
                                            }
                                            StdDraw.setPenColor(StdDraw.WHITE) ; 
                                            StdDraw.filledRectangle(650,900,30,50);
                                            StdDraw.setPenColor();
                                            StdDraw.show();
                                            StdDraw.text(650,900,"B5 -> E1");
                                            Files.hamlePrint("B5","E1");
                                            StdDraw.show();
                                            try{
                                                Thread.sleep(300);
                                            }catch(InterruptedException e){}
                                            return ; 
                                        }
                                    }
                                }    
                            }
                        }
                    } 
                }
                else if(((_x>=92 && _x<=150)&&(_y>=242 && _y<=444)) && A5.numberOfX > 0){      // from A5
                    if(checkA5 == false){
                        StdDraw.setPenColor(StdDraw.RED) ; 
                        StdDraw.text(650,900,"No valid move from A5.");
                        StdDraw.show();
                        StdDraw.setPenColor();
                        try{
                            Thread.sleep(300);
                        }catch(InterruptedException e){}
                        StdDraw.setPenColor(StdDraw.WHITE);
                        StdDraw.filledRectangle(650, 900, 90,50);
                        StdDraw.setPenColor();
                        StdDraw.show();
                    }
                    else{
                        StdDraw.text(650,900,"A5 -> ? ");
                        StdDraw.show();
                        double _x2 ; 
                        double _y2 ; 
                        while(true){                                                                // destination loop.
                            if(StdDraw.isMousePressed()){
                                _x2 = StdDraw.mouseX() ; 
                                _y2 = StdDraw.mouseY() ; 
                                if(((_x2>=92&&_x2<=150)&&(_y2>=556&&_y2<=757))&&(A1.numberOfY < 2)){     // A1'e git.
                                    for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                                        if(Board.hamleler.get(i)==1){
                                            Board.hamleler.remove(i);
                                            A5.numberOfX -=1 ; 
                                            A1.numberOfX +=1 ; 
                                            if(A1.numberOfY ==1){
                                                A1.numberOfY = 0 ; 
                                                H3.numberOfY += 1 ; 
                                            }
                                            StdDraw.setPenColor(StdDraw.WHITE) ; 
                                            StdDraw.filledRectangle(650,900,30,50);
                                            StdDraw.setPenColor();
                                            StdDraw.show();
                                            StdDraw.text(650,900,"A5 -> A1");
                                            Files.hamlePrint("A5","A1");
                                            StdDraw.show();
                                            try{
                                                Thread.sleep(300);
                                            }catch(InterruptedException e){}
                                            return ; 
                                        }
                                        else{
                                            continue ; 
                                        }
                                    }
                                }
                                else if(((_x2>=158&&_x2<=215)&&(_y2>=556&&_y2<=757))&&(B1.numberOfY < 2)){   // B1'e git.
                                    
                                    for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                                        if(Board.hamleler.get(i)==2){
                                            Board.hamleler.remove(i);
                                            A5.numberOfX -=1 ; 
                                            B1.numberOfX +=1 ; 
                                            if(B1.numberOfY ==1){
                                                B1.numberOfY = 0 ; 
                                                H3.numberOfY += 1 ; 
                                            }
                                            StdDraw.setPenColor(StdDraw.WHITE) ; 
                                            StdDraw.filledRectangle(650,900,30,50);
                                            StdDraw.setPenColor();
                                            StdDraw.show();
                                            StdDraw.text(650,900,"A5 -> B1");
                                            Files.hamlePrint("A5","B1");
                                            StdDraw.show();
                                            try{
                                                Thread.sleep(300);
                                            }catch(InterruptedException e){}
                                            return ; 
                                        }
                                        else{
                                            continue ; 
                                        }
                                    }
                                }
                                else if(((_x2>=223&&_x2<=278)&&(_y2>=556&&_y2<=757))&&(C1.numberOfY < 2)){   // C1'e git
                                    
                                    for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                                        if(Board.hamleler.get(i)==3){
                                            Board.hamleler.remove(i);
                                            A5.numberOfX -=1 ; 
                                            C1.numberOfX +=1 ; 
                                            if(C1.numberOfY ==1){
                                                C1.numberOfY = 0 ; 
                                                H3.numberOfY += 1 ; 
                                            }
                                            StdDraw.setPenColor(StdDraw.WHITE) ; 
                                            StdDraw.filledRectangle(650,900,30,50);
                                            StdDraw.setPenColor();
                                            StdDraw.show();
                                            StdDraw.text(650,900,"A5 -> C1");
                                            Files.hamlePrint("A5","C1");
                                            StdDraw.show();
                                            try{
                                                Thread.sleep(300);
                                            }catch(InterruptedException e){}
                                            return ; 
                                        }
                                        else{
                                            continue ; 
                                        }
                                    }
                                }
                                else if(((_x2>=293&&_x2<=343)&&(_y2>=556&&_y2<=757))&&(D1.numberOfY < 2)){      //D1 icin.
                                    
                                    for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                                        if(Board.hamleler.get(i)==4){
                                            Board.hamleler.remove(i);
                                            A5.numberOfX -=1 ; 
                                            D1.numberOfX +=1 ; 
                                            if(D1.numberOfY ==1){
                                                D1.numberOfY = 0 ; 
                                                H3.numberOfY += 1 ; 
                                            }
                                            StdDraw.setPenColor(StdDraw.WHITE) ; 
                                            StdDraw.filledRectangle(650,900,30,50);
                                            StdDraw.setPenColor();
                                            StdDraw.show();
                                            StdDraw.text(650,900,"A5 -> D1");
                                            Files.hamlePrint("A5","D1");
                                            StdDraw.show();
                                            try{
                                                Thread.sleep(300);
                                            }catch(InterruptedException e){}
                                            return ; 
                                        }
                                        else{
                                            continue ; 
                                        }
                                    }
                                }
                                else if(((_x2>=353&&_x2<=409)&&(_y2>=556&&_y2<=757))&&(E1.numberOfY < 2)){    // E1 icin.
                                    for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                                        if(Board.hamleler.get(i)==5){
                                            Board.hamleler.remove(i);
                                            A5.numberOfX -=1 ; 
                                            E1.numberOfX +=1 ; 
                                            if(E1.numberOfY ==1){
                                                E1.numberOfY = 0 ; 
                                                H3.numberOfY += 1 ; 
                                            }
                                            StdDraw.setPenColor(StdDraw.WHITE) ; 
                                            StdDraw.filledRectangle(650,900,30,50);
                                            StdDraw.setPenColor();
                                            StdDraw.show();
                                            StdDraw.text(650,900,"A5 -> E1");
                                            Files.hamlePrint("A5","E1");
                                            StdDraw.show();
                                            try{
                                                Thread.sleep(300);
                                            }catch(InterruptedException e){}
                                            return ; 
                                        }
                                        else{
                                            continue ; 
                                        }
                                    }
                                }
                                else if(((_x2>=419&&_x2<=478)&&(_y2>=556&&_y2<=757))&&(F1.numberOfY < 2)){     // F1 icin
                                    for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                                        if(Board.hamleler.get(i)==6){
                                            Board.hamleler.remove(i);
                                            A5.numberOfX -=1 ; 
                                            F1.numberOfX +=1 ; 
                                            if(F1.numberOfY ==1){
                                                F1.numberOfY = 0 ; 
                                                H3.numberOfY += 1 ; 
                                            }
                                            StdDraw.setPenColor(StdDraw.WHITE) ; 
                                            StdDraw.filledRectangle(650,900,30,50);
                                            StdDraw.setPenColor();
                                            StdDraw.show();
                                            StdDraw.text(650,900,"A5 -> F1");
                                            Files.hamlePrint("A5","F1");
                                            StdDraw.show();
                                            try{
                                                Thread.sleep(300);
                                            }catch(InterruptedException e){}
                                            return ; 
                                        }
                                    }
                                }    
                            }
                        }
                    } 
                }
                else if(((_x>=92 && _x<=150)&&(_y>=556 && _y<=757)) && A1.numberOfX > 0){    // from A1
                    if(checkA1 == false){
                        StdDraw.setPenColor(StdDraw.RED) ; 
                        StdDraw.text(650,900,"No valid move from A1.");
                        StdDraw.show();
                        StdDraw.setPenColor();
                        try{
                            Thread.sleep(300);
                        }catch(InterruptedException e){}
                        StdDraw.setPenColor(StdDraw.WHITE);
                        StdDraw.filledRectangle(650, 900, 90,50);
                        StdDraw.setPenColor();
                        StdDraw.show();
                    }
                    else{
                        StdDraw.text(650,900,"A1 -> ? ");
                        StdDraw.show();
                        double _x2 ; 
                        double _y2 ; 
                        while(true){                                                                // destination loop.
                            if(StdDraw.isMousePressed()){
                                _x2 = StdDraw.mouseX() ; 
                                _y2 = StdDraw.mouseY() ; 
                                if(((_x2>=158&&_x2<=215)&&(_y2>=556&&_y2<=757))&&(B1.numberOfY < 2)){     // B1'e git.
                                    for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                                        if(Board.hamleler.get(i)==1){
                                            Board.hamleler.remove(i);
                                            A1.numberOfX -=1 ; 
                                            B1.numberOfX +=1 ; 
                                            if(B1.numberOfY ==1){
                                                B1.numberOfY = 0 ; 
                                                H3.numberOfY += 1 ; 
                                            }
                                            StdDraw.setPenColor(StdDraw.WHITE) ; 
                                            StdDraw.filledRectangle(650,900,30,50);
                                            StdDraw.setPenColor();
                                            StdDraw.show();
                                            StdDraw.text(650,900,"A1 -> B1");
                                            Files.hamlePrint("A1","B1");
                                            StdDraw.show();
                                            try{
                                                Thread.sleep(300);
                                            }catch(InterruptedException e){}
                                            return ; 
                                        }
                                        else{
                                            continue ; 
                                        }
                                    }
                                }
                                else if(((_x2>=223&&_x2<=278)&&(_y2>=556&&_y2<=757))&&(C1.numberOfY < 2)){   // C1'e git.
                                    
                                    for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                                        if(Board.hamleler.get(i)==2){
                                            Board.hamleler.remove(i);
                                            A1.numberOfX -=1 ; 
                                            C1.numberOfX +=1 ; 
                                            if(C1.numberOfY ==1){
                                                C1.numberOfY = 0 ; 
                                                H3.numberOfY += 1 ; 
                                            }
                                            StdDraw.setPenColor(StdDraw.WHITE) ; 
                                            StdDraw.filledRectangle(650,900,30,50);
                                            StdDraw.setPenColor();
                                            StdDraw.show();
                                            StdDraw.text(650,900,"A1 -> C1");
                                            Files.hamlePrint("A1","C1");
                                            StdDraw.show();
                                            try{
                                                Thread.sleep(300);
                                            }catch(InterruptedException e){}
                                            return ; 
                                        }
                                        else{
                                            continue ; 
                                        }
                                    }
                                }
                                else if(((_x2>=293&&_x2<=343)&&(_y2>=556&&_y2<=757))&&(D1.numberOfY < 2)){   // D1'e git
                                    
                                    for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                                        if(Board.hamleler.get(i)==3){
                                            Board.hamleler.remove(i);
                                            A1.numberOfX -=1 ; 
                                            D1.numberOfX +=1 ; 
                                            if(D1.numberOfY ==1){
                                                D1.numberOfY = 0 ; 
                                                H3.numberOfY += 1 ; 
                                            }
                                            StdDraw.setPenColor(StdDraw.WHITE) ; 
                                            StdDraw.filledRectangle(650,900,30,50);
                                            StdDraw.setPenColor();
                                            StdDraw.show();
                                            StdDraw.text(650,900,"A1 -> D1");
                                            Files.hamlePrint("A1","D1");
                                            StdDraw.show();
                                            try{
                                                Thread.sleep(300);
                                            }catch(InterruptedException e){}
                                            return ; 
                                        }
                                        else{
                                            continue ; 
                                        }
                                    }
                                }
                                else if(((_x2>=353&&_x2<=409)&&(_y2>=556&&_y2<=757))&&(E1.numberOfY < 2)){      //E1 icin.
                                    
                                    for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                                        if(Board.hamleler.get(i)==4){
                                            Board.hamleler.remove(i);
                                            A1.numberOfX -=1 ; 
                                            E1.numberOfX +=1 ; 
                                            if(E1.numberOfY ==1){
                                                E1.numberOfY = 0 ; 
                                                H3.numberOfY += 1 ; 
                                            }
                                            StdDraw.setPenColor(StdDraw.WHITE) ; 
                                            StdDraw.filledRectangle(650,900,30,50);
                                            StdDraw.setPenColor();
                                            StdDraw.show();
                                            StdDraw.text(650,900,"A1 -> E1");
                                            Files.hamlePrint("A1","E1");
                                            StdDraw.show();
                                            try{
                                                Thread.sleep(300);
                                            }catch(InterruptedException e){}
                                            return ; 
                                        }
                                        else{
                                            continue ; 
                                        }
                                    }
                                }
                                else if(((_x2>=419&&_x2<=478)&&(_y2>=556&&_y2<=757))&&(F1.numberOfY < 2)){    // F1 icin.
                                    for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                                        if(Board.hamleler.get(i)==5){
                                            Board.hamleler.remove(i);
                                            A1.numberOfX -=1 ; 
                                            F1.numberOfX +=1 ; 
                                            if(F1.numberOfY ==1){
                                                F1.numberOfY = 0 ; 
                                                H3.numberOfY += 1 ; 
                                            }
                                            StdDraw.setPenColor(StdDraw.WHITE) ; 
                                            StdDraw.filledRectangle(650,900,30,50);
                                            StdDraw.setPenColor();
                                            StdDraw.show();
                                            StdDraw.text(650,900,"A1 -> F1");
                                            Files.hamlePrint("A1","F1");
                                            StdDraw.show();
                                            try{
                                                Thread.sleep(300);
                                            }catch(InterruptedException e){}
                                            return ; 
                                        }
                                        else{
                                            continue ; 
                                        }
                                    }
                                }
                                else if(((_x2>=528&&_x2<=583)&&(_y2>=556&&_y2<=757))&&(G1.numberOfY < 2)){     // G1 icin
                                    for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                                        if(Board.hamleler.get(i)==6){
                                            Board.hamleler.remove(i);
                                            A1.numberOfX -=1 ; 
                                            G1.numberOfX +=1 ; 
                                            if(G1.numberOfY ==1){
                                                G1.numberOfY = 0 ; 
                                                H3.numberOfY += 1 ; 
                                            }
                                            StdDraw.setPenColor(StdDraw.WHITE) ; 
                                            StdDraw.filledRectangle(650,900,30,50);
                                            StdDraw.setPenColor();
                                            StdDraw.show();
                                            StdDraw.text(650,900,"A1 -> G1");
                                            Files.hamlePrint("A1","G1");
                                            StdDraw.show();
                                            try{
                                                Thread.sleep(300);
                                            }catch(InterruptedException e){}
                                            return ; 
                                        }
                                    }
                                }    
                            }
                        }
                    } 
                }
                else if(((_x>=158 && _x<=215)&&(_y>=556 && _y<=757)) && B1.numberOfX > 0){         // from B1
                    if(checkB1 == false){
                        StdDraw.setPenColor(StdDraw.RED) ; 
                        StdDraw.text(650,900,"No valid move from B1.");
                        StdDraw.show();
                        StdDraw.setPenColor();
                        try{
                            Thread.sleep(300);
                        }catch(InterruptedException e){}
                        StdDraw.setPenColor(StdDraw.WHITE);
                        StdDraw.filledRectangle(650, 900, 90,50);
                        StdDraw.setPenColor();
                        StdDraw.show();
                    }
                    else{
                        StdDraw.text(650,900,"B1 -> ? ");
                        StdDraw.show();
                        double _x2 ; 
                        double _y2 ; 
                        while(true){                                                                // destination loop.
                            if(StdDraw.isMousePressed()){
                                _x2 = StdDraw.mouseX() ; 
                                _y2 = StdDraw.mouseY() ; 
                                if(((_x2>=223&&_x2<=278)&&(_y2>=556&&_y2<=757))&&(C1.numberOfY < 2)){     // C1'e git.
                                    for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                                        if(Board.hamleler.get(i)==1){
                                            Board.hamleler.remove(i);
                                            B1.numberOfX -=1 ; 
                                            C1.numberOfX +=1 ; 
                                            if(C1.numberOfY ==1){
                                                C1.numberOfY = 0 ; 
                                                H3.numberOfY += 1 ; 
                                            }
                                            StdDraw.setPenColor(StdDraw.WHITE) ; 
                                            StdDraw.filledRectangle(650,900,30,50);
                                            StdDraw.setPenColor();
                                            StdDraw.show();
                                            StdDraw.text(650,900,"B1 -> C1");
                                            Files.hamlePrint("B1","C1");
                                            StdDraw.show();
                                            try{
                                                Thread.sleep(300);
                                            }catch(InterruptedException e){}
                                            return ; 
                                        }
                                        else{
                                            continue ; 
                                        }
                                    }
                                }
                                else if(((_x2>=293&&_x2<=343)&&(_y2>=556&&_y2<=757))&&(D1.numberOfY < 2)){   // D1'e git.
                                    
                                    for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                                        if(Board.hamleler.get(i)==2){
                                            Board.hamleler.remove(i);
                                            B1.numberOfX -=1 ; 
                                            D1.numberOfX +=1 ; 
                                            if(D1.numberOfY ==1){
                                                D1.numberOfY = 0 ; 
                                                H3.numberOfY += 1 ; 
                                            }
                                            StdDraw.setPenColor(StdDraw.WHITE) ; 
                                            StdDraw.filledRectangle(650,900,30,50);
                                            StdDraw.setPenColor();
                                            StdDraw.show();
                                            StdDraw.text(650,900,"B1 -> D1");
                                            Files.hamlePrint("B1","D1");
                                            StdDraw.show();
                                            try{
                                                Thread.sleep(300);
                                            }catch(InterruptedException e){}
                                            return ; 
                                        }
                                        else{
                                            continue ; 
                                        }
                                    }
                                }
                                else if(((_x2>=353&&_x2<=409)&&(_y2>=556&&_y2<=757))&&(E1.numberOfY < 2)){   // E1'e git
                                    
                                    for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                                        if(Board.hamleler.get(i)==3){
                                            Board.hamleler.remove(i);
                                            B1.numberOfX -=1 ; 
                                            E1.numberOfX +=1 ; 
                                            if(E1.numberOfY ==1){
                                                E1.numberOfY = 0 ; 
                                                H3.numberOfY += 1 ; 
                                            }
                                            StdDraw.setPenColor(StdDraw.WHITE) ; 
                                            StdDraw.filledRectangle(650,900,30,50);
                                            StdDraw.setPenColor();
                                            StdDraw.show();
                                            StdDraw.text(650,900,"B1 -> E1");
                                            Files.hamlePrint("B1","E1");
                                            StdDraw.show();
                                            try{
                                                Thread.sleep(300);
                                            }catch(InterruptedException e){}
                                            return ; 
                                        }
                                        else{
                                            continue ; 
                                        }
                                    }
                                }
                                else if(((_x2>=419&&_x2<=478)&&(_y2>=556&&_y2<=757))&&(F1.numberOfY < 2)){      //F1 icin.
                                    
                                    for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                                        if(Board.hamleler.get(i)==4){
                                            Board.hamleler.remove(i);
                                            B1.numberOfX -=1 ; 
                                            F1.numberOfX +=1 ; 
                                            if(F1.numberOfY ==1){
                                                F1.numberOfY = 0 ; 
                                                H3.numberOfY += 1 ; 
                                            }
                                            StdDraw.setPenColor(StdDraw.WHITE) ; 
                                            StdDraw.filledRectangle(650,900,30,50);
                                            StdDraw.setPenColor();
                                            StdDraw.show();
                                            StdDraw.text(650,900,"B1 -> F1");
                                            Files.hamlePrint("B1","F1");
                                            StdDraw.show();
                                            try{
                                                Thread.sleep(300);
                                            }catch(InterruptedException e){}
                                            return ; 
                                        }
                                        else{
                                            continue ; 
                                        }
                                    }
                                }
                                else if(((_x2>=528&&_x2<=583)&&(_y2>=556&&_y2<=757))&&(G1.numberOfY < 2)){    // G1 icin.
                                    for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                                        if(Board.hamleler.get(i)==5){
                                            Board.hamleler.remove(i);
                                            B1.numberOfX -=1 ; 
                                            G1.numberOfX +=1 ; 
                                            if(G1.numberOfY ==1){
                                                G1.numberOfY = 0 ; 
                                                H3.numberOfY += 1 ; 
                                            }
                                            StdDraw.setPenColor(StdDraw.WHITE) ; 
                                            StdDraw.filledRectangle(650,900,30,50);
                                            StdDraw.setPenColor();
                                            StdDraw.show();
                                            StdDraw.text(650,900,"B1 -> G1");
                                            Files.hamlePrint("B1","G1");
                                            StdDraw.show();
                                            try{
                                                Thread.sleep(300);
                                            }catch(InterruptedException e){}
                                            return ; 
                                        }
                                        else{
                                            continue ; 
                                        }
                                    }
                                }
                                else if(((_x2>=594&&_x2<=647)&&(_y2>=556&&_y2<=757))&&(H1.numberOfY < 2)){     // H1 icin
                                    for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                                        if(Board.hamleler.get(i)==6){
                                            Board.hamleler.remove(i);
                                            B1.numberOfX -=1 ; 
                                            H1.numberOfX +=1 ; 
                                            if(H1.numberOfY ==1){
                                                H1.numberOfY = 0 ; 
                                                H3.numberOfY += 1 ; 
                                            }
                                            StdDraw.setPenColor(StdDraw.WHITE) ; 
                                            StdDraw.filledRectangle(650,900,30,50);
                                            StdDraw.setPenColor();
                                            StdDraw.show();
                                            StdDraw.text(650,900,"B1 -> H1");
                                            Files.hamlePrint("B1","H1");
                                            StdDraw.show();
                                            try{
                                                Thread.sleep(300);
                                            }catch(InterruptedException e){}
                                            return ; 
                                        }
                                    }
                                }    
                            }
                        }
                    } 
                }
                else if(((_x>=223 && _x<=278)&&(_y>=556 && _y<=757)) && C1.numberOfX > 0){           // from C1
                     if(checkC1 == false){
                        StdDraw.setPenColor(StdDraw.RED) ; 
                        StdDraw.text(650,900,"No valid move from C1.");
                        StdDraw.show();
                        StdDraw.setPenColor();
                        try{
                            Thread.sleep(300);
                        }catch(InterruptedException e){}
                        StdDraw.setPenColor(StdDraw.WHITE);
                        StdDraw.filledRectangle(650, 900, 90,50);
                        StdDraw.setPenColor();
                        StdDraw.show();
                    }
                    else{
                        StdDraw.text(650,900,"C1 -> ? ");
                        StdDraw.show();
                        double _x2 ; 
                        double _y2 ; 
                        while(true){                                                                // destination loop.
                            if(StdDraw.isMousePressed()){
                                _x2 = StdDraw.mouseX() ; 
                                _y2 = StdDraw.mouseY() ; 
                                if(((_x2>=293&&_x2<=343)&&(_y2>=556&&_y2<=757))&&(D1.numberOfY < 2)){     // D1'e git.
                                    for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                                        if(Board.hamleler.get(i)==1){
                                            Board.hamleler.remove(i);
                                            C1.numberOfX -=1 ; 
                                            D1.numberOfX +=1 ; 
                                            if(D1.numberOfY ==1){
                                                D1.numberOfY = 0 ; 
                                                H3.numberOfY += 1 ; 
                                            }
                                            StdDraw.setPenColor(StdDraw.WHITE) ; 
                                            StdDraw.filledRectangle(650,900,30,50);
                                            StdDraw.setPenColor();
                                            StdDraw.show();
                                            StdDraw.text(650,900,"C1 -> D1");
                                            Files.hamlePrint("C1","D1");
                                            StdDraw.show();
                                            try{
                                                Thread.sleep(300);
                                            }catch(InterruptedException e){}
                                            return ; 
                                        }
                                        else{
                                            continue ; 
                                        }
                                    }
                                }
                                else if(((_x2>=353&&_x2<=409)&&(_y2>=556&&_y2<=757))&&(E1.numberOfY < 2)){   // E1'e git.
                                    
                                    for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                                        if(Board.hamleler.get(i)==2){
                                            Board.hamleler.remove(i);
                                            C1.numberOfX -=1 ; 
                                            E1.numberOfX +=1 ; 
                                            if(E1.numberOfY ==1){
                                                E1.numberOfY = 0 ; 
                                                H3.numberOfY += 1 ; 
                                            }
                                            StdDraw.setPenColor(StdDraw.WHITE) ; 
                                            StdDraw.filledRectangle(650,900,30,50);
                                            StdDraw.setPenColor();
                                            StdDraw.show();
                                            StdDraw.text(650,900,"C1 -> E1");
                                            Files.hamlePrint("C1","E1");
                                            StdDraw.show();
                                            try{
                                                Thread.sleep(300);
                                            }catch(InterruptedException e){}
                                            return ; 
                                        }
                                        else{
                                            continue ; 
                                        }
                                    }
                                }
                                else if(((_x2>=419&&_x2<=478)&&(_y2>=556&&_y2<=757))&&(F1.numberOfY < 2)){   // F1'e git
                                    
                                    for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                                        if(Board.hamleler.get(i)==3){
                                            Board.hamleler.remove(i);
                                            C1.numberOfX -=1 ; 
                                            F1.numberOfX +=1 ; 
                                            if(F1.numberOfY ==1){
                                                F1.numberOfY = 0 ; 
                                                H3.numberOfY += 1 ; 
                                            }
                                            StdDraw.setPenColor(StdDraw.WHITE) ; 
                                            StdDraw.filledRectangle(650,900,30,50);
                                            StdDraw.setPenColor();
                                            StdDraw.show();
                                            StdDraw.text(650,900,"C1 -> F1");
                                            Files.hamlePrint("C1","F1");
                                            StdDraw.show();
                                            try{
                                                Thread.sleep(300);
                                            }catch(InterruptedException e){}
                                            return ; 
                                        }
                                        else{
                                            continue ; 
                                        }
                                    }
                                }
                                else if(((_x2>=528&&_x2<=583)&&(_y2>=556&&_y2<=757))&&(G1.numberOfY < 2)){      //G1 icin.
                                    
                                    for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                                        if(Board.hamleler.get(i)==4){
                                            Board.hamleler.remove(i);
                                            C1.numberOfX -=1 ; 
                                            G1.numberOfX +=1 ; 
                                            if(G1.numberOfY ==1){
                                                G1.numberOfY = 0 ; 
                                                H3.numberOfY += 1 ; 
                                            }
                                            StdDraw.setPenColor(StdDraw.WHITE) ; 
                                            StdDraw.filledRectangle(650,900,30,50);
                                            StdDraw.setPenColor();
                                            StdDraw.show();
                                            StdDraw.text(650,900,"C1 -> G1");
                                            Files.hamlePrint("C1","G1");
                                            StdDraw.show();
                                            try{
                                                Thread.sleep(300);
                                            }catch(InterruptedException e){}
                                            return ; 
                                        }
                                        else{
                                            continue ; 
                                        }
                                    }
                                }
                                else if(((_x2>=594&&_x2<=647)&&(_y2>=556&&_y2<=757))&&(H1.numberOfY < 2)){    // H1 icin.
                                    for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                                        if(Board.hamleler.get(i)==5){
                                            Board.hamleler.remove(i);
                                            C1.numberOfX -=1 ; 
                                            H1.numberOfX +=1 ; 
                                            if(H1.numberOfY ==1){
                                                H1.numberOfY = 0 ; 
                                                H3.numberOfY += 1 ; 
                                            }
                                            StdDraw.setPenColor(StdDraw.WHITE) ; 
                                            StdDraw.filledRectangle(650,900,30,50);
                                            StdDraw.setPenColor();
                                            StdDraw.show();
                                            StdDraw.text(650,900,"C1 -> H1");
                                            Files.hamlePrint("C1","H1");
                                            StdDraw.show();
                                            try{
                                                Thread.sleep(300);
                                            }catch(InterruptedException e){}
                                            return ; 
                                        }
                                        else{
                                            continue ; 
                                        }
                                    }
                                }
                                else if(((_x2>=659&&_x2<=715)&&(_y2>=556&&_y2<=757))&&(I1.numberOfY < 2)){     // I1 icin
                                    for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                                        if(Board.hamleler.get(i)==6){
                                            Board.hamleler.remove(i);
                                            C1.numberOfX -=1 ; 
                                            I1.numberOfX +=1 ; 
                                            if(I1.numberOfY ==1){
                                                I1.numberOfY = 0 ; 
                                                H3.numberOfY += 1 ; 
                                            }
                                            StdDraw.setPenColor(StdDraw.WHITE) ; 
                                            StdDraw.filledRectangle(650,900,30,50);
                                            StdDraw.setPenColor();
                                            StdDraw.show();
                                            StdDraw.text(650,900,"C1 -> I1");
                                            Files.hamlePrint("C1","I1");
                                            StdDraw.show();
                                            try{
                                                Thread.sleep(300);
                                            }catch(InterruptedException e){}
                                            return ; 
                                        }
                                    }
                                }    
                            }
                        }
                    } 
                }
                else if(((_x>=293 && _x<=343)&&(_y>=556 && _y<=757)) && D1.numberOfX > 0){        // from D1
                    if(checkD1 == false){
                        StdDraw.setPenColor(StdDraw.RED) ; 
                        StdDraw.text(650,900,"No valid move from D1.");
                        StdDraw.show();
                        StdDraw.setPenColor();
                        try{
                            Thread.sleep(300);
                        }catch(InterruptedException e){}
                        StdDraw.setPenColor(StdDraw.WHITE);
                        StdDraw.filledRectangle(650, 900, 90,50);
                        StdDraw.setPenColor();
                        StdDraw.show();
                    }
                    else{
                        StdDraw.text(650,900,"D1 -> ? ");
                        StdDraw.show();
                        double _x2 ; 
                        double _y2 ; 
                        while(true){                                                                // destination loop.
                            if(StdDraw.isMousePressed()){
                                _x2 = StdDraw.mouseX() ; 
                                _y2 = StdDraw.mouseY() ; 
                                if(((_x2>=353&&_x2<=409)&&(_y2>=556&&_y2<=757))&&(E1.numberOfY < 2)){     // E1'e git.
                                    for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                                        if(Board.hamleler.get(i)==1){
                                            Board.hamleler.remove(i);
                                            D1.numberOfX -=1 ; 
                                            E1.numberOfX +=1 ; 
                                            if(E1.numberOfY ==1){
                                                E1.numberOfY = 0 ; 
                                                H3.numberOfY += 1 ; 
                                            }
                                            StdDraw.setPenColor(StdDraw.WHITE) ; 
                                            StdDraw.filledRectangle(650,900,30,50);
                                            StdDraw.setPenColor();
                                            StdDraw.show();
                                            StdDraw.text(650,900,"D1 -> E1");
                                            Files.hamlePrint("D1","E1");
                                            StdDraw.show();
                                            try{
                                                Thread.sleep(300);
                                            }catch(InterruptedException e){}
                                            return ; 
                                        }
                                        else{
                                            continue ; 
                                        }
                                    }
                                }
                                else if(((_x2>=419&&_x2<=478)&&(_y2>=556&&_y2<=757))&&(F1.numberOfY < 2)){   // F1'e git.
                                    
                                    for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                                        if(Board.hamleler.get(i)==2){
                                            Board.hamleler.remove(i);
                                            D1.numberOfX -=1 ; 
                                            F1.numberOfX +=1 ; 
                                            if(F1.numberOfY ==1){
                                                F1.numberOfY = 0 ; 
                                                H3.numberOfY += 1 ; 
                                            }
                                            StdDraw.setPenColor(StdDraw.WHITE) ; 
                                            StdDraw.filledRectangle(650,900,30,50);
                                            StdDraw.setPenColor();
                                            StdDraw.show();
                                            StdDraw.text(650,900,"D1 -> F1");
                                            Files.hamlePrint("D1","F1");
                                            StdDraw.show();
                                            try{
                                                Thread.sleep(300);
                                            }catch(InterruptedException e){}
                                            return ; 
                                        }
                                        else{
                                            continue ; 
                                        }
                                    }
                                }
                                else if(((_x2>=528&&_x2<=583)&&(_y2>=556&&_y2<=757))&&(G1.numberOfY < 2)){   // G1'e git
                                    
                                    for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                                        if(Board.hamleler.get(i)==3){
                                            Board.hamleler.remove(i);
                                            D1.numberOfX -=1 ; 
                                            G1.numberOfX +=1 ; 
                                            if(G1.numberOfY ==1){
                                                G1.numberOfY = 0 ; 
                                                H3.numberOfY += 1 ; 
                                            }
                                            StdDraw.setPenColor(StdDraw.WHITE) ; 
                                            StdDraw.filledRectangle(650,900,30,50);
                                            StdDraw.setPenColor();
                                            StdDraw.show();
                                            StdDraw.text(650,900,"D1 -> G1");
                                            Files.hamlePrint("D1","G1");
                                            StdDraw.show();
                                            try{
                                                Thread.sleep(300);
                                            }catch(InterruptedException e){}
                                            return ; 
                                        }
                                        else{
                                            continue ; 
                                        }
                                    }
                                }
                                else if(((_x2>=594&&_x2<=647)&&(_y2>=556&&_y2<=757))&&(H1.numberOfY < 2)){      //H1 icin.
                                    
                                    for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                                        if(Board.hamleler.get(i)==4){
                                            Board.hamleler.remove(i);
                                            D1.numberOfX -=1 ; 
                                            H1.numberOfX +=1 ; 
                                            if(H1.numberOfY ==1){
                                                H1.numberOfY = 0 ; 
                                                H3.numberOfY += 1 ; 
                                            }
                                            StdDraw.setPenColor(StdDraw.WHITE) ; 
                                            StdDraw.filledRectangle(650,900,30,50);
                                            StdDraw.setPenColor();
                                            StdDraw.show();
                                            StdDraw.text(650,900,"D1 -> H1");
                                            Files.hamlePrint("D1","H1");
                                            StdDraw.show();
                                            try{
                                                Thread.sleep(300);
                                            }catch(InterruptedException e){}
                                            return ; 
                                        }
                                        else{
                                            continue ; 
                                        }
                                    }
                                }
                                else if(((_x2>=659&&_x2<=715)&&(_y2>=556&&_y2<=757))&&(I1.numberOfY < 2)){    // I1 icin.
                                    for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                                        if(Board.hamleler.get(i)==5){
                                            Board.hamleler.remove(i);
                                            D1.numberOfX -=1 ; 
                                            I1.numberOfX +=1 ; 
                                            if(I1.numberOfY ==1){
                                                I1.numberOfY = 0 ; 
                                                H3.numberOfY += 1 ; 
                                            }
                                            StdDraw.setPenColor(StdDraw.WHITE) ; 
                                            StdDraw.filledRectangle(650,900,30,50);
                                            StdDraw.setPenColor();
                                            StdDraw.show();
                                            StdDraw.text(650,900,"D1 -> I1");
                                            Files.hamlePrint("D1","I1");
                                            StdDraw.show();
                                            try{
                                                Thread.sleep(300);
                                            }catch(InterruptedException e){}
                                            return ; 
                                        }
                                        else{
                                            continue ; 
                                        }
                                    }
                                }
                                else if(((_x2>=725&&_x2<=782)&&(_y2>=556&&_y2<=757))&&(J1.numberOfY < 2)){     // J1 icin
                                    for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                                        if(Board.hamleler.get(i)==6){
                                            Board.hamleler.remove(i);
                                            D1.numberOfX -=1 ; 
                                            J1.numberOfX +=1 ; 
                                            if(J1.numberOfY ==1){
                                                J1.numberOfY = 0 ; 
                                                H3.numberOfY += 1 ; 
                                            }
                                            StdDraw.setPenColor(StdDraw.WHITE) ; 
                                            StdDraw.filledRectangle(650,900,30,50);
                                            StdDraw.setPenColor();
                                            StdDraw.show();
                                            StdDraw.text(650,900,"D1 -> J1");
                                            Files.hamlePrint("D1","J1");
                                            StdDraw.show();
                                            try{
                                                Thread.sleep(300);
                                            }catch(InterruptedException e){}
                                            return ; 
                                        }
                                    }
                                }    
                            }
                        }
                    } 
                
                }
                else if(((_x>=353 && _x<=409)&&(_y>=556 && _y<=757)) && E1.numberOfX > 0){              // from E1
                     if(checkE1 == false){
                        StdDraw.setPenColor(StdDraw.RED) ; 
                        StdDraw.text(650,900,"No valid move from E1.");
                        StdDraw.show();
                        StdDraw.setPenColor();
                        try{
                            Thread.sleep(300);
                        }catch(InterruptedException e){}
                        StdDraw.setPenColor(StdDraw.WHITE);
                        StdDraw.filledRectangle(650, 900, 90,50);
                        StdDraw.setPenColor();
                        StdDraw.show();
                    }
                    else{
                        StdDraw.text(650,900,"E1 -> ? ");
                        StdDraw.show();
                        double _x2 ; 
                        double _y2 ; 
                        while(true){                                                                // destination loop.
                            if(StdDraw.isMousePressed()){
                                _x2 = StdDraw.mouseX() ; 
                                _y2 = StdDraw.mouseY() ; 
                                if(((_x2>=419&&_x2<=478)&&(_y2>=556&&_y2<=757))&&(F1.numberOfY < 2)){     // F1'e git.
                                    for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                                        if(Board.hamleler.get(i)==1){
                                            Board.hamleler.remove(i);
                                            E1.numberOfX -=1 ; 
                                            F1.numberOfX +=1 ; 
                                            if(F1.numberOfY ==1){
                                                F1.numberOfY = 0 ; 
                                                H3.numberOfY += 1 ; 
                                            }
                                            StdDraw.setPenColor(StdDraw.WHITE) ; 
                                            StdDraw.filledRectangle(650,900,30,50);
                                            StdDraw.setPenColor();
                                            StdDraw.show();
                                            StdDraw.text(650,900,"E1 -> F1");
                                            Files.hamlePrint("E1","F1");
                                            StdDraw.show();
                                            try{
                                                Thread.sleep(300);
                                            }catch(InterruptedException e){}
                                            return ; 
                                        }
                                        else{
                                            continue ; 
                                        }
                                    }
                                }
                                else if(((_x2>=528&&_x2<=583)&&(_y2>=556&&_y2<=757))&&(G1.numberOfY < 2)){   // G1'e git.
                                    
                                    for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                                        if(Board.hamleler.get(i)==2){
                                            Board.hamleler.remove(i);
                                            E1.numberOfX -=1 ; 
                                            G1.numberOfX +=1 ; 
                                            if(G1.numberOfY ==1){
                                                G1.numberOfY = 0 ; 
                                                H3.numberOfY += 1 ; 
                                            }
                                            StdDraw.setPenColor(StdDraw.WHITE) ; 
                                            StdDraw.filledRectangle(650,900,30,50);
                                            StdDraw.setPenColor();
                                            StdDraw.show();
                                            StdDraw.text(650,900,"E1 -> G1");
                                            Files.hamlePrint("E1","G1");
                                            StdDraw.show();
                                            try{
                                                Thread.sleep(300);
                                            }catch(InterruptedException e){}
                                            return ; 
                                        }
                                        else{
                                            continue ; 
                                        }
                                    }
                                }
                                else if(((_x2>=594&&_x2<=647)&&(_y2>=556&&_y2<=757))&&(H1.numberOfY < 2)){   // H1'e git
                                    
                                    for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                                        if(Board.hamleler.get(i)==3){
                                            Board.hamleler.remove(i);
                                            E1.numberOfX -=1 ; 
                                            H1.numberOfX +=1 ; 
                                            if(H1.numberOfY ==1){
                                                H1.numberOfY = 0 ; 
                                                H3.numberOfY += 1 ; 
                                            }
                                            StdDraw.setPenColor(StdDraw.WHITE) ; 
                                            StdDraw.filledRectangle(650,900,30,50);
                                            StdDraw.setPenColor();
                                            StdDraw.show();
                                            StdDraw.text(650,900,"E1 -> H1");
                                            Files.hamlePrint("E1","H1");
                                            StdDraw.show();
                                            try{
                                                Thread.sleep(300);
                                            }catch(InterruptedException e){}
                                            return ; 
                                        }
                                        else{
                                            continue ; 
                                        }
                                    }
                                }
                                else if(((_x2>=659&&_x2<=715)&&(_y2>=556&&_y2<=757))&&(I1.numberOfY < 2)){      //I1 icin.
                                    
                                    for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                                        if(Board.hamleler.get(i)==4){
                                            Board.hamleler.remove(i);
                                            E1.numberOfX -=1 ; 
                                            I1.numberOfX +=1 ; 
                                            if(I1.numberOfY ==1){
                                                I1.numberOfY = 0 ; 
                                                H3.numberOfY += 1 ; 
                                            }
                                            StdDraw.setPenColor(StdDraw.WHITE) ; 
                                            StdDraw.filledRectangle(650,900,30,50);
                                            StdDraw.setPenColor();
                                            StdDraw.show();
                                            StdDraw.text(650,900,"E1 -> I1");
                                            Files.hamlePrint("E1","I1");
                                            StdDraw.show();
                                            try{
                                                Thread.sleep(300);
                                            }catch(InterruptedException e){}
                                            return ; 
                                        }
                                        else{
                                            continue ; 
                                        }
                                    }
                                }
                                else if(((_x2>=725&&_x2<=781)&&(_y2>=556&&_y2<=757))&&(J1.numberOfY < 2)){    // J1 icin.
                                    for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                                        if(Board.hamleler.get(i)==5){
                                            Board.hamleler.remove(i);
                                            E1.numberOfX -=1 ; 
                                            J1.numberOfX +=1 ; 
                                            if(J1.numberOfY ==1){
                                                J1.numberOfY = 0 ; 
                                                H3.numberOfY += 1 ; 
                                            }
                                            StdDraw.setPenColor(StdDraw.WHITE) ; 
                                            StdDraw.filledRectangle(650,900,30,50);
                                            StdDraw.setPenColor();
                                            StdDraw.show();
                                            StdDraw.text(650,900,"E1 -> J1");
                                             Files.hamlePrint("E1","J1");
                                            StdDraw.show();
                                            try{
                                                Thread.sleep(300);
                                            }catch(InterruptedException e){}
                                            return ; 
                                        }
                                        else{
                                            continue ; 
                                        }
                                    }
                                }
                                else if(((_x2>=788&&_x2<=842)&&(_y2>=556&&_y2<=757))&&(K1.numberOfY < 2)){     // K1 icin
                                    for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                                        if(Board.hamleler.get(i)==6){
                                            Board.hamleler.remove(i);
                                            E1.numberOfX -=1 ; 
                                            K1.numberOfX +=1 ; 
                                            if(K1.numberOfY ==1){
                                                K1.numberOfY = 0 ; 
                                                H3.numberOfY += 1 ; 
                                            }
                                            StdDraw.setPenColor(StdDraw.WHITE) ; 
                                            StdDraw.filledRectangle(650,900,30,50);
                                            StdDraw.setPenColor();
                                            StdDraw.show();
                                            StdDraw.text(650,900,"E1 -> K1");
                                            Files.hamlePrint("E1","K1");
                                            StdDraw.show();
                                            try{
                                                Thread.sleep(300);
                                            }catch(InterruptedException e){}
                                            return ; 
                                        }
                                    }
                                }    
                            }
                        }
                    } 
                }
                else if(((_x>=419 && _x<=478)&&(_y>=556 && _y<=757)) && F1.numberOfX > 0){            //from F1.
                    if(checkF1 == false){
                        StdDraw.setPenColor(StdDraw.RED) ; 
                        StdDraw.text(650,900,"No valid move from F1.");
                        StdDraw.show();
                        StdDraw.setPenColor();
                        try{
                            Thread.sleep(300);
                        }catch(InterruptedException e){}
                        StdDraw.setPenColor(StdDraw.WHITE);
                        StdDraw.filledRectangle(650, 900, 90,50);
                        StdDraw.setPenColor();
                        StdDraw.show();
                    }
                    else{
                        StdDraw.text(650,900,"F1 -> ? ");
                        StdDraw.show();
                        double _x2 ; 
                        double _y2 ; 
                        while(true){                                                                // destination loop.
                            if(StdDraw.isMousePressed()){
                                _x2 = StdDraw.mouseX() ; 
                                _y2 = StdDraw.mouseY() ; 
                                if(((_x2>=528&&_x2<=583)&&(_y2>=556&&_y2<=757))&&(G1.numberOfY < 2)){     // G1'e git.
                                    for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                                        if(Board.hamleler.get(i)==1){
                                            Board.hamleler.remove(i);
                                            F1.numberOfX -=1 ; 
                                            G1.numberOfX +=1 ; 
                                            if(G1.numberOfY ==1){
                                                G1.numberOfY = 0 ; 
                                                H3.numberOfY += 1 ; 
                                            }
                                            StdDraw.setPenColor(StdDraw.WHITE) ; 
                                            StdDraw.filledRectangle(650,900,30,50);
                                            StdDraw.setPenColor();
                                            StdDraw.show();
                                            StdDraw.text(650,900,"F1 -> G1");
                                            Files.hamlePrint("F1","G1");
                                            StdDraw.show();
                                            try{
                                                Thread.sleep(300);
                                            }catch(InterruptedException e){}
                                            return ; 
                                        }
                                        else{
                                            continue ; 
                                        }
                                    }
                                }
                                else if(((_x2>=594&&_x2<=647)&&(_y2>=556&&_y2<=757))&&(H1.numberOfY < 2)){   // H1'e git.
                                    
                                    for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                                        if(Board.hamleler.get(i)==2){
                                            Board.hamleler.remove(i);
                                            F1.numberOfX -=1 ; 
                                            H1.numberOfX +=1 ; 
                                            if(H1.numberOfY ==1){
                                                H1.numberOfY = 0 ; 
                                                H3.numberOfY += 1 ; 
                                            }
                                            StdDraw.setPenColor(StdDraw.WHITE) ; 
                                            StdDraw.filledRectangle(650,900,30,50);
                                            StdDraw.setPenColor();
                                            StdDraw.show();
                                            StdDraw.text(650,900,"F1 -> H1");
                                            Files.hamlePrint("F1","H1");
                                            StdDraw.show();
                                            try{
                                                Thread.sleep(300);
                                            }catch(InterruptedException e){}
                                            return ; 
                                        }
                                        else{
                                            continue ; 
                                        }
                                    }
                                }
                                else if(((_x2>=659&&_x2<=715)&&(_y2>=556&&_y2<=757))&&(I1.numberOfY < 2)){   // I1'e git
                                    
                                    for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                                        if(Board.hamleler.get(i)==3){
                                            Board.hamleler.remove(i);
                                            F1.numberOfX -=1 ; 
                                            I1.numberOfX +=1 ; 
                                            if(I1.numberOfY ==1){
                                                I1.numberOfY = 0 ; 
                                                H3.numberOfY += 1 ; 
                                            }
                                            StdDraw.setPenColor(StdDraw.WHITE) ; 
                                            StdDraw.filledRectangle(650,900,30,50);
                                            StdDraw.setPenColor();
                                            StdDraw.show();
                                            StdDraw.text(650,900,"F1 -> I1");
                                            Files.hamlePrint("F1","I1");
                                            StdDraw.show();
                                            try{
                                                Thread.sleep(300);
                                            }catch(InterruptedException e){}
                                            return ; 
                                        }
                                        else{
                                            continue ; 
                                        }
                                    }
                                }
                                else if(((_x2>=725&&_x2<=781)&&(_y2>=556&&_y2<=757))&&(J1.numberOfY < 2)){      //J1 icin.
                                    
                                    for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                                        if(Board.hamleler.get(i)==4){
                                            Board.hamleler.remove(i);
                                            F1.numberOfX -=1 ; 
                                            J1.numberOfX +=1 ; 
                                            if(J1.numberOfY ==1){
                                                J1.numberOfY = 0 ; 
                                                H3.numberOfY += 1 ; 
                                            }
                                            StdDraw.setPenColor(StdDraw.WHITE) ; 
                                            StdDraw.filledRectangle(650,900,30,50);
                                            StdDraw.setPenColor();
                                            StdDraw.show();
                                            StdDraw.text(650,900,"F1 -> J1");
                                            Files.hamlePrint("F1","J1");
                                            StdDraw.show();
                                            try{
                                                Thread.sleep(300);
                                            }catch(InterruptedException e){}
                                            return ; 
                                        }
                                        else{
                                            continue ; 
                                        }
                                    }
                                }
                                else if(((_x2>=788&&_x2<=842)&&(_y2>=556&&_y2<=757))&&(K1.numberOfY < 2)){    // K1 icin.
                                    for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                                        if(Board.hamleler.get(i)==5){
                                            Board.hamleler.remove(i);
                                            F1.numberOfX -=1 ; 
                                            K1.numberOfX +=1 ; 
                                            if(K1.numberOfY ==1){
                                                K1.numberOfY = 0 ; 
                                                H3.numberOfY += 1 ; 
                                            }
                                            StdDraw.setPenColor(StdDraw.WHITE) ; 
                                            StdDraw.filledRectangle(650,900,30,50);
                                            StdDraw.setPenColor();
                                            StdDraw.show();
                                            StdDraw.text(650,900,"F1 -> K1");
                                            Files.hamlePrint("F1","K1");
                                            StdDraw.show();
                                            try{
                                                Thread.sleep(300);
                                            }catch(InterruptedException e){}
                                            return ; 
                                        }
                                        else{
                                            continue ; 
                                        }
                                    }
                                }
                                else if(((_x2>=854&&_x2<=910)&&(_y2>=556&&_y2<=757))&&(L1.numberOfY < 2)){     // L1 icin
                                    for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                                        if(Board.hamleler.get(i)==6){
                                            Board.hamleler.remove(i);
                                            F1.numberOfX -=1 ; 
                                            L1.numberOfX +=1 ; 
                                            if(L1.numberOfY ==1){
                                                L1.numberOfY = 0 ; 
                                                H3.numberOfY += 1 ; 
                                            }
                                            StdDraw.setPenColor(StdDraw.WHITE) ; 
                                            StdDraw.filledRectangle(650,900,30,50);
                                            StdDraw.setPenColor();
                                            StdDraw.show();
                                            StdDraw.text(650,900,"F1 -> L1");
                                             Files.hamlePrint("F1","L1");
                                            StdDraw.show();
                                            try{
                                                Thread.sleep(300);
                                            }catch(InterruptedException e){}
                                            return ; 
                                        }
                                    }
                                }    
                            }
                        }
                    } 
                }
                else if(((_x>=528 && _x<=583)&&(_y>=556 && _y<=757)) && G1.numberOfX > 0){     // from G1
                     if(checkG1 == false){
                        StdDraw.setPenColor(StdDraw.RED) ; 
                        StdDraw.text(650,900,"No valid move from G1.");
                        StdDraw.show();
                        StdDraw.setPenColor();
                        try{
                            Thread.sleep(300);
                        }catch(InterruptedException e){}
                        StdDraw.setPenColor(StdDraw.WHITE);
                        StdDraw.filledRectangle(650, 900, 90,50);
                        StdDraw.setPenColor();
                        StdDraw.show();
                    }
                    else{
                        StdDraw.text(650,900,"G1 -> ? ");
                        StdDraw.show();
                        double _x2 ; 
                        double _y2 ; 
                        while(true){                                                                // destination loop.
                            if(StdDraw.isMousePressed()){
                                _x2 = StdDraw.mouseX() ; 
                                _y2 = StdDraw.mouseY() ; 
                                if(((_x2>=594&&_x2<=647)&&(_y2>=556&&_y2<=757))&&(H1.numberOfY < 2)){     // H1'e git.
                                    for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                                        if(Board.hamleler.get(i)==1){
                                            Board.hamleler.remove(i);
                                            G1.numberOfX -=1 ; 
                                            H1.numberOfX +=1 ; 
                                            if(H1.numberOfY ==1){
                                                H1.numberOfY = 0 ; 
                                                H3.numberOfY += 1 ; 
                                            }
                                            StdDraw.setPenColor(StdDraw.WHITE) ; 
                                            StdDraw.filledRectangle(650,900,30,50);
                                            StdDraw.setPenColor();
                                            StdDraw.show();
                                            StdDraw.text(650,900,"G1 -> H1");
                                            Files.hamlePrint("G1","H1");
                                            StdDraw.show();
                                            try{
                                                Thread.sleep(300);
                                            }catch(InterruptedException e){}
                                            return ; 
                                        }
                                        else{
                                            continue ; 
                                        }
                                    }
                                }
                                else if(((_x2>=659&&_x2<=715)&&(_y2>=556&&_y2<=757))&&(I1.numberOfY < 2)){   // I1'e git.
                                    
                                    for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                                        if(Board.hamleler.get(i)==2){
                                            Board.hamleler.remove(i);
                                            G1.numberOfX -=1 ; 
                                            I1.numberOfX +=1 ; 
                                            if(I1.numberOfY ==1){
                                                I1.numberOfY = 0 ; 
                                                H3.numberOfY += 1 ; 
                                            }
                                            StdDraw.setPenColor(StdDraw.WHITE) ; 
                                            StdDraw.filledRectangle(650,900,30,50);
                                            StdDraw.setPenColor();
                                            StdDraw.show();
                                            StdDraw.text(650,900,"G1 -> I1");
                                            Files.hamlePrint("G1","I1");
                                            StdDraw.show();
                                            try{
                                                Thread.sleep(300);
                                            }catch(InterruptedException e){}
                                            return ; 
                                        }
                                        else{
                                            continue ; 
                                        }
                                    }
                                }
                                else if(((_x2>=725&&_x2<=781)&&(_y2>=556&&_y2<=757))&&(J1.numberOfY < 2)){   // J1'e git
                                    
                                    for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                                        if(Board.hamleler.get(i)==3){
                                            Board.hamleler.remove(i);
                                            G1.numberOfX -=1 ; 
                                            J1.numberOfX +=1 ; 
                                            if(J1.numberOfY ==1){
                                                J1.numberOfY = 0 ; 
                                                H3.numberOfY += 1 ; 
                                            }
                                            StdDraw.setPenColor(StdDraw.WHITE) ; 
                                            StdDraw.filledRectangle(650,900,30,50);
                                            StdDraw.setPenColor();
                                            StdDraw.show();
                                            StdDraw.text(650,900,"G1 -> J1");
                                            Files.hamlePrint("G1","J1");
                                            StdDraw.show();
                                            try{
                                                Thread.sleep(300);
                                            }catch(InterruptedException e){}
                                            return ; 
                                        }
                                        else{
                                            continue ; 
                                        }
                                    }
                                }
                                else if(((_x2>=788&&_x2<=842)&&(_y2>=556&&_y2<=757))&&(K1.numberOfY < 2)){      //K1 icin.
                                    
                                    for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                                        if(Board.hamleler.get(i)==4){
                                            Board.hamleler.remove(i);
                                            G1.numberOfX -=1 ; 
                                            K1.numberOfX +=1 ; 
                                            if(K1.numberOfY ==1){
                                                K1.numberOfY = 0 ; 
                                                H3.numberOfY += 1 ; 
                                            }
                                            StdDraw.setPenColor(StdDraw.WHITE) ; 
                                            StdDraw.filledRectangle(650,900,30,50);
                                            StdDraw.setPenColor();
                                            StdDraw.show();
                                            StdDraw.text(650,900,"G1 -> K1");
                                            Files.hamlePrint("G1","K1");
                                            StdDraw.show();
                                            try{
                                                Thread.sleep(300);
                                            }catch(InterruptedException e){}
                                            return ; 
                                        }
                                        else{
                                            continue ; 
                                        }
                                    }
                                }
                                else if(((_x2>=854&&_x2<=910)&&(_y2>=556&&_y2<=757))&&(L1.numberOfY < 2)){    // L1 icin.
                                    for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                                        if(Board.hamleler.get(i)==5){
                                            Board.hamleler.remove(i);
                                            G1.numberOfX -=1 ; 
                                            L1.numberOfX +=1 ; 
                                            if(L1.numberOfY ==1){
                                                L1.numberOfY = 0 ; 
                                                H3.numberOfY += 1 ; 
                                            }
                                            StdDraw.setPenColor(StdDraw.WHITE) ; 
                                            StdDraw.filledRectangle(650,900,30,50);
                                            StdDraw.setPenColor();
                                            StdDraw.show();
                                            StdDraw.text(650,900,"G1 -> L1");
                                            Files.hamlePrint("G1","L1");
                                            StdDraw.show();
                                            try{
                                                Thread.sleep(300);
                                            }catch(InterruptedException e){}
                                            return ; 
                                        }
                                        else{
                                            continue ; 
                                        }
                                    }
                                }
                                    
                            }
                        }
                    } 
                }
                else if(((_x>=594 && _x<=647)&&(_y>=556 && _y<=757)) && H1.numberOfX > 0){          // from H1
                    if(checkH1 == false){
                        StdDraw.setPenColor(StdDraw.RED) ; 
                        StdDraw.text(650,900,"No valid move from H1.");
                        StdDraw.show();
                        StdDraw.setPenColor();
                        try{
                            Thread.sleep(300);
                        }catch(InterruptedException e){}
                        StdDraw.setPenColor(StdDraw.WHITE);
                        StdDraw.filledRectangle(650, 900, 90,50);
                        StdDraw.setPenColor();
                        StdDraw.show();
                    }
                    else{
                        StdDraw.text(650,900,"H1 -> ? ");
                        StdDraw.show();
                        double _x2 ; 
                        double _y2 ; 
                        while(true){                                                                // destination loop.
                            if(StdDraw.isMousePressed()){
                                _x2 = StdDraw.mouseX() ; 
                                _y2 = StdDraw.mouseY() ; 
                                if(((_x2>=659&&_x2<=715)&&(_y2>=556&&_y2<=757))&&(I1.numberOfY < 2)){     // I1'e git.
                                    for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                                        if(Board.hamleler.get(i)==1){
                                            Board.hamleler.remove(i);
                                            H1.numberOfX -=1 ; 
                                            I1.numberOfX +=1 ; 
                                            if(I1.numberOfY ==1){
                                                I1.numberOfY = 0 ; 
                                                H3.numberOfY += 1 ; 
                                            }
                                            StdDraw.setPenColor(StdDraw.WHITE) ; 
                                            StdDraw.filledRectangle(650,900,30,50);
                                            StdDraw.setPenColor();
                                            StdDraw.show();
                                            StdDraw.text(650,900,"H1 -> I1");
                                            Files.hamlePrint("H1","I1");
                                            StdDraw.show();
                                            try{
                                                Thread.sleep(300);
                                            }catch(InterruptedException e){}
                                            return ; 
                                        }
                                        else{
                                            continue ; 
                                        }
                                    }
                                }
                                else if(((_x2>=725&&_x2<=781)&&(_y2>=556&&_y2<=757))&&(J1.numberOfY < 2)){   // J1'e git.
                                    
                                    for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                                        if(Board.hamleler.get(i)==2){
                                            Board.hamleler.remove(i);
                                            H1.numberOfX -=1 ; 
                                            J1.numberOfX +=1 ; 
                                            if(J1.numberOfY ==1){
                                                J1.numberOfY = 0 ; 
                                                H3.numberOfY += 1 ; 
                                            }
                                            StdDraw.setPenColor(StdDraw.WHITE) ; 
                                            StdDraw.filledRectangle(650,900,30,50);
                                            StdDraw.setPenColor();
                                            StdDraw.show();
                                            StdDraw.text(650,900,"H1 -> J1");
                                            Files.hamlePrint("H1","J1");
                                            StdDraw.show();
                                            try{
                                                Thread.sleep(300);
                                            }catch(InterruptedException e){}
                                            return ; 
                                        }
                                        else{
                                            continue ; 
                                        }
                                    }
                                }
                                else if(((_x2>=788&&_x2<=842)&&(_y2>=556&&_y2<=757))&&(K1.numberOfY < 2)){   // K1'e git
                                    
                                    for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                                        if(Board.hamleler.get(i)==3){
                                            Board.hamleler.remove(i);
                                            H1.numberOfX -=1 ; 
                                            K1.numberOfX +=1 ; 
                                            if(K1.numberOfY ==1){
                                                K1.numberOfY = 0 ; 
                                                H3.numberOfY += 1 ; 
                                            }
                                            StdDraw.setPenColor(StdDraw.WHITE) ; 
                                            StdDraw.filledRectangle(650,900,30,50);
                                            StdDraw.setPenColor();
                                            StdDraw.show();
                                            StdDraw.text(650,900,"H1 -> K1");
                                            Files.hamlePrint("H1","K1");
                                            StdDraw.show();
                                            try{
                                                Thread.sleep(300);
                                            }catch(InterruptedException e){}
                                            return ; 
                                        }
                                        else{
                                            continue ; 
                                        }
                                    }
                                }
                                else if(((_x2>=854&&_x2<=910)&&(_y2>=556&&_y2<=757))&&(L1.numberOfY < 2)){      //L1 icin.
                                    
                                    for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                                        if(Board.hamleler.get(i)==4){
                                            Board.hamleler.remove(i);
                                            H1.numberOfX -=1 ; 
                                            L1.numberOfX +=1 ; 
                                            if(L1.numberOfY ==1){
                                                L1.numberOfY = 0 ; 
                                                H3.numberOfY += 1 ; 
                                            }
                                            StdDraw.setPenColor(StdDraw.WHITE) ; 
                                            StdDraw.filledRectangle(650,900,30,50);
                                            StdDraw.setPenColor();
                                            StdDraw.show();
                                            StdDraw.text(650,900,"H1 -> L1");
                                            Files.hamlePrint("H1","L1");
                                            StdDraw.show();
                                            try{
                                                Thread.sleep(300);
                                            }catch(InterruptedException e){}
                                            return ; 
                                        }
                                        else{
                                            continue ; 
                                        }
                                    }
                                }     
                            }
                        }
                    } 
                }
                else if(((_x>=659 && _x<=715)&&(_y>=556 && _y<=757)) && I1.numberOfX > 0){           // from I1.
                     if(checkI1 == false){
                        StdDraw.setPenColor(StdDraw.RED) ; 
                        StdDraw.text(650,900,"No valid move from I1.");
                        StdDraw.show();
                        StdDraw.setPenColor();
                        try{
                            Thread.sleep(300);
                        }catch(InterruptedException e){}
                        StdDraw.setPenColor(StdDraw.WHITE);
                        StdDraw.filledRectangle(650, 900, 90,50);
                        StdDraw.setPenColor();
                        StdDraw.show();
                    }
                    else{
                        StdDraw.text(650,900,"I1 -> ? ");
                        StdDraw.show();
                        double _x2 ; 
                        double _y2 ; 
                        while(true){                                                                // destination loop.
                            if(StdDraw.isMousePressed()){
                                _x2 = StdDraw.mouseX() ; 
                                _y2 = StdDraw.mouseY() ; 
                                if(((_x2>=725&&_x2<=781)&&(_y2>=556&&_y2<=757))&&(J1.numberOfY < 2)){     // J1'e git.
                                    for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                                        if(Board.hamleler.get(i)==1){
                                            Board.hamleler.remove(i);
                                            I1.numberOfX -=1 ; 
                                            J1.numberOfX +=1 ; 
                                            if(J1.numberOfY ==1){
                                                J1.numberOfY = 0 ; 
                                                H3.numberOfY += 1 ; 
                                            }
                                            StdDraw.setPenColor(StdDraw.WHITE) ; 
                                            StdDraw.filledRectangle(650,900,30,50);
                                            StdDraw.setPenColor();
                                            StdDraw.show();
                                            StdDraw.text(650,900,"I1 -> J1");
                                            Files.hamlePrint("I1","J1");
                                            StdDraw.show();
                                            try{
                                                Thread.sleep(300);
                                            }catch(InterruptedException e){}
                                            return ; 
                                        }
                                        else{
                                            continue ; 
                                        }
                                    }
                                }
                                else if(((_x2>=788&&_x2<=842)&&(_y2>=556&&_y2<=757))&&(K1.numberOfY < 2)){   // K1'e git.
                                    
                                    for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                                        if(Board.hamleler.get(i)==2){
                                            Board.hamleler.remove(i);
                                            I1.numberOfX -=1 ; 
                                            K1.numberOfX +=1 ; 
                                            if(K1.numberOfY ==1){
                                                K1.numberOfY = 0 ; 
                                                H3.numberOfY += 1 ; 
                                            }
                                            StdDraw.setPenColor(StdDraw.WHITE) ; 
                                            StdDraw.filledRectangle(650,900,30,50);
                                            StdDraw.setPenColor();
                                            StdDraw.show();
                                            StdDraw.text(650,900,"I1 -> K1");
                                            Files.hamlePrint("I1","K1");
                                            StdDraw.show();
                                            try{
                                                Thread.sleep(300);
                                            }catch(InterruptedException e){}
                                            return ; 
                                        }
                                        else{
                                            continue ; 
                                        }
                                    }
                                }
                                else if(((_x2>=854&&_x2<=910)&&(_y2>=556&&_y2<=757))&&(L1.numberOfY < 2)){   // L1'e git
                                    
                                    for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                                        if(Board.hamleler.get(i)==3){
                                            Board.hamleler.remove(i);
                                            I1.numberOfX -=1 ; 
                                            L1.numberOfX +=1 ; 
                                            if(L1.numberOfY ==1){
                                                L1.numberOfY = 0 ; 
                                                H3.numberOfY += 1 ; 
                                            }
                                            StdDraw.setPenColor(StdDraw.WHITE) ; 
                                            StdDraw.filledRectangle(650,900,30,50);
                                            StdDraw.setPenColor();
                                            StdDraw.show();
                                            StdDraw.text(650,900,"I1 -> L1");
                                            Files.hamlePrint("I1","L1");
                                            StdDraw.show();
                                            try{
                                                Thread.sleep(300);
                                            }catch(InterruptedException e){}
                                            return ; 
                                        }
                                        else{
                                            continue ; 
                                        }
                                    }
                                }    
                            }
                        }
                    } 
                }
                else if(((_x>=725 && _x<=781)&&(_y>=556 && _y<=757)) && J1.numberOfX > 0){          //from J1
                    if(checkJ1 == false){
                        StdDraw.setPenColor(StdDraw.RED) ; 
                        StdDraw.text(650,900,"No valid move from J1.");
                        StdDraw.show();
                        StdDraw.setPenColor();
                        try{
                            Thread.sleep(300);
                        }catch(InterruptedException e){}
                        StdDraw.setPenColor(StdDraw.WHITE);
                        StdDraw.filledRectangle(650, 900, 90,50);
                        StdDraw.setPenColor();
                        StdDraw.show();
                    }
                    else{
                        StdDraw.text(650,900,"J1 -> ? ");
                        StdDraw.show();
                        double _x2 ; 
                        double _y2 ; 
                        while(true){                                                                // destination loop.
                            if(StdDraw.isMousePressed()){
                                _x2 = StdDraw.mouseX() ; 
                                _y2 = StdDraw.mouseY() ; 
                                if(((_x2>=788&&_x2<=842)&&(_y2>=556&&_y2<=757))&&(K1.numberOfY < 2)){     // K1'e git.
                                    for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                                        if(Board.hamleler.get(i)==1){
                                            Board.hamleler.remove(i);
                                            J1.numberOfX -=1 ; 
                                            K1.numberOfX +=1 ; 
                                            if(K1.numberOfY ==1){
                                                K1.numberOfY = 0 ; 
                                                H3.numberOfY += 1 ; 
                                            }
                                            StdDraw.setPenColor(StdDraw.WHITE) ; 
                                            StdDraw.filledRectangle(650,900,30,50);
                                            StdDraw.setPenColor();
                                            StdDraw.show();
                                            StdDraw.text(650,900,"J1 -> K1");
                                            Files.hamlePrint("J1","K1");
                                            StdDraw.show();
                                            try{
                                                Thread.sleep(300);
                                            }catch(InterruptedException e){}
                                            return ; 
                                        }
                                        else{
                                            continue ; 
                                        }
                                    }
                                }
                                else if(((_x2>=854&&_x2<=910)&&(_y2>=556&&_y2<=757))&&(L1.numberOfY < 2)){   // L1'e git.
                                    
                                    for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                                        if(Board.hamleler.get(i)==2){
                                            Board.hamleler.remove(i);
                                            J1.numberOfX -=1 ; 
                                            L1.numberOfX +=1 ; 
                                            if(L1.numberOfY ==1){
                                                L1.numberOfY = 0 ; 
                                                H3.numberOfY += 1 ; 
                                            }
                                            StdDraw.setPenColor(StdDraw.WHITE) ; 
                                            StdDraw.filledRectangle(650,900,30,50);
                                            StdDraw.setPenColor();
                                            StdDraw.show();
                                            StdDraw.text(650,900,"J1 -> L1");
                                            Files.hamlePrint("J1","L1");
                                            StdDraw.show();
                                            try{
                                                Thread.sleep(300);
                                            }catch(InterruptedException e){}
                                            return ; 
                                        }
                                        else{
                                            continue ; 
                                        }
                                    }
                                }  
                            }
                        }
                    } 
                }
                else if(((_x>=788 && _x<=842)&&(_y>=556 && _y<=757)) && K1.numberOfX > 0){               // from K1.
                    if(checkK1 == false){
                        StdDraw.setPenColor(StdDraw.RED) ; 
                        StdDraw.text(650,900,"No valid move from K1.");
                        StdDraw.show();
                        StdDraw.setPenColor();
                        try{
                            Thread.sleep(300);
                        }catch(InterruptedException e){}
                        StdDraw.setPenColor(StdDraw.WHITE);
                        StdDraw.filledRectangle(650, 900, 90,50);
                        StdDraw.setPenColor();
                        StdDraw.show();
                    }
                    else{
                        StdDraw.text(650,900,"K1 -> ? ");
                        StdDraw.show();
                        double _x2 ; 
                        double _y2 ; 
                        while(true){                                                                // destination loop.
                            if(StdDraw.isMousePressed()){
                                _x2 = StdDraw.mouseX() ; 
                                _y2 = StdDraw.mouseY() ; 
                                if(((_x2>=854&&_x2<=910)&&(_y2>=556&&_y2<=757))&&(L1.numberOfY < 2)){     // L1'e git.
                                    for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                                        if(Board.hamleler.get(i)==1){
                                            Board.hamleler.remove(i);
                                            K1.numberOfX -=1 ; 
                                            L1.numberOfX +=1 ; 
                                            if(L1.numberOfY ==1){
                                                L1.numberOfY = 0 ; 
                                                H3.numberOfY += 1 ; 
                                            }
                                            StdDraw.setPenColor(StdDraw.WHITE) ; 
                                            StdDraw.filledRectangle(650,900,30,50);
                                            StdDraw.setPenColor();
                                            StdDraw.show();
                                            StdDraw.text(650,900,"K1 -> L1");
                                            Files.hamlePrint("K1","L1");
                                            StdDraw.show();
                                            try{
                                                Thread.sleep(300);
                                            }catch(InterruptedException e){}
                                            return ; 
                                        }
                                        else{
                                            continue ; 
                                        }
                                    }
                                }  
                            }
                        }
                    }
                }
                
            }

                
        }
    }      // END OF THIS FUNCTION!
    
    static void userYKirik(){
        boolean checkH3 = false  ;
        for(int i = 0 ; i < Board.hamleler.size();++i){
            if(Board.hamleler.get(i)==1){
                if(L1.numberOfX < 2 ){
                    checkH3 = true ; break ; 
                }
            }
            else if(Board.hamleler.get(i)==2){
                if(Board.hamleler.get(i)==2){
                    if(K1.numberOfX < 2 ){
                        checkH3 = true ; break ; 
                    }
                }
            }
            else if(Board.hamleler.get(i)==3){
                if(J1.numberOfX < 2 ){
                    checkH3 = true ; break  ;
                }
            }
            else if(Board.hamleler.get(i)==4){
                if(I1.numberOfX < 2 ){
                    checkH3  =true ; break ; 
                }
            }
            else if(Board.hamleler.get(i)==5){
                if(H1.numberOfX < 2 ){
                    checkH3  =true ; break ; 
                }
            }
            else if(Board.hamleler.get(i)==6){
                if(G1.numberOfX < 2 ){
                    checkH3  =true ; break ; 
                }
            }
        }
        if(checkH3 == false){
            StdDraw.setPenColor(StdDraw.RED);
            StdDraw.text(650,110, "No valid Move");
            StdDraw.setPenColor();
            StdDraw.show();
            try{
                Thread.sleep(300);
            }catch(InterruptedException e){}
            return ; 
        }
        StdDraw.text(650,110,"H3 -> ?");
        StdDraw.show();
        double _x2 , _y2 ;  
        while(true){                                              // destinitaion loop.
            if(StdDraw.isMousePressed()){
                _x2 = StdDraw.mouseX() ; 
                _y2 = StdDraw.mouseY() ; 
                if(((_x2>=854&&_x2<=910)&&(_y2>=556&&_y2<=757))&&(L1.numberOfX < 2)){         // L1'e git.
                    for(int i = 0 ; i < Board.hamleler.size();++i){
                        if(Board.hamleler.get(i)==1){
                            Board.hamleler.remove(i);
                            H3.numberOfY -=1 ;
                            L1.numberOfY +=1  ;
                            if(L1.numberOfX ==1){
                                L1.numberOfX = 0 ; 
                                E3.numberOfX +=1  ;
                            }
                            StdDraw.setPenColor(StdDraw.WHITE);
                            StdDraw.filledRectangle(650,110,30,50);
                            StdDraw.setPenColor();
                            StdDraw.show();
                            StdDraw.text(650,110,"H3 -> L1");
                            Files.hamlePrint("H3","L1");
                            StdDraw.show();
                            try{
                                Thread.sleep(300);
                            }catch(InterruptedException e){}
                            return ;
                        }
                    }
                }
                else if(((_x2>=790&&_x2<=842)&&(_y2>=556&&_y2<=757))&&(K1.numberOfX < 2)){       // K1'e git
                    for(int i = 0 ; i < Board.hamleler.size();++i){
                        if(Board.hamleler.get(i)==2){
                            Board.hamleler.remove(i);
                            H3.numberOfY -=1 ;
                            K1.numberOfY +=1  ;
                            if(K1.numberOfX ==1){
                                K1.numberOfX = 0 ; 
                                E3.numberOfX +=1  ;
                            }
                            StdDraw.setPenColor(StdDraw.WHITE);
                            StdDraw.filledRectangle(650,110,30,50);
                            StdDraw.setPenColor();
                            StdDraw.show();
                            StdDraw.text(650,110,"H3 -> K1");
                            Files.hamlePrint("H3","K1");
                            StdDraw.show();
                            try{
                                Thread.sleep(300);
                            }catch(InterruptedException e){}
                            return ;
                        }
                    }
                }
                else if(((_x2>=722&&_x2<=780)&&(_y2>=556&&_y2<=757))&&(J1.numberOfX < 2)){             //J1'e git
                    for(int i = 0 ; i < Board.hamleler.size();++i){
                        if(Board.hamleler.get(i)==3){
                            Board.hamleler.remove(i);
                            H3.numberOfY -=1 ;
                            J1.numberOfY +=1  ;
                            if(J1.numberOfX ==1){
                                J1.numberOfX = 0 ; 
                                E3.numberOfX +=1  ;
                            }
                            StdDraw.setPenColor(StdDraw.WHITE);
                            StdDraw.filledRectangle(650,110,30,50);
                            StdDraw.setPenColor();
                            StdDraw.show();
                            StdDraw.text(650,110,"H3 -> J1");
                            Files.hamlePrint("H3","J1");
                            StdDraw.show();
                            try{
                                Thread.sleep(300);
                            }catch(InterruptedException e){}
                            return ;
                        }
                    }
                }
                else if(((_x2>=659&&_x2<=715)&&(_y2>=556&&_y2<=757))&&(I1.numberOfX < 2)){          // I1'e git
                    for(int i = 0 ; i < Board.hamleler.size();++i){
                        if(Board.hamleler.get(i)==4){
                            Board.hamleler.remove(i);
                            H3.numberOfY -=1 ;
                            I1.numberOfY +=1  ;
                            if(I1.numberOfX ==1){
                                I1.numberOfX = 0 ; 
                                E3.numberOfX +=1  ;
                            }
                            StdDraw.setPenColor(StdDraw.WHITE);
                            StdDraw.filledRectangle(650,110,30,50);
                            StdDraw.setPenColor();
                            StdDraw.show();
                            StdDraw.text(650,110,"H3 -> I1");
                            Files.hamlePrint("H3","I1");
                            StdDraw.show();
                            try{
                                Thread.sleep(300);
                            }catch(InterruptedException e){}
                            return ;
                        }
                    }
                }
                else if(((_x2>=595&&_x2<=652)&&(_y2>=556&&_y2<=757))&&(I1.numberOfX < 2)){         // H1'e git.
                    for(int i = 0 ; i < Board.hamleler.size();++i){
                        if(Board.hamleler.get(i)==5){
                            Board.hamleler.remove(i);
                            H3.numberOfY -=1 ;
                            H1.numberOfY +=1  ;
                            if(H1.numberOfX ==1){
                                H1.numberOfX = 0 ; 
                                E3.numberOfX +=1  ;
                            }
                            StdDraw.setPenColor(StdDraw.WHITE);
                            StdDraw.filledRectangle(650,110,30,50);
                            StdDraw.setPenColor();
                            StdDraw.show();
                            StdDraw.text(650,110,"H3 -> H1");
                            Files.hamlePrint("H3","H1");
                            StdDraw.show();
                            try{
                                Thread.sleep(300);
                            }catch(InterruptedException e){}
                            return ;
                        }
                    }
                }
                else if(((_x2>=528&&_x2<=583)&&(_y2>=556&&_y2<=757))&&(G1.numberOfX < 2)){          // G1'e git
                    for(int i = 0 ; i < Board.hamleler.size();++i){
                        if(Board.hamleler.get(i)==6){
                            Board.hamleler.remove(i);
                            H3.numberOfY -=1 ;
                            G1.numberOfY +=1  ;
                            if(G1.numberOfX ==1){
                                G1.numberOfX = 0 ; 
                                E3.numberOfX +=1  ;
                            }
                            StdDraw.setPenColor(StdDraw.WHITE);
                            StdDraw.filledRectangle(650,110,30,50);
                            StdDraw.setPenColor();
                            StdDraw.show();
                            StdDraw.text(650,110,"H3 -> G1");
                            Files.hamlePrint("H3","G1");
                            StdDraw.show();
                            try{
                                Thread.sleep(300);
                            }catch(InterruptedException e){}
                            return ;
                        }
                    }
                }
            }
            
        }
    }
    
    static void userYBase(){
        boolean checkG5=false  , checkH5 = false , checkI5 = false , checkJ5= false , checkK5 = false, checkL5  =false ; 
        if(G5.numberOfY > 0){
            for(int i = 0 ; i < Board.hamleler.size();++i){
                if(Board.hamleler.get(i)==1){
                    if(H5.numberOfX < 2 ){
                        checkG5  = true ; break ; 
                    }
                }
                else if(Board.hamleler.get(i)==2){
                    if(I5.numberOfX < 2 ){
                        checkG5 = true ; break ; 
                    }
                }
                else if(Board.hamleler.get(i)==3){
                    if(J5.numberOfX < 2 ){
                        checkG5 = true ; break ; 
                    }
                }
                else if(Board.hamleler.get(i)==4){
                    if(K5.numberOfX < 2 ){
                        checkG5 = true ; break  ;
                    }
                }
                else if(Board.hamleler.get(i)==5){
                    if(L5.numberOfX < 2 ){
                        checkG5 = true ; break ; 
                    }
                }
                else{                       // it means dice is 6. it can moves to TopY.
                    checkG5 = true ; break  ;
                }
            }
        }
        if(H5.numberOfY > 0){
            for(int i = 0 ; i < Board.hamleler.size();++i){
                if(Board.hamleler.get(i)==1){
                    if(I5.numberOfX < 2 ){
                        checkH5  = true ; break ; 
                    }
                }
                else if(Board.hamleler.get(i)==2){
                    if(J5.numberOfX < 2 ){
                        checkH5 = true ; break ; 
                    }
                }
                else if(Board.hamleler.get(i)==3){
                    if(K5.numberOfX < 2 ){
                        checkH5 = true ; break ; 
                    }
                }
                else if(Board.hamleler.get(i)==4){
                    if(L5.numberOfX < 2 ){
                        checkH5 = true ; break  ;
                    }
                }
                else{                       // it means dice is 5 or  6. it can moves to TopY.
                    checkH5 = true ; break  ;
                }
            }
        }
        if(I5.numberOfY > 0){
           for(int i = 0 ; i < Board.hamleler.size();++i){
                if(Board.hamleler.get(i)==1){
                    if(J5.numberOfX < 2 ){
                        checkI5  = true ; break ; 
                    }
                }
                else if(Board.hamleler.get(i)==2){
                    if(K5.numberOfX < 2 ){
                        checkI5 = true ; break ; 
                    }
                }
                else if(Board.hamleler.get(i)==3){
                    if(L5.numberOfX < 2 ){
                        checkI5 = true ; break ; 
                    }
                }
                else{                       // it means dice is 4 or 5 or  6. it can moves to TopY.
                    checkI5 = true ; break  ;
                }
            } 
        }
        if(J5.numberOfY > 0){
            for(int i = 0 ; i < Board.hamleler.size();++i){
                if(Board.hamleler.get(i)==1){
                    if(K5.numberOfX < 2 ){
                        checkJ5  = true ; break ; 
                    }
                }
                else if(Board.hamleler.get(i)==2){
                    if(L5.numberOfX < 2 ){
                        checkJ5 = true ; break ; 
                    }
                }
                else{                       // it means dice is 3 or 4 or 5 or  6. it can moves to TopY.
                    checkJ5 = true ; break  ;
                }
            }
        }
        if(K5.numberOfY >  0){
            for(int i = 0 ; i < Board.hamleler.size();++i){
                if(Board.hamleler.get(i)==1){
                    if(L5.numberOfX < 2 ){
                        checkK5  = true ; break ; 
                    }
                }
                else{                       // it means dice is 2 or 3 or 4 or 5 or  6. it can moves to TopY.
                    checkK5 = true ; break  ;
                }
            }
        }
        if(L5.numberOfY > 0){
            checkL5 = true ;
        }
        //--------------------------------------------------------------------- has finished control instructions.
        if(!(checkG5 || checkH5 || checkI5 || checkJ5 || checkK5 || checkL5)){
            StdDraw.setPenColor(StdDraw.RED);
            StdDraw.text(650,900, "No valid Move");
            StdDraw.setPenColor();
            StdDraw.show();
            try{ Thread.sleep(300);} catch(InterruptedException e){}
            return ;
        }
        StdDraw.text(650, 110, "Please select stone");
        StdDraw.show();
        try{ Thread.sleep(2000);}catch(InterruptedException e){} 
        StdDraw.setPenColor(StdDraw.WHITE);
        StdDraw.filledRectangle(650, 110, 80, 50);
        StdDraw.show();

        StdDraw.setPenColor();
        
        double _x , _y ; 
        while(true){                           // select stone loop.
            if(StdDraw.isMousePressed()){
                _x = StdDraw.mouseX()  ; 
                _y = StdDraw.mouseY()  ;
                if(((_x>=528 && _x<=583)&&(_y>=242 && _y<=444)) && G5.numberOfY > 0){             // from G5
                    if(checkG5 == false){
                        StdDraw.setPenColor(StdDraw.RED) ; 
                        StdDraw.text(650,900,"No valid move from G5.");
                        StdDraw.show();
                        StdDraw.setPenColor();
                        try{
                            Thread.sleep(300);
                        }catch(InterruptedException e){}
                        StdDraw.setPenColor(StdDraw.WHITE);
                        StdDraw.filledRectangle(650, 110, 90,50);
                        StdDraw.setPenColor();
                        StdDraw.show();
                    }
                    else{
                        StdDraw.text(650,110,"G5 -> ? ");
                        StdDraw.show();
                        double _x2 ; 
                        double _y2 ; 
                        while(true){                                                    // destination loop.
                            if(StdDraw.isMousePressed()){
                                _x2 = StdDraw.mouseX() ; 
                                _y2 = StdDraw.mouseY() ; 
                                if(((_x2>=594&&_x2<=647)&&(_y2>=242&&_y2<=444))&&(H5.numberOfX < 2)){     // H5'e git.
                                    for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                                        if(Board.hamleler.get(i)==1){
                                            Board.hamleler.remove(i);
                                            G5.numberOfY -=1 ; 
                                            H5.numberOfY +=1 ; 
                                            if(H5.numberOfX ==1){
                                                H5.numberOfX = 0 ; 
                                                E3.numberOfX += 1 ; 
                                            }
                                            StdDraw.setPenColor(StdDraw.WHITE) ; 
                                            StdDraw.filledRectangle(650,110,30,50);
                                            StdDraw.setPenColor();
                                            StdDraw.show();
                                            StdDraw.text(650,110,"G5 -> H5");
                                            Files.hamlePrint("G5","H5");
                                            StdDraw.show();
                                            try{
                                                Thread.sleep(300);
                                            }catch(InterruptedException e){}
                                            return ; 
                                        }
                                        else{
                                            continue ; 
                                        }
                                    }
                                }
                                else if(((_x2>=659&&_x2<=715)&&(_y2>=242&&_y2<=444))&&(I5.numberOfX < 2)){   // I5'e git.
                                    
                                    for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                                        if(Board.hamleler.get(i)==2){
                                            Board.hamleler.remove(i);
                                            G5.numberOfY -=1 ; 
                                            I5.numberOfY +=1 ; 
                                            if(I5.numberOfX ==1){
                                                I5.numberOfX = 0 ; 
                                                E3.numberOfX += 1 ; 
                                            }
                                            StdDraw.setPenColor(StdDraw.WHITE) ; 
                                            StdDraw.filledRectangle(650,110,30,50);
                                            StdDraw.setPenColor();
                                            StdDraw.show();
                                            StdDraw.text(650,110,"G5 -> I5");
                                            Files.hamlePrint("G5","I5");
                                            StdDraw.show();
                                            try{
                                                Thread.sleep(300);
                                            }catch(InterruptedException e){}
                                            return ; 
                                        }
                                        else{
                                            continue ; 
                                        }
                                    }
                                }
                                else if(((_x2>=725&&_x2<=781)&&(_y2>=242&&_y2<=444))&&(J5.numberOfX < 2)){   // J5'e git
                                    
                                    for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                                        if(Board.hamleler.get(i)==3){
                                            Board.hamleler.remove(i);
                                            G5.numberOfY -=1 ; 
                                            J5.numberOfY +=1 ; 
                                            if(J5.numberOfX ==1){
                                                J5.numberOfX = 0 ; 
                                                E3.numberOfX += 1 ; 
                                            }
                                            StdDraw.setPenColor(StdDraw.WHITE) ; 
                                            StdDraw.filledRectangle(650,110,30,50);
                                            StdDraw.setPenColor();
                                            StdDraw.show();
                                            StdDraw.text(650,110,"G5 -> J5");
                                            Files.hamlePrint("G5","J5");
                                            StdDraw.show();
                                            try{
                                                Thread.sleep(300);
                                            }catch(InterruptedException e){}
                                            return ; 
                                        }
                                        else{
                                            continue ; 
                                        }
                                    }
                                }
                                else if(((_x2>=788&&_x2<=842)&&(_y2>=242&&_y2<=444))&&(K5.numberOfX < 2)){      //K5 icin.
                                    
                                    for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                                        if(Board.hamleler.get(i)==4){
                                            Board.hamleler.remove(i);
                                            G5.numberOfY -=1 ; 
                                            K5.numberOfY +=1 ; 
                                            if(K5.numberOfX ==1){
                                                K5.numberOfX = 0 ; 
                                                E3.numberOfX += 1 ; 
                                            }
                                            StdDraw.setPenColor(StdDraw.WHITE) ; 
                                            StdDraw.filledRectangle(650,110,30,50);
                                            StdDraw.setPenColor();
                                            StdDraw.show();
                                            StdDraw.text(650,110,"G5 -> K5");
                                            Files.hamlePrint("G5","K5");
                                            StdDraw.show();
                                            try{
                                                Thread.sleep(300);
                                            }catch(InterruptedException e){}
                                            return ; 
                                        }
                                        else{
                                            continue ; 
                                        }
                                    }
                                }
                                else if(((_x2>=854&&_x2<=910)&&(_y2>=242&&_y2<=444))&&(L5.numberOfX < 2)){    // L5 icin.
                                    for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                                        if(Board.hamleler.get(i)==5){
                                            Board.hamleler.remove(i);
                                            G5.numberOfY -=1 ; 
                                            L5.numberOfY +=1 ; 
                                            if(L5.numberOfX ==1){
                                                L5.numberOfX = 0 ; 
                                                E3.numberOfX += 1 ; 
                                            }
                                            StdDraw.setPenColor(StdDraw.WHITE) ; 
                                            StdDraw.filledRectangle(650,110,30,50);
                                            StdDraw.setPenColor();
                                            StdDraw.show();
                                            StdDraw.text(650,110,"G5 -> L5");
                                            Files.hamlePrint("G5","L5");
                                            StdDraw.show();
                                            try{
                                                Thread.sleep(300);
                                            }catch(InterruptedException e){}
                                            return ; 
                                        }
                                        else{
                                            continue ; 
                                        }
                                    }
                                }
                                else if(((_x2>=952&&_x2<=994)&&(_y2>=242&&_y2<=444))){     // TopY icin
                                    for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                                        if(Board.hamleler.get(i)==6){
                                            Board.hamleler.remove(i);
                                            G5.numberOfY -=1 ; 
                                            TopY.numberOfY +=1 ; 
                                            StdDraw.setPenColor(StdDraw.WHITE) ; 
                                            StdDraw.filledRectangle(650,110,30,50);
                                            StdDraw.setPenColor();
                                            StdDraw.show();
                                            StdDraw.text(650,110,"G5 -> TopY");
                                            Files.hamlePrint("G5","Topy");
                                            StdDraw.show();
                                            try{
                                                Thread.sleep(300);
                                            }catch(InterruptedException e){}
                                            return ; 
                                        }
                                    }
                                }    
                            }
                        }
                    }
                }
                else if(((_x>=594 && _x<=647)&&(_y>=242 && _y<=444)) && H5.numberOfY > 0){     // fromH5
                    if(checkH5 == false){
                        StdDraw.setPenColor(StdDraw.RED) ; 
                        StdDraw.text(650,900,"No valid move from H5.");
                        StdDraw.show();
                        StdDraw.setPenColor();
                        try{
                            Thread.sleep(300);
                        }catch(InterruptedException e){}
                        StdDraw.setPenColor(StdDraw.WHITE);
                        StdDraw.filledRectangle(650, 110, 90,50);
                        StdDraw.setPenColor();
                        StdDraw.show();
                    }
                    else{
                        StdDraw.text(650,110,"H5 -> ? ");
                        StdDraw.show();
                        double _x2 ; 
                        double _y2 ; 
                        while(true){                                                    // destination loop.
                            if(StdDraw.isMousePressed()){
                                _x2 = StdDraw.mouseX() ; 
                                _y2 = StdDraw.mouseY() ; 
                                if(((_x2>=659&&_x2<=715)&&(_y2>=242&&_y2<=444))&&(I5.numberOfX < 2)){     // I5'e git.
                                    for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                                        if(Board.hamleler.get(i)==1){
                                            Board.hamleler.remove(i);
                                            H5.numberOfY -=1 ; 
                                            I5.numberOfY +=1 ; 
                                            if(I5.numberOfX ==1){
                                                I5.numberOfX = 0 ; 
                                                E3.numberOfX += 1 ; 
                                            }
                                            StdDraw.setPenColor(StdDraw.WHITE) ; 
                                            StdDraw.filledRectangle(650,110,30,50);
                                            StdDraw.setPenColor();
                                            StdDraw.show();
                                            StdDraw.text(650,110,"H5 -> I5");
                                            Files.hamlePrint("H5","I5");
                                            StdDraw.show();
                                            try{
                                                Thread.sleep(300);
                                            }catch(InterruptedException e){}
                                            return ; 
                                        }
                                        else{
                                            continue ; 
                                        }
                                    }
                                }
                                else if(((_x2>=725&&_x2<=781)&&(_y2>=242&&_y2<=444))&&(J5.numberOfX < 2)){   // J5'e git.
                                    
                                    for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                                        if(Board.hamleler.get(i)==2){
                                            Board.hamleler.remove(i);
                                            H5.numberOfY -=1 ; 
                                            J5.numberOfY +=1 ; 
                                            if(J5.numberOfX ==1){
                                                J5.numberOfX = 0 ; 
                                                E3.numberOfX += 1 ; 
                                            }
                                            StdDraw.setPenColor(StdDraw.WHITE) ; 
                                            StdDraw.filledRectangle(650,110,30,50);
                                            StdDraw.setPenColor();
                                            StdDraw.show();
                                            StdDraw.text(650,110,"H5 -> J5");
                                            Files.hamlePrint("H5","J5");
                                            StdDraw.show();
                                            try{
                                                Thread.sleep(300);
                                            }catch(InterruptedException e){}
                                            return ; 
                                        }
                                        else{
                                            continue ; 
                                        }
                                    }
                                }
                                else if(((_x2>=788&&_x2<=842)&&(_y2>=242&&_y2<=444))&&(K5.numberOfX < 2)){   // K5'e git
                                    
                                    for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                                        if(Board.hamleler.get(i)==3){
                                            Board.hamleler.remove(i);
                                            H5.numberOfY -=1 ; 
                                            K5.numberOfY +=1 ; 
                                            if(K5.numberOfX ==1){
                                                K5.numberOfX = 0 ; 
                                                E3.numberOfX += 1 ; 
                                            }
                                            StdDraw.setPenColor(StdDraw.WHITE) ; 
                                            StdDraw.filledRectangle(650,110,30,50);
                                            StdDraw.setPenColor();
                                            StdDraw.show();
                                            StdDraw.text(650,110,"H5 -> K5");
                                            Files.hamlePrint("H5","K5");
                                            StdDraw.show();
                                            try{
                                                Thread.sleep(300);
                                            }catch(InterruptedException e){}
                                            return ; 
                                        }
                                        else{
                                            continue ; 
                                        }
                                    }
                                }
                                else if(((_x2>=854&&_x2<=910)&&(_y2>=242&&_y2<=444))&&(L5.numberOfX < 2)){      //L5 icin.
                                    
                                    for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                                        if(Board.hamleler.get(i)==4){
                                            Board.hamleler.remove(i);
                                            H5.numberOfY -=1 ; 
                                            L5.numberOfY +=1 ; 
                                            if(L5.numberOfX ==1){
                                                L5.numberOfX = 0 ; 
                                                E3.numberOfX += 1 ; 
                                            }
                                            StdDraw.setPenColor(StdDraw.WHITE) ; 
                                            StdDraw.filledRectangle(650,110,30,50);
                                            StdDraw.setPenColor();
                                            StdDraw.show();
                                            StdDraw.text(650,110,"H5 -> L5");
                                            Files.hamlePrint("H5","L5");
                                            StdDraw.show();
                                            try{
                                                Thread.sleep(300);
                                            }catch(InterruptedException e){}
                                            return ; 
                                        }
                                        else{
                                            continue ; 
                                        }
                                    }
                                }
                                else if(((_x2>=952&&_x2<=994)&&(_y2>=242&&_y2<=444))){     // TopY icin
                                    for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                                        if(Board.hamleler.get(i)>=5){
                                            Board.hamleler.remove(i);
                                            H5.numberOfY -=1 ; 
                                            TopY.numberOfY +=1 ; 
                                            StdDraw.setPenColor(StdDraw.WHITE) ; 
                                            StdDraw.filledRectangle(650,110,30,50);
                                            StdDraw.setPenColor();
                                            StdDraw.show();
                                            StdDraw.text(650,110,"H5 -> TopY");
                                             Files.hamlePrint("H5","TopY");
                                            StdDraw.show();
                                            try{
                                                Thread.sleep(300);
                                            }catch(InterruptedException e){}
                                            return ; 
                                        }
                                    }
                                }    
                            }
                        }
                    }
                }
                else if(((_x>=659 && _x<=715)&&(_y>=242 && _y<=444)) && I5.numberOfY > 0){         // from I5
                     if(checkI5 == false){
                        StdDraw.setPenColor(StdDraw.RED) ; 
                        StdDraw.text(650,900,"No valid move from I5.");
                        StdDraw.show();
                        StdDraw.setPenColor();
                        try{
                            Thread.sleep(300);
                        }catch(InterruptedException e){}
                        StdDraw.setPenColor(StdDraw.WHITE);
                        StdDraw.filledRectangle(650, 110, 90,50);
                        StdDraw.setPenColor();
                        StdDraw.show();
                    }
                    else{
                        StdDraw.text(650,110,"I5 -> ? ");
                        StdDraw.show();
                        double _x2 ; 
                        double _y2 ; 
                        while(true){                                                    // destination loop.
                            if(StdDraw.isMousePressed()){
                                _x2 = StdDraw.mouseX() ; 
                                _y2 = StdDraw.mouseY() ; 
                                if(((_x2>=725&&_x2<=781)&&(_y2>=242&&_y2<=444))&&(J5.numberOfX < 2)){     // J5'e git.
                                    for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                                        if(Board.hamleler.get(i)==1){
                                            Board.hamleler.remove(i);
                                            I5.numberOfY -=1 ; 
                                            J5.numberOfY +=1 ; 
                                            if(J5.numberOfX ==1){
                                                J5.numberOfX = 0 ; 
                                                E3.numberOfX += 1 ; 
                                            }
                                            StdDraw.setPenColor(StdDraw.WHITE) ; 
                                            StdDraw.filledRectangle(650,110,30,50);
                                            StdDraw.setPenColor();
                                            StdDraw.show();
                                            StdDraw.text(650,110,"I5 -> J5");
                                            Files.hamlePrint("I5","J5");
                                            StdDraw.show();
                                            try{
                                                Thread.sleep(300);
                                            }catch(InterruptedException e){}
                                            return ; 
                                        }
                                        else{
                                            continue ; 
                                        }
                                    }
                                }
                                else if(((_x2>=788&&_x2<=842)&&(_y2>=242&&_y2<=444))&&(K5.numberOfX < 2)){   // K5'e git.
                                    
                                    for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                                        if(Board.hamleler.get(i)==2){
                                            Board.hamleler.remove(i);
                                            I5.numberOfY -=1 ; 
                                            K5.numberOfY +=1 ; 
                                            if(K5.numberOfX ==1){
                                                K5.numberOfX = 0 ; 
                                                E3.numberOfX += 1 ; 
                                            }
                                            StdDraw.setPenColor(StdDraw.WHITE) ; 
                                            StdDraw.filledRectangle(650,110,30,50);
                                            StdDraw.setPenColor();
                                            StdDraw.show();
                                            StdDraw.text(650,110,"I5 -> K5");
                                            Files.hamlePrint("I5","K5");
                                            StdDraw.show();
                                            try{
                                                Thread.sleep(300);
                                            }catch(InterruptedException e){}
                                            return ; 
                                        }
                                        else{
                                            continue ; 
                                        }
                                    }
                                }
                                else if(((_x2>=854&&_x2<=910)&&(_y2>=242&&_y2<=444))&&(L5.numberOfX < 2)){   // L5'e git
                                    
                                    for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                                        if(Board.hamleler.get(i)==3){
                                            Board.hamleler.remove(i);
                                            I5.numberOfY -=1 ; 
                                            L5.numberOfY +=1 ; 
                                            if(L5.numberOfX ==1){
                                                L5.numberOfX = 0 ; 
                                                E3.numberOfX += 1 ; 
                                            }
                                            StdDraw.setPenColor(StdDraw.WHITE) ; 
                                            StdDraw.filledRectangle(650,110,30,50);
                                            StdDraw.setPenColor();
                                            StdDraw.show();
                                            StdDraw.text(650,110,"I5 -> L5");
                                            Files.hamlePrint("I5","L5");
                                            StdDraw.show();
                                            try{
                                                Thread.sleep(300);
                                            }catch(InterruptedException e){}
                                            return ; 
                                        }
                                        else{
                                            continue ; 
                                        }
                                    }
                                }
                                else if(((_x2>=952&&_x2<=994)&&(_y2>=242&&_y2<=444))){     // TopY icin
                                    for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                                        if(Board.hamleler.get(i)>=4){
                                            Board.hamleler.remove(i);
                                            I5.numberOfY -=1 ; 
                                            TopY.numberOfY +=1 ; 
                                            StdDraw.setPenColor(StdDraw.WHITE) ; 
                                            StdDraw.filledRectangle(650,110,30,50);
                                            StdDraw.setPenColor();
                                            StdDraw.show();
                                            StdDraw.text(650,110,"I5 -> TopY");
                                            Files.hamlePrint("I5","TopY");
                                            StdDraw.show();
                                            try{
                                                Thread.sleep(300);
                                            }catch(InterruptedException e){}
                                            return ; 
                                        }
                                    }
                                }    
                            }
                        }
                    }
                }
                else if(((_x>=725 && _x<=781)&&(_y>=242 && _y<=444)) && J5.numberOfY > 0){       // from J5
                    if(checkJ5 == false){
                        StdDraw.setPenColor(StdDraw.RED) ; 
                        StdDraw.text(650,900,"No valid move from J5.");
                        StdDraw.show();
                        StdDraw.setPenColor();
                        try{
                            Thread.sleep(300);
                        }catch(InterruptedException e){}
                        StdDraw.setPenColor(StdDraw.WHITE);
                        StdDraw.filledRectangle(650, 110, 90,50);
                        StdDraw.setPenColor();
                        StdDraw.show();
                    }
                    else{
                        StdDraw.text(650,110,"J5 -> ? ");
                        StdDraw.show();
                        double _x2 ; 
                        double _y2 ; 
                        while(true){                                                    // destination loop.
                            if(StdDraw.isMousePressed()){
                                _x2 = StdDraw.mouseX() ; 
                                _y2 = StdDraw.mouseY() ; 
                                if(((_x2>=788&&_x2<=842)&&(_y2>=242&&_y2<=444))&&(K5.numberOfX < 2)){     // K5'e git.
                                    for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                                        if(Board.hamleler.get(i)==1){
                                            Board.hamleler.remove(i);
                                            J5.numberOfY -=1 ; 
                                            K5.numberOfY +=1 ; 
                                            if(K5.numberOfX ==1){
                                                K5.numberOfX = 0 ; 
                                                E3.numberOfX += 1 ; 
                                            }
                                            StdDraw.setPenColor(StdDraw.WHITE) ; 
                                            StdDraw.filledRectangle(650,110,30,50);
                                            StdDraw.setPenColor();
                                            StdDraw.show();
                                            StdDraw.text(650,110,"J5 -> K5");
                                            Files.hamlePrint("J5","K5");
                                            StdDraw.show();
                                            try{
                                                Thread.sleep(300);
                                            }catch(InterruptedException e){}
                                            return ; 
                                        }
                                        else{
                                            continue ; 
                                        }
                                    }
                                }
                                else if(((_x2>=854&&_x2<=910)&&(_y2>=242&&_y2<=444))&&(L5.numberOfX < 2)){   // L5'e git.
                                    
                                    for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                                        if(Board.hamleler.get(i)==2){
                                            Board.hamleler.remove(i);
                                            J5.numberOfY -=1 ; 
                                            L5.numberOfY +=1 ; 
                                            if(L5.numberOfX ==1){
                                                L5.numberOfX = 0 ; 
                                                E3.numberOfX += 1 ; 
                                            }
                                            StdDraw.setPenColor(StdDraw.WHITE) ; 
                                            StdDraw.filledRectangle(650,110,30,50);
                                            StdDraw.setPenColor();
                                            StdDraw.show();
                                            StdDraw.text(650,110,"J5 -> L5");
                                            Files.hamlePrint("J5","L5");
                                            StdDraw.show();
                                            try{
                                                Thread.sleep(300);
                                            }catch(InterruptedException e){}
                                            return ; 
                                        }
                                        else{
                                            continue ; 
                                        }
                                    }
                                }
                                else if(((_x2>=952&&_x2<=994)&&(_y2>=242&&_y2<=444))){     // TopY icin
                                    for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                                        if(Board.hamleler.get(i)>=3){
                                            Board.hamleler.remove(i);
                                            J5.numberOfY -=1 ; 
                                            TopY.numberOfY +=1 ; 
                                            StdDraw.setPenColor(StdDraw.WHITE) ; 
                                            StdDraw.filledRectangle(650,110,30,50);
                                            StdDraw.setPenColor();
                                            StdDraw.show();
                                            StdDraw.text(650,110,"J5 -> TopY");
                                            Files.hamlePrint("J5","TopY");
                                            StdDraw.show();
                                            try{
                                                Thread.sleep(300);
                                            }catch(InterruptedException e){}
                                            return ; 
                                        }
                                    }
                                }    
                            }
                        }
                    }
                }
                else if(((_x>=788 && _x<=842)&&(_y>=242 && _y<=444)) && K5.numberOfY > 0){            // from K5
                    if(checkK5 == false){
                        StdDraw.setPenColor(StdDraw.RED) ; 
                        StdDraw.text(650,900,"No valid move from K5.");
                        StdDraw.show();
                        StdDraw.setPenColor();
                        try{
                            Thread.sleep(300);
                        }catch(InterruptedException e){}
                        StdDraw.setPenColor(StdDraw.WHITE);
                        StdDraw.filledRectangle(650, 110, 90,50);
                        StdDraw.setPenColor();
                        StdDraw.show();
                    }
                    else{
                        StdDraw.text(650,110,"K5 -> ? ");
                        StdDraw.show();
                        double _x2 ; 
                        double _y2 ; 
                        while(true){                                                    // destination loop.
                            if(StdDraw.isMousePressed()){
                                _x2 = StdDraw.mouseX() ; 
                                _y2 = StdDraw.mouseY() ; 
                                if(((_x2>=854&&_x2<=910)&&(_y2>=242&&_y2<=444))&&(L5.numberOfX < 2)){     // L5'e git.
                                    for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                                        if(Board.hamleler.get(i)==1){
                                            Board.hamleler.remove(i);
                                            K5.numberOfY -=1 ; 
                                            L5.numberOfY +=1 ; 
                                            if(L5.numberOfX ==1){
                                                L5.numberOfX = 0 ; 
                                                E3.numberOfX += 1 ; 
                                            }
                                            StdDraw.setPenColor(StdDraw.WHITE) ; 
                                            StdDraw.filledRectangle(650,110,30,50);
                                            StdDraw.setPenColor();
                                            StdDraw.show();
                                            StdDraw.text(650,110,"K5 -> L5");
                                            Files.hamlePrint("K5","L5");
                                            StdDraw.show();
                                            try{
                                                Thread.sleep(300);
                                            }catch(InterruptedException e){}
                                            return ; 
                                        }
                                        else{
                                            continue ; 
                                        }
                                    }
                                }
                                else if(((_x2>=952&&_x2<=994)&&(_y2>=242&&_y2<=444))){     // TopY icin
                                    for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                                        if(Board.hamleler.get(i)>=2){
                                            Board.hamleler.remove(i);
                                            K5.numberOfY -=1 ; 
                                            TopY.numberOfY +=1 ; 
                                            StdDraw.setPenColor(StdDraw.WHITE) ; 
                                            StdDraw.filledRectangle(650,110,30,50);
                                            StdDraw.setPenColor();
                                            StdDraw.show();
                                            StdDraw.text(650,110,"K5 -> TopY");
                                            Files.hamlePrint("K5","TopY");
                                            StdDraw.show();
                                            try{
                                                Thread.sleep(300);
                                            }catch(InterruptedException e){}
                                            return ; 
                                        }
                                    }
                                }    
                            }
                        }
                    }
                }
                else if(((_x>=854 && _x<=910)&&(_y>=242 && _y<=444)) && L5.numberOfY > 0){        // from L5
                     if(checkL5 == false){
                        StdDraw.setPenColor(StdDraw.RED) ; 
                        StdDraw.text(650,900,"No valid move from L5.");
                        StdDraw.show();
                        StdDraw.setPenColor();
                        try{
                            Thread.sleep(300);
                        }catch(InterruptedException e){}
                        StdDraw.setPenColor(StdDraw.WHITE);
                        StdDraw.filledRectangle(650, 110, 90,50);
                        StdDraw.setPenColor();
                        StdDraw.show();
                    }
                    else{
                        StdDraw.text(650,110,"L5 -> ? ");
                        StdDraw.show();
                        double _x2 ; 
                        double _y2 ; 
                        while(true){                                                    // destination loop.
                            if(StdDraw.isMousePressed()){
                                _x2 = StdDraw.mouseX() ; 
                                _y2 = StdDraw.mouseY() ; 
                                if(((_x2>=952&&_x2<=994)&&(_y2>=242&&_y2<=444))){     // TopY icin
                                    for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                                        if(Board.hamleler.get(i)>=1){
                                            Board.hamleler.remove(i);
                                            L5.numberOfY -=1 ; 
                                            TopY.numberOfY +=1 ; 
                                            StdDraw.setPenColor(StdDraw.WHITE) ; 
                                            StdDraw.filledRectangle(650,110,30,50);
                                            StdDraw.setPenColor();
                                            StdDraw.show();
                                            StdDraw.text(650,110,"L5 -> TopY");
                                            Files.hamlePrint("L5","TopY");
                                            StdDraw.show();
                                            try{
                                                Thread.sleep(300);
                                            }catch(InterruptedException e){}
                                            return ; 
                                        }
                                    }
                                }    
                            }
                        }
                    }
                }
            }                        
            
        }                                      
        
    }     
    
    static void userYNormal(){
        boolean checkA1=false, checkB1 = false, checkC1 = false, checkD1 = false, checkE1=false, checkF1=false, checkG1=false, checkH1 = false ;  
        boolean checkI1=false, checkJ1=false, checkK1=false, checkL1=false ; 
        boolean checkA5=false, checkB5=false, checkC5=false, checkD5=false, checkE5=false, checkF5=false, checkG5 = false ; 
        boolean checkH5=false, checkI5=false, checkJ5=false, checkK5=false, checkL5=false ; 
        if(L1.numberOfY > 0){
            for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                if(Board.hamleler.get(i)==1){
                    if(K1.numberOfX < 2){
                        checkL1 = true ;  break ; 
                    }
                }
                else if(Board.hamleler.get(i)==2){
                    if(J1.numberOfX < 2 ){
                        checkL1 = true  ; break ; 
                    }
                }
                else if(Board.hamleler.get(i)==3){
                    if(I1.numberOfX< 2 ){
                        checkL1 = true ;  break ; 
                    }
                }
                else if(Board.hamleler.get(i)==4){
                    if(H1.numberOfX < 2 ){
                        checkL1 = true  ; break ; 
                    }
                }
                else if (Board.hamleler.get(i)==5){
                    if(G1.numberOfX < 2 ){
                        checkL1 = true ;  break ; 
                    }
                }
                else{                           // dice is 6 means .
                    if(F1.numberOfX < 2 ){
                        checkL1 = true  ;  break  ;
                    } 
                }
            }
        }
        if(K1.numberOfY > 0){
            for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                if(Board.hamleler.get(i)==1){
                    if(J1.numberOfX < 2){
                        checkK1 = true ;  break ; 
                    }
                }
                else if(Board.hamleler.get(i)==2){
                    if(I1.numberOfX < 2 ){
                        checkK1 = true  ; break ; 
                    }
                }
                else if(Board.hamleler.get(i)==3){
                    if(H1.numberOfX< 2 ){
                        checkK1 = true ;  break ; 
                    }
                }
                else if(Board.hamleler.get(i)==4){
                    if(G1.numberOfX < 2 ){
                        checkK1 = true  ; break ; 
                    }
                }
                else if (Board.hamleler.get(i)==5){
                    if(F1.numberOfX < 2 ){
                        checkK1 = true ;  break ; 
                    }
                }
                else{                           // dice is 6 means .
                    if(E1.numberOfX < 2 ){
                        checkK1 = true  ;  break  ;
                    } 
                }
            }
        }
        if(J1.numberOfY > 0){
            for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                if(Board.hamleler.get(i)==1){
                    if(I1.numberOfX < 2){
                        checkJ1 = true ;  break ; 
                    }
                }
                else if(Board.hamleler.get(i)==2){
                    if(H1.numberOfX < 2 ){
                        checkJ1 = true  ; break ; 
                    }
                }
                else if(Board.hamleler.get(i)==3){
                    if(G1.numberOfX< 2 ){
                        checkJ1 = true ;  break ; 
                    }
                }
                else if(Board.hamleler.get(i)==4){
                    if(F1.numberOfX < 2 ){
                        checkJ1 = true  ; break ; 
                    }
                }
                else if (Board.hamleler.get(i)==5){
                    if(E1.numberOfX < 2 ){
                        checkJ1 = true ;  break ; 
                    }
                }
                else{                           // dice is 6 means .
                    if(D1.numberOfX < 2 ){
                        checkJ1 = true  ;  break  ;
                    } 
                }
            }
        }
        if(I1.numberOfY > 0){
            for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                if(Board.hamleler.get(i)==1){
                    if(H1.numberOfX < 2){
                        checkI1 = true ;  break ; 
                    }
                }
                else if(Board.hamleler.get(i)==2){
                    if(G1.numberOfX < 2 ){
                        checkI1 = true  ; break ; 
                    }
                }
                else if(Board.hamleler.get(i)==3){
                    if(F1.numberOfX< 2 ){
                        checkI1 = true ;  break ; 
                    }
                }
                else if(Board.hamleler.get(i)==4){
                    if(E1.numberOfX < 2 ){
                        checkI1 = true  ; break ; 
                    }
                }
                else if (Board.hamleler.get(i)==5){
                    if(D1.numberOfX < 2 ){
                        checkI1 = true ;  break ; 
                    }
                }
                else{                           // dice is 6 means .
                    if(C1.numberOfX < 2 ){
                        checkI1 = true  ;  break  ;
                    } 
                }
            }
        }
        if(H1.numberOfY > 0){
            for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                if(Board.hamleler.get(i)==1){
                    if(G1.numberOfX < 2){
                        checkH1 = true ;  break ; 
                    }
                }
                else if(Board.hamleler.get(i)==2){
                    if(F1.numberOfX < 2 ){
                        checkH1 = true  ; break ; 
                    }
                }
                else if(Board.hamleler.get(i)==3){
                    if(E1.numberOfX< 2 ){
                        checkH1 = true ;  break ; 
                    }
                }
                else if(Board.hamleler.get(i)==4){
                    if(D1.numberOfX < 2 ){
                        checkH1 = true  ; break ; 
                    }
                }
                else if (Board.hamleler.get(i)==5){
                    if(C1.numberOfX < 2 ){
                        checkH1 = true ;  break ; 
                    }
                }
                else{                           // dice is 6 means .
                    if(B1.numberOfX < 2 ){
                        checkH1 = true  ;  break  ;
                    } 
                }
            }
        }
        if(G1.numberOfY > 0){
            for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                if(Board.hamleler.get(i)==1){
                    if(F1.numberOfX < 2){
                        checkG1 = true ;  break ; 
                    }
                }
                else if(Board.hamleler.get(i)==2){
                    if(E1.numberOfX < 2 ){
                        checkG1 = true  ; break ; 
                    }
                }
                else if(Board.hamleler.get(i)==3){
                    if(D1.numberOfX< 2 ){
                        checkG1 = true ;  break ; 
                    }
                }
                else if(Board.hamleler.get(i)==4){
                    if(C1.numberOfX < 2 ){
                        checkG1 = true  ; break ; 
                    }
                }
                else if (Board.hamleler.get(i)==5){
                    if(B1.numberOfX < 2 ){
                        checkG1 = true ;  break ; 
                    }
                }
                else{                           // dice is 6 means .
                    if(A1.numberOfX < 2 ){
                        checkG1 = true  ;  break  ;
                    } 
                }
            }
        }
        if(F1.numberOfY > 0){
            for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                if(Board.hamleler.get(i)==1){
                    if(E1.numberOfX < 2){
                        checkF1 = true ;  break ; 
                    }
                }
                else if(Board.hamleler.get(i)==2){
                    if(D1.numberOfX < 2 ){
                        checkF1 = true  ; break ; 
                    }
                }
                else if(Board.hamleler.get(i)==3){
                    if(C1.numberOfX< 2 ){
                        checkF1 = true ;  break ; 
                    }
                }
                else if(Board.hamleler.get(i)==4){
                    if(B1.numberOfX < 2 ){
                        checkF1 = true  ; break ; 
                    }
                }
                else if (Board.hamleler.get(i)==5){
                    if(A1.numberOfX < 2 ){
                        checkF1 = true ;  break ; 
                    }
                }
                else{                           // dice is 6 means .
                    if(A5.numberOfX < 2 ){
                        checkF1 = true  ;  break  ;
                    } 
                }
            }
        }
        if(E1.numberOfY > 0){
            for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                if(Board.hamleler.get(i)==1){
                    if(D1.numberOfX < 2){
                        checkE1 = true ;  break ; 
                    }
                }
                else if(Board.hamleler.get(i)==2){
                    if(C1.numberOfX < 2 ){
                        checkE1 = true  ; break ; 
                    }
                }
                else if(Board.hamleler.get(i)==3){
                    if(B1.numberOfX< 2 ){
                        checkE1 = true ;  break ; 
                    }
                }
                else if(Board.hamleler.get(i)==4){
                    if(A1.numberOfX < 2 ){
                        checkE1 = true  ; break ; 
                    }
                }
                else if (Board.hamleler.get(i)==5){
                    if(A5.numberOfX < 2 ){
                        checkE1 = true ;  break ; 
                    }
                }
                else{                           // dice is 6 means .
                    if(B5.numberOfX < 2 ){
                        checkE1 = true  ;  break  ;
                    } 
                }
            }
        }
        if(D1.numberOfY > 0){
           for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                if(Board.hamleler.get(i)==1){
                    if(C1.numberOfX < 2){
                        checkD1 = true ;  break ; 
                    }
                }
                else if(Board.hamleler.get(i)==2){
                    if(B1.numberOfX < 2 ){
                        checkD1 = true  ; break ; 
                    }
                }
                else if(Board.hamleler.get(i)==3){
                    if(A1.numberOfX< 2 ){
                        checkD1 = true ;  break ; 
                    }
                }
                else if(Board.hamleler.get(i)==4){
                    if(A5.numberOfX < 2 ){
                        checkD1 = true  ; break ; 
                    }
                }
                else if (Board.hamleler.get(i)==5){
                    if(B5.numberOfX < 2 ){
                        checkD1 = true ;  break ; 
                    }
                }
                else{                           // dice is 6 means .
                    if(C5.numberOfX < 2 ){
                        checkD1 = true  ;  break  ;
                    } 
                }
            } 
        }
        if(C1.numberOfY > 0){
            for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                if(Board.hamleler.get(i)==1){
                    if(B1.numberOfX < 2){
                        checkC1 = true ;  break ; 
                    }
                }
                else if(Board.hamleler.get(i)==2){
                    if(A1.numberOfX < 2 ){
                        checkC1 = true  ; break ; 
                    }
                }
                else if(Board.hamleler.get(i)==3){
                    if(A5.numberOfX< 2 ){
                        checkC1 = true ;  break ; 
                    }
                }
                else if(Board.hamleler.get(i)==4){
                    if(B5.numberOfX < 2 ){
                        checkC1 = true  ; break ; 
                    }
                }
                else if (Board.hamleler.get(i)==5){
                    if(C5.numberOfX < 2 ){
                        checkC1 = true ;  break ; 
                    }
                }
                else{                           // dice is 6 means .
                    if(D5.numberOfX < 2 ){
                        checkC1 = true  ;  break  ;
                    } 
                }
            }
        }
        if(B1.numberOfY > 0){
            for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                if(Board.hamleler.get(i)==1){
                    if(A1.numberOfX < 2){
                        checkB1 = true ;  break ; 
                    }
                }
                else if(Board.hamleler.get(i)==2){
                    if(A5.numberOfX < 2 ){
                        checkB1 = true  ; break ; 
                    }
                }
                else if(Board.hamleler.get(i)==3){
                    if(B5.numberOfX< 2 ){
                        checkB1 = true ;  break ; 
                    }
                }
                else if(Board.hamleler.get(i)==4){
                    if(C5.numberOfX < 2 ){
                        checkB1 = true  ; break ; 
                    }
                }
                else if (Board.hamleler.get(i)==5){
                    if(D5.numberOfX < 2 ){
                        checkB1 = true ;  break ; 
                    }
                }
                else{                           // dice is 6 means .
                    if(E5.numberOfX < 2 ){
                        checkB1 = true  ;  break  ;
                    } 
                }
            }
        }
        if(A1.numberOfY > 0){
            for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                if(Board.hamleler.get(i)==1){
                    if(A5.numberOfX < 2){
                        checkA1 = true ;  break ; 
                    }
                }
                else if(Board.hamleler.get(i)==2){
                    if(B5.numberOfX < 2 ){
                        checkA1 = true  ; break ; 
                    }
                }
                else if(Board.hamleler.get(i)==3){
                    if(C5.numberOfX< 2 ){
                        checkA1 = true ;  break ; 
                    }
                }
                else if(Board.hamleler.get(i)==4){
                    if(D5.numberOfX < 2 ){
                        checkA1 = true  ; break ; 
                    }
                }
                else if (Board.hamleler.get(i)==5){
                    if(E5.numberOfX < 2 ){
                        checkA1 = true ;  break ; 
                    }
                }
                else{                           // dice is 6 means .
                    if(F5.numberOfX < 2 ){
                        checkA1 = true  ;  break  ;
                    } 
                }
            }
        }
        if(A5.numberOfY > 0){
            for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                if(Board.hamleler.get(i)==1){
                    if(B5.numberOfX < 2){
                        checkA5 = true ;  break ; 
                    }
                }
                else if(Board.hamleler.get(i)==2){
                    if(C5.numberOfX < 2 ){
                        checkA5 = true  ; break ; 
                    }
                }
                else if(Board.hamleler.get(i)==3){
                    if(D5.numberOfX< 2 ){
                        checkA5 = true ;  break ; 
                    }
                }
                else if(Board.hamleler.get(i)==4){
                    if(E5.numberOfX < 2 ){
                        checkA5 = true  ; break ; 
                    }
                }
                else if (Board.hamleler.get(i)==5){
                    if(F5.numberOfX < 2 ){
                        checkA5 = true ;  break ; 
                    }
                }
                else{                           // dice is 6 means .
                    if(G5.numberOfX < 2 ){
                        checkA5 = true  ;  break  ;
                    } 
                }
            }
        }
        if(B5.numberOfY > 0){
            for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                if(Board.hamleler.get(i)==1){
                    if(C5.numberOfX < 2){
                        checkB5 = true ;  break ; 
                    }
                }
                else if(Board.hamleler.get(i)==2){
                    if(D5.numberOfX < 2 ){
                        checkB5 = true  ; break ; 
                    }
                }
                else if(Board.hamleler.get(i)==3){
                    if(E5.numberOfX< 2 ){
                        checkB5 = true ;  break ; 
                    }
                }
                else if(Board.hamleler.get(i)==4){
                    if(F5.numberOfX < 2 ){
                        checkB5 = true  ; break ; 
                    }
                }
                else if (Board.hamleler.get(i)==5){
                    if(G5.numberOfX < 2 ){
                        checkB5 = true ;  break ; 
                    }
                }
                else{                           // dice is 6 means .
                    if(H5.numberOfX < 2 ){
                        checkB5 = true  ;  break  ;
                    } 
                }
            }
        }
        if(C5.numberOfY > 0){
            for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                if(Board.hamleler.get(i)==1){
                    if(D5.numberOfX < 2){
                        checkC5 = true ;  break ; 
                    }
                }
                else if(Board.hamleler.get(i)==2){
                    if(E5.numberOfX < 2 ){
                        checkC5 = true  ; break ; 
                    }
                }
                else if(Board.hamleler.get(i)==3){
                    if(F5.numberOfX< 2 ){
                        checkC5 = true ;  break ; 
                    }
                }
                else if(Board.hamleler.get(i)==4){
                    if(G5.numberOfX < 2 ){
                        checkC5 = true  ; break ; 
                    }
                }
                else if (Board.hamleler.get(i)==5){
                    if(H5.numberOfX < 2 ){
                        checkC5 = true ;  break ; 
                    }
                }
                else{                           // dice is 6 means .
                    if(I5.numberOfX < 2 ){
                        checkC5 = true  ;  break  ;
                    } 
                }
            }
        }
        if(D5.numberOfY > 0){
            for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                if(Board.hamleler.get(i)==1){
                    if(E5.numberOfX < 2){
                        checkD5 = true ;  break ; 
                    }
                }
                else if(Board.hamleler.get(i)==2){
                    if(F5.numberOfX < 2 ){
                        checkD5 = true  ; break ; 
                    }
                }
                else if(Board.hamleler.get(i)==3){
                    if(G5.numberOfX< 2 ){
                        checkD5 = true ;  break ; 
                    }
                }
                else if(Board.hamleler.get(i)==4){
                    if(H5.numberOfX < 2 ){
                        checkD5 = true  ; break ; 
                    }
                }
                else if (Board.hamleler.get(i)==5){
                    if(I5.numberOfX < 2 ){
                        checkD5 = true ;  break ; 
                    }
                }
                else{                           // dice is 6 means .
                    if(J5.numberOfX < 2 ){
                        checkD5 = true  ;  break  ;
                    } 
                }
            }
        }
        if(E5.numberOfY > 0){
            for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                if(Board.hamleler.get(i)==1){
                    if(F5.numberOfX < 2){
                        checkE5 = true ;  break ; 
                    }
                }
                else if(Board.hamleler.get(i)==2){
                    if(G5.numberOfX < 2 ){
                        checkE5 = true  ; break ; 
                    }
                }
                else if(Board.hamleler.get(i)==3){
                    if(H5.numberOfX< 2 ){
                        checkE5 = true ;  break ; 
                    }
                }
                else if(Board.hamleler.get(i)==4){
                    if(I5.numberOfX < 2 ){
                        checkE5 = true  ; break ; 
                    }
                }
                else if (Board.hamleler.get(i)==5){
                    if(J5.numberOfX < 2 ){
                        checkE5 = true ;  break ; 
                    }
                }
                else{                           // dice is 6 means .
                    if(K5.numberOfX < 2 ){
                        checkE5 = true  ;  break  ;
                    } 
                }
            }
        }
        if(F5.numberOfY > 0){
            for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                if(Board.hamleler.get(i)==1){
                    if(G5.numberOfX < 2){
                        checkF5 = true ;  break ; 
                    }
                }
                else if(Board.hamleler.get(i)==2){
                    if(H5.numberOfX < 2 ){
                        checkF5 = true  ; break ; 
                    }
                }
                else if(Board.hamleler.get(i)==3){
                    if(I5.numberOfX< 2 ){
                        checkF5 = true ;  break ; 
                    }
                }
                else if(Board.hamleler.get(i)==4){
                    if(J5.numberOfX < 2 ){
                        checkF5 = true  ; break ; 
                    }
                }
                else if (Board.hamleler.get(i)==5){
                    if(K5.numberOfX < 2 ){
                        checkF5 = true ;  break ; 
                    }
                }
                else{                           // dice is 6 means .
                    if(L5.numberOfX < 2 ){
                        checkF5 = true  ;  break  ;
                    } 
                }
            }
        }
        if(G5.numberOfY > 0){
            for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                if(Board.hamleler.get(i)==1){
                    if(H5.numberOfX < 2){
                        checkG5 = true ;  break ; 
                    }
                }
                else if(Board.hamleler.get(i)==2){
                    if(I5.numberOfX < 2 ){
                        checkG5 = true  ; break ; 
                    }
                }
                else if(Board.hamleler.get(i)==3){
                    if(J5.numberOfX< 2 ){
                        checkG5 = true ;  break ; 
                    }
                }
                else if(Board.hamleler.get(i)==4){
                    if(K5.numberOfX < 2 ){
                        checkG5 = true  ; break ; 
                    }
                }
                else if (Board.hamleler.get(i)==5){
                    if(L5.numberOfX < 2 ){
                        checkG5 = true ;  break ; 
                    }
                }
                
            }
        }
        if(H5.numberOfY > 0){
            for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                if(Board.hamleler.get(i)==1){
                    if(I5.numberOfX < 2){
                        checkH5 = true ;  break ; 
                    }
                }
                else if(Board.hamleler.get(i)==2){
                    if(J5.numberOfX < 2 ){
                        checkH5 = true  ; break ; 
                    }
                }
                else if(Board.hamleler.get(i)==3){
                    if(K5.numberOfX< 2 ){
                        checkH5 = true ;  break ; 
                    }
                }
                else if(Board.hamleler.get(i)==4){
                    if(L5.numberOfX < 2 ){
                        checkH5 = true  ; break ; 
                    }
                }
            }
        }
        if(I5.numberOfY > 0){
            for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                if(Board.hamleler.get(i)==1){
                    if(J5.numberOfX < 2){
                        checkI5 = true ;  break ; 
                    }
                }
                else if(Board.hamleler.get(i)==2){
                    if(K5.numberOfX < 2 ){
                        checkI5 = true  ; break ; 
                    }
                }
                else if(Board.hamleler.get(i)==3){
                    if(L5.numberOfX< 2 ){
                        checkI5 = true ;  break ; 
                    }
                }
            }
        }
        if(J5.numberOfY > 0){
            for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                if(Board.hamleler.get(i)==1){
                    if(K5.numberOfX < 2){
                        checkJ5 = true ;  break ; 
                    }
                }
                else if(Board.hamleler.get(i)==2){
                    if(L5.numberOfX < 2 ){
                        checkJ5 = true  ; break ; 
                    }
                }
            }
        }
        if(K5.numberOfY > 0){
            for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                if(Board.hamleler.get(i)==1){
                    if(L5.numberOfX < 2){
                        checkK5 = true ;  break ; 
                    }
                }
            }
        }
        if(L5.numberOfY > 0){
            
        }
        //----------------------------------------------------------------------
        if(!(checkL5 || checkK5 || checkJ5 || checkI5 || checkH5 || checkG5 || checkF5 ||checkE5 || checkD5 || checkC5 || checkB5 || checkA5
                || checkA1 || checkB1 || checkC1 || checkD1 || checkE1 || checkF1 || checkG1 || checkH1 || checkI1 || checkJ1 || checkK1 
                || checkL1)){
            StdDraw.setPenColor(StdDraw.RED);
            StdDraw.text(650,110, "No valid Move");
            StdDraw.setPenColor();
            StdDraw.show();
            try{
                Thread.sleep(300);
            }catch(InterruptedException e){}
            return ;  
        }
        StdDraw.text(650, 110, "Please select stone");
        StdDraw.show();
        try{
            Thread.sleep(2000);
        }catch(InterruptedException e){
            
        }        
        StdDraw.setPenColor(StdDraw.WHITE);
        StdDraw.filledRectangle(650, 110, 80, 50);
        StdDraw.show();

        StdDraw.setPenColor();
        //--------------------------------------------------------
        double _x, _y ; 
        while(true){                                                            // stone loop.
            if(StdDraw.isMousePressed()){
                _x  =StdDraw.mouseX()  ;
                _y = StdDraw.mouseY()  ; 
                if(((_x>=854 && _x<=910)&&(_y>=556 && _y<=757)) && L1.numberOfY > 0){               // from L1
                   if(checkL1 == false){
                        StdDraw.setPenColor(StdDraw.RED) ; 
                        StdDraw.text(650,110,"No valid move from L1.");
                        StdDraw.show();
                        StdDraw.setPenColor();
                        try{
                            Thread.sleep(300);
                        }catch(InterruptedException e){}
                        StdDraw.setPenColor(StdDraw.WHITE);
                        StdDraw.filledRectangle(650, 110, 90,50);
                        StdDraw.setPenColor();
                        StdDraw.show();
                    }
                   else{
                       StdDraw.text(650,110,"L1 -> ? ");
                        StdDraw.show();
                        double _x2 ; 
                        double _y2 ; 
                        while(true){                                                    // destination loop.
                            if(StdDraw.isMousePressed()){
                                _x2 = StdDraw.mouseX() ; 
                                _y2 = StdDraw.mouseY() ; 
                                if(((_x2>=788&&_x2<=842)&&(_y2>=556&&_y2<=757))&&(K1.numberOfX < 2)){     // K1'e git.
                                    for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                                        if(Board.hamleler.get(i)==1){
                                            Board.hamleler.remove(i);
                                            L1.numberOfY -=1 ; 
                                            K1.numberOfY +=1 ; 
                                            if(K1.numberOfX ==1){
                                                K1.numberOfX = 0 ; 
                                                E3.numberOfX += 1 ; 
                                            }
                                            StdDraw.setPenColor(StdDraw.WHITE) ; 
                                            StdDraw.filledRectangle(650,110,30,50);
                                            StdDraw.setPenColor();
                                            StdDraw.show();
                                            StdDraw.text(650,110,"L1 -> K1");
                                            Files.hamlePrint("L1","K1");
                                            StdDraw.show();
                                            try{
                                                Thread.sleep(300);
                                            }catch(InterruptedException e){}
                                            return ; 
                                        }
                                        else{
                                            continue ; 
                                        }
                                    }
                                }
                                else if(((_x2>=725&&_x2<=781)&&(_y2>=556&&_y2<=757))&&(J1.numberOfX < 2)){   // J1'e git.
                                    
                                    for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                                        if(Board.hamleler.get(i)==2){
                                            Board.hamleler.remove(i);
                                            L1.numberOfY -=1 ; 
                                            J1.numberOfY +=1 ; 
                                            if(J1.numberOfX ==1){
                                                J1.numberOfX = 0 ; 
                                                E3.numberOfX += 1 ; 
                                            }
                                            StdDraw.setPenColor(StdDraw.WHITE) ; 
                                            StdDraw.filledRectangle(650,110,30,50);
                                            StdDraw.setPenColor();
                                            StdDraw.show();
                                            StdDraw.text(650,110,"L1 -> J1");
                                            Files.hamlePrint("L1","J1");
                                            StdDraw.show();
                                            try{
                                                Thread.sleep(300);
                                            }catch(InterruptedException e){}
                                            return ; 
                                        }
                                        else{
                                            continue ; 
                                        }
                                    }
                                }
                                else if(((_x2>=659&&_x2<=715)&&(_y2>=556&&_y2<=757))&&(I1.numberOfX < 2)){   // I1'e git
                                    
                                    for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                                        if(Board.hamleler.get(i)==3){
                                            Board.hamleler.remove(i);
                                            L1.numberOfY -=1 ; 
                                            I1.numberOfY +=1 ; 
                                            if(I1.numberOfX ==1){
                                                I1.numberOfX = 0 ; 
                                                E3.numberOfX += 1 ; 
                                            }
                                            StdDraw.setPenColor(StdDraw.WHITE) ; 
                                            StdDraw.filledRectangle(650,110,30,50);
                                            StdDraw.setPenColor();
                                            StdDraw.show();
                                            StdDraw.text(650,110,"L1 -> I1");
                                            Files.hamlePrint("L1","I1");
                                            StdDraw.show();
                                            try{
                                                Thread.sleep(300);
                                            }catch(InterruptedException e){}
                                            return ; 
                                        }
                                        else{
                                            continue ; 
                                        }
                                    }
                                }
                                else if(((_x2>=594&&_x2<=647)&&(_y2>=556&&_y2<=757))&&(H1.numberOfX < 2)){      //H1 icin.
                                    
                                    for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                                        if(Board.hamleler.get(i)==4){
                                            Board.hamleler.remove(i);
                                            L1.numberOfY -=1 ; 
                                            H1.numberOfY +=1 ; 
                                            if(H1.numberOfX ==1){
                                                H1.numberOfX = 0 ; 
                                                E3.numberOfX += 1 ; 
                                            }
                                            StdDraw.setPenColor(StdDraw.WHITE) ; 
                                            StdDraw.filledRectangle(650,110,30,50);
                                            StdDraw.setPenColor();
                                            StdDraw.show();
                                            StdDraw.text(650,110,"L1 -> H1");
                                            Files.hamlePrint("L1","H1");
                                            StdDraw.show();
                                            try{
                                                Thread.sleep(300);
                                            }catch(InterruptedException e){}
                                            return ; 
                                        }
                                        else{
                                            continue ; 
                                        }
                                    }
                                }
                                else if(((_x2>=528&&_x2<=583)&&(_y2>=556&&_y2<=757))&&(G1.numberOfX < 2)){    // G1 icin.
                                    for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                                        if(Board.hamleler.get(i)==5){
                                            Board.hamleler.remove(i);
                                            L1.numberOfY -=1 ; 
                                            G1.numberOfY +=1 ; 
                                            if(G1.numberOfX ==1){
                                                G1.numberOfX = 0 ; 
                                                E3.numberOfX += 1 ; 
                                            }
                                            StdDraw.setPenColor(StdDraw.WHITE) ; 
                                            StdDraw.filledRectangle(650,110,30,50);
                                            StdDraw.setPenColor();
                                            StdDraw.show();
                                            StdDraw.text(650,110,"L1 -> G1");
                                            Files.hamlePrint("L1","G1");
                                            StdDraw.show();
                                            try{
                                                Thread.sleep(300);
                                            }catch(InterruptedException e){}
                                            return ; 
                                        }
                                        else{
                                            continue ; 
                                        }
                                    }
                                }
                                else if(((_x2>=419&&_x2<=478)&&(_y2>=556&&_y2<=757))&&(F1.numberOfX < 2)){     // F1 icin
                                    for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                                        if(Board.hamleler.get(i)==6){
                                            Board.hamleler.remove(i);
                                            L1.numberOfY -=1 ; 
                                            F1.numberOfY +=1 ; 
                                            if(F1.numberOfX ==1){
                                                F1.numberOfX = 0 ; 
                                                E3.numberOfX += 1 ; 
                                            }
                                            StdDraw.setPenColor(StdDraw.WHITE) ; 
                                            StdDraw.filledRectangle(650,110,30,50);
                                            StdDraw.setPenColor();
                                            StdDraw.show();
                                            StdDraw.text(650,110,"L1 -> F1");
                                            Files.hamlePrint("L1","F1");
                                            StdDraw.show();
                                            try{
                                                Thread.sleep(300);
                                            }catch(InterruptedException e){}
                                            return ; 
                                        }
                                    }
                                }    
                            }
                        }
                   }
                }
                else if(((_x>=788 && _x<=842)&&(_y>=556 && _y<=757)) && K1.numberOfY > 0){          // from K1
                    if(checkK1 == false){
                        StdDraw.setPenColor(StdDraw.RED) ; 
                        StdDraw.text(650,110,"No valid move from K1.");
                        StdDraw.show();
                        StdDraw.setPenColor();
                        try{
                            Thread.sleep(300);
                        }catch(InterruptedException e){}
                        StdDraw.setPenColor(StdDraw.WHITE);
                        StdDraw.filledRectangle(650, 110, 90,50);
                        StdDraw.setPenColor();
                        StdDraw.show();
                    }
                   else{
                       StdDraw.text(650,110,"K1 -> ? ");
                        StdDraw.show();
                        double _x2 ; 
                        double _y2 ; 
                        while(true){                                                    // destination loop.
                            if(StdDraw.isMousePressed()){
                                _x2 = StdDraw.mouseX() ; 
                                _y2 = StdDraw.mouseY() ; 
                                if(((_x2>=722&&_x2<=780)&&(_y2>=556&&_y2<=757))&&(J1.numberOfX < 2)){     // J1'e git.
                                    for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                                        if(Board.hamleler.get(i)==1){
                                            Board.hamleler.remove(i);
                                            K1.numberOfY -=1 ; 
                                            J1.numberOfY +=1 ; 
                                            if(J1.numberOfX ==1){
                                                J1.numberOfX = 0 ; 
                                                E3.numberOfX += 1 ; 
                                            }
                                            StdDraw.setPenColor(StdDraw.WHITE) ; 
                                            StdDraw.filledRectangle(650,110,30,50);
                                            StdDraw.setPenColor();
                                            StdDraw.show();
                                            StdDraw.text(650,110,"K1 -> J1");
                                            Files.hamlePrint("K1","J1");
                                            StdDraw.show();
                                            try{
                                                Thread.sleep(300);
                                            }catch(InterruptedException e){}
                                            return ; 
                                        }
                                        else{
                                            continue ; 
                                        }
                                    }
                                }
                                else if(((_x2>=659&&_x2<=715)&&(_y2>=556&&_y2<=757))&&(I1.numberOfX < 2)){   // I1'e git.
                                    
                                    for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                                        if(Board.hamleler.get(i)==2){
                                            Board.hamleler.remove(i);
                                            K1.numberOfY -=1 ; 
                                            I1.numberOfY +=1 ; 
                                            if(I1.numberOfX ==1){
                                                I1.numberOfX = 0 ; 
                                                E3.numberOfX += 1 ; 
                                            }
                                            StdDraw.setPenColor(StdDraw.WHITE) ; 
                                            StdDraw.filledRectangle(650,110,30,50);
                                            StdDraw.setPenColor();
                                            StdDraw.show();
                                            StdDraw.text(650,110,"K1 -> I1");
                                            Files.hamlePrint("K1","I1");
                                            StdDraw.show();
                                            try{
                                                Thread.sleep(300);
                                            }catch(InterruptedException e){}
                                            return ; 
                                        }
                                        else{
                                            continue ; 
                                        }
                                    }
                                }
                                else if(((_x2>=594&&_x2<=647)&&(_y2>=556&&_y2<=757))&&(H1.numberOfX < 2)){   // H1'e git
                                    
                                    for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                                        if(Board.hamleler.get(i)==3){
                                            Board.hamleler.remove(i);
                                            K1.numberOfY -=1 ; 
                                            H1.numberOfY +=1 ; 
                                            if(H1.numberOfX ==1){
                                                H1.numberOfX = 0 ; 
                                                E3.numberOfX += 1 ; 
                                            }
                                            StdDraw.setPenColor(StdDraw.WHITE) ; 
                                            StdDraw.filledRectangle(650,110,30,50);
                                            StdDraw.setPenColor();
                                            StdDraw.show();
                                            StdDraw.text(650,110,"K1 -> H1");
                                            Files.hamlePrint("K1","H1");
                                            StdDraw.show();
                                            try{
                                                Thread.sleep(300);
                                            }catch(InterruptedException e){}
                                            return ; 
                                        }
                                        else{
                                            continue ; 
                                        }
                                    }
                                }
                                else if(((_x2>=528&&_x2<=583)&&(_y2>=556&&_y2<=757))&&(G1.numberOfX < 2)){      //G1 icin.
                                    
                                    for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                                        if(Board.hamleler.get(i)==4){
                                            Board.hamleler.remove(i);
                                            K1.numberOfY -=1 ; 
                                            G1.numberOfY +=1 ; 
                                            if(G1.numberOfX ==1){
                                                G1.numberOfX = 0 ; 
                                                E3.numberOfX += 1 ; 
                                            }
                                            StdDraw.setPenColor(StdDraw.WHITE) ; 
                                            StdDraw.filledRectangle(650,110,30,50);
                                            StdDraw.setPenColor();
                                            StdDraw.show();
                                            StdDraw.text(650,110,"K1 -> G1");
                                            Files.hamlePrint("K1","G1");
                                            StdDraw.show();
                                            try{
                                                Thread.sleep(300);
                                            }catch(InterruptedException e){}
                                            return ; 
                                        }
                                        else{
                                            continue ; 
                                        }
                                    }
                                }
                                else if(((_x2>=419&&_x2<=478)&&(_y2>=556&&_y2<=757))&&(F1.numberOfX < 2)){    // F1 icin.
                                    for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                                        if(Board.hamleler.get(i)==5){
                                            Board.hamleler.remove(i);
                                            K1.numberOfY -=1 ; 
                                            F1.numberOfY +=1 ; 
                                            if(F1.numberOfX ==1){
                                                F1.numberOfX = 0 ; 
                                                E3.numberOfX += 1 ; 
                                            }
                                            StdDraw.setPenColor(StdDraw.WHITE) ; 
                                            StdDraw.filledRectangle(650,110,30,50);
                                            StdDraw.setPenColor();
                                            StdDraw.show();
                                            StdDraw.text(650,110,"K1 -> F1");
                                            Files.hamlePrint("K1","F1");
                                            StdDraw.show();
                                            try{
                                                Thread.sleep(300);
                                            }catch(InterruptedException e){}
                                            return ; 
                                        }
                                        else{
                                            continue ; 
                                        }
                                    }
                                }
                                else if(((_x2>=353&&_x2<=409)&&(_y2>=556&&_y2<=757))&&(E1.numberOfX < 2)){     // E1 icin
                                    for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                                        if(Board.hamleler.get(i)==6){
                                            Board.hamleler.remove(i);
                                            K1.numberOfY -=1 ; 
                                            E1.numberOfY +=1 ; 
                                            if(E1.numberOfX ==1){
                                                E1.numberOfX = 0 ; 
                                                E3.numberOfX += 1 ; 
                                            }
                                            StdDraw.setPenColor(StdDraw.WHITE) ; 
                                            StdDraw.filledRectangle(650,110,30,50);
                                            StdDraw.setPenColor();
                                            StdDraw.show();
                                            StdDraw.text(650,110,"K1 -> E1");
                                            Files.hamlePrint("K1","E1");
                                            StdDraw.show();
                                            try{
                                                Thread.sleep(300);
                                            }catch(InterruptedException e){}
                                            return ; 
                                        }
                                    }
                                }    
                            }
                        }
                   }
                }
                else if(((_x>=722 && _x<=780)&&(_y>=556 && _y<=757)) && J1.numberOfY > 0){    // from J1
                    if(checkJ1 == false){
                        StdDraw.setPenColor(StdDraw.RED) ; 
                        StdDraw.text(650,110,"No valid move from J1.");
                        StdDraw.show();
                        StdDraw.setPenColor();
                        try{
                            Thread.sleep(300);
                        }catch(InterruptedException e){}
                        StdDraw.setPenColor(StdDraw.WHITE);
                        StdDraw.filledRectangle(650, 110, 90,50);
                        StdDraw.setPenColor();
                        StdDraw.show();
                    }
                   else{
                       StdDraw.text(650,110,"J1 -> ? ");
                        StdDraw.show();
                        double _x2 ; 
                        double _y2 ; 
                        while(true){                                                    // destination loop.
                            if(StdDraw.isMousePressed()){
                                _x2 = StdDraw.mouseX() ; 
                                _y2 = StdDraw.mouseY() ; 
                                if(((_x2>=659&&_x2<=715)&&(_y2>=556&&_y2<=757))&&(I1.numberOfX < 2)){     // I1'e git.
                                    for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                                        if(Board.hamleler.get(i)==1){
                                            Board.hamleler.remove(i);
                                            J1.numberOfY -=1 ; 
                                            I1.numberOfY +=1 ; 
                                            if(I1.numberOfX ==1){
                                                I1.numberOfX = 0 ; 
                                                E3.numberOfX += 1 ; 
                                            }
                                            StdDraw.setPenColor(StdDraw.WHITE) ; 
                                            StdDraw.filledRectangle(650,110,30,50);
                                            StdDraw.setPenColor();
                                            StdDraw.show();
                                            StdDraw.text(650,110,"J1 -> I1");
                                            Files.hamlePrint("J1","I1");
                                            StdDraw.show();
                                            try{
                                                Thread.sleep(300);
                                            }catch(InterruptedException e){}
                                            return ; 
                                        }
                                        else{
                                            continue ; 
                                        }
                                    }
                                }
                                else if(((_x2>=594&&_x2<=647)&&(_y2>=556&&_y2<=757))&&(H1.numberOfX < 2)){   // H1'e git.
                                    
                                    for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                                        if(Board.hamleler.get(i)==2){
                                            Board.hamleler.remove(i);
                                            J1.numberOfY -=1 ; 
                                            H1.numberOfY +=1 ; 
                                            if(H1.numberOfX ==1){
                                                H1.numberOfX = 0 ; 
                                                E3.numberOfX += 1 ; 
                                            }
                                            StdDraw.setPenColor(StdDraw.WHITE) ; 
                                            StdDraw.filledRectangle(650,110,30,50);
                                            StdDraw.setPenColor();
                                            StdDraw.show();
                                            StdDraw.text(650,110,"J1 -> H1");
                                            Files.hamlePrint("J1","H1");
                                            StdDraw.show();
                                            try{
                                                Thread.sleep(300);
                                            }catch(InterruptedException e){}
                                            return ; 
                                        }
                                        else{
                                            continue ; 
                                        }
                                    }
                                }
                                else if(((_x2>=528&&_x2<=583)&&(_y2>=556&&_y2<=757))&&(G1.numberOfX < 2)){   // G1'e git
                                    
                                    for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                                        if(Board.hamleler.get(i)==3){
                                            Board.hamleler.remove(i);
                                            J1.numberOfY -=1 ; 
                                            G1.numberOfY +=1 ; 
                                            if(G1.numberOfX ==1){
                                                G1.numberOfX = 0 ; 
                                                E3.numberOfX += 1 ; 
                                            }
                                            StdDraw.setPenColor(StdDraw.WHITE) ; 
                                            StdDraw.filledRectangle(650,110,30,50);
                                            StdDraw.setPenColor();
                                            StdDraw.show();
                                            StdDraw.text(650,110,"J1 -> G1");
                                            Files.hamlePrint("J1","G1");
                                            StdDraw.show();
                                            try{
                                                Thread.sleep(300);
                                            }catch(InterruptedException e){}
                                            return ; 
                                        }
                                        else{
                                            continue ; 
                                        }
                                    }
                                }
                                else if(((_x2>=419&&_x2<=478)&&(_y2>=556&&_y2<=757))&&(F1.numberOfX < 2)){      //F1 icin.
                                    
                                    for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                                        if(Board.hamleler.get(i)==4){
                                            Board.hamleler.remove(i);
                                            J1.numberOfY -=1 ; 
                                            F1.numberOfY +=1 ; 
                                            if(F1.numberOfX ==1){
                                                F1.numberOfX = 0 ; 
                                                E3.numberOfX += 1 ; 
                                            }
                                            StdDraw.setPenColor(StdDraw.WHITE) ; 
                                            StdDraw.filledRectangle(650,110,30,50);
                                            StdDraw.setPenColor();
                                            StdDraw.show();
                                            StdDraw.text(650,110,"J1 -> F1");
                                            Files.hamlePrint("J1","F1");
                                            StdDraw.show();
                                            try{
                                                Thread.sleep(300);
                                            }catch(InterruptedException e){}
                                            return ; 
                                        }
                                        else{
                                            continue ; 
                                        }
                                    }
                                }
                                else if(((_x2>=353&&_x2<=409)&&(_y2>=556&&_y2<=757))&&(E1.numberOfX < 2)){    // E1 icin.
                                    for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                                        if(Board.hamleler.get(i)==5){
                                            Board.hamleler.remove(i);
                                            J1.numberOfY -=1 ; 
                                            E1.numberOfY +=1 ; 
                                            if(E1.numberOfX ==1){
                                                E1.numberOfX = 0 ; 
                                                E3.numberOfX += 1 ; 
                                            }
                                            StdDraw.setPenColor(StdDraw.WHITE) ; 
                                            StdDraw.filledRectangle(650,110,30,50);
                                            StdDraw.setPenColor();
                                            StdDraw.show();
                                            StdDraw.text(650,110,"J1 -> E1");
                                            Files.hamlePrint("J1","E1");
                                            StdDraw.show();
                                            try{
                                                Thread.sleep(300);
                                            }catch(InterruptedException e){}
                                            return ; 
                                        }
                                        else{
                                            continue ; 
                                        }
                                    }
                                }
                                else if(((_x2>=293&&_x2<=343)&&(_y2>=556&&_y2<=757))&&(D1.numberOfX < 2)){     // D1 icin
                                    for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                                        if(Board.hamleler.get(i)==6){
                                            Board.hamleler.remove(i);
                                            J1.numberOfY -=1 ; 
                                            D1.numberOfY +=1 ; 
                                            if(D1.numberOfX ==1){
                                                D1.numberOfX = 0 ; 
                                                E3.numberOfX += 1 ; 
                                            }
                                            StdDraw.setPenColor(StdDraw.WHITE) ; 
                                            StdDraw.filledRectangle(650,110,30,50);
                                            StdDraw.setPenColor();
                                            StdDraw.show();
                                            StdDraw.text(650,110,"J1 -> D1");
                                            Files.hamlePrint("J1","D1");
                                            StdDraw.show();
                                            try{
                                                Thread.sleep(300);
                                            }catch(InterruptedException e){}
                                            return ; 
                                        }
                                    }
                                }    
                            }
                        }
                   }
                }
                else if(((_x>=659 && _x<=715)&&(_y>=556 && _y<=757)) && I1.numberOfY > 0){     // from I1.
                     if(checkI1 == false){
                        StdDraw.setPenColor(StdDraw.RED) ; 
                        StdDraw.text(650,110,"No valid move from I1.");
                        StdDraw.show();
                        StdDraw.setPenColor();
                        try{
                            Thread.sleep(300);
                        }catch(InterruptedException e){}
                        StdDraw.setPenColor(StdDraw.WHITE);
                        StdDraw.filledRectangle(650, 110, 90,50);
                        StdDraw.setPenColor();
                        StdDraw.show();
                    }
                   else{
                       StdDraw.text(650,110,"I1 -> ? ");
                        StdDraw.show();
                        double _x2 ; 
                        double _y2 ; 
                        while(true){                                                    // destination loop.
                            if(StdDraw.isMousePressed()){
                                _x2 = StdDraw.mouseX() ; 
                                _y2 = StdDraw.mouseY() ; 
                                if(((_x2>=594&&_x2<=647)&&(_y2>=556&&_y2<=757))&&(H1.numberOfX < 2)){     // H1'e git.
                                    for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                                        if(Board.hamleler.get(i)==1){
                                            Board.hamleler.remove(i);
                                            I1.numberOfY -=1 ; 
                                            H1.numberOfY +=1 ; 
                                            if(H1.numberOfX ==1){
                                                H1.numberOfX = 0 ; 
                                                E3.numberOfX += 1 ; 
                                            }
                                            StdDraw.setPenColor(StdDraw.WHITE) ; 
                                            StdDraw.filledRectangle(650,110,30,50);
                                            StdDraw.setPenColor();
                                            StdDraw.show();
                                            StdDraw.text(650,110,"I1 -> H1");
                                            Files.hamlePrint("I1","H1");
                                            StdDraw.show();
                                            try{
                                                Thread.sleep(300);
                                            }catch(InterruptedException e){}
                                            return ; 
                                        }
                                        else{
                                            continue ; 
                                        }
                                    }
                                }
                                else if(((_x2>=528&&_x2<=583)&&(_y2>=556&&_y2<=757))&&(G1.numberOfX < 2)){   // G1'e git.
                                    
                                    for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                                        if(Board.hamleler.get(i)==2){
                                            Board.hamleler.remove(i);
                                            I1.numberOfY -=1 ; 
                                            G1.numberOfY +=1 ; 
                                            if(G1.numberOfX ==1){
                                                G1.numberOfX = 0 ; 
                                                E3.numberOfX += 1 ; 
                                            }
                                            StdDraw.setPenColor(StdDraw.WHITE) ; 
                                            StdDraw.filledRectangle(650,110,30,50);
                                            StdDraw.setPenColor();
                                            StdDraw.show();
                                            StdDraw.text(650,110,"I1 -> G1");
                                            Files.hamlePrint("I1","G1");
                                            StdDraw.show();
                                            try{
                                                Thread.sleep(300);
                                            }catch(InterruptedException e){}
                                            return ; 
                                        }
                                        else{
                                            continue ; 
                                        }
                                    }
                                }
                                else if(((_x2>=419&&_x2<=478)&&(_y2>=556&&_y2<=757))&&(F1.numberOfX < 2)){   // F1'e git
                                    
                                    for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                                        if(Board.hamleler.get(i)==3){
                                            Board.hamleler.remove(i);
                                            I1.numberOfY -=1 ; 
                                            F1.numberOfY +=1 ; 
                                            if(F1.numberOfX ==1){
                                                F1.numberOfX = 0 ; 
                                                E3.numberOfX += 1 ; 
                                            }
                                            StdDraw.setPenColor(StdDraw.WHITE) ; 
                                            StdDraw.filledRectangle(650,110,30,50);
                                            StdDraw.setPenColor();
                                            StdDraw.show();
                                            StdDraw.text(650,110,"I1 -> F1");
                                            Files.hamlePrint("I1","F1");
                                            StdDraw.show();
                                            try{
                                                Thread.sleep(300);
                                            }catch(InterruptedException e){}
                                            return ; 
                                        }
                                        else{
                                            continue ; 
                                        }
                                    }
                                }
                                else if(((_x2>=353&&_x2<=409)&&(_y2>=556&&_y2<=757))&&(E1.numberOfX < 2)){      //E1 icin.
                                    
                                    for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                                        if(Board.hamleler.get(i)==4){
                                            Board.hamleler.remove(i);
                                            I1.numberOfY -=1 ; 
                                            E1.numberOfY +=1 ; 
                                            if(E1.numberOfX ==1){
                                                E1.numberOfX = 0 ; 
                                                E3.numberOfX += 1 ; 
                                            }
                                            StdDraw.setPenColor(StdDraw.WHITE) ; 
                                            StdDraw.filledRectangle(650,110,30,50);
                                            StdDraw.setPenColor();
                                            StdDraw.show();
                                            StdDraw.text(650,110,"I1 -> E1");
                                            Files.hamlePrint("I1","E1");
                                            StdDraw.show();
                                            try{
                                                Thread.sleep(300);
                                            }catch(InterruptedException e){}
                                            return ; 
                                        }
                                        else{
                                            continue ; 
                                        }
                                    }
                                }
                                else if(((_x2>=293&&_x2<=343)&&(_y2>=556&&_y2<=757))&&(D1.numberOfX < 2)){    // D1 icin.
                                    for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                                        if(Board.hamleler.get(i)==5){
                                            Board.hamleler.remove(i);
                                            I1.numberOfY -=1 ; 
                                            D1.numberOfY +=1 ; 
                                            if(D1.numberOfX ==1){
                                                D1.numberOfX = 0 ; 
                                                E3.numberOfX += 1 ; 
                                            }
                                            StdDraw.setPenColor(StdDraw.WHITE) ; 
                                            StdDraw.filledRectangle(650,110,30,50);
                                            StdDraw.setPenColor();
                                            StdDraw.show();
                                            StdDraw.text(650,110,"I1 -> D1");
                                            Files.hamlePrint("I1","D1");
                                            StdDraw.show();
                                            try{
                                                Thread.sleep(300);
                                            }catch(InterruptedException e){}
                                            return ; 
                                        }
                                        else{
                                            continue ; 
                                        }
                                    }
                                }
                                else if(((_x2>=223&&_x2<=278)&&(_y2>=556&&_y2<=757))&&(C1.numberOfX < 2)){     // C1 icin
                                    for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                                        if(Board.hamleler.get(i)==6){
                                            Board.hamleler.remove(i);
                                            I1.numberOfY -=1 ; 
                                            C1.numberOfY +=1 ; 
                                            if(C1.numberOfX ==1){
                                                C1.numberOfX = 0 ; 
                                                E3.numberOfX += 1 ; 
                                            }
                                            StdDraw.setPenColor(StdDraw.WHITE) ; 
                                            StdDraw.filledRectangle(650,110,30,50);
                                            StdDraw.setPenColor();
                                            StdDraw.show();
                                            StdDraw.text(650,900,"I1 -> C1");
                                            Files.hamlePrint("I1","C1");
                                            StdDraw.show();
                                            try{
                                                Thread.sleep(300);
                                            }catch(InterruptedException e){}
                                            return ; 
                                        }
                                    }
                                }    
                            }
                        }
                   }
                }
                else if(((_x>=594 && _x<=647)&&(_y>=556 && _y<=757)) && H1.numberOfY > 0){   // from H1
                    if(checkH1 == false){
                        StdDraw.setPenColor(StdDraw.RED) ; 
                        StdDraw.text(650,110,"No valid move from H1.");
                        StdDraw.show();
                        StdDraw.setPenColor();
                        try{
                            Thread.sleep(300);
                        }catch(InterruptedException e){}
                        StdDraw.setPenColor(StdDraw.WHITE);
                        StdDraw.filledRectangle(650, 110, 90,50);
                        StdDraw.setPenColor();
                        StdDraw.show();
                    }
                   else{
                       StdDraw.text(650,110,"H1 -> ? ");
                        StdDraw.show();
                        double _x2 ; 
                        double _y2 ; 
                        while(true){                                                    // destination loop.
                            if(StdDraw.isMousePressed()){
                                _x2 = StdDraw.mouseX() ; 
                                _y2 = StdDraw.mouseY() ; 
                                if(((_x2>=528&&_x2<=583)&&(_y2>=556&&_y2<=757))&&(G1.numberOfX < 2)){     // G1'e git.
                                    for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                                        if(Board.hamleler.get(i)==1){
                                            Board.hamleler.remove(i);
                                            H1.numberOfY -=1 ; 
                                            G1.numberOfY +=1 ; 
                                            if(G1.numberOfX ==1){
                                                G1.numberOfX = 0 ; 
                                                E3.numberOfX += 1 ; 
                                            }
                                            StdDraw.setPenColor(StdDraw.WHITE) ; 
                                            StdDraw.filledRectangle(650,110,30,50);
                                            StdDraw.setPenColor();
                                            StdDraw.show();
                                            StdDraw.text(650,110,"H1 -> G1");
                                            Files.hamlePrint("H1","G1");
                                            StdDraw.show();
                                            try{
                                                Thread.sleep(300);
                                            }catch(InterruptedException e){}
                                            return ; 
                                        }
                                        else{
                                            continue ; 
                                        }
                                    }
                                }
                                else if(((_x2>=419&&_x2<=478)&&(_y2>=556&&_y2<=757))&&(F1.numberOfX < 2)){   // F1'e git.
                                    
                                    for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                                        if(Board.hamleler.get(i)==2){
                                            Board.hamleler.remove(i);
                                            H1.numberOfY -=1 ; 
                                            F1.numberOfY +=1 ; 
                                            if(F1.numberOfX ==1){
                                                F1.numberOfX = 0 ; 
                                                E3.numberOfX += 1 ; 
                                            }
                                            StdDraw.setPenColor(StdDraw.WHITE) ; 
                                            StdDraw.filledRectangle(650,110,30,50);
                                            StdDraw.setPenColor();
                                            StdDraw.show();
                                            StdDraw.text(650,110,"H1 -> F1");
                                            Files.hamlePrint("H1","F1");
                                            StdDraw.show();
                                            try{
                                                Thread.sleep(300);
                                            }catch(InterruptedException e){}
                                            return ; 
                                        }
                                        else{
                                            continue ; 
                                        }
                                    }
                                }
                                else if(((_x2>=353&&_x2<=409)&&(_y2>=556&&_y2<=757))&&(E1.numberOfX < 2)){   // E1'e git
                                    
                                    for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                                        if(Board.hamleler.get(i)==3){
                                            Board.hamleler.remove(i);
                                            H1.numberOfY -=1 ; 
                                            E1.numberOfY +=1 ; 
                                            if(E1.numberOfX ==1){
                                                E1.numberOfX = 0 ; 
                                                E3.numberOfX += 1 ; 
                                            }
                                            StdDraw.setPenColor(StdDraw.WHITE) ; 
                                            StdDraw.filledRectangle(650,110,30,50);
                                            StdDraw.setPenColor();
                                            StdDraw.show();
                                            StdDraw.text(650,110,"H1 -> E1");
                                            Files.hamlePrint("H1","E1");
                                            StdDraw.show();
                                            try{
                                                Thread.sleep(300);
                                            }catch(InterruptedException e){}
                                            return ; 
                                        }
                                        else{
                                            continue ; 
                                        }
                                    }
                                }
                                else if(((_x2>=293&&_x2<=343)&&(_y2>=556&&_y2<=757))&&(D1.numberOfX < 2)){      //D1 icin.
                                    
                                    for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                                        if(Board.hamleler.get(i)==4){
                                            Board.hamleler.remove(i);
                                            H1.numberOfY -=1 ; 
                                            D1.numberOfY +=1 ; 
                                            if(D1.numberOfX ==1){
                                                D1.numberOfX = 0 ; 
                                                E3.numberOfX += 1 ; 
                                            }
                                            StdDraw.setPenColor(StdDraw.WHITE) ; 
                                            StdDraw.filledRectangle(650,110,30,50);
                                            StdDraw.setPenColor();
                                            StdDraw.show();
                                            StdDraw.text(650,110,"H1 -> D1");
                                            Files.hamlePrint("H1","D1");
                                            StdDraw.show();
                                            try{
                                                Thread.sleep(300);
                                            }catch(InterruptedException e){}
                                            return ; 
                                        }
                                        else{
                                            continue ; 
                                        }
                                    }
                                }
                                else if(((_x2>=223&&_x2<=278)&&(_y2>=556&&_y2<=757))&&(C1.numberOfX < 2)){    // C1 icin.
                                    for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                                        if(Board.hamleler.get(i)==5){
                                            Board.hamleler.remove(i);
                                            H1.numberOfY -=1 ; 
                                            C1.numberOfY +=1 ; 
                                            if(C1.numberOfX ==1){
                                                C1.numberOfX = 0 ; 
                                                E3.numberOfX += 1 ; 
                                            }
                                            StdDraw.setPenColor(StdDraw.WHITE) ; 
                                            StdDraw.filledRectangle(650,110,30,50);
                                            StdDraw.setPenColor();
                                            StdDraw.show();
                                            StdDraw.text(650,110,"H1 -> C1");
                                            Files.hamlePrint("H1","C1");
                                            StdDraw.show();
                                            try{
                                                Thread.sleep(300);
                                            }catch(InterruptedException e){}
                                            return ; 
                                        }
                                        else{
                                            continue ; 
                                        }
                                    }
                                }
                                else if(((_x2>=158&&_x2<=215)&&(_y2>=556&&_y2<=757))&&(B1.numberOfX < 2)){     // B1 icin
                                    for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                                        if(Board.hamleler.get(i)==6){
                                            Board.hamleler.remove(i);
                                            H1.numberOfY -=1 ; 
                                            B1.numberOfY +=1 ; 
                                            if(B1.numberOfX ==1){
                                                B1.numberOfX = 0 ; 
                                                E3.numberOfX += 1 ; 
                                            }
                                            StdDraw.setPenColor(StdDraw.WHITE) ; 
                                            StdDraw.filledRectangle(650,110,30,50);
                                            StdDraw.setPenColor();
                                            StdDraw.show();
                                            StdDraw.text(650,110,"H1 -> B1");
                                            Files.hamlePrint("H1","B1");
                                            StdDraw.show();
                                            try{
                                                Thread.sleep(300);
                                            }catch(InterruptedException e){}
                                            return ; 
                                        }
                                    }
                                }    
                            }
                        }
                   }
                }
                else if(((_x>=528 && _x<=583)&&(_y>=556 && _y<=757)) && G1.numberOfY > 0){       // from G1
                    if(checkG1 == false){
                        StdDraw.setPenColor(StdDraw.RED) ; 
                        StdDraw.text(650,110,"No valid move from G1.");
                        StdDraw.show();
                        StdDraw.setPenColor();
                        try{
                            Thread.sleep(300);
                        }catch(InterruptedException e){}
                        StdDraw.setPenColor(StdDraw.WHITE);
                        StdDraw.filledRectangle(650, 110, 90,50);
                        StdDraw.setPenColor();
                        StdDraw.show();
                    }
                   else{
                       StdDraw.text(650,110,"G1 -> ? ");
                        StdDraw.show();
                        double _x2 ; 
                        double _y2 ; 
                        while(true){                                                    // destination loop.
                            if(StdDraw.isMousePressed()){
                                _x2 = StdDraw.mouseX() ; 
                                _y2 = StdDraw.mouseY() ; 
                                if(((_x2>=419&&_x2<=478)&&(_y2>=556&&_y2<=757))&&(F1.numberOfX < 2)){     // F1'e git.
                                    for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                                        if(Board.hamleler.get(i)==1){
                                            Board.hamleler.remove(i);
                                            G1.numberOfY -=1 ; 
                                            F1.numberOfY +=1 ; 
                                            if(F1.numberOfX ==1){
                                                F1.numberOfX = 0 ; 
                                                E3.numberOfX += 1 ; 
                                            }
                                            StdDraw.setPenColor(StdDraw.WHITE) ; 
                                            StdDraw.filledRectangle(650,110,30,50);
                                            StdDraw.setPenColor();
                                            StdDraw.show();
                                            StdDraw.text(650,110,"G1 -> F1");
                                            Files.hamlePrint("G1","F1");
                                            StdDraw.show();
                                            try{
                                                Thread.sleep(300);
                                            }catch(InterruptedException e){}
                                            return ; 
                                        }
                                        else{
                                            continue ; 
                                        }
                                    }
                                }
                                else if(((_x2>=353&&_x2<=409)&&(_y2>=556&&_y2<=757))&&(E1.numberOfX < 2)){   // E1'e git.
                                    
                                    for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                                        if(Board.hamleler.get(i)==2){
                                            Board.hamleler.remove(i);
                                            G1.numberOfY -=1 ; 
                                            E1.numberOfY +=1 ; 
                                            if(E1.numberOfX ==1){
                                                E1.numberOfX = 0 ; 
                                                E3.numberOfX += 1 ; 
                                            }
                                            StdDraw.setPenColor(StdDraw.WHITE) ; 
                                            StdDraw.filledRectangle(650,110,30,50);
                                            StdDraw.setPenColor();
                                            StdDraw.show();
                                            StdDraw.text(650,110,"G1 -> E1");
                                            Files.hamlePrint("G1","E1");
                                            StdDraw.show();
                                            try{
                                                Thread.sleep(300);
                                            }catch(InterruptedException e){}
                                            return ; 
                                        }
                                        else{
                                            continue ; 
                                        }
                                    }
                                }
                                else if(((_x2>=293&&_x2<=343)&&(_y2>=556&&_y2<=757))&&(D1.numberOfX < 2)){   // D1'e git
                                    
                                    for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                                        if(Board.hamleler.get(i)==3){
                                            Board.hamleler.remove(i);
                                            G1.numberOfY -=1 ; 
                                            D1.numberOfY +=1 ; 
                                            if(D1.numberOfX ==1){
                                                D1.numberOfX = 0 ; 
                                                E3.numberOfX += 1 ; 
                                            }
                                            StdDraw.setPenColor(StdDraw.WHITE) ; 
                                            StdDraw.filledRectangle(650,110,30,50);
                                            StdDraw.setPenColor();
                                            StdDraw.show();
                                            StdDraw.text(650,110,"G1 -> D1");
                                            Files.hamlePrint("G1","D1");
                                            StdDraw.show();
                                            try{
                                                Thread.sleep(300);
                                            }catch(InterruptedException e){}
                                            return ; 
                                        }
                                        else{
                                            continue ; 
                                        }
                                    }
                                }
                                else if(((_x2>=223&&_x2<=278)&&(_y2>=556&&_y2<=757))&&(C1.numberOfX < 2)){      //C1 icin.
                                    
                                    for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                                        if(Board.hamleler.get(i)==4){
                                            Board.hamleler.remove(i);
                                            G1.numberOfY -=1 ; 
                                            C1.numberOfY +=1 ; 
                                            if(C1.numberOfX ==1){
                                                C1.numberOfX = 0 ; 
                                                E3.numberOfX += 1 ; 
                                            }
                                            StdDraw.setPenColor(StdDraw.WHITE) ; 
                                            StdDraw.filledRectangle(650,110,30,50);
                                            StdDraw.setPenColor();
                                            StdDraw.show();
                                            StdDraw.text(650,110,"G1 -> C1");
                                            Files.hamlePrint("G1","C1");
                                            StdDraw.show();
                                            try{
                                                Thread.sleep(300);
                                            }catch(InterruptedException e){}
                                            return ; 
                                        }
                                        else{
                                            continue ; 
                                        }
                                    }
                                }
                                else if(((_x2>=158&&_x2<=215)&&(_y2>=556&&_y2<=757))&&(B1.numberOfX < 2)){    // B1 icin.
                                    for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                                        if(Board.hamleler.get(i)==5){
                                            Board.hamleler.remove(i);
                                            G1.numberOfY -=1 ; 
                                            B1.numberOfY +=1 ; 
                                            if(B1.numberOfX ==1){
                                                B1.numberOfX = 0 ; 
                                                E3.numberOfX += 1 ; 
                                            }
                                            StdDraw.setPenColor(StdDraw.WHITE) ; 
                                            StdDraw.filledRectangle(650,110,30,50);
                                            StdDraw.setPenColor();
                                            StdDraw.show();
                                            StdDraw.text(650,110,"G1 -> B1");
                                            Files.hamlePrint("G1","B1");
                                            StdDraw.show();
                                            try{
                                                Thread.sleep(300);
                                            }catch(InterruptedException e){}
                                            return ; 
                                        }
                                        else{
                                            continue ; 
                                        }
                                    }
                                }
                                else if(((_x2>=92&&_x2<=150)&&(_y2>=556&&_y2<=757))&&(A1.numberOfX < 2)){     // A1 icin
                                    for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                                        if(Board.hamleler.get(i)==6){
                                            Board.hamleler.remove(i);
                                            G1.numberOfY -=1 ; 
                                            A1.numberOfY +=1 ; 
                                            if(A1.numberOfX ==1){
                                                A1.numberOfX = 0 ; 
                                                E3.numberOfX += 1 ; 
                                            }
                                            StdDraw.setPenColor(StdDraw.WHITE) ; 
                                            StdDraw.filledRectangle(650,110,30,50);
                                            StdDraw.setPenColor();
                                            StdDraw.show();
                                            StdDraw.text(650,110,"G1 -> A1");
                                            Files.hamlePrint("G1","A1");
                                            StdDraw.show();
                                            try{
                                                Thread.sleep(300);
                                            }catch(InterruptedException e){}
                                            return ; 
                                        }
                                    }
                                }    
                            }
                        }
                   }
                }
                else if(((_x>=419 && _x<=478)&&(_y>=556 && _y<=757)) && F1.numberOfY > 0){       // from F1
                    if(checkF1 == false){
                        StdDraw.setPenColor(StdDraw.RED) ; 
                        StdDraw.text(650,110,"No valid move from F1.");
                        StdDraw.show();
                        StdDraw.setPenColor();
                        try{
                            Thread.sleep(300);
                        }catch(InterruptedException e){}
                        StdDraw.setPenColor(StdDraw.WHITE);
                        StdDraw.filledRectangle(650, 110, 90,50);
                        StdDraw.setPenColor();
                        StdDraw.show();
                    }
                   else{
                       StdDraw.text(650,110,"F1 -> ? ");
                        StdDraw.show();
                        double _x2 ; 
                        double _y2 ; 
                        while(true){                                                    // destination loop.
                            if(StdDraw.isMousePressed()){
                                _x2 = StdDraw.mouseX() ; 
                                _y2 = StdDraw.mouseY() ; 
                                if(((_x2>=353&&_x2<=409)&&(_y2>=556&&_y2<=757))&&(E1.numberOfX < 2)){     // E1'e git.
                                    for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                                        if(Board.hamleler.get(i)==1){
                                            Board.hamleler.remove(i);
                                            F1.numberOfY -=1 ; 
                                            E1.numberOfY +=1 ; 
                                            if(E1.numberOfX ==1){
                                                E1.numberOfX = 0 ; 
                                                E3.numberOfX += 1 ; 
                                            }
                                            StdDraw.setPenColor(StdDraw.WHITE) ; 
                                            StdDraw.filledRectangle(650,110,30,50);
                                            StdDraw.setPenColor();
                                            StdDraw.show();
                                            StdDraw.text(650,110,"F1 -> E1");
                                            Files.hamlePrint("F1","E1");
                                            StdDraw.show();
                                            try{
                                                Thread.sleep(300);
                                            }catch(InterruptedException e){}
                                            return ; 
                                        }
                                        else{
                                            continue ; 
                                        }
                                    }
                                }
                                else if(((_x2>=293&&_x2<=343)&&(_y2>=556&&_y2<=757))&&(D1.numberOfX < 2)){   // D1'e git.
                                    
                                    for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                                        if(Board.hamleler.get(i)==2){
                                            Board.hamleler.remove(i);
                                            F1.numberOfY -=1 ; 
                                            D1.numberOfY +=1 ; 
                                            if(D1.numberOfX ==1){
                                                D1.numberOfX = 0 ; 
                                                E3.numberOfX += 1 ; 
                                            }
                                            StdDraw.setPenColor(StdDraw.WHITE) ; 
                                            StdDraw.filledRectangle(650,110,30,50);
                                            StdDraw.setPenColor();
                                            StdDraw.show();
                                            StdDraw.text(650,110,"F1 -> D1");
                                            Files.hamlePrint("F1","D1");
                                            StdDraw.show();
                                            try{
                                                Thread.sleep(300);
                                            }catch(InterruptedException e){}
                                            return ; 
                                        }
                                        else{
                                            continue ; 
                                        }
                                    }
                                }
                                else if(((_x2>=223&&_x2<=278)&&(_y2>=556&&_y2<=757))&&(C1.numberOfX < 2)){   // C1'e git
                                    for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                                        if(Board.hamleler.get(i)==3){
                                            Board.hamleler.remove(i);
                                            F1.numberOfY -=1 ; 
                                            C1.numberOfY +=1 ; 
                                            if(C1.numberOfX ==1){
                                                C1.numberOfX = 0 ; 
                                                E3.numberOfX += 1 ; 
                                            }
                                            StdDraw.setPenColor(StdDraw.WHITE) ; 
                                            StdDraw.filledRectangle(650,110,30,50);
                                            StdDraw.setPenColor();
                                            StdDraw.show();
                                            StdDraw.text(650,110,"F1 -> C1");
                                            Files.hamlePrint("F1","C1");
                                            StdDraw.show();
                                            try{
                                                Thread.sleep(300);
                                            }catch(InterruptedException e){}
                                            return ; 
                                        }
                                        else{
                                            continue ; 
                                        }
                                    }
                                }
                                else if(((_x2>=158&&_x2<=215)&&(_y2>=556&&_y2<=757))&&(B1.numberOfX < 2)){      //B1 icin.
                                    
                                    for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                                        if(Board.hamleler.get(i)==4){
                                            Board.hamleler.remove(i);
                                            F1.numberOfY -=1 ; 
                                            B1.numberOfY +=1 ; 
                                            if(B1.numberOfX ==1){
                                                B1.numberOfX = 0 ; 
                                                E3.numberOfX += 1 ; 
                                            }
                                            StdDraw.setPenColor(StdDraw.WHITE) ; 
                                            StdDraw.filledRectangle(650,110,30,50);
                                            StdDraw.setPenColor();
                                            StdDraw.show();
                                            StdDraw.text(650,110,"F1 -> B1");
                                            Files.hamlePrint("F1","B1");
                                            StdDraw.show();
                                            try{
                                                Thread.sleep(300);
                                            }catch(InterruptedException e){}
                                            return ; 
                                        }
                                        else{
                                            continue ; 
                                        }
                                    }
                                }
                                else if(((_x2>=92&&_x2<=150)&&(_y2>=556&&_y2<=757))&&(A1.numberOfX < 2)){    // A1 icin.
                                    for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                                        if(Board.hamleler.get(i)==5){
                                            Board.hamleler.remove(i);
                                            F1.numberOfY -=1 ; 
                                            A1.numberOfY +=1 ; 
                                            if(A1.numberOfX ==1){
                                                A1.numberOfX = 0 ; 
                                                E3.numberOfX += 1 ; 
                                            }
                                            StdDraw.setPenColor(StdDraw.WHITE) ; 
                                            StdDraw.filledRectangle(650,110,30,50);
                                            StdDraw.setPenColor();
                                            StdDraw.show();
                                            StdDraw.text(650,110,"F1 -> A1");
                                            Files.hamlePrint("F1","A1");
                                            StdDraw.show();
                                            try{
                                                Thread.sleep(300);
                                            }catch(InterruptedException e){}
                                            return ; 
                                        }
                                        else{
                                            continue ; 
                                        }
                                    }
                                }
                                else if(((_x2>=92&&_x2<=150)&&(_y2>=242&&_y2<=444))&&(A5.numberOfX < 2)){     // A5 icin
                                    for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                                        if(Board.hamleler.get(i)==6){
                                            Board.hamleler.remove(i);
                                            F1.numberOfY -=1 ; 
                                            A5.numberOfY +=1 ; 
                                            if(A5.numberOfX ==1){
                                                A5.numberOfX = 0 ; 
                                                E3.numberOfX += 1 ; 
                                            }
                                            StdDraw.setPenColor(StdDraw.WHITE) ; 
                                            StdDraw.filledRectangle(650,110,30,50);
                                            StdDraw.setPenColor();
                                            StdDraw.show();
                                            StdDraw.text(650,110,"F1 -> A5");
                                            Files.hamlePrint("F1","A5");
                                            StdDraw.show();
                                            try{
                                                Thread.sleep(300);
                                            }catch(InterruptedException e){}
                                            return ; 
                                        }
                                    }
                                }    
                            }
                        }
                   }
                }
                else if(((_x>=353 && _x<=409)&&(_y>=556 && _y<=757)) && E1.numberOfY > 0){   // from E1
                     if(checkE1 == false){
                        StdDraw.setPenColor(StdDraw.RED) ; 
                        StdDraw.text(650,110,"No valid move from E1.");
                        StdDraw.show();
                        StdDraw.setPenColor();
                        try{
                            Thread.sleep(300);
                        }catch(InterruptedException e){}
                        StdDraw.setPenColor(StdDraw.WHITE);
                        StdDraw.filledRectangle(650, 110, 90,50);
                        StdDraw.setPenColor();
                        StdDraw.show();
                    }
                   else{
                       StdDraw.text(650,110,"E1 -> ? ");
                        StdDraw.show();
                        double _x2 ; 
                        double _y2 ; 
                        while(true){                                                    // destination loop.
                            if(StdDraw.isMousePressed()){
                                _x2 = StdDraw.mouseX() ; 
                                _y2 = StdDraw.mouseY() ; 
                                if(((_x2>=293&&_x2<=343)&&(_y2>=556&&_y2<=757))&&(D1.numberOfX < 2)){     // D1'e git.
                                    for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                                        if(Board.hamleler.get(i)==1){
                                            Board.hamleler.remove(i);
                                            E1.numberOfY -=1 ; 
                                            D1.numberOfY +=1 ; 
                                            if(D1.numberOfX ==1){
                                                D1.numberOfX = 0 ; 
                                                E3.numberOfX += 1 ; 
                                            }
                                            StdDraw.setPenColor(StdDraw.WHITE) ; 
                                            StdDraw.filledRectangle(650,110,30,50);
                                            StdDraw.setPenColor();
                                            StdDraw.show();
                                            StdDraw.text(650,110,"E1 -> D1");
                                            StdDraw.show();
                                            try{
                                                Thread.sleep(300);
                                            }catch(InterruptedException e){}
                                            return ; 
                                        }
                                        else{
                                            continue ; 
                                        }
                                    }
                                }
                                else if(((_x2>=223&&_x2<=278)&&(_y2>=556&&_y2<=757))&&(C1.numberOfX < 2)){   // C1'e git.
                                    
                                    for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                                        if(Board.hamleler.get(i)==2){
                                            Board.hamleler.remove(i);
                                            E1.numberOfY -=1 ; 
                                            C1.numberOfY +=1 ; 
                                            if(C1.numberOfX ==1){
                                                C1.numberOfX = 0 ; 
                                                E3.numberOfX += 1 ; 
                                            }
                                            StdDraw.setPenColor(StdDraw.WHITE) ; 
                                            StdDraw.filledRectangle(650,110,30,50);
                                            StdDraw.setPenColor();
                                            StdDraw.show();
                                            StdDraw.text(650,110,"E1 -> C1");
                                            Files.hamlePrint("E1","C1");
                                            StdDraw.show();
                                            try{
                                                Thread.sleep(300);
                                            }catch(InterruptedException e){}
                                            return ; 
                                        }
                                        else{
                                            continue ; 
                                        }
                                    }
                                }
                                else if(((_x2>=158&&_x2<=215)&&(_y2>=556&&_y2<=757))&&(B1.numberOfX < 2)){   // B1'e git
                                    for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                                        if(Board.hamleler.get(i)==3){
                                            Board.hamleler.remove(i);
                                            E1.numberOfY -=1 ; 
                                            B1.numberOfY +=1 ; 
                                            if(B1.numberOfX ==1){
                                                B1.numberOfX = 0 ; 
                                                E3.numberOfX += 1 ; 
                                            }
                                            StdDraw.setPenColor(StdDraw.WHITE) ; 
                                            StdDraw.filledRectangle(650,110,30,50);
                                            StdDraw.setPenColor();
                                            StdDraw.show();
                                            StdDraw.text(650,110,"E1 -> B1");
                                            Files.hamlePrint("E1","B1");
                                            StdDraw.show();
                                            try{
                                                Thread.sleep(300);
                                            }catch(InterruptedException e){}
                                            return ; 
                                        }
                                        else{
                                            continue ; 
                                        }
                                    }
                                }
                                else if(((_x2>=92&&_x2<=150)&&(_y2>=556&&_y2<=757))&&(A1.numberOfX < 2)){      //A1 icin.
                                    
                                    for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                                        if(Board.hamleler.get(i)==4){
                                            Board.hamleler.remove(i);
                                            E1.numberOfY -=1 ; 
                                            A1.numberOfY +=1 ; 
                                            if(A1.numberOfX ==1){
                                                A1.numberOfX = 0 ; 
                                                E3.numberOfX += 1 ; 
                                            }
                                            StdDraw.setPenColor(StdDraw.WHITE) ; 
                                            StdDraw.filledRectangle(650,110,30,50);
                                            StdDraw.setPenColor();
                                            StdDraw.show();
                                            StdDraw.text(650,110,"E1 -> A1");
                                            Files.hamlePrint("E1","A1");
                                            StdDraw.show();
                                            try{
                                                Thread.sleep(300);
                                            }catch(InterruptedException e){}
                                            return ; 
                                        }
                                        else{
                                            continue ; 
                                        }
                                    }
                                }
                                else if(((_x2>=92&&_x2<=150)&&(_y2>=242&&_y2<=444))&&(A5.numberOfX < 2)){    // A5 icin.
                                    for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                                        if(Board.hamleler.get(i)==5){
                                            Board.hamleler.remove(i);
                                            E1.numberOfY -=1 ; 
                                            A5.numberOfY +=1 ; 
                                            if(A5.numberOfX ==1){
                                                A5.numberOfX = 0 ; 
                                                E3.numberOfX += 1 ; 
                                            }
                                            StdDraw.setPenColor(StdDraw.WHITE) ; 
                                            StdDraw.filledRectangle(650,110,30,50);
                                            StdDraw.setPenColor();
                                            StdDraw.show();
                                            StdDraw.text(650,110,"E1 -> A5");
                                            Files.hamlePrint("E1","A5");
                                            StdDraw.show();
                                            try{
                                                Thread.sleep(300);
                                            }catch(InterruptedException e){}
                                            return ; 
                                        }
                                        else{
                                            continue ; 
                                        }
                                    }
                                }
                                else if(((_x2>=158&&_x2<=215)&&(_y2>=242&&_y2<=444))&&(B5.numberOfX < 2)){     // B5 icin
                                    for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                                        if(Board.hamleler.get(i)==6){
                                            Board.hamleler.remove(i);
                                            E1.numberOfY -=1 ; 
                                            B5.numberOfY +=1 ; 
                                            if(B5.numberOfX ==1){
                                                B5.numberOfX = 0 ; 
                                                E3.numberOfX += 1 ; 
                                            }
                                            StdDraw.setPenColor(StdDraw.WHITE) ; 
                                            StdDraw.filledRectangle(650,110,30,50);
                                            StdDraw.setPenColor();
                                            StdDraw.show();
                                            StdDraw.text(650,110,"E1 -> B5");
                                            Files.hamlePrint("E1","B5");
                                            StdDraw.show();
                                            try{
                                                Thread.sleep(300);
                                            }catch(InterruptedException e){}
                                            return ; 
                                        }
                                    }
                                }    
                            }
                        }
                   }
                }
                else if(((_x>=293 && _x<=343)&&(_y>=556 && _y<=757)) && D1.numberOfY > 0){          // from D1
                    if(checkD1 == false){
                        StdDraw.setPenColor(StdDraw.RED) ; 
                        StdDraw.text(650,110,"No valid move from D1.");
                        StdDraw.show();
                        StdDraw.setPenColor();
                        try{
                            Thread.sleep(300);
                        }catch(InterruptedException e){}
                        StdDraw.setPenColor(StdDraw.WHITE);
                        StdDraw.filledRectangle(650, 110, 90,50);
                        StdDraw.setPenColor();
                        StdDraw.show();
                    }
                   else{
                       StdDraw.text(650,110,"D1 -> ? ");
                        StdDraw.show();
                        double _x2 ; 
                        double _y2 ; 
                        while(true){                                                    // destination loop.
                            if(StdDraw.isMousePressed()){
                                _x2 = StdDraw.mouseX() ; 
                                _y2 = StdDraw.mouseY() ; 
                                if(((_x2>=223&&_x2<=278)&&(_y2>=556&&_y2<=757))&&(C1.numberOfX < 2)){     // C1'e git.
                                    for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                                        if(Board.hamleler.get(i)==1){
                                            Board.hamleler.remove(i);
                                            D1.numberOfY -=1 ; 
                                            C1.numberOfY +=1 ; 
                                            if(C1.numberOfX ==1){
                                                C1.numberOfX = 0 ; 
                                                E3.numberOfX += 1 ; 
                                            }
                                            StdDraw.setPenColor(StdDraw.WHITE) ; 
                                            StdDraw.filledRectangle(650,110,30,50);
                                            StdDraw.setPenColor();
                                            StdDraw.show();
                                            StdDraw.text(650,110,"D1 -> C1");
                                            Files.hamlePrint("D1","C1");
                                            StdDraw.show();
                                            try{
                                                Thread.sleep(300);
                                            }catch(InterruptedException e){}
                                            return ; 
                                        }
                                        else{
                                            continue ; 
                                        }
                                    }
                                }
                                else if(((_x2>=158&&_x2<=215)&&(_y2>=556&&_y2<=757))&&(B1.numberOfX < 2)){   // B1'e git.
                                    
                                    for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                                        if(Board.hamleler.get(i)==2){
                                            Board.hamleler.remove(i);
                                            D1.numberOfY -=1 ; 
                                            B1.numberOfY +=1 ; 
                                            if(B1.numberOfX ==1){
                                                B1.numberOfX = 0 ; 
                                                E3.numberOfX += 1 ; 
                                            }
                                            StdDraw.setPenColor(StdDraw.WHITE) ; 
                                            StdDraw.filledRectangle(650,110,30,50);
                                            StdDraw.setPenColor();
                                            StdDraw.show();
                                            StdDraw.text(650,110,"D1 -> B1");
                                            Files.hamlePrint("D1","B1");
                                            StdDraw.show();
                                            try{
                                                Thread.sleep(300);
                                            }catch(InterruptedException e){}
                                            return ; 
                                        }
                                        else{
                                            continue ; 
                                        }
                                    }
                                }
                                else if(((_x2>=92&&_x2<=150)&&(_y2>=556&&_y2<=757))&&(A1.numberOfX < 2)){   // A1'e git
                                    for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                                        if(Board.hamleler.get(i)==3){
                                            Board.hamleler.remove(i);
                                            D1.numberOfY -=1 ; 
                                            A1.numberOfY +=1 ; 
                                            if(A1.numberOfX ==1){
                                                A1.numberOfX = 0 ; 
                                                E3.numberOfX += 1 ; 
                                            }
                                            StdDraw.setPenColor(StdDraw.WHITE) ; 
                                            StdDraw.filledRectangle(650,110,30,50);
                                            StdDraw.setPenColor();
                                            StdDraw.show();
                                            StdDraw.text(650,110,"D1 -> A1");
                                            Files.hamlePrint("D1","A1");
                                            StdDraw.show();
                                            try{
                                                Thread.sleep(300);
                                            }catch(InterruptedException e){}
                                            return ; 
                                        }
                                        else{
                                            continue ; 
                                        }
                                    }
                                }
                                else if(((_x2>=92&&_x2<=150)&&(_y2>=242&&_y2<=444))&&(A5.numberOfX < 2)){      //A5 icin.
                                    
                                    for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                                        if(Board.hamleler.get(i)==4){
                                            Board.hamleler.remove(i);
                                            D1.numberOfY -=1 ; 
                                            A5.numberOfY +=1 ; 
                                            if(A5.numberOfX ==1){
                                                A5.numberOfX = 0 ; 
                                                E3.numberOfX += 1 ; 
                                            }
                                            StdDraw.setPenColor(StdDraw.WHITE) ; 
                                            StdDraw.filledRectangle(650,110,30,50);
                                            StdDraw.setPenColor();
                                            StdDraw.show();
                                            StdDraw.text(650,110,"D1 -> A5");
                                            Files.hamlePrint("D1","A5");
                                            StdDraw.show();
                                            try{
                                                Thread.sleep(300);
                                            }catch(InterruptedException e){}
                                            return ; 
                                        }
                                        else{
                                            continue ; 
                                        }
                                    }
                                }
                                else if(((_x2>=158&&_x2<=215)&&(_y2>=242&&_y2<=444))&&(B5.numberOfX < 2)){    // B5 icin.
                                    for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                                        if(Board.hamleler.get(i)==5){
                                            Board.hamleler.remove(i);
                                            D1.numberOfY -=1 ; 
                                            B5.numberOfY +=1 ; 
                                            if(B5.numberOfX ==1){
                                                B5.numberOfX = 0 ; 
                                                E3.numberOfX += 1 ; 
                                            }
                                            StdDraw.setPenColor(StdDraw.WHITE) ; 
                                            StdDraw.filledRectangle(650,110,30,50);
                                            StdDraw.setPenColor();
                                            StdDraw.show();
                                            StdDraw.text(650,110,"D1 -> B5");
                                            Files.hamlePrint("D1","B5");
                                            StdDraw.show();
                                            try{
                                                Thread.sleep(300);
                                            }catch(InterruptedException e){}
                                            return ; 
                                        }
                                        else{
                                            continue ; 
                                        }
                                    }
                                }
                                else if(((_x2>=223&&_x2<=278)&&(_y2>=242&&_y2<=444))&&(C5.numberOfX < 2)){     // C5 icin
                                    for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                                        if(Board.hamleler.get(i)==6){
                                            Board.hamleler.remove(i);
                                            D1.numberOfY -=1 ; 
                                            C5.numberOfY +=1 ; 
                                            if(C5.numberOfX ==1){
                                                C5.numberOfX = 0 ; 
                                                E3.numberOfX += 1 ; 
                                            }
                                            StdDraw.setPenColor(StdDraw.WHITE) ; 
                                            StdDraw.filledRectangle(650,110,30,50);
                                            StdDraw.setPenColor();
                                            StdDraw.show();
                                            StdDraw.text(650,110,"D1 -> C5");
                                            Files.hamlePrint("D1","C5");
                                            StdDraw.show();
                                            try{
                                                Thread.sleep(300);
                                            }catch(InterruptedException e){}
                                            return ; 
                                        }
                                    }
                                }    
                            }
                        }
                   }
                }
                else if(((_x>=223 && _x<=278)&&(_y>=556 && _y<=757)) && C1.numberOfY > 0){        // from C1
                    if(checkC1 == false){
                        StdDraw.setPenColor(StdDraw.RED) ; 
                        StdDraw.text(650,110,"No valid move from C1.");
                        StdDraw.show();
                        StdDraw.setPenColor();
                        try{
                            Thread.sleep(300);
                        }catch(InterruptedException e){}
                        StdDraw.setPenColor(StdDraw.WHITE);
                        StdDraw.filledRectangle(650, 110, 90,50);
                        StdDraw.setPenColor();
                        StdDraw.show();
                    }
                   else{
                       StdDraw.text(650,110,"C1 -> ? ");
                        StdDraw.show();
                        double _x2 ; 
                        double _y2 ; 
                        while(true){                                                    // destination loop.
                            if(StdDraw.isMousePressed()){
                                _x2 = StdDraw.mouseX() ; 
                                _y2 = StdDraw.mouseY() ; 
                                if(((_x2>=158&&_x2<=215)&&(_y2>=556&&_y2<=757))&&(B1.numberOfX < 2)){     // B1'e git.
                                    for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                                        if(Board.hamleler.get(i)==1){
                                            Board.hamleler.remove(i);
                                            C1.numberOfY -=1 ; 
                                            B1.numberOfY +=1 ; 
                                            if(B1.numberOfX ==1){
                                                B1.numberOfX = 0 ; 
                                                E3.numberOfX += 1 ; 
                                            }
                                            StdDraw.setPenColor(StdDraw.WHITE) ; 
                                            StdDraw.filledRectangle(650,110,30,50);
                                            StdDraw.setPenColor();
                                            StdDraw.show();
                                            StdDraw.text(650,110,"C1 -> B1");
                                            Files.hamlePrint("C1","B1");
                                            StdDraw.show();
                                            try{
                                                Thread.sleep(300);
                                            }catch(InterruptedException e){}
                                            return ; 
                                        }
                                        else{
                                            continue ; 
                                        }
                                    }
                                }
                                else if(((_x2>=92&&_x2<=150)&&(_y2>=556&&_y2<=757))&&(A1.numberOfX < 2)){   // A1'e git.
                                    
                                    for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                                        if(Board.hamleler.get(i)==2){
                                            Board.hamleler.remove(i);
                                            C1.numberOfY -=1 ; 
                                            A1.numberOfY +=1 ; 
                                            if(A1.numberOfX ==1){
                                                A1.numberOfX = 0 ; 
                                                E3.numberOfX += 1 ; 
                                            }
                                            StdDraw.setPenColor(StdDraw.WHITE) ; 
                                            StdDraw.filledRectangle(650,110,30,50);
                                            StdDraw.setPenColor();
                                            StdDraw.show();
                                            StdDraw.text(650,110,"C1 -> A1");
                                            Files.hamlePrint("C1","A1");
                                            StdDraw.show();
                                            try{
                                                Thread.sleep(300);
                                            }catch(InterruptedException e){}
                                            return ; 
                                        }
                                        else{
                                            continue ; 
                                        }
                                    }
                                }
                                else if(((_x2>=92&&_x2<=150)&&(_y2>=242&&_y2<=444))&&(A5.numberOfX < 2)){   // A5'e git
                                    for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                                        if(Board.hamleler.get(i)==3){
                                            Board.hamleler.remove(i);
                                            C1.numberOfY -=1 ; 
                                            A5.numberOfY +=1 ; 
                                            if(A5.numberOfX ==1){
                                                A5.numberOfX = 0 ; 
                                                E3.numberOfX += 1 ; 
                                            }
                                            StdDraw.setPenColor(StdDraw.WHITE) ; 
                                            StdDraw.filledRectangle(650,110,30,50);
                                            StdDraw.setPenColor();
                                            StdDraw.show();
                                            StdDraw.text(650,110,"C1 -> A5");
                                            Files.hamlePrint("C1","A5");
                                            StdDraw.show();
                                            try{
                                                Thread.sleep(300);
                                            }catch(InterruptedException e){}
                                            return ; 
                                        }
                                        else{
                                            continue ; 
                                        }
                                    }
                                }
                                else if(((_x2>=158&&_x2<=215)&&(_y2>=242&&_y2<=444))&&(B5.numberOfX < 2)){      //B5 icin.
                                    
                                    for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                                        if(Board.hamleler.get(i)==4){
                                            Board.hamleler.remove(i);
                                            C1.numberOfY -=1 ; 
                                            B5.numberOfY +=1 ; 
                                            if(B5.numberOfX ==1){
                                                B5.numberOfX = 0 ; 
                                                E3.numberOfX += 1 ; 
                                            }
                                            StdDraw.setPenColor(StdDraw.WHITE) ; 
                                            StdDraw.filledRectangle(650,110,30,50);
                                            StdDraw.setPenColor();
                                            StdDraw.show();
                                            StdDraw.text(650,110,"C1 -> B5");
                                            Files.hamlePrint("C1","B5");
                                            StdDraw.show();
                                            try{
                                                Thread.sleep(300);
                                            }catch(InterruptedException e){}
                                            return ; 
                                        }
                                        else{
                                            continue ; 
                                        }
                                    }
                                }
                                else if(((_x2>=223&&_x2<=278)&&(_y2>=242&&_y2<=444))&&(C5.numberOfX < 2)){    // C5 icin.
                                    for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                                        if(Board.hamleler.get(i)==5){
                                            Board.hamleler.remove(i);
                                            C1.numberOfY -=1 ; 
                                            C5.numberOfY +=1 ; 
                                            if(C5.numberOfX ==1){
                                                C5.numberOfX = 0 ; 
                                                E3.numberOfX += 1 ; 
                                            }
                                            StdDraw.setPenColor(StdDraw.WHITE) ; 
                                            StdDraw.filledRectangle(650,110,30,50);
                                            StdDraw.setPenColor();
                                            StdDraw.show();
                                            StdDraw.text(650,110,"C1 -> C5");
                                            Files.hamlePrint("C1","C5");
                                            StdDraw.show();
                                            try{
                                                Thread.sleep(300);
                                            }catch(InterruptedException e){}
                                            return ; 
                                        }
                                        else{
                                            continue ; 
                                        }
                                    }
                                }
                                else if(((_x2>=293&&_x2<=343)&&(_y2>=242&&_y2<=444))&&(D5.numberOfX < 2)){     // D5 icin
                                    for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                                        if(Board.hamleler.get(i)==6){
                                            Board.hamleler.remove(i);
                                            C1.numberOfY -=1 ; 
                                            D5.numberOfY +=1 ; 
                                            if(D5.numberOfX ==1){
                                                D5.numberOfX = 0 ; 
                                                E3.numberOfX += 1 ; 
                                            }
                                            StdDraw.setPenColor(StdDraw.WHITE) ; 
                                            StdDraw.filledRectangle(650,110,30,50);
                                            StdDraw.setPenColor();
                                            StdDraw.show();
                                            StdDraw.text(650,110,"C1 -> D5");
                                            Files.hamlePrint("C1","D5");
                                            StdDraw.show();
                                            try{
                                                Thread.sleep(300);
                                            }catch(InterruptedException e){}
                                            return ; 
                                        }
                                    }
                                }    
                            }
                        }
                   }
                }
                else if(((_x>=158 && _x<=215)&&(_y>=556 && _y<=757)) && B1.numberOfY > 0){       // from B1
                    if(checkB1 == false){
                        StdDraw.setPenColor(StdDraw.RED) ; 
                        StdDraw.text(650,110,"No valid move from B1.");
                        StdDraw.show();
                        StdDraw.setPenColor();
                        try{
                            Thread.sleep(300);
                        }catch(InterruptedException e){}
                        StdDraw.setPenColor(StdDraw.WHITE);
                        StdDraw.filledRectangle(650, 110, 90,50);
                        StdDraw.setPenColor();
                        StdDraw.show();
                    }
                   else{
                       StdDraw.text(650,110,"B1 -> ? ");
                        StdDraw.show();
                        double _x2 ; 
                        double _y2 ; 
                        while(true){                                                    // destination loop.
                            if(StdDraw.isMousePressed()){
                                _x2 = StdDraw.mouseX() ; 
                                _y2 = StdDraw.mouseY() ; 
                                if(((_x2>=92&&_x2<=150)&&(_y2>=556&&_y2<=757))&&(A1.numberOfX < 2)){     // A1'e git.
                                    for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                                        if(Board.hamleler.get(i)==1){
                                            Board.hamleler.remove(i);
                                            B1.numberOfY -=1 ; 
                                            A1.numberOfY +=1 ; 
                                            if(A1.numberOfX ==1){
                                                A1.numberOfX = 0 ; 
                                                E3.numberOfX += 1 ; 
                                            }
                                            StdDraw.setPenColor(StdDraw.WHITE) ; 
                                            StdDraw.filledRectangle(650,110,30,50);
                                            StdDraw.setPenColor();
                                            StdDraw.show();
                                            StdDraw.text(650,110,"B1 -> A1");
                                            Files.hamlePrint("B1","A1");
                                            StdDraw.show();
                                            try{
                                                Thread.sleep(300);
                                            }catch(InterruptedException e){}
                                            return ; 
                                        }
                                        else{
                                            continue ; 
                                        }
                                    }
                                }
                                else if(((_x2>=92&&_x2<=150)&&(_y2>=242&&_y2<=444))&&(A5.numberOfX < 2)){   // A5'e git.
                                    
                                    for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                                        if(Board.hamleler.get(i)==2){
                                            Board.hamleler.remove(i);
                                            B1.numberOfY -=1 ; 
                                            A5.numberOfY +=1 ; 
                                            if(A5.numberOfX ==1){
                                                A5.numberOfX = 0 ; 
                                                E3.numberOfX += 1 ; 
                                            }
                                            StdDraw.setPenColor(StdDraw.WHITE) ; 
                                            StdDraw.filledRectangle(650,110,30,50);
                                            StdDraw.setPenColor();
                                            StdDraw.show();
                                            StdDraw.text(650,110,"B1 -> A5");
                                            Files.hamlePrint("B1","A5");
                                            StdDraw.show();
                                            try{
                                                Thread.sleep(300);
                                            }catch(InterruptedException e){}
                                            return ; 
                                        }
                                        else{
                                            continue ; 
                                        }
                                    }
                                }
                                else if(((_x2>=158&&_x2<=215)&&(_y2>=242&&_y2<=444))&&(B5.numberOfX < 2)){   // B5'e git
                                    for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                                        if(Board.hamleler.get(i)==3){
                                            Board.hamleler.remove(i);
                                            B1.numberOfY -=1 ; 
                                            B5.numberOfY +=1 ; 
                                            if(B5.numberOfX ==1){
                                                B5.numberOfX = 0 ; 
                                                E3.numberOfX += 1 ; 
                                            }
                                            StdDraw.setPenColor(StdDraw.WHITE) ; 
                                            StdDraw.filledRectangle(650,110,30,50);
                                            StdDraw.setPenColor();
                                            StdDraw.show();
                                            StdDraw.text(650,110,"B1 -> B5");
                                            Files.hamlePrint("B1","B5");
                                            StdDraw.show();
                                            try{
                                                Thread.sleep(300);
                                            }catch(InterruptedException e){}
                                            return ; 
                                        }
                                        else{
                                            continue ; 
                                        }
                                    }
                                }
                                else if(((_x2>=223&&_x2<=278)&&(_y2>=242&&_y2<=444))&&(C5.numberOfX < 2)){      //C5 icin.
                                    
                                    for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                                        if(Board.hamleler.get(i)==4){
                                            Board.hamleler.remove(i);
                                            B1.numberOfY -=1 ; 
                                            C5.numberOfY +=1 ; 
                                            if(C5.numberOfX ==1){
                                                C5.numberOfX = 0 ; 
                                                E3.numberOfX += 1 ; 
                                            }
                                            StdDraw.setPenColor(StdDraw.WHITE) ; 
                                            StdDraw.filledRectangle(650,110,30,50);
                                            StdDraw.setPenColor();
                                            StdDraw.show();
                                            StdDraw.text(650,110,"B1 -> C5");
                                            Files.hamlePrint("B1","C5");
                                            StdDraw.show();
                                            try{
                                                Thread.sleep(300);
                                            }catch(InterruptedException e){}
                                            return ; 
                                        }
                                        else{
                                            continue ; 
                                        }
                                    }
                                }
                                else if(((_x2>=293&&_x2<=343)&&(_y2>=242&&_y2<=444))&&(D5.numberOfX < 2)){    // D5 icin.
                                    for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                                        if(Board.hamleler.get(i)==5){
                                            Board.hamleler.remove(i);
                                            B1.numberOfY -=1 ; 
                                            D5.numberOfY +=1 ; 
                                            if(D5.numberOfX ==1){
                                                D5.numberOfX = 0 ; 
                                                E3.numberOfX += 1 ; 
                                            }
                                            StdDraw.setPenColor(StdDraw.WHITE) ; 
                                            StdDraw.filledRectangle(650,110,30,50);
                                            StdDraw.setPenColor();
                                            StdDraw.show();
                                            StdDraw.text(650,110,"B1 -> D5");
                                            Files.hamlePrint("B1","D5");
                                            StdDraw.show();
                                            try{
                                                Thread.sleep(300);
                                            }catch(InterruptedException e){}
                                            return ; 
                                        }
                                        else{
                                            continue ; 
                                        }
                                    }
                                }
                                else if(((_x2>=353&&_x2<=409)&&(_y2>=242&&_y2<=444))&&(E5.numberOfX < 2)){     // E5 icin
                                    for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                                        if(Board.hamleler.get(i)==6){
                                            Board.hamleler.remove(i);
                                            B1.numberOfY -=1 ; 
                                            E5.numberOfY +=1 ; 
                                            if(E5.numberOfX ==1){
                                                E5.numberOfX = 0 ; 
                                                E3.numberOfX += 1 ; 
                                            }
                                            StdDraw.setPenColor(StdDraw.WHITE) ; 
                                            StdDraw.filledRectangle(650,110,30,50);
                                            StdDraw.setPenColor();
                                            StdDraw.show();
                                            StdDraw.text(650,110,"B1 -> E5");
                                            Files.hamlePrint("B1","E5");
                                            StdDraw.show();
                                            try{
                                                Thread.sleep(300);
                                            }catch(InterruptedException e){}
                                            return ; 
                                        }
                                    }
                                }    
                            }
                        }
                   }
                }
                else if(((_x>=92 && _x<=150)&&(_y>=556 && _y<=757)) && A1.numberOfY > 0){     // from A1
                    if(checkA1 == false){
                        StdDraw.setPenColor(StdDraw.RED) ; 
                        StdDraw.text(650,110,"No valid move from A1.");
                        StdDraw.show();
                        StdDraw.setPenColor();
                        try{
                            Thread.sleep(300);
                        }catch(InterruptedException e){}
                        StdDraw.setPenColor(StdDraw.WHITE);
                        StdDraw.filledRectangle(650, 110, 90,50);
                        StdDraw.setPenColor();
                        StdDraw.show();
                    }
                   else{
                       StdDraw.text(650,110,"A1 -> ? ");
                        StdDraw.show();
                        double _x2 ; 
                        double _y2 ; 
                        while(true){                                                    // destination loop.
                            if(StdDraw.isMousePressed()){
                                _x2 = StdDraw.mouseX() ; 
                                _y2 = StdDraw.mouseY() ; 
                                if(((_x2>=92&&_x2<=150)&&(_y2>=242&&_y2<=444))&&(A5.numberOfX < 2)){     // A5'e git.
                                    for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                                        if(Board.hamleler.get(i)==1){
                                            Board.hamleler.remove(i);
                                            A1.numberOfY -=1 ; 
                                            A5.numberOfY +=1 ; 
                                            if(A5.numberOfX ==1){
                                                A5.numberOfX = 0 ; 
                                                E3.numberOfX += 1 ; 
                                            }
                                            StdDraw.setPenColor(StdDraw.WHITE) ; 
                                            StdDraw.filledRectangle(650,110,30,50);
                                            StdDraw.setPenColor();
                                            StdDraw.show();
                                            StdDraw.text(650,110,"A1 -> A5");
                                            Files.hamlePrint("A1","A5");
                                            StdDraw.show();
                                            try{
                                                Thread.sleep(300);
                                            }catch(InterruptedException e){}
                                            return ; 
                                        }
                                        else{
                                            continue ; 
                                        }
                                    }
                                }
                                else if(((_x2>=158&&_x2<=215)&&(_y2>=242&&_y2<=444))&&(B5.numberOfX < 2)){   // B5'e git.
                                    
                                    for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                                        if(Board.hamleler.get(i)==2){
                                            Board.hamleler.remove(i);
                                            A1.numberOfY -=1 ; 
                                            B5.numberOfY +=1 ; 
                                            if(B5.numberOfX ==1){
                                                B5.numberOfX = 0 ; 
                                                E3.numberOfX += 1 ; 
                                            }
                                            StdDraw.setPenColor(StdDraw.WHITE) ; 
                                            StdDraw.filledRectangle(650,110,30,50);
                                            StdDraw.setPenColor();
                                            StdDraw.show();
                                            StdDraw.text(650,110,"A1 -> B5");
                                            Files.hamlePrint("A1","B5");
                                            StdDraw.show();
                                            try{
                                                Thread.sleep(300);
                                            }catch(InterruptedException e){}
                                            return ; 
                                        }
                                        else{
                                            continue ; 
                                        }
                                    }
                                }
                                else if(((_x2>=223&&_x2<=278)&&(_y2>=242&&_y2<=444))&&(C5.numberOfX < 2)){   // C5'e git
                                    for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                                        if(Board.hamleler.get(i)==3){
                                            Board.hamleler.remove(i);
                                            A1.numberOfY -=1 ; 
                                            C5.numberOfY +=1 ; 
                                            if(C5.numberOfX ==1){
                                                C5.numberOfX = 0 ; 
                                                E3.numberOfX += 1 ; 
                                            }
                                            StdDraw.setPenColor(StdDraw.WHITE) ; 
                                            StdDraw.filledRectangle(650,110,30,50);
                                            StdDraw.setPenColor();
                                            StdDraw.show();
                                            StdDraw.text(650,110,"A1 -> C5");
                                            Files.hamlePrint("A1","C5");
                                            StdDraw.show();
                                            try{
                                                Thread.sleep(300);
                                            }catch(InterruptedException e){}
                                            return ; 
                                        }
                                        else{
                                            continue ; 
                                        }
                                    }
                                }
                                else if(((_x2>=293&&_x2<=343)&&(_y2>=242&&_y2<=444))&&(D5.numberOfX < 2)){      //D5 icin.
                                    
                                    for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                                        if(Board.hamleler.get(i)==4){
                                            Board.hamleler.remove(i);
                                            A1.numberOfY -=1 ; 
                                            D5.numberOfY +=1 ; 
                                            if(D5.numberOfX ==1){
                                                D5.numberOfX = 0 ; 
                                                E3.numberOfX += 1 ; 
                                            }
                                            StdDraw.setPenColor(StdDraw.WHITE) ; 
                                            StdDraw.filledRectangle(650,110,30,50);
                                            StdDraw.setPenColor();
                                            StdDraw.show();
                                            StdDraw.text(650,110,"A1 -> D5");
                                            Files.hamlePrint("A1","D5");
                                            StdDraw.show();
                                            try{
                                                Thread.sleep(300);
                                            }catch(InterruptedException e){}
                                            return ; 
                                        }
                                        else{
                                            continue ; 
                                        }
                                    }
                                }
                                else if(((_x2>=353&&_x2<=409)&&(_y2>=242&&_y2<=444))&&(E5.numberOfX < 2)){    // E5 icin.
                                    for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                                        if(Board.hamleler.get(i)==5){
                                            Board.hamleler.remove(i);
                                            A1.numberOfY -=1 ; 
                                            E5.numberOfY +=1 ; 
                                            if(E5.numberOfX ==1){
                                                E5.numberOfX = 0 ; 
                                                E3.numberOfX += 1 ; 
                                            }
                                            StdDraw.setPenColor(StdDraw.WHITE) ; 
                                            StdDraw.filledRectangle(650,110,30,50);
                                            StdDraw.setPenColor();
                                            StdDraw.show();
                                            StdDraw.text(650,110,"A1 -> E5");
                                            Files.hamlePrint("A1","E5");
                                            StdDraw.show();
                                            try{
                                                Thread.sleep(300);
                                            }catch(InterruptedException e){}
                                            return ; 
                                        }
                                        else{
                                            continue ; 
                                        }
                                    }
                                }
                                else if(((_x2>=419&&_x2<=478)&&(_y2>=242&&_y2<=444))&&(F5.numberOfX < 2)){     // F5 icin
                                    for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                                        if(Board.hamleler.get(i)==6){
                                            Board.hamleler.remove(i);
                                            A1.numberOfY -=1 ; 
                                            F5.numberOfY +=1 ; 
                                            if(F5.numberOfX ==1){
                                                F5.numberOfX = 0 ; 
                                                E3.numberOfX += 1 ; 
                                            }
                                            StdDraw.setPenColor(StdDraw.WHITE) ; 
                                            StdDraw.filledRectangle(650,110,30,50);
                                            StdDraw.setPenColor();
                                            StdDraw.show();
                                            StdDraw.text(650,110,"A1 -> F5");
                                            Files.hamlePrint("A1","F5");
                                            StdDraw.show();
                                            try{
                                                Thread.sleep(300);
                                            }catch(InterruptedException e){}
                                            return ; 
                                        }
                                    }
                                }    
                            }
                        }
                   }
                }
                else if(((_x>=92 && _x<=150)&&(_y>=242 && _y<=444)) && A5.numberOfY > 0){   // from A5
                    if(checkA5 == false){
                        StdDraw.setPenColor(StdDraw.RED) ; 
                        StdDraw.text(650,110,"No valid move from A5.");
                        StdDraw.show();
                        StdDraw.setPenColor();
                        try{
                            Thread.sleep(300);
                        }catch(InterruptedException e){}
                        StdDraw.setPenColor(StdDraw.WHITE);
                        StdDraw.filledRectangle(650, 110, 90,50);
                        StdDraw.setPenColor();
                        StdDraw.show();
                    }
                   else{
                       StdDraw.text(650,110,"A5 -> ? ");
                        StdDraw.show();
                        double _x2 ; 
                        double _y2 ; 
                        while(true){                                                    // destination loop.
                            if(StdDraw.isMousePressed()){
                                _x2 = StdDraw.mouseX() ; 
                                _y2 = StdDraw.mouseY() ; 
                                if(((_x2>=158&&_x2<=215)&&(_y2>=242&&_y2<=444))&&(B5.numberOfX < 2)){     // B5'e git.
                                    for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                                        if(Board.hamleler.get(i)==1){
                                            Board.hamleler.remove(i);
                                            A5.numberOfY -=1 ; 
                                            B5.numberOfY +=1 ; 
                                            if(B5.numberOfX ==1){
                                                B5.numberOfX = 0 ; 
                                                E3.numberOfX += 1 ; 
                                            }
                                            StdDraw.setPenColor(StdDraw.WHITE) ; 
                                            StdDraw.filledRectangle(650,110,30,50);
                                            StdDraw.setPenColor();
                                            StdDraw.show();
                                            StdDraw.text(650,110,"A5 -> B5");
                                            Files.hamlePrint("A5","B5");
                                            StdDraw.show();
                                            try{
                                                Thread.sleep(300);
                                            }catch(InterruptedException e){}
                                            return ; 
                                        }
                                        else{
                                            continue ; 
                                        }
                                    }
                                }
                                else if(((_x2>=223&&_x2<=278)&&(_y2>=242&&_y2<=444))&&(C5.numberOfX < 2)){   // C5'e git.
                                    
                                    for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                                        if(Board.hamleler.get(i)==2){
                                            Board.hamleler.remove(i);
                                            A5.numberOfY -=1 ; 
                                            C5.numberOfY +=1 ; 
                                            if(C5.numberOfX ==1){
                                                C5.numberOfX = 0 ; 
                                                E3.numberOfX += 1 ; 
                                            }
                                            StdDraw.setPenColor(StdDraw.WHITE) ; 
                                            StdDraw.filledRectangle(650,110,30,50);
                                            StdDraw.setPenColor();
                                            StdDraw.show();
                                            StdDraw.text(650,110,"A5 -> C5");
                                            Files.hamlePrint("A5","C5");
                                            StdDraw.show();
                                            try{
                                                Thread.sleep(300);
                                            }catch(InterruptedException e){}
                                            return ; 
                                        }
                                        else{
                                            continue ; 
                                        }
                                    }
                                }
                                else if(((_x2>=293&&_x2<=343)&&(_y2>=242&&_y2<=444))&&(D5.numberOfX < 2)){   // D5'e git
                                    for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                                        if(Board.hamleler.get(i)==3){
                                            Board.hamleler.remove(i);
                                            A5.numberOfY -=1 ; 
                                            D5.numberOfY +=1 ; 
                                            if(D5.numberOfX ==1){
                                                D5.numberOfX = 0 ; 
                                                E3.numberOfX += 1 ; 
                                            }
                                            StdDraw.setPenColor(StdDraw.WHITE) ; 
                                            StdDraw.filledRectangle(650,110,30,50);
                                            StdDraw.setPenColor();
                                            StdDraw.show();
                                            StdDraw.text(650,110,"A5 -> D5");
                                            Files.hamlePrint("A5","D5");
                                            StdDraw.show();
                                            try{
                                                Thread.sleep(300);
                                            }catch(InterruptedException e){}
                                            return ; 
                                        }
                                        else{
                                            continue ; 
                                        }
                                    }
                                }
                                else if(((_x2>=353&&_x2<=409)&&(_y2>=242&&_y2<=444))&&(E5.numberOfX < 2)){      //E5 icin.
                                    
                                    for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                                        if(Board.hamleler.get(i)==4){
                                            Board.hamleler.remove(i);
                                            A5.numberOfY -=1 ; 
                                            E5.numberOfY +=1 ; 
                                            if(E5.numberOfX ==1){
                                                E5.numberOfX = 0 ; 
                                                E3.numberOfX += 1 ; 
                                            }
                                            StdDraw.setPenColor(StdDraw.WHITE) ; 
                                            StdDraw.filledRectangle(650,110,30,50);
                                            StdDraw.setPenColor();
                                            StdDraw.show();
                                            StdDraw.text(650,110,"A5 -> E5");
                                            Files.hamlePrint("A5","E5");
                                            StdDraw.show();
                                            try{
                                                Thread.sleep(300);
                                            }catch(InterruptedException e){}
                                            return ; 
                                        }
                                        else{
                                            continue ; 
                                        }
                                    }
                                }
                                else if(((_x2>=419&&_x2<=478)&&(_y2>=242&&_y2<=444))&&(F5.numberOfX < 2)){    // F5 icin.
                                    for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                                        if(Board.hamleler.get(i)==5){
                                            Board.hamleler.remove(i);
                                            A5.numberOfY -=1 ; 
                                            F5.numberOfY +=1 ; 
                                            if(F5.numberOfX ==1){
                                                F5.numberOfX = 0 ; 
                                                E3.numberOfX += 1 ; 
                                            }
                                            StdDraw.setPenColor(StdDraw.WHITE) ; 
                                            StdDraw.filledRectangle(650,110,30,50);
                                            StdDraw.setPenColor();
                                            StdDraw.show();
                                            StdDraw.text(650,110,"A5 -> F5");
                                            Files.hamlePrint("A5","F5");
                                            StdDraw.show();
                                            try{
                                                Thread.sleep(300);
                                            }catch(InterruptedException e){}
                                            return ; 
                                        }
                                        else{
                                            continue ; 
                                        }
                                    }
                                }
                                else if(((_x2>=528&&_x2<=583)&&(_y2>=242&&_y2<=444))&&(G5.numberOfX < 2)){     // G5 icin
                                    for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                                        if(Board.hamleler.get(i)==6){
                                            Board.hamleler.remove(i);
                                            A5.numberOfY -=1 ; 
                                            G5.numberOfY +=1 ; 
                                            if(G5.numberOfX ==1){
                                                G5.numberOfX = 0 ; 
                                                E3.numberOfX += 1 ; 
                                            }
                                            StdDraw.setPenColor(StdDraw.WHITE) ; 
                                            StdDraw.filledRectangle(650,110,30,50);
                                            StdDraw.setPenColor();
                                            StdDraw.show();
                                            StdDraw.text(650,110,"A5 -> G5");
                                            Files.hamlePrint("A5","G5");
                                            StdDraw.show();
                                            try{
                                                Thread.sleep(300);
                                            }catch(InterruptedException e){}
                                            return ; 
                                        }
                                    }
                                }    
                            }
                        }
                   }
                }
                else if(((_x>=158 && _x<=215)&&(_y>=242 && _y<=444)) && B5.numberOfY > 0){            // from B5
                    if(checkB5 == false){
                        StdDraw.setPenColor(StdDraw.RED) ; 
                        StdDraw.text(650,110,"No valid move from B5.");
                        StdDraw.show();
                        StdDraw.setPenColor();
                        try{
                            Thread.sleep(300);
                        }catch(InterruptedException e){}
                        StdDraw.setPenColor(StdDraw.WHITE);
                        StdDraw.filledRectangle(650, 110, 90,50);
                        StdDraw.setPenColor();
                        StdDraw.show();
                    }
                   else{
                       StdDraw.text(650,110,"B5 -> ? ");
                        StdDraw.show();
                        double _x2 ; 
                        double _y2 ; 
                        while(true){                                                    // destination loop.
                            if(StdDraw.isMousePressed()){
                                _x2 = StdDraw.mouseX() ; 
                                _y2 = StdDraw.mouseY() ; 
                                if(((_x2>=223&&_x2<=278)&&(_y2>=242&&_y2<=444))&&(C5.numberOfX < 2)){     // C5'e git.
                                    for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                                        if(Board.hamleler.get(i)==1){
                                            Board.hamleler.remove(i);
                                            B5.numberOfY -=1 ; 
                                            C5.numberOfY +=1 ; 
                                            if(C5.numberOfX ==1){
                                                C5.numberOfX = 0 ; 
                                                E3.numberOfX += 1 ; 
                                            }
                                            StdDraw.setPenColor(StdDraw.WHITE) ; 
                                            StdDraw.filledRectangle(650,110,30,50);
                                            StdDraw.setPenColor();
                                            StdDraw.show();
                                            StdDraw.text(650,110,"B5 -> C5");
                                            Files.hamlePrint("B5","C5");
                                            StdDraw.show();
                                            try{
                                                Thread.sleep(300);
                                            }catch(InterruptedException e){}
                                            return ; 
                                        }
                                        else{
                                            continue ; 
                                        }
                                    }
                                }
                                else if(((_x2>=293&&_x2<=343)&&(_y2>=242&&_y2<=444))&&(D5.numberOfX < 2)){   // D5'e git.
                                    
                                    for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                                        if(Board.hamleler.get(i)==2){
                                            Board.hamleler.remove(i);
                                            B5.numberOfY -=1 ; 
                                            D5.numberOfY +=1 ; 
                                            if(D5.numberOfX ==1){
                                                D5.numberOfX = 0 ; 
                                                E3.numberOfX += 1 ; 
                                            }
                                            StdDraw.setPenColor(StdDraw.WHITE) ; 
                                            StdDraw.filledRectangle(650,110,30,50);
                                            StdDraw.setPenColor();
                                            StdDraw.show();
                                            StdDraw.text(650,110,"B5 -> D5");
                                            Files.hamlePrint("B5","D5");
                                            StdDraw.show();
                                            try{
                                                Thread.sleep(300);
                                            }catch(InterruptedException e){}
                                            return ; 
                                        }
                                        else{
                                            continue ; 
                                        }
                                    }
                                }
                                else if(((_x2>=353&&_x2<=409)&&(_y2>=242&&_y2<=444))&&(E5.numberOfX < 2)){   // E5'e git
                                    for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                                        if(Board.hamleler.get(i)==3){
                                            Board.hamleler.remove(i);
                                            B5.numberOfY -=1 ; 
                                            E5.numberOfY +=1 ; 
                                            if(E5.numberOfX ==1){
                                                E5.numberOfX = 0 ; 
                                                E3.numberOfX += 1 ; 
                                            }
                                            StdDraw.setPenColor(StdDraw.WHITE) ; 
                                            StdDraw.filledRectangle(650,110,30,50);
                                            StdDraw.setPenColor();
                                            StdDraw.show();
                                            StdDraw.text(650,110,"B5 -> E5");
                                            Files.hamlePrint("B5","E5");
                                            StdDraw.show();
                                            try{
                                                Thread.sleep(300);
                                            }catch(InterruptedException e){}
                                            return ; 
                                        }
                                        else{
                                            continue ; 
                                        }
                                    }
                                }
                                else if(((_x2>=419&&_x2<=478)&&(_y2>=242&&_y2<=444))&&(F5.numberOfX < 2)){      //F5 icin.
                                    
                                    for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                                        if(Board.hamleler.get(i)==4){
                                            Board.hamleler.remove(i);
                                            B5.numberOfY -=1 ; 
                                            F5.numberOfY +=1 ; 
                                            if(F5.numberOfX ==1){
                                                F5.numberOfX = 0 ; 
                                                E3.numberOfX += 1 ; 
                                            }
                                            StdDraw.setPenColor(StdDraw.WHITE) ; 
                                            StdDraw.filledRectangle(650,110,30,50);
                                            StdDraw.setPenColor();
                                            StdDraw.show();
                                            StdDraw.text(650,110,"B5 -> F5");
                                            Files.hamlePrint("B5","F5");
                                            StdDraw.show();
                                            try{
                                                Thread.sleep(300);
                                            }catch(InterruptedException e){}
                                            return ; 
                                        }
                                        else{
                                            continue ; 
                                        }
                                    }
                                }
                                else if(((_x2>=528&&_x2<=583)&&(_y2>=242&&_y2<=444))&&(G5.numberOfX < 2)){    // G5 icin.
                                    for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                                        if(Board.hamleler.get(i)==5){
                                            Board.hamleler.remove(i);
                                            B5.numberOfY -=1 ; 
                                            G5.numberOfY +=1 ; 
                                            if(G5.numberOfX ==1){
                                                G5.numberOfX = 0 ; 
                                                E3.numberOfX += 1 ; 
                                            }
                                            StdDraw.setPenColor(StdDraw.WHITE) ; 
                                            StdDraw.filledRectangle(650,110,30,50);
                                            StdDraw.setPenColor();
                                            StdDraw.show();
                                            StdDraw.text(650,110,"B5 -> G5");
                                            Files.hamlePrint("B5","G5");
                                            StdDraw.show();
                                            try{
                                                Thread.sleep(300);
                                            }catch(InterruptedException e){}
                                            return ; 
                                        }
                                        else{
                                            continue ; 
                                        }
                                    }
                                }
                                else if(((_x2>=594&&_x2<=647)&&(_y2>=242&&_y2<=444))&&(H5.numberOfX < 2)){     // H5 icin
                                    for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                                        if(Board.hamleler.get(i)==6){
                                            Board.hamleler.remove(i);
                                            B5.numberOfY -=1 ; 
                                            H5.numberOfY +=1 ; 
                                            if(H5.numberOfX ==1){
                                                H5.numberOfX = 0 ; 
                                                E3.numberOfX += 1 ; 
                                            }
                                            StdDraw.setPenColor(StdDraw.WHITE) ; 
                                            StdDraw.filledRectangle(650,110,30,50);
                                            StdDraw.setPenColor();
                                            StdDraw.show();
                                            StdDraw.text(650,110,"B5 -> H5");
                                            Files.hamlePrint("B5","H5");
                                            StdDraw.show();
                                            try{
                                                Thread.sleep(300);
                                            }catch(InterruptedException e){}
                                            return ; 
                                        }
                                    }
                                }    
                            }
                        }
                   }
                }
                else if(((_x>=223 && _x<=278)&&(_y>=242 && _y<=444)) && C5.numberOfY > 0){      //from C5
                    if(checkC5 == false){
                        StdDraw.setPenColor(StdDraw.RED) ; 
                        StdDraw.text(650,110,"No valid move from C5.");
                        StdDraw.show();
                        StdDraw.setPenColor();
                        try{
                            Thread.sleep(300);
                        }catch(InterruptedException e){}
                        StdDraw.setPenColor(StdDraw.WHITE);
                        StdDraw.filledRectangle(650, 110, 90,50);
                        StdDraw.setPenColor();
                        StdDraw.show();
                    }
                   else{
                       StdDraw.text(650,110,"C5 -> ? ");
                        StdDraw.show();
                        double _x2 ; 
                        double _y2 ; 
                        while(true){                                                    // destination loop.
                            if(StdDraw.isMousePressed()){
                                _x2 = StdDraw.mouseX() ; 
                                _y2 = StdDraw.mouseY() ; 
                                if(((_x2>=293&&_x2<=343)&&(_y2>=242&&_y2<=444))&&(D5.numberOfX < 2)){     // D5'e git.
                                    for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                                        if(Board.hamleler.get(i)==1){
                                            Board.hamleler.remove(i);
                                            C5.numberOfY -=1 ; 
                                            D5.numberOfY +=1 ; 
                                            if(D5.numberOfX ==1){
                                                D5.numberOfX = 0 ; 
                                                E3.numberOfX += 1 ; 
                                            }
                                            StdDraw.setPenColor(StdDraw.WHITE) ; 
                                            StdDraw.filledRectangle(650,110,30,50);
                                            StdDraw.setPenColor();
                                            StdDraw.show();
                                            StdDraw.text(650,110,"C5 -> D5");
                                            Files.hamlePrint("C5","D5");
                                            StdDraw.show();
                                            try{
                                                Thread.sleep(300);
                                            }catch(InterruptedException e){}
                                            return ; 
                                        }
                                        else{
                                            continue ; 
                                        }
                                    }
                                }
                                else if(((_x2>=353&&_x2<=409)&&(_y2>=242&&_y2<=444))&&(E5.numberOfX < 2)){   // E5'e git.
                                    
                                    for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                                        if(Board.hamleler.get(i)==2){
                                            Board.hamleler.remove(i);
                                            C5.numberOfY -=1 ; 
                                            E5.numberOfY +=1 ; 
                                            if(E5.numberOfX ==1){
                                                E5.numberOfX = 0 ; 
                                                E3.numberOfX += 1 ; 
                                            }
                                            StdDraw.setPenColor(StdDraw.WHITE) ; 
                                            StdDraw.filledRectangle(650,110,30,50);
                                            StdDraw.setPenColor();
                                            StdDraw.show();
                                            StdDraw.text(650,110,"C5 -> E5");
                                            Files.hamlePrint("C5","E5");
                                            StdDraw.show();
                                            try{
                                                Thread.sleep(300);
                                            }catch(InterruptedException e){}
                                            return ; 
                                        }
                                        else{
                                            continue ; 
                                        }
                                    }
                                }
                                else if(((_x2>=419&&_x2<=478)&&(_y2>=242&&_y2<=444))&&(F5.numberOfX < 2)){   // F5'e git
                                    for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                                        if(Board.hamleler.get(i)==3){
                                            Board.hamleler.remove(i);
                                            C5.numberOfY -=1 ; 
                                            F5.numberOfY +=1 ; 
                                            if(F5.numberOfX ==1){
                                                F5.numberOfX = 0 ; 
                                                E3.numberOfX += 1 ; 
                                            }
                                            StdDraw.setPenColor(StdDraw.WHITE) ; 
                                            StdDraw.filledRectangle(650,110,30,50);
                                            StdDraw.setPenColor();
                                            StdDraw.show();
                                            StdDraw.text(650,110,"C5 -> F5");
                                            Files.hamlePrint("C5","F5");
                                            StdDraw.show();
                                            try{
                                                Thread.sleep(300);
                                            }catch(InterruptedException e){}
                                            return ; 
                                        }
                                        else{
                                            continue ; 
                                        }
                                    }
                                }
                                else if(((_x2>=528&&_x2<=583)&&(_y2>=242&&_y2<=444))&&(G5.numberOfX < 2)){      //G5 icin.
                                    
                                    for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                                        if(Board.hamleler.get(i)==4){
                                            Board.hamleler.remove(i);
                                            C5.numberOfY -=1 ; 
                                            G5.numberOfY +=1 ; 
                                            if(G5.numberOfX ==1){
                                                G5.numberOfX = 0 ; 
                                                E3.numberOfX += 1 ; 
                                            }
                                            StdDraw.setPenColor(StdDraw.WHITE) ; 
                                            StdDraw.filledRectangle(650,110,30,50);
                                            StdDraw.setPenColor();
                                            StdDraw.show();
                                            StdDraw.text(650,110,"C5 -> G5");
                                            Files.hamlePrint("C5","G5");
                                            StdDraw.show();
                                            try{
                                                Thread.sleep(300);
                                            }catch(InterruptedException e){}
                                            return ; 
                                        }
                                        else{
                                            continue ; 
                                        }
                                    }
                                }
                                else if(((_x2>=594&&_x2<=647)&&(_y2>=242&&_y2<=444))&&(H5.numberOfX < 2)){    // H5 icin.
                                    for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                                        if(Board.hamleler.get(i)==5){
                                            Board.hamleler.remove(i);
                                            C5.numberOfY -=1 ; 
                                            H5.numberOfY +=1 ; 
                                            if(H5.numberOfX ==1){
                                                H5.numberOfX = 0 ; 
                                                E3.numberOfX += 1 ; 
                                            }
                                            StdDraw.setPenColor(StdDraw.WHITE) ; 
                                            StdDraw.filledRectangle(650,110,30,50);
                                            StdDraw.setPenColor();
                                            StdDraw.show();
                                            StdDraw.text(650,110,"C5 -> H5");
                                            Files.hamlePrint("C5","H5");
                                            StdDraw.show();
                                            try{
                                                Thread.sleep(300);
                                            }catch(InterruptedException e){}
                                            return ; 
                                        }
                                        else{
                                            continue ; 
                                        }
                                    }
                                }
                                else if(((_x2>=659&&_x2<=715)&&(_y2>=242&&_y2<=444))&&(I5.numberOfX < 2)){     // I5 icin
                                    for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                                        if(Board.hamleler.get(i)==6){
                                            Board.hamleler.remove(i);
                                            C5.numberOfY -=1 ; 
                                            I5.numberOfY +=1 ; 
                                            if(I5.numberOfX ==1){
                                                I5.numberOfX = 0 ; 
                                                E3.numberOfX += 1 ; 
                                            }
                                            StdDraw.setPenColor(StdDraw.WHITE) ; 
                                            StdDraw.filledRectangle(650,110,30,50);
                                            StdDraw.setPenColor();
                                            StdDraw.show();
                                            StdDraw.text(650,110,"C5 -> I5");
                                            Files.hamlePrint("C5","I5");
                                            StdDraw.show();
                                            try{
                                                Thread.sleep(300);
                                            }catch(InterruptedException e){}
                                            return ; 
                                        }
                                    }
                                }    
                            }
                        }
                   }
                }
                else if(((_x>=293 && _x<=343)&&(_y>=242 && _y<=444)) && D5.numberOfY > 0){     // from D5
                    if(checkD5 == false){
                        StdDraw.setPenColor(StdDraw.RED) ; 
                        StdDraw.text(650,110,"No valid move from D5.");
                        StdDraw.show();
                        StdDraw.setPenColor();
                        try{
                            Thread.sleep(300);
                        }catch(InterruptedException e){}
                        StdDraw.setPenColor(StdDraw.WHITE);
                        StdDraw.filledRectangle(650, 110, 90,50);
                        StdDraw.setPenColor();
                        StdDraw.show();
                    }
                   else{
                       StdDraw.text(650,110,"D5 -> ? ");
                        StdDraw.show();
                        double _x2 ; 
                        double _y2 ; 
                        while(true){                                                    // destination loop.
                            if(StdDraw.isMousePressed()){
                                _x2 = StdDraw.mouseX() ; 
                                _y2 = StdDraw.mouseY() ; 
                                if(((_x2>=353&&_x2<=409)&&(_y2>=242&&_y2<=444))&&(E5.numberOfX < 2)){     // E5'e git.
                                    for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                                        if(Board.hamleler.get(i)==1){
                                            Board.hamleler.remove(i);
                                            D5.numberOfY -=1 ; 
                                            E5.numberOfY +=1 ; 
                                            if(E5.numberOfX ==1){
                                                E5.numberOfX = 0 ; 
                                                E3.numberOfX += 1 ; 
                                            }
                                            StdDraw.setPenColor(StdDraw.WHITE) ; 
                                            StdDraw.filledRectangle(650,110,30,50);
                                            StdDraw.setPenColor();
                                            StdDraw.show();
                                            StdDraw.text(650,110,"D5 -> E5");
                                            Files.hamlePrint("D5","E5");
                                            StdDraw.show();
                                            try{
                                                Thread.sleep(300);
                                            }catch(InterruptedException e){}
                                            return ; 
                                        }
                                        else{
                                            continue ; 
                                        }
                                    }
                                }
                                else if(((_x2>=419&&_x2<=478)&&(_y2>=242&&_y2<=444))&&(F5.numberOfX < 2)){   // F5'e git.
                                    
                                    for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                                        if(Board.hamleler.get(i)==2){
                                            Board.hamleler.remove(i);
                                            D5.numberOfY -=1 ; 
                                            F5.numberOfY +=1 ; 
                                            if(F5.numberOfX ==1){
                                                F5.numberOfX = 0 ; 
                                                E3.numberOfX += 1 ; 
                                            }
                                            StdDraw.setPenColor(StdDraw.WHITE) ; 
                                            StdDraw.filledRectangle(650,110,30,50);
                                            StdDraw.setPenColor();
                                            StdDraw.show();
                                            StdDraw.text(650,110,"D5 -> F5");
                                            Files.hamlePrint("D5","F5");
                                            StdDraw.show();
                                            try{
                                                Thread.sleep(300);
                                            }catch(InterruptedException e){}
                                            return ; 
                                        }
                                        else{
                                            continue ; 
                                        }
                                    }
                                }
                                else if(((_x2>=528&&_x2<=583)&&(_y2>=242&&_y2<=444))&&(G5.numberOfX < 2)){   // G5'e git
                                    for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                                        if(Board.hamleler.get(i)==3){
                                            Board.hamleler.remove(i);
                                            D5.numberOfY -=1 ; 
                                            G5.numberOfY +=1 ; 
                                            if(G5.numberOfX ==1){
                                                G5.numberOfX = 0 ; 
                                                E3.numberOfX += 1 ; 
                                            }
                                            StdDraw.setPenColor(StdDraw.WHITE) ; 
                                            StdDraw.filledRectangle(650,110,30,50);
                                            StdDraw.setPenColor();
                                            StdDraw.show();
                                            StdDraw.text(650,110,"D5 -> G5");
                                            Files.hamlePrint("D5","G5");
                                            StdDraw.show();
                                            try{
                                                Thread.sleep(300);
                                            }catch(InterruptedException e){}
                                            return ; 
                                        }
                                        else{
                                            continue ; 
                                        }
                                    }
                                }
                                else if(((_x2>=594&&_x2<=647)&&(_y2>=242&&_y2<=444))&&(H5.numberOfX < 2)){      //H5 icin.
                                    
                                    for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                                        if(Board.hamleler.get(i)==4){
                                            Board.hamleler.remove(i);
                                            D5.numberOfY -=1 ; 
                                            H5.numberOfY +=1 ; 
                                            if(H5.numberOfX ==1){
                                                H5.numberOfX = 0 ; 
                                                E3.numberOfX += 1 ; 
                                            }
                                            StdDraw.setPenColor(StdDraw.WHITE) ; 
                                            StdDraw.filledRectangle(650,110,30,50);
                                            StdDraw.setPenColor();
                                            StdDraw.show();
                                            StdDraw.text(650,110,"D5 -> H5");
                                            Files.hamlePrint("D5","H5");
                                            StdDraw.show();
                                            try{
                                                Thread.sleep(300);
                                            }catch(InterruptedException e){}
                                            return ; 
                                        }
                                        else{
                                            continue ; 
                                        }
                                    }
                                }
                                else if(((_x2>=659&&_x2<=715)&&(_y2>=242&&_y2<=444))&&(I5.numberOfX < 2)){    // I5 icin.
                                    for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                                        if(Board.hamleler.get(i)==5){
                                            Board.hamleler.remove(i);
                                            D5.numberOfY -=1 ; 
                                            I5.numberOfY +=1 ; 
                                            if(I5.numberOfX ==1){
                                                I5.numberOfX = 0 ; 
                                                E3.numberOfX += 1 ; 
                                            }
                                            StdDraw.setPenColor(StdDraw.WHITE) ; 
                                            StdDraw.filledRectangle(650,110,30,50);
                                            StdDraw.setPenColor();
                                            StdDraw.show();
                                            StdDraw.text(650,110,"D5 -> I5");
                                            Files.hamlePrint("D5","I5");
                                            StdDraw.show();
                                            try{
                                                Thread.sleep(300);
                                            }catch(InterruptedException e){}
                                            return ; 
                                        }
                                        else{
                                            continue ; 
                                        }
                                    }
                                }
                                else if(((_x2>=722&&_x2<=780)&&(_y2>=242&&_y2<=444))&&(J5.numberOfX < 2)){     // J5 icin
                                    for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                                        if(Board.hamleler.get(i)==6){
                                            Board.hamleler.remove(i);
                                            D5.numberOfY -=1 ; 
                                            J5.numberOfY +=1 ; 
                                            if(J5.numberOfX ==1){
                                                J5.numberOfX = 0 ; 
                                                E3.numberOfX += 1 ; 
                                            }
                                            StdDraw.setPenColor(StdDraw.WHITE) ; 
                                            StdDraw.filledRectangle(650,110,30,50);
                                            StdDraw.setPenColor();
                                            StdDraw.show();
                                            StdDraw.text(650,110,"D5 -> J5");
                                            Files.hamlePrint("D5","J5");
                                            StdDraw.show();
                                            try{
                                                Thread.sleep(300);
                                            }catch(InterruptedException e){}
                                            return ; 
                                        }
                                    }
                                }    
                            }
                        }
                   }
                }
                else if(((_x>=353 && _x<=409)&&(_y>=242 && _y<=444)) && E5.numberOfY > 0){      // from E5
                     if(checkE5 == false){
                        StdDraw.setPenColor(StdDraw.RED) ; 
                        StdDraw.text(650,110,"No valid move from E5.");
                        StdDraw.show();
                        StdDraw.setPenColor();
                        try{
                            Thread.sleep(300);
                        }catch(InterruptedException e){}
                        StdDraw.setPenColor(StdDraw.WHITE);
                        StdDraw.filledRectangle(650, 110, 90,50);
                        StdDraw.setPenColor();
                        StdDraw.show();
                    }
                   else{
                       StdDraw.text(650,110,"E5 -> ? ");
                        StdDraw.show();
                        double _x2 ; 
                        double _y2 ; 
                        while(true){                                                    // destination loop.
                            if(StdDraw.isMousePressed()){
                                _x2 = StdDraw.mouseX() ; 
                                _y2 = StdDraw.mouseY() ; 
                                if(((_x2>=419&&_x2<=478)&&(_y2>=242&&_y2<=444))&&(F5.numberOfX < 2)){     // F5'e git.
                                    for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                                        if(Board.hamleler.get(i)==1){
                                            Board.hamleler.remove(i);
                                            E5.numberOfY -=1 ; 
                                            F5.numberOfY +=1 ; 
                                            if(F5.numberOfX ==1){
                                                F5.numberOfX = 0 ; 
                                                E3.numberOfX += 1 ; 
                                            }
                                            StdDraw.setPenColor(StdDraw.WHITE) ; 
                                            StdDraw.filledRectangle(650,110,30,50);
                                            StdDraw.setPenColor();
                                            StdDraw.show();
                                            StdDraw.text(650,110,"E5 -> F5");
                                            Files.hamlePrint("E5","F5");
                                            StdDraw.show();
                                            try{
                                                Thread.sleep(300);
                                            }catch(InterruptedException e){}
                                            return ; 
                                        }
                                        else{
                                            continue ; 
                                        }
                                    }
                                }
                                else if(((_x2>=528&&_x2<=583)&&(_y2>=242&&_y2<=444))&&(G5.numberOfX < 2)){   // G5'e git.
                                    
                                    for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                                        if(Board.hamleler.get(i)==2){
                                            Board.hamleler.remove(i);
                                            E5.numberOfY -=1 ; 
                                            G5.numberOfY +=1 ; 
                                            if(G5.numberOfX ==1){
                                                G5.numberOfX = 0 ; 
                                                E3.numberOfX += 1 ; 
                                            }
                                            StdDraw.setPenColor(StdDraw.WHITE) ; 
                                            StdDraw.filledRectangle(650,110,30,50);
                                            StdDraw.setPenColor();
                                            StdDraw.show();
                                            StdDraw.text(650,110,"E5 -> G5");
                                            Files.hamlePrint("E5","G5");
                                            StdDraw.show();
                                            try{
                                                Thread.sleep(300);
                                            }catch(InterruptedException e){}
                                            return ; 
                                        }
                                        else{
                                            continue ; 
                                        }
                                    }
                                }
                                else if(((_x2>=594&&_x2<=647)&&(_y2>=242&&_y2<=444))&&(H5.numberOfX < 2)){   // H5'e git
                                    for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                                        if(Board.hamleler.get(i)==3){
                                            Board.hamleler.remove(i);
                                            E5.numberOfY -=1 ; 
                                            H5.numberOfY +=1 ; 
                                            if(H5.numberOfX ==1){
                                                H5.numberOfX = 0 ; 
                                                E3.numberOfX += 1 ; 
                                            }
                                            StdDraw.setPenColor(StdDraw.WHITE) ; 
                                            StdDraw.filledRectangle(650,110,30,50);
                                            StdDraw.setPenColor();
                                            StdDraw.show();
                                            StdDraw.text(650,110,"E5 -> H5");
                                            Files.hamlePrint("E5","H5");
                                            StdDraw.show();
                                            try{
                                                Thread.sleep(300);
                                            }catch(InterruptedException e){}
                                            return ; 
                                        }
                                        else{
                                            continue ; 
                                        }
                                    }
                                }
                                else if(((_x2>=659&&_x2<=715)&&(_y2>=242&&_y2<=444))&&(I5.numberOfX < 2)){      //I5 icin.
                                    
                                    for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                                        if(Board.hamleler.get(i)==4){
                                            Board.hamleler.remove(i);
                                            E5.numberOfY -=1 ; 
                                            I5.numberOfY +=1 ; 
                                            if(I5.numberOfX ==1){
                                                I5.numberOfX = 0 ; 
                                                E3.numberOfX += 1 ; 
                                            }
                                            StdDraw.setPenColor(StdDraw.WHITE) ; 
                                            StdDraw.filledRectangle(650,110,30,50);
                                            StdDraw.setPenColor();
                                            StdDraw.show();
                                            StdDraw.text(650,110,"E5 -> I5");
                                            Files.hamlePrint("E5","I5");
                                            StdDraw.show();
                                            try{
                                                Thread.sleep(300);
                                            }catch(InterruptedException e){}
                                            return ; 
                                        }
                                        else{
                                            continue ; 
                                        }
                                    }
                                }
                                else if(((_x2>=722&&_x2<=780)&&(_y2>=242&&_y2<=444))&&(J5.numberOfX < 2)){    // J5 icin.
                                    for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                                        if(Board.hamleler.get(i)==5){
                                            Board.hamleler.remove(i);
                                            E5.numberOfY -=1 ; 
                                            J5.numberOfY +=1 ; 
                                            if(J5.numberOfX ==1){
                                                J5.numberOfX = 0 ; 
                                                E3.numberOfX += 1 ; 
                                            }
                                            StdDraw.setPenColor(StdDraw.WHITE) ; 
                                            StdDraw.filledRectangle(650,110,30,50);
                                            StdDraw.setPenColor();
                                            StdDraw.show();
                                            StdDraw.text(650,110,"E5 -> J5");
                                            Files.hamlePrint("E5","J5");
                                            StdDraw.show();
                                            try{
                                                Thread.sleep(300);
                                            }catch(InterruptedException e){}
                                            return ; 
                                        }
                                        else{
                                            continue ; 
                                        }
                                    }
                                }
                                else if(((_x2>=790&&_x2<=842)&&(_y2>=242&&_y2<=444))&&(K5.numberOfX < 2)){     // K5 icin
                                    for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                                        if(Board.hamleler.get(i)==6){
                                            Board.hamleler.remove(i);
                                            E5.numberOfY -=1 ; 
                                            K5.numberOfY +=1 ; 
                                            if(K5.numberOfX ==1){
                                                K5.numberOfX = 0 ; 
                                                E3.numberOfX += 1 ; 
                                            }
                                            StdDraw.setPenColor(StdDraw.WHITE) ; 
                                            StdDraw.filledRectangle(650,110,30,50);
                                            StdDraw.setPenColor();
                                            StdDraw.show();
                                            StdDraw.text(650,110,"E5 -> K5");
                                            Files.hamlePrint("E5","K5");
                                            StdDraw.show();
                                            try{
                                                Thread.sleep(300);
                                            }catch(InterruptedException e){}
                                            return ; 
                                        }
                                    }
                                }    
                            }
                        }
                   }
                }
                else if(((_x>=419 && _x<=478)&&(_y>=242 && _y<=444)) && F5.numberOfY > 0){     // from F5
                    if(checkF5 == false){
                        StdDraw.setPenColor(StdDraw.RED) ; 
                        StdDraw.text(650,110,"No valid move from F5.");
                        StdDraw.show();
                        StdDraw.setPenColor();
                        try{
                            Thread.sleep(300);
                        }catch(InterruptedException e){}
                        StdDraw.setPenColor(StdDraw.WHITE);
                        StdDraw.filledRectangle(650, 110, 90,50);
                        StdDraw.setPenColor();
                        StdDraw.show();
                    }
                   else{
                       StdDraw.text(650,110,"F5 -> ? ");
                        StdDraw.show();
                        double _x2 ; 
                        double _y2 ; 
                        while(true){                                                    // destination loop.
                            if(StdDraw.isMousePressed()){
                                _x2 = StdDraw.mouseX() ; 
                                _y2 = StdDraw.mouseY() ; 
                                if(((_x2>=528&&_x2<=583)&&(_y2>=242&&_y2<=444))&&(G5.numberOfX < 2)){     // G5'e git.
                                    for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                                        if(Board.hamleler.get(i)==1){
                                            Board.hamleler.remove(i);
                                            F5.numberOfY -=1 ; 
                                            G5.numberOfY +=1 ; 
                                            if(G5.numberOfX ==1){
                                                G5.numberOfX = 0 ; 
                                                E3.numberOfX += 1 ; 
                                            }
                                            StdDraw.setPenColor(StdDraw.WHITE) ; 
                                            StdDraw.filledRectangle(650,110,30,50);
                                            StdDraw.setPenColor();
                                            StdDraw.show();
                                            StdDraw.text(650,110,"F5 -> G5");
                                            Files.hamlePrint("E5","G5");
                                            StdDraw.show();
                                            try{
                                                Thread.sleep(300);
                                            }catch(InterruptedException e){}
                                            return ; 
                                        }
                                        else{
                                            continue ; 
                                        }
                                    }
                                }
                                else if(((_x2>=594&&_x2<=647)&&(_y2>=242&&_y2<=444))&&(H5.numberOfX < 2)){   // H5'e git.
                                    
                                    for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                                        if(Board.hamleler.get(i)==2){
                                            Board.hamleler.remove(i);
                                            F5.numberOfY -=1 ; 
                                            H5.numberOfY +=1 ; 
                                            if(H5.numberOfX ==1){
                                                H5.numberOfX = 0 ; 
                                                E3.numberOfX += 1 ; 
                                            }
                                            StdDraw.setPenColor(StdDraw.WHITE) ; 
                                            StdDraw.filledRectangle(650,110,30,50);
                                            StdDraw.setPenColor();
                                            StdDraw.show();
                                            StdDraw.text(650,110,"F5 -> H5");
                                            Files.hamlePrint("E5","H5");
                                            StdDraw.show();
                                            try{
                                                Thread.sleep(300);
                                            }catch(InterruptedException e){}
                                            return ; 
                                        }
                                        else{
                                            continue ; 
                                        }
                                    }
                                }
                                else if(((_x2>=659&&_x2<=715)&&(_y2>=242&&_y2<=444))&&(I5.numberOfX < 2)){   // I5'e git
                                    for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                                        if(Board.hamleler.get(i)==3){
                                            Board.hamleler.remove(i);
                                            F5.numberOfY -=1 ; 
                                            I5.numberOfY +=1 ; 
                                            if(I5.numberOfX ==1){
                                                I5.numberOfX = 0 ; 
                                                E3.numberOfX += 1 ; 
                                            }
                                            StdDraw.setPenColor(StdDraw.WHITE) ; 
                                            StdDraw.filledRectangle(650,110,30,50);
                                            StdDraw.setPenColor();
                                            StdDraw.show();
                                            StdDraw.text(650,110,"F5 -> I5");
                                            Files.hamlePrint("E5","I5");
                                            StdDraw.show();
                                            try{
                                                Thread.sleep(300);
                                            }catch(InterruptedException e){}
                                            return ; 
                                        }
                                        else{
                                            continue ; 
                                        }
                                    }
                                }
                                else if(((_x2>=722&&_x2<=780)&&(_y2>=242&&_y2<=444))&&(J5.numberOfX < 2)){      //J5 icin.
                                    
                                    for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                                        if(Board.hamleler.get(i)==4){
                                            Board.hamleler.remove(i);
                                            F5.numberOfY -=1 ; 
                                            J5.numberOfY +=1 ; 
                                            if(J5.numberOfX ==1){
                                                J5.numberOfX = 0 ; 
                                                E3.numberOfX += 1 ; 
                                            }
                                            StdDraw.setPenColor(StdDraw.WHITE) ; 
                                            StdDraw.filledRectangle(650,110,30,50);
                                            StdDraw.setPenColor();
                                            StdDraw.show();
                                            StdDraw.text(650,110,"F5 -> J5");
                                            Files.hamlePrint("E5","J5");
                                            StdDraw.show();
                                            try{
                                                Thread.sleep(300);
                                            }catch(InterruptedException e){}
                                            return ; 
                                        }
                                        else{
                                            continue ; 
                                        }
                                    }
                                }
                                else if(((_x2>=790&&_x2<=842)&&(_y2>=242&&_y2<=444))&&(K5.numberOfX < 2)){    // K5 icin.
                                    for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                                        if(Board.hamleler.get(i)==5){
                                            Board.hamleler.remove(i);
                                            F5.numberOfY -=1 ; 
                                            K5.numberOfY +=1 ; 
                                            if(K5.numberOfX ==1){
                                                K5.numberOfX = 0 ; 
                                                E3.numberOfX += 1 ; 
                                            }
                                            StdDraw.setPenColor(StdDraw.WHITE) ; 
                                            StdDraw.filledRectangle(650,110,30,50);
                                            StdDraw.setPenColor();
                                            StdDraw.show();
                                            StdDraw.text(650,110,"F5 -> K5");
                                            Files.hamlePrint("E5","K5");
                                            StdDraw.show();
                                            try{
                                                Thread.sleep(300);
                                            }catch(InterruptedException e){}
                                            return ; 
                                        }
                                        else{
                                            continue ; 
                                        }
                                    }
                                }
                                else if(((_x2>=852&&_x2<=910)&&(_y2>=242&&_y2<=444))&&(L5.numberOfX < 2)){     // L5 icin
                                    for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                                        if(Board.hamleler.get(i)==6){
                                            Board.hamleler.remove(i);
                                            F5.numberOfY -=1 ; 
                                            L5.numberOfY +=1 ; 
                                            if(L5.numberOfX ==1){
                                                L5.numberOfX = 0 ; 
                                                E3.numberOfX += 1 ; 
                                            }
                                            StdDraw.setPenColor(StdDraw.WHITE) ; 
                                            StdDraw.filledRectangle(650,110,30,50);
                                            StdDraw.setPenColor();
                                            StdDraw.show();
                                            StdDraw.text(650,110,"F5 -> L5");
                                             Files.hamlePrint("E5","L5");
                                            StdDraw.show();
                                            try{
                                                Thread.sleep(300);
                                            }catch(InterruptedException e){}
                                            return ; 
                                        }
                                    }
                                }    
                            }
                        }
                   }
                }
                else if(((_x>=528 && _x<=583)&&(_y>=242 && _y<=444)) && G5.numberOfY > 0){   // from G5
                    if(checkG5 == false){
                        StdDraw.setPenColor(StdDraw.RED) ; 
                        StdDraw.text(650,110,"No valid move from G5.");
                        StdDraw.show();
                        StdDraw.setPenColor();
                        try{
                            Thread.sleep(300);
                        }catch(InterruptedException e){}
                        StdDraw.setPenColor(StdDraw.WHITE);
                        StdDraw.filledRectangle(650, 110, 90,50);
                        StdDraw.setPenColor();
                        StdDraw.show();
                    }
                   else{
                       StdDraw.text(650,110,"G5 -> ? ");
                        StdDraw.show();
                        double _x2 ; 
                        double _y2 ; 
                        while(true){                                                    // destination loop.
                            if(StdDraw.isMousePressed()){
                                _x2 = StdDraw.mouseX() ; 
                                _y2 = StdDraw.mouseY() ; 
                                if(((_x2>=594&&_x2<=647)&&(_y2>=242&&_y2<=444))&&(H5.numberOfX < 2)){     // H5'e git.
                                    for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                                        if(Board.hamleler.get(i)==1){
                                            Board.hamleler.remove(i);
                                            G5.numberOfY -=1 ; 
                                            H5.numberOfY +=1 ; 
                                            if(H5.numberOfX ==1){
                                                H5.numberOfX = 0 ; 
                                                E3.numberOfX += 1 ; 
                                            }
                                            StdDraw.setPenColor(StdDraw.WHITE) ; 
                                            StdDraw.filledRectangle(650,110,30,50);
                                            StdDraw.setPenColor();
                                            StdDraw.show();
                                            StdDraw.text(650,110,"G5 -> H5");
                                            Files.hamlePrint("G5","H5");
                                            StdDraw.show();
                                            try{
                                                Thread.sleep(300);
                                            }catch(InterruptedException e){}
                                            return ; 
                                        }
                                        else{
                                            continue ; 
                                        }
                                    }
                                }
                                else if(((_x2>=659&&_x2<=715)&&(_y2>=242&&_y2<=444))&&(I5.numberOfX < 2)){   // I5'e git.
                                    
                                    for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                                        if(Board.hamleler.get(i)==2){
                                            Board.hamleler.remove(i);
                                            G5.numberOfY -=1 ; 
                                            I5.numberOfY +=1 ; 
                                            if(I5.numberOfX ==1){
                                                I5.numberOfX = 0 ; 
                                                E3.numberOfX += 1 ; 
                                            }
                                            StdDraw.setPenColor(StdDraw.WHITE) ; 
                                            StdDraw.filledRectangle(650,110,30,50);
                                            StdDraw.setPenColor();
                                            StdDraw.show();
                                            StdDraw.text(650,110,"G5 -> I5");
                                            Files.hamlePrint("G5","I5");
                                            StdDraw.show();
                                            try{
                                                Thread.sleep(300);
                                            }catch(InterruptedException e){}
                                            return ; 
                                        }
                                        else{
                                            continue ; 
                                        }
                                    }
                                }
                                else if(((_x2>=722&&_x2<=780)&&(_y2>=242&&_y2<=444))&&(J5.numberOfX < 2)){   // J5'e git
                                    for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                                        if(Board.hamleler.get(i)==3){
                                            Board.hamleler.remove(i);
                                            G5.numberOfY -=1 ; 
                                            J5.numberOfY +=1 ; 
                                            if(J5.numberOfX ==1){
                                                J5.numberOfX = 0 ; 
                                                E3.numberOfX += 1 ; 
                                            }
                                            StdDraw.setPenColor(StdDraw.WHITE) ; 
                                            StdDraw.filledRectangle(650,110,30,50);
                                            StdDraw.setPenColor();
                                            StdDraw.show();
                                            StdDraw.text(650,110,"G5 -> J5");
                                            Files.hamlePrint("G5","J5");
                                            StdDraw.show();
                                            try{
                                                Thread.sleep(300);
                                            }catch(InterruptedException e){}
                                            return ; 
                                        }
                                        else{
                                            continue ; 
                                        }
                                    }
                                }
                                else if(((_x2>=790&&_x2<=842)&&(_y2>=242&&_y2<=444))&&(K5.numberOfX < 2)){      //K5 icin.
                                    
                                    for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                                        if(Board.hamleler.get(i)==4){
                                            Board.hamleler.remove(i);
                                            G5.numberOfY -=1 ; 
                                            K5.numberOfY +=1 ; 
                                            if(K5.numberOfX ==1){
                                                K5.numberOfX = 0 ; 
                                                E3.numberOfX += 1 ; 
                                            }
                                            StdDraw.setPenColor(StdDraw.WHITE) ; 
                                            StdDraw.filledRectangle(650,110,30,50);
                                            StdDraw.setPenColor();
                                            StdDraw.show();
                                            StdDraw.text(650,110,"G5 -> K5");
                                            Files.hamlePrint("G5","K5");
                                            StdDraw.show();
                                            try{
                                                Thread.sleep(300);
                                            }catch(InterruptedException e){}
                                            return ; 
                                        }
                                        else{
                                            continue ; 
                                        }
                                    }
                                }
                                else if(((_x2>=852&&_x2<=910)&&(_y2>=242&&_y2<=444))&&(L5.numberOfX < 2)){    // L5 icin.
                                    for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                                        if(Board.hamleler.get(i)==5){
                                            Board.hamleler.remove(i);
                                            G5.numberOfY -=1 ; 
                                            L5.numberOfY +=1 ; 
                                            if(L5.numberOfX ==1){
                                                L5.numberOfX = 0 ; 
                                                E3.numberOfX += 1 ; 
                                            }
                                            StdDraw.setPenColor(StdDraw.WHITE) ; 
                                            StdDraw.filledRectangle(650,110,30,50);
                                            StdDraw.setPenColor();
                                            StdDraw.show();
                                            StdDraw.text(650,110,"G5 -> L5");
                                            Files.hamlePrint("G5","L5");
                                            StdDraw.show();
                                            try{
                                                Thread.sleep(300);
                                            }catch(InterruptedException e){}
                                            return ; 
                                        }
                                        else{
                                            continue ; 
                                        }
                                    }
                                } 
                            }
                        }
                   }
                }
                else if(((_x>=594 && _x<=647)&&(_y>=242 && _y<=444)) && H5.numberOfY > 0){    // from H5
                    if(checkH5 == false){
                        StdDraw.setPenColor(StdDraw.RED) ; 
                        StdDraw.text(650,110,"No valid move from H5.");
                        StdDraw.show();
                        StdDraw.setPenColor();
                        try{
                            Thread.sleep(300);
                        }catch(InterruptedException e){}
                        StdDraw.setPenColor(StdDraw.WHITE);
                        StdDraw.filledRectangle(650, 110, 90,50);
                        StdDraw.setPenColor();
                        StdDraw.show();
                    }
                   else{
                       StdDraw.text(650,110,"H5 -> ? ");
                        StdDraw.show();
                        double _x2 ; 
                        double _y2 ; 
                        while(true){                                                    // destination loop.
                            if(StdDraw.isMousePressed()){
                                _x2 = StdDraw.mouseX() ; 
                                _y2 = StdDraw.mouseY() ; 
                                if(((_x2>=659&&_x2<=715)&&(_y2>=242&&_y2<=444))&&(I5.numberOfX < 2)){     // I5'e git.
                                    for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                                        if(Board.hamleler.get(i)==1){
                                            Board.hamleler.remove(i);
                                            H5.numberOfY -=1 ; 
                                            I5.numberOfY +=1 ; 
                                            if(I5.numberOfX ==1){
                                                I5.numberOfX = 0 ; 
                                                E3.numberOfX += 1 ; 
                                            }
                                            StdDraw.setPenColor(StdDraw.WHITE) ; 
                                            StdDraw.filledRectangle(650,110,30,50);
                                            StdDraw.setPenColor();
                                            StdDraw.show();
                                            StdDraw.text(650,110,"H5 -> I5");
                                            Files.hamlePrint("H5","I5");
                                            StdDraw.show();
                                            try{
                                                Thread.sleep(300);
                                            }catch(InterruptedException e){}
                                            return ; 
                                        }
                                        else{
                                            continue ; 
                                        }
                                    }
                                }
                                else if(((_x2>=722&&_x2<=780)&&(_y2>=242&&_y2<=444))&&(J5.numberOfX < 2)){   // J5'e git.
                                    
                                    for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                                        if(Board.hamleler.get(i)==2){
                                            Board.hamleler.remove(i);
                                            H5.numberOfY -=1 ; 
                                            J5.numberOfY +=1 ; 
                                            if(J5.numberOfX ==1){
                                                J5.numberOfX = 0 ; 
                                                E3.numberOfX += 1 ; 
                                            }
                                            StdDraw.setPenColor(StdDraw.WHITE) ; 
                                            StdDraw.filledRectangle(650,110,30,50);
                                            StdDraw.setPenColor();
                                            StdDraw.show();
                                            StdDraw.text(650,110,"H5 -> J5");
                                            Files.hamlePrint("H5","J5");
                                            StdDraw.show();
                                            try{
                                                Thread.sleep(300);
                                            }catch(InterruptedException e){}
                                            return ; 
                                        }
                                        else{
                                            continue ; 
                                        }
                                    }
                                }
                                else if(((_x2>=790&&_x2<=842)&&(_y2>=242&&_y2<=444))&&(K5.numberOfX < 2)){   // K5'e git
                                    for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                                        if(Board.hamleler.get(i)==3){
                                            Board.hamleler.remove(i);
                                            H5.numberOfY -=1 ; 
                                            K5.numberOfY +=1 ; 
                                            if(K5.numberOfX ==1){
                                                K5.numberOfX = 0 ; 
                                                E3.numberOfX += 1 ; 
                                            }
                                            StdDraw.setPenColor(StdDraw.WHITE) ; 
                                            StdDraw.filledRectangle(650,110,30,50);
                                            StdDraw.setPenColor();
                                            StdDraw.show();
                                            StdDraw.text(650,110,"H5 -> K5");
                                            Files.hamlePrint("H5","K5");
                                            StdDraw.show();
                                            try{
                                                Thread.sleep(300);
                                            }catch(InterruptedException e){}
                                            return ; 
                                        }
                                        else{
                                            continue ; 
                                        }
                                    }
                                }
                                else if(((_x2>=852&&_x2<=910)&&(_y2>=242&&_y2<=444))&&(L5.numberOfX < 2)){      //L5 icin.
                                    
                                    for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                                        if(Board.hamleler.get(i)==4){
                                            Board.hamleler.remove(i);
                                            H5.numberOfY -=1 ; 
                                            L5.numberOfY +=1 ; 
                                            if(L5.numberOfX ==1){
                                                L5.numberOfX = 0 ; 
                                                E3.numberOfX += 1 ; 
                                            }
                                            StdDraw.setPenColor(StdDraw.WHITE) ; 
                                            StdDraw.filledRectangle(650,110,30,50);
                                            StdDraw.setPenColor();
                                            StdDraw.show();
                                            StdDraw.text(650,110,"H5 -> L5");
                                            Files.hamlePrint("H5","L5");
                                            StdDraw.show();
                                            try{
                                                Thread.sleep(300);
                                            }catch(InterruptedException e){}
                                            return ; 
                                        }
                                        else{
                                            continue ; 
                                        }
                                    }
                                }
                            }
                        }
                   }
                }
                else if(((_x>=659 && _x<=715)&&(_y>=242 && _y<=444)) && I5.numberOfY > 0){     // from I5
                    if(checkI5 == false){
                        StdDraw.setPenColor(StdDraw.RED) ; 
                        StdDraw.text(650,110,"No valid move from I5.");
                        StdDraw.show();
                        StdDraw.setPenColor();
                        try{
                            Thread.sleep(300);
                        }catch(InterruptedException e){}
                        StdDraw.setPenColor(StdDraw.WHITE);
                        StdDraw.filledRectangle(650, 110, 90,50);
                        StdDraw.setPenColor();
                        StdDraw.show();
                    }
                   else{
                       StdDraw.text(650,110,"I5 -> ? ");
                        StdDraw.show();
                        double _x2 ; 
                        double _y2 ; 
                        while(true){                                                    // destination loop.
                            if(StdDraw.isMousePressed()){
                                _x2 = StdDraw.mouseX() ; 
                                _y2 = StdDraw.mouseY() ; 
                                if(((_x2>=722&&_x2<=780)&&(_y2>=242&&_y2<=444))&&(J5.numberOfX < 2)){     // J5'e git.
                                    for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                                        if(Board.hamleler.get(i)==1){
                                            Board.hamleler.remove(i);
                                            I5.numberOfY -=1 ; 
                                            J5.numberOfY +=1 ; 
                                            if(J5.numberOfX ==1){
                                                J5.numberOfX = 0 ; 
                                                E3.numberOfX += 1 ; 
                                            }
                                            StdDraw.setPenColor(StdDraw.WHITE) ; 
                                            StdDraw.filledRectangle(650,110,30,50);
                                            StdDraw.setPenColor();
                                            StdDraw.show();
                                            StdDraw.text(650,110,"I5 -> J5");
                                            Files.hamlePrint("I5","J5");
                                            StdDraw.show();
                                            try{
                                                Thread.sleep(300);
                                            }catch(InterruptedException e){}
                                            return ; 
                                        }
                                        else{
                                            continue ; 
                                        }
                                    }
                                }
                                else if(((_x2>=790&&_x2<=842)&&(_y2>=242&&_y2<=444))&&(K5.numberOfX < 2)){   // K5'e git.
                                    
                                    for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                                        if(Board.hamleler.get(i)==2){
                                            Board.hamleler.remove(i);
                                            I5.numberOfY -=1 ; 
                                            K5.numberOfY +=1 ; 
                                            if(K5.numberOfX ==1){
                                                K5.numberOfX = 0 ; 
                                                E3.numberOfX += 1 ; 
                                            }
                                            StdDraw.setPenColor(StdDraw.WHITE) ; 
                                            StdDraw.filledRectangle(650,110,30,50);
                                            StdDraw.setPenColor();
                                            StdDraw.show();
                                            StdDraw.text(650,110,"I5 -> K5");
                                            Files.hamlePrint("I5","K5");
                                            StdDraw.show();
                                            try{
                                                Thread.sleep(300);
                                            }catch(InterruptedException e){}
                                            return ; 
                                        }
                                        else{
                                            continue ; 
                                        }
                                    }
                                }
                                else if(((_x2>=852&&_x2<=910)&&(_y2>=242&&_y2<=444))&&(L5.numberOfX < 2)){   // L5'e git
                                    for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                                        if(Board.hamleler.get(i)==3){
                                            Board.hamleler.remove(i);
                                            I5.numberOfY -=1 ; 
                                            L5.numberOfY +=1 ; 
                                            if(L5.numberOfX ==1){
                                                L5.numberOfX = 0 ; 
                                                E3.numberOfX += 1 ; 
                                            }
                                            StdDraw.setPenColor(StdDraw.WHITE) ; 
                                            StdDraw.filledRectangle(650,110,30,50);
                                            StdDraw.setPenColor();
                                            StdDraw.show();
                                            StdDraw.text(650,110,"I5 -> L5");
                                            Files.hamlePrint("I5","L5");
                                            StdDraw.show();
                                            try{
                                                Thread.sleep(300);
                                            }catch(InterruptedException e){}
                                            return ; 
                                        }
                                        else{
                                            continue ; 
                                        }
                                    }
                                }
                            }
                        }
                   }
                }
                else if(((_x>=722 && _x<=780)&&(_y>=242 && _y<=444)) && J5.numberOfY > 0){      // from J5
                    if(checkJ5 == false){
                        StdDraw.setPenColor(StdDraw.RED) ; 
                        StdDraw.text(650,110,"No valid move from J5.");
                        StdDraw.show();
                        StdDraw.setPenColor();
                        try{
                            Thread.sleep(300);
                        }catch(InterruptedException e){}
                        StdDraw.setPenColor(StdDraw.WHITE);
                        StdDraw.filledRectangle(650, 110, 90,50);
                        StdDraw.setPenColor();
                        StdDraw.show();
                    }
                   else{
                       StdDraw.text(650,110,"J5 -> ? ");
                        StdDraw.show();
                        double _x2 ; 
                        double _y2 ; 
                        while(true){                                                    // destination loop.
                            if(StdDraw.isMousePressed()){
                                _x2 = StdDraw.mouseX() ; 
                                _y2 = StdDraw.mouseY() ; 
                                if(((_x2>=790&&_x2<=842)&&(_y2>=242&&_y2<=444))&&(K5.numberOfX < 2)){     // K5'e git.
                                    for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                                        if(Board.hamleler.get(i)==1){
                                            Board.hamleler.remove(i);
                                            J5.numberOfY -=1 ; 
                                            K5.numberOfY +=1 ; 
                                            if(K5.numberOfX ==1){
                                                K5.numberOfX = 0 ; 
                                                E3.numberOfX += 1 ; 
                                            }
                                            StdDraw.setPenColor(StdDraw.WHITE) ; 
                                            StdDraw.filledRectangle(650,110,30,50);
                                            StdDraw.setPenColor();
                                            StdDraw.show();
                                            StdDraw.text(650,110,"J5 -> K5");
                                            Files.hamlePrint("J5","K5");
                                            StdDraw.show();
                                            try{
                                                Thread.sleep(300);
                                            }catch(InterruptedException e){}
                                            return ; 
                                        }
                                        else{
                                            continue ; 
                                        }
                                    }
                                }
                                else if(((_x2>=852&&_x2<=910)&&(_y2>=242&&_y2<=444))&&(L5.numberOfX < 2)){   // L5'e git.
                                    
                                    for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                                        if(Board.hamleler.get(i)==2){
                                            Board.hamleler.remove(i);
                                            J5.numberOfY -=1 ; 
                                            L5.numberOfY +=1 ; 
                                            if(L5.numberOfX ==1){
                                                L5.numberOfX = 0 ; 
                                                E3.numberOfX += 1 ; 
                                            }
                                            StdDraw.setPenColor(StdDraw.WHITE) ; 
                                            StdDraw.filledRectangle(650,110,30,50);
                                            StdDraw.setPenColor();
                                            StdDraw.show();
                                            StdDraw.text(650,110,"J5 -> L5");
                                            Files.hamlePrint("J5","L5");
                                            StdDraw.show();
                                            try{
                                                Thread.sleep(300);
                                            }catch(InterruptedException e){}
                                            return ; 
                                        }
                                        else{
                                            continue ; 
                                        }
                                    }
                                }
                            }
                        }
                   }
                }
                else if(((_x>=790 && _x<=842)&&(_y>=242 && _y<=444)) && K5.numberOfY > 0){        // from K5
                    if(checkK5 == false){
                        StdDraw.setPenColor(StdDraw.RED) ; 
                        StdDraw.text(650,110,"No valid move from K5.");
                        StdDraw.show();
                        StdDraw.setPenColor();
                        try{
                            Thread.sleep(300);
                        }catch(InterruptedException e){}
                        StdDraw.setPenColor(StdDraw.WHITE);
                        StdDraw.filledRectangle(650, 110, 90,50);
                        StdDraw.setPenColor();
                        StdDraw.show();
                    }
                   else{
                       StdDraw.text(650,110,"K5 -> ? ");
                        StdDraw.show();
                        double _x2 ; 
                        double _y2 ; 
                        while(true){                                                    // destination loop.
                            if(StdDraw.isMousePressed()){
                                _x2 = StdDraw.mouseX() ; 
                                _y2 = StdDraw.mouseY() ; 
                                if(((_x2>=852&&_x2<=910)&&(_y2>=242&&_y2<=444))&&(L5.numberOfX < 2)){     // L5'e git.
                                    for(int i = 0 ; i < Board.hamleler.size() ; ++i){
                                        if(Board.hamleler.get(i)==1){
                                            Board.hamleler.remove(i);
                                            K5.numberOfY -=1 ; 
                                            L5.numberOfY +=1 ; 
                                            if(L5.numberOfX ==1){
                                                L5.numberOfX = 0 ; 
                                                E3.numberOfX += 1 ; 
                                            }
                                            StdDraw.setPenColor(StdDraw.WHITE) ; 
                                            StdDraw.filledRectangle(650,110,30,50);
                                            StdDraw.setPenColor();
                                            StdDraw.show();
                                            StdDraw.text(650,110,"K5 -> L5");
                                            Files.hamlePrint("K5","L5");
                                            StdDraw.show();
                                            try{
                                                Thread.sleep(300);
                                            }catch(InterruptedException e){}
                                            return ; 
                                        }
                                        else{
                                            continue ; 
                                        }
                                    }
                                }
                            }
                        }
                   }
                }

            }
        }
        
    }
    
    static boolean hasGameFinished(){
        if(Board.user_info=='x'){
            if(TopX.numberOfX == 15){
                Files.makeFinished(true);
                return true; 
            }
           
        }
        else{
           if(TopY.numberOfY ==15){
               Files.makeFinished(true);
               return true ; 
           } 
        }
        return false ; 
    }
        
}     