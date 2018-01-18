package picture;

public class Main {

  public static void main(String[] args) {
    for (String arg : args) {
      System.out.println(arg);
      switch (args[0]) {
        case "invert" :
          Process process = new Process(Utils.loadPicture(args[1]));
          process.invert();
          Utils.savePicture(process.getPicture(), args[2]);
          break;
        case "grayscale" :
          process = new Process(Utils.loadPicture(args[1]));
          process.greyScale();
          Utils.savePicture(process.getPicture(), args[2]);
          break;
        case "rotate" :
          process = new Process(Utils.loadPicture(args[2]));
          switch (args[1]) {
            case "90":
              process.rotate90();
              break;
            case "180":
              process.rotate90();
              process.rotate90();
              break;
            case "270":
              process.rotate90();
              process.rotate90();
              process.rotate90();
              break;
          }
          Utils.savePicture(process.getPicture(), args[3]);
          break;
        case "flip" :
          process = new Process(Utils.loadPicture(args[2]));
          switch (args[1]) {
            case "V" :
              process.flipV();
              break;
            case "H" :
              process.flipH();
              break;
          }
          Utils.savePicture(process.getPicture(), args[3]);
          break;
        case "blend":
          process = new Process(Utils.loadPicture(args[1]));
          break;
        case "blur":
          process = new Process(Utils.loadPicture(args[1]));
          break;
        default:
          break;
      }
    }
    System.err.println("TODO: Finish implementing main");
  }
}
