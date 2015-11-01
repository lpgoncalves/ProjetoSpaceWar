package game;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.IOException;

import sun.text.resources.ro.CollationData_ro;

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
	
	public static Font Get(){
		Font fonte =  new Font(FontGame.GetFontArcade(), Font.TRUETYPE_FONT, 18);; 
		return fonte;
	}
	
	public static Color SelectedColor()
	{
		Color selectedColor = new Color(255, 0, 0); // COR VERMELHA para o item selecionado
		return selectedColor;
	}
	
	public static Color DefaultColor()
	{
		Color defaultColor = new Color(0, 0, 0); // COR PRETA para o item que não está selecionado
		return defaultColor;
	}
	
}
