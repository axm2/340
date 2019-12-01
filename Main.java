import java.util.Vector;

public class Main {
    public static long time = System.currentTimeMillis();
    // Total # of customer, clerk, cashiers
    static int totalCustomers;
    static int totalClerks;
    static int totalCashiers;
    // Thread pointer arrays
    static Thread[] customerThreads;
    static Thread[] clerkThreads;
    static Thread[] cashierThreads;
    // Queues
    static Vector<Customer> arrived = new Vector<Customer>();
    static Vector<Customer> waitForSlip = new Vector<Customer>();
    static Vector<Clerk> waitToServe = new Vector<Clerk>();
    static Vector<Customer> waitPayCash = new Vector<Customer>();
    static Vector<Customer> waitPayCredit = new Vector<Customer>();
    static Vector<Clerk> availClerks = new Vector<Clerk>();
    static Vector<Customer> doneShopping = new Vector<Customer>();

    public static void main(String[] args) {

        // Set default arguments
        // TODO: Make sure the program runs total 40-90 seconds.
        totalCustomers = 12;
        totalClerks = 3;
        totalCashiers = 2;
        // Replace defaults with command line arguments if they exist
        if (args.length != 0) {
            totalCustomers = Integer.parseInt(args[0]);
        }

        customerThreads = new Thread[totalCustomers];
        clerkThreads = new Thread[totalClerks];
        cashierThreads = new Thread[totalCashiers];

        for (int i = 0; i < totalCashiers; i++) {
            cashierThreads[i] = new Thread(new Cashier(i));
            cashierThreads[i].start();
        }
        for (int i = 0; i < totalClerks; i++) {
            clerkThreads[i] = new Thread(new Clerk(i));
            clerkThreads[i].start();
        }
        for (int i = 0; i < totalCustomers; i++) {
            customerThreads[i] = new Thread(new Customer(i));
            customerThreads[i].start();
        }

    }

}