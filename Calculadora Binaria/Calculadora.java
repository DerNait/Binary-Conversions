import java.util.*;

public class Calculadora {

    public static void main(String[] args) {
        
        //MENU
        boolean salir = false;
        String seleccion;
        Scanner scan = new Scanner(System.in);

        while (!salir) {
            System.out.println("\n===CALCULADORA BINARA===\n");
            System.out.println("1. Convertir un decimal a binario");
            System.out.println("2. Convertir decimal en complemento a dos");
            System.out.println("3. Sumar dos binarios");
            System.out.println("4. Salir");
            seleccion = scan.nextLine();

            switch (seleccion) {
                case "1":
                    double decimalNum;
                    System.out.println("\nIngrese su numero entero para pasarlo a binario. Que este en el rango de numeros de 8 bits");
                    decimalNum = Double.parseDouble(scan.nextLine());
                    System.out.println("\nSu numero en binario es: "+ decimalToBinaryOperation(decimalNum,8));
                    break;
                case "2":
                    System.out.println("\nIngrese su numero entero para pasarlo a complemento a 2. Que este en el rango de numeros de 8 bits");
                    decimalNum = Double.parseDouble(scan.nextLine());
                    String decimalToBinary = decimalToBinaryOperation(decimalNum, 8);
                    System.out.println("\nSu numero en complemento a 2 es: " + binaryToC2(decimalToBinary));
                    break;
                case "3":
                    System.out.println("\nIngrese el primer numero binario. Que este en 8 bits");
                    String binaryNum1 = scan.nextLine();
                    System.out.println("\nIngrese el segundo numero binario. Que este en 8 bits");
                    String binaryNum2 = scan.nextLine();
                    double resultSum = binaryOperation(binaryNum1) + binaryOperation(binaryNum2);
                    System.out.println("\nEl resultado de la suma binaria es de: " + decimalToBinaryOperation(resultSum, 8));
                    break;
                case "4":
                    System.out.println("\nHasta pronto");
                    scan.close();
                    salir = true;
                default:
                    break;
            }

        }

        //BINARIA A DECIMAL
        String binaryNumber = "101011110001";
        double binaryResult = binaryOperation(binaryNumber);
        System.out.println("\nEl resultado del numero binario: " + binaryNumber + " es igual a: " + binaryResult + "\n");

        //HEXADECIMAL A DECIMAL
        String hexNumber = "19F";
        double hexResult = hexOperation(hexNumber);
        System.out.println("\nEl resultado del numero hexadecimal: " + hexNumber + " es igual a: " + hexResult + "\n");

        //HEXADECIMAL A BINARIA
        String hexNumber2 = "1AB";
        String binaryResult2 = hexToBCD(hexNumber2);
        System.out.println("\nEl Hexadecimal: "+ hexNumber2 +" en Binario es: " + binaryResult2 + "\n");

        //DECIMAL A BINARIA
        double decimalNumber = 204;
        int bits = 8;
        binaryNumber = decimalToBinaryOperation(decimalNumber, bits);
        System.out.println("\nEl decimal: "+ decimalNumber +" en Binario es: " + binaryNumber + "\n");

        //DECIMAL A HEXADECIMAL
        double decimalHexNumber = 427;
        hexNumber = decimalToHexOperation(decimalHexNumber);
        System.out.println("\nEl decimal: "+ decimalHexNumber +" en Hexadecimal es: " + hexNumber + "\n");
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
        
        System.out.println(binaryNumber);

        String addOneBinary = "00000001";
        double addOne = binaryOperation(binaryNumber) + binaryOperation(addOneBinary);

        System.out.println(addOne);
        
        return decimalToBinaryOperation(addOne, 8);
    }

    public static String hexToBCD(String hexNumber){
        String[] separatedNumbersHex = hexNumber.split("");
        String hexToBinary = "";

        for(String s : separatedNumbersHex){
            switch (s) {
                case "0":
                    hexToBinary += "0000";
                    break;
                case "1":
                    hexToBinary += "0001";
                    break;
                case "2":
                    hexToBinary += "0010";
                    break;
                case "3":
                    hexToBinary += "0011";
                    break;
                case "4":
                    hexToBinary += "0100";
                    break;
                case "5":
                    hexToBinary += "0101";
                    break;
                case "6":
                    hexToBinary += "0110";
                    break;
                case "7":
                    hexToBinary += "0111";
                    break;
                case "8":
                    hexToBinary += "1000";
                    break;
                case "9":
                    hexToBinary += "1001";
                    break;
                case "A":
                    hexToBinary += "1010";
                    break;
                case "B":
                    hexToBinary += "1011";
                    break;
                case "C":
                    hexToBinary += "1100";
                    break;
                case "D":
                    hexToBinary += "1101";
                    break;
                case "E":
                    hexToBinary += "1110";
                    break;
                case "F":
                    hexToBinary += "1111";
                    break;
            }
        }

        return hexToBinary;
    }

    public static double hexOperation(String hexToBinary){
        String binaryNumber = hexToBCD(hexToBinary);
        return binaryOperation(binaryNumber);
    }

    public static String decimalToHexOperation(double hexNumber){
            double temp = hexNumber;
            String hexNumberReverse = "";
            String realHexNumber = "";
            int residue;
            int cociente;

            for(int i = 0; i < (Integer.toString((int) hexNumber)).length(); i++){
                String residueCharacter = "";
                cociente = (int)temp / 16;
                residue = (int)temp % 16;
                temp = cociente;

                if(residue >= 10){
                    switch (residue) {
                        case 10:
                            residueCharacter = "A";
                            break;
                        case 11:
                            residueCharacter = "B";
                            break;
                        case 12:
                            residueCharacter = "C";
                            break;
                        case 13:
                            residueCharacter = "D";
                            break;
                        case 14:
                            residueCharacter = "E";
                            break;
                        case 15:
                            residueCharacter = "F";
                            break;            
                    }
                }else{
                    residueCharacter = Integer.toString(residue);
                }
                
                if(!residueCharacter.equals("0"))
                    hexNumberReverse += residueCharacter;
            }

            for (int i = hexNumberReverse.length() - 1; i >= 0; i--) {
                // Y vamos concatenando cada carácter a la nueva cadena
                realHexNumber += hexNumberReverse.charAt(i);
            }

            return realHexNumber;
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