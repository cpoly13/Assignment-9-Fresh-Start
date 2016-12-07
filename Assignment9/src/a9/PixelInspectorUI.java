package a9;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class PixelInspectorUI extends JPanel implements ActionListener {

	private JLabel x_label;
	private JLabel y_label;
	private JLabel pixel_info;
	private JButton copyPixel;
	private JButton undo;
	private JButton open;
	private JTextField picSite;
	private Pixel pixelToCopy;
	private ImageEditorModel model;
	
	
	public PixelInspectorUI(ImageEditorModel model) {
		this.model=model;
		x_label = new JLabel("X: ");
		y_label = new JLabel("Y: ");
		pixel_info = new JLabel("(r,g,b)");
		
		copyPixel=new JButton("Copy Pixel");
		copyPixel.setActionCommand("copy");
		copyPixel.addActionListener(this);
		undo= new JButton("Undo");
		undo.setActionCommand("undo");
		undo.addActionListener(this);
		open=new JButton("Open");
		open.setActionCommand("open");
		open.addActionListener(this);
		
		picSite=new JTextField(10);
		picSite.addActionListener(this);
		

		setLayout(new GridLayout(7,1));
		add(x_label);
		add(y_label);
		add(pixel_info);
		add(copyPixel);
		add(undo);
		add(picSite);
		add(open);
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
		if(arg0.getActionCommand().equals("copy")){
		System.out.println("Pixel to copy is: Red: "+pixelToCopy.getRed()+ " Green: "+ pixelToCopy.getGreen()+
				" Blue: "+ pixelToCopy.getBlue());
		model.setCopiedPixel(pixelToCopy);
		model.setCopyButtonOverride(true);
		}
		
		else if(arg0.getActionCommand().equals("undo")){
			model.undo();
		}
		
		else if(arg0.getActionCommand().equals("open")){
			String[] args=new String[]{picSite.getText()};
			try {
				ImageEditor.main(args);
				model.disposePic();
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
	}
}
