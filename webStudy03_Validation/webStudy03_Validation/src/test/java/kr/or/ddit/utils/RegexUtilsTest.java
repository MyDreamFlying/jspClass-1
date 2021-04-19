package kr.or.ddit.utils;

import static org.junit.Assert.*;

import org.junit.Test;

public class RegexUtilsTest {

	@Test
	public void testFilteringTokens() {
		String origin = "하하하 말미잘같은놈 멍게나 먹어라!";
		String newStr = RegexUtils.filteringTokens(origin, '#', "말미잘","해삼","멍게");
		System.out.println(newStr);
	}

}
