import com.guidedhacking.GHArchitecture;
import com.guidedhacking.GHInput;
import com.guidedhacking.GHMemory;
import com.guidedhacking.GHTools;
import com.sun.glass.events.KeyEvent;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class SauerbratenHack extends Application{
	private static boolean redraw = true;
	private static byte gmValue = 99;
	private static GameMode gameMode;
	private final int moduleBase = Helper.moduleBase;
	
	private static boolean godmode = false;
	private static boolean esp = false; 
	private static boolean aimbot = false; 
	private static boolean rakeMode = false; 
	
	private static Label header;
	private static Label line1;
	private static Label line2;
	private static Label exit;
	private static Label mode;
	
	private static boolean attached = false; 
	static Stage stage;
	protected static Pane pane;
	
	public static void main(String[] args){	
		try{
		   if(GHMemory.openProcess("Cube 2: Sauerbraten")) {
				attached = true;
				GHMemory.setArchitecture(GHArchitecture.Win32);
				launch(args);
		   }
		} catch(Exception e){
			
		}
	}
	
	//deactivate all cheats
	public static void deactivate() {
		if(godmode) {
			godmode = false; 
			GodMode.deActivateGodMode();
		}
		
		if(aimbot) {
			aimbot = false;
			AimbotMode.stopAimbot();
		}
		
		if(esp) {
			esp = false;
			EspMode.stopEsp();
		}
		
		if(rakeMode) {
			rakeMode = false;
			RakeMode.stopRake();
		}
	}
	
	@Override
	public void stop(){
		 //clean up:
        deactivate();
        GHMemory.close();
        stage.close();
        System.exit(0);
	}

	@SuppressWarnings("static-access")
	@Override
	public void start(Stage primaryStage) throws Exception {
		if(attached){
			this.stage = primaryStage;
			pane = new Pane();
			Scene scene = new Scene(pane, GHTools.getGameWidth(), GHTools.getGameHeight());
			System.out.println((GHTools.getGameWidth()-6)+"x"+(GHTools.getGameHeight()-31));
			primaryStage.setTitle("MENU_OVERLAY");
			
			primaryStage.initStyle(StageStyle.TRANSPARENT);
			scene.setFill(Color.TRANSPARENT);
			pane.setStyle("-fx-background-color: rgba(255, 255, 255, 0);");
			primaryStage.setAlwaysOnTop(true);
			
			
			header = new Label("<<GUIDED::HACKING>>");
		    line1 = new Label("visit us:");
		    line2 = new Label("guidedhacking.com");
		    exit = new Label("Exit [X]");
		    mode = new Label("MODE: NORMAL");
		    
		    pane.getChildren().add(header);
		    pane.getChildren().add(line1);
		    pane.getChildren().add(line2);
		    pane.getChildren().add(exit);
		    pane.getChildren().add(mode);
		    
		    header.setLayoutX(30);
		    line1.setLayoutX(30); 
		    line2.setLayoutX(30); 
		    exit.setLayoutX(30); 
		    mode.setLayoutX(30); 
		    
		    header.setLayoutY(30);
		    line1.setLayoutY(60); 
		    line2.setLayoutY(90); 
		    exit.setLayoutY(120); 
		    mode.setLayoutY(150);
		    
		    header.setTextFill(Color.ALICEBLUE);
		    line1.setTextFill(Color.ORANGE);
		    line2.setTextFill(Color.ORANGE); 
		    exit.setTextFill(Color.RED); 
		    mode.setTextFill(Color.GREEN);
		    
		    primaryStage.setX(GHTools.getGameXPos());
		    primaryStage.setY(GHTools.getGameYPos());
		    primaryStage.setScene(scene);
		    primaryStage.sizeToScene();
	        primaryStage.show();
	        
	        AnimationTimer updater = new AnimationTimer(){
	        	@Override
                public void handle(long arg0) {
					if(attached) {
			        	//check if Game Mode changed:
			        	if(gmValue != GHMemory.readByte(moduleBase+0x1E5C38) && gmValue != GHMemory.readByte(moduleBase+0x1E5C28)) {
			        		gmValue = GHMemory.readByte(moduleBase+0x1E5C28);
			        		mode.setText("MODE: NORMAL");
			        		deactivate();
			        		redraw = true;
			        	}
			        	
			        	
			        	stage.setX(GHTools.getGameXPos());
		        		stage.setY(GHTools.getGameYPos());
		        		
			        	//Update:
			        	if(redraw) {
			        		//check the GameMode:
			        		switch(gmValue) {
			        			case (byte)255:
			        				gameMode = GameMode.MainMenu;
			        				header.setText("<<GUIDED::HACKING>>");
			        			    line1.setText("visit us:");
			        			    line2.setText("guidedhacking.com");
			        			break;
			        			case (byte)254:
			        				gameMode = GameMode.Invasion;
			        			    header.setText("Invasion Menu:");
			        			    line1.setText("God Mode [V]");
			        			    line2.setText("Reset [C]");
			        			break;
			        			case (byte)253:
			        				gameMode = GameMode.Campaign;
			        			    header.setText("Campaign Menu:");
		        			        line1.setText("God Mode [V]");
		        			        line2.setText("Reset [C]");
			        			break;
			        			case (byte)22:
			        				gameMode = GameMode.EfficiencyCollect;
		        			    	header.setText("Efficiency Collect:");
		        			    	line1.setText("Rake Mode [V]");
		        			    	line2.setText("Reset [C]");
			        			break;
			        			case (byte)21:
			        				gameMode = GameMode.InstaCollect;
			        				header.setText("InstaCollect:");
			        				line1.setText("Aimbot[V]");
			        				line2.setText("Reset [C]");
			        			break;
			        			case (byte)20:
			        				gameMode = GameMode.Collect;
			        				header.setText("Collect:");
			        				line1.setText("Aimbot[V]");
			        				line2.setText("Reset [C]");
			        			break;
			        			case (byte)19:
			        				gameMode = GameMode.EfficiencyHold;
			        				header.setText("EfficiencyHold:");
			        				line1.setText("Aimbot[V]");
			        				line2.setText("Reset [C]");
			        			break;
			        			case (byte)18:
			        				gameMode = GameMode.EfficiencyCollect;
			        				header.setText("EfficenecyCollect:");
			        				line1.setText("Aimbot[V]");
			        				line2.setText("Reset [C]");
			        			break;
			        			case (byte)17:
			        				gameMode = GameMode.EfficencyCTF;
			        				header.setText("EfficiencyCTF:");
			        				line1.setText("Aimbot[V]");
			        				line2.setText("Reset [C]");
			        			break;
			        			case (byte)16:
			        				gameMode = GameMode.InstaHold;
			        				header.setText("InstaHODL!:");
			        				line1.setText("ESP[V]");
			        				line2.setText("Reset [C]");
			        			break;
			        			case (byte)15:
			        				gameMode = GameMode.Hold;
			        				header.setText("Hold:");
			        				line1.setText("Aimbot[V]");
			        				line2.setText("Reset [C]");
			        			break;
			        			case (byte)14:
			        				gameMode = GameMode.InstaProtect;
			        				header.setText("InstaProtect:");
			        				line1.setText("Aimbot[V]");
			        				line2.setText("Reset [C]");
			        			break;
			        			case (byte)13:
			        				gameMode = GameMode.Protect;
			        				header.setText("Protect:");
			        				line1.setText("Aimbot[V]");
			        				line2.setText("Reset [C]");
			        			break;
			        			case (byte)12:
			        				gameMode = GameMode.InstaCTF;
			        				header.setText("InstaCTF");
			        				line1.setText("Aimbot & ESP[V]");
			        				line2.setText("Reset [C]");
			        			break;
			        			case (byte)11:
			        				gameMode = GameMode.CTF;
			        				header.setText("CTF:");
			        				line1.setText("Aimbot & ESP[V]");
			        				line2.setText("Return to Home [C]");
			        			break;
			        			case (byte)10:
			        				gameMode = GameMode.RegenCapture;
			        				header.setText("RegenCapture");
		        					line1.setText("Aimbot & ESP[V]");
		        					line2.setText("Return to Home [C]");
			        			break;
			        			case (byte)9:
			        				gameMode = GameMode.Capture;
			        				header.setText("Capture:");
			        				line1.setText("Aimbot & ESPx[V]");
			        				line2.setText("Return to Home [C]");
			        			break;
			        			case (byte)8:
			        				gameMode = GameMode.TacticsTeam;
			        				header.setText("TactictsTeam:");
		        					line1.setText("Aimbot[V]");
		        					line2.setText("Reset [C]");
			        			break;
			        			case (byte)7:
			        				gameMode = GameMode.Tactics;
			        				header.setText("Tactics:");
			        				line1.setText("Aimbot[V]");
			        				line2.setText("Reset [C]");
			        			break;
			        			case (byte)6:
			        				gameMode = GameMode.EfficiencyTeam;
			        				header.setText("EfficencyTeam:");
			        				line1.setText("Aimbot[V]");
			        				line2.setText("Reset [C]");
			        			break;
			        			case (byte)5:
			        				gameMode = GameMode.Efficiency;
			        				header.setText("Efficiency:");
			        				line1.setText("Aimbot[V]");
			        				line2.setText("Reset [C]");
			        			break;
			        			case (byte)4:
			        				gameMode = GameMode.InstagibTeam;
			        			    header.setText("Instagib Team:");
		    			    	    line1.setText("Aimbot[V]");
		    			    	    line2.setText("Reset [C]");
			        			break;
			        			case (byte)3:
			        				gameMode = GameMode.Instagib;
			        				header.setText("Instagib:");
			        				line1.setText("Aimbot & ESP[V]");
			        				line2.setText("Reset [C]");
			        			break;
			        			case (byte)2:
			        				gameMode = GameMode.TeamPlay;
			        				header.setText("Instagib Team:");
			        				line1.setText("Aimbot[V]");
			        				line2.setText("Reset [C]");
			        			break;
			        			case (byte)1:
			        				gameMode = GameMode.CoopEdit;
			        				header.setText("Noob Edit:");
			        				line1.setText("Dont make me");
			        				line2.setText("watch this!!");
			        				exit.setText("PLEASE[X]");
			        				
			        			break;
			        			case (byte)0:
			        				gameMode = GameMode.FFA;
			        				header.setText("FFA:");
			        				line1.setText("Aimbot[V]");
			        				line2.setText("Reset [C]");
			        			break;
			        			
			        		}
			        		
		    				if(gameMode != GameMode.CoopEdit) {
		    					exit.setText("Exit [x]");
		    				}
		    				redraw = false;
			        	}
			        	
			        	if(GHInput.getKeyDown(KeyEvent.VK_V)) {
			        		switch(gameMode) {
			        			case CoopEdit:
			        			case MainMenu:
			        				header.setText("Made by Erarnitox");
			        				line1.setText("join the 31337:");
			        				line2.setText("guidedhacking.com");
			        			break;
			        			
			        			case Invasion:
			        			case Campaign:
			        				if(!godmode) {
			        					godmode = true;
			        					mode.setText("MODE: GOD");
			        					GodMode.activateGodMode();
			        				}
			        			break;
			        			
			        			case EfficiencyCollect:
			        				if(!rakeMode) {
			        					rakeMode = true;
			        					mode.setText("MODE: RAKE");
			        					RakeMode.startRake();
			        				}
			        			break;
			        			case InstaHold:
			        			case Hold:
			        				if(!esp) {
			        					esp = true;
			        					EspMode.startEsp(gameMode);
			        					mode.setText("MODE: ESP");
			        				}
			        			break;
			        			
			        			case RegenCapture:
			        			case Capture:
			        			case CTF:
			        			case Instagib:
			        			case InstaCTF:
			        				if(!aimbot) {
			        					aimbot = true;
			        					AimbotMode.startAimbot(gameMode);
			        				}
			        				if(!esp) {
			        					esp = true;
			        					EspMode.startEsp(gameMode);
			        				}
			        				mode.setText("MODE: AIMBOT & ESP");
			        			break;
			        			
			        			default:
			        				if(!aimbot) {
			        					aimbot = true;
			        					mode.setText("MODE: AIMBOT");
			        					AimbotMode.startAimbot(gameMode);
			        				}
			        			break;
			        		}
			        	}
			        	
			        	if(GHInput.getKeyDown(KeyEvent.VK_C)) {
			        		switch(gameMode) {
			        			case CoopEdit:
			        			case MainMenu:
			        				header.setText("The cake");
			        				line1.setText("really IS");
			        				line2.setText("a LIE");
			        			break;
			        			
			        			case Invasion:
			        			case Campaign:
			        				if(godmode) {
			        					godmode = false;
			        					mode.setText("MODE: NORMAL");
			        					GodMode.deActivateGodMode();
			        				}
			        			break;
			        			
			        			case EfficiencyCollect:
			        				if(rakeMode) {
			        					rakeMode = false;
			        					RakeMode.stopRake();
			        					mode.setText("MODE: NORMAL");
			        				}
			        			break;
			        			
			        			case InstaHold:
			        			case Hold:
			        				if(esp) {
			        					esp = false;
			        					EspMode.stopEsp();
			        					mode.setText("MODE: NORMAL");
			        				}
			        			break;
			        			
			        			case CTF:
			        			case RegenCapture:
			        			case Capture:
			        			case Instagib:
			        			case InstaCTF:
			        				if(aimbot) {
			        					aimbot = false;
			        					AimbotMode.stopAimbot();
			        				}
			        				if(esp) {
			        					esp = false;
			        					EspMode.stopEsp();
			        				}
			        				mode.setText("MODE: NORMAL");
			        			break;
			        			
			        			default:
			        				if(aimbot) {
			        					aimbot = false;
			        					mode.setText("MODE: NORMAL");
			        					AimbotMode.stopAimbot();
			        				}
			        			break;
			        		}
			        	}
			        	
			        	//Safety escape:
			        	if(GHInput.getKeyDown(KeyEvent.VK_X)) {
			        		attached = false;
			        	}    	
			        }else{
			        	Platform.exit();
			        }
		        }
	        };
	        updater.start();
		}
	}
}

