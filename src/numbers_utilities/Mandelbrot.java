package numbers_utilities;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
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
     *  @param shiftRe Re shift if the image center
     *  @param shiftIm Im shift of the image center
     *  @param mag zoom factor
     */
    public static BufferedImage drawMandelbrot(int x, int y, double shiftRe, double shiftIm, double mag){
        int greyValue=0;
        BufferedImage img = new BufferedImage(2*x,2*y, BufferedImage.TYPE_BYTE_GRAY );
        for (int i=-x; i<x; i++){
            for(int j=-y;j<y;j++){
                // (-1)*... to take into account that (0, 0) is the upper left corner of BufferedImage
                // and not lower left corner as expected in cartesian coordinates
                greyValue = 255-Mandelbrot.mandel(i/((double)mag*x)*2+shiftRe,(-1)*j/((double)mag*y)+shiftIm);
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
            return img;
        }
    }

    /**
     *  controls the size of the resulting image representation of the mandelbrot set
     *
     *  @param args first: height of the resulting picture in pixel, second: Re shift of the image center, third: Im shift of the image center
     *              fourth: zoom
     *  @throws IllegalArgumentException inappropriate args are passed
     */
    public static void main(String[] args) throws IllegalArgumentException {
        if(args.length!=4)
            throw new IllegalArgumentException("java Mandelbrot.class image_height Re_shift Im_shift zoom");
        int imageHeight = Integer.parseInt(args[0])/2;;
        double shiftRe = Double.parseDouble(args[1]);
        double shiftIm= Double.parseDouble(args[2]);
        double mag = Double.parseDouble(args[3]);

        System.out.println("Image will be of size:" + 4*imageHeight + "x"+2*imageHeight);
        System.out.printf("Center of image will be Re=%f Im= %f \n", shiftRe,shiftIm);

        BufferedImage image = Mandelbrot.drawMandelbrot(2*imageHeight, imageHeight, shiftRe, shiftIm, mag);

        JLabel imageLabel = new JLabel(new ImageIcon(image));
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.add(imageLabel);
        frame.pack();
        frame.setVisible(true);;

    }

}
