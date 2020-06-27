package impl.image;

import api.image.ConvolutionProvider;
import api.image.ImageConverter;

import java.awt.*;

public class ConvolutionProviderImpl implements ConvolutionProvider {
    @Override
    public Color[][] apply(Color[][] image, double[][] kernel) {

        int kernelSizeRow = kernel.length;
        int kernelRadiusRow = kernelSizeRow / 2;
        int kernelSizeColumn = kernel[0].length;
        int kernelRadiusColumn = kernelSizeColumn / 2;
        int imageSizeRow = image.length;
        int imageSizeColumn = image[0].length;
        Color[][] output = new Color[imageSizeRow][imageSizeColumn];

        for (int imageRow = 0; imageRow < imageSizeRow; imageRow++) {
            for (int imageColumn = 0; imageColumn < imageSizeColumn; imageColumn++) {

                int redNew = 0;
                int greenNew = 0;
                int blueNew = 0;

                for (int kernelRow = 0; kernelRow < kernelSizeRow; kernelRow++) {
                    for (int kernelColumn = 0; kernelColumn < kernelSizeColumn; kernelColumn++) {

                        int rowNavigator = kernelRow - kernelRadiusRow; // can be -1, 0, +1 for 3x3 kernel
                        int columnNavigator = kernelColumn - kernelRadiusColumn; // can be -1, 0, +1 for 3x3 kernel

                        // if core in image range ... else do nothing, as * with 0 is always 0
                        int imageRowCorrection = imageRow + rowNavigator;
                        int imageColumnCorrection = imageColumn + columnNavigator;
                        if ((imageRowCorrection > -1) && (imageRowCorrection < output.length) && (imageColumnCorrection > -1) && (imageColumnCorrection < output[imageRow].length)) {
                            int redTemp = image[imageRow + rowNavigator][imageColumn + columnNavigator].getRed();
                            int greenTemp = image[imageRow + rowNavigator][imageColumn + columnNavigator].getGreen();
                            int blueTemp = image[imageRow + rowNavigator][imageColumn + columnNavigator].getBlue();
                            redTemp = (int)(redTemp * kernel[kernelRow][kernelColumn]);
                            greenTemp = (int)(greenTemp * kernel[kernelRow][kernelColumn]);
                            blueTemp = (int)(blueTemp * kernel[kernelRow][kernelColumn]);
                            redNew += redTemp;
                            greenNew += greenTemp;
                            blueNew += blueTemp;
                        }
                    }
                }
                output[imageRow][imageColumn] = new Color(redNew, greenNew, blueNew, 255);
            }
        }
        return output;
    }
}
