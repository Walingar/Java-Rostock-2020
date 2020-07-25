package impl.image;

import api.image.ImageConverter;

import java.awt.*;

public class ImageConverterImpl implements ImageConverter {

    @Override
    public Color[][] convertToColor(int[][] image) {
        Color[][] output = new Color[image.length][];

        for (int rowCounter = 0; rowCounter < image.length; rowCounter++) {
            output[rowCounter] = new Color[image[rowCounter].length];
            for (int columnCounter = 0; columnCounter < image[rowCounter].length; columnCounter++) {
                output[rowCounter][columnCounter] = new Color(image[rowCounter][columnCounter]);
            }
        }
        return output;
    }

    @Override
    public int[][] convertToRgb(Color[][] image) {
        int[][] output = new int[image.length][];

        for (int rowCounter = 0; rowCounter < image.length; rowCounter++) {
            output[rowCounter] = new int[image[rowCounter].length];
            for (int columnCounter = 0; columnCounter < image[rowCounter].length; columnCounter++) {
                output[rowCounter][columnCounter] = image[rowCounter][columnCounter].getRGB();
            }
        }
        return output;
    }
}

