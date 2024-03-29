import java.util.ArrayList;

public class CarGenerator extends Thread
{
	private int redCounter = 0, blueCounter = 0;
	private Scheduler myScheduler;
	private SystemLog log;
	private boolean stop;

	public CarGenerator(String directory)
	{
		stop = false;
		log = new SystemLog(directory);
	}

	public void run()
	{
		Car aCar;

		ArrayList<Car> threadList = new ArrayList<Car>();
		while(!stop)
		{			
			aCar = this.getNewRandomCar();			
			aCar.setMyScheduler(myScheduler);
			aCar.start();
			threadList.add(aCar);
			try 
			{
				sleep((int)(1000/Main.carRate));
			} 
			catch (InterruptedException e) 
			{
				e.printStackTrace();
			}
		}
		log.closeWriter();
	}

	public synchronized Car getNewRandomCar()
	{
		int num, colour;		
		if(Math.random()>=0.5)
		{
			redCounter++;
			num = redCounter;
			colour = 2;
		}
		else
		{
			blueCounter++;
			num = blueCounter;
			colour=1;
		}
		Car aCar = new Car(num,colour,this.myScheduler,log);
		aCar.setArrived(System.currentTimeMillis());//Orizetai o xronos afikshs tou amaksiou sth gefyra.
		return aCar;
	}

	public void setScheduler(Scheduler aScheduler)
	{
		this.myScheduler = aScheduler;
	}
	
	public Scheduler getScheduler()
	{
		return this.myScheduler;
	}

	public SystemLog getLog() 
	{
		return log;
	}

	public void stopCarProduction()
	{
		stop = true;
	}
}