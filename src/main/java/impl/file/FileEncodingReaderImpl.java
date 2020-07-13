package impl.file;

import api.file.FileEncodingReader;

import java.io.*;
import java.nio.charset.Charset;

public class FileEncodingReaderImpl implements FileEncodingReader {

    @Override
    public Reader read(File file, Charset fileEncoding) {
        try {
            InputStream fileStream = new FileInputStream(file);
            BufferedInputStream bufferedInputStream = new BufferedInputStream(fileStream);
            return new InputStreamReader(bufferedInputStream, fileEncoding);
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

}
