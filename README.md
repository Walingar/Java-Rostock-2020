# Homeworks for "Java Programming" course

# Task 5. Files

Your task is to implement the interfaces `api.file.FileEncodingReader` and `api.file.FileEncodingWriter`.
Create implemented instances in methods `impl.file.FileEncodingReaderFactory.getInstance` and `impl.file.FileEncodingWriterFactory.getInstance`.

`FileEncodingReader` should create `java.io.Reader` from the given file with the specific charset.

`FileEncodingWriter` should be able to create a new file and write data from `InputStream` used `dataEncoding` to this file with specific `fileEncoding` (`UTF-8`, if `fileEncoding` is not given).