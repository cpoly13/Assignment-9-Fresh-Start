package a9;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class PixelInspectorUI extends JPanel implements ActionListener {

	private JLabel x_label;
	private JLabel y_label;
	private JLabel pixel_info;
	private JPanel infoPanel;
	private JPanel pixelPanel;
	private JPanel buttonPanel;
	private JButton copyPixel;
	private JButton undo;
	private JButton open;
	private Pixel pixelToCopy;
	private ImageEditorModel model;
	private PictureView zoomWindow;
	
	
	public PixelInspectorUI(ImageEditorModel model) {
		this.model=model;
		
		infoPanel=new JPanel();
		infoPanel.setLayout(new BorderLayout());
		
		pixelPanel=new JPanel();
		pixelPanel.setLayout(new GridLayout(0,1));
		
		buttonPanel=new JPanel();
		buttonPanel.setLayout(new FlowLayout());
		
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
		
		zoomWindow=new PictureView(new ObservablePictureImpl(60,60));
	
		

		setLayout(new GridLayout(0,1));
		
		pixelPanel.add(x_label);
		pixelPanel.add(y_label);
		pixelPanel.add(pixel_info);
		
		infoPanel.add(pixelPanel, BorderLayout.WEST);
		infoPanel.add(zoomWindow,BorderLayout.EAST);
		
		buttonPanel.add(copyPixel);
		buttonPanel.add(undo);
		buttonPanel.add(open);
	
		add(infoPanel);
		add(buttonPanel);
	}
	
	
	
	public void setInfo(int x, int y, Pixel p) {
		x_label.setText("X: " + x);
		y_label.setText("Y: " + y);
		pixel_info.setText(String.format("(%3.2f, %3.2f, %3.2f)", p.getRed(), p.getBlue(), p.getGreen()));
		
		//chris added
		
		pixelToCopy=new ColorPixel(p.getRed(),p.getGreen(),p.getBlue());
	}
	
	public void setZoomPicture(Picture p){
		ObservablePicture preview_frame =zoomWindow.getPicture();
		preview_frame.suspendObservable();
		for (int x=0; x<preview_frame.getWidth(); x++) {
			for (int y=0; y<preview_frame.getHeight(); y++) {
				preview_frame.setPixel(x, y, p.getPixel(x, y));
			}
		}
		preview_frame.resumeObservable();
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getActionCommand().equals("copy")){
			if(pixelToCopy!=null){
		System.out.println("Pixel to copy is: Red: "+pixelToCopy.getRed()+ " Green: "+ pixelToCopy.getGreen()+
				" Blue: "+ pixelToCopy.getBlue());
		model.setCopiedPixel(pixelToCopy);
		model.setCopyButtonOverride(true);
			}
		}
		
		else if(arg0.getActionCommand().equals("undo")){
			model.undo();
		}
		
		else if(arg0.getActionCommand().equals("open")){
			
			String variable=JOptionPane.showInputDialog(null, "URL to open"); 
			String[] args=new String[]{variable};
			try {
				ImageEditor.main(args);
				model.disposePic();
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				
			}
		}
		
		
	}
}
