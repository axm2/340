public class Customer implements Runnable {
    public int ID;
    public boolean hasSlip;
    public static int joinNum;
    // public static long time = System.currentTimeMillis(); // Maybe sync the time

    Customer(int ID) {
        this.ID = ID;
        hasSlip = false;
        joinNum = Main.totalCustomers - 1;

    }

    @Override
    public void run() {
        msg("is looking for an item to buy");
        try {
            Thread.sleep((long) (Math.random() * 100));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        msg("is deciding whether or not to purchase");
        Thread.yield();
        Thread.yield();

        msg("is requesting a slip");
        getSlip();
        // After we got slip, go to cashier and pay
        while (!hasSlip) {
        }
        msg("recieved their slip");
        int prio = Thread.currentThread().getPriority();
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Thread.currentThread().setPriority(prio + 1);
        // When the customer is at the cashier, set prio to default value
        pay(Math.random() < 0.5); // Customer decides cash or credit
        msg("has paid");
        Thread.currentThread().setPriority(Thread.NORM_PRIORITY);
        Main.doneShopping.add(this);
        msg("waiting for the rest of the customers to finish shopping");
        while (Main.doneShopping.size() != Main.totalCustomers) {
        } // Taking a break until all customers are done with their shopping.
        // Static int n init at totalCustomers, then we check is the ID == n? If it is,
        // then we begin the join chain.
        try {
            if (ID != 0) {
                Main.customerThreads[ID-1].join();
                msg("Joining with "+ Integer.toString(ID-1));
                //Main.doneShopping.remove(0);//Doesn't matter which we remove I think.
                msg("is leaving the store");
            }
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        if(ID==0){
            for(int i=Main.totalClerks-1;i>=0;i--){
                Main.clerkThreads[i].interrupt();
            }
            for(int i=Main.totalCashiers-1;i>=0;i--){
                Main.cashierThreads[i].interrupt();
            }
            msg("is leaving the store");
            //Main.doneShopping.remove(0);
        }
        // Customer N leaves with customer N-1, customer N-1 joins with N-2, etc. (use
        // join and isAlive)
        // Customer 1 announces to the floor clerks that it is closing time. calls
        // interrupt() on all floor clerks.

    }

    public void msg(String m) {
        System.out.println("[" + (System.currentTimeMillis() - Main.time) + "] " + "Customer-" + ID + ": " + m);
    }

    public void getSlip() {
        try {
            Main.availClerks.remove(0);
            Main.waitForSlip.add(this);
        } catch (Exception e) {
            return;
        }
    }

    public void setSlip() {
        this.hasSlip = true;
    }

    public void pay(boolean n) {
        if (n) {
            Main.waitPayCash.add(this);
            msg("has decided to pay with cash");
        } else {
            Main.waitPayCredit.add(this);
            msg("has decided to pay with credit");
        }
    }
}
