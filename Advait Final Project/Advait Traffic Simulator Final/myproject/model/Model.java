package myproject.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Observable;

import myproject.util.Animator;

/**
 * An example to model for a simple visualization. The model contains roads
 * organized in a matrix. See {@link #Model(AnimatorBuilder, int, int)}.
 */
public class Model extends Observable {

	private Animator animator;
	private boolean disposed;
	private double time;

	private List<Agent> agents;

	/**
	 * Creates a model to be visualized using the <code>builder</code>. If the
	 * builder is null, no visualization is performed. The number of
	 * <code>rows</code> and <code>columns</code> indicate the number of
	 * {@link Light}s, organized as a 2D matrix. These are separated and
	 * surrounded by horizontal and vertical {@link Road}s. For example, calling
	 * the constructor with 1 row and 2 columns generates a model of the form:
	 * 
	 * <pre>
	 *     |  |
	 *   --@--@--
	 *     |  |
	 * </pre>
	 * 
	 * where <code>@</code> is a {@link Light}, <code>|</code> is a vertical
	 * {@link Road} and <code>--</code> is a horizontal {@link Road}. Each road
	 * has one {@link Car}.
	 * 
	 * <p>
	 * The {@link AnimatorBuilder} is used to set up an {@link Animator}.
	 * {@link AnimatorBuilder#getAnimator()} is registered as an observer of
	 * this model.
	 * <p>
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
		for (int i = 0; i < duration; i++) {

			time += MP.getvarTimeStep();

			CarServerEdge.getServer().run(MP.getvarTimeStep());

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

		CarDirectionInterface[][] intersections = new Light[rows][columns];

		// Adding Lights
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				intersections[i][j] = Inventory.newLight();
				builder.addLight((Light) intersections[i][j], i, j);
				CarServerEdge.getServer().enqueue(0,
						(Agent) intersections[i][j]);
			}
		}

		// Adding Horizontal Roads
		boolean eastToWest = false;
		for (int i = 0; i < rows; i++) {
			List<CarDirectionInterface> roads = new ArrayList<CarDirectionInterface>();
			for (int j = 0; j <= columns; j++) {

				CarDirectionInterface l = Inventory.newEWRoad();

				roads.add(l);
				builder.addHorizontalRoad((Road) l, i, j, eastToWest);
				if (j < columns) {
					roads.add(intersections[i][j]);
				}
			}

			if (eastToWest) {
				Collections.reverse(roads);
			}
			/*
			 * Adding All together East west lights and roads
			 */
			Iterator<CarDirectionInterface> it = roads.iterator();
			CarDirectionInterface first = it.next();
			CarDirectionInterface firstRoad = first;
			CarDirectionInterface nextElement = null;
			while (it.hasNext()) {
				nextElement = it.next();
				first.setNextEastWestRoad(nextElement);
				first = nextElement;
			}
			CarDirectionInterface source = Inventory.newSource();
			source.setNextEastWestRoad(firstRoad);
			CarServerEdge.getServer().enqueue(0, (Agent) source);

			CarDirectionInterface sink = Inventory.newSink();
			nextElement.setNextEastWestRoad(sink);

			if (MP.getTrafficPattern())
				eastToWest = !eastToWest;

		}

		// Adding Vertical Roads
		boolean southToNorth = false;
		for (int j = 0; j < columns; j++) {
			List<CarDirectionInterface> roads = new ArrayList<CarDirectionInterface>();
			for (int i = 0; i <= rows; i++) {
				CarDirectionInterface l = Inventory.newNSRoad();
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
			 * All together North South lights and roads
			 */
			Iterator<CarDirectionInterface> it = roads.iterator();
			CarDirectionInterface first = it.next();
			CarDirectionInterface firstRoad = first;
			CarDirectionInterface nextElement = null;
			while (it.hasNext()) {
				nextElement = it.next();
				first.setNextNorthSouthRoad(nextElement);
				first = nextElement;
			}
			CarDirectionInterface source = Inventory.newSource();
			source.setNextNorthSouthRoad(firstRoad);
			CarServerEdge.getServer().enqueue(0, (Agent) source);

			CarDirectionInterface sink = Inventory.newSink();
			nextElement.setNextNorthSouthRoad(sink);

			if (MP.getTrafficPattern())
				southToNorth = !southToNorth;

		}
	}
}
