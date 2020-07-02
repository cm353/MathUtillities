package NumbersUtilities;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Mandelbrot {

    static int mandel(double x, double y) {
        double r = x;
        double s = y;
        double rAlt;
        double sAlt;
        int iteration = 0;

        do {
            rAlt = r;
            sAlt = s;
            r = rAlt * rAlt - sAlt * sAlt + x;
            s = 2.0 * rAlt * sAlt + y;
            iteration++;
        } while ((r * r + s * s) < 4 && iteration < 255);
        return iteration;
    }

    public static void drawMandelbrot(int x, int y){
        int greyValue=0;
        BufferedImage img = new BufferedImage(x+x,y+y, BufferedImage.TYPE_BYTE_GRAY );

        for (int i=-x; i<x; i++){
            for(int j=-y;j<y;j++){
                greyValue = 255-Mandelbrot.mandel(i/(double)x*2,j/(double)y);
//                System.out.println(i+x + " " + (j+y));
                img.setRGB(i+x,j+y, new Color(greyValue, greyValue, greyValue).getRGB());
            }
            System.out.println("finished: " + ((float)i+x)/(2*x)*100+"%");
        }
        try {
            File outputfile = new File( "resources","saved.jpg");
            ImageIO.write(img, "jpg", outputfile);
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        int imageHeight = 5000;
        Mandelbrot.drawMandelbrot(2*imageHeight,imageHeight);
    }

}
