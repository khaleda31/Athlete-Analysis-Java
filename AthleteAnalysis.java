package questionOne;

/**
 * F28PA | Software Development A | Coursework
 * 
 * The Coursework specification is provided in Canvas. Please read through it in
 * full before you start work.
 * 
 * @author Nur Khaleda binti Mohd Hilmi
 */
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.File;

public class AthleteAnalysis {

	public static void main(String[] args) throws FileNotFoundException {
		Scanner scan= new Scanner(System.in);
		System.out.println("Please select one: ");
		System.out.println("1-Enter Data Manually | 2-Read Data from File");//asking the user whether they would like to enter manually or read from the file
		
		int select=0;
		while (!scan.hasNextInt()) { //this will validate the input to ensure it is numeric
		    System.out.println("Invalid input. Please enter either 1 or 2.");
		    scan.next(); 
		}select= scan.nextInt();
				
		if (select==1) { //calling all the methods if the user chooses to enter the data manually
			System.out.println("Enter the number of data entries: ");
			int entry= scan.nextInt();
			
			int[] mMedalCounts = new int[3]; // 0: gold, 1: silver, 2: bronze
			int[] fMedalCounts = new int[3]; // 0: gold, 1: silver, 2: bronze
			String []name = new String [entry];
			String[]gender= new String[entry];
			int[]age= new int[entry];
			double []height= new double[entry];
			double[]weight= new double[entry];
			String[]sport = new String[entry];
			String[] medal= new String[entry];
			
			inputData(name, gender, age, height, weight, sport, medal);
			genderRatio(gender);
			calcStdDevMean(gender, age, weight, height);
			calcOldestYoungest(gender, age, name);
			printUniqueSport(sport);
			medalAllSport(gender, medal,mMedalCounts, fMedalCounts);
			medalEachSport(gender, medal, sport);
			printDetails(name, gender, age, height, weight, sport, medal);
			
		}else if (select==2) { //reading the data from the text file data.txt 
			File text = new File("data.txt");
			Scanner fileRead = new Scanner(text);
			int[] mMedalCounts = new int[3]; // 0: gold, 1: silver, 2: bronze
			int[] fMedalCounts = new int[3]; // 0: gold, 1: silver, 2: bronze
			String []name = new String [20];
			String[]gender= new String[20];
			int[]age= new int[20];
			double []height= new double[20];
			double[]weight= new double[20];
			String[]sport = new String[20];
			String[] medal= new String[20];
			fileRead.useDelimiter(",");
			int numEntry=fileRead.nextInt();
			fileRead.nextLine();
			for(int  j=0;j<numEntry;j++) {
				name[j]=fileRead.next();
				gender[j]=fileRead.next();
				age[j]=Integer.parseInt(fileRead.next());
				height[j]=Double.parseDouble(fileRead.next());
				weight[j]=Double.parseDouble(fileRead.next());
				sport[j]=fileRead.next();
				String medal1=fileRead.nextLine();
				String medal2=medal1.substring(1,medal1.length());
				medal[j]=medal2;
			}
			scan.close(); //and calling all the necessary methods
			genderRatio(gender);
			calcStdDevMean(gender, age, weight, height);
			calcOldestYoungest(gender, age, name);
			printUniqueSport(sport);
			medalAllSport(gender, medal,mMedalCounts, fMedalCounts);
			medalEachSport(gender, medal, sport);
			printDetails(name, gender, age, height, weight, sport, medal);
		}else { //if the user enters a number other than 1 or 2
			System.out.println("Invalid input. Please rerun the program");
		}
		
	}	
		public static void inputData(String[] name, String[]gender, int[]age, double[]height, double[]weight, String[] sport, String[]medal) {
		Scanner scan= new Scanner(System.in);
		for (int i=0; i<name.length; i++) { //the number of entries are all the same
			System.out.println("Enter the name of the athlete " + (i+1));
			name[i]= scan.next();
			
			 do {//validating the gender input
		            System.out.println("Enter the athlete's gender (F/M): ");
		            gender[i] = scan.next();
		            if (gender[i].equalsIgnoreCase("f") || gender[i].equalsIgnoreCase("m")) {
		                System.out.println("Valid Input");
		            } else {
		                System.out.println("Input invalid. Please reenter");
		            }
		        } while (!gender[i].equalsIgnoreCase("f") && !gender[i].equalsIgnoreCase("m"));
			do {
				System.out.println("Enter the athlete's age: ");
				while (!scan.hasNextInt()) { //to validate that the input is numeric
				    System.out.println("Invalid input. Please enter a numeric value for age.");
				    scan.next(); 
				}age[i] = scan.nextInt();
			}while(age[i]<=12|| age[i]>=50); //setting ranges for the age input
			
			do {
				System.out.println("Enter the height of the athlete (cm): ");
				while (!scan.hasNextDouble()) {
				    System.out.println("Invalid input. Please enter a numeric value for age.");
				    scan.next(); 
				}height[i]= scan.nextDouble();
			}while(height[i]<=140||height[i]>=200); //setting the ranges for the height in cm
		
			do {
				System.out.println("Enter the weight of the athlete (kg): ");
				while (!scan.hasNextDouble()) {
				    System.out.println("Invalid input. Please enter a numeric value for age.");
				    scan.next(); 
				}weight[i]= scan.nextDouble();
			}while(weight[i]<=30||weight[i]>=100); //setting ranges for the weight in kg
		
			System.out.println("Enter the athlete's sport: ");
			sport[i]= scan.next();
			
			do {//validating the medal input 
				System.out.println("Enter the athlete's medal: ");
				medal[i]=scan.next();
	            if (medal[i].equalsIgnoreCase("gold") || medal[i].equalsIgnoreCase("silver")||medal[i].equalsIgnoreCase("bronze")) {
	                System.out.println("Input is valid");
	                System.out.println(" ");
	            } else {
	                System.out.println("The input is not valid. Please reenter");
	            }
	        } while (!medal[i].equalsIgnoreCase("gold") && !medal[i].equalsIgnoreCase("silver")&& !medal[i].equalsIgnoreCase("bronze"));
		}
	}
		//1. calculate the gender ratio
		public static void genderRatio(String[]gender) {
			int f=0;
			int m=0;
			 for (String genders : gender) {
		            if (genders.equalsIgnoreCase("F")) {
		                f++;
		            } else if (genders.equalsIgnoreCase("M")) {
		                m++;
		            }
		        }
		     double ratio = (double) f / m;
		     System.out.println("Gender Ratio (female to male): " + ratio); 
		     System.out.println(" ");
		}
		public static void calcStdDevMean(String[]gender, int[]age, double[]weight, double[]height) {
			int fCount=0, mCount=0, totalFWeight=0, totalMWeight=0, totalFHeight=0, totalMHeight=0, totalFAge=0, totalMAge=0;
			
	       
	        //calculating the number of females and males 
	        for (int i = 0; i < gender.length; i++) {
	            if (gender[i].equalsIgnoreCase("F")) {
	                totalFWeight += weight[i]; //calculating total number of the female athlete's weight
	                totalFAge+=age[i]; //calculating the total number for age
	                totalFHeight+=height[i]; //calculating the total number for height
	                fCount++;
	            } else if (gender[i].equalsIgnoreCase("M")) {
	                totalMWeight += weight[i];
	                totalMAge+=age[i];
	                totalMHeight+=height[i];
	                mCount++;
	            }
	        }
	        //calculating the mean for age for each gender
	        double meanFAge = (double) totalFAge / fCount;
	        double meanMAge = (double) totalMAge / mCount;
	        
	        //calculating the mean for weight for each gender
	        double meanFWeight = (double) totalFWeight / fCount;
	        double meanMWeight = (double) totalMWeight / mCount;
	        
	        //calculating the mean for height for each gender
	        double meanFHeight = (double) totalFHeight / fCount;
	        double meanMHeight = (double) totalMHeight / mCount;

	        //calculating the standard deviation for each gender
	        double sumFAge = 0, sumMAge=0, sumFHeight=0, sumMHeight=0, sumFWeight=0, sumMWeight=0;
	        

	        for (int i = 0; i < gender.length; i++) {
	            if (gender[i].equalsIgnoreCase("F")) {
	                sumFAge += Math.pow(age[i] - meanFAge, 2);
	                sumFHeight += Math.pow(height[i] - meanFHeight, 2);
	                sumFWeight += Math.pow(weight[i] - meanFWeight, 2);
	            } else if (gender[i].equalsIgnoreCase("M")) {
	                sumMAge += Math.pow(age[i] - meanMAge, 2);
	                sumMHeight += Math.pow(height[i] - meanMHeight, 2);
	                sumMWeight += Math.pow(weight[i] - meanMWeight, 2);
	            }
	        }
	        double sdFAge = Math.sqrt(sumFAge / fCount);
	        double sdMAge = Math.sqrt(sumMAge / mCount);
	        
	        double sdFHeight = Math.sqrt(sumFHeight / fCount);
	        double sdMHeight = Math.sqrt(sumMHeight / mCount);
	        
	        double sdFWeight = Math.sqrt(sumFWeight / fCount);
	        double sdMWeight = Math.sqrt(sumMWeight / mCount);

	        System.out.println("Mean Age for Female: " + String.format("%.2f", meanFAge));
	        System.out.println("Standard Deviation for Female: " +String.format("%.2f", sdFAge));
	        System.out.println("Mean Age for Male: " + String.format("%.2f", meanMAge));
	        System.out.println("Standard Deviation for Male: " + String.format("%.2f", sdMAge));
	        System.out.println(" ");
	        
	        System.out.println("Mean Height for Female: " + String.format("%.2f", meanFHeight));
	        System.out.println("Standard Deviation for Female: " + String.format("%.2f", sdFHeight));
	        System.out.println("Mean Height for Male: " + String.format("%.2f", meanMHeight));
	        System.out.println("Standard Deviation for Male: " + String.format("%.2f", sdMHeight)); 
	        System.out.println(" ");
	        
	        System.out.println("Mean weight for Female: " + String.format("%.2f", meanFWeight));
	        System.out.println("Standard Deviation for Female: " + String.format("%.2f", sdFWeight));
	        System.out.println("Mean weight for Male: " + String.format("%.2f", meanMWeight));
	        System.out.println("Standard Deviation for Male: " + String.format("%.2f", sdMWeight)); 
	        System.out.println(" ");
	        }
	        
		//5. calculate and indicate the name and age of the oldest athlete for each gender
		public static void calcOldestYoungest(String[]gender, int[]age, String[]name ) {
			int oldestM = Integer.MIN_VALUE; 
			int oldestF = Integer.MIN_VALUE; 
			int youngestF= Integer.MAX_VALUE;
			int youngestM= Integer.MAX_VALUE;
				
			for (int i=0; i<gender.length; i++) {
				if (gender[i].equalsIgnoreCase("M") && age[i] > oldestM) {
					oldestM = age[i];
				} else if (gender[i].equalsIgnoreCase("F") && age[i] > oldestF) {
					oldestF = age[i];
				}else if (gender[i].equalsIgnoreCase("M") && age[i]<youngestM){
					youngestM=age[i];
				}else if (gender[i].equalsIgnoreCase("F")&& age[i]<youngestF){ 
					youngestF=age[i];
				}
				else {
						
				}
			}
			for (int i = 0; i < name.length; i++) { //to print out the name of the oldest athlete
				if (gender[i].equalsIgnoreCase("M") && age[i] == oldestM) {
				    System.out.println("Oldest Male Athlete: " + name[i] + ", Age: " + age[i]);
				} else if (gender[i].equalsIgnoreCase("F") && age[i] == oldestF) {
				    System.out.println("Oldest Female Athlete: " + name[i] + ", Age: " + age[i]);
				}else if (gender[i].equalsIgnoreCase("M") && age[i] == youngestM) {
				    System.out.println("Youngest Male Athlete: "+ name[i]+ ", Age: "+ age[i]);
				}else if (gender[i].equalsIgnoreCase("F") && age[i] == youngestF) {
				    System.out.println("Youngest Female Athlete: "+ name[i]+ ", Age: "+ age[i]);
				}
				}System.out.println(" ");
			}
			//to obtain the unique sports
			public static void printUniqueSport(String[]sport) {
				String[] uniqueSports = new String[sport.length];
		        int uniqueCount = 0;  //source: w3Schools, GeekforGeeks 
		        for (String sports : sport) {
		            boolean isUnique = true;
		            for (int i = 0; i < uniqueCount; i++) {
		                if (uniqueSports[i].equals(sports)) {
		                    isUnique = false;
		                    break;}
		            }
		            if (isUnique) {
		                uniqueSports[uniqueCount] = sports;
		                uniqueCount++;
		            }
		        }
		        System.out.println("Unique Sports Available:");
		        for (int i = 0; i < uniqueCount; i++) {
		            System.out.print(uniqueSports[i] + " ");
		        }
		        System.out.println(" ");
		        System.out.println("Number of Unique Sports: " + uniqueCount);
		        System.out.println(" ");
		    }
			//calculate how many medals each gender gets in all sports for each medal type
			public static void medalAllSport(String[] gender, String[] medal,int[] mMedalCounts, int[] fMedalCounts) {
			    for (int i = 0; i < gender.length; i++) {
			        String genders = gender[i];
			        if (genders.equalsIgnoreCase("M")) {  //this will take in the data according to the each gender
			            mMedalCounts = MedalCount(medal[i], mMedalCounts); //calls the method to obtain the medals obtained by the gender
			        } else if (genders.equalsIgnoreCase("F")) {
			            fMedalCounts = MedalCount(medal[i], fMedalCounts);
			        }
			    }
			    System.out.println("Male - Gold: " + mMedalCounts[0] + ", Silver: " + mMedalCounts[1] + ", Bronze: " + mMedalCounts[2]);
			    System.out.println("Female - Gold: " + fMedalCounts[0] + ", Silver: " + fMedalCounts[1] + ", Bronze: " + fMedalCounts[2]);
			}
			//to obtain the number of medal types for each gender
			public static int[] MedalCount(String medal, int[] counts) {
			    if (medal.equalsIgnoreCase("Gold")) {
			        counts[0]++;
			    } else if (medal.equalsIgnoreCase("Silver")) {
			        counts[1]++;
			    } else if (medal.equalsIgnoreCase("Bronze")) {
			        counts[2]++;  //i created an array for the medal count in the main method
			    }
			    return counts;
			}
			//calculate how many medals each gender gets in each sport for each medal type,
			public static void medalEachSport(String[] gender, String[] medal, String[] sport) {
				String[] uniqueSports = getUniqueSports(sport); //creating a new array of different sports
				for (String sports : uniqueSports) {
					// Count medals for male and female athletes
					int maleGold = 0, maleSilver = 0, maleBronze = 0, femaleGold = 0, femaleSilver = 0, femaleBronze = 0;
					
					
				for (int i = 0; i < sport.length; i++) {
					if (sport[i].equals(sports)) {//it will iterate the new data according to the sport
					if (gender[i].equalsIgnoreCase("M")) {  //the if statement will let the medal count be obtained according to gender
						if (medal[i].equalsIgnoreCase("Gold")) {
									maleGold++;
						} else if (medal[i].equalsIgnoreCase("Silver")) {
									maleSilver++;
						} else if (medal[i].equalsIgnoreCase("Bronze")) {
									maleBronze++;
						}
					} else if (gender[i].equalsIgnoreCase("F")) {
						if (medal[i].equalsIgnoreCase("Gold")) {
									femaleGold++;
						} else if (medal[i].equalsIgnoreCase("Silver")) {
										femaleSilver++;
						} else if (medal[i].equalsIgnoreCase("Bronze")) {
									femaleBronze++;
						}
					}
				}
						}
					System.out.println(" ");
					System.out.println("Sport: " + sports);
					System.out.println("Male - Gold: " + maleGold + ", Silver: " + maleSilver + ", Bronze: " + maleBronze);
					System.out.println("Female - Gold: " + femaleGold + ", Silver: " + femaleSilver + ", Bronze: " + femaleBronze);
					System.out.println();
					}
	}	public static String[] getUniqueSports(String[] sports) { //to get the different sports
			int uniqueCount = 0;
				for (int i = 0; i < sports.length; i++) {
				 	boolean isUnique = true;
				for (int j = 0; j < uniqueCount; j++) {
				    if (sports[i].equals(sports[j])) {
				     isUnique = false;
				     break;
				     }
				 }
				     if (isUnique) {
				      sports[uniqueCount] = sports[i];
				       uniqueCount++;
				      }
				 }
				    String[] uniqueSports = new String[uniqueCount];  //create an array for different sports
				    System.arraycopy(sports, 0, uniqueSports, 0, uniqueCount); //source: GeekforGeeks
		        return uniqueSports;
		    }
		public static void printDetails(String[] name, String[]gender, int[]age, double[]height, double[]weight, String[] sport, String[]medal) {
			//source: w3Schools
		    System.out.println("-----------------------------------------------------------------------------");
		    System.out.printf("%-20s %-8s %-4s %-8s %-8s %-15s %-6s\n", "Name", "Gender", "Age", "Height", "Weight", "Sport", "Medal");
		    System.out.println("-----------------------------------------------------------------------------");
		    // Print athlete details in a table
		    for (int i = 0; i < name.length; i++) {
		        System.out.format("%-20s %-8s %-4d %-8.2f %-8.2f %-15s %-6s\n", name[i], gender[i], age[i], height[i], weight[i], sport[i], medal[i]);
		    }
		    System.out.println("-----------------------------------------------------------------------------");
		}
}
        
