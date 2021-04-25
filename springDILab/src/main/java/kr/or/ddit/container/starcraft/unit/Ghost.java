package kr.or.ddit.container.starcraft.unit;

import kr.or.ddit.container.starcraft.tool.NuclearWeapon;
import kr.or.ddit.container.starcraft.tool.Weapon;

public class Ghost extends FootSoldier {
	Weapon weapon = new NuclearWeapon();
	
	@Override
	public String walking() {
		return super.walking();
	}

	@Override
	protected String withWeapon() {
		return weapon.damage();
	}


}
