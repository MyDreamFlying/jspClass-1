package kr.or.ddit.annotation;

import java.io.File;
import java.io.FilenameFilter;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 *	Annotation : 사람과 시스템에게 일정 정보를 전달하기 위한 방법 
 *	종류
 *	1. Marker annotation
 *	2. SingleValue annotation
 *	3. Multivalue annotation
 *
 *	Custom annotation
 *	1. @interfae 키워드로 생성 : Annotation 의 구현체의 형태로 정의됨.
 *	2. 필수 정책
 *		1) 어노테이션 사용 위치 : @Target
 *		2) 어노테이션의 생존 범위 : @Retention (SOURCE, COMPILE, RUNTIME)
 *
 */
public class AnnotationDesc {
	public static void main(String[] args) throws InstantiationException, IllegalAccessException {
		String basePackage = "kr.or.ddit.designpattern.commandpattern";
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		URL baseFolderURL = loader.getResource(basePackage.replace('.','/'));
		File baseFolder = new File(baseFolderURL.getFile());
		String baseFolderAP = baseFolder.getAbsolutePath();
		System.out.println(baseFolder.getAbsolutePath());
		File[] files = baseFolder.listFiles(new FilenameFilter() {
			@Override
			public boolean accept(File dir, String name) {
				return name.endsWith("class");
			}
		});
		List<Class<?>> withGroup = new ArrayList<>();
		List<Class<?>> withoutGroup = new ArrayList<>();
		
		
		for(File classFile : files) {
//			System.out.println(classFile.getAbsolutePath());
			String clzAP = classFile.getAbsolutePath();
			int lastIdx = clzAP.lastIndexOf(".");
			String className = clzAP.substring(baseFolderAP.length()+1, lastIdx);
//			System.out.println(basePackage + "." + className);
			String qualified = basePackage + "." + className;
			try {
				Class<?> clz = Class.forName(qualified);
				FirstAnnotation annotation = clz.getAnnotation(FirstAnnotation.class);
				if(annotation == null) {
					withoutGroup.add(clz);
				}else {
					withGroup.add(clz);
				}
				System.out.println(clz);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}// end of for
		
		System.out.println(withGroup);
		
		Map<String, Object> instanceMap = new HashMap<>();
		for(Class<?> tmp : withGroup) {
			Object instance = tmp.newInstance();
			FirstAnnotation annotation = tmp.getAnnotation(FirstAnnotation.class);
			String key = annotation.value();
			instanceMap.put(key, instance);
		}
		
		for(Entry<String, Object> entry : instanceMap.entrySet()) {
			String key = entry.getKey();
			Object value = entry.getValue();
			System.out.printf("%s : %s\n",key,value);
		}
		
	}
}
