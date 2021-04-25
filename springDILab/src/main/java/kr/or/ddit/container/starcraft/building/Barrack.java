package kr.or.ddit.container.starcraft.building;

import java.util.ArrayList;
import java.util.List;

import kr.or.ddit.container.starcraft.unit.FireBat;
import kr.or.ddit.container.starcraft.unit.FootSoldier;
import kr.or.ddit.container.starcraft.unit.Ghost;
import kr.or.ddit.container.starcraft.unit.Marine;
import kr.or.ddit.container.starcraft.unit.Medic;

public class Barrack implements Trainable{

	@Override
	public FootSoldier trainingSoldier(SoldierType type) {
		System.out.printf("%s을(를) 생성했습니다.\n",type.toString());
		switch(type) {
		case FIREBAT:
			return generateFirebat();
		case GHOST:
			return generateGhost();
		case MARINE:
			return generateMarine();
		case MEDIC:
			return generateMedic();
		}
		return null;
	}

	@Override
	public List<FootSoldier> trainingSoldiers(SoldierType type, int num) {
		List<FootSoldier> list = new ArrayList<>();
		for(int i=0; i<num; i++) {
			list.add(trainingSoldier(type));
		}
		return list;
	}
	
	public FootSoldier generateMarine(){
		return new Marine();
	}
	public FootSoldier generateFirebat() {
		return new FireBat();
	}
	public FootSoldier generateGhost() {
		return new Ghost();
	}
	public FootSoldier generateMedic() {
		return new Medic();
	}

}
