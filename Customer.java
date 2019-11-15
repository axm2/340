public class Customer implements Runnable {
    public int ID;
    public boolean isServed;
    public static long time = System.currentTimeMillis();

    Customer(int ID) {
        this.ID = ID;
        isServed = false;

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
        request();

    }

    public void msg(String m) {
        System.out.println("[" + (System.currentTimeMillis() - time) + "] " + "Customer-" + ID + ": " + m);
    }

    public void request() {
        int randomClerkIndex = (int) (Math.random() * Main.totalClerks);
        while (Main.clerks[randomClerkIndex] != null) {
            //Make the customers busy wait. By inserting I don't think we're actually busy waiting
            Main.clerks[randomClerkIndex].insert(this);
            break;
        }
    }

}