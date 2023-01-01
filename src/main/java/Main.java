import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int number_of_variables;
        number_of_variables = Integer.parseInt(scanner.nextLine());

        ArrayList<Integer> zhegalkin_polynomial = new ArrayList<>();
        StringBuilder result = new StringBuilder();
        int[][] truth_table = new int[(int) Math.pow(2, number_of_variables)][number_of_variables + 1];
        int[] column = new int[(int) Math.pow(2, number_of_variables)];

        for(int i = 0; i < Math.pow(2, number_of_variables); i++){
            String variable_values = "";
            while(variable_values.length() != number_of_variables + 1){
                variable_values = scanner.nextLine().replace(" ", "");
                for(int j = 0; j < variable_values.length(); j++){
                    truth_table[i][j] = variable_values.charAt(j) - '0';
                    column[i] = truth_table[i][j];
                }
            }
        }
        zhegalkin_polynomial.add(column[0]);
        while(zhegalkin_polynomial.size() != (int) Math.pow(2, number_of_variables)){
            for(int i = 0; i < column.length - 1; i++){
                column[i] = (column[i] + column[i + 1]) % 2;
            }
            int[] smaller_column = new int[column.length - 1];
            System.arraycopy(column, 0, smaller_column, 0, smaller_column.length);
            column = smaller_column;
            zhegalkin_polynomial.add(column[0]);
        }
        for(int i = 0; i < zhegalkin_polynomial.size(); i++){
            if(zhegalkin_polynomial.get(i) == 1){
                result.append(" + ");
                StringBuilder binary_number = new StringBuilder().append(Integer.toBinaryString(i));
                if(i == 0){
                    result.append("1");
                }
                else if(binary_number.length() < number_of_variables){
                    binary_number = new StringBuilder("0".repeat(number_of_variables
                            - binary_number.length()) + binary_number);
                }
                for(int j = 0; j < binary_number.length(); j++){
                    if(binary_number.charAt(j) == '1'){
                        result.append((char)('a' + j));
                    }
                }
/*              we don't really need the entered variable values in the table
                  int count = 0;
                  result.append(" + ");
                  for(int j = 0; j < n; j++){
                      if(table[i][j] == 0){
                          count++;
                          if(count == n && table[i][j + 1] == 1){
                              result.append("1");
                          }
                      }
                      if(table[i][j] == 1){
                          result.append((char)('a' + j));
                      }
                }
*/            }
        }
        System.out.println(result.length() > 0 ? result.substring(3) : "0");
    }
}