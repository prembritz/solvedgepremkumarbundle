package interview.assessment;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ContinentData {
	
	
public static LinkedHashMap<String, List<String>> LoadContinentData(String ContinentCSVFile)
{
	LinkedHashMap<String, List<String>>ContinentDetailsMap=new LinkedHashMap<>();
	try (Stream<String> lines = Files.lines(Paths.get(ContinentCSVFile))) {
		lines.filter(line -> line.contains(",")).forEach(line -> {
			String[] keyValuePair = line.split(",", 2);
			String key = keyValuePair[0].toLowerCase();
			String value = keyValuePair[1];
			if (ContinentDetailsMap.containsKey(key)) {
				ContinentDetailsMap.get(key).add(value);
			} else {
				ContinentDetailsMap.put(key, Stream.of(value).collect(Collectors.toList()));
			}
		});
	} catch (IOException e) {
		e.printStackTrace();
	}
	return ContinentDetailsMap;
}
}
