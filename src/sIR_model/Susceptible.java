package sIR_model;

import repast.simphony.context.Context;
import repast.simphony.engine.schedule.ScheduledMethod;
import repast.simphony.random.RandomHelper;
import repast.simphony.space.continuous.ContinuousSpace;
import repast.simphony.space.continuous.NdPoint;
import repast.simphony.space.grid.Grid;
import repast.simphony.space.grid.GridPoint;
import repast.simphony.util.ContextUtils;

public class Susceptible {
	ContinuousSpace<Object> space;
	Grid<Object> grid;
	float infectionRate;

	public Susceptible(ContinuousSpace<Object> space, Grid<Object> grid, float infectionRate) {
		this.grid = grid;
		this.space = space;
		this.infectionRate = infectionRate;
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
	
	public void resist() {
		if(RandomHelper.nextDouble() < infectionRate) {
			// infected :c
			GridPoint pt = grid.getLocation(this);
			NdPoint spacePt = space.getLocation(this);
			
			Context<Object> context = ContextUtils.getContext(this);
			context.remove(this);
			Infectious infectious = new Infectious(space, grid);
			context.add(infectious);
			space.moveTo(infectious, spacePt.getX(), spacePt.getY());
			grid.moveTo(infectious, pt.getX(), pt.getY());
		}
	}
}
