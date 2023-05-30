package interview.assessment;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Scanner;

public class Main {

	  static Map<String, List<String>> ContinentDetailsMap = new LinkedHashMap<>();
	  static Map<String, List<String>> RecoveredCasesMap = new LinkedHashMap<>();
	  static Map<String, List<String>> ConfirmedCasesMap = new LinkedHashMap<>();
	  
	  static SimpleDateFormat Inputformat=new SimpleDateFormat("yyyy-MM-dd");
	  static SimpleDateFormat Outputformat=new SimpleDateFormat("M/d/yy");
	  
	static
	{
		File ContinentFile =new File("CSV"+File.separator+"countries_to_continent.csv");
		ContinentDetailsMap=ContinentData.LoadContinentData(ContinentFile.getPath());

		File RecoveredCasesFile =new File("CSV"+File.separator+"covid_recovered.csv");
         RecoveredCasesMap=RecoveredCasesData.LoadRecoveredCasesData(RecoveredCasesFile.getPath());
	  
         File ConfirmedCasesFile =new File("CSV"+File.separator+"covid_confirmed.csv");
	    ConfirmedCasesMap=ConfirmedCasesData.LoadConfirmedCasesData(ConfirmedCasesFile.getPath());
	}
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		
		Scanner in=new Scanner(System.in);
		
		System.out.println("Enter the Details listed below");
		System.out.println("1.Input Date");
		System.out.println("2.Country");
		System.out.println("3.State");
		System.out.println("4.Continent");
		System.out.println("Country and Dates are mandatory");
		
		String Date=in.nextLine();
		String Country=in.nextLine().toLowerCase();
		String State=in.nextLine().toLowerCase();
		String Continent=in.nextLine().toLowerCase();
		
		System.out.println("Input Date="+Date+",Country="+Country+",State="+State+",Continent="+Continent);
		
		
		if(!Continent.equals("") && !Country.equals(""))
			throw new UserException("Either Continent or Country Should be mandatory!");
		
		int RecoveredDateIndex=-1;
		int ConfirmedDateIndex=-1;
		
		if(!Date.equals(""))	
		{
			Date=Outputformat.format(Inputformat.parse(Date));
			try
			{
			RecoveredDateIndex=RecoveredCasesMap.get(RecoveredCasesMap.keySet().toArray()[0]).indexOf(Date);
			ConfirmedDateIndex=ConfirmedCasesMap.get(ConfirmedCasesMap.keySet().toArray()[0]).indexOf(Date);
			}
			catch (ArrayIndexOutOfBoundsException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
				
		List<String>Countries=new ArrayList<>();
		int RecoveredCounts[]=new int[2];
		
		if(!Continent.equals("") && Country.equals("") && State.equals(""))					
				{
			Countries=ContinentDetailsMap.get(Continent);	
			RecoveredCounts=RatioCalculations.Covid19Series(Countries,RecoveredDateIndex,ConfirmedDateIndex,
					RecoveredCasesMap,ConfirmedCasesMap);
				}
		
		if(!Country.equals("") && !State.equals(""))
			
		{
			RecoveredCounts=RatioCalculations.Covid19Series(Country,State,RecoveredDateIndex,ConfirmedDateIndex
					,RecoveredCasesMap,ConfirmedCasesMap);
		}
		
       if(!Country.equals("") && State.equals(""))
			
		{
    	   RecoveredCounts=RatioCalculations.Covid19Series(Country,RecoveredDateIndex,ConfirmedDateIndex
    			   ,RecoveredCasesMap,ConfirmedCasesMap);
		}
		
        if(!State.equals("") && Country.equals(""))
			
		{
        	 RecoveredCounts=RatioCalculations.Covid19Series(State,RecoveredDateIndex,ConfirmedDateIndex
        			 ,RecoveredCasesMap,ConfirmedCasesMap);
		}
		
        int RecoveryRatio=0;
        try
        {
        	if(Optional.ofNullable(RecoveredCounts[0]).orElse(0) != 0 && Optional.ofNullable(RecoveredCounts[1]).orElse(0) != 0)
                    RecoveryRatio=RecoveredCounts[0]/(RecoveredCounts[1]!=0?RecoveredCounts[1]:0);
        }
        catch (ArithmeticException e) {
			// TODO: handle exception
        	e.printStackTrace();
		}
        System.out.println("Recovered="+RecoveredCounts[0]+", Confirmed="+RecoveredCounts[1]+",RecoveryRatio="+RecoveryRatio);
	}
}
