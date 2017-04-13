import java.io.IOException;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class App {
	public static void main(String[] args) {
		ExecutorService executor = Executors.newCachedThreadPool();
		
		/*
		executor.submit(new Runnable() {
			public void run() {
				Random random = new Random();
				int duration = random.nextInt(4000);
				System.out.println("Starting ...");

				try {
					Thread.sleep(duration);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

				System.out.println("Finished.");
			}
		});
		*/
		
		// type I wan to return
		/*
		 * 
		 * alternative use
		 * Future<?> future = executor.submit(new Callable<void>() {
		 * ...
		 * public void call() throws Exception {
		 * ...
		 * return null
		 * 
		 *  allow us to use all the functionalities of the future class
		 *  without returning a value
		 * */
		Future<Integer> future = executor.submit(new Callable<Integer>() {
			public Integer call() throws Exception {
				Random random = new Random();
				int duration = random.nextInt(4000);
				
				// throwing custom exceptions
				if(duration > 2000) {
					throw new IOException("Sleeping for too long.");
					
				}
				System.out.println("Starting ...");

				try {
					Thread.sleep(duration);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

				System.out.println("Finished.");
				return duration;
			}
		});

		executor.shutdown();
		
		
		try {
			System.out.println("Result is: " + future.get());
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			IOException ex = (IOException) e.getCause();
			System.out.println(ex.getMessage());
		}
		

	}

}
