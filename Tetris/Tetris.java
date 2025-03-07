public class Tetris {
  private MyBoundedGrid<Block> field;
  private BlockDisplay display;

  public Tetris(int r, int c) {
    this.field = new MyBoundedGrid(r, c);
    this.display = new BlockDisplay;
    this.display.setTitle("Tetris");
    this.display.showBlocks();
  }

  public Tetris() {
    this(20, 10);
  }
}
