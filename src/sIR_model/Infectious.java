package sIR_model;

import java.util.ArrayList;
import java.util.List;

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

public class Infectious {
	ContinuousSpace<Object> space;
	Grid<Object> grid;
	float recoveryRate;
	
	
	public Infectious(ContinuousSpace<Object> space, Grid<Object> grid) {
		this.grid = grid;
		this.space = space;

		Parameters params = RunEnvironment.getInstance().getParameters();
		this.recoveryRate =  (Float) params.getValue("recover_rate");
	}
	
	@ScheduledMethod(start = 1, interval = 1)
	public void step() {
		GridPoint pt = grid.getLocation(this);
		moveRandom();
		infect();
		recover();
	}
	
	public void moveRandom() {
		NdPoint ndPoint = space.getLocation(this);
		space.moveByVector(this, 1, Math.random() * 2 * 3.1415f, 0);
		ndPoint = space.getLocation(this);
		grid.moveTo(this, (int) ndPoint.getX(), (int) ndPoint.getY());

	}
	
	public void infect() {
		GridPoint pt = grid.getLocation(this);
		List<Susceptible> susceptibles = new ArrayList<Susceptible>();
		for (Object obj : grid.getObjectsAt(pt.getX(), pt.getY())) {
			if (obj instanceof Susceptible) {
				susceptibles.add((Susceptible) obj);
			}
		}
		
		for(Susceptible s: susceptibles) {
			s.resist();
		}
	}
	
	public void recover() {
		if ( RandomHelper.nextDouble() < recoveryRate) {
			GridPoint pt = grid.getLocation(this);
			NdPoint spacePt = space.getLocation(this);
			
			Context<Object> context = ContextUtils.getContext(this);
			context.remove(this);
			Recovered recovered = new Recovered(space, grid);
			context.add(recovered);
			space.moveTo(recovered, spacePt.getX(), spacePt.getY());
			grid.moveTo(recovered, pt.getX(), pt.getY());
		
		}
	}
}
