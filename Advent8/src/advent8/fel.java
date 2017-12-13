package advent8;
import java.util.HashMap;

public class fel {

	public static void main(String[] args) {
		HashMap<String, Integer> vars = new HashMap<String, Integer>();
		String[] a = { "-278", "7" };
		vars.put("peter", -278);
		
		
		Integer x=6;
		Integer y=6;
		
		if( x == y) {
			System.out.println("same");
		}
		
		System.out.println(  Integer.valueOf(a[0]) instanceof Integer );
		System.out.println(  vars.get("peter") instanceof Integer );
		
		if (vars.get("peter") == Integer.valueOf(a[0]) ) {
			System.out.println("rimligt");
		} else{
			System.out.println("ORIMLIGT!!");
			System.out.println("för " + vars.get("peter"));
			System.out.println("är inte " + Integer.valueOf(a[0]));
		}
	}
}
