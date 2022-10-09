import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;
import java.util.*;

public class Assignment3 {

	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		System.out.println("Assignment 3");
		
		String contents = new String(Files.readAllBytes(Paths.get("./JobResult_124432.txt")), StandardCharsets.UTF_8);
		
		//read the string as byte
		String filename = "JobResult_124432.txt";
		byte[] bytes = Files.readAllBytes(Paths.get(filename));


		List<String> words = Arrays.asList(contents.split("\\PL+"));
		long countwords = 0;
		
		
		//converting the bytes to String
		String input = new String(bytes, Charset.forName("UTF-8"));
		
		List<String> hex = Arrays.asList(input);
		
		//creating a pattern
		String regex1 = "[a-z0-9]{12,16}";//"[0-9]{9-16}[a-z]{1}[a-z0-9]{7}[a-z0-9]{1}";//000000008e030000, 0000000056030000, 0000000069020000, 000000008a020000
		// 000000005c020000
		String regex2 = "[a-z0-9]{12,16}";
		
		Pattern pattern1 = Pattern.compile(regex1);
		Pattern pattern2 = Pattern.compile(regex2);
		
		Matcher matcher1 = pattern1.matcher(input);
		Matcher matcher2 = pattern2.matcher(input);
		
		//System.out.println(Pattern.matches("[a-z0-9]{9,16}", "000000008e030000"));
		
		for(String w: words) {
			if (w.length() < 15) {
				//System.out.println(w);
				countwords++;
			}
		}
		System.out.println("String size:" + countwords);
		
		
		System.out.println();
		//System.out.println(System.currentTimeMillis()); 
		long preTime = (int) System.currentTimeMillis();
		
		while( matcher1.find()) {	
		String match1 = matcher1.group();
		
		List<String> numbers = Arrays.asList(match1);
			
			long count = 0;
			
			count =  numbers.stream().filter(x -> x.matches("[a-z0-9]{9,16}")).count();
			

		}
		
		long postTime = (int) System.currentTimeMillis(); 
		long streamTime = postTime - preTime;
		System.out.println("Millisecs using stream: "+ streamTime);
		
		
		System.out.println();
		

		
		long parallelpreTime = (int) System.currentTimeMillis();
		
		while( matcher2.find()) {	
		String match2 = matcher2.group();
		
		List<String> numbers2 = Arrays.asList(match2);
			//System.out.println(numbers);
			long count2 = 0;
			 
			count2 =  numbers2.stream().filter(x -> x.matches("[a-z0-9]{9,16}")).count();
			
					
		}
		//System.out.println(System.currentTimeMillis());
		long parallelpostTime = (int) System.currentTimeMillis(); 
		long parallelStreamTime = parallelpostTime - parallelpreTime;
		System.out.println("Millisecs using parallelStream: "+ parallelStreamTime);
		
		if (streamTime < parallelStreamTime) {
			System.out.println(" Results: stream was" +streamTime + " faster than parallelStream.");
			
		}
		else {
			System.out.println("Results: parallelStream was "+ parallelStreamTime + " faster than stream.");
		}

	}
	
}
