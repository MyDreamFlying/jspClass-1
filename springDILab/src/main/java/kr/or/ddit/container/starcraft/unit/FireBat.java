package kr.or.ddit.container.starcraft.unit;

import kr.or.ddit.container.starcraft.tool.FlameThrower;
import kr.or.ddit.container.starcraft.tool.Weapon;

public class FireBat extends FootSoldier {
	Weapon weapon = new FlameThrower();
	
	@Override
	public String walking() {
		return super.walking();
	}

	@Override
	protected String withWeapon() {
		return weapon.damage();
	}

}
