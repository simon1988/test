package fragments;

public enum TestEnum {
	A("+A"),
	B("+B");
	private String name;
	private TestEnum(String name){
		this.name=name;
	}
	public static String getName(String key){
		try{
			return TestEnum.valueOf(key).name;
		}catch (IllegalArgumentException e) {
			return key;
		}
	}
}
