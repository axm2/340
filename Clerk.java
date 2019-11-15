import java.util.ArrayDeque;

public class Clerk implements Runnable {
    public int ID;
    public ArrayDeque<Customer> queue;
    public static long time = System.currentTimeMillis();

    Clerk(int ID) {
        this.ID = ID;
        queue = new ArrayDeque<Customer>();
    }

    @Override
    public void run() {
        msg("is running");
        //TODO: This busy wait is broken, maybe instead of giving each clerk a queue we make one big one
        while(!queue.isEmpty()){
            msg("Serving a customer");
            queue.pop();
            Main.customersServed++;
        }
        if(Main.customersServed==Main.totalCustomers){
            try{
                msg("Waiting for closing time");
                Thread.sleep(100);
            }
            catch(InterruptedException e){
                e.printStackTrace();
            }
        }

    }

    public void msg(String m) {
        System.out.println("[" + (System.currentTimeMillis() - time) + "] " + "Clerk-" + ID + ": " + m);
    }


    public void insert(Customer x){
        queue.add(x);
    }


}