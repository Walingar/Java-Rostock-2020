package impl.file;

import api.file.FileEncodingWriter;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class FileEncodingWriterImpl implements FileEncodingWriter {
    @Override
    public void write(File file, InputStream data, Charset dataEncoding) {
        write(file, data, dataEncoding, StandardCharsets.UTF_8);
    }

    @Override
    public void write(File file, InputStream data, Charset dataEncoding, Charset fileEncoding) {

        try {
            if (!file.exists()) {
                if (!file.getParentFile().mkdirs() || !file.createNewFile()){
                    System.out.println("File not existing and not creatable");
                    return;
                }
            }

            Reader reader = new InputStreamReader(data, dataEncoding);
            OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(file));
            Writer writer = new OutputStreamWriter(outputStream, fileEncoding);
            reader.transferTo(writer);
            writer.close();

        } catch (IOException e) {
            System.out.println(e.toString());
        }

    }
}
