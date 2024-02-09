public class Calculadora {

    public static void main(String[] args) {
        
        //BINARIA
        String binaryNumber = "00001111";
        double binaryResult = binaryOperation(binaryNumber);
        System.out.println("\nEl resultado del numero binario: " + binaryNumber + " es igual a: " + binaryResult + "\n");

        //HEXADECIMAL
        String hexNumber = "19F";
        double hexResult = hexOperation(hexNumber);
        System.out.println("\nEl resultado del numero hexadecimal: " + hexNumber + " es igual a: " + hexResult + "\n");

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

    public static void invertUsingFor(String[] array) {
        for (int i = 0; i < array.length / 2; i++) {
            String temp = array[i];
            array[i] = array[array.length - 1 - i];
            array[array.length - 1 - i] = temp;
        }
    }
}