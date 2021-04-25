package kr.or.ddit.container.starcraft.unit;

import kr.or.ddit.container.starcraft.tool.InjectorWeapon;
import kr.or.ddit.container.starcraft.tool.Weapon;

public class Medic extends FootSoldier {
	Weapon weapon = new InjectorWeapon();
	
	@Override
	public String walking() {
		return super.walking();
	}

	@Override
	protected String withWeapon() {
		return weapon.damage();
	}

}
