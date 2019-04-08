package lt.seb.SebUzduotis;
import java.util.Scanner;
import java.math.BigInteger;
import java.util.*;

public class PatikrintiIBAN {
	
	/*
	 * Metodas skirtingiems saliu kodams, nes kiekviena salis turi skirtinga IBAN formata
	 * */
	public static Map<String, Integer> makeFormats() {
		final String saliuFormatai = "AL28,AD24,AT20,AZ28,BE16,BH22,BA20,BR29,BG22," +
				"HR21,CY28,CZ24,DK18,DO28,EE20,FO18,FI18,FR27,GE22,DE22,GI23," +
				"GL18,GT28,HU28,IS26,IE22,IL23,IT27,KZ20,KW30,LV21,LB28,LI21," +
				"LT20,LU20,MK19,MT31,MR27,MU30,MC27,MD24,ME22,NL18,NO15,PK24," +
				"PS29,PL28,PT25,RO24,SM27,SA24,RS22,SK24,SI19,ES24,SE24,CH21," +
				"TN24,TR26,AE23,GB22,VG24,GR27,CR21";
		
		final Map<String, Integer> formatai = new HashMap<>();
		
		for (String reiksme : saliuFormatai.split(",")) {
			formatai.put(reiksme.substring(0, 2), Integer.parseInt(reiksme.substring(2)));
		}
		return formatai;
	}
	
	public static boolean IBANPatikrinimas(String IBAN) {

		int ilgis = IBAN.length();
		
		/*
		 * Patikrina ar atitinka kazkokios salies koda
		 * */
		if (ilgis < 4 || !IBAN.matches("[0-9A-Z]+") || makeFormats().getOrDefault(IBAN.substring(0, 2), 0) != ilgis)
			return false;
		/*
		 * 4 pradiniai simboliai perkeliami i pabaiga
		 * */
		IBAN = IBAN.substring(4) + IBAN.substring(0, 4);
		/*
		 * raide pakeiciamos skaiciais
		 * */
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < ilgis; i++)
			sb.append(Character.digit(IBAN.charAt(i), 36));
		 
		BigInteger bigInt = new BigInteger(sb.toString());
		
		/*
		 * skaiciuojama mod 97, jei gaunama liekana 1, reiskia, kad IBAN validus
		 * */
		return bigInt.mod(BigInteger.valueOf(97)).intValue() == 1;
	}
	
	 public static void main(String[] args) {
		System.out.println("Tai yra IBAN numerio tikrinimas, pasirinkite norima uzduoti:");
		System.out.println("1. IBAN tikrinimas konsoleje");
		System.out.println("2. IBAN tikrinimas is failo");
		System.out.println("3. IBAN tikrinimas REST");
		 

		Scanner ivestis;
		int pasirinkimas;
			
		while(true) {
			System.out.println("Pasirinkite uzduoti:");
			ivestis = new Scanner(System.in);
			pasirinkimas = ivestis.nextInt();
			if (pasirinkimas==1) {
				System.out.println("Ivesk savo IBAN:");
				ivestis = new Scanner(System.in);
				String iban = ivestis.next();
				System.out.printf("%s yra %s.%n", iban, IBANPatikrinimas(iban) ? "tinkamas" : "netinkamas");
			}
			else {
				System.out.println("Darbas baigtas");
				break;
			}
		}
	 }
}
