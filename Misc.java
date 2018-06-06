
public class Misc { //will come in handy
	public static double map(double x, double a, double b, double c, double d) {
		return (x-a)/(b-a) * (d-c) + c;
	}
	
	public static double map(double x, double a, double b, double c, double d, double min, double max) {
		double val = (x-a)/(b-a) * (d-c) + c;
		if(val > max) return max;
		else if(val < min) return min;
		return val;
	}
	
	public static double constrain(double x, double min, double max) {
		if(x > max) return max;
		else if(x < min) return min;
		return x;
	}
}
