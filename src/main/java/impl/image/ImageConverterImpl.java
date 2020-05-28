package impl.image;

import api.image.ImageConverter;

import java.awt.*;

public class ImageConverterImpl implements ImageConverter {
    @Override
    public Color[][] convertToColor(int[][] image) {
        //TODO
        Color[][] temp = new Color[image.length][image[0].length]; // new temp array for Color types
        for(int i=0; i<image.length; i++) { // do for every
            for (int j = 0; j < image[i].length; j++) { // single pixel of image
                // int consists of 4 bytes... split up and save seperately in same field
                int b = image[i][j] & 0xFF;
                int g = image[i][j] >> 8 & 0xFF;
                int r = image[i][j] >> 16 & 0xFF;
                int a = image[i][j] >> 24 & 0xFF;
                temp[i][j] = new Color(r, g, b, a);
            }
        }
        return temp;
    }

    @Override
    public int[][] convertToRgb(Color[][] image) {
        //TODO
        int[][] temp = new int[image.length][image[0].length];
        for(int i=0; i<image.length; i++){ // do for every
            for(int j=0; j<image[i].length; j++){ // single pixel of image in same field
                // int represented as 4 distinct bytes... re-combine
                int r = image[i][j].getRed() & 0xFF;
                int g = image[i][j].getGreen() & 0xFF;
                int b = image[i][j].getBlue() & 0xFF;
                int a = image[i][j].getAlpha() & 0xFF;
                temp[i][j] = (b) + (g << 8) + (r << 16) + (a << 24);
            }
        }
        return temp;
    }

}

