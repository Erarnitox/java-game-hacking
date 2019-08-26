import com.guidedhacking.GHMemory;
import com.guidedhacking.GHPointer;
import com.guidedhacking.GHTools;
import javafx.animation.AnimationTimer;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class RakeThread extends AnimationTimer {
	private boolean stop = false;
	private static Pane EspOverlay;
	private boolean initialized = false;
	private Enemy[] players;
	private final int moduleBase = 0x1330000;
	private int[] c; 
	
	ImageView[] RakeFaces;
	GHPointer listPointer;
	long playerList;
	Player myPlayer;
	Position enemyWorldPos;
	Position2D enemyScreenPos;
	
	public void stopRake() {
		stop = true;
		for(ImageView i : RakeFaces){
			if(i != null)
			i.setVisible(false);
		}
	}

	@Override
	public void handle(long now) {
		if(!stop) {
			try {
				if(!initialized){
					EspOverlay = SauerbratenHack.pane;
					Image rakeFace = new Image("rake.png");
					players = new Enemy[GHMemory.readInt(moduleBase+0x29CD3C)-1];
					RakeFaces = new ImageView[players.length];
					
					listPointer = new GHPointer(moduleBase+0x29CD34,0x4);
					playerList = GHMemory.getObjectAddress(listPointer);
					myPlayer = new Player();
					enemyScreenPos = new Position2D(0,0);
					c = new int[players.length];
					
					for(int i=0; i < players.length; i++) {
						players[i] = new Enemy((int)playerList+(i*4));
						c[i] = 50;
						
						RakeFaces[i] = new ImageView (rakeFace);
						EspOverlay.getChildren().add(RakeFaces[i]);
						
					}
					initialized = true; 
				}
				
				if(players.length == (GHMemory.readInt(moduleBase+0x29CD3C)-1) && !stop) {
					for(int i=0; i < players.length; i++) {
						if(stop) {
							break;
						}
						
							if(players[i].canBeSeen()) {
								c[i]=0;
								enemyWorldPos = players[i].getPosition();
								enemyWorldPos.z -= 3;
								
								if(players[i].isAlive() && Helper.WorldToScreen(enemyWorldPos, enemyScreenPos, GHTools.getGameWidth(), GHTools.getGameHeight()) && !stop){
									if(enemyScreenPos.x > 0 && enemyScreenPos.y > 0) {
										 RakeFaces[i].setVisible(true);
										 float dist = myPlayer.getDistance(enemyWorldPos);
										 RakeFaces[i].setFitHeight(2000/dist);
										 RakeFaces[i].setFitWidth(2000/dist);
										 RakeFaces[i].setX(enemyScreenPos.x-(RakeFaces[i].getFitWidth()/2));
										 RakeFaces[i].setY(enemyScreenPos.y-(RakeFaces[i].getFitHeight()/2));
									}
								}else{
									RakeFaces[i].setVisible(false);
								}
							}else{
								c[i]++;
								if(c[i] > 50){
									RakeFaces[i].setVisible(false);
									c[i]=0;
								}
							}
					}
				}
			}
			catch(Exception e) {
			 //exception handeling
			}	
		}	
	}
}
