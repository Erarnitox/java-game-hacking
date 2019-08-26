import com.guidedhacking.GHMemory;
import com.guidedhacking.GHPointer;

public class AimbotThread extends Thread{
	private boolean stop = false;
	private GameMode gm;
	private final int moduleBase = Helper.moduleBase;
	
	public AimbotThread(GameMode gm) {
		this.gm = gm;
	}
	
	public void run() {
		while(!stop) {
			try {
				Enemy[] players = new Enemy[GHMemory.readInt(moduleBase+0x29CD3C)-1];
				Player myPlayer = new Player();
				GHPointer cooldownPtr = new GHPointer(moduleBase+0x216454, 0x174);
				GHPointer listPointer = new GHPointer(moduleBase+0x29CD34,0x4);
				long cooldownObj = GHMemory.getObjectAddress(cooldownPtr);
				long playerList = GHMemory.getObjectAddress(listPointer);
				boolean hasShot = false;
				int n= 0;
				
				float xRot;
				float yRot;
				float dist;
				Position enemyPos;
				Position playerPos;
				
				for(int i=0; i < players.length; i++) {
					players[i] = new Enemy((int)playerList+(i*4));
				}
				
				
				while(players.length == (GHMemory.readInt(moduleBase+0x29CD3C)-1) && !stop) {
					for(int i=0; i < players.length; i++) {
						if(players[i].canBeSeen() && players[i].isAlive()) {
							if(!gm.isTeamMode || myPlayer.isEnemy(players[i])) {
								enemyPos = players[i].getPosition();
								playerPos = myPlayer.getPosition();
								
								while(!(hasShot || ++n > 10) && !stop){
									Thread.sleep(5);
									//calcute x and y rotation to aim at enemy:
									yRot = (float) (-Math.atan2(enemyPos.x-playerPos.x,enemyPos.y-playerPos.y)/ Math.PI * 180);
									dist = (float) (Math.sqrt(Math.pow((enemyPos.x-playerPos.x),2)+ Math.pow((enemyPos.y-playerPos.y),2) + Math.pow((enemyPos.z-playerPos.z),2)));
									xRot = (float) (Math.asin((enemyPos.z - playerPos.z) / dist) * 180.0F / Math.PI);
								
									//rotate player to point at enemy: 
									myPlayer.setRotation(xRot, yRot);
								
									//is enemy in crosshair
									if(GHMemory.readByte(moduleBase+0x1E587B) != (byte)90) {
										//shoot:
										myPlayer.shoot();
										Thread.sleep(50);
										myPlayer.stopShooting();
										Thread.sleep(GHMemory.readInt(cooldownObj)+50);
										hasShot = true;
									}
								}
								n = 0;
								hasShot = false;
							}
						}
					}
				}
				
			}catch(InterruptedException e) {
				//return;
			}
		}
	}
	
	public void stopAimbot() {
		stop = true;
	}
}
