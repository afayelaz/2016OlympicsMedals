package com.cybertek;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class sortTest {
	static WebDriver driver;
	
	@BeforeClass //runs once for all tests
	public void setUp() {
		System.out.println("Setting up WebDriver in BeforeClass...");
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		//driver.manage().window().fullscreen();
		driver.get("https://en.wikipedia.org/wiki/2016_Summer_Olympics#Medal_table");	
	}
	
//	@BeforeMethod //runs before each @Test
//	public void navigateToHomePage() {
//		System.out.println("Navigating to homepage in @BeforeMethod....");
//		driver.get("https://en.wikipedia.org/wiki/2016_Summer_Olympics#Medal_table");	
//	}
	
	@Test
	public void checkRankTC1() {
		List<WebElement> firstColumn = driver.findElements(By.xpath("//table[@class='wikitable sortable plainrowheaders jquery-tablesorter']/tbody/tr/td[1]"));
		
		ArrayList<Integer> actualList = new ArrayList<>(); 
		for (int i = 0; i < firstColumn.size()-1; i++) {
			actualList.add(Integer.parseInt((firstColumn.get(i).getText())));
		}
		System.out.println(actualList+" Arraylist");
		SortedSet<Integer> expectedList = new TreeSet<>(actualList);
		System.out.println(expectedList+" Treeset");
		Assert.assertEquals(actualList, expectedList);
		
		driver.findElement(By.xpath("//th[@class='headerSort'][contains(text(),'NOC')]")).click();
        
        List<WebElement> countries = driver.findElements(By.xpath("//table[@class='wikitable sortable plainrowheaders jquery-tablesorter']/tbody/tr/th/a"));
        
        ArrayList<String> actualCountryList = new ArrayList<>();
        
 
        for (int i = 0; i < countries.size()-1; i++) {
            actualCountryList.add(countries.get(i).getText());
            //System.out.println(actualCountryList);
        }
        System.out.println(actualCountryList+" Arraylist");
        SortedSet<String> expectedCountryList = new TreeSet<>(actualCountryList);
        System.out.println(expectedCountryList+"Treeset");
        Assert.assertEquals(actualCountryList, expectedCountryList);
        
        //verify rank not in order 5 REQ
        List<WebElement> firstColumn1 = driver.findElements(By.xpath("//table[@class='wikitable sortable plainrowheaders jquery-tablesorter']/tbody/tr/td[1]"));
		ArrayList<Integer> actualList1 = new ArrayList<>(); 
		for (int i = 0; i < firstColumn.size()-1; i++) {
			actualList1.add(Integer.parseInt((firstColumn1.get(i).getText())));
		}
		System.out.println("ex"+expectedList);
		System.out.println("ac"+actualList1);
		Assert.assertFalse(expectedList.equals(actualList1));
		
		
	}
	
	
	
	@Test(priority=2)
	public void theMostTC2() {
	
	driver.get("https://en.wikipedia.org/wiki/2016_Summer_Olympics#Medal_table");
	
	System.out.println(mostGold());
	Assert.assertTrue(mostGold().equals("46 - United States")); 
	System.out.println(mostSilver());
	Assert.assertTrue(mostSilver().equals("37 - United States")); 
	Assert.assertTrue(mostBronze().equals("38 - United States")); 
	Assert.assertTrue(Total().equals("121 - United States")); 	
		
	}
	
	public static String mostGold(){
		SortedMap<Integer, String> mp= new TreeMap<>(); 
		List<WebElement> medals= driver.findElements(By.xpath("//table[@class='wikitable sortable plainrowheaders jquery-tablesorter']/tbody/tr/td[2]")); 
		List<WebElement> countries = driver.findElements(By.xpath("//table[@class='wikitable sortable plainrowheaders jquery-tablesorter']/tbody/tr/th/a")); 
		
		for(int i=0; i<medals.size(); i++){
			mp.put(Integer.parseInt(medals.get(i).getText()),countries.get(i).getText() ); 
			
		}
		
		return mp.lastKey()+ " - " +  mp.get(mp.lastKey()); 
	}
	
	public static String mostSilver(){
		SortedMap<Integer, String> mp= new TreeMap<>(); 
		List<WebElement> medals= driver.findElements(By.xpath("//table[@class='wikitable sortable plainrowheaders jquery-tablesorter']/tbody/tr/td[3]")); 
		List<WebElement> countries = driver.findElements(By.xpath("//table[@class='wikitable sortable plainrowheaders jquery-tablesorter']/tbody/tr/th/a")); 
		
		for(int i=0; i<medals.size(); i++){
			mp.put(Integer.parseInt(medals.get(i).getText()),countries.get(i).getText() ); 
			
		}
		
		return mp.lastKey()+ " - " +  mp.get(mp.lastKey()); 
	}
	
	public static String mostBronze(){
		SortedMap<Integer, String> mp= new TreeMap<>(); 
		List<WebElement> medals= driver.findElements(By.xpath("//table[@class='wikitable sortable plainrowheaders jquery-tablesorter']/tbody/tr/td[4]")); 
		List<WebElement> countries = driver.findElements(By.xpath("//table[@class='wikitable sortable plainrowheaders jquery-tablesorter']/tbody/tr/th/a")); 
		
		for(int i=0; i<medals.size(); i++){
			mp.put(Integer.parseInt(medals.get(i).getText()),countries.get(i).getText() ); 
			
		}
		
		return mp.lastKey()+ " - " +  mp.get(mp.lastKey()); 
	}
	
	public static String Total(){
		SortedMap<Integer, String> mp= new TreeMap<>(); 
		List<WebElement> medals= driver.findElements(By.xpath("//table[@class='wikitable sortable plainrowheaders jquery-tablesorter']/tbody/tr/td[5]")); 
		List<WebElement> countries = driver.findElements(By.xpath("//table[@class='wikitable sortable plainrowheaders jquery-tablesorter']/tbody/tr/th/a")); 
		
		for(int i=0; i<medals.size(); i++){
			mp.put(Integer.parseInt(medals.get(i).getText()),countries.get(i).getText() ); 
			
		}
		
		return mp.lastKey()+ " - " +  mp.get(mp.lastKey()); 
	}
	
	
	@Test(priority=3)
	public void countryByMedalTC3() {
		
		driver.get("https://en.wikipedia.org/wiki/2016_Summer_Olympics#Medal_table");
		System.out.println(SilverMedal() + "tc3 results");
		
		SoftAssert softAssert = new SoftAssert();
		
		softAssert.assertEquals(SilverMedal(), "China","France" );  // webtables class Murodil like Eygpt we find the same way
	
		List<String> list1 = new ArrayList<>();
		list1.add("China");
		list1.add("France");
		Assert.assertEquals(SilverMedal() ,  list1); 
//		Assert.assertEquals(SilverMedal() ,  "France");
		
	}
	
	public static List<String> SilverMedal(){
		//SortedMap<Integer, String> mp= new TreeMap<>(); 
		Set<String> sSet= new HashSet<>();
		
		List<WebElement> medals= driver.findElements(By.xpath("//table[@class='wikitable sortable plainrowheaders jquery-tablesorter']/tbody/tr/td[3]")); 
		List<WebElement> countries = driver.findElements(By.xpath("//table[@class='wikitable sortable plainrowheaders jquery-tablesorter']/tbody/tr/th/a")); 
		
		for(int i=0; i<medals.size() -1 ; i++){
			if (Integer.parseInt(medals.get(i).getText()) == 18) {
				sSet.add(countries.get(i).getText());
			}
		}
		List<String> list = new ArrayList<>(sSet);
		return list; 
	}
	
	@Test(priority=4)
	public void getIndex() {
		driver.get("https://en.wikipedia.org/wiki/2016_Summer_Olympics#Medal_table");
		rowAndColumn("Japan");
		System.out.println(rowAndColumn("Japan"));
		Assert.assertEquals(rowAndColumn("Japan"), "7,2");
	}
	
	public String rowAndColumn(String country) {
		Map<String, Integer> map = new HashMap<>();
		List<WebElement> countryNames = driver.findElements(By.xpath("//table[@class='wikitable sortable plainrowheaders jquery-tablesorter']/tbody/tr/th/a"));
		List<WebElement> data = driver.findElements(By.xpath("//table[@class='wikitable sortable plainrowheaders jquery-tablesorter']/tbody/tr/td[1]"));
		
		for (int i = 0; i < data.size(); i++) {
			map.put((countryNames.get(i).getText()), i+1);
	
		}	
	
		return map.get(country)+","+2;
	}
	
	@Test(priority=5)
	public void getSumTC5 () {
		driver.get("https://en.wikipedia.org/wiki/2016_Summer_Olympics#Medal_table");
		
		System.out.println(getSum());
//		SoftAssert softAssert = new SoftAssert();
//		
//		softAssert.assertEquals(getSum() ,"[Australia, Italy]" );  // webtables class Murodil like Eygpt we find the same way
//		
		List<String> list2 = new ArrayList<>();
		list2.add("Australia");
		list2.add("Italy");
		Assert.assertEquals(getSum() ,  list2); 
		
	}
	
	
	public static List<String> getSum(){
		
		List<WebElement> medals= driver.findElements(By.xpath("//table[@class='wikitable sortable plainrowheaders jquery-tablesorter']/tbody/tr/td[4]"));
		List<WebElement> countries = driver.findElements(By.xpath("//table[@class='wikitable sortable plainrowheaders jquery-tablesorter']/tbody/tr/th/a"));
		HashMap<String,Integer> mp= new HashMap<>();
		HashMap<String,Integer> hmp= new HashMap<>();
		
		SortedSet<String> st= new TreeSet<>();
		for(int i=0; i<medals.size()-1; i++){
			mp.put(countries.get(i).getText(), Integer.parseInt(medals.get(i).getText()) );
		}
		for(int i=0; i<medals.size()-1; i++){
			hmp.put(countries.get(i).getText(), Integer.parseInt(medals.get(i).getText()) ); 
		}
		for(Entry <String,Integer> each: mp.entrySet()){
			for(Entry <String,Integer> other: hmp.entrySet()){
				
				if(!(each.getKey().equals(other.getKey()))&&(each.getValue()+other.getValue())==18){
					st.add(each.getKey());
					st.add(other.getKey());
				} 
				}
			}
			
		 
		
		List<String> ls= new ArrayList<>(st);
		
		return ls; 
	}
	

}
