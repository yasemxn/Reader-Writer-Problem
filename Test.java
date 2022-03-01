import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
public class Test {
    public static void main(String [] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        ReadWriteLock RW = new ReadWriteLock();


        executorService.execute(new Writer(RW));
        executorService.execute(new Writer(RW));
        executorService.execute(new Writer(RW));
        executorService.execute(new Writer(RW));

        executorService.execute(new Reader(RW));
        executorService.execute(new Reader(RW));
        executorService.execute(new Reader(RW));
        executorService.execute(new Reader(RW));


    }
}
class ReadWriteLock{
    private Semaphore readSemaphore=new Semaphore(1);
    private Semaphore writeSemaphore=new Semaphore(1);
    private boolean isWriting = false;

    public void readLock() {
        
        try{
            readSemaphore.acquire();
        }catch (InterruptedException e) {}

        
        isWriting=true;

        if (isWriting == true){
            try{
                writeSemaphore.acquire();
            }
            catch (InterruptedException e) {}
        }
      

        System.out.println("Thread " + Thread.currentThread().getName() + " is READING.");
    }  
    public void writeLock() {
        
        try{
            writeSemaphore.acquire();
       
        }
        catch (InterruptedException e) {}
        System.out.println("Thread " + Thread.currentThread().getName() + " is WRITING.");

    }
    
    public void readUnLock() throws InterruptedException {

        isWriting=false;

        if(isWriting==false) {
            writeSemaphore.release();
        }
        System.out.println("Thread " + Thread.currentThread().getName() + " DONE READING.");

        readSemaphore.release();

    }


    public void writeUnLock() {
        isWriting=false;

        System.out.println("Thread " + Thread.currentThread().getName() + "  DONE WRITING.");
        writeSemaphore.release();

    }
}

class Writer implements Runnable
{
    private ReadWriteLock RW_lock;


    public Writer(ReadWriteLock rw) {
        RW_lock = rw;
    }

    public void run() {
        while (true){
            threadSleep.nap();
            RW_lock.writeLock();
            threadSleep.nap();
            RW_lock.writeUnLock();

        }
    }


}



class Reader implements Runnable
{
    private ReadWriteLock RW_lock;


    public Reader(ReadWriteLock rw) {
        RW_lock = rw;
    }
    public void run() {
        while (true){
            threadSleep.nap();
            RW_lock.readLock();


            try {
                threadSleep.nap();
                RW_lock.readUnLock();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }
    }


}
class threadSleep
{
    public static void nap() {
        nap(NAP_TIME);
    }

    public static void nap(int duration) {
        int sleeptime = (int) (NAP_TIME * Math.random() );
        try { Thread.sleep(sleeptime*1000); }
        catch (InterruptedException e) {}
    }

    private static final int NAP_TIME = 5;
}