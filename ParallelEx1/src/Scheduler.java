
public abstract class Scheduler{

	protected double timeToCross, carsOnBridgeSimultaneously;
	public Scheduler(double time, double cars)
	{
		timeToCross = time;
		carsOnBridgeSimultaneously = cars;
	}
	public abstract void crossBridge(Car c);
	public abstract void enterBridge(Car c);
	public abstract void exitBridge(Car c);
	
}
