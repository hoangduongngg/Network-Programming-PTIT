/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package producerconsumer;

/**
 *
 * @author hoangduongngg
 */
public class ProducerConsumer {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        SharedData sd = new SharedData();
        new Producer(sd, 5).start();
        new Thread(new Consumer()sd, 5).start();
        
    }
    
}

class Producer extends Thread{
    
    SharedData shareData;
    int num;
   
    public Producer (SharedData sharedData, int num) {
        this.shareData = sharedData;
        this.num = num;
    }
    
    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            
        }
    }
}

class Consumer implements Runnable {
    SharedData sharedData;
    
}

class SharedData {
    int data;
    boolean produced = false;
    
    public void consume {
            System.out.println("consume: " + this.data);
            this.data = 0;
    }
}
