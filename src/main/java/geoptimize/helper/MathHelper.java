package geoptimize.helper;

public class MathHelper {
	private MathHelper() {}
	
	public static float clamp(float min, float max, float v) {
		return java.lang.Math.max(min, java.lang.Math.min(max, v));
	}
	
	public static int clamp(int min, int max, int v) {
		return java.lang.Math.max(min, java.lang.Math.min(max, v));
	}
}
