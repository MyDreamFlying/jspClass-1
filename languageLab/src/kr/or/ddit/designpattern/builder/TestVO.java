package kr.or.ddit.designpattern.builder;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TestVO implements Serializable{
	
	private String prop1;
	private String prop2;
	private String prop3;
	
}
