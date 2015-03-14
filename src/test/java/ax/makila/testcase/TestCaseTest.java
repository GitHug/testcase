package ax.makila.testcase;

import static org.junit.Assert.*;

import org.junit.Test;

import ax.makila.testcase.resources.ClassForTest;

public class TestCaseTest extends TestCase<ClassForTest> {
	
	public TestCaseTest() {
		super(ClassForTest.class, new ClassForTest());
	}
	
	@Test
	public void testAccessMethods() throws NoSuchMethodException, IllegalArgumentException, IllegalAccessException {

		TestMethod<String> privateMethodWithoutParameters = getAccessibleMethod("privateMethod");
		TestMethod<String> privateMethodWithParameters = 
				getAccessibleMethod("privateMethod", Object.class, String[].class, int.class);
		
		TestMethod<String> defaultMethodWithoutParameters = getAccessibleMethod("defaultMethod");
		TestMethod<String> defaultMethodWithParameters = 
				getAccessibleMethod("defaultMethod", Object.class, String[].class, int.class);
		
		TestMethod<String> protectedMethodWithoutParameters = getAccessibleMethod("protectedMethod");
		TestMethod<String> protectedMethodWithParameters = 
				getAccessibleMethod("protectedMethod", Object.class, String[].class, int.class);
		
		TestMethod<String> publicMethodWithoutParameters = getAccessibleMethod("publicMethod");
		TestMethod<String> publicMethodWithParameters = 
				getAccessibleMethod("publicMethod", Object.class, String[].class, int.class);
		
		assertEquals("Accessing privateMethodWithoutParameters", "Private method without parameters",
				privateMethodWithoutParameters.invoke());
		assertEquals("Accessing privateMethodWithParameters", "Private method with parameters",
				privateMethodWithParameters.invoke(new Object(), new String[]{}, 0));
		
		assertEquals("Accessing defaultMethodWithoutParameters", "Default method without parameters",
				defaultMethodWithoutParameters.invoke());
		assertEquals("Accessing defaultMethodWithParameters", "Default method with parameters",
				defaultMethodWithParameters.invoke(new Object(), new String[]{}, 0));
		
		assertEquals("Accessing protectedMethodWithoutParameters", "Protected method without parameters",
				protectedMethodWithoutParameters.invoke());
		assertEquals("Accessing protectedMethodWithParameters", "Protected method with parameters",
				protectedMethodWithParameters.invoke(new Object(), new String[]{}, 0));
		
		assertEquals("Accessing publicMethodWithoutParameters", "Public method without parameters",
				publicMethodWithoutParameters.invoke());
		assertEquals("Accessing publicMethodWithParameters", "Public method with parameters",
				publicMethodWithParameters.invoke(new Object(), new String[]{}, 0));
	}
	
	@Test(expected=NoSuchMethodException.class)
	public void testNull() throws NoSuchMethodException {		
		getAccessibleMethod(null);
	}
	
	@Test(expected=NoSuchMethodException.class)
	public void testEmptyString() throws NoSuchMethodException {		
		getAccessibleMethod("");
	}
	
	@Test(expected=NoSuchMethodException.class)
	public void testNoSuchMethod() throws NoSuchMethodException {		
		getAccessibleMethod("thisMethodCertainlyDoesNotExistInTheTargetClass");
	}

	


}
