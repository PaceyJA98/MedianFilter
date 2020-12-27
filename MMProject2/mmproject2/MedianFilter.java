//Pacey Amos
//Multimedia Project 2
//------------------------------------------------------------
//Median Filter to filter out "Salt and Pepper Noise" by 
//storing pixel values in an array, averaging neighboring 
//pixels values, and storing them in a new array for an 
//output image to be created without noise
//------------------------------------------------------------
package mmproject2;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Arrays;
import javax.imageio.*;

class MedianFilter{
    public static void main(String[] array)throws Throwable
    {
    	//Save Input file "Lena_noise.jpg" to new File inputfile
        File inputfile =new File("Lena_noise.jpg");
        
        //New 3x3 color array
        Color[] pixel=new Color[9];
        int[] R=new int[9];
        int[] B=new int[9];
        int[] G=new int[9];
        
        //Declare output file
        File outputfile =new File("outputfile.jpg");
        
        //read given input file and save to img
        BufferedImage img=ImageIO.read(inputfile);
        
        //Sort through the 3x3 array
        for(int i=1;i<img.getWidth()-1;i++)
            for(int j=1;j<img.getHeight()-1;j++)
            {
               pixel[0]=new Color(img.getRGB(i-1,j-1));
               pixel[1]=new Color(img.getRGB(i-1,j));
               pixel[2]=new Color(img.getRGB(i-1,j+1));
               pixel[3]=new Color(img.getRGB(i,j+1));
               pixel[4]=new Color(img.getRGB(i+1,j+1));
               pixel[5]=new Color(img.getRGB(i+1,j));
               pixel[6]=new Color(img.getRGB(i+1,j-1));
               pixel[7]=new Color(img.getRGB(i,j-1));
               pixel[8]=new Color(img.getRGB(i,j));
               
               //Three RBG rows
               for(int k=0;k<9;k++){
                   R[k]=pixel[k].getRed();
                   B[k]=pixel[k].getBlue();
                   G[k]=pixel[k].getGreen();
               }
               
               Arrays.sort(R);
               Arrays.sort(G);
               Arrays.sort(B);
               
               //set new sorted RGB colors to image
               img.setRGB(i,j,new Color(R[4],B[4],G[4]).getRGB());
            }
        //write new image outputfile
        ImageIO.write(img,"jpg",outputfile);
    }
}