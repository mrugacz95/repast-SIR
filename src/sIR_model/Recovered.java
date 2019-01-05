package sIR_model;

import repast.simphony.engine.schedule.ScheduledMethod;
import repast.simphony.space.continuous.ContinuousSpace;
import repast.simphony.space.continuous.NdPoint;
import repast.simphony.space.grid.Grid;
import repast.simphony.space.grid.GridPoint;

public class Recovered {

	ContinuousSpace<Object> space;
	Grid<Object> grid;
	
	public Recovered(ContinuousSpace<Object> space, Grid<Object> grid) {
		this.grid = grid;
		this.space = space;
	}
	
	
	@ScheduledMethod(start = 1, interval = 1)
	public void step() {
		GridPoint pt = grid.getLocation(this);
		moveRandom();
	}
	
	public void moveRandom() {
		NdPoint ndPoint = space.getLocation(this);
		space.moveByVector(this, 1, Math.random() * 2 * 3.1415f, 0);
		ndPoint = space.getLocation(this);
		grid.moveTo(this, (int) ndPoint.getX(), (int) ndPoint.getY());
	}
}
