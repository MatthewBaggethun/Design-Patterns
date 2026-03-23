package behavioral.command.textEditor.invoker;

import java.util.HashMap;
import java.util.Map;

import behavioral.command.textEditor.commands.Command;

/**
 * Responsible for executing, undoing and redoing commands. This is an invoker
 * implementation utilizing the command pattern. Maintains a tree structure
 * containing CommandNodes for undo/redo functionality.
 * 
 * @see CommandNode
 */
public final class CommandManager {

	private Map<Integer, CommandNode> nodeIndex = new HashMap<>();
	private CommandNode root;
	private CommandNode current;

	public CommandManager() {
		root = new CommandNode(null, null);
		nodeIndex.put(root.getId(), root);
		current = root;
	}

	public void executeCommand(Command command) {
		command.execute();

		CommandNode newNode = new CommandNode(command, current);
		nodeIndex.put(newNode.getId(), newNode);
		current.addChild(newNode);

		current = newNode;
	}

	public void undo() {
		if (current == root)
			return;

		current.getCommand().undo();
		current = current.getParent();
	}

	public void redo(int childrenIndex) {
		if (current.getChildren().isEmpty())
			return;

		if (childrenIndex < 0 || childrenIndex >= current.getChildren().size())
			throw new IllegalArgumentException(
					"The index " + childrenIndex + " is not within the bounds of the child array.");

		CommandNode redoChild = current.getChildren().get(childrenIndex);
		redoChild.getCommand().execute();
		current = redoChild;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		nodeIndex.values().forEach(v -> builder.append(v + ", "));
		return builder.toString();
	}

}
