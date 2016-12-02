package a9;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PixelInspectorUI extends JPanel implements ActionListener {

	private JLabel x_label;
	private JLabel y_label;
	private JLabel pixel_info;
	private JButton copyPixel;
	private Pixel pixelToCopy;
	private ImageEditorModel model;
	
	
	public PixelInspectorUI(ImageEditorModel model) {
		this.model=model;
		x_label = new JLabel("X: ");
		y_label = new JLabel("Y: ");
		pixel_info = new JLabel("(r,g,b)");
		copyPixel=new JButton("Copy Pixel");
		copyPixel.addActionListener(this);
		

		setLayout(new GridLayout(4,1));
		add(x_label);
		add(y_label);
		add(pixel_info);
		add(copyPixel);
	}
	
	public void setInfo(int x, int y, Pixel p) {
		x_label.setText("X: " + x);
		y_label.setText("Y: " + y);
		pixel_info.setText(String.format("(%3.2f, %3.2f, %3.2f)", p.getRed(), p.getBlue(), p.getGreen()));
		
		//chris added
		
		pixelToCopy=new ColorPixel(p.getRed(),p.getGreen(),p.getBlue());
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println("Pixel to copy is: Red: "+pixelToCopy.getRed()+ " Green: "+ pixelToCopy.getGreen()+
				" Blue: "+ pixelToCopy.getBlue());
		model.setCopiedPixel(pixelToCopy);
		model.setCopyButtonOverride(true);
		
	}
}
