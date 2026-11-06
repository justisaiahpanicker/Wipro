import java.io.*;
import java.util.*;

class UserMainCode
{
    public int AddSub(int input1, int input2) {
        int result = input1;
        boolean add = (input2 == 2);
        for (int i = input1 - 1; i >= 1; i--)
        {
            if (add)
            {
                result += i;
            }
            else
            {
                result -= i;
            }
            add = !add;
        }
        return result;
    }
}
