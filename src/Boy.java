// Import statements
import java.util.ArrayList;

public class Boy {
	
	// Properties
	String name;
	int points;
	ArrayList <String> conv;
	
	public Boy () {
		this.points = 0;
		this.conv = new ArrayList <String> ();
		this.name = "";
	}
	
	
	public Boy (String name, ArrayList <String> conv) {
		this.name = name;
		this.points = 0;
		this.conv = conv;
	}
	

}
