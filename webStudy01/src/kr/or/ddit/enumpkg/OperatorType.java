package kr.or.ddit.enumpkg;

import kr.or.ddit.vo.CalculateVO;

public enum OperatorType{
	PLUS('+', new RealOperator() {
		public double operate(double left, double right) {
			return left + right;
		}
	}), 
	MINUS('-', new RealOperator() {
		public double operate(double left, double right) {
			return left - right;
		}
	}), 
	MULTIPLY('*', new RealOperator() {
		public double operate(double left, double right) {
			return left * right;
		}
	}), 
	DIVIDE('/', new RealOperator() {
		public double operate(double left, double right) {
			return left / right;
		}
	}); 
	
	private interface RealOperator{
		double operate(double left, double right);
	}
	
	private RealOperator realOperator; 
	
	private char sign;
	
	private OperatorType(char sign, RealOperator realOperator) {
		this.sign = sign;
		this.realOperator = realOperator;
	}
	
	public char getSign() {
		return sign;
	}
	
	public double operate(double left, double right) {
		return realOperator.operate(left,right);
	}
	
	private static final String EXPRPTRN = "%.2f %s %.2f = %.2f";
	
	public String expression(CalculateVO vo) {
		return String.format(EXPRPTRN,vo.getLeft(),sign,vo.getRight(), operate(vo.getLeft(),vo.getRight()));
	}

	
}