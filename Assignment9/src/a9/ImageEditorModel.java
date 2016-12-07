package a9;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

public class ImageEditorModel extends Observable {

	private Picture original;
	private ObservablePicture current;
	private Pixel copiedPixel;
	private boolean copyButtonOverride;
	private ArrayList <Picture> previousPics;
	
	public ImageEditorModel(Picture f) {
		original = f;
		current = original.copy().createObservable();
		copyButtonOverride=false;
		previousPics= new ArrayList<Picture>();
		
	}
	
	public void disposePic(){
		setChanged();
		notifyObservers();
	}

	public ObservablePicture getCurrent() {
		return current;
	}

	public Pixel getPixel(int x, int y) {
		return current.getPixel(x, y);
	}

	public void paintAt(int x, int y, Pixel brushColor, int brush_size) {
		previousPics.add(current.copy());
		current.suspendObservable();
		
		
		for (int xpos=x-brush_size+1; xpos <=x+brush_size-1; xpos++) {
			for (int ypos=y-brush_size+1; ypos <=y+brush_size-1; ypos++) {
				if (xpos >= 0 &&
					xpos < current.getWidth() &&
					ypos >= 0 &&
					ypos < current.getHeight()) {
					current.setPixel(xpos, ypos, brushColor);
				}
			}
		}
		current.resumeObservable();
	}
	
	public void undo(){
		if(previousPics.size()>0){
		Picture previous=previousPics.get(previousPics.size()-1).copy();
		current.suspendObservable();
		for(int x=0; x<current.getWidth(); x++){
			for(int y=0; y<current.getHeight(); y++){
				current.setPixel(x, y,previous.getPixel(x, y));
			}
		}
		previousPics.remove(previousPics.size()-1);
		current.resumeObservable();
		}
	}
	
	public void setCopiedPixel(Pixel p){
		copiedPixel=p;
	}
	
	public Pixel getCopiedPixel(){
		return copiedPixel;
	}
	
	public void setCopyButtonOverride(boolean x){
		copyButtonOverride=x;
	}
	
	public boolean getCopyButtonOverride(){
		return copyButtonOverride;
	}
}
