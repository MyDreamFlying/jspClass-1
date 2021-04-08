package kr.or.ddit.designpattern.commandpattern;

import kr.or.ddit.annotation.FirstAnnotation;

@FirstAnnotation(number=45)
public class Invoker2NoPattern {
	private Receiver2 recv2;
	
	public Invoker2NoPattern() {
		super();
	}


	public Invoker2NoPattern(Receiver2 recv2) {
		super();
		this.recv2 = recv2;
	}


	public void order() {
		recv2.specific2Operate2();
	}
}
