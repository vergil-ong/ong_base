package ong.ong.util;

import java.util.concurrent.CountDownLatch;

import com.ong.util.SerialNumHelper;

public class SerialNumThread implements Runnable {
	
	private CountDownLatch latch;
	
	public SerialNumThread(CountDownLatch latch){
		this.latch = latch;
	}
	

	@Override
	public void run() {
		String serialNum = SerialNumHelper.generateSerial(20);
//		System.out.println(serialNum);
		if(SerialNumTest.set.contains(serialNum)){
			System.out.println("重复了 "+serialNum);
			SerialNumTest.dupSet.add(serialNum);
		}else{
			SerialNumTest.set.add(serialNum);
		}
		latch.countDown();
	}

}
