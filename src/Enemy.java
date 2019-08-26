import com.guidedhacking.GHMemory;
import com.guidedhacking.GHPointer;

public class Enemy {
	private GHPointer healthPtr;
    private GHPointer posYPtr;
    private GHPointer posXPtr;
    private GHPointer posZPtr;
    private GHPointer canBeSeenPtr;
    private GHPointer teamPtr;
    private int canBeSeen;
    private String name;
    
    public Enemy(int enemyPointer) {
    	 healthPtr  = new GHPointer(enemyPointer,0x15c);
         posXPtr = new GHPointer(enemyPointer,0x30);
         posYPtr = new GHPointer(enemyPointer,0x34);
         posZPtr = new GHPointer(enemyPointer,0x38);
         canBeSeenPtr = new GHPointer(enemyPointer,0x570);
         teamPtr = new GHPointer(enemyPointer,0x354);
         canBeSeen = (int)GHMemory.getObjectAddress(canBeSeenPtr);
         GHPointer namePtr = new GHPointer(enemyPointer,0x458);
         name = GHMemory.readString(GHMemory.getObjectAddress(namePtr), 4);
    }
    
    public Position getPosition() {
    	float x = GHMemory.readFloat(GHMemory.getObjectAddress(posXPtr));
    	float y = GHMemory.readFloat(GHMemory.getObjectAddress(posYPtr));
    	float z = GHMemory.readFloat(GHMemory.getObjectAddress(posZPtr));
    	
    	return new Position(x,y,z);
    }
    
    public void setPosition(Position pos) {
    	setPosition(pos.x, pos.y, pos.z);
    }
    
    public void setPosition(float x, float y, float z) {
    	GHMemory.writeFloat(x,GHMemory.getObjectAddress(posXPtr));
    	GHMemory.writeFloat(y,GHMemory.getObjectAddress(posYPtr));
    	GHMemory.writeFloat(z,GHMemory.getObjectAddress(posZPtr));
    }
    
    public int getHealth() {
    	return GHMemory.readInt(GHMemory.getObjectAddress(healthPtr));
    }
    public boolean isAlive() {
    	return (getHealth()>0 && getHealth()<1000);
    }
    
    public boolean canBeSeen() {
    	return (GHMemory.readByte(canBeSeen)> 0);
    }
    
    public String getTeam() {
    	return GHMemory.readString(GHMemory.getObjectAddress(teamPtr),5);
    }
    public String getName() {
    	return name;
    }
}
