package interview.assessment;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ConfirmedCasesData {

	public static LinkedHashMap<String, List<String>> LoadConfirmedCasesData(String ConfirmedCasesCSVFile)
	{
		LinkedHashMap<String, List<String>>ConfirmedCasesMap=new LinkedHashMap<>();
		try (Stream<String> lines = Files.lines(Paths.get(ConfirmedCasesCSVFile))) {
			lines.filter(line -> line.contains(",")).forEach(line -> {
				String[] keyValuePair = line.split(",", 3);
				String key = ((keyValuePair[0].equals("") || keyValuePair[0] == null) ? "" : keyValuePair[0] + "-")
						+ keyValuePair[1];
				String[] Values = keyValuePair[2].split("\\,");
				ConfirmedCasesMap.put(key.toLowerCase(), Stream.of(Values).collect(Collectors.toList()));
			});
		} catch (IOException e) {
			e.printStackTrace();
		}
		return ConfirmedCasesMap;
	}
}
