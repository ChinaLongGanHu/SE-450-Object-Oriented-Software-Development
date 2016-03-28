package myproject.model;

import java.util.Observable;

import myproject.util.Animator;

/**
 * An example to model for a simple visualization. The model contains two roads.
 * See {@link #SimpleModel(AnimatorBuilder)}.
 */
public class SimpleModel extends Observable {
	private Animator animator;
	private boolean disposed;
	private double time;

	/**
	 * Creates a model to be visualized using the <code>builder</code>. If the
	 * builder is null, no visualization is performed. Each road has one
	 * {@link Car}.
	 * 
	 */
	public SimpleModel(AnimatorBuilder builder) {
		if (builder == null) {
			builder = new NullAnimatorBuilder();
		}
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
			time += MP.getvarTimeStep();

			CarServerEdge.getServer().run(MP.getvarTimeStep());

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
		CarDirectionInterface r1 = Inventory.newEWRoad();
		CarDirectionInterface r2 = Inventory.newEWRoad();
		CarDirectionInterface r3 = Inventory.newNSRoad();
		CarDirectionInterface r4 = Inventory.newNSRoad();
		CarDirectionInterface source2 = Inventory.newSource();
		CarDirectionInterface sink2 = Inventory.newSink();
		CarDirectionInterface sink = Inventory.newSink();
		CarDirectionInterface source = Inventory.newSource();
		CarDirectionInterface light = Inventory.newLight();
		source.setNextEastWestRoad(r1);
		r1.setNextEastWestRoad(light);
		light.setNextEastWestRoad(r2);
		r2.setNextEastWestRoad(sink);
		source2.setNextNorthSouthRoad(r3);
		r3.setNextNorthSouthRoad(light);
		light.setNextNorthSouthRoad(r4);
		r4.setNextNorthSouthRoad(sink2);

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
