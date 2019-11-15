public class Cashier implements Runnable {
    public int ID;
    public static long time = System.currentTimeMillis();

    Cashier(int ID) {
        this.ID = ID;

    }

    @Override
    public void run() {
        msg("is running");

    }

    public void msg(String m) {
        System.out.println("[" + (System.currentTimeMillis() - time) + "] " + "Cashier-" + ID + ": " + m);
    }


}