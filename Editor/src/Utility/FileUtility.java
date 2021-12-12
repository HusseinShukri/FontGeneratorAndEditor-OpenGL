package Utility;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;

import Models.FontCharacter;
import Models.FontHashMap;
import Models.Point;
import Models.DTO.FontDto;

public class FileUtility {

	public static String ReadFile(String path) throws IOException {
		File file = new File(path);
		if (!file.exists()) {
			throw new IOException("File Not Exist");
		}
		if (!file.canRead()) {
			throw new IOException("File Can't Read");
		}
		String data = "";
		try {
			Scanner myReader = new Scanner(file);
			while (myReader.hasNextLine()) {
				data += myReader.nextLine();

			}
			myReader.close();
		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
		return data;
	}

	public static FontHashMap ReadFontFile(String path) throws IOException {
		File file = new File(path);
		if (!file.exists()) {
			throw new IOException("File Not Exist");
		}
		if (!file.canRead()) {
			throw new IOException("File Can't Read");
		}
		FontHashMap map = new FontHashMap();
		try {
			Scanner myReader = new Scanner(file);
			while (myReader.hasNextLine()) {
				map.add(getFontCharacter(myReader.nextLine()));
			}
			myReader.close();
		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
		return map;
	}

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
			// size
			fontDto.characterSize = Integer.parseInt(scanner.nextLine().trim());
			// line to character space
			fontDto.lineToCharacterSpaceShift = Integer.parseInt(scanner.nextLine().trim());
			// characters
			FontHashMap map = new FontHashMap();
			while (scanner.hasNextLine()) {
				var temp = scanner.nextLine();
				var chara = getFontCharacter(temp);
				map.add(chara);
			}
			scanner.close();
			fontDto.fontCharacters = map;
		} catch (FileNotFoundException e) {
			System.out.println("An error occurred while reading the font file.");
			e.printStackTrace();
		}
		if (!fontDto.fontCharacters.contains(" ")) {
			fontDto.fontCharacters.add(new FontCharacter(" ", new LinkedList<>()));
		}
		if (!fontDto.fontCharacters.contains("")) {
			fontDto.fontCharacters.add(new FontCharacter("", new LinkedList<>()));
		}
		return fontDto;
	}

	private static FontCharacter getFontCharacter(String data) {
		String[] str = data.split(",");
		LinkedList<Point> points = new LinkedList<Point>();
		for (int i = 1; i < str.length; i++) {
			String[] temp = str[i].split("-");
			int x;
			int y;
			try {
				x = Integer.parseInt(temp[0].trim());
				y = Integer.parseInt(temp[1].trim());
				points.add(new Point(x, y));
			} catch (NumberFormatException e) {
				e.printStackTrace();
				System.exit(0);
			}

		}
		FontCharacter fChar = new FontCharacter(str[0], points);
		return fChar;
	}
	
	public static FontDto GetFontDtoBottomDown(String path) throws IOException {
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
			// size
			fontDto.characterSize = Integer.parseInt(scanner.nextLine().trim());
			// line to character space
			fontDto.lineToCharacterSpaceShift = Integer.parseInt(scanner.nextLine().trim());
			// characters
			FontHashMap map = new FontHashMap();
			while (scanner.hasNextLine()) {
				var temp = scanner.nextLine();
				var chara = getFontCharacterBottomDown(temp,fontDto.characterSize);
				map.add(chara);
			}
			scanner.close();
			fontDto.fontCharacters = map;
		} catch (FileNotFoundException e) {
			System.out.println("An error occurred while reading the font file.");
			e.printStackTrace();
		}
		if (!fontDto.fontCharacters.contains(" ")) {
			fontDto.fontCharacters.add(new FontCharacter(" ", new LinkedList<>()));
		}
		if (!fontDto.fontCharacters.contains("")) {
			fontDto.fontCharacters.add(new FontCharacter("", new LinkedList<>()));
		}
		return fontDto;
	}
	
	private static FontCharacter getFontCharacterBottomDown (String data,int characterSize) {
		String[] str = data.split(",");
		LinkedList<Point> points = new LinkedList<Point>();
		for (int i = 1; i < str.length; i++) {
			String[] temp = str[i].split("-");
			int x;
			int y;
			try {
				x = Integer.parseInt(temp[0].trim());
				y = Integer.parseInt(temp[1].trim());
				//(Math.abs(point.getY()-16))
				points.add(new Point(x, Math.abs(y-characterSize)));
			} catch (NumberFormatException e) {
				e.printStackTrace();
				System.exit(0);
			}
		}
		FontCharacter fChar = new FontCharacter(str[0], points);
		return fChar;
	}

}
