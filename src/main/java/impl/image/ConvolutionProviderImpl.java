package impl.image;

import api.image.ConvolutionProvider;
import api.image.ImageConverter;

import java.awt.*;

public class ConvolutionProviderImpl implements ConvolutionProvider {
    @Override
    public Color[][] apply(Color[][] image, double[][] kernel) {
        int kernelSizeRow = kernel.length;
        int kernelSizeColumn = kernel[0].length;

        int kernelRadiusRow = kernelSizeRow / 2;
        int kernelRadiusColumn = kernelSizeColumn / 2;

        int imageSizeRow = image.length;
        int imageSizeColumn = image[0].length;

        Color[][] output = new Color[imageSizeRow][imageSizeColumn];

        for (int imageRow = 0; imageRow < imageSizeRow; imageRow++) {
            for (int imageColumn = 0; imageColumn < imageSizeColumn; imageColumn++) {

                int redOutput = 0;
                int greenOutput = 0;
                int blueOutput=0;

                for (int kernelRow = 0; kernelRow < kernelSizeRow; kernelRow++) {
                    for (int kernelColumn = 0; kernelColumn < kernelSizeColumn; kernelColumn++) {

                        // shifts for navigation through kernel based on the selected pixel
                        int rowShift = kernelRow - kernelRadiusRow;
                        int columnShift = kernelColumn - kernelRadiusColumn;

                        int rowDisplacement = imageRow + rowShift;
                        int columnDisplacement = imageColumn + columnShift;

                        // if kernelcoordinates based on the selected pixel fits is inside the image
                        if ((rowDisplacement > -1) && (rowDisplacement < image.length) && (columnDisplacement > -1) && (columnDisplacement < image[imageRow].length)) {
                            int redTemp = image[rowDisplacement][columnDisplacement].getRed();
                            int greenTemp = image[rowDisplacement][columnDisplacement].getGreen();
                            int blueTemp = image[rowDisplacement][columnDisplacement].getBlue();

                            redTemp = (int)Math.floor(redTemp * kernel[kernelRow][kernelColumn]);
                            greenTemp = (int)Math.floor(greenTemp * kernel[kernelRow][kernelColumn]);
                            blueTemp = (int)Math.floor(blueTemp * kernel[kernelRow][kernelColumn]);

                            redOutput = redOutput + redTemp;
                            greenOutput = greenOutput + greenTemp;
                            blueOutput = blueOutput + blueTemp;
                        }
                    }
                }
                output[imageRow][imageColumn] = new Color(redOutput, greenOutput, blueOutput, 255);
            }
        }
        return output;
    }

}

