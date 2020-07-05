package impl.file;

import api.file.FileEncodingReader;

import java.io.*;
import java.nio.charset.Charset;

public class FileEncodingReaderImpl implements FileEncodingReader {

    @Override
    public Reader read(File file, Charset fileEncoding) {
        try {
            InputStream fileStream = new FileInputStream(file);
            Reader output = new InputStreamReader(fileStream, fileEncoding);
            return output;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

}
