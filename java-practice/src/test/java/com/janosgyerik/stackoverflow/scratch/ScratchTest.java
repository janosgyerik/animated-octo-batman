package com.janosgyerik.stackoverflow.scratch;

import javafx.concurrent.Task;
import org.junit.Test;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.sql.Time;
import java.util.*;
import java.util.concurrent.*;
import java.util.stream.Collectors;

import static javax.sound.sampled.LineEvent.Type.CLOSE;
import static javax.sound.sampled.LineEvent.Type.STOP;
import static org.junit.Assert.assertEquals;

class Temperature {

	public double degrees;

	Temperature() {
		degrees = 0;
	}

	Temperature(double enteredtemp) {
		degrees = enteredtemp;
	}

	Temperature(double enteredtemp, char scale) {
		Temperature t1 = new Temperature(enteredtemp, scale);
		t1.set(enteredtemp, scale);


	}

	public void set(double enteredtemp, char scale) {
		switch (scale) {
			case 'r':
			case 'R':
				degrees = (enteredtemp / (9 / 5));
				break;
			case 'c':
			case 'C':
				degrees = enteredtemp + 273.15;
				break;
			case 'f':
			case 'F':
				degrees = ((enteredtemp + 459.67) * 9 / 5);
				break;
		}
	}

	public double get() {
		return degrees;
	}

	public double get(char scale) {
		if (scale == 'c' || scale == 'C') {
			degrees = (degrees - 273.15);
		} else if (scale == 'r' || scale == 'R') {
			degrees = (degrees * (9 / 5));
		} else if (scale == 'f' || scale == 'F') {
			degrees = (degrees * (9 / 5) - 459.67);
		}
		return (degrees);
	}


	public boolean isLessThan(Temperature t) {
		return get() < t.get();
	}

	public boolean isGreaterThan(Temperature t) {
		return get() > t.get();
	}

	public boolean isEqual(Temperature t) {
		return Math.abs(t.get() - get()) <= 10E-12;
	}

	public boolean isGreaterThanOrEqual(Temperature t) {
		return t.get() >= get();
	}

	public boolean isLessThanorEqual(Temperature t) {
		return t.get() <= get();
	}

}

class Medico {
	public Medico(int i, String juan, String pérez, String s, String s1, int i1, int i2, boolean b) {

	}
}

class Enfermera {

}

class Listas {
	//atributos
	private Medico[] medicos;
	private Enfermera[] enfermeras;

	//Constructor
	public Listas(){
		medicos = new Medico[5];
		enfermeras = new Enfermera[5];

		Medico med1 = new Medico(1, "Juan" , "Pérez", "6799652-3", "1234567870101", 89098, 15000, true);
		Medico med2 = new Medico(2, "Luis", "Gutiérrez", "8964547-3", "9876543260101", 98554, 12000, false);
		Medico med3 = new Medico(3, "Eduardo", "González", "8786456-6", "5642871750101", 5653, 17000, true);
		Medico med4 = new Medico(4, "Guadalupe", "Torres", "5684873-1", "4562973920101", 10098, 17000, true);
		Medico med5 = new Medico(5, "María", "Castillo", "8765485-7", "569395290101", 67965, 13000, false);

		medicos[1] = med1;
		medicos[2] = med2;
		medicos[3] = med3;
		medicos[4] = med4;
		medicos[5] = med5;
	}

	//Sets y gets
//Medicos
	public void setMedicos(Medico[] medicos){
		this.medicos = medicos;
	}
	public Medico[] getMedicos(){
		return medicos;
	}
	//Enfermeras
	public void setEnfermeras(Enfermera[] enfermeras){
		this.enfermeras = enfermeras;
	}
	public Enfermera[] getEnfermeras(){
		return enfermeras;
	}


}

class coordinate {
	
}

public class ScratchTest {
	@Test
	public void scratch15() {
		int a11 = 65536 + 'A' + 1;
		char ch22 = (char) a11;
		assertEquals('B', ch22);
	}
	public void scratch14() {
		int charCounter = 0;
		int rawSize = 0;
		String line = "2 2 2";
		List<Integer> myIntArray = new ArrayList<>();
		while(charCounter < line.length()){
			char a_char = line.charAt(charCounter);
			if(a_char != ' '){
				System.out.print(String.valueOf(a_char)+" ");
				int temp = Character.digit(a_char, 10);
				myIntArray.add(temp);
				System.out.println(String.valueOf(myIntArray.get(rawSize)));
				rawSize++;
			}
			charCounter++;
		}
	}
	public void scratch13() {
//		permutation(Arrays.asList(1, 2, 3));
	}

	public static void permutation(List<coordinate> nums) {
		List<List<coordinate>> accum = new ArrayList<List<coordinate>>();
		permutation(accum, Arrays.<coordinate>asList(), nums);
		System.out.println(accum);
	}

	private static void permutation(List<List<coordinate>> accum, List<coordinate> prefix, List<coordinate> nums) {
		int n = nums.size();
		if (n == 0) {
			accum.add(prefix);
		} else {
			for (int i = 0; i < n; ++i) {
				List<coordinate> newPrefix = new ArrayList<coordinate>();
				newPrefix.addAll(prefix);
				newPrefix.add(nums.get(i));
				List<coordinate> numsLeft = new ArrayList<coordinate>();
				numsLeft.addAll(nums);
				numsLeft.remove(i);
				permutation(accum, newPrefix, numsLeft);
			}
		}
	}

	public void scratch12() {
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		map.put(2,5);
		map.put(9,35);
		map.put(20,50);
		map.put(12,15);
		map.put(4,20);
		assertEquals("[2=5, 4=20, 9=35]", map.entrySet().stream()
				.filter(entry -> entry.getKey() < 10).collect(Collectors.toList()).toString());
	}

	public void scratch11() {
//		Scanner scanner = new Scanner("3 4 5 6 7 8 9 10 11");
		Scanner scanner = new Scanner("3 4 5 6");
		int[] values = new int[6];
		for (int i = 0; i < values.length && scanner.hasNextInt(); ++i) {
			values[i] = scanner.nextInt();
		}
		int a = values[0];
		int b = values[1];
		int c = values[2];
		int d = values[3];
		int e = values[4];
		int f = values[5];
		assertEquals("3", "" + values[0]);
		assertEquals("6", "" + values[3]);
		assertEquals("0", "" + values[5]);
	}
	public void scratch10() {
		String pattern = "\\/\\/s.ytimg.com\\/yts\\/jsbin\\/html5player-en_US-vfllxLx6Z\\/html5player.js";
//		String pattern = "\\s";
	}
	public void scratch9() {
		Scanner scanner = new Scanner("Hello#inline comment\n world.");
		scanner.useDelimiter("#.*(\r?\n|\r)?");
		assertEquals("Hello", scanner.next());
		assertEquals(" world.", scanner.next());
	}

	public void scratch8() {
		List<Integer> a = Arrays.asList(123, 123, 123, 123, 123, 123, 123, 123,
				123, 123);
		List<Integer> b = Arrays.asList(null, 3, null, null, 2, 3, null, 1,
				null, 2);
		List<Integer> merged = merge(a, b);
		assertEquals(Arrays.asList(123, 1233, 123, 123, 1232, 1233, 123, 1231, 123, 1232), merged);
	}

	private List<Integer> merge(List<Integer> a, List<Integer> b) {
		List<Integer> merged = new ArrayList<>(a.size());
		for (int i = 0; i < a.size(); ++i) {
			Integer x1 = a.get(i);
			Integer x2 = b.get(i);
			merged.add(Integer.parseInt(String.format("%s%s", x1 == null ? "" : x1, x2 == null ? "" : x2)));
		}
		return merged;
	}

	public void scratch7() {
		AudioInputStream audioInputStream = null;

		try {
			audioInputStream = AudioSystem.getAudioInputStream(getClass().getClassLoader().getResource("files" + File.separator + "newGame.wav"));
			AudioFormat audioFormat = audioInputStream.getFormat();
			DataLine.Info info = new DataLine.Info(Clip.class, audioFormat);
			Clip clip = (Clip) AudioSystem.getLine(info);
			clip.open(audioInputStream);
			clip.addLineListener(new LineListener() {
				@Override
				public void update(LineEvent event) {
					if (event.equals(CLOSE) || event.equals(STOP)) {
						System.exit(0);
					}
				}
			});
			clip.start();
		} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
			System.out.println(e.getMessage());
			System.exit(-1);
		} finally {
			try {
				if (audioInputStream != null) {
					audioInputStream.close();
				}
			} catch (IOException e) {
				System.out.println(e.getMessage());
				System.exit(-1);
			}
		}

	}

	public void scratch6() {
		Scanner scanner = new Scanner("There are seven words in this line.\n" +
				"And then there are few more words in this line.");
		List<String> phraseSet = new ArrayList<>();
		String prev = scanner.next();
		while (scanner.hasNext()) {
			String word = scanner.next();
			String phrase = prev + " " + word;
			phraseSet.add(phrase);
			prev = word;
		}
		for (String phrase : phraseSet) {
			System.out.println(phrase);
		}
	}

	public void scratch5() {
		assertEquals(88, getMax(8));
	}

	int getMax(int x) {
		switch (x) {
			case 0:
				return 1;
			case 1:
				return 2;
			default:
				return getMax(x - 1) + getMax(x - 2) + 1;
		}
	}

	public void scratch4() {
//		HttpPost post = new HttpPost("");
//		post.addHeader("", "");
	}

	public void scratch3() {
		LinkedList[] bucket = new LinkedList[20];
		for (int i = 0; i < bucket.length; ++i) {
			bucket[i] = new LinkedList();
		}
	}

	public void scratch2() throws IOException {
//		String line = new BufferedReader(new InputStreamReader(System.in)).readLine();
		Scanner chopper = new Scanner("1 5 3 4 5 5 5 4 3 2 5 5 5 3");
		chopper.nextLine();

		int[] numCount = new int[10];
		int number;

		while (chopper.hasNextInt()) {
			number = chopper.nextInt();
			numCount[number]++;
		}

		for (int i = 0; i < 10; i++) {
			System.out.println(i + " - " + numCount[i]);
		}
	}

	public void scratch() {
		Map<String, Integer> map = new TreeMap<>();
		String target = "something";

		for (String key : map.keySet()) {
			if (key.equals(target)) {
				// do something
			}
		}

		for (Map.Entry<String, Integer> entry : map.entrySet()) {
			if (entry.getKey().equals(target)) {
				// do something with entry.getValue()
			}
		}
	}
}
