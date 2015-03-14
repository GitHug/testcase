package ax.makila.testcase.resources;

public final class ClassForTest {

	@SuppressWarnings("unused")
	private String privateMethod() {
		return "Private method without parameters";
	}
	
	@SuppressWarnings("unused")
	private String privateMethod(Object obj, String[] args, int integer) {
		return "Private method with parameters";
	}
	
	String defaultMethod() {
		return "Default method without parameters";
	}
	
	String defaultMethod(Object obj, String[] args, int integer) {
		return "Default method with parameters";
	}
	
	protected String protectedMethod() {
		return "Protected method without parameters";
	}
	
	protected String protectedMethod(Object obj, String[] args, int integer) {
		return "Protected method with parameters";
	}
	
	public String publicMethod() {
		return "Public method without parameters";
	}
	
	public String publicMethod(Object obj, String[] args, int integer) {
		return "Public method with parameters";
		
	}

}
