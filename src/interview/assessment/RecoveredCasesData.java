package interview.assessment;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class RecoveredCasesData {

	public static LinkedHashMap<String, List<String>> LoadRecoveredCasesData(String RecoveredCasesCSVFile)
	{
		LinkedHashMap<String, List<String>>RecoveredCasesMap=new LinkedHashMap<>();
		try (Stream<String> lines = Files.lines(Paths.get(RecoveredCasesCSVFile))) {
			lines.filter(line -> line.contains(",")).forEach(line -> {
				String[] keyValuePair = line.split(",", 3);
				String key = ((keyValuePair[0].equals("") || keyValuePair[0] == null) ? "" : keyValuePair[0] + "-")
						+ keyValuePair[1];
				String[] Values = keyValuePair[2].split("\\,");
				RecoveredCasesMap.put(key.toLowerCase(), Stream.of(Values).collect(Collectors.toList()));
			});
		} catch (IOException e) {
			e.printStackTrace();
		}
		return RecoveredCasesMap;
	}
}
