package SeleniumFrameworkDesign.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import SeleniumFrameworkDesign.pageObject.LandingPage;

public class BaseTest {
	
	public WebDriver driver;
	public LandingPage lp;  //Declaring object at class level so that we need not create another object in main test case and remove the boilerplate code by giving this launchApplication method a Before annotation.
	
	public WebDriver initializeDriver() throws IOException {
		
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\SeleniumFrameworkDesign\\resources\\GlobalData.properties");
		prop.load(fis);
		
		String browserName = System.getProperty("browser");

		if (browserName == null) {
		    browserName = prop.getProperty("browser");
		}

		System.out.println("========== BROWSER DEBUG ==========");
		System.out.println("System property browser: " + System.getProperty("browser"));
		System.out.println("Config property browser: " + prop.getProperty("browser"));
		System.out.println("Final browserName: " + browserName);
		System.out.println("===================================");
		
		//String browserName = prop.getProperty("browser");

		if(browserName.equalsIgnoreCase("chrome")) {
		
			//Handling password change alert
			ChromeOptions options = new ChromeOptions();
			Map<String, Object> prefs = new HashMap<>();
			prefs.put("profile.password_manager_leak_detection", false);
			options.setExperimentalOption("prefs", prefs);
	
			driver = new ChromeDriver(options);
		}
		else if(browserName.equalsIgnoreCase("firefox")) {
			
			//firefox
		}
		else if(browserName.equalsIgnoreCase("edge")) {
			
			//Handling password change alert
			EdgeOptions options = new EdgeOptions();
			Map<String, Object> prefs = new HashMap<>();
			prefs.put("profile.password_manager_leak_detection", false);
			options.setExperimentalOption("prefs", prefs);
			
			driver = new EdgeDriver(options);
		}
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		return driver;		
	}
	
	public List<HashMap<String, String>> getJsonDataToMap(String filePath) throws IOException {
		
		//Read Json to String
		String jsonData = FileUtils.readFileToString(new File(filePath), StandardCharsets.UTF_8); //Give this encoding format as argument
		
		//String to HashMap -> You need Jackson databind dependency -> Go and import it in pom.xml file
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data = mapper.readValue(jsonData, new TypeReference<List<HashMap<String, String>>>(){});
		
		return data;
	}
	
	public String getScreenshot(String testCaseName, WebDriver driver) throws IOException {
		
		TakesScreenshot ts = ((TakesScreenshot)driver);
		File source = ts.getScreenshotAs(OutputType.FILE);
		File dest = new File(System.getProperty("user.dir")+"//reports//"+ testCaseName +".png");
		FileUtils.copyFile(source, dest);
		return (System.getProperty("user.dir")+"//reports//"+ testCaseName +".png");
	}
	
	@BeforeMethod(alwaysRun=true)
	public LandingPage launchApplcation() throws IOException {
		
		driver = initializeDriver();
		lp = new LandingPage(driver);
		lp.goToUrl();
		return lp;
	}
	
	@AfterMethod(alwaysRun=true)
	public void tearDown() {
		
		driver.close();
	}
}
