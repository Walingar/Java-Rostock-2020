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
                        if (isInRange(imageRowCorrection, imageSizeRow, imageColumnCorrection, imageSizeColumn)) {
                            Color pixel = image[imageRowCorrection][imageColumnCorrection];
                            redNew += (int) (pixel.getRed() * kernel[kernelRow][kernelColumn]);
                            greenNew += (int) (pixel.getGreen() * kernel[kernelRow][kernelColumn]);
                            blueNew += (int) (pixel.getBlue() * kernel[kernelRow][kernelColumn]);
                        }
                    }
                }
                output[imageRow][imageColumn] = new Color(redNew, greenNew, blueNew, 255);
            }
        }
        return output;
    }

    private boolean isInRange(int imageRowCorrection, int imageSizeRow, int imageColumnCorrection, int imageSizeColumn) {
        return (imageRowCorrection > -1) && (imageRowCorrection < imageSizeRow) && (imageColumnCorrection > -1) && (imageColumnCorrection < imageSizeColumn);
    }
}