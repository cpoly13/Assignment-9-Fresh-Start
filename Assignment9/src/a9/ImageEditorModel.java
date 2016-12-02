package a9;

public class ImageEditorModel {

	private Picture original;
	private ObservablePicture current;
	private Pixel copiedPixel;
	private boolean copyButtonOverride;
	
	public ImageEditorModel(Picture f) {
		original = f;
		current = original.copy().createObservable();
		copyButtonOverride=false;
	}

	public ObservablePicture getCurrent() {
		return current;
	}

	public Pixel getPixel(int x, int y) {
		return current.getPixel(x, y);
	}

	public void paintAt(int x, int y, Pixel brushColor, int brush_size) {
		current.suspendObservable();;
		
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
