package impl.file;

import api.file.FileEncodingReader;

import java.io.*;
import java.nio.charset.Charset;

public class FileEncodingReaderImpl implements FileEncodingReader {
    @Override
    public Reader read(File file, Charset fileEncoding) {
        try {
            InputStream inputStream = new BufferedInputStream(new FileInputStream(file));
            Reader inputStreamReader = new InputStreamReader(inputStream, fileEncoding);
            return inputStreamReader;

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
