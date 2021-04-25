package kr.or.ddit.container.starcraft.building;

import java.util.List;

import kr.or.ddit.container.starcraft.unit.FootSoldier;

public interface Trainable {
	public FootSoldier trainingSoldier(SoldierType type);
	public List<FootSoldier> trainingSoldiers(SoldierType type, int num);
}
