package kr.or.ddit.designpattern.templatemethodpattern;

public abstract class TemplateClass {
	public final void template() {
		stepOne();
		stepTwo();
		stepThree();
	}
	
	private void stepOne() {
		System.out.println("1단계");
	}
	
	protected abstract void stepTwo();
	
	private void stepThree() {
		System.out.println("3단계");
	}
	
}
