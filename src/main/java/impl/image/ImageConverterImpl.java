package impl.image;

import api.image.ImageConverter;

import java.awt.*;

public class ImageConverterImpl implements ImageConverter {

    @Override
    public Color[][] convertToColor(int[][] image) {
        //TODO
        Color[][] coloritem = new Color[image.length][image[0].length]; // new variable of Color-Class

        for(int i=0; i<image.length; i++) { // do it for every line of pixels in the image
            for (int j = 0; j < image[i].length; j++) { // do it for every column of pixels in the image
                int b = image[i][j] & 0xFF;
                int g = image[i][j] >> 8 & 0xFF;
                int r = image[i][j] >> 16 & 0xFF;
                int a = image[i][j] >> 24 & 0xFF;
                coloritem[i][j] = new Color(r, g, b, a);
            }
        }
        return coloritem;
    }

    @Override
    public int[][] convertToRgb(Color[][] image) {
        //TODO
        int[][] colorarray = new int[image.length][image[0].length]; // new variable of int[][]

        for(int i=0; i<image.length; i++){ // do it for every line of pixels in the image
            for(int j=0; j<image[i].length; j++){ // do it for every column of pixels in the image
                int r = image[i][j].getRed() & 0xFF; // & 0xFF(11111111) nessesary to cut of bit higher than 8?
                int g = image[i][j].getGreen() & 0xFF;
                int b = image[i][j].getBlue() & 0xFF;
                int a = image[i][j].getAlpha() & 0xFF;
                colorarray[i][j] = (b) + (g << 8) + (r << 16) + (a << 24);
            }
        }
        return colorarray;
    }

    /* second idea
    public int[][] convertToRgb(Color[][] image) {

        int[][] colorarray = new int[image.length][image[0].length]; // new variable of int[][]

        for(int i=0; i<image.length; i++){ // do for every
            for(int j=0; j<image[i].length; j++){ // single pixel of image in same field
                colorarray[i][j] = image[i][j].getRGB()
            }
        }
        return colorarray;
    }
    */

    /* third idea
    public int[][] convertToRgb(Color[][] image) {

        int[][] colorarray = new int[image.length][image[0].length]; // new variable of int[][]

        for(int i=0; i<image.length; i++){ // do it for every line of pixels in the image
            for(int j=0; j<image[i].length; j++){ // do it for every column of pixels in the image
                int r = image[i][j].getRed(); // int between 0-255
                Integer.toBinaryString(r);
                int g = image[i][j].getGreen(); // int between 0-255
                Integer.toBinaryString(g);
                int b = image[i][j].getBlue(); // int between 0-255
                Integer.toBinaryString(b);
                int a = image[i][j].getAlpha(); // int between 0-255
                Integer.toBinaryString(a);
                colorarray[i][j] = a + r + g + b; // (b) + (g << 8) + (r << 16) + (a << 24)
            }
        }
        return colorarray;
    }
    */

}

