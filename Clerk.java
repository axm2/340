
public class Clerk implements Runnable {
    public int ID;
    // public static long time = System.currentTimeMillis(); // Maybe sync the time
    public static int totalCustomersHelpedClerk;

    Clerk(int ID) {
        this.ID = ID;
    }

    @Override
    public void run() {
        msg("is waiting to help customers");
        while (Main.customerThreads[0] == null) {
        } // busy wait until at least one customer arrives.
        Main.availClerks.add(this);
        while (totalCustomersHelpedClerk != Main.totalCustomers) { // keep trying to help until someone has helped every customer
            giveSlip();
        }
        try {
            Thread.sleep(400000); // when we have helped all the customers, wait for closing time.
            msg("is waiting for the store to close...");
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            msg("is leaving because it was notified about closing time");
        }

    }

    public void msg(String m) {
        System.out.println("[" + (System.currentTimeMillis() - Main.time) + "] " + "Clerk-" + ID + ": " + m);
    }

    public void giveSlip() {
        try {
            // add to the queue
            Main.waitForSlip.remove(0).setSlip(); // help the customers in a FCFS order ( one at a time )
            totalCustomersHelpedClerk++;
            Main.availClerks.add(this);
        } catch (Exception e) {
            // add to the queue
            return;
            // If there are no customers in line, we need to go back to busy waiting. so
            // just return
        }
    }

}