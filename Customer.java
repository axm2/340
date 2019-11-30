public class Customer implements Runnable {
    public int ID;
    public static long time = System.currentTimeMillis(); // Maybe sync the time

    Customer(int ID) {
        this.ID = ID;

    }

    @Override
    public void run() {
        msg("is looking for an item to buy");
        try {
            // Check if this is actually randomly generating number under 100
            Thread.sleep((long) (Math.random() * 100));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        msg("is deciding whether or not to purchase");
        Thread.yield();
        Thread.yield();

        msg("is requesting a slip");
        // getSlip();
        // After we got slip, go to cashier and pay
        int prio = Thread.currentThread().getPriority();
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Thread.currentThread().setPriority(prio+1);
        //When the customer is at the cashier, set prio to default value
        Thread.currentThread().setPriority(Thread.NORM_PRIORITY);
        //Customer decides cash or credit
        //Flip a coin
        
    }

    public void msg(String m) {
        System.out.println("[" + (System.currentTimeMillis() - time) + "] " + "Customer-" + ID + ": " + m);
    }
}
