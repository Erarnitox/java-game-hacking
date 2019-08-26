public class EspMode {
	private static boolean active = false;
	private static EspThread esp;
	
	public static void startEsp(GameMode gm) {
		if(!active) {
			active = true;
			esp = new EspThread(gm);
			esp.start();	
		}
	}
	
	public static void stopEsp() {
		if(active) {
			active = false;
			esp.stopEsp();
		}
		
	}
}
