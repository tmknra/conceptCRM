package pack.concept.file_service.service;

import java.io.FileInputStream;
import java.io.FileOutputStream;

public class FileManager {

    private final String src;
    private final String dest;
    private final byte[] buf;

    public FileManager(String src, String dest, int bufSize) {
        this.src = src;
        this.dest = dest;
        this.buf = new byte[bufSize];
    }

    public Boolean copy() {
        try (FileInputStream fis = new FileInputStream(src);
             FileOutputStream fos = new FileOutputStream(dest)) {
            int count;
            while ((count = fis.read()) > 0)
                fos.write(buf, 0, count);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }
}
