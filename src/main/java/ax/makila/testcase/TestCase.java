package ax.makila.testcase;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * 
 * @author Fredrik Makila
 *
 * @param <T>
 */
public abstract class TestCase<T> {
	private final Class<T> m_targetClass;
	private final Object m_testInstance;
	
	public TestCase(Class<T> targetClass, Object testInstance) {
		m_targetClass = targetClass;
		m_testInstance = testInstance;
	}
	
	public <U> TestMethod<U> getAccessibleMethod(String methodName, Class<?>... argClasses) throws NoSuchMethodException {
		
		try {
			Method[] declaredMethods = m_targetClass.getDeclaredMethods();
			for(Method declaredMethod : declaredMethods) {
				if(declaredMethod.getName().equals(methodName)
						&& Arrays.equals(declaredMethod.getParameterTypes(), argClasses)) {
					
					declaredMethod.setAccessible(true);
					
					return new TestMethod<U>(declaredMethod);
				}
			}
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		throw new NoSuchMethodException("No such method: " + methodName 
				+ " with args " + argClasses);
	}
	
	public class TestMethod<V> {
		private final Method m_method;

		public TestMethod(Method method) {
			m_method = method;
		}
		
		@SuppressWarnings("unchecked")
		public V invoke(Object... args) throws IllegalArgumentException, IllegalAccessException {
			Object returnValue = null;
			
			try {
				if (args == null) {
					returnValue = m_method.invoke(m_testInstance, new Object[] {args});
				} else if (args.length == 0) {
					returnValue = m_method.invoke(m_testInstance);
				} else {
					returnValue = m_method.invoke(m_testInstance, args);
				}
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
			
			V castValue = null;
			try {
				castValue = (V) returnValue;
				return castValue;
				
			} catch(ClassCastException ex) {
				ex.printStackTrace();
				throw new IllegalArgumentException("Illegal parameterized return type of invokation method");
			}
		}
 	}
	
	
}



