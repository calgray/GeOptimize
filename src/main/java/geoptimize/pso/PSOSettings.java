package geoptimize.pso;

import java.awt.Rectangle;

import geoptimize.pso.fitness.FitnessType;
import geoptimize.pso.fitness.PSOFitnessFunction;

public class PSOSettings {
	
	//Solution Settings
	public int nodes;
	public int range;
	
	//Simulation/Particle Settings
	public int particles;
	public Rectangle region;
	public FitnessType fitnessType;

	public float localBestWeight = 0.3f;
	public float globalBestWeight = 0.1f;
	public float inertia = 1f;
	
}
