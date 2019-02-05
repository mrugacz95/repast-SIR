package sIR_model;

import repast.simphony.engine.schedule.ScheduledMethod;
import repast.simphony.space.continuous.ContinuousSpace;
import repast.simphony.space.continuous.NdPoint;
import repast.simphony.space.grid.Grid;
import repast.simphony.space.grid.GridPoint;

public class Recovered extends Actor {
	public Recovered(ContinuousSpace<Object> space, Grid<Object> grid) {
		super(space, grid);
	}
	
	
	@ScheduledMethod(start = 1, interval = 1)
	public void step() {
		moveRandom();
	}
}
