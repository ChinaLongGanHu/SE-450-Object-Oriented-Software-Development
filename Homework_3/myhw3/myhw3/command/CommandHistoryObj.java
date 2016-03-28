package myhw3.command;
import java.util.Stack;

public final class CommandHistoryObj implements CommandHistory {
	Stack<Command> undoStack = new Stack<Command>();
	Stack<Command> redoStack = new Stack<Command>();

	public void add(Command cmd) {
		// TODO
	}

	public boolean undo() {
		boolean result = !undoStack.empty();
		if (result) {
			// TODO
		}
		return result;
	}

	public boolean redo() {
		boolean result = !redoStack.empty();
		if (result) {
			// TODO
		}
		return result;
	}

	// For testing
	Command topUndoCommand() {
		if (undoStack.empty())
			return null;
		else
			return undoStack.peek();
	}
	// For testing
	Command topRedoCommand() {
		if (redoStack.empty())
			return null;
		else
			return redoStack.peek();
	}
}
