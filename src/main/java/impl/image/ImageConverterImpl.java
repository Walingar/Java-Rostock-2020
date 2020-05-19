package impl.image;

import api.image.ImageConverter;

import javax.print.attribute.standard.PresentationDirection;
import java.awt.*;

public class ImageConverterImpl implements ImageConverter {
    @Override
    public Color[][] convertToColor(int[][] image) {
        // TODO: write implementation here
        for(int i=0; i<image.length; i++){ // do for every
            for(int j=0; j<image[i].length; j++){ // single pixel of image
                // int consists of 4 bytes... split up and save seperately in same field
                int r = image[i][j] >> 24 & 0xFF;
                int g = image[i][j] >> 16 & 0xFF;
                int b = image[i][j] >> 8 & 0xFF;
                int a = image[i][j] & 0xFF;
                image[i][j] = Color(r,g,b,a);
            }
        }
        return null;
    }

    @Override
    public int[][] convertToRgb(Color[][] image) {
        // TODO: write implementation here
        for(int i=0; i<image.length; i++){ // do for every
            for(int j=0; j<image[i].length; j++){ // single pixel of image in same field
                // int represented as 4 distinct bytes... re-combine
                int r = image[i][j].getRed() & 0xFF;
                int g = image[i][j].getGreen() & 0xFF;
                int b = image[i][j].getBlue() & 0xFF;
                int a = image[i][j].getAlpha() & 0xFF;
                int rgba = (r << 24) + (g << 16) + (b << 8) + (a);
                image[i][j] = (int) rgba;
            }
        }
        return null;
    }
}
