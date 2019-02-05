package sIR_model;

import repast.simphony.context.Context;
import repast.simphony.engine.environment.RunEnvironment;
import repast.simphony.engine.schedule.ScheduledMethod;
import repast.simphony.parameter.Parameters;
import repast.simphony.random.RandomHelper;
import repast.simphony.space.continuous.ContinuousSpace;
import repast.simphony.space.continuous.NdPoint;
import repast.simphony.space.grid.Grid;
import repast.simphony.space.grid.GridPoint;
import repast.simphony.util.ContextUtils;

public class Susceptible extends Actor {
	float infectionRate;

	public Susceptible(ContinuousSpace<Object> space, Grid<Object> grid) {
		super(space, grid);
		Parameters params = RunEnvironment.getInstance().getParameters();
		this.infectionRate =  (Float) params.getValue("infection_rate");
	}

	@ScheduledMethod(start = 1, interval = 1)
	public void step() {
		moveRandom();
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
