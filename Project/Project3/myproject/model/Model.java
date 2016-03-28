package myproject.model;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;
import java.util.Observable;

import myproject.util.Animator;

/**
 * An example to model for a simple visualization.
 * The model contains roads organized in a matrix.
 * See {@link #Model(AnimatorBuilder, int, int)}.
 */
public class Model extends Observable {
	private List<Agent> agents;
	private Animator animator;
	private boolean disposed;
	private double time;

	/** Creates a model to be visualized using the <code>builder</code>.
	 *  If the builder is null, no visualization is performed.
	 *  The number of <code>rows</code> and <code>columns</code>
	 *  indicate the number of {@link Light}s, organized as a 2D
	 *  matrix.  These are separated and surrounded by horizontal and
	 *  vertical {@link Road}s.  For example, calling the constructor with 1
	 *  row and 2 columns generates a model of the form:
	 *  <pre>
	 *     |  |
	 *   --@--@--
	 *     |  |
	 *  </pre>
	 *  where <code>@</code> is a {@link Light}, <code>|</code> is a
	 *  vertical {@link Road} and <code>--</code> is a horizontal {@link Road}.
	 *  Each road has one {@link Car}.
	 *
	 *  <p>
	 *  The {@link AnimatorBuilder} is used to set up an {@link
	 *  Animator}.
	 *  {@link AnimatorBuilder#getAnimator()} is registered as
	 *  an observer of this model.
	 *  <p>
	 */
	public Model(AnimatorBuilder builder, int rows, int columns) {
		if (rows < 0 || columns < 0 || (rows == 0 && columns == 0)) {
			throw new IllegalArgumentException();
		}
		if (builder == null) {
			builder = new NullAnimatorBuilder();
		}
		this.agents = new ArrayList<Agent>();
		setup(builder, rows, columns);
		this.animator = builder.getAnimator();
		super.addObserver(animator);
	}

	/**
	 * Run the simulation for <code>duration</code> model seconds.
	 */
	public void run(double duration) {
		if (disposed)
			throw new IllegalStateException();
		for (int i=0; i<duration; i++) {
			time++;
			// iterate through a copy because agents may change during iteration...
			for (Agent a : agents.toArray(new Agent[0])) {
				a.run();
			}
			super.setChanged();
			super.notifyObservers();
		}
		CarServerEdge.getServer().reset();
	}

	/**
	 * Throw away this model.
	 */
	public void dispose() {
		animator.dispose();
		disposed = true;
	}

	/**
	 * Construct the model, establishing correspondences with the visualizer.
	 */
	private void setup(AnimatorBuilder builder, int rows, int columns) {
		
		CarDirections[][] intersections = new Light[rows][columns];

		// Add Lights
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {

				intersections[i][j] = Inventory.newLight();
				builder.addLight((Light) intersections[i][j], i, j);

				CarServerEdge.getServer().enqueue(0,
						(Agent) intersections[i][j]);
			}
		}
		
		// Add Horizontal Roads
				boolean eastToWest = false;
				for (int i = 0; i < rows; i++) 
				{
					List<CarDirections> roads = new ArrayList<CarDirections>();
					for (int j = 0; j <= columns; j++) 
					{

						CarDirections l = Inventory.newEWRoad();

						roads.add(l);
						builder.addHorizontalRoad((Road) l, i, j, eastToWest);
						if (j < columns)
						{
							roads.add(intersections[i][j]);
						}
					}

					if (eastToWest) 
					{
						Collections.reverse(roads);
					}
					/*
					 * putting all the East/west carAccptors together:
					 */
					Iterator<CarDirections> it = roads.iterator();
					CarDirections first = it.next();
					CarDirections firstRoad = first;
					CarDirections nextElement = null;
					while (it.hasNext()) {
						nextElement = it.next();
						first.setNextEWRoad(nextElement);
						first = nextElement;
					}
					CarDirections source = Inventory.newSource();
					source.setNextEWRoad(firstRoad);
					CarServerEdge.getServer().enqueue(0, (Agent) source);

					CarDirections sink = Inventory.newSink();
					nextElement.setNextEWRoad(sink);

					if (MP.getTrafficPattern())
						eastToWest = !eastToWest;

				}

				// Add Vertical Roads
				boolean southToNorth = false;
				for (int j = 0; j < columns; j++) {
					List<CarDirections> roads = new ArrayList<CarDirections>();
					for (int i = 0; i <= rows; i++) {
						CarDirections l = Inventory.newNSRoad();
						roads.add(l);
						builder.addVerticalRoad((Road) l, i, j, southToNorth);
						if (i < rows) {
							roads.add(intersections[i][j]);
						}
					}
					if (southToNorth) {
						Collections.reverse(roads);
					}

					/*
					 * putting together the NS lights and roads
					 */
					Iterator<CarDirections> it = roads.iterator();
					CarDirections first = it.next();
					CarDirections firstRoad = first;
					CarDirections nextElement = null;
					while (it.hasNext()) {
						nextElement = it.next();
						first.setNextNSRoad(nextElement);
						first = nextElement;
					}
					CarDirections source = Inventory.newSource();
					source.setNextNSRoad(firstRoad);
					CarServerEdge.getServer().enqueue(0, (Agent) source);

					CarDirections sink = Inventory.newSink();
					nextElement.setNextNSRoad(sink);

					if (MP.getTrafficPattern())
					{
						southToNorth = !southToNorth;
					}
				}

		
	}
}
