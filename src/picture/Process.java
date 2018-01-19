package picture;

import java.util.Arrays;
import java.util.Comparator;

public class Process {

  //fields
  private picture.Picture picture;
  private final int MAX_PIXEL_VAL = 255;
  private final int NUM_COLOUR_ARGS = 3;
  private final int NUM_PIXELS_3_BY_3 = 9;
  //TODO: NO MAGIC NUMBERS

  // Constructor
  public Process(Picture inputPicture) {
    this.picture = inputPicture;
  }

  public Process() {
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
        Color colour = new Color(MAX_PIXEL_VAL - this.picture.getPixel(x, y).getRed(),
            MAX_PIXEL_VAL - this.picture.getPixel(x, y).getGreen(),
            MAX_PIXEL_VAL - this.picture.getPixel(x, y).getBlue());
        this.picture.setPixel(x, y, colour);
      }
    }
  }

  public void greyScale() {
    for (int y = 0; y < this.picture.getHeight(); y++) {
      for (int x = 0; x < this.picture.getWidth(); x++) {
        int avg = (this.picture.getPixel(x, y).getRed() + this.picture.getPixel
            (x, y).getGreen() + this.picture.getPixel(x, y).getBlue()) /
            NUM_COLOUR_ARGS;
        Color colour = new Color(avg, avg, avg);
        this.picture.setPixel(x, y, colour);
      }
    }
  }

  public void rotate90() {
    Picture newPic = Utils.createPicture(this.picture
        .getHeight(), this.picture.getWidth());
    for (int y = 0; y < this.picture.getHeight(); y++) {
      for (int x = 0; x < this.picture.getWidth(); x++) {
        Color colour = this.picture.getPixel(x, y);
        newPic.setPixel(this.picture.getHeight() - 1 - y,x , colour);
      }
    }
    this.picture = newPic;
  }

  public void flipV() {

    Picture newPic = Utils.createPicture(this.picture.getWidth(), this.picture
        .getHeight());
    for (int y = 0; y < this.picture.getHeight(); y++) {
      for (int x = 0; x < this.picture.getWidth(); x++) {
        Color colour = this.picture.getPixel(x, y);
        newPic.setPixel(x, (this.picture.getHeight() - 1) - y, colour);
      }
    }
    this.picture = newPic;
  }

  public void flipH() {
    Picture newPic = Utils.createPicture(this.picture.getWidth(), this.picture
        .getHeight());
    for (int y = 0; y < this.picture.getHeight(); y++) {
      for (int x = 0; x < this.picture.getWidth(); x++) {
        Color colour = this.picture.getPixel(x, y);
        newPic.setPixel((this.picture.getWidth() - 1) - x, y, colour);
      }
    }
    this.picture = newPic;
  }

  public void blend(Picture[] pics) {

    // Tried to calculate min height and width using streams as it seemed
    // more elegant.

    int minWidth = Arrays.stream(pics).min(Comparator.comparing
        (Picture::getWidth)).get().getWidth();

    int minHeight = Arrays.stream(pics).min(Comparator.comparing
        (Picture::getHeight)).get().getHeight();

    Picture newPic = Utils.createPicture(minWidth, minHeight);

    for (int y = 0; y < minHeight; y++) {
      for (int x = 0; x < minWidth; x++) {
        int avgR = 0, avgG = 0, avgB = 0;
        for (Picture pic : pics) {
          avgR += pic.getPixel(x, y).getRed();
          avgG += pic.getPixel(x, y).getGreen();
          avgB += pic.getPixel(x, y).getBlue();
        }
        avgR = avgR / (pics.length);
        avgG = avgG / (pics.length);
        avgB = avgB / (pics.length);

        Color colour = new Color(avgR, avgG, avgB);
        newPic.setPixel(x, y, colour);
      }
    }
    this.picture = newPic;
  }

  public void blur() {
    Picture newPic = Utils.createPicture(this.picture.getWidth(), this.picture
        .getHeight());

    for (int y = 0; y < this.picture.getHeight(); y++) {
      for (int x = 0; x < this.picture.getWidth(); x++) {
        if (x == 0 || y == 0 || x == this.picture.getWidth() - 1 || y == this
            .picture.getHeight() - 1) {
          newPic.setPixel(x, y, this.picture.getPixel(x, y));
        } else {
          int avgR = 0, avgG = 0, avgB = 0;
          for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
              avgR += this.picture.getPixel(x + i, y + j).getRed();
              avgG += this.picture.getPixel(x + i, y + j).getGreen();
              avgB += this.picture.getPixel(x + i, y + j).getBlue();
            }
          }
          avgR = avgR / NUM_PIXELS_3_BY_3;
          avgG = avgG / NUM_PIXELS_3_BY_3;
          avgB = avgB / NUM_PIXELS_3_BY_3;
          Color colour = new Color(avgR, avgG, avgB);

          newPic.setPixel(x, y, colour);
        }
      }
    }
    this.picture = newPic;
  }
}