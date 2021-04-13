package kr.or.ddit.mvc.annotation.resolvers;

import java.io.IOException;
import java.lang.reflect.Parameter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.ClassUtils;
import org.apache.commons.lang3.StringUtils;

public class RequestPartArgumentResolver implements IHandlerMethodArgumentResolver {

	@Override
	public boolean isSupported(Parameter parameter) {
		Class<?> parameterType = parameter.getType();
		RequestPart annotation = parameter.getAnnotation(RequestPart.class);
		boolean supported = annotation != null
				&&!(
						String.class.equals(parameterType)
						|| ClassUtils.isPrimitiveOrWrapper(parameterType)
				);
		return supported;
	}

	@Override
	public Object argumentResolve(Parameter parameter, HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		Class<?> parameterType = parameter.getType();
		RequestPart annotation = parameter.getAnnotation(RequestPart.class);
		
		String reqPartName = annotation.value();
		String reqPartValue = req.getParameter(reqPartName);
		boolean required = annotation.required();
		if(required && StringUtils.isBlank(reqPartValue)){
			throw new BadRequestException("필수 파라미터 누락");
		}else if(!required && StringUtils.isBlank(reqPartValue)) {
	
		}
		
		try {
			Object parameterValue = parameterType.newInstance();
			String attributeName = annotation.value();
			req.setAttribute(attributeName, parameterValue);
			BeanUtils.populate(parameterValue, req.getParameterMap());
			return parameterValue;
		}catch (Exception e) {
			throw new ServletException(e);
		}
		
	}

}
