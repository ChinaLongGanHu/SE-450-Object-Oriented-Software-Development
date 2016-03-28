package myproject.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import myproject.util.Animator;

/**
 * An example to model for a simple visualization.
 * The model contains two roads.
 * See {@link #SimpleModel(AnimatorBuilder)}.
 */
public class SimpleModel extends Observable {
	private List<Agent> agents;
	private Animator animator;
	private boolean disposed;
	private double time;

	/** Creates a model to be visualized using the <code>builder</code>.
	 *  If the builder is null, no visualization is performed.
	 *  Each road has one {@link Car}.
	 *
	 */
	public SimpleModel(AnimatorBuilder builder) {
		if (builder == null) {
			builder = new NullAnimatorBuilder();
		}
		agents = new ArrayList<Agent>();
		setup(builder);
		animator = builder.getAnimator();
		super.addObserver(animator);
	}

	/**
	 * Run the simulation for <code>duration</code> model seconds.
	 */
	public void run(double duration) {
		if (disposed)
			throw new IllegalStateException();
		for (int i = 0; i < duration; i++) {
			time += MP.getTimeStep();

			CarServerEdge.getServer().run(MP.getTimeStep());

			super.setChanged();
			super.notifyObservers();
		}
	}

	/**
	 * Throw away this model.
	 */
	public void dispose() {
		animator.dispose();
		disposed = true;
	}

	private void setup(AnimatorBuilder builder) {
		CarDirections r1 = Inventory.newEWRoad();
		CarDirections r2 = Inventory.newEWRoad();
		CarDirections r3 = Inventory.newNSRoad();
		CarDirections r4 = Inventory.newNSRoad();
		CarDirections source2 = Inventory.newSource();
		CarDirections sink2 = Inventory.newSink();
		CarDirections sink = Inventory.newSink();
		CarDirections source = Inventory.newSource();
		CarDirections light = Inventory.newLight();
		source.setNextEWRoad(r1);
		r1.setNextEWRoad(light);
		light.setNextEWRoad(r2);
		r2.setNextEWRoad(sink);
		source2.setNextNSRoad(r3);
		r3.setNextNSRoad(light);
		light.setNextNSRoad(r4);
		r4.setNextNSRoad(sink2);

		CarServerEdge.getServer().enqueue(0, (Agent) source);
		CarServerEdge.getServer().enqueue(0, (Agent) source2);
		CarServerEdge.getServer().enqueue(0, (Agent) light);
		builder.addVerticalRoad((Road) r3, 0, 1, false);
		builder.addVerticalRoad((Road) r4, 1, 1, false);
		builder.addHorizontalRoad((Road) r1, 0, 1, false);
		builder.addLight((Light) light, 0, 1);
		builder.addHorizontalRoad((Road) r2, 0, 2, false);

	}	
}
