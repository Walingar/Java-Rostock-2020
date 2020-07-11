package impl.file;

import api.file.FileEncodingWriter;
import api.file.FileEncodingReader;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class FileEncodingWriterImpl implements FileEncodingWriter {
    @Override
    public void write(File file, InputStream data, Charset dataEncoding) {
        this.write(file, data, dataEncoding, StandardCharsets.UTF_8);
    }

    @Override
    public void write(File file, InputStream data, Charset dataEncoding, Charset fileEncoding) {
        try {
            Reader reader = new InputStreamReader(data, dataEncoding);

            OutputStream outputStream = new FileOutputStream(file);
            Writer writer = new OutputStreamWriter(outputStream, fileEncoding);

            reader.transferTo(writer);
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
