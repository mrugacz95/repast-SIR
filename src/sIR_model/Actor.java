package sIR_model;

import repast.simphony.engine.environment.RunEnvironment;
import repast.simphony.parameter.Parameters;
import repast.simphony.space.continuous.ContinuousSpace;
import repast.simphony.space.continuous.NdPoint;
import repast.simphony.space.grid.Grid;

public class Actor {
	protected ContinuousSpace<Object> space;
	protected Grid<Object> grid;
	
	public Actor(ContinuousSpace<Object> space, Grid<Object> grid) {
		this.grid = grid;
		this.space = space;
	}
	
	protected void moveRandom() {
		NdPoint ndPoint = space.getLocation(this);
		space.moveByVector(this, 1, Math.random() * 2 * 3.1415f, 0);
		ndPoint = space.getLocation(this);
		grid.moveTo(this, (int) ndPoint.getX(), (int) ndPoint.getY());

	}
}
