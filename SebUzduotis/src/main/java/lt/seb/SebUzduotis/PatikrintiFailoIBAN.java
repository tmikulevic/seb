package lt.seb.SebUzduotis;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class PatikrintiFailoIBAN {
	public static ArrayList<String> gaunamFailoDuomenis(String failoPavadinimas)
	{
		String eilute;
		StringBuilder sb = new StringBuilder();
		boolean tinkamumas;
		ArrayList<String> tekstas = new ArrayList<String>();
	  try
	  {
		File failas = new File(failoPavadinimas);
	    BufferedReader reader = new BufferedReader(new FileReader(failas));
	    
	    while(true) 
	    {
	    	String inputText = reader.readLine();
	    	if(inputText == null)
	    		break;
	    	tinkamumas = PatikrintiIBAN.IBANPatikrinimas(inputText);
	    	sb.append(inputText).append(";").append(tinkamumas);
	    	eilute = sb.toString();
	    	tekstas.add(eilute);
	    	sb.setLength(0);
	    }

	    reader.close();
	    return tekstas;
	  }
	  catch (Exception e)
	  {
	    System.err.format("Ivyko klaida faile '%s'.", failoPavadinimas);
	    //e.printStackTrace();
	    return null;
	  }
	}
	
	private static void irasomeIFaila(ArrayList<String> tekstas, String failoPavadinimas) {

		if (tekstas == null) {
			return;
		}
		
		BufferedWriter bw = null;
		FileWriter fw = null;
		
		try {
			fw = new FileWriter(failoPavadinimas);
			bw = new BufferedWriter(fw);
			
			for(int i = 0; i < tekstas.size(); i++) {
				bw.write(tekstas.get(i));
				bw.newLine();
			}
			
			System.out.println("Failas issaugotas");

		} 
		catch (IOException e) {
			e.printStackTrace();
		}
		finally {
			try {
				if (bw != null)
					bw.close();
				if (fw != null)
					fw.close();
			}
			catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}
	
	public static void patikrinti(String failoPavadinimas) {
		String saugomasFailas = failoPavadinimas + ".out";
		irasomeIFaila(gaunamFailoDuomenis(failoPavadinimas), saugomasFailas);
	}
}
