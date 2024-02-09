public class Calculadora {

    public static void main(String[] args) {
        
        //BINARIA A DECIMAL
        String binaryNumber = "11001100";
        double binaryResult = binaryOperation(binaryNumber);
        System.out.println("\nEl resultado del numero binario: " + binaryNumber + " es igual a: " + binaryResult + "\n");

        //HEXADECIMAL A DECIMAL
        String hexNumber = "19F";
        double hexResult = hexOperation(hexNumber);
        System.out.println("\nEl resultado del numero hexadecimal: " + hexNumber + " es igual a: " + hexResult + "\n");

        //DECIMAL A BINARIA
        double decimalNumber = 204;
        binaryNumber = decimalToBinaryOperation(decimalNumber);
        System.out.println("\nEl decimal: "+ decimalNumber +" en Binario es: " + binaryNumber + "\n");

        //DECIMAL A HEXADECIMAL
        double decimalHexNumber = 427;
        hexNumber = decimalToHexOperation(decimalHexNumber);
        System.out.println("\nEl decimal: "+ decimalHexNumber +" en Hexadecimal es: " + hexNumber + "\n");
    }

    public static Double hexOperation(String hexNumber){
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

        return binaryOperation(hexToBinary);
    }

    public static String decimalToHexOperation(double hexNumber){
            double temp = hexNumber;
            String hexNumberReverse = "";
            String realHexNumber = "";
            int residue;
            int cociente;
            System.out.println((Integer.toString((int) hexNumber)).length());
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

    public static String decimalToBinaryOperation(double decimalNumber){
        double temp = decimalNumber;
        String binaryNumberReverse = "";
        String realBinaryNumber = "";
        int residue;
        int cociente;
        for(int i = 0; i < 8; i++){
            cociente = (int)temp / 2;
            residue = (int)temp % 2;
            temp = cociente;
            binaryNumberReverse += ""+residue;
        }

		for (int i = binaryNumberReverse.length() - 1; i >= 0; i--) {
			// Y vamos concatenando cada carácter a la nueva cadena
			realBinaryNumber += binaryNumberReverse.charAt(i);
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