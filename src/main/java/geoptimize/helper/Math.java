package geoptimize.helper;

public class Math {
	private Math() {}
	
	public static float clamp(float min, float max, float v) {
		return java.lang.Math.max(min, java.lang.Math.min(max, v));
	}
}
