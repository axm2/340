public class Cashier implements Runnable {
    public int ID;
    //public static long time = System.currentTimeMillis();
    public static int totalCustomersHelpedCashier;

    Cashier(int ID) {
        this.ID = ID;

    }

    @Override
    public void run() {
        msg("is waiting to help customers");
        if(ID == 0){ // cashier 0 is the cash cashier
            while(totalCustomersHelpedCashier!=Main.totalCustomers){
                serveCash();
            }
        }
        if(ID == 1){ // cashier 1 is the credit cashier
            while(totalCustomersHelpedCashier!=Main.totalCustomers){
                serveCredit();
            }
        }
        try {
            Thread.sleep(400000); // when we have helped all the customers, wait for closing time.
            msg("is waiting for the store to close...");
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            msg("is leaving because it was notified about closing time");
        }
        // busy wait on cash or credit queue, whichever is appropriate
        // serve customer in a FCFS basis
        // Terminate at closing time? I guess wait for interrupt from customer 1.

    }

    public void msg(String m) {
        System.out.println("[" + (System.currentTimeMillis() - Main.time) + "] " + "Cashier-" + ID + ": " + m);
    }

    public void serveCash(){
        try{
            Main.waitPayCash.remove(0);
            totalCustomersHelpedCashier++;
        }
        catch(Exception e){
            return;
        }
    }

    public void serveCredit(){
        try{
            Main.waitPayCredit.remove(0);
            totalCustomersHelpedCashier++;
        }
        catch(Exception e){
            return;
        }

    }


}