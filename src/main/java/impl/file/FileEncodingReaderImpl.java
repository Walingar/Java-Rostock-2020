package impl.file;

import api.file.FileEncodingReader;

import java.io.*;
import java.nio.charset.Charset;

public class FileEncodingReaderImpl implements FileEncodingReader {

    @Override
    public Reader read(File file, Charset fileEncoding) {
        try {
            InputStream inputStream = new BufferedInputStream(new FileInputStream(file));
            return new InputStreamReader(inputStream, fileEncoding);
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

}
