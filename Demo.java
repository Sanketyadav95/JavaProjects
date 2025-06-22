import Calculator.Calculate; 
 
public class Demo { 
    public static void main(String[] args) { 
        Calculate calc = new Calculate(); 
 
        int a = 10, b = 5; 
 
        System.out.println("Addition: " + calc.add(a, b)); 
        System.out.println("Subtraction: " + calc.sub(a, b)); 
        System.out.println("Multiplication: " + calc.mul(a, b)); 
    } 
}
