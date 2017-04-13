import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class Processor implements Runnable {
	private CountDownLatch latch;
	private int id;
	public Processor(int id, CountDownLatch latch){
		this.id = id;
		this.latch = latch;
	}
	
	public void run() {
		System.out.println("Started " + id + ".");
		try {
			Thread.sleep(2000);
			System.out.println("C " + id + ".");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		latch.countDown();
		
	}
}

public class App {
	public static void main(String[] args) {
		CountDownLatch latch = new CountDownLatch(3);
		
		ExecutorService executor = Executors.newFixedThreadPool(3);
		
		for (int i = 0; i < 3; i++) {			
			executor.submit(new Processor(i, latch));			
		}
		
		try {
			latch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Completed.");
		
		
		
	}

}
