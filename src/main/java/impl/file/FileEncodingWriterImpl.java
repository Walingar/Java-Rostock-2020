package impl.file;

import api.file.FileEncodingReader;
import api.file.FileEncodingWriter;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class FileEncodingWriterImpl implements FileEncodingWriter {
    @Override
    public void write(File file, InputStream data, Charset dataEncoding) {
        try {
            file.getParentFile().mkdirs();
            file.createNewFile();
            Reader reader = new InputStreamReader(data, dataEncoding);
            OutputStream outputStream = new FileOutputStream(file);
            Writer writer = new OutputStreamWriter(outputStream, StandardCharsets.UTF_8);
            reader.transferTo(writer);
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void write(File file, InputStream data, Charset dataEncoding, Charset fileEncoding) {
        try {
            file.getParentFile().mkdirs();
            file.createNewFile();
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
