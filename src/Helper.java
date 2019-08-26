import com.guidedhacking.GHMemory;

public class Helper {	
	public static final int moduleBase = 0x1330000;
	public static boolean WorldToScreen(Position worldPos, Position2D screenPos, int windowWidth, int windowHeight) {
		float[] matrix = new float[16];
		
		//load in view matrix:
		for(int i=0; i<16; i++) {
			matrix[i] = GHMemory.readFloat(moduleBase+0x297AF0+(i*0x4));
		}

	    float x = worldPos.x*matrix[0] + worldPos.y*matrix[4] + worldPos.z*matrix[8] + matrix[12];
	    float y = worldPos.x*matrix[1] + worldPos.y*matrix[5] + worldPos.z*matrix[9] + matrix[13];
	    float w = worldPos.x*matrix[3] + worldPos.y*matrix[7] + worldPos.z*matrix[11] + matrix[15];

	    if (w < 0.1f) {
	    	return false;
	    }
	    
	    else {
	    	x /= w;
	    	y /= w;
	    	screenPos.x = (windowWidth / 2 * x) + (x + windowWidth / 2);
	    	screenPos.y = -(windowHeight / 2 * y) + (y + windowHeight / 2);
	    	return true;
	    }
	}
}
