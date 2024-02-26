package Designe2e.data;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DataReader {

	public List<HashMap<String, String>> getJsondataToMap() throws IOException {
		// ReadJson to String
		String JsonContent = FileUtils.readFileToString(
				new File(System.getProperty("User.dir") + "\\src\\test\\java\\Designe2e\\data\\PurchaseOrder.json"),
				StandardCharsets.UTF_8);

		// Convert String to HashMap Jackson Databind
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data = mapper.readValue(JsonContent,
				new TypeReference<List<HashMap<String, String>>>() {
				});

		return data;

	}

}
