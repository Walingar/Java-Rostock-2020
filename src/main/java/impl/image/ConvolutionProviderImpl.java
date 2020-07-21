package impl.image;

import api.image.ConvolutionProvider;
import api.image.ImageConverter;

import java.awt.*;

public class ConvolutionProviderImpl implements ConvolutionProvider {
    @Override
    public Color[][] apply(Color[][] image, double[][] kernel) {
        int imageHeight = image.length;
        int imageWidth = image[0].length;

        int kernelHeight = kernel.length;
        int kernelWidth = kernel[0].length;

        int kernelRadiusRow = kernelHeight / 2;
        int kernelRadiusColumn = kernelWidth / 2;

        Color[][] output = new Color[imageHeight][imageWidth];

        for (int imageRow = 0; imageRow < imageHeight; imageRow++) {
            for (int imageColumn = 0; imageColumn < imageWidth; imageColumn++) {

                int redOutput = 0;
                int greenOutput = 0;
                int blueOutput = 0;

                for (int kernelRow = 0; kernelRow < kernelHeight; kernelRow++) {
                    for (int kernelColumn = 0; kernelColumn < kernelWidth; kernelColumn++) {

                        // shifts for navigation through kernel based on the selected pixel
                        int rowShift = kernelRow - kernelRadiusRow;
                        int columnShift = kernelColumn - kernelRadiusColumn;

                        int rowDisplacement = imageRow + rowShift;
                        int columnDisplacement = imageColumn + columnShift;

                        // if kernel coordinates based on the selected pixel fits is inside the image
                        if ((rowDisplacement > -1) && (rowDisplacement < imageHeight) &&
                                (columnDisplacement > -1) && (columnDisplacement < imageWidth)
                        ) {
                            Color imagePixel = image[rowDisplacement][columnDisplacement];
                            int redTemp = imagePixel.getRed();
                            int greenTemp = imagePixel.getGreen();
                            int blueTemp = imagePixel.getBlue();

                            redTemp = (int) (redTemp * kernel[kernelRow][kernelColumn]);
                            greenTemp = (int) (greenTemp * kernel[kernelRow][kernelColumn]);
                            blueTemp = (int) (blueTemp * kernel[kernelRow][kernelColumn]);

                            redOutput += redTemp;
                            greenOutput += greenTemp;
                            blueOutput += blueTemp;
                        }
                    }
                }
                output[imageRow][imageColumn] = new Color(redOutput, greenOutput, blueOutput, 255);
            }
        }
        return output;
    }

}

