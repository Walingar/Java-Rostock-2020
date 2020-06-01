package impl.image;

import api.image.ConvolutionProvider;
import api.image.ImageConverter;

import java.awt.*;

public class ConvolutionProviderImpl implements ConvolutionProvider {
    @Override
    public Color[][] apply(Color[][] image, double[][] kernel) {
        // TODO: write implementation here
        ImageConverter converter = new ImageConverterImpl(); // Convert Color
        int[][] temp = converter.convertToRgb(image); // new variable int[][] temp, convert from Color (r,g,b,a) to int

        int r = 0;
        int g = 0;
        int b = 0;
        int a = 0xFF;

        double[][] result = new double[temp.length][temp[0].length]; //result

        for(int i_r = 0; i_r < result.length; i_r++) { // do it for every pixel in result
            for(int j_r = 0; j_r < result[i_r].length; j_r++) {
                result[i_r][j_r] = 0;

                for (int i_k = 0; i_k < kernel.length; i_k++) {
                    for (int j_k = 0; j_k < kernel[i_k].length; j_k++) {
                        double help = 0;

                        int x = i_k - (int)Math.floor(kernel.length/2.0);
                        int y = j_k - (int)Math.floor(kernel[i_k].length/2.0);

                        if((i_r + x < 0) && (i_r + x >= result.length) && (j_r + y < 0) || (j_r + y >= result[i_r].length)){ // if core element out of image
                            help = ((b) + (g << 8) + (r << 16) + (a << 24)) * kernel[i_k][j_k]; // return default value
                        }
                        else{
                            help = (temp[i_r+x][j_r+y] * kernel[i_k][j_k]); // calculation of the new value of the pixel
                        }
                        result[i_r][j_r] = result[i_r][j_r] + Math.round(help);
                    }
                }
            }
        }

        for(int i = 0; i < temp.length; i++){
            for(int j = 0; j < temp[i].length; j++){
                temp[i][j] = (int)result[i][j]; // convert double back to int
            }
        }
        Color[][] result_color = converter.convertToColor(temp); // Converting back from int[][] to Color[][]
        return result_color;
    }

}

