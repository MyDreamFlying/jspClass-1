package kr.or.ddit.container.starcraft;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import kr.or.ddit.container.starcraft.building.Barrack;
import kr.or.ddit.container.starcraft.building.SoldierType;
import kr.or.ddit.container.starcraft.unit.FootSoldier;

public class BattleField {
	
	public static void main(String[] args) {
		BattleField field = new BattleField();
		field.play();
		
	}
	
	public void play() {
		
		ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("kr/or/ddit/container/conf/starcraft.xml");
		
		Barrack barrack = (Barrack) context.getBean("barrack");

		barrack.trainingSoldiers(SoldierType.FIREBAT, 3);
		
		FootSoldier soldier = barrack.trainingSoldier(SoldierType.MEDIC);
		soldier.attack();
		
		context.close();
	}

}
