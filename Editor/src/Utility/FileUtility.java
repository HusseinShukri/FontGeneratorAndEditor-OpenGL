package Utility;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;

import Model.Basecore.Point;
import Model.Dto.FontDto;
import Model.Editor.Characters.FontCharacter;
import Model.Editor.Characters.FontCharacterHashMap;

public class FileUtility {

	public static FontDto GetFontDto(String path) throws IOException {
		File file = new File(path);
		if (!file.exists()) {
			throw new IOException("File Not Exist");
		}
		if (!file.canRead()) {
			throw new IOException("File Can't Read");
		}
		FontDto fontDto = new FontDto();
		try {
			Scanner scanner = new Scanner(file);
			// font name
			String[] str = path.split("[/]");
			fontDto.FontName = str[str.length - 1].split("[.]")[0];
			// size
			fontDto.FontWidth = fontDto.FontHeight = Integer.parseInt(scanner.nextLine().trim());
			// line to character space
			fontDto.lineCharacterSpacing = Integer.parseInt(scanner.nextLine().trim());
			// characters
			FontCharacterHashMap map = new FontCharacterHashMap();
			while (scanner.hasNextLine()) {
				var temp = scanner.nextLine();
				var chara = getFontCharacter(temp, fontDto.FontHeight);
				map.add(chara);
			}
			scanner.close();
			fontDto.FontCharactersSet = map;
		} catch (FileNotFoundException e) {
			System.out.println("An error occurred while reading the font file.");
			e.printStackTrace();
		}
		return fontDto;
	}

	private static FontCharacter getFontCharacter(String data, int characterHeight) {
		String[] str = data.split(",");
		LinkedList<Point> points = new LinkedList<Point>();
		for (int i = 1; i < str.length; i++) {
			String[] temp = str[i].split("-");
			int x;
			int y;
			try {
				x = Integer.parseInt(temp[0].trim());
				y = Integer.parseInt(temp[1].trim());
				points.add(new Point(x, Math.abs(y - characterHeight)));
			} catch (NumberFormatException e) {
				e.printStackTrace();
				System.exit(0);
			}
		}
		FontCharacter fChar = new FontCharacter(str[0], points);
		return fChar;
	}

}
