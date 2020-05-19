package impl.image;

import api.image.ImageConverter;

import javax.print.attribute.standard.PresentationDirection;
import java.awt.*;

public class ImageConverterImpl implements ImageConverter {
    @Override
    public Color[][] convertToColor(int[][] image) {
        // TODO: write implementation here
        for(i=0, i<image.length, i++){ // do for every
            for(j=0, j<image[i].length, j++){ // single pixel of image
                // int consists of 4 bytes... split up and save seperately
                image[i][j] = {
                        r: image[i][j] >> 24 & 0xFF,
                        g: image[i][j] >> 16 & 0xFF,
                        b: image[i][j] >> 8 & 0xFF,
                        a: image[i][j] & 0xFF
                };
            }
        }
        return null;
    }

    @Override
    public int[][] convertToRgb(Color[][] image) {
        // TODO: write implementation here
        for(i=0, i<image.length, i++){ // do for every
            for(j=0, j<image[i].length, j++){ // single pixel of array
                // int represented as 4 distinct bytes... re-combine
                var r = image[i][j].getRed() & 0xFF;
                var g = image[i][j].getGreen() & 0xFF;
                var b = image[i][j].getBlue() & 0xFF;
                var a = image[i][j].getAlpha() & 0xFF;
                image[i][j] = (r << 24) + (g << 16) + (b << 8) + (a);
            }
        }
        return null;
    }
}
