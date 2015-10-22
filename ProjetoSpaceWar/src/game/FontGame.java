package game;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.IOException;

public class FontGame {
	
	GraphicsEnvironment ge;
	private static String[] font;
	public FontGame(){
		try {
		 ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		     ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("res\\ARCADECLASSIC.TTF")));
		     font = ge.getAvailableFontFamilyNames();
		} catch (IOException|FontFormatException e) {
		     e.printStackTrace();
		}
		
	}

	public static String GetFontArcade(){
		     return font[0];
	}
}
