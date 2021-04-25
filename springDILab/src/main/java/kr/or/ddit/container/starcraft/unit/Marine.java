package kr.or.ddit.container.starcraft.unit;

import kr.or.ddit.container.starcraft.tool.RifleGun;
import kr.or.ddit.container.starcraft.tool.Weapon;

public class Marine extends FootSoldier {
	Weapon weapon;
	
	public Marine() {
		weapon = new RifleGun();
	}
	
	@Override
	public String walking() {
		return super.walking();
	}

	@Override
	protected String withWeapon() {
		return weapon.damage();
	}


}
