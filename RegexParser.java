import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexParser {
	public static void main(String[] args) {
        Pattern pattern[] = new Pattern[2];
        pattern[0] = Pattern.compile("^(?<name>[^;]+);(?<FirstJoin>\\d+/\\d+/\\d+, \\d+:\\d+:\\d+ [AP]M);(?<LastLeave>\\d+/\\d+/\\d+, \\d+:\\d+:\\d+ [AP]M);(?<Runtime>(?:\\d+[hms]\\s*)+);(?<EMail>[^;]+?)?;(?<UserID>[^;]+?)?;(?<Role>[^;]+)$");
        pattern[1] = Pattern.compile("^(?<name>[^;]+);(?<FirstJoin>\\d+/\\d+/\\d+, \\d+:\\d+:\\d+ [AP]M);(?<LastLeave>\\d+/\\d+/\\d+, \\d+:\\d+:\\d+ [AP]M);(?<Runtime>(?:\\d+[hms]\\s*)+);(?<EMail>[^;]+?)?;(?<Role>[^;]+);$");
        		
        String fn = "c:\\Users\\pc\\Downloads\\meetingmuster.csv";
        
        try (BufferedReader reader = new BufferedReader(new FileReader(fn))) {
            String line;
            while ((line = reader.readLine()) != null) {
            	for(int p=0; p<pattern.length; p++) {
                    Matcher matcher = pattern[p].matcher(line);
                    
                    if (matcher.matches()) {
                        System.out.println("Name: " + matcher.group("name"));
                        System.out.println("FirstJoin: " + matcher.group("FirstJoin"));
                        System.out.println("LastLeave: " + matcher.group("LastLeave"));
                        System.out.println("Runtime: " + matcher.group("Runtime"));
                        System.out.println("EMail: " + matcher.group("EMail"));
                        if(p==0)
                        	System.out.println("UserID: " + (matcher.group("UserID") == null ? "1" : "0"));
                        System.out.println("Role: " + matcher.group("Role"));
                        System.out.println();
                        break;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
