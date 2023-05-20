package utilities;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public class ImageProfile {
	/*
	 * Classe mi- retailler ny sary rehetra miasa @ appli
	 * 
	 * */
	public ImageProfile() {}
	
	public Image ResizeCercle(String sary,int x,int y,Color loko){
		BufferedImage img = null;
		img = decouperEnCercle(sary,loko);
		
		Image reIm = img.getScaledInstance(x,y,Image.SCALE_SMOOTH);
		
		return reIm;
	}
	public static BufferedImage decouperEnCercle(String ImgFile, Color loko) {
		//importer l'image
		BufferedImage avatar = null;
		try {
			avatar = ImageIO.read(new File(ImgFile));
			avatar = retaillerBoribory(avatar,avatar.getWidth(),avatar.getHeight());
			int width = avatar.getWidth();
			
			//creer un autre image découpé centralement en forme de cercle
			BufferedImage formatAvatar = new BufferedImage(width,width,BufferedImage.TYPE_4BYTE_ABGR);
			Graphics2D graph = formatAvatar.createGraphics();
			graph.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
			
			int border = 1;
			
			Ellipse2D.Double forme = new Ellipse2D.Double(border,border,width-border*2,width-border*2);
			
			graph.setClip(forme);
			graph.drawImage(avatar,border,border,width-border*2,width-border*2,null);
			graph.dispose();
			
			graph = formatAvatar.createGraphics();
			graph.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
			int border1 = 3;
			
			Stroke s = new BasicStroke(10F,BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND);
			graph.setStroke(s);
			graph.setColor(loko);
			graph.drawOval(border1,border1,width-border1*2,width-border1*2);
			graph.dispose();
			
			return formatAvatar;
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private static BufferedImage retaillerBoribory(BufferedImage inImage, int newWidth, int newHeight) {
		try {
			int type = inImage.getColorModel().getTransparency();
			int width = inImage.getWidth();
			int height  = inImage.getHeight();
			
			//Antiailasing tsy be pixel
			
			RenderingHints render = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			
			//Compression
			
			render.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
			
			BufferedImage img = new BufferedImage(newWidth,newHeight,type);
			Graphics2D graphics = img.createGraphics();
			graphics.setRenderingHints(render);
			graphics.drawImage(inImage,0,0,newWidth,newHeight,0,0,width,height,null);
			graphics.dispose();
			
			return img;
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
