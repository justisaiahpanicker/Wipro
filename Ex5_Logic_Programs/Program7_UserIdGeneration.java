import java.io.*;
import java.util.*;

class UserMainCode
{
    public String userIdGeneration(String input1, String input2, int input3, int input4) {
        String smallerName = "";
        String longerName = "";

        if (input1.length() < input2.length())
        {
            smallerName = input1;
            longerName = input2;
        }
        else if (input2.length() < input1.length())
        {
            smallerName = input2;
            longerName = input1;
        }
        else
        {
            if (input1.compareTo(input2) < 0)
            {
                smallerName = input2;
                longerName = input1;
            }
            else {
                smallerName = input1;
                longerName = input2;
            }
        }
        String pinStr = String.valueOf(input3);
        int n = input4;
        char lastLetter = smallerName.charAt(smallerName.length() - 1);
        char leftDigit = pinStr.charAt(n - 1);
        char rightDigit = pinStr.charAt(pinStr.length() - n);
        String rawUserId = lastLetter + longerName + leftDigit + rightDigit;

        StringBuilder userId = new StringBuilder();
        for (int i = 0; i < rawUserId.length(); i++)
        {
            char ch = rawUserId.charAt(i);
            if (Character.isUpperCase(ch))
            {
                userId.append(Character.toLowerCase(ch));
            }
            else if (Character.isLowerCase(ch))
            {
                userId.append(Character.toUpperCase(ch));
            }
            else {
                userId.append(ch);
            }
        }

        return userId.toString();
    }
}
