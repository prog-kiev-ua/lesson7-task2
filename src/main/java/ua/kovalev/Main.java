package ua.kovalev;

import java.io.File;

public class Main {
    public static void main(String[] args) {
        ServiceCopy serviceCopy = new ServiceCopy();
        new Thread(new Provider(new File("ubuntu-20.04.2-desktop-amd64.iso"), serviceCopy)).start();
        new Thread(new Consumer(new File("_ubuntu-20.04.2-desktop-amd64.iso"), serviceCopy)).start();
    }
}
