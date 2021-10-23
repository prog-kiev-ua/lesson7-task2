package ua.kovalev;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class Provider implements Runnable {
    private ServiceCopy serviceCopy;
    private File file;

    public Provider(File file, ServiceCopy serviceCopy) {
        this.file = file;
        this.serviceCopy = serviceCopy;
    }

    @Override
    public void run() {
        int sizeBlock = (int) (file.length() / 100);
        try (FileInputStream inputStream = new FileInputStream(file)) {
            boolean lastBlock = false;
            for (int i = 1; i <= 100; i++) {
                byte[] block;
                if (i == 100) {
                    block = inputStream.readAllBytes();
                    lastBlock = true;
                } else {
                    block = inputStream.readNBytes(sizeBlock);
                }
                serviceCopy.setBlock(block, i, lastBlock);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
