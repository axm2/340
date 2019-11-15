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
        try{
            Thread.sleep((long)Math.random()*100);
        }
        catch(InterruptedException e){
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

    public void request(){
        //Fix this so that we get a random clerk from one to Main.totalClerks
        Main.clerks[0].serve();
    }

}