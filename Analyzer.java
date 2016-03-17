import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * @author Ian Hoegen
 * @serial CS 143 T-TH 10:30-12:30
 */
public class Analyzer
{
    Analyzer(File inputFile)
    {
        try
        {
            Scanner input = new Scanner(inputFile);
            while (input.hasNext())
            {
                String currentLine = input.nextLine().replaceAll("\"", "");
                String[] delimited = currentLine.split(",");
                if (ZipCode.zipList.containsKey(delimited[0]))
                {
                    ZipCode.zipList.get(delimited[0]).compare(Double.parseDouble(delimited[1]), Double.parseDouble(delimited[2]), Double.parseDouble(delimited[3]), Double.parseDouble(delimited[4]));
                } else if (!delimited[0].equalsIgnoreCase("ZipCode"))
                {
                    ZipCode.zipList.put(delimited[0], new ZipCode(delimited[0], Double.parseDouble(delimited[1]), Double.parseDouble(delimited[2]), Double.parseDouble(delimited[3]), Double.parseDouble(delimited[4])));
                }
            }
            new MainGUI();
        } catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.exit(1);
        }

    }

    public static void saveToFile(File destination)
    {
        try
        {
            PrintWriter printWriter = new PrintWriter(new FileWriter(destination, true));
            for (String z : ZipCode.zipList.keySet())
            {
                ZipCode current = ZipCode.zipList.get(z);
                printWriter.print(current.label + current.toString());
            }
            printWriter.close();
        } catch (Exception e)
        {
            System.exit(1);
        }
    }
}