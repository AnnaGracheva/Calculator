
import java.util.Scanner;

public class Calculator {
    public static void main(String[] args) {
        boolean exit = false;
        while (!exit) {
            Scanner in = new Scanner(System.in);
            System.out.println("Введите арифметическую операцию или \"stop\" для завершения программы: ");
            String operation = in.nextLine();
            if (operation.equals("stop")) {
                exit = true;
            } else {
                String[] symbol = operation.split("\\s");
                Numbers scan = new Numbers(symbol[0], symbol[2]);
                if (operation.contains("I") | operation.contains("V") | operation.contains("X")) {
                    if ((scan.getA()).matches("[0-9]+") | (scan.getB()).matches("[0-9]+"))
                        throw new FormatException("Введенные числа разного формата");
                    if ((symbol[1]).equals("+") | symbol[1].equals("-") | symbol[1].equals("*") | symbol[1].equals("/")) {
                        Calculations math = new Calculations(symbol[1], scan.reverseRimToArab(scan.getA()), scan.reverseRimToArab(scan.getB()));
                        Numbers.reverseArabToRim(math.Calculate());
                    } else {
                        throw new WrongArithmeticException("Неверная арифметическая операция");
                    }
                } else {
                    if (symbol[1].equals("+") | symbol[1].equals("-") | symbol[1].equals("*") | symbol[1].equals("/")) {
                        Calculations math = new Calculations(symbol[1], Integer.parseInt(scan.getA()), Integer.parseInt(scan.getB()));
                        math.Calculate();
                    } else {
                        throw new WrongArithmeticException("Неверная арифметическая операция");
                    }
                }
            }
        }
    }
}

class Calculations {
    private int a;
    private int b;
    private String sign;

    public Calculations(String sign, int a, int b) {
        this.a = a;
        this.b = b;
        this.sign = sign;
    }

    public String getSign() {
        return this.sign;
    }

    public int Calculate() {
        int result = 0;
        if (a < 1 | b < 1) throw new TooMinInputException("Введенные числа меньше 1");
        if (a > 10 | b > 10) throw new TooBigInputException("Введенные числа больше 10");
        switch (sign) {
            case ("+"):
                result = a + b;
                break;
            case ("-"):
                result = a - b;
                break;
            case ("*"):
                result = a * b;
                break;
            case ("/"):
                result = a / b;
                break;
        }
        System.out.println("Результат: " + result);
        return result;
    }
}

class Numbers {
    private String a;
    private String b;

    public Numbers(String a, String b) {
        this.a = a;
        this.b = b;
    }

    public String getA() {
        return this.a;
    }

    public String getB() {
        return this.b;
    }

    public int reverseRimToArab(String element) {
        int aR = 0;
        char[] charAR = element.toCharArray();
        switch (element) {
            case ("IV"):
                aR = 4;
                break;
            case ("IX"):
                aR = 9;
                break;
            default:
                for (int j = 0; j < charAR.length; j++) {
                    if (String.valueOf(charAR[j]).equals("X")) {
                        aR = aR + 10;
                    } else {
                        if (String.valueOf(charAR[j]).equals("V")) {
                            aR = aR + 5;
                        } else {
                            if (String.valueOf(charAR[j]).equals("I")) {
                                aR = aR + 1;
                            } else {
                                if (String.valueOf(charAR[j]).equals("-")) {
                                    throw new TooBigInputException("Введенные числа меньше 0");
                                } else {
                                    throw new TooBigInputException("Введенные числа больше 10");
                                }
                            }
                        }
                    }
                }
        }
        return aR;
    }

    public static void reverseArabToRim(int rimResult) {
        if (rimResult <= 0)
            throw new FormatException("Ответ меньше, либо равен нулю. Ответ в арабских цифрах: " + rimResult);
        StringBuilder finalRim = new StringBuilder();
        while (rimResult != 0) {
            if (rimResult == 100) {
                finalRim.append("C");
                rimResult = rimResult - 100;
            } else {
                if (rimResult >= 90) {
                    finalRim.append("XC");
                    rimResult = rimResult - 90;
                } else {
                    if (rimResult >= 50) {
                        finalRim.append("L");
                        rimResult = rimResult - 50;
                    } else {
                        if (rimResult >= 40) {
                            finalRim.append("XL");
                            rimResult = rimResult - 40;
                        } else {
                            if (rimResult >= 10) {
                                finalRim.append("X");
                                rimResult = rimResult - 10;
                            } else {
                                if (rimResult >= 9) {
                                    finalRim.append("IX");
                                    rimResult = rimResult - 9;
                                } else {
                                    if (rimResult >= 5) {
                                        finalRim.append("V");
                                        rimResult = rimResult - 5;
                                    } else {
                                        if (rimResult >= 4) {
                                            finalRim.append("IV");
                                            rimResult = rimResult - 4;
                                        } else {
                                            if (rimResult > 0) {
                                                finalRim.append("I");
                                                rimResult = rimResult - 1;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        System.out.println("Результат в римских цифрах: " + finalRim);
    }
}

class TooMinInputException extends RuntimeException {
    public TooMinInputException(String message) {
        super(message);
    }
}

class TooBigInputException extends RuntimeException {
    public TooBigInputException(String message) {
        super(message);
    }
}

class FormatException extends RuntimeException {
    public FormatException(String message) {
        super(message);
    }
}

class WrongArithmeticException extends RuntimeException {
    public WrongArithmeticException(String message) {
        super(message);
    }
}

