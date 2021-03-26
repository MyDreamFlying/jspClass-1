package kr.or.ddit.designpattern.reflection;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import kr.or.ddit.reflect.ReflectionTest;

public class ReflectionDesc {
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		Object obj = ReflectionTest.getObject();
		System.out.println(obj);
		
		Map<Object,Object> map = new HashMap<>();
		dePopulate(obj, map);
		System.out.println(map);
	
	}
	
	public static void dePopulate(Object bean, Map<Object,Object> map) {
		Class clz = bean.getClass();
		Field[] fields = clz.getDeclaredFields();
		for(Field tmp : fields) {
			String varName = tmp.getName();
			try {
				PropertyDescriptor pd = new PropertyDescriptor(varName, clz);
				Class varType = pd.getPropertyType();
				Method getter = pd.getReadMethod();
				Object value = getter.invoke(bean);
				
				Method setter = pd.getWriteMethod();
				
				setter.invoke(bean,"");
				
				System.out.printf("%s %s = %s;\n",
						varType.getSimpleName(), varName, value);
				
				map.put(varName, value);
				
			} catch (IntrospectionException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			} 
		}
	}
	
	private static void reflect1(Object obj) {
		
		Class clz = obj.getClass();
		System.out.println(clz);
		Field[] fields = clz.getDeclaredFields();
		for(Field tmp : fields) {
			String varName = tmp.getName();
			Class varType = tmp.getType();
			try {
//				tmp.setAccessible(true);
//				Object value = tmp.get(obj);
				String readMethodName = "get"
						+varName.substring(0,1).toUpperCase()
						+varName.substring(1);
				Method readMethod = clz.getDeclaredMethod(readMethodName);
				Object value = readMethod.invoke(obj);
				
				System.out.printf("%s %s=%s;\n", varType.getSimpleName(), varName, value);
			} catch (IllegalArgumentException | IllegalAccessException e) {
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			} catch (SecurityException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
		}
	}
	
	private static void reflect2(Object obj) {
		
		Class clz = obj.getClass();
		Field[] fields = clz.getDeclaredFields();
		for(Field tmp : fields) {
			String varName = tmp.getName();
			try {
				PropertyDescriptor pd = new PropertyDescriptor(varName, clz);
				Class varType = pd.getPropertyType();
				Method getter = pd.getReadMethod();
				Object value = getter.invoke(obj);
				System.out.printf("%s %s = %s;\n",
						varType.getSimpleName(), varName, value);
			} catch (IntrospectionException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			} 
		}
		
	}
	
}
