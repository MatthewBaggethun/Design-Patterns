package behavioral.command.textEditor.invoker;

import java.util.ArrayList;
import java.util.List;

import behavioral.command.textEditor.commands.Command;

/**
 * A CommandNode object holds a reference to a parent and it's children for use
 * in a tree.
 */
public final class CommandNode {

	private final Command command;
	private final CommandNode parent;
	private final int id;
	private List<CommandNode> children;

	private static int counter = 0;

	public CommandNode(Command command, CommandNode parent) {
		this.command = command;
		this.parent = parent;
		this.children = new ArrayList<>();
		this.id = counter++;
	}

	public void addChild(CommandNode child) {
		children.add(child);
	}

	public Command getCommand() {
		return command;
	}

	public CommandNode getParent() {
		return parent;
	}

	public List<CommandNode> getChildren() {
		return children;
	}

	public int getId() {
		return id;
	}

	@Override
	public String toString() {
		return "Node " + id;
	}

}
