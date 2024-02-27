import java.util.Arrays;
import java.util.*;

public class OperadoraBinaria {

    public static void main(String[] args) {
        
        //MENU
        boolean salir = false;
        String seleccion;
        Scanner scan = new Scanner(System.in);

        while (!salir) {
            System.out.println("\n===CALCULADORA BINARA===\n");
            System.out.println("1. Binario en formato IEEE 754 de precisión simple a decimal");
            System.out.println("2. Decimal a binario en formato IEEE 754 de precisión simple");
            System.out.println("3. Salir");
            seleccion = scan.nextLine();

            switch (seleccion) {
                case "1":
                    System.out.println("\nIngrese su numero binario formato IEEE 754 de precisión simple. Tiene que ser de 32 bits");
                    String decimalNum = scan.nextLine();
                    System.out.println("\nSu numero en decimal es: "+ binaryOperationIEEE(decimalNum));
                    break;
                case "2":
                    System.out.println("\nIngrese su numero entero para pasarlo a complemento a 2. Que este en el rango de numeros de 8 bits");
                    double userDecimal = Double.parseDouble(scan.nextLine());
                    String decimalToBinary = decimalToBinaryOperationIEEE(userDecimal);
                    System.out.println("\nSu numero es: " + decimalToBinary);
                    break;
                case "3":
                    System.out.println("\nHasta pronto");
                    scan.close();
                    salir = true;
                default:
                    break;
            }

        }
    }

    public static String binaryToC2(String binaryNumber){
        char[] binaryNumberChars = binaryNumber.toCharArray();

        for(int i = 0; i < binaryNumberChars.length; i++){
            switch (binaryNumberChars[i]) {
                case '1':
                    binaryNumberChars[i] = '0';
                    break;
                case '0':
                    binaryNumberChars[i] = '1';
                default:
                    break;
            }
        }

        binaryNumber = String.valueOf(binaryNumberChars);        
        return decimalToBinaryOperation(binaryOperation(binaryNumber) + 1, 8);
    }

    public static Double binaryOperation(String binaryNumber){
        String[] separatedNumbers = binaryNumber.split("");
        invertUsingFor(separatedNumbers);

        double currentNumber = 0;
        double result = 0;

        for(int i = 0 ; i < separatedNumbers.length; i++){

            currentNumber = Math.pow(2, i);;
            result += currentNumber * Integer.parseInt(separatedNumbers[i]);
        }

        return result;
    }

    public static Double binaryOperationIEEE(String binaryNumber){
        if(binaryNumber.length() <= 32){
            int missingBits = 32-binaryNumber.length();
            for(int i = 0; i < missingBits; i++){
                binaryNumber += "0";
            } 
        }
        String[] separatedNumbers = binaryNumber.split("");
        String[] invertedSeparatedNumbers = binaryNumber.split("");
        invertUsingFor(invertedSeparatedNumbers);

        int sign = Integer.parseInt(separatedNumbers[0]) == 0 ? 1 : -1;

        double currentNumber = 0;
        double exponent = 0;
        double fraction = 0;

        //Exponente
        for(int i = 23 ; i < 31; i++){
            currentNumber = Math.pow(2, (i-23));;
            exponent += currentNumber * Integer.parseInt(invertedSeparatedNumbers[i]);
        }

        //Fracción
        for(int i = 10 ; i < 33; i++){
            currentNumber = Math.pow(2, -(i-9));;
            fraction += currentNumber * Integer.parseInt(separatedNumbers[i-1]);
        }

        String fractionString = String.valueOf(fraction);
        String[] fractionSplitted = fractionString.split("\\.");

        fractionString = "1."+fractionSplitted[1]; 

        Double myDecimal = sign*Double.parseDouble(fractionString)*(Math.pow(2, exponent-127));

        return myDecimal;
    }

    public static String decimalToBinaryOperationIEEE(double decimalNumber){
        int sign = (decimalNumber < 0) ? 1 : 0;

        if(decimalNumber < 0){
            decimalNumber *= -1;
        }

        //PARTE ENTERA
        double temp = decimalNumber;
        String binaryNumberReverseInt = "";
        String realBinaryNumber = "";
        int residue;
        int cociente;
        for(int i = 0; i < 8; i++){
            cociente = (int)temp / 2;
            residue = (int)temp % 2;
            temp = cociente;
            binaryNumberReverseInt += ""+residue;
        }

        for (int i = binaryNumberReverseInt.length() - 1; i >= 0; i--) {
			// Y vamos concatenando cada carácter a la nueva cadena
			realBinaryNumber += binaryNumberReverseInt.charAt(i);
		}

        //FRACCION
        String[] decimalNumberSplit = String.valueOf(decimalNumber).split("\\.");
        temp = Double.parseDouble("."+decimalNumberSplit[1]);
        String binaryNumberFrac = "";
        double product;
        for(int i = 0; i < 23; i++){
            product = temp * 2;
            String productChars[] = String.valueOf(product).split("\\.");
            if(productChars[1].contains("E"))
                productChars[0] = "0";
            temp = Double.parseDouble("."+productChars[1]);
            binaryNumberFrac += ""+productChars[0];
        }

        //OBTENER EXPONENTE
        boolean firstOne = false;
        boolean dotOnFirstOne = false;
        boolean dot = false;
        String completeNumber = realBinaryNumber+"."+binaryNumberFrac;
        System.out.println(completeNumber);

        String[] completeNumberChars = completeNumber.split(""); 
        int i = 0;
        int j = 0;
        while (!firstOne && !dotOnFirstOne) {
            if(completeNumberChars[i].equals("1"))
                firstOne = true;
            else if(completeNumberChars[i].equals(".")){
                dotOnFirstOne = true;
                firstOne = true;
                i = 9;
            }

            if(!dotOnFirstOne)
                i++;
            System.err.println("Ciclo i: " + i);
        }

        while(!dot){
            if(completeNumberChars[j].equals("."))
                dot = true;
            if(!dot)
                j++;
            System.err.println("Ciclo j: " + j);
        }
        int exponent = 127 + (j-i);
        System.out.println("i final: " + i);
        System.out.println("j final: " + j);
        System.out.println("Exponente: " + exponent);

        completeNumberChars[i-1] = ".";
        completeNumber = "";
        for(String completeNumberChar : completeNumberChars)
            completeNumber += completeNumberChar;

        System.out.println(completeNumber);
        completeNumberChars = completeNumber.split("\\.");

        if(!dotOnFirstOne)
            return sign+" "+decimalToBinaryOperation(exponent, 8)+" "+completeNumberChars[1]+completeNumberChars[2];
        else
            return sign+" "+decimalToBinaryOperation(exponent, 8)+" "+completeNumberChars[1];
    }

    public static String decimalToBinaryOperation(double decimalNumber, int bits){
        double originalNumber = decimalNumber;
        if(decimalNumber < 0){
            decimalNumber *= -1;
        }

        double temp = decimalNumber;
        String binaryNumberReverse = "";
        String realBinaryNumber = "";
        int residue;
        int cociente;
        for(int i = 0; i < bits; i++){
            cociente = (int)temp / 2;
            residue = (int)temp % 2;
            temp = cociente;
            binaryNumberReverse += ""+residue;
        }
        
		for (int i = binaryNumberReverse.length() - 1; i >= 0; i--) {
			// Y vamos concatenando cada carácter a la nueva cadena
			realBinaryNumber += binaryNumberReverse.charAt(i);
		}

        if(originalNumber < 0){
            char[] realBinaryNumberChars = realBinaryNumber.toCharArray();
            realBinaryNumberChars[0] = '1';
            realBinaryNumber = String.valueOf(realBinaryNumberChars);
        }

        return realBinaryNumber;
    }

    
    public static void invertUsingFor(String[] array) {
        for (int i = 0; i < array.length / 2; i++) {
            String temp = array[i];
            array[i] = array[array.length - 1 - i];
            array[array.length - 1 - i] = temp;
        }
    }
}