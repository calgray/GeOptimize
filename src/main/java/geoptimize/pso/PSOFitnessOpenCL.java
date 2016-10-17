
package geoptimize.pso;

import java.awt.Rectangle;
import java.nio.ByteOrder;

import org.bridj.Pointer;

import com.nativelibs4java.opencl.CLBuffer;
import com.nativelibs4java.opencl.CLContext;
import com.nativelibs4java.opencl.CLEvent;
import com.nativelibs4java.opencl.CLKernel;
import com.nativelibs4java.opencl.CLMem.Usage;
import com.nativelibs4java.opencl.CLProgram;
import com.nativelibs4java.opencl.CLQueue;
import com.nativelibs4java.opencl.JavaCL;
import com.nativelibs4java.util.IOUtils;
import com.nativelibs4java.opencl.util.OpenCLType;
import com.nativelibs4java.opencl.util.ReductionUtils;
import com.nativelibs4java.opencl.util.ReductionUtils.Reductor;


/**
 * Perform fitness on a single node.
 * Alternatively, could accept a collection of PSOParticles
 * @author Callan
 *
 */
public class PSOFitnessOpenCL {
	
	CLContext context;
	CLQueue queue;
	CLProgram program;
	CLKernel kernel;
	Reductor<Float> reductor;
	
	CLBuffer<Float> a;
	CLBuffer<Float> b;
	CLBuffer<Float> output;
	
	float[] populationData;
	int width;
	int height;
	int size;
	
	public PSOFitnessOpenCL(float[] populationData, int nNodes, Rectangle region) {
		context = JavaCL.createBestContext();
		queue = context.createDefaultQueue();
		ByteOrder byteOrder = context.getByteOrder();
		
		//Data A
		Pointer<Float> pa = Pointer.allocateFloats(populationData.length).order(byteOrder);
		for(int i = 0; i < populationData.length; i++) {
			pa.set(i, populationData[i]);
		}
		a = context.createBuffer(Usage.Input, pa);
		
		//Data B (x, y, radius)
		Pointer<Float> pb = Pointer.allocateFloats(nNodes * 3);
		b = context.createBuffer(Usage.Input, pb, false);
		
		//Output
		output = context.createFloatBuffer(Usage.Output, 1);
			
		//Program
		
		
		//Distance Kernel
		//String src = IOUtils.readText();
		
		//Reduce Kernel
		reductor = ReductionUtils.createReductor(
				context, 
				ReductionUtils.Operation.Add, 
				OpenCLType.Float, 
				populationData.length);
		
	}
	
	public float calculateFitness(PSOSolution solution) {
		
		//load solution into b
		
		kernel.setArgs(a, b, output);
		CLEvent addEvt = kernel.enqueueNDRange(queue, new int[] { size });
		
		return 0;
	}
}
