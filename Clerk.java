
public class Clerk implements Runnable {
    public int ID;
    public static long time = System.currentTimeMillis(); // Maybe sync the time

    Clerk(int ID) {
        this.ID = ID;
    }

    @Override
    public void run() {
        msg("is running");
        // busy wait until customers arrive
        // help the customers in a FCFS order ( one at a time )
        // when we have helped all the customers, wait for closing time.
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        

    }

    public void msg(String m) {
        System.out.println("[" + (System.currentTimeMillis() - time) + "] " + "Clerk-" + ID + ": " + m);
    }

}