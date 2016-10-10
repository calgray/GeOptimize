package geoptimize.pso;

import com.nativelibs4java.opencl.*;

/**
 * Assign the heatmap and ServiceNode locations, and return the fitness
 * 
 * @author Callan
 *
 */
public interface PSOFitnessTask {

	public double runTask();

}
