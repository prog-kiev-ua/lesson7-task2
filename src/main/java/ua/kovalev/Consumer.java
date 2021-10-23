package ua.kovalev;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class Consumer implements Runnable {
    private ServiceCopy serviceCopy;
    private File file;

    public Consumer(File file, ServiceCopy serviceCopy) {
        this.file = file;
        this.serviceCopy = serviceCopy;
    }

    @Override
    public void run() {
        try (FileOutputStream os = new FileOutputStream(file)) {
            while (true){
                byte[] block = serviceCopy.getBlock();
                os.write(block);
                os.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NoMoreBytesForCopyException e){
            System.out.print("\nФайл скопирован");
        }
    }
}
