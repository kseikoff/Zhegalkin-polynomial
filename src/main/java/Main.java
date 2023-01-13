import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int number_of_variables;
        number_of_variables = Integer.parseInt(scanner.nextLine());

        ArrayList<Integer> zhegalkin_polynomial = new ArrayList<>();
        StringBuilder result = new StringBuilder();
        int[] column = new int[(int) Math.pow(2, number_of_variables)];

        for(int i = 0; i < Math.pow(2, number_of_variables); i++){
            String variable_values = "";
            while(variable_values.length() != number_of_variables + 1){
                variable_values = scanner.nextLine().replace(" ", "");
                for(int j = 0; j < variable_values.length(); j++){
                    column[i] = variable_values.charAt(j) - '0';
                }
            }
        }
        zhegalkin_polynomial.add(column[0]);
        int last_element = column.length;
        while(zhegalkin_polynomial.size() != (int) Math.pow(2, number_of_variables)){
            for(int i = 0; i < column.length - 1; i++){
                column[i] = (column[i] + column[i + 1]) % 2;
            }
            last_element--;
            column[last_element] = 0;
            zhegalkin_polynomial.add(column[0]);
        }
        for(int i = 0; i < zhegalkin_polynomial.size(); i++){
            if(zhegalkin_polynomial.get(i) == 1){
                result.append(" + ");
                if(i == 0){
                    result.append("1");
                } else {
                    for(int j = number_of_variables; j >= 0; j--){
                        if((i & (1 << j)) != 0){
                            result.append((char)('a' + number_of_variables - j - 1));
                        }
                    }
                }
            }
        }
        System.out.println(result.length() > 0 ? result.substring(3) : "0");
    }
}