import java.util.ArrayDeque;

public class Clerk implements Runnable {
    public int ID;
    public ArrayDeque<Customer> queue;
    public static long time = System.currentTimeMillis();

    Clerk(int ID) {
        this.ID = ID;
    }

    @Override
    public void run() {
        /* while(!queue.isEmpty()){
            System.out.println("Shouldn't get here");
        } */

    }

    public void msg(String m) {
        System.out.println("[" + (System.currentTimeMillis() - time) + "] " + "Clerk-" + ID + ": " + m);
    }

    public void serve(){
        //check if queue not empty, if its not, pop (one or all?)
    }


}