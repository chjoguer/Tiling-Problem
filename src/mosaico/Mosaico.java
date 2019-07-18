/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mosaico;

import java.util.Random;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;

/**
 *
 * @author Christian Guerrero
 */
public class Mosaico {

    
    public int [][] mosaico;   // Mosaico
    public Rectangle[][] msquare;
    int pos;  // posicion actual para usar
    Pane _root;
    //int posEnUso;
    int size;
    public Mosaico(int size){
        this.size=size;
      mosaico = new int [size][size];
      pos = 1;
      _root = new Pane();
      //posEnUso =1;
   }

    public int[][] getMosaico() {
        return mosaico;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setMosaico(int[][] mosaico) {
        this.mosaico = mosaico;
    }

    public int getPos() {
        return pos;
    }

    public void setPos(int pos) {
        this.pos = pos;
    }
    
    
    /** @param topFil número de fila de la esquina superior izquierda del mosaico
     * @param topColumn número de columna de la esquina superior izquierda del mosaico
     * @param defectFil número de fila del cuadrado defectuoso
     * @param defectColumn número de columna del cuadrado defectuoso
     * @param tam longitud de un lado del mosaico */
   public void posMosaico(int topFil, int topColumn,
                         int defectFil, int defectColumn, int tam)
   {
      if (tam == 1){
          return ;
      } 
      
        int  posEnUso = pos++;
        int cuadranteTam = tam / 2;
      
      // cuadrante superior izquierdo
      if (defectFil < topFil + cuadranteTam &&
          defectColumn < topColumn + cuadranteTam)
         // existe un defecto en este cuadrante
         posMosaico(topFil, topColumn, defectFil, defectColumn,
                   cuadranteTam);
      else
      {// este cuadrante no tiene ningun defecto
         // coloque una parte del mosaico o azulejo en la  esquina
          // inferior derecha
         mosaico[topFil + cuadranteTam - 1][topColumn + cuadranteTam - 1]
              = posEnUso;
         //  msquare[topFil + cuadranteTam - 1][topColumn + cuadranteTam - 1].setStroke(Color.GREY);
           //           msquare[topFil + cuadranteTam - 1][topColumn + cuadranteTam - 1].setStroke(generarColor());

                     pintar(posEnUso);

         // el resto 
         posMosaico(topFil, topColumn, topFil + cuadranteTam - 1,
                   topColumn + cuadranteTam - 1, cuadranteTam);
      }
      
      // cuadrante superior derecho
      if (defectFil < topFil + cuadranteTam &&
          defectColumn >= topColumn + cuadranteTam)
         // existe un defecto en este cuadrante
         posMosaico(topFil, topColumn + cuadranteTam, defectFil,
                   defectColumn, cuadranteTam);
      else
      {// este cuadrante no tiene ningun defecto
          //coloque una parte del mosaico o azulejo en la  esquina
         // inferior izquierda
         mosaico[topFil + cuadranteTam - 1][topColumn + cuadranteTam]
              = posEnUso;
        // msquare[topFil + cuadranteTam - 1][topColumn + cuadranteTam].setStroke(Color.GREY);
        // msquare[topFil + cuadranteTam - 1][topColumn + cuadranteTam].setFill(generarColor());
          pintar(posEnUso);


         // el resto
         posMosaico(topFil, topColumn + cuadranteTam, topFil +
                cuadranteTam - 1, topColumn + cuadranteTam, cuadranteTam);
       }
      
      //cuadrante inferior izquierdo
      if (defectFil >= topFil + cuadranteTam &&
          defectColumn < topColumn + cuadranteTam)
         //existe un defecto en este cuadrante
         posMosaico(topFil + cuadranteTam, topColumn, defectFil,
                   defectColumn, cuadranteTam);
      else
      {// coloque una parte del mosaico o azulejo en la esquina 
          //superior derecha
         mosaico[topFil + cuadranteTam][topColumn + cuadranteTam - 1]
              = posEnUso;
       // msquare[topFil + cuadranteTam][topColumn + cuadranteTam - 1].setStroke(Color.GREY);
       // msquare[topFil + cuadranteTam][topColumn + cuadranteTam - 1].setFill(generarColor());
          pintar(posEnUso);

         

         // el resto
         posMosaico(topFil + cuadranteTam, topColumn, topFil + cuadranteTam,
                   topColumn + cuadranteTam - 1, cuadranteTam);
      }
      
      // cuadrante inferior derecho
      if (defectFil >= topFil + cuadranteTam && 
          defectColumn >= topColumn + cuadranteTam)
         // existe un defecto en este cuadrante
         posMosaico(topFil + cuadranteTam, topColumn + cuadranteTam,
                   defectFil, defectColumn, cuadranteTam);
      else
      {// coloque una parte del mosaico o azulejo en la esquina 
          //superior izquierda
         mosaico[topFil + cuadranteTam][topColumn + cuadranteTam]
              = posEnUso;
          pintar(posEnUso);
       // msquare[topFil + cuadranteTam][topColumn + cuadranteTam].setStroke(Color.GREY);
        //msquare[topFil + cuadranteTam][topColumn + cuadranteTam].setFill(generarColor());



         // el resto
         posMosaico(topFil + cuadranteTam, topColumn + cuadranteTam,
             topFil + cuadranteTam, topColumn + cuadranteTam, cuadranteTam);
       }
   }
   
    /** salida de pantalla del mosaico */
   /** @param tam longitud del mosaico */
   public void outputMosaico(int tam){
      for (int i = 0; i < tam; i++)
      {
         for (int j = 0; j < tam; j++)
            System.out.print(mosaico[i][j] + "\t");
         System.out.println();
      }
   }
    public void outputMosaico(){
      for (int i = 0; i < size; i++)
      {
         for (int j = 0; j < size; j++)
            System.out.print(mosaico[i][j] + "\t");
         System.out.println();
      }
   }
    public Pane getRoot() {
        return _root;
    }

    public void setRoot(Pane _root) {
        this._root = _root;
    }
    
    
    
    public void pintar(int position){
        Color c = generarColor();
       for(int i=0;i<mosaico.length;i++){
       for(int j=0;j<mosaico.length;j++){
       if(mosaico[i][j]==position){
            msquare[i][j].setStroke(Color.GREY);
            msquare[i][j].setFill(c);
       if(mosaico[i][j]==0){
            msquare[i][j].setStrokeWidth(10);
             msquare[i][j].setFill(Color.BLACK);
            msquare[i][j].setStrokeType(StrokeType.INSIDE);}
      
       }
       }
  }
    }
   public void matrizPane(){
       msquare = new Rectangle[size][size];
       for (int i = 0; i < mosaico.length; i++) {
           for(int j =0;j<mosaico.length;j++){
                 msquare[i][j]=((new Rectangle(10+512/mosaico.length*j,10+512/mosaico.length*i,512/mosaico.length,512/mosaico.length)));
           }
       }
       for (int i = 0; i < mosaico.length; i++) {
           for(int j =0; j<mosaico.length; j++){
               _root.getChildren().addAll(msquare[i][j]);
           }
       }  
   }
    public static Color generarColor(){
        Random rand = new Random();
        int r = rand.nextInt(256);
        int g = rand.nextInt(256);
        int b = rand.nextInt(256);
        
        Color color =  Color.rgb(r, g, b);
        return color ;
    }
   
    
    
}
