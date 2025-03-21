import java.io.*;

public class StockfishEngine {

  // docs: https://official-stockfish.github.io/docs/stockfish-wiki/UCI-&-Commands.html
  //       https://www.wbec-ridderkerk.nl/html/UCIProtocol.html
  //       https://github.com/official-stockfish/Stockfish
  // download w/ "brew install stockfish" or any other method from https://stockfishchess.org/download/
  // modify path to which stockfish is installed below

  private static final String STOCKFISH_PATH = "/opt/homebrew/bin/stockfish";  // which stockfish

  public static String getBestMove(String fen, int depth) {
    String bestMove = "";

    try {
      // start
      ProcessBuilder pb = new ProcessBuilder(STOCKFISH_PATH);
      pb.redirectErrorStream(true); 
      Process p = pb.start();
      BufferedWriter w = new BufferedWriter(new OutputStreamWriter(p.getOutputStream()));
      BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));

      // init with uci
      w.write("uci\n");
      w.flush();

      // wait for uciok
      String l;
      while ((l = r.readLine()) != null) {
        if (l.contains("uciok")) {
          break;
        }
      }

      // options
      w.write("setoption name Threads value 8\n");

      // send fen
      w.write("position fen " + fen + "\n");
      w.flush();

      // wait for reply
      w.write("go depth ");
      w.write(depth + "\n");
      w.flush();

      // read reply
      while ((l= r.readLine()) != null) {
        if (l.startsWith("bestmove")) {
          System.out.println("found bestmove");
          bestMove = l.split(" ")[1];
          break;
        }
      }

      // end
      w.close();
      r.close();
      p.waitFor();
    } catch (IOException | InterruptedException e) {
      System.out.println("bad communication" + e.getMessage());
    }

    return bestMove;
  }
}

