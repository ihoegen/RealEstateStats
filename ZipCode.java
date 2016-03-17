import java.text.DecimalFormat;
import java.util.TreeMap;

/**
 *
 */
public class ZipCode
{
    public DecimalFormat DECIMAL_FORMAT = new DecimalFormat("#.00");
    public DecimalFormat MONEY = new DecimalFormat("#,###.00");

    private String zipcode;
    private int highPrice = Integer.MIN_VALUE;
    private int lowPrice = 100000000;
    private double totalPrice;
    private int biggest = Integer.MIN_VALUE;
    private int smallest = Integer.MAX_VALUE;
    private double totalSize;
    private double totalBeds;
    private double totalBaths;
    private int total = 0;
    public String label;
    public static TreeMap<String, ZipCode> zipList = new TreeMap<>();

    public ZipCode(String zipcode, double price, double beds, double baths, double sqft)
    {
        this.zipcode = zipcode;
        label = "\n\n\nZip Code: " + zipcode + "\n\n";
        compare(price, beds, baths, sqft);
    }

    public void compare(double price, double beds, double baths, double sqft)
    {
        if (price >= highPrice)
            highPrice = (int) price;
        if (price <= lowPrice)
            lowPrice = (int) price;
        if (sqft >= biggest)
            biggest = (int) sqft;
        if (sqft <= smallest)
            smallest = (int) sqft;
        ++total;
        totalPrice = (totalPrice + price);
        totalSize += sqft;
        totalBaths += baths;
        totalBeds += beds;
    }

    public String toString()
    {
        return "Average Price: $" + MONEY.format(getTotalPrice() / total) +
                "\n\nMost Expensive: $" + MONEY.format(getHighPrice()) + "\n\nLeast Expensive: $" + MONEY.format(getLowPrice()) + "\n\nAverage Size: " + DECIMAL_FORMAT.format(getTotalSize() / total) +
                "\n\nLargest size: " + getBiggest() + "\n\nSmallest size: " + getSmallest() / total + "\n\nAverage Bedrooms: " + DECIMAL_FORMAT.format(getTotalBeds() / total) +
                "\n\nAverage Bathrooms: " + DECIMAL_FORMAT.format(getTotalBaths() / total) + "\n\nTotal: " + getTotal();

    }

    public double getTotalPrice()
    {
        return totalPrice;
    }

    public String getZipcode()
    {
        return zipcode;
    }

    public int getHighPrice()
    {
        return highPrice;
    }

    public int getLowPrice()
    {
        return lowPrice;
    }

    public int getBiggest()
    {
        return biggest;
    }

    public int getSmallest()
    {
        return smallest;
    }

    public double getTotalSize()
    {
        return totalSize;
    }


    public double getTotalBeds()
    {
        return totalBeds;
    }

    public double getTotalBaths()
    {
        return totalBaths;
    }

    public int getTotal()
    {
        return total;
    }
}
