import java.util.Scanner;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

import java.util.stream.Collectors;

public class CSV implements Comparable<CSV> {
	String s;
	ArrayList<CSV> arr;
	// private int type;

	public static void main(String[] args) {
		if (args.length > 0) {
			System.out.println(parseCSV(args[0]));
		} else {
			Scanner sc = new Scanner(System.in);
			while (true) {
				String csvStr = sc.nextLine();
				if (csvStr.equals("/exit")) {
					sc.close();
					System.exit(0);
				}
				CSV csv = parseCSV(csvStr);
				System.out.print("" + csv.length() + ":");
				System.out.println(csv.printString());
			}
			// "g, \"\"\"r, t\"\",d ,a\", c"
		}
	}

	public boolean isEmpty() {
		return this.s == null && this.arr == null;
	}

	public int getType() {
		if (this.arr != null) {
			return 1;
		} else if (this.s != null) {
			return 0;
		}
		return -1;
	}

	public int length() {
		if (this.isEmpty()) {
			return 0;
		} else if (this.getType() == 0) {
			return this.s.length();
		} else {
			return this.arr.size();
		}
	}

	public int size() {
		if (this.getType() == 1) {
			return this.arr.size();
		} else {
			return -1;
		}
	}

	public CSV() {
	}

	// if allow creation of CSV from another single type 0 CSV, inceptions will
	// occur
	public CSV(CSV... csvs) {
		assert (csvs.length >= 1);
		this.arr = new ArrayList<CSV>(Arrays.asList(csvs));
	}

	public CSV(String... str) {
		assert (str.length > 0);
		if (str.length == 1) {
			if (str[0].length() != 0) {
				this.s = str[0];
			}
		} else {
			this.arr = new ArrayList<CSV>(Arrays.asList(str).stream().map(x -> new CSV(x)).collect(Collectors.toList()));
		}
	}

	public CSV(List<String> lst) {
		if (lst.size() != 0) {
			this.arr = new ArrayList<CSV>(lst.stream().map(CSV::new).collect(Collectors.toList()));
		}
	}

	public static void flatPrint(CSV csvstr) {
		switch (csvstr.getType()) {
		case 0:
			System.out.print(csvstr.s);
			break;
		case 1:
			csvstr.arr.forEach(CSV::flatPrint);
			break;
		}
	}

	public CSV get(int i) {
		switch (this.getType()) {
		default:
			return this;
		case 1:
			return this.arr.get(i);
		}
	}

	public boolean remove() {
		if (this.getType() == 0) {
			this.s = null;
			return true;
		} else {
			return false;
		}
	}

	public CSV remove(int idx) {
		if (getType() == 1 && this.arr.size() > idx) {
			return this.arr.remove(idx);
		} else {
			return null;
		}
	}

	public String getStr(int i) {
		return this.get(i).toString();
	}

	public boolean add(CSV csv) {
		if (this.isEmpty()) {
			this.arr = new ArrayList<>();
			return this.arr.add(csv);
		} else if (this.getType() == 1) {
			return this.arr.add(csv);
		} else {
			switchType1();
			return this.arr.add(csv);
		}
	}

	private void switchType1() {
		if (this.getType() == 0) {
			this.arr = new ArrayList<CSV>();
			if (this.s != null) {
				this.arr.add(new CSV(this.s));
				this.s = null;
			}
		}
	}

	public boolean addAll(CSV csv) {
		if (csv.isEmpty()) {
			return false;
		}
		this.switchType1();
		switch (csv.getType()) {
		case 1:
			return this.arr.addAll(csv.arr);
		default:
			return this.arr.add(csv);
		}
	}

	public static String cFormat(String s) {
		return s.replace("\\", "\\\\").replace(",", "\\,").replace("\"", "\\\"");
	}

	public static String csFormat(String s) {
		s = s.replace("\\\\", Character.toString((char) 25)).replace("\\,", Character.toString((char) 26)).replace("\\\"",
				Character.toString((char) 24));
		return s;
	}

	public static String deSFormat(String s) {
		if (s == null) {
			return null;
		}
		return s.replace((char) 25, '\\').replace((char) 26, ',').replace((char) 24, '\"');
	}

	private void deS() {
		if (getType() == 0) {
			this.s = deSFormat(this.s);
		} else if (getType() == 1) {
			this.arr.forEach(x -> x.deS());
		}
	}

	public static CSV parseCSV(String str) {
		str = csFormat(str);
		CSV ans = parseCSV(str, 0);
		ans.deS();
		return ans;
	}

	private static CSV parseCSV(String str, int layer) {
		int m = 1 + layer;
		str = str.replace("\"".repeat(m), "\"");
		if (str.length() == 0) {
			return new CSV();
		}
		if (!str.contains("\"")) {
			String[] strarr = str.split(",");
			return new CSV(Arrays.asList(strarr));
		} else if (str.trim().startsWith("\"")) {
			Scanner sc = new Scanner(str.trim() + " ").useDelimiter("\"");
			String s = sc.next();
			String ss = "";
			int i = 1; // no. of quotes encountered
			do {
				do {
					ss += "\"" + s;
					s = sc.next();
					i++;
				} while (s.length() == 0);
			} while (i % 2 != 0);
			CSV ans = parseCSV(ss.substring(1), 1);
			ans = new CSV(ans);
			str = str.substring(ss.length() + str.indexOf(ss) + 1);
			if (str.trim().startsWith(",")) {
				str = str.substring(str.indexOf(",") + 1);
			}
			ans.addAll(parseCSV(str, layer));
			return ans;
		} else if (str.contains(",")) {
			int r = str.indexOf(",");
			CSV ans = new CSV(str.substring(0, r));
			ans.addAll(parseCSV(str.substring(r + 1), layer));
			return ans;
		} else {
			return new CSV(str);
		}
	}

	private static String quoteFrame(String str, int n) {
		if (n < 0) {
			return str;
		}
		String q = "\"".repeat((int) Math.pow(2, n));
		return q + str + q;
	}

	private String toStrn(int n) {
		if (isEmpty()) {
			return quoteFrame("", n);
		}
		switch (this.getType()) {
		case 0:
			return this.s;
		case 1:
			return quoteFrame(this.arr.stream().map(x -> x.toStrn(n + 1)).collect(Collectors.joining(",")), n);
		default:
			return "";
		}
	}

	private String printStrn(int n) {
		if (isEmpty()) {
			return quoteFrame("", n);
		}
		switch (this.getType()) {
		case 0:
			return cFormat(this.s);
		case 1:
			return quoteFrame(this.arr.stream().map(x -> x.printStrn(n + 1)).collect(Collectors.joining(",")), n);
		default:
			return "";
		}
	}

	public ArrayList<String> toStringArrayList() {
		if (isEmpty()) {
			return new ArrayList<String>();
		}
		switch (this.getType()) {
		case 0:
			ArrayList<String> ans = new ArrayList<String>(1);
			ans.add(this.s);
			return ans;
		case 1:
			return new ArrayList<String>(this.arr.stream().map(x -> x.toStrn(0)).collect(Collectors.toList()));
		default:
			return new ArrayList<String>();
		}
	}

	public String printString() {
		if (isEmpty()) {
			return "";
		}
		switch (this.getType()) {
		case 0:
			return cFormat(this.s);
		case 1:
			return this.arr.stream().map(x -> x.printStrn(0)).collect(Collectors.joining(","));
		default:
			return ""; // empty CSV
		}
	}

	public int compareTo(CSV csv2) {
		return length() - csv2.length();
	}

	@Override
	public String toString() {
		if (isEmpty()) {
			return "";
		}
		switch (this.getType()) {
		case 0:
			return this.s;
		case 1:
			return this.arr.stream().map(x -> x.toStrn(0)).collect(Collectors.joining(","));
		default:
			return "";
		}
	}
}

interface CSVParsable {
	public CSV toCSV();
}