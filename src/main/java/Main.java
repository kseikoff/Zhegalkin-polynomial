import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = 0;
        try {
            n = Integer.parseInt(reader.readLine());
        } catch (Exception e) {
            e.printStackTrace();
        }

        ArrayList<Integer> zhegalkin_polynomial = new ArrayList<>();
        StringBuilder result = new StringBuilder();
        int[][] table = new int[(int) Math.pow(2, n)][n + 1];
        int[] column = new int[(int) Math.pow(2, n)];

        for(int i = 0; i < Math.pow(2, n); i++){
            String temp = "";
            while(temp.length() != n + 1){
                try {
                    temp = reader.readLine().replace(" ", "");
                } catch (Exception e) {
                    e.printStackTrace();
                }
                for(int j = 0; j < temp.length(); j++){
                    table[i][j] = temp.charAt(j) - '0';
                    column[i] = table[i][j];
                }
            }
        }
        zhegalkin_polynomial.add(column[0]);
        while(zhegalkin_polynomial.size() != (int) Math.pow(2, n)){
            for(int i = 0; i < column.length - 1; i++){
                column[i] = (column[i] + column[i + 1]) % 2;
            }
            int[] temp = new int[column.length - 1];
            System.arraycopy(column, 0, temp, 0, temp.length);
            column = temp;
            zhegalkin_polynomial.add(column[0]);
        }
        for(int i = 0; i < zhegalkin_polynomial.size(); i++){
            if(zhegalkin_polynomial.get(i) == 1){
                result.append(" + ");
                StringBuilder temp = new StringBuilder().append(Integer.toBinaryString(i));
                if(i == 0){
                    result.append("1");
                }
                else if(temp.length() < n){
                    temp = new StringBuilder("0".repeat(n - temp.length()) + temp);
                }
                for(int j = 0; j < temp.length(); j++){
                    if(temp.charAt(j) == '1'){
                        result.append((char)('a' + j));
                    }
                }
/*              we don't really care about the entered variable values in the table
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