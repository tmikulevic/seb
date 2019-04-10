package lt.seb.SebUzduotis;

import java.util.Scanner;

public class Vykdyti {
	private static void spausdintiPradziosteksta() {
		System.out.println("Tai yra IBAN numerio tikrinimas, pasirinkite norima uzduoti:");
		System.out.println("1. IBAN tikrinimas konsoleje");
		System.out.println("2. IBAN tikrinimas is failo");
		System.out.println("3. IBAN tikrinimas REST");
	}
	public static void main(String[] args) {
		spausdintiPradziosteksta();
		Scanner ivestis;
		Scanner IBANas;
		int pasirinkimas;
			
		while(true) {
			System.out.println("Pasirinkite uzduoti:");
			ivestis = new Scanner(System.in);
			pasirinkimas = ivestis.nextInt();
			if (pasirinkimas==1) {
				System.out.println("Ivesk savo IBAN:");
				IBANas = new Scanner(System.in);
				String iban = IBANas.next();
				System.out.printf("%s yra %s.%n", iban, PatikrintiIBAN.IBANPatikrinimas(iban) ? "tinkamas" : "netinkamas");
			}
			else if(pasirinkimas == 2) {
				System.out.println("Ivesk pilna failo kelia:");//E:\mano\git\seb\test.txt
				IBANas = new Scanner(System.in);
				String iban = IBANas.next();
				PatikrintiFailoIBAN.patikrinti(iban);
			}
			else if(pasirinkimas == 3) {
				Application.paleisti();
				System.out.println("Serveris paleistas");
				break;
			}
			else {
				System.out.println("Darbas baigtas");
				break;
			}
			System.out.println("---");
		}
	 }
}
