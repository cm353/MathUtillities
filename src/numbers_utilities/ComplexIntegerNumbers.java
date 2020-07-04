package numbers_utilities;

import java.math.BigDecimal;
import java.math.BigInteger;

public class ComplexIntegerNumbers extends Number implements Comparable {

    private final BigInteger realPart;
    private final BigInteger imaginaryPart;

    public ComplexIntegerNumbers(String value){
//TODO: implement cases where only Im or Re is passed
        String realString = value.split("/+")[0];
        String imaginaryString = value.split("/+")[1];
        this.realPart = new BigInteger(realString);
        this.imaginaryPart = new BigInteger(imaginaryString);
    }

    public BigInteger getRealPart() {
        return realPart;
    }

    public BigInteger getImaginaryPart() {
        return imaginaryPart;
    }

    //TODO
    public BigDecimal absoluteValue(){
        return null;
    }

    //TODO
    @Override
    public int intValue() {
        return 0;
    }

    //TODO
    @Override
    public long longValue() {
        return 0;
    }

    //TODO
    @Override
    public float floatValue() {
        return 0;
    }

    //TODO
    @Override
    public double doubleValue() {
        return 0;
    }

    //TODO
    @Override
    public int hashCode() {
        return super.hashCode();
    }

    //TODO
    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    //TODO
    @Override
    public int compareTo(Object o) {
        return 0;
    }
}
