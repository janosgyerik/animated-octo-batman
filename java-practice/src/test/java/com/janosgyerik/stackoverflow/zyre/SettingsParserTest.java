package com.janosgyerik.stackoverflow.zyre;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SettingsParserTest {
	private static class Setting {
		final int id;
		final int x;
		final int y;

		private Setting(int id, int x, int y) {
			this.id = id;
			this.x = x;
			this.y = y;
		}

		@Override
		public String toString() {
			return String.format("[%d:{X=%d,Y=%d}]", id, x, y);
		}
	}

	@Test
	public void test() {
		Pattern pattern = Pattern.compile("\\[(\\d+):\\{X=(\\d+),Y=(\\d+)\\}\\]");
		String sample = "[1:{X=7,Y=29}][2:{X=52,Y=433}]";

		List<Setting> matches = new ArrayList<>();
		Matcher matcher = pattern.matcher(sample);
		while (matcher.find()) {
			matches.add(new Setting(
					Integer.parseInt(matcher.group(1)),
					Integer.parseInt(matcher.group(2)),
					Integer.parseInt(matcher.group(3))
			));
		}

		System.out.println(matches);
	}
}
