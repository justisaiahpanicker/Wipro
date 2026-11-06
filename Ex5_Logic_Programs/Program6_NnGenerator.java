import java.io.*;
import java.util.*;

class UserMainCode
{
    public int nnGenerator(String input1) {
        StringBuilder result = new StringBuilder();
        int i = 0;
        int n = input1.length();

        while (i < n)
        {
            int firstDigit = input1.charAt(i) - '0';
            boolean startIsOdd = (firstDigit % 2 != 0);
            int currentSum = firstDigit;
            i++;

            while (i < n && (currentSum % 2 != 0) == startIsOdd)
            {
                currentSum += (input1.charAt(i) - '0');
                i++;
            }
            result.append(currentSum);
        }
        return Integer.parseInt(result.toString());
    }
}
