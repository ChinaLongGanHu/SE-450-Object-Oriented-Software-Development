package template.sort;

/* This example is from Robert C. Martin */
abstract class BubbleSorter {
	private int operations = 0;
	protected int length = 0;
	protected int doSort() {
		operations = 0;
		if (length <= 1) return operations;
		for (int nextToLast = length-2; nextToLast >= 0; nextToLast--)
			for (int index = 0; index <= nextToLast; index++) {
				if (outOfOrder(index)) swap(index);
				operations++;
			}
		return operations;
	}
	protected abstract void swap(int index);
	protected abstract boolean outOfOrder(int index);
}

class IntBubbleSorter extends BubbleSorter {
	private int[] array = null;
	public int sort(int [] array) {
		this.array = array;
		this.length = array.length;
		return doSort();
	}
	protected void swap(int index) {
		int temp = array[index];
		array[index] = array[index+1];
		array[index+1] = temp;
	}
	protected boolean outOfOrder(int index) {
		return (array[index] > array[index+1]);
	}
}

class DoubleBubbleSorter extends BubbleSorter {
	private double[] array = null;
	public int sort(double [] array) {
		this.array = array;
		this.length = array.length;
		return doSort();
	}
	protected void swap(int index) {
		double temp = array[index];
		array[index] = array[index+1];
		array[index+1] = temp;
	}
	protected boolean outOfOrder(int index) {
		return (array[index] > array[index+1]);
	}
}
