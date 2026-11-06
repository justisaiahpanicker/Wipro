import java.io.*;
import java.util.*;

class UserMainCode
{
    public String moveRobot(int input1, int input2, String input3, String input4) {
        String[] startParts = input3.split("-");
        int x = Integer.parseInt(startParts[0]);
        int y = Integer.parseInt(startParts[1]);
        char dir = startParts[2].charAt(0);
        char[] directions = {'N', 'E', 'S', 'W'};
        int dirIndex = 0;
        for (int i = 0; i < directions.length; i++)
        {
            if (directions[i] == dir)
            {
                dirIndex = i;
                break;
            }
        }

        boolean isBlocked = false;
        for (int i = 0; i < input4.length(); i++)
        {
            char cmd = input4.charAt(i);

            if (cmd == 'L')
            {
                dirIndex = (dirIndex + 3) % 4;
            }
            else if (cmd == 'R')
            {
                dirIndex = (dirIndex + 1) % 4;
            }
            else if (cmd == 'M')
            {
                int newX = x;
                int newY = y;

                char currentDir = directions[dirIndex];

                if (currentDir == 'N') newY++;
                else if (currentDir == 'E') newX++;
                else if (currentDir == 'S') newY--;
                else if (currentDir == 'W') newX--;

                if (newX < 0 || newX > input1 || newY < 0 || newY > input2)
                {
                    isBlocked = true;
                    break;
                }
                else {
                    x = newX;
                    y = newY;
                }
            }
        }

        String result = x + "-" + y + "-" + directions[dirIndex];
        if (isBlocked)
        {
            result += "-ER";
        }
        return result;
    }
}
