package kr.or.ddit.designpattern.adapterpattern;

public class Client {
	private Target target;

	public Client(Target target) {
		super();
		this.target = target;
	}
	
	public void execute() {
		target.request();
	}
	
	public static void main(String[] args) {
//		Target target = new OtherConcrete();
		Target target = new Adapter(new Adaptee());
		Client client = new Client(target);
		client.execute();
	}
	
}
