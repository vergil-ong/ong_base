package ong.ong.util;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SerialNumTest {
	
	public static Set<String> set = new HashSet<String>();
	public static Set<String> dupSet = new HashSet<String>();
	
	public static void main(String[] args) {
		int count = 2000000;
		
		CountDownLatch latch = new CountDownLatch(count);
		
		ExecutorService newFixedThreadPool = Executors.newFixedThreadPool(2000);
		
		for(int i=0;i<count;i++){
			newFixedThreadPool.execute(new SerialNumThread(latch));
		}
		
		try {
			latch.await();
			System.out.println("set size is "+set.size());
			System.out.println(dupSet);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		
		
		newFixedThreadPool.shutdown();
	}
}
