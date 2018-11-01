/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utility;

import java.awt.Dimension;
import java.awt.Image;
import javax.swing.JFrame;
import java.awt.Toolkit;

/**
 *
 * @author Ziyin
 */
public class UiUtil {
    private UiUtil(){}
    

        public static void setFram(JFrame jf,String imageName){
        
        Toolkit tf = Toolkit.getDefaultToolkit();
        Image i = tf.getImage("src\\resource\\"+imageName);
        jf.setIconImage(i);
    
    }
    
    
    public static void setFrameC(JFrame jf ){
    Toolkit tk = Toolkit.getDefaultToolkit();
    
    Dimension d = tk.getScreenSize();
    double screenWidth = d.width;
    double screenHeight = d.height;
    
    int frameW = jf.getWidth();
    int frameH = jf.getHeight();
    
    
    int width = (int)(screenWidth-frameW)/2;
    int height=(int)(screenHeight-frameH)/2;
    
    jf.setLocation(width, height);
    
    }
}
