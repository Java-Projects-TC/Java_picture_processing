package picture;

public class Process {

  //fields
  private picture.Picture picture;
  //TODO: NO MAGIC NUMBERS

  // Constructor
  public Process(Picture inputPicture) {
    this.picture = inputPicture;
  }

  // Methods

  public Picture getPicture() {
    return this.picture;
  }

  public void invert() {
    // not sure if the line below would help readability
    // Picture result = this.picture (then substitute into code)
    // use picture.setpixel
    for (int y = 0; y < this.picture.getHeight(); y++) {
      for (int x = 0; x < this.picture.getWidth(); x++) {
        Color colour = new Color(255 - this.picture.getPixel(x, y).getRed(),
            255 - this.picture.getPixel(x, y).getGreen(),
            255 - this.picture.getPixel(x, y).getBlue());
        this.picture.setPixel(x, y, colour);
      }
    }
  }

  public void greyScale() {
    for (int y = 0; y < this.picture.getHeight(); y++) {
      for (int x = 0; x < this.picture.getWidth(); x++) {
        int avg = (this.picture.getPixel(x, y).getRed() + this.picture.getPixel
            (x, y).getGreen() + this.picture.getPixel(x, y).getBlue()) / 3;
        Color colour = new Color(avg, avg, avg);
        this.picture.setPixel(x, y, colour);
      }
    }
  }

  public void rotate90(){
    Picture newPic = Utils.createPicture(this.picture
        .getHeight(), this.picture.getWidth());
    for (int y = 0; y < this.picture.getHeight(); y++) {
      for (int x = 0; x < this.picture.getWidth(); x++) {
        Color colour = this.picture.getPixel(y, (this.picture.getWidth() - 1)
            - x);
        newPic.setPixel(x, y, colour);
      }
    }
    this.picture = newPic;
  }

  public void flipV(){

    Picture newPic = Utils.createPicture(this.picture.getWidth(), this.picture
        .getHeight());
    for (int y = 0; y < this.picture.getHeight(); y++) {
      for (int x = 0; x < this.picture.getWidth(); x++) {
        Color colour = this.picture.getPixel(x, y);
        newPic.setPixel(x,(this.picture.getHeight() - 1) - y, colour);
      }
    }
    this.picture = newPic;
  }

  public void flipH(){
    Picture newPic = Utils.createPicture(this.picture.getWidth(), this.picture
        .getHeight());
    for (int y = 0; y < this.picture.getHeight(); y++) {
      for (int x = 0; x < this.picture.getWidth(); x++) {
        Color colour = this.picture.getPixel(x, y);

        newPic.setPixel((this.picture.getHeight() - 1) - x, y, colour);
      }
    }
    this.picture = newPic;
  }

  public void blend(){

  }

  public void blur() {

  }
}
