package com.jsc.javageeks.provider;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.Calendar;

import javax.ws.rs.ext.ParamConverter;
import javax.ws.rs.ext.ParamConverterProvider;
import javax.ws.rs.ext.Provider;

import com.jsc.javageeks.domain.MyDate;

@Provider
public class MyDateParamConvertorProvider implements ParamConverterProvider {

	@Override
	public <T> ParamConverter<T> getConverter(Class<T> rawType,
			Type genericType, Annotation[] annotations) {
		if (rawType.getName().equals(MyDate.class.getName())) {
			return new ParamConverter<T>() {

				@Override
				public T fromString(String value) {
					Calendar requestedDate = Calendar.getInstance();
					if (value.equals("yesterday")) {
						requestedDate.add(Calendar.DATE, -1);
					} else if (value.equals("tommorow")) {
						requestedDate.add(Calendar.DATE, 1);
					}
					MyDate myDate = new MyDate();
					myDate.setDate(requestedDate.get(Calendar.DATE));
					myDate.setMonth(requestedDate.get(Calendar.MONTH));
					myDate.setYear(requestedDate.get(Calendar.YEAR));
					return (T) myDate;
				}

				@Override
				public String toString(T clazz) {
					if (clazz == null) {
						return null;
					}
					return clazz.toString();
				}

			};
		}
		return null;
	}

}
