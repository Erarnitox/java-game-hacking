public class AimbotMode {
	private static boolean active = false;
	private static AimbotThread aimbot;
	
	public static void startAimbot(GameMode gm) {
		if(!active) {
			active = true;
			aimbot = new AimbotThread(gm);
			aimbot.start();	
		}
	}
	
	public static void stopAimbot() {
		if(active) {
			active = false;
			aimbot.stopAimbot();
		}
		
	}
}
