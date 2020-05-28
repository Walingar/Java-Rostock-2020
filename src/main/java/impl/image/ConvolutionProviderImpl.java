package impl.image;

import api.image.ConvolutionProvider;
import api.image.ImageConverter;

import java.awt.*;

public class ConvolutionProviderImpl implements ConvolutionProvider {
    @Override
    public Color[][] apply(Color[][] image, double[][] kernel) {
        // TODO: write implementation here
        ImageConverter converter = new ImageConverterImpl(); // Convert Color
        int[][] temp = converter.convertToRgb(image); // To int

        int r = 0;
        int g = 0;
        int b = 0;
        int a = 255;
        int color_default = (b) + (g << 8) + (r << 16) + (a << 24); // default color for out of reach core

        double[][] output = new double[temp.length][temp[0].length];
        for(int i_o = 0; i_o < output.length; i_o++) { // image array
            for(int j_o = 0; j_o < output[i_o].length; j_o++) {
                output[i_o][j_o] = 0;

                for (int i_k = 0; i_k < kernel.length; i_k++) { // kernel array
                    for (int j_k = 0; j_k < kernel[i_k].length; j_k++) {
                        double help = 0;
                        int x = i_k - (int)Math.floor(kernel.length/2.0); // navigate in image array due to kernel array (can be -1, 0, +1)
                        int y = j_k - (int)Math.floor(kernel[i_k].length/2.0); // navigate in image array due to kernel array (can be -1, 0, +1)
                        if( (i_o + x > -1) && (i_o + x < output.length) && (j_o + y > -1) && (j_o + y < output[i_o].length) ){ // core in image range
                            help = (temp[i_o+x][j_o+y] * kernel[i_k][j_k]); // calculate as usual
                        } else{ // core out of image range
                            help = color_default * kernel[i_k][j_k];
                        }
                        output[i_o][j_o] = output[i_o][j_o] + Math.round(help); // round after multiplication
                    }
                }

            }
        }

        for(int i = 0; i < temp.length; i++){ // convert double back to int to enable re-transforming to Color
            for(int j = 0; j < temp[i].length; j++){
                temp[i][j] = (int)output[i][j];
            }
        }
        Color[][] output_color = converter.convertToColor(temp);
        return output_color;
    }
}
