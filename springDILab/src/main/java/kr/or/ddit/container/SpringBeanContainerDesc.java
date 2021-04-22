package kr.or.ddit.container;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import kr.or.ddit.example.service.IExampleService;

/**
 *	Spring Bean Container 사용 단계
 *	: bean 의 lifecycle 관리자
 *	1. spring container module을 빌드패스에 추가
 *		(beans, core, context, spEL)
 *	2. bean meta data(bean definition metadata) 등록 파일
 *		1) bean 등록(bean 엘리먼트)
 *		2) 등록된 bean 들간의 의존관계 형성 ( 의존성 주입, dependency injection)
 *			- constructor injection (필수 전략 주입)
 *				constructor-arg, c namespace(3.1)
 *			- setter injection (optional 전략 주입)
 *				property, p namespace(3.0)
 *	3. entry poiunt 에서 Container 객체 생성
 *		ApplicationContext 의 구현체
 *	4. getBean 으로 의존객체 주입
 *		- type을 기준으로 한 주입 (두 개 이상의 빈이 존재시 exception 발생)
 *		- id를 기준으로 한 주입
 *	5. 컨테이너 종료(shutdownHook 등록)
 *
 *	*** 컨테이너의 빈 관리 정책
 *	1. 특별한 설정(scope)이 없는 한 빈은 Singleton 으로 관리됨.
 *		** 싱글턴의 대상은 빈 !!! 
 *	scope - singleton (기본 정책) - 하나의 빈은 하나의 객체
 *			prototype - 주입될때마다 새로운 객체가 생성됨.
 *			request / session
 *	2. 특별한 설정(lazy-init)이 없는 한 컨테이너가 초기화 될 때 등록된 Bean의 모든 객체를 생성한다.
 *		: 객체의 생성 시점을 지연시키거나 생성 순서를 어느정도 제어할 수 있음.
 *
 *	3. depends-on 을 이용하여 Bean들간의 순서를 직접 제어도 가능은 함.
 *	4. 생명 주기 콜백 정의 가능
 *	*** init-mothod는 필요한 주입이 모두 끝난 후에 호출됨.
 *
 */
public class SpringBeanContainerDesc {
	public static void main(String[] args) {
		String xml = "classpath:kr/or/ddit/container/conf/spring-container.xml";
		ConfigurableApplicationContext container = new GenericXmlApplicationContext(xml);
		container.registerShutdownHook();
		
//		IExampleService service1 = container.getBean("service1",IExampleService.class);
//		IExampleService service1_1 = container.getBean("service1",IExampleService.class);
//		IExampleService service2 = container.getBean("service2",IExampleService.class);
//		IExampleService service2_2 = container.getBean("service2",IExampleService.class);
//		System.out.println(service1.readData("할룽"));
//		System.out.println(service1 == service2);
//		System.out.println(service1 == service1_1);
//		System.out.println(service2 == service2_2);
		
		container.close();
		
	}

}
