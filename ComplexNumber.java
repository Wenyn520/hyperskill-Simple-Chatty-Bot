//coding exercise 34 - Complex operations
public class ComplexNumber {
    private double real, imaginary;

    public ComplexNumber(double real, double imaginary) {
        this.real = real;
        this.imaginary = imaginary;
    }

    public double getReal() {
        return this.real;
    }

    public double getImaginary() {
        return this.imaginary;
    }
    public void add (ComplexNumber complexNumber){
        add(complexNumber.real, complexNumber.imaginary);
    }
    public void add (double real, double imaginary){
        this.real += real;
        this.imaginary += imaginary;
    }
    public void subtract (ComplexNumber complexNumber){
        subtract(complexNumber.real, complexNumber.imaginary);
    }
    public void subtract (double real, double imaginary){
        this.real -= real;
        this.imaginary -= imaginary;
    }
}
