import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class App {
	
	public static void main(String[] args) throws InterruptedException {
		/*
		Semaphore sem = new Semaphore(1);
		
		sem.acquire();
		sem.release();
		
		System.out.println("Available permits: " + sem.availablePermits());
		*/
		
		Connection.getInstance().connect();
		
		ExecutorService executor = Executors.newCachedThreadPool();
		
		for (int i = 0; i < 200; i++) {
			executor.submit(new Runnable() {
				public void run() {
					Connection.getInstance().connect();				
				}
			});
			
		}
		
		executor.shutdown();
		executor.awaitTermination(1, TimeUnit.DAYS);
		
		
		
		
	}

}
