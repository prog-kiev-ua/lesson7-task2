package ua.kovalev;

import java.util.Arrays;

public class ServiceCopy {
    private boolean hasData = false;
    private int numberBlock;
    private boolean stop;
    private byte[] block;

    synchronized public byte[] getBlock() throws NoMoreBytesForCopyException {
        for (; this.hasData == false && !stop; ) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if (block == null) {
            throw new NoMoreBytesForCopyException();
        }
        System.out.print(numberBlock);
        if (!stop) {
            System.out.print(" ");
        }
        byte[] blockTemp = Arrays.copyOf(block, block.length);
        this.hasData = false;
        this.block = null;
        notifyAll();
        return blockTemp;
    }

    synchronized public void setBlock(byte[] block, int count, boolean lastBlock) {
        for (; this.hasData == true; ) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.hasData = true;
        this.block = block;
        this.numberBlock = count;
        this.stop = lastBlock;
        notifyAll();
    }


    public boolean isStop() {
        return stop;
    }

    public void setStop(boolean stop) {
        this.stop = stop;
    }

    public int getNumberBlock() {
        return numberBlock;
    }

    public void setNumberBlock(int numberBlock) {
        this.numberBlock = numberBlock;
    }

    public boolean isHasData() {
        return hasData;
    }

    public void setHasData(boolean hasData) {
        this.hasData = hasData;
    }

    public void setBlock(byte[] block) {
        this.block = block;
    }
}
