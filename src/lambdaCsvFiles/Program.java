package lambdaCsvFiles;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;

	public class Program {
	 public static void main(String[] args) {
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		
		List <Sale> list = new ArrayList<>();
		Map<String, Double> map = new HashMap<>();
		
		
		System.out.println("Enter the file name: ");
		
		try {
			try (BufferedReader scan = new BufferedReader(new FileReader(sc.nextLine()))) {
				String readL = scan.readLine();
				while(readL!=null) {
					String[] split= readL.split(",");					
					list.add(new Sale(Integer.parseInt(split[0]),Integer.parseInt(split[1]), split[2],Integer.parseInt(split[3]),Double.parseDouble(split[4])));
					map.merge(split[2], Double.parseDouble(split[4]),(Double::sum));
					readL = scan.readLine();
					
				}
				
				
			}
		} catch (FileNotFoundException e) {
			System.out.println("Error:  "+e.getMessage());

			
		} catch (IOException e) {
			System.out.println("Error"+e.getMessage());
			
		}
		
       System.out.println("Total Sales by each Seller: ");
		
		map.forEach((x,y)-> System.out.printf("\nName: %s, Total Sales: $ %.2f",x, y));



		list.sort((e1,e2) -> -e1.averagePrice().compareTo(e2.averagePrice()));
		
		System.out.println("\n");

		System.out.println("\nEnter the year: to see the top 5 average sales: ");
		int year = sc.nextInt();
		
		list.stream().filter(e->e.getYear()==year).limit(5).forEach(e->System.out.println(e));;
				
		sc.nextLine();
		System.out.println("Enter the name of the seller: to see the top 5 average sales: ");
		String seller = sc.nextLine();

		list.stream().filter(e->e.getSeller().equals(seller)).limit(5).forEach(e->System.out.println(e));
		
		
		

			
		


		
		
		
		
		sc.close();
}
}
