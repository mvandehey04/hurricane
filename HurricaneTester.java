/**
 * Starter code for the Hurricane Tester
 * Madeline Vande Hey 3/3/22
 *
 */
 
import java.io.IOException;
//import java.io.PrintWriter;
import java.io.File;
import java.util.Scanner;
import java.util.ArrayList;
public class HurricaneTester

{

    public static void main(String[] args) throws IOException
    {
        //read data from text file & put in an array
        File fileName = new File("hurricanedata.txt");
        Scanner inFile = new Scanner(fileName);
        int numValues = 0;
        
        //count number of lines in text file
        while (inFile.hasNextLine())
        {
            inFile.nextLine();
            numValues++;
        }
        inFile.close();
        
        
        //initialize arrays based on lines counted in text file
        int [] years = new int[numValues];
        String [] months = new String[numValues];
        int [] pressures = new int[numValues];
        double [] windSpeeds = new double[numValues];
        String [] names = new String[numValues];
        
        //read and assign data from text file to the arrays
        inFile = new Scanner(fileName);
        int index = 0;
        while(inFile.hasNext() )
        {
            years[index] = inFile.nextInt();
            months[index] = inFile.next();
            pressures[index] = inFile.nextInt();
            windSpeeds[index] = inFile.nextDouble();
            names[index] = inFile.next();
            index++;
        }
        inFile.close();

        //create a Hurricane ArrayList using the data above
        ArrayList<Hurricane> hurricaneList = new ArrayList<Hurricane>();
        
        //convert the windspeed, determine categories, calculate sums
        Hurricane hurricane = null;
        int cat1 = 0;
        int cat2 = 0;
        int cat3 = 0;
        int cat4 = 0;
        int cat5 = 0;

        for(int i = 0; i < years.length; i++){
            // category 1
            if(windSpeeds[i] * 1.15078 < 95){
                hurricane = new Hurricane(years[i], names[i], months[i], 1, pressures[i], windSpeeds[i] * 1.15078);
            }
            // category 2
            else if(windSpeeds[i] * 1.15078 >= 96 && windSpeeds[i] <= 110){
                hurricane = new Hurricane(years[i], names[i], months[i], 2, pressures[i], windSpeeds[i] * 1.15078);
            }
            // category 3
            else if(windSpeeds[i] * 1.15078 >= 111 && windSpeeds[i] <= 129){
                hurricane = new Hurricane(years[i], names[i], months[i], 3, pressures[i], windSpeeds[i] * 1.15078);
            }
            // category 4
            else if(windSpeeds[i] * 1.15078 >= 130 && windSpeeds[i] <= 156){
                hurricane = new Hurricane(years[i], names[i], months[i], 4, pressures[i], windSpeeds[i] * 1.15078);
            }
            // category 5
            else if(windSpeeds[i] * 1.15078 >= 157){
                hurricane = new Hurricane(years[i], names[i], months[i], 5, pressures[i], windSpeeds[i] * 1.15078);
            }
            hurricaneList.add(hurricane);
            
        }
        
        //user prompted for range of years
        Scanner collectRange = new Scanner(System.in);
        System.out.println("Give a range of years between 1995 and 2019." + "\n" + "EX: 1995-2003");
        String range = collectRange.nextLine();
        //System.out.println(range);
        collectRange.close();

        // set range into 2 years
        range = range.trim();
        String year1 = range.substring(0,4);
        String year2 = range.substring(5,9);
        int yearone = Integer.parseInt(year1);
        int yeartwo = Integer.parseInt(year2);

        // check correct format and correct year range
        if(range.length() < 9 || range.length() > 9){
            System.out.println("Please use the correct format to display data.");
        }
        else if(yearone < 1995 || yearone > 2019 || yeartwo < 1995 || yeartwo > 2019){
            System.out.println("Please choose a range of years between 1995 and 2019.");
        }
        else{
            // declare average variables
            double avgCat = 0;
            double avgPress = 0;
            double avgWind = 0;
            int count = 0;

            // print the data
            System.out.println();
            System.out.printf("%36s %s %s %s %s", "Hurricanes ", year1, " - ", year2, "\n");
            System.out.println();
            System.out.printf("%5s %14s %13s %17s %20s %s", "Year", "Hurricane", "Category", "Pressure (mb)", "Wind Speed (mph)", "\n");
            System.out.println("==========================================================================");
            for(Hurricane h : hurricaneList){
                if(yearone <= h.getYear() && yeartwo >= h.getYear()){
                    // toString print hurricane data
                    System.out.println(" " + h);

                    // add to averages
                    avgCat += h.getCat();
                    avgPress += h.getPressure();
                    avgWind += h.getWindspeed();

                    // add to count
                    count++;

                    // category count
                    if(h.getCat() == 1){
                        cat1 ++;
                    }
                    if(h.getCat() == 2){
                        cat2 ++;
                    }
                    if(h.getCat() == 3){
                        cat3 ++;
                    }
                    if(h.getCat() == 4){
                        cat4 ++;
                    }
                    if(h.getCat() == 5){
                        cat5 ++;
                    } 
                }
            }
            System.out.println("==========================================================================");

            // calculate averages
            avgCat = avgCat / count;
            avgPress = avgPress / count;
            avgWind = avgWind / count;

            System.out.printf("%6s %8s %16.1f %13.0f %20.2f", "", "Average:", avgCat, avgPress, avgWind);
            System.out.println();

            // calculate min and max
            double minCat = Integer.MAX_VALUE;
            double maxCat = Integer.MIN_VALUE;
            double minPress = Integer.MAX_VALUE;
            double maxPress = Integer.MIN_VALUE;
            double minWind = Integer.MAX_VALUE;
            double maxWind = Integer.MIN_VALUE;

            for(Hurricane h : hurricaneList){
                if(yearone <= h.getYear() && yeartwo >= h.getYear()){
                // category
                if(h.getCat() < minCat){
                    minCat = h.getCat();
                }
                if(h.getCat() > maxCat){
                    maxCat = h.getCat();
                }

                // pressure
                if(h.getPressure() < minPress){
                    minPress = h.getPressure();
                }
                if(h.getPressure() > maxPress){
                    maxPress = h.getPressure();
                }

                // wind
                if(h.getWindspeed() < minWind){
                    minWind = h.getWindspeed();
                }
                if(h.getWindspeed() > maxWind){
                    maxWind = h.getWindspeed();
                }
            }
            }

            // max and mins
            System.out.printf("%6s %s %14.0f %15.0f %20.2f", "", "Minimum:", minCat, minPress, minWind);
            System.out.printf("\n %5s %s %14.0f %15.0f %20.2f", "", "Maximum:", maxCat, maxPress, maxWind);

            // summary of cats
            System.out.println();
            System.out.printf("\n %s", "Summary of Categories:");
            System.out.printf("\n %7s %2d", "Cat 1: ", cat1);
            System.out.printf("\n %7s %2d", "Cat 2: ", cat2);
            System.out.printf("\n %7s %2d", "Cat 3: ", cat3);
            System.out.printf("\n %7s %2d", "Cat 4: ", cat4);
            System.out.printf("\n %7s %2d", "Cat 5: ", cat5);
            System.out.println();

        }
        
        
     }
}