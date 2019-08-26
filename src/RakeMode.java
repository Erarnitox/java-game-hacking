public class RakeMode {
	private static boolean active = false;
	private static RakeThread rake;
	
	public static void startRake() {
		if(!active) {
			active = true;
			rake = new RakeThread();
			rake.start();	
		}
	}
	
	public static void stopRake() {
		if(active) {
			active = false;
			rake.stopRake();
		}
		
	}
}
