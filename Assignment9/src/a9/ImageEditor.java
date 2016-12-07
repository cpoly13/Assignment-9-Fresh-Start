package a9;

import java.awt.BorderLayout;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class ImageEditor {
	public static void main(String[] args) throws IOException {
		Picture f;
		System.out.println(args.length);
		
		if(args.length==0){
		f = PictureImpl.readFromURL("http://www.mtgeezer.com/wp-content/uploads/2014"
				+ "/07/nat-games-street-fighter-2-hadouken.jpg");
		}
		
		else{
			f = PictureImpl.readFromURL(args[0]);
		}

		JFrame main_frame = new JFrame();
		main_frame.setTitle("Assignment 9 Image Editor");
		main_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		

		ImageEditorModel model = new ImageEditorModel(f);
		ImageEditorView view = new ImageEditorView(main_frame, model);
 		ImageEditorController controller = new ImageEditorController(view, model);
		

		JPanel top_panel = new JPanel();
		top_panel.setLayout(new BorderLayout());
		top_panel.add(view, BorderLayout.CENTER);
		main_frame.setContentPane(top_panel);

		main_frame.pack();
		main_frame.setVisible(true);
	}
}