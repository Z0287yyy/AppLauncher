package com.chris.applauncher.utils.base;

import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ObjectUtils {

	@SuppressWarnings("rawtypes")
	public static boolean isWeakReferenceNull(WeakReference reference) {
		boolean res = false;
		if (reference == null || reference.get() == null) {
			res = true;
		}
		return res;
	}
	
	@SuppressWarnings("rawtypes")
	public static boolean isSoftReferenceNull(SoftReference reference) {
		boolean res = false;
		if (reference == null || reference.get() == null) {
			res = true;
		}
		return res;
	}
	
	@SuppressWarnings("unchecked")
	public static <T> Class<T> getTClass(Object object) {
		if (object == null) {
			return null;
		}

		try {
			Type[] types = object.getClass().getGenericInterfaces();
			Type type = object.getClass().getGenericSuperclass();

			if (types == null || types.length == 0) {
				if (type instanceof ParameterizedType) {
					ParameterizedType paramType = (ParameterizedType) type;
					Type[] actualTypes = paramType.getActualTypeArguments();

					if (actualTypes != null && actualTypes.length > 0) {
						try {
							return (Class<T>) actualTypes[0];
						} catch (Exception e) {
							return null;
						}

					}
				}
			} else {
				for (int i = 0; i < types.length; i++) {
					Type subType = types[i];
					if (subType instanceof ParameterizedType) {
						ParameterizedType paramType = (ParameterizedType) subType;
						Type[] actualTypes = paramType.getActualTypeArguments();

						if (actualTypes != null && actualTypes.length > 0) {
							try {
								return (Class<T>) actualTypes[0];
							} catch (Exception e) {
								try {
									return (Class<T>) Class.forName(actualTypes[0].toString());
								} catch (Exception ex) {
									try {
										final String className = actualTypes[0].toString();

										Pattern mainClassPattern = Pattern.compile("<\\S+>");
										Matcher mainClassMatcher = mainClassPattern.matcher(className);
										String mainClassName = mainClassMatcher.replaceAll("");

										Pattern subClassPattern = Pattern.compile("\\<.*?\\>");
										Matcher subClassMatcher = subClassPattern.matcher(className);
										subClassMatcher.find();
										String subClassName = subClassMatcher.group().replaceAll("\\<", "").replaceAll("\\>", "");
										Class subClass = Class.forName(subClassName);
										
										Class resultClass = Class.forName(mainClassName).asSubclass(subClass);
										return resultClass;
									} catch (Exception exp) {
										exp.printStackTrace();
									}
								}
							}

						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
	
	public static Type getTType(Object object) {
		if (object == null) {
			return null;
		}

		try {
			Type[] types = object.getClass().getGenericInterfaces();
			Type type = object.getClass().getGenericSuperclass();

			if (types == null || types.length == 0) {
				if (type instanceof ParameterizedType) {
					ParameterizedType paramType = (ParameterizedType) type;
					Type[] actualTypes = paramType.getActualTypeArguments();

					if (actualTypes != null && actualTypes.length > 0) {
						try {
							return actualTypes[0];
						} catch (Exception e) {
							return null;
						}

					}
				}
			} else {
				for (int i = 0; i < types.length; i++) {
					Type subType = types[i];
					if (subType instanceof ParameterizedType) {
						ParameterizedType paramType = (ParameterizedType) subType;
						Type[] actualTypes = paramType.getActualTypeArguments();
						
						if (actualTypes != null && actualTypes.length > 0) {
							return actualTypes[0];
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
}
