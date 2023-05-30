package interview.assessment;


import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class RatioCalculations {

	public static int[] Covid19Series(String Country, int RecoveredDateIndex, int ConfirmedDateIndex,
			Map<String, List<String>> RecoveredCasesMap,
			Map<String, List<String>> ConfirmedCasesMap) {
		int RecoveredCount[] = new int[2];
		RecoveredCount[0] = 0;
		RecoveredCount[1] = 0;

		try {
			List<List<String>> RecoveredList = RecoveredCasesMap.entrySet().stream()
					.filter(mapp -> mapp.getKey().contains(Country)).map(elem -> elem.getValue())
					.collect(Collectors.toList());

			for (int i = 0; i < RecoveredList.size(); i++) {
				RecoveredCount[0] += Integer.parseInt(RecoveredList.get(i).get(RecoveredDateIndex));
			}
			// System.out.println(RecoveredCount[0]);

			List<List<String>> ConfirmedList = ConfirmedCasesMap.entrySet().stream()
					.filter(mapp -> mapp.getKey().contains(Country)).map(elem -> elem.getValue())
					.collect(Collectors.toList());

			for (int i = 0; i < ConfirmedList.size(); i++) {
				RecoveredCount[1] += Integer.parseInt(ConfirmedList.get(i).get(ConfirmedDateIndex));
			}
			// System.out.println(RecoveredCount[1]);
		} catch (ArrayIndexOutOfBoundsException e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return RecoveredCount;
	}

	public static int[] Covid19Series(String Country, String State, int RecoveredDateIndex, int ConfirmedDateIndex,
			Map<String, List<String>> RecoveredCasesMap,
			Map<String, List<String>> ConfirmedCasesMap) throws Exception {
		int RecoveredCount[] = new int[2];
		RecoveredCount[0] = 0;
		RecoveredCount[1] = 0;
		try {
			List<List<String>> RecoveredList = RecoveredCasesMap.entrySet().stream()
					.filter(mapp -> mapp.getKey().contains(State + "-" + Country)).map(elem -> elem.getValue())
					.collect(Collectors.toList());

			for (int i = 0; i < RecoveredList.size(); i++) {
				RecoveredCount[0] += Integer.parseInt(RecoveredList.get(i).get(RecoveredDateIndex));
			}
			// System.out.println(RecoveredCount[0]);

			List<List<String>> ConfirmedList = ConfirmedCasesMap.entrySet().stream()
					.filter(mapp -> mapp.getKey().contains(State + "-" + Country)).map(elem -> elem.getValue())
					.collect(Collectors.toList());

			for (int i = 0; i < ConfirmedList.size(); i++) {
				RecoveredCount[1] += Integer.parseInt(ConfirmedList.get(i).get(ConfirmedDateIndex));
			}
			// System.out.println(RecoveredCount[1]);

		} catch (ArrayIndexOutOfBoundsException e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return RecoveredCount;
	}

	public static int[] Covid19Series(List<String> Countries, int RecoveredDateIndex, int ConfirmedDateIndex,
			Map<String, List<String>> RecoveredCasesMap,
			Map<String, List<String>> ConfirmedCasesMap) throws Exception {
		int RecoveredCount[] = new int[2];
		RecoveredCount[0] = 0;

		RecoveredCount[1] = 0;
		for (String country : Countries) {
			try {
				List<List<String>> RecoveredList = RecoveredCasesMap.entrySet().stream()
						.filter(mapp -> mapp.getKey().contains(country.toLowerCase())).map(elem -> elem.getValue())
						.collect(Collectors.toList());

				for (int i = 0; i < RecoveredList.size(); i++) {
					RecoveredCount[0] += Integer.parseInt(RecoveredList.get(i).get(RecoveredDateIndex));
				}

				List<List<String>> ConfirmedList = ConfirmedCasesMap.entrySet().stream()
						.filter(mapp -> mapp.getKey().contains(country.toLowerCase())).map(elem -> elem.getValue())
						.collect(Collectors.toList());

				for (int i = 0; i < ConfirmedList.size(); i++) {
					RecoveredCount[1] += Integer.parseInt(ConfirmedList.get(i).get(ConfirmedDateIndex));
				}

			} catch (ArrayIndexOutOfBoundsException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		// System.out.println(RecoveredCount[0]);
		// System.out.println(RecoveredCount[1]);

		return RecoveredCount;
	}

}
