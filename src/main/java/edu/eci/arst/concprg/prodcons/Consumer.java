/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arst.concprg.prodcons;

import java.util.Queue;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author hcadavid
 */
public class Consumer extends Thread{
    
    private Queue<Integer> queue;
    
    
    public Consumer(Queue<Integer> queue){
        this.queue=queue; 
    }

    @Override
    public void run() {
        while (true) {
            if (queue.size() <= 0) {
                this.waiting();
            } else {
                int elem = queue.poll();
                try {
                    Thread.sleep(500);//
                } catch (InterruptedException ex) {
                    Logger.getLogger(Producer.class.getName()).log(Level.SEVERE, null, ex);
                }
                System.out.println("Consumer consumes " + elem);
            }
            synchronized (queue) {
                queue.notify();
            }
        }
    }

    public void waiting() {
        synchronized (queue) {
            try {
                queue.wait();
            } catch (InterruptedException ex) {
                Logger.getLogger(Consumer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}

