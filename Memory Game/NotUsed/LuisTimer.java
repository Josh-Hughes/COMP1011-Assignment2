package gc.cs.comp1011.memorygame;
/**
 * 
 * @author Luis Acevedo & Josh Hughes
 *
 */
public class LuisTimer {
	//variables
	private final double TIMER_SETPOINT_IN_SECONDS = 5;
	private double startTime;
	private double endTime;
	
	public LuisTimer() {
		this.startTime = 0;
		this.endTime = 0;
	}
	
	public void startTimer(){
		if(startTime == 0){
			setStartTime(System.currentTimeMillis());
			setEndTime(getStartTime() + (TIMER_SETPOINT_IN_SECONDS * 1000));
		}
	}
	
	public boolean checkTimer(){
		if(System.currentTimeMillis() >= getEndTime())
			return true;
		
		return false;
	}
	
	public int getTimeRemaining(){
		return (int) ((getEndTime() - System.currentTimeMillis())/1000);
	}
	
	public void addTime(int timeInSeconds){
		double timeInMilli;
		
		timeInMilli = (double) (timeInSeconds * 1000);
		timeInMilli += getEndTime();
		
		setEndTime(timeInMilli);
	}

	private double getStartTime() {
		return startTime;
	}

	private void setStartTime(double startTime) {
		this.startTime = startTime;
	}

	private double getEndTime() {
		return endTime;
	}

	private void setEndTime(double endTime) {
		this.endTime = endTime;
	}
}