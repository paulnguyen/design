//
// http://javapapers.com/java/java-linkedblockingqueue/
//


import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class LinkedBlockingQueueExample {
	public static void main(String[] args) {
		final BlockingQueue<String> linkedBlockingQueue = new LinkedBlockingQueue<String>();

		LinkedBlockingQueueProducer queueProducer = new LinkedBlockingQueueProducer(
				linkedBlockingQueue);
		new Thread(queueProducer).start();

		LinkedBlockingQueueConsumer queueConsumer1 = new LinkedBlockingQueueConsumer(
				linkedBlockingQueue);
		new Thread(queueConsumer1).start();

		LinkedBlockingQueueConsumer queueConsumer2 = new LinkedBlockingQueueConsumer(
				linkedBlockingQueue);
		new Thread(queueConsumer2).start();

	}
}