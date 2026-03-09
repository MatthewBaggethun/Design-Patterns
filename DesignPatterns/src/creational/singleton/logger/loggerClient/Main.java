package creational.singleton.logger.loggerClient;

import java.util.concurrent.atomic.AtomicInteger;

import creational.singleton.logger.loggers.LoggerClassic;
import creational.singleton.logger.loggers.LoggerModern;
import creational.singleton.logger.serviceSimulatorsClassic.AuthServiceSimulatorClassic;
import creational.singleton.logger.serviceSimulatorsClassic.FileProcessorSimulatorClassic;
import creational.singleton.logger.serviceSimulatorsClassic.TaskManagerSimulatorClassic;
import creational.singleton.logger.serviceSimulatorsModern.AuthServiceSimulatorModern;
import creational.singleton.logger.serviceSimulatorsModern.FileProcessorSimulatorModern;
import creational.singleton.logger.serviceSimulatorsModern.TaskManagerSimulatorModern;

public class Main {

	public static void main(String[] args) {

		// testClassicSimulators();
		// testModernSimulators();
		
		System.out.println("Testing thread safety of the classic logger:" + "\n");
		testThreadSafetyClassic();
		
		System.out.println("\n" + "Testing thread safety of the modern logger:" + "\n");
		testThreadSafetyModern();

	}

	private static void testAuthServiceClassic() {
		AuthServiceSimulatorClassic authService = new AuthServiceSimulatorClassic();

		authService.login("Jimbo", "wrongpassword");
		authService.login("Bilbo Bagins", "mypassword123");
		authService.logout("Bilbo Bagins");
	}

	private static void testFileProcessorClassic() {
		FileProcessorSimulatorClassic fileProcessor = new FileProcessorSimulatorClassic();

		fileProcessor.processFile("path/to/my/file.txt");
		fileProcessor.processFile("this/path/will/fail.png");
	}

	private static void testTaskManagerClassic() {
		TaskManagerSimulatorClassic taskManager = new TaskManagerSimulatorClassic();

		taskManager.createTask("This is task 1");
		taskManager.deleteTask("This is task 1");

		taskManager.createTask("This is task 2");
		taskManager.completeTask("This is task 2");
	}

	private static void testClassicSimulators() {
		System.out.println("Testing AuthServiceSimulator with a classic logger:" + "\n");
		testAuthServiceClassic();

		System.out.println("\n" + "Testing FileProcessorSimulator with a classic logger:" + "\n");
		testFileProcessorClassic();

		System.out.println("\n" + "Testing TaskManagerSimulator with a classic logger:" + "\n");
		testTaskManagerClassic();
	}

	private static void testAuthServiceModern() {
		AuthServiceSimulatorModern authService = new AuthServiceSimulatorModern();

		authService.login("Jimbo", "wrongpassword");
		authService.login("Bilbo Bagins", "mypassword123");
		authService.logout("Bilbo Bagins");
	}

	private static void testFileProcessorModern() {
		FileProcessorSimulatorModern fileProcessor = new FileProcessorSimulatorModern();

		fileProcessor.processFile("path/to/my/file.txt");
		fileProcessor.processFile("this/path/will/fail.png");
	}

	private static void testTaskManagerModern() {
		TaskManagerSimulatorModern taskManager = new TaskManagerSimulatorModern();

		taskManager.createTask("This is task 1");
		taskManager.deleteTask("This is task 1");

		taskManager.createTask("This is task 2");
		taskManager.completeTask("This is task 2");
	}

	private static void testModernSimulators() {
		System.out.println("Testing AuthServiceSimulator with a modern logger:" + "\n");
		testAuthServiceClassic();

		System.out.println("\n" + "Testing FileProcessorSimulator with a modern logger:" + "\n");
		testFileProcessorClassic();

		System.out.println("\n" + "Testing TaskManagerSimulator with a modern logger:" + "\n");
		testTaskManagerClassic();
	}

	private static void testThreadSafetyClassic() {
		AtomicInteger firstHash = new AtomicInteger(0);

		Runnable task = () -> {
			LoggerClassic logger = LoggerClassic.getInstance();
			int currentHash = logger.hashCode();

			if (firstHash.compareAndSet(0, currentHash)) {
			} else if (firstHash.get() != currentHash) {
				logger.warn("Race condition detected! Multiple Logger instances created.");
			}
		};

		Thread[] threads = new Thread[30];

		for (int i = 0; i < threads.length; i++) {
			threads[i] = new Thread(task);
			threads[i].start();
		}

		for (Thread t : threads) {
			try {
				t.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		LoggerClassic.getInstance().info(
				"All threads finished. If no warnings appeared, the singleton behaved thread-safe during this run.");
	}

	private static void testThreadSafetyModern() {
		AtomicInteger firstHash = new AtomicInteger(0);

		Runnable task = () -> {
			LoggerModern logger = LoggerModern.INSTANCE;
			int currentHash = logger.hashCode();

			if (firstHash.compareAndSet(0, currentHash)) {
			} else if (firstHash.get() != currentHash) {
				logger.warn("Race condition detected! Multiple Logger instances created.");
			}
		};

		Thread[] threads = new Thread[30];

		for (int i = 0; i < threads.length; i++) {
			threads[i] = new Thread(task);
			threads[i].start();
		}

		for (Thread t : threads) {
			try {
				t.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		LoggerModern.INSTANCE.info(
				"All threads finished. If no warnings appeared, the singleton behaved thread-safe during this run.");
	}

}
