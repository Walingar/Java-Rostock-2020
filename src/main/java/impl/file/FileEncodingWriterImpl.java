package impl.file;

import api.file.FileEncodingReader;
import api.file.FileEncodingWriter;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class FileEncodingWriterImpl implements FileEncodingWriter {
    @Override
    public void write(File file, InputStream data, Charset dataEncoding) {
        write(file,data, dataEncoding, StandardCharsets.UTF_8);
    }

    @Override
    public void write(File file, InputStream data, Charset dataEncoding, Charset fileEncoding) {
        try {
            if (!file.exists()) {
                if (!file.getParentFile().mkdirs() || !file.createNewFile()) {
                    System.out.println("File doesn't exist and it paths as well as itself couldn't be created.");
                    return;
                }
            }
            execute(file, data, dataEncoding, fileEncoding);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private void execute(File file, InputStream data, Charset dataEncoding, Charset fileEncoding) throws IOException {
        Reader reader = new InputStreamReader(data, dataEncoding);
        OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(file));
        try (Writer writer = new OutputStreamWriter(outputStream, fileEncoding)) {
            reader.transferTo(writer);
        }
    }
}
