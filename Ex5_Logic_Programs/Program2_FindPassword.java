import java.io.*;
import java.util.*;

class UserMainCode
{
    public int findPassword(int input1, int input2, int input3, int input4, int input5) {
        int[] inputs = {input1, input2, input3, input4, input5};
        int stableSum = 0;
        int unstableSum = 0;

        for (int num : inputs)
        {
            if (isStable(num))
            {
                stableSum += num;
            }
            else {
                unstableSum += num;
            }
        }
        return stableSum - unstableSum;
    }

    private static boolean isStable(int num)
    {
        int[] count = new int[10];
        int temp = Math.abs(num);
        while (temp > 0)
        {
            count[temp % 10]++;
            temp /= 10;
        }
        int targetFreq = 0;
        for (int i = 0; i < 10; i++)
        {
            if (count[i] > 0)
            {
                if (targetFreq == 0)
                {
                    targetFreq = count[i];
                }
                else if (count[i] != targetFreq)
                {
                    return false;
                }
            }
        }
        return true;
    }
}
