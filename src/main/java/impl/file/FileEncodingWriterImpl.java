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
            if (!file.exists()) {
                if (!file.getParentFile().mkdirs() || !file.createNewFile()){
                    System.out.println("File not existing and not creatable");
                    return;
                }
                // im confused by our lecture on Tuesday
                // do i have to try to create a file in order to write into it? (when a.txt does not exists?)
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
