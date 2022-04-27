package JavaTester;

public class Topic_02_And_Or {
	
	public static void main(String[] args) {
		boolean answerPersonA;
		boolean answerPersonB;
		boolean resultC;
		
		//And
		answerPersonA = true;
		answerPersonB = false;
		System.out.println("1. A && B = " + (answerPersonA && answerPersonB));
		System.out.println("1. A || B = " + (answerPersonA || answerPersonB));
		
		answerPersonA = false;
		answerPersonB = true;
		resultC = answerPersonA && answerPersonB;
		System.out.println("1. A && B = " + (answerPersonA && answerPersonB));
		System.out.println("1. A || B = " + (answerPersonA || answerPersonB));
		
		
		answerPersonA = false;
		answerPersonB = false;
		System.out.println("1. A && B = " + (answerPersonA && answerPersonB));
		System.out.println("1. A || B = " + (answerPersonA || answerPersonB));
		
		
		answerPersonA = true;
		answerPersonB = true;
		System.out.println("1. A && B = " + (answerPersonA && answerPersonB));
		System.out.println("1. A || B = " + (answerPersonA || answerPersonB));
		
	}
	
}