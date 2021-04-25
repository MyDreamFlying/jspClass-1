package kr.or.ddit.container.starcraft.unit;

public abstract class FootSoldier {
	protected String walking() {
		return "그냥 걷기";
	}
	protected abstract String withWeapon();
	public void attack() {
		System.out.println(withWeapon());
	}
}
