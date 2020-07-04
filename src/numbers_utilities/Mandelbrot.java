package numbers_utilities;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 *  Calculates and draws an image representation of the mandelbrot set
 *  number of iterations is represented as inverted greyscale
 *
 */
public class Mandelbrot {

    /**
     *  calculates whether the point passed belongs to the mandelbrot set
     *
     *  @param x real value of the current point on complex number plane
     *  @param y imaginary value of the current point on complex number plane
     *  @return number of iterations necessary to decide whether the point belongs to the mandelbrot set
     */

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

    /**
     *  draws an image representation of the mandelbrot set using a the method provided
     *  to decide whether a point belongs to the mandelbrot set or not
     *
     *  @param x width of the resulting picture in pixel
     *  @param y height of the resulting picture in pixel
     */
    public static void drawMandelbrot(int x, int y){
        int greyValue=0;
        BufferedImage img = new BufferedImage(2*x,2*y, BufferedImage.TYPE_BYTE_GRAY );
        for (int i=-x; i<x; i++){
            for(int j=-y;j<y;j++){
                // (-1)*... to take into account that (0, 0) is the upper left corner of BufferedImage
                // and not lower left corner as expected in cartesian coordinates
                greyValue = 255-Mandelbrot.mandel(i/(double)x*2,(-1)*j/(double)y);
                img.setRGB(i+x,j+y, new Color(greyValue, greyValue, greyValue).getRGB());
            }
            System.out.println("finished: " + ((float)i+x)/(2*x)*100+"%");
        }
        try {
            File outputFile = new File( "resources","mandelbrot.jpg");
            ImageIO.write(img, "jpg", outputFile);
        } catch (IOException e){
            e.printStackTrace();
        } finally {
            System.out.print("Program run finished");
        }
    }

    /**
     *  controls the size of the resulting image representation of the mandelbrot set
     *
     *  @param args height of the resulting picture in pixel; if zero args are passed, default height will be used
     *  @throws IllegalArgumentException if to much args are passed or args are not an integer literal or designated height is smaller than 2
     */
    public static void main(String[] args) throws IllegalArgumentException {
        if(args.length>1)
            throw new IllegalArgumentException("only one or zero arguments allowed");
        if(args.length==1 && Integer.parseInt(args[0])<2)
            throw new IllegalArgumentException("image size must be bigger than 1");
        int imageHeight;
        if(args.length==1) {
            imageHeight = Integer.parseInt(args[0])/2;
            System.out.println("Image will be of size;" + 4*imageHeight + "x"+2*imageHeight);
        } else {
            imageHeight = 10000 / 2;
            System.out.println("Default image size of;" + 4*imageHeight + "x"+2*imageHeight + " selected");
        }
        Mandelbrot.drawMandelbrot(2*imageHeight,imageHeight);
    }

}
