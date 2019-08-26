import com.guidedhacking.*;

public class Player{
	
	 private final int moduleBase = Helper.moduleBase; 
	 
	 //General Info:
	 private GHPointer teamPtr = new GHPointer(moduleBase+0x216454,0x354);
	 private GHPointer healthPtr  = new GHPointer(moduleBase+0x216454,0x15c);
	 
     private GHPointer shieldOnePtr  = new GHPointer(moduleBase+0x216454,0x168);
     private GHPointer shieldTwoPtr  = new GHPointer(moduleBase+0x216454,0x164);
     private GHPointer shieldThreePtr  = new GHPointer(moduleBase+0x216454,0x160);
     
     private GHPointer posXPtr = new GHPointer(moduleBase+0x216454,0x30);
     private GHPointer posYPtr = new GHPointer(moduleBase+0x216454,0x34);
     private GHPointer posZPtr = new GHPointer(moduleBase+0x216454,0x38);
     
     private GHPointer rotXPtr = new GHPointer(moduleBase+0x216454, 0x40);
     private GHPointer rotYPtr = new GHPointer(moduleBase+0x216454, 0x3c);
     
     //Weapon Ammo:
     private GHPointer chainSawAmmoPtr = new GHPointer(moduleBase+0x216454,0x178);
     private GHPointer shotGunAmmoPtr = new GHPointer(moduleBase+0x216454,0x17C);
     private GHPointer miniGunAmmoPtr = new GHPointer(moduleBase+0x216454,0x180);
     private GHPointer rpgAmmoPtr = new GHPointer(moduleBase+0x216454,0x184);
     private GHPointer huntingRifleAmmoPtr = new GHPointer(moduleBase+0x216454,0x188);
     private GHPointer grenadeAmmoPtr = new GHPointer(moduleBase+0x216454,0x18C);
     private GHPointer pistolAmmoPtr = new GHPointer(moduleBase+0x216454,0x190);
     
     //Misc:
     private GHPointer weaponCooldownPtr = new GHPointer(moduleBase+0x216454,0x174);
     private GHPointer quadDamagePtr = new GHPointer(moduleBase+0x216454,0x16C);
     private GHPointer isShooting = new GHPointer(moduleBase+0x216454,0x1E0);
     

     public Position getPosition() {
     	float x = GHMemory.readFloat(GHMemory.getObjectAddress(posXPtr));
     	float y = GHMemory.readFloat(GHMemory.getObjectAddress(posYPtr));
     	float z = GHMemory.readFloat(GHMemory.getObjectAddress(posZPtr));
     	
     	return new Position(x,y,z);
     }
     
     public float getDistance(Position x){
    	 Position here = this.getPosition();
    	 return (float) Math.sqrt(Math.pow((here.x-x.x),2) + Math.pow((here.y-x.y),2) + Math.pow((here.z-x.z),2));
     }
     
     public void setPosition(Position pos) {
     	setPosition(pos.x, pos.y, pos.z);
     }
     
     public void setPosition(float x, float y, float z) {
     	GHMemory.writeFloat(x,GHMemory.getObjectAddress(posXPtr));
     	GHMemory.writeFloat(y,GHMemory.getObjectAddress(posYPtr));
     	GHMemory.writeFloat(z,GHMemory.getObjectAddress(posZPtr));
     }
     
     public Rotation getRotation() {
      	float x = GHMemory.readFloat(GHMemory.getObjectAddress(rotXPtr));
      	float y = GHMemory.readFloat(GHMemory.getObjectAddress(rotYPtr));
      	
      	return new Rotation(x,y);
      }
     
     public void setRotation(Rotation rot) {
      	setRotation(rot.x, rot.y);
      }
      
      public void setRotation(float x, float y) {
      	GHMemory.writeFloat(x,GHMemory.getObjectAddress(rotXPtr));
      	GHMemory.writeFloat(y,GHMemory.getObjectAddress(rotYPtr));
      }
     
     public int getHealth() {
     	return GHMemory.readInt(GHMemory.getObjectAddress(healthPtr));
     }
     
     public void setHealth(int health) {
    	 GHMemory.writeInt(health,GHMemory.getObjectAddress(healthPtr));
     }
     
     public int getPistolAmmo() {
    	 return GHMemory.readInt(GHMemory.getObjectAddress(pistolAmmoPtr));
     }
     
     public int getRPGAmmo() {
    	 return GHMemory.readInt(GHMemory.getObjectAddress(rpgAmmoPtr));
     }
     
     public int getChainsawAmmo() {
    	 return GHMemory.readInt(GHMemory.getObjectAddress(chainSawAmmoPtr));
     }
     
     public int getRifleAmmo() {
    	 return GHMemory.readInt(GHMemory.getObjectAddress(huntingRifleAmmoPtr));
     }
     
     public int getShotgunAmmo() {
    	 return GHMemory.readInt(GHMemory.getObjectAddress(shotGunAmmoPtr));
     }
     
     public int getMinigunAmmo() {
    	 return GHMemory.readInt(GHMemory.getObjectAddress(miniGunAmmoPtr));
     }
     
     public int getGrenadeAmmo() {
    	 return GHMemory.readInt(GHMemory.getObjectAddress(grenadeAmmoPtr));
     }
     
     public void setPistolAmmo(int ammo) {
    	 GHMemory.writeInt(ammo,GHMemory.getObjectAddress(pistolAmmoPtr));
     }
     
     public void setRPGAmmo(int ammo) {
    	 GHMemory.writeInt(ammo,GHMemory.getObjectAddress(rpgAmmoPtr));
     }
     
     public void setChainsawAmmo(int ammo) {
    	 GHMemory.writeInt(ammo,GHMemory.getObjectAddress(chainSawAmmoPtr));
     }
     
     public void setRifleAmmo(int ammo) {
    	 GHMemory.writeInt(ammo,GHMemory.getObjectAddress(huntingRifleAmmoPtr));
     }
     
     public void setShotgunAmmo(int ammo) {
    	 GHMemory.writeInt(ammo,GHMemory.getObjectAddress(shotGunAmmoPtr));
     }
     
     public void setMinigunAmmo(int ammo) {
    	 GHMemory.writeInt(ammo,GHMemory.getObjectAddress(miniGunAmmoPtr));
     }
     
     public void setGrenadeAmmo(int ammo) {
    	 GHMemory.writeInt(ammo,GHMemory.getObjectAddress(grenadeAmmoPtr));
     }
     
     public void resetCooldwon() {
    	 GHMemory.writeInt(0,GHMemory.getObjectAddress(weaponCooldownPtr));
     }
     
     public void activateQuadDamage() {
    	 GHMemory.writeInt(Integer.MAX_VALUE,GHMemory.getObjectAddress(quadDamagePtr));
     }
     
     public void deActivateQuadDamage() {
    	 GHMemory.writeInt(0,GHMemory.getObjectAddress(quadDamagePtr));
     }
      
     public void shoot() {
    	 GHMemory.writeByte((byte)1, GHMemory.getObjectAddress(isShooting));
     }
     
     public void stopShooting() {
    	 GHMemory.writeByte((byte)0, GHMemory.getObjectAddress(isShooting));
     }
     
     public void setShieldValue(int shield) {
    	 GHMemory.writeInt(shield,GHMemory.getObjectAddress(shieldOnePtr));
    	 GHMemory.writeInt(shield,GHMemory.getObjectAddress(shieldTwoPtr));
    	 GHMemory.writeInt(shield,GHMemory.getObjectAddress(shieldThreePtr));
     }
     
     public String getTeam() {
     	return GHMemory.readString(GHMemory.getObjectAddress(teamPtr),5);
     }
     
     public boolean isEnemy(Enemy player) {
    	 return !(getTeam().contains(player.getTeam()));
     }
     
}
