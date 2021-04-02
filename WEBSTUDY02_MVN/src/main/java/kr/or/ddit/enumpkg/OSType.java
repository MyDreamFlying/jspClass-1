package kr.or.ddit.enumpkg;

public enum OSType{
	IPHONE("아이폰"), 
	ANDROID("안드로이드"), 
	MAC("맥OS"), 
	WINDOWS("윈도우"), 
	OTHER("기타");
	
	OSType(String osName){
		this.osName = osName;
	}
	private String osName;
	public String getOSName(){
		return this.osName;
	}
	
	public static String getOSName(String agent) {
		agent = agent.toUpperCase();
		OSType searched = OTHER;
		for( OSType tmp : values()){
			if(agent.contains(tmp.name())){
				searched = tmp;
				break;
			}
		}
		String name = searched.getOSName();
		return name;
		
	}
}