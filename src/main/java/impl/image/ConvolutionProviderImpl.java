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

        double kernel_divisor = 0.0;
        for(int i = 0; i < kernel.length; i++){ // calculate kernel divisor
            for(int j = 0; j < kernel[i].length; j++){
                kernel_divisor = kernel_divisor + kernel[i][j];
            }
        }

        int kernel_navigator_row_i = (int)Math.floor(kernel.length/2.0); // navigator for rows in image
        int kernel_navigator_column_j = (int)Math.floor(kernel[0].length/2.0); // navigator for columns in image

        double[][] output = new double[temp.length][temp[0].length];
        for(int i_o = 0; i_o < output.length; i_o++) { // image array with i_o image rows
            for(int j_o = 0; j_o < output[i_o].length; j_o++) { // and j_o image columns
                output[i_o][j_o] = 0;

                for (int i_k = 0; i_k < kernel.length; i_k++) { // kernel array with i_k kernel rows
                    for (int j_k = 0; j_k < kernel[i_k].length; j_k++) { // and j_k kernel columns
                        double help;
                        int n_x = i_k - kernel_navigator_row_i; // navigate in image array due to kernel array (can be -1, 0, +1 for 3x3 kernel)
                        int n_y = j_k - kernel_navigator_column_j; // navigate in image array due to kernel array (can be -1, 0, +1 for 3x3 kernel)
                        if( (i_o + n_x > -1) && (i_o + n_x < output.length) && (j_o + n_y > -1) && (j_o + n_y < output[i_o].length) ){ // core in image range
                            help = (temp[i_o+n_x][j_o+n_y] * kernel[i_k][j_k]); // calculate as usual
                        } else{ // core out of image range
                            help = color_default * kernel[i_k][j_k]; // calculate with default color
                        }
                        output[i_o][j_o] = output[i_o][j_o] + Math.round(help); // round after multiplication
                    }
                }

                output[i_o][j_o] = Math.round(output[i_o][j_o] / kernel_divisor); // apply kernel divisor to sum of kernel fields
            }
        }

        for(int i = 0; i < temp.length; i++){ // convert double back to int to enable re-transforming to Color
            for(int j = 0; j < temp[i].length; j++){
                temp[i][j] = (int)output[i][j];
            }
        }

        return converter.convertToColor(temp);
    }
}
