package impl.image;

import api.image.ImageConverter;

import java.awt.*;

public class ImageConverterImpl implements ImageConverter {

    @Override
    public Color[][] convertToColor(int[][] image) {
        Color[][] output = new Color[image.length][image[0].length];

        for (int rowCounter = 0; rowCounter < image.length; rowCounter++) {
            for (int columnCounter = 0; columnCounter < image[rowCounter].length; columnCounter++) {
                int blue = image[rowCounter][columnCounter] & 0xFF;
                int green = image[rowCounter][columnCounter] >> 8 & 0xFF;
                int red = image[rowCounter][columnCounter] >> 16 & 0xFF;
                int alpha = image[rowCounter][columnCounter] >> 24 & 0xFF;
                output[rowCounter][columnCounter] = new Color(red, green, blue, alpha);
            }
        }
        return output;
    }

    @Override
    public int[][] convertToRgb(Color[][] image) {
        int[][] output = new int[image.length][image[0].length];

        for (int rowCounter = 0; rowCounter < image.length; rowCounter++) {
            for (int columnCounter = 0; columnCounter < image[rowCounter].length; columnCounter++) {
                int red = image[rowCounter][columnCounter].getRed() & 0xFF;
                int green = image[rowCounter][columnCounter].getGreen() & 0xFF;
                int blue = image[rowCounter][columnCounter].getBlue() & 0xFF;
                int alpha = image[rowCounter][columnCounter].getAlpha() & 0xFF;
                output[rowCounter][columnCounter] = (blue) + (green << 8) + (red << 16) + (alpha << 24);

                /* second idea
                output[rowCounter][columnCounter] = image[rowCounter][columnCounter].getRGB();
                */

                /* third idea
                int red = image[rowCounter][columnCounter].getRed(); // int between 0-255
                red = Integer.toBinaryString(red);
                int green = image[rowCounter][columnCounter].getGreen(); // int between 0-255
                green = Integer.toBinaryString(green);
                int blue = image[rowCounter][columnCounter].getBlue(); // int between 0-255
                blue = Integer.toBinaryString(blue);
                int alpha = image[rowCounter][columnCounter].getAlpha(); // int between 0-255
                alpha = Integer.toBinaryString(alpha);
                output[rowCounter][columnCounter] = alpha + red + green + blue;
                */
            }
        }
        return output;
    }


}

