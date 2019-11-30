public class Main {
    public static long time = System.currentTimeMillis();
    static int totalCustomers;
    static int totalClerks;
    static int totalCashiers;
    static Thread[] customerThreads;
    static Thread[] clerkThreads;
    static Thread[] cashierThreads;
    public static void main(String[] args) {

        // Set default arguments
        //TODO: Make sure the program runs total 40-90 seconds.
        //Change this so that the only arg is the customer one. Hardcode num of Clerk and Cashier
        totalCustomers = 12;
        totalClerks = 3;
        totalCashiers = 2;
        // Replace defaults with command line arguments if they exist
        if (args.length > 0) {
            totalCustomers = Integer.parseInt(args[0]);
        }
        if (args.length > 1) {
            totalClerks = Integer.parseInt(args[1]);
        }
        if (args.length > 2) {
            totalCashiers = Integer.parseInt(args[2]);
        }

        customerThreads = new Thread[totalCustomers];
        clerkThreads = new Thread[totalClerks];
        cashierThreads = new Thread[totalCashiers];

        for (int i = 0; i < totalCustomers; i++) {
            customerThreads[i] = new Thread(new Customer(i));
            customerThreads[i].start();
        }
        for (int i = 0; i < totalClerks; i++) {
            clerkThreads[i] = new Thread(new Clerk(i));
            clerkThreads[i].start();
        }
        for (int i = 0; i < totalCashiers; i++) {
            cashierThreads[i] = new Thread(new Cashier(i));
            cashierThreads[i].start();
        }

    }


}