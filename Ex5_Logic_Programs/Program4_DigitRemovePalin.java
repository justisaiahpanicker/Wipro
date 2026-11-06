import java.io.*;
import java.util.*;

class UserMainCode
{
    public int digitRemove_Palin(int input1) {
        String s = String.valueOf(input1);

        if (isPalindrome(s))
        {
            return -1;
        }
        for (int i = 0; i < s.length(); i++)
        {
            String modified = s.substring(0, i) + s.substring(i + 1);
            if (isPalindrome(modified)) {
                return s.charAt(i) - '0';
            }
        }
        return -1;
    }

    private static boolean isPalindrome(String str)
    {
        int left = 0;
        int right = str.length() - 1;
        while (left < right)
        {
            if (str.charAt(left) != str.charAt(right))
            {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
}
