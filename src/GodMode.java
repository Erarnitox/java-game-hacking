import com.guidedhacking.*;

public class GodMode {
	private static final int moduleBase = Helper.moduleBase;
	private static Player player = new Player();
	private static int bkpHealth;
	private static int bkpPistolAmmo;
	private static int bkpRifleAmmo;
	private static int bkpRPGAmmo;
	private static int bkpChainSawAmmo;
	private static int bkpShotgunAmmo;
	private static int bkpGrenadeAmmo; 
	private static int bkpMinigunAmmo; 
	private static byte bkpAmmoDecOpcode;
	private static byte[] bkpHealthDecOpcodes;
	private static byte[] bkpCooldownOpcodes;
	private static byte[] bkpQuadOpcodes;
	private static byte[] bkpDamageOpcodes;
	
	private static boolean active = false; 
	
	public static void activateGodMode() {
		if(!active) {
			active = true;
			
			//back up values: 
			bkpHealth = player.getHealth();
		
			bkpRifleAmmo = player.getRifleAmmo();
			bkpPistolAmmo = player.getPistolAmmo();
			bkpRPGAmmo = player.getRPGAmmo();
			bkpShotgunAmmo = player.getShotgunAmmo();
			bkpGrenadeAmmo = player.getGrenadeAmmo();
			bkpMinigunAmmo = player.getMinigunAmmo();
			bkpChainSawAmmo = player.getChainsawAmmo();
		
			//set new values:
			player.setChainsawAmmo(69);
			player.setHealth(666);
			player.setMinigunAmmo(1337);
			player.setPistolAmmo(88);
			player.setShotgunAmmo(42);
			player.setGrenadeAmmo(1997);
			player.setRifleAmmo(80085);
			player.setRPGAmmo(31337);
			
			//set other:
			player.activateQuadDamage();
			player.setShieldValue(101);
				
			//back opcodes: 
			bkpAmmoDecOpcode = GHMemory.readByte(moduleBase+0x18EAD6);
			bkpDamageOpcodes = GHMemory.readByteArray(moduleBase+0x858B1, 7);
			bkpHealthDecOpcodes = GHMemory.readByteArray(moduleBase+0x17EE51, 6);
			bkpCooldownOpcodes = GHMemory.readByteArray(moduleBase+0x18EE37, 6);
			bkpQuadOpcodes = GHMemory.readByteArray(moduleBase+0x17CE9F, 6);
			
			
			//set new opcodes
			GHMemory.writeByte((byte)0x90, moduleBase+0x18EAD6);//ammo dec
			GHMemory.write(new byte[] {(byte)0xBE,(byte)0x39,(byte)0x05,(byte)0x09,(byte)0x00,(byte)0x90,(byte)0x90}, moduleBase+0x858B1);//leet damage
			GHMemory.write(new byte[] {(byte)0x90,(byte)0x90,(byte)0x90,(byte)0x90,(byte)0x90,(byte)0x90}, moduleBase+0x18EE37); //ultra rapid fire
			GHMemory.write(new byte[] {(byte)0x90,(byte)0x90,(byte)0x90,(byte)0x90,(byte)0x90,(byte)0x90}, moduleBase+0x17EE51); //health dec
			GHMemory.write(new byte[] {(byte)0x90,(byte)0x90,(byte)0x90,(byte)0x90,(byte)0x90,(byte)0x90}, moduleBase+0x17CE9F); //quad dec
			GHMemory.writeByte((byte)0x14, moduleBase+0x18EB7C);//load addy with 0 in kickback
		}
	}
	
	public static void deActivateGodMode() {
		if(active) {
			active = false; 
			//reset old values:
			player.setChainsawAmmo(bkpChainSawAmmo);
			player.setHealth(bkpHealth);
			player.setMinigunAmmo(bkpMinigunAmmo);
			player.setPistolAmmo(bkpPistolAmmo);
			player.setShotgunAmmo(bkpShotgunAmmo);
			player.setGrenadeAmmo(bkpGrenadeAmmo);
			player.setRifleAmmo(bkpRifleAmmo);
			player.setRPGAmmo(bkpRPGAmmo);
			
			//set other:
			player.deActivateQuadDamage();
			player.setShieldValue(0);
			
			//reset old opcodes
			GHMemory.writeByte(bkpAmmoDecOpcode, moduleBase+0x18EAD6);//ammo
			GHMemory.write(bkpDamageOpcodes, moduleBase+0x858B1); //damage
			GHMemory.write(bkpCooldownOpcodes, moduleBase+0x18EE37); //ultra rapid fire
			GHMemory.write(bkpHealthDecOpcodes, moduleBase+0x17EE51); //health dec
			GHMemory.write(bkpQuadOpcodes, moduleBase+0x17CE9F); //quad dec	
			GHMemory.writeByte((byte)0x10, moduleBase+0x18EB7C); //load addy with kickback force
		}
	}

}
