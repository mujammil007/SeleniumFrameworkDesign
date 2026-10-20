package SeleniumFrameworkDesign.Data;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DataReader {
	
	public List<HashMap<String, String>> getJsonDataToMap() throws IOException {
		
		//Read Json to String
		String jsonData = FileUtils.readFileToString(new File(System.getProperty("user.dir")+
				"\\src\\test\\java\\SeleniumFrameworkDesign\\Data\\PurchaseOrder.json"), StandardCharsets.UTF_8); //Give this encoding format as argument
		
		//String to HashMap -> You need Jackson databind dependency -> Go and import it in pom.xml file
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data = mapper.readValue(jsonData, new TypeReference<List<HashMap<String, String>>>(){});
		
		return data;
	}

}
