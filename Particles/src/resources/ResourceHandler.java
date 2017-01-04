package resources;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class ResourceHandler {

	private static String dataDelim = ":";

	public enum FileType {
		RESOURCE("res");

		private String extension;

		FileType(String extension) {
			this.extension = extension;
		}

		public String getExtension() {
			return this.extension;
		}
	}

	// resources
	private HashMap<String, String> text = new HashMap<String, String>();
	private HashMap<String, Double> numbers = new HashMap<String, Double>();
	private HashMap<String, BufferedImage> images = new HashMap<String, BufferedImage>();
	private HashMap<String, Clip> audio = new HashMap<String, Clip>();
	private HashMap<String, Color> colors = new HashMap<String, Color>();
	private HashMap<String, Font> fonts = new HashMap<String, Font>();
	private HashMap<String, String[]> files = new HashMap<String, String[]>();

	public enum ResourceType {
		TEXT, NUMBER, IMAGE, AUDIO, COLOR, FONT, FILE;
	}

	public ResourceHandler(String srcPath) {
		if (srcPath != null) {
			loadAllResources(loadFile(srcPath));
		}
	}

	private void loadAllResources(String[] src) {

		try {
			for (String s : src) {

				String[] resourceData = s.split(dataDelim);

				ResourceType type = ResourceType.valueOf(resourceData[0]);
				String key = resourceData[1];
				String objsrc = resourceData[2];

				switch (type) {
				case TEXT:
					text.put(key, objsrc);
					break;
				case NUMBER:
					numbers.put(key, Double.parseDouble(objsrc));
					break;
				case IMAGE:
					images.put(key, loadImage(objsrc));
					break;
				case AUDIO:
					audio.put(key, loadAudio(objsrc));
					break;
				case COLOR:
					colors.put(key, loadColor(objsrc));
					break;
				case FONT:
					fonts.put(key, loadFont(objsrc));
					break;
				case FILE:
					files.put(key, loadFile(objsrc));
				default:
					break;
				}
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}
	}

	private static BufferedImage loadImage(String data) {
		BufferedImage image = null;

		try {
			image = ImageIO.read(ResourceHandler.class.getResourceAsStream(data));
		} catch (IOException e) {
			e.printStackTrace();
		}

		return image;
	}

	private static Clip loadAudio(String data) {
		Clip clip = null;

		try {
			clip = AudioSystem.getClip();
			clip.open(AudioSystem.getAudioInputStream(ResourceHandler.class.getResourceAsStream(data)));
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		}

		return clip;
	}

	private static Color loadColor(String data) {
		Color color = null;

		try {
			String[] clrData = data.split(",");

			int r = Integer.parseInt(clrData[0]);
			int g = Integer.parseInt(clrData[1]);
			int b = Integer.parseInt(clrData[2]);

			int a;

			if (clrData.length > 3) {
				a = Integer.parseInt(clrData[3]);
				color = new Color(r, g, b, a);
			} else {
				color = new Color(r, g, b);
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}

		return color;
	}

	private static Font loadFont(String data) {
		Font font = null;

		try {
			font = Font.createFont(Font.TRUETYPE_FONT, ResourceHandler.class.getResourceAsStream(data));
		} catch (FontFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return font;
	}

	private static String[] loadFile(String data) {
		String[] flData = null;
		try {
			ArrayList<String> fileData = new ArrayList<String>();
			BufferedReader reader = new BufferedReader(
					new InputStreamReader(ResourceHandler.class.getResourceAsStream(data)));

			String nextLine;
			while ((nextLine = reader.readLine()) != null) {
				fileData.add(nextLine);
			}

			flData = new String[fileData.size()];

			for (int i = 0; i < fileData.size(); i++) {
				flData[i] = fileData.get(i);
			}
			reader.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return flData;
	}

	public String getText(String key) {
		return this.text.get(key);
	}

	public double getNumber(String key) {
		return this.numbers.get(key);
	}

	public BufferedImage getImage(String key) {
		return this.images.get(key);
	}

	public Clip getAudio(String key) {
		return this.audio.get(key);
	}

	public Color getColor(String key) {
		return this.colors.get(key);
	}

	public Font getFont(String key) {
		return this.fonts.get(key);
	}

	public String[] getFile(String key) {
		return this.files.get(key);
	}

	public void clearResources() {
		this.text.clear();
		this.numbers.clear();
		this.images.clear();
		this.audio.clear();
		this.colors.clear();
		this.fonts.clear();
		this.files.clear();
	}
}
