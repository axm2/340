public class Main {
    public static long time = System.currentTimeMillis();
    static int totalCustomers;
    static int totalClerks;
    static int totalCashiers;
    static Customer[] customers;
    static Clerk[] clerks;
    static Cashier[] cashiers;
    static Thread[] customerThreads;
    static Thread[] clerkThreads;
    static Thread[] cashierThreads;
    static int customersServed;

    public static void main(String[] args) {

        // Set default arguments
        //TODO: Change this so that we only support args for customers.
        //TODO: Make sure the program runs total 40-90 seconds.
        totalCustomers = 12;
        totalClerks = 3;
        totalCashiers = 2;
        customersServed = 0;
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

        customers = new Customer[totalCustomers];
        clerks = new Clerk[totalClerks];
        cashiers = new Cashier[totalCashiers];
        customerThreads = new Thread[totalCustomers];
        clerkThreads = new Thread[totalClerks];
        cashierThreads = new Thread[totalCashiers];

        for (int i = 0; i < totalCustomers; i++) {
            customers[i] = new Customer(i);
            customerThreads[i] = new Thread(customers[i]);
            customerThreads[i].start();
        }
        for (int i = 0; i < totalClerks; i++) {
            clerks[i] = new Clerk(i);
            clerkThreads[i] = new Thread(clerks[i]);
            clerkThreads[i].start();
        }
        for (int i = 0; i < totalCashiers; i++) {
            cashiers[i] = new Cashier(i);
            cashierThreads[i] = new Thread(cashiers[i]);
            cashierThreads[i].start();
        }

    }


}