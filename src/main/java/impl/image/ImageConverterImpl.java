package impl.image;

import api.image.ImageConverter;

import javax.print.attribute.standard.PresentationDirection;
import java.awt.*;

public class ImageConverterImpl implements ImageConverter {
    @Override
    public Color[][] convertToColor(int[][] image) {

        int imageSizeRow = image.length;
        int imageSizeColumn = image[0].length;
        Color[][] result = new Color[imageSizeRow][imageSizeColumn];
        for (int imageRow = 0; imageRow < imageSizeRow; imageRow++) {
            for (int imageColumn = 0; imageColumn < imageSizeColumn; imageColumn++) {
                result[imageRow][imageColumn] = new Color(image[imageRow][imageColumn]);
            }
        }
        return result;
    }

    @Override
    public int[][] convertToRgb(Color[][] image) {

        int imageSizeRow = image.length;
        int imageSizeColumn = image[0].length;
        int[][] result = new int[imageSizeRow][imageSizeColumn];
        for (int imageRow = 0; imageRow < imageSizeRow; imageRow++) {
            for (int imageColumn = 0; imageColumn < imageSizeColumn; imageColumn++) {
                result[imageRow][imageColumn] = image[imageRow][imageColumn].getRGB();
            }
        }
        return result;
    }
}
