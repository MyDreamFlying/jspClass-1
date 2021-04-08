package kr.or.ddit.designpattern.commandpattern;

public class Invoker {
	private Command commands[];
	
	public Invoker(Command...command) {
		super();
		this.commands = command;
	}
	
	public void order(int mapping) {
		if(mapping >= commands.length) {
			throw new RuntimeException("404");
		}
		commands[mapping].execute();
	}
	
}
