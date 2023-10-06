package gateway;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class LinkedBlockingQueueUnique {

    private static LinkedBlockingQueueUnique instance;

    private BlockingQueue<String> sharedQueue = new LinkedBlockingQueue<>();

    private LinkedBlockingQueueUnique() {

    }
    public static synchronized LinkedBlockingQueueUnique getInstance() {
        if (instance == null) {
            instance = new LinkedBlockingQueueUnique();
        }
        return instance;
    }

    public synchronized void addInQueue(String add) throws InterruptedException {
        try {
            sharedQueue.put(add);
        }catch (InterruptedException e){
            Thread.currentThread().interrupt(); // Preserva o status de interrupção
        }
    }
    public synchronized String getFromQueue() throws InterruptedException {
        try {
            return sharedQueue.take();
        }catch (InterruptedException e){
            Thread.currentThread().interrupt(); // Preserva o status de interrupção
            return null;
        }
    }

}
