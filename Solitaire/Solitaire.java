import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.*;


public class Solitaire {
	public static void main(String[] args) {
		new Solitaire();
	}

	private Stack<Card> stock;
	private Stack<Card> waste;
	private Stack<Card>[] foundations;
	private Stack<Card>[] piles;
	private SolitaireDisplay display;

  private boolean hasWon;
  private int score;

  private Stack<String> moves;

	public Solitaire() {
    display = new SolitaireDisplay(this);
    display.frame.addKeyListener(new listener());

    this.reset();

    this.hasWon = false;

    this.score = 0;
  }

  private void reset() {
		foundations = new Stack[4];
		piles = new Stack[7];

    for (int i = 0; i < 4; i++) {
      foundations[i] = new Stack<Card>();
    }

    for (int i = 0; i < 7; i++) {
      piles[i] = new Stack<Card>();
    }

    stock = new Stack<Card>(); 

    waste = new Stack<Card>();

    this.createStock();

    this.deal();

    moves = new Stack<String>();  
  }

	//returns the card on top of the stock,
	//or null if the stock is empty
	public Card getStockCard() {
    return stock.empty() ? null : stock.peek();
	}

	//returns the card on top of the waste,
	//or null if the waste is empty
	public Card getWasteCard() {
    return waste.empty() ? null : waste.peek();
	}

	//precondition:  0 <= index < 4
	//postcondition: returns the card on top of the given
	//               foundation, or null if the foundation
	//               is empty
	public Card getFoundationCard(int index) {
    return foundations[index].empty() ? null : foundations[index].peek();
	}

	//precondition:  0 <= index < 7
	//postcondition: returns a reference to the given pile
	public Stack<Card> getPile(int index) {
    return piles[index];
	}

  public void createStock() {
    ArrayList<Card> c = new ArrayList<Card>();

    for (int i = 0; i < 4; i++) {
      for (int j = 1; j < 14; j++) {
        if (i == 0) {
          c.add(new Card(j, "d"));
        } else if (i == 1) {
          c.add(new Card(j, "h"));
        } else if (i == 2) {
          c.add(new Card(j, "c"));
        } else if (i == 3) {
          c.add(new Card(j, "s"));
        }
      }
    }

    while (c.size() > 0) {
      Random rand = new Random();
      int r = (int)(rand.nextInt(c.size()));
      stock.push(c.get(r));
      c.remove(r);
    }
  }

  public void dealThreeCards() {
    waste.push(stock.pop());
    waste.peek().turnUp();
    //for (int i = 0; i < 3 && !stock.empty(); i++) {
    //  waste.push(stock.pop());
    //  waste.peek().turnUp();
    //}
  }

  public void resetStock() {
    while (!waste.empty()) {
      stock.push(waste.pop());
      stock.peek().turnDown();
    }
  }

  public void deal() {
    for (int i = 0; i < 7; i++) {
      for (int j = 0; j < i + 1; j++) {
        if (j == i) {
          piles[i].push(stock.pop());
          piles[i].peek().turnUp();
        } else {
          piles[i].push(stock.pop());
        }
      }
    }
  }

  public boolean canAddToPile(Card c, int i) {
    if (piles[i].empty()) {
      return c.getRank() == 13;
    }
    Card top = piles[i].peek();
    if (!top.isFaceUp()) {
      return false;
    } 

    if (piles[i].empty()) {
      return false;
    } 

    if (c.getRank() == top.getRank() - 1 && top.red() == !c.red()){
      return true;
    }

    return false;
  }

  private Stack<Card> removeFaceUpCards(int i) {
    Stack<Card> c = new Stack<Card>();
    while (!piles[i].isEmpty() && piles[i].peek().isFaceUp()) {
      c.push(piles[i].pop());
    }
    return c;
  }

  private void addToPile(Stack<Card> c, int i) {
    while (!c.empty()) {
      piles[i].push(c.pop());
    }
  }

  private boolean canAddToFoundation(Card c, int i) {
    if (this.foundations[i].empty()) {
      return c.getRank() == 1;
    } else {
      return (c.getRank() - 1 == foundations[i].peek().getRank()) && 
        (c.getSuit().equals(foundations[i].peek().getSuit()));
    }
  }


	//called when the stock is clicked
	public void stockClicked() {
    if (this.hasWon) {
      hasWon = false;
      this.reset();
    }

    if (this.display.isWasteSelected() || this.display.isPileSelected()) {
      this.display.unselect();
      return;
    }

    if (!stock.empty()) {
      this.dealThreeCards();
    } else {
      this.resetStock();
    }

    this.display.unselect();

		System.out.println("stock clicked");
	}

	//called when the waste is clicked
	public void wasteClicked() {
    if (!this.waste.empty() && !this.display.isWasteSelected() && 
        !this.display.isPileSelected()) {
      this.display.selectWaste();
    } else if (this.display.isWasteSelected()) {
      System.out.println("double click watse");

      for (int i = 0; i < 4; i++) {
        if (foundations[i].empty() && waste.peek().getRank() == 1) {
          foundations[i].push(waste.pop());
          System.out.println("waste clicked");
          this.display.unselect();
          return;
        } else if (!foundations[i].empty() && canAddToFoundation(waste.peek(), i)) {
          this.foundations[i].push(waste.pop());
          System.out.println("waste clicked");
          this.display.unselect();
          return;
        }
      }
      
      for (int i = 0; i < 7; i++) {
        if (!waste.empty() && canAddToPile(waste.peek(), i)) {
          this.piles[i].push(waste.pop());
          this.display.unselect();
          break;
        }
      }

      this.display.unselect();
    }

		System.out.println("waste clicked");
	}

	//precondition:  0 <= index < 4
	//called when given foundation is clicked
	public void foundationClicked(int index) {
    int s = this.display.selectedPile();

    if (this.display.isWasteSelected() && canAddToFoundation(waste.peek(), index)) {
      this.foundations[index].push(waste.pop());
      this.display.unselect();
    } else if (this.display.isPileSelected() && canAddToFoundation(piles[s].peek(), index)) {
      this.foundations[index].push(piles[s].pop());
      this.display.unselect();
    }
    
    // logic to check if won
    if (!foundations[0].empty() && foundations[0].peek().getRank() == 13 && 
        !foundations[1].empty() && foundations[1].peek().getRank() == 13 &&
        !foundations[2].empty() && foundations[2].peek().getRank() == 13 && 
        !foundations[3].empty() && foundations[3].peek().getRank() == 13 &&
        hasWon == false) {
      System.out.println("YOU WIN!");
      this.hasWon = true;
      this.score++;
      System.out.println("current score is: " + this.score);
      System.out.println("click on stock to reset");
        }

		System.out.println("foundation #" + index + " clicked");
	}

	//precondition:  0 <= index < 7
	//called when given pile is clicked
	public void pileClicked(int index) {
    if (this.piles[index].empty() && this.display.selectedPile() == -1) {
      return;
    }

    // logic for moving waste to piles
    if (this.display.isWasteSelected() && canAddToPile(waste.peek(), index)) {
      this.piles[index].push(waste.pop());
      this.display.unselect();
    }

    //logic for selecting and deselecting piles
    if (!this.display.isWasteSelected() && !this.display.isPileSelected() && 
         !this.piles[index].empty() && this.piles[index].peek().isFaceUp()) {
      this.display.selectPile(index);
    } else if (this.display.selectedPile() == index) {
      // double click automoves to foundation, if possible
      System.out.println("double clicked pile " + index);

      for (int i = 0; i < 4; i++) {
        if (!piles[index].empty() && canAddToFoundation(piles[index].peek(), i)) {
          this.foundations[i].push(piles[index].pop());
          this.display.unselect();
          break;
        }
      }

      this.display.unselect();


      //if (!foundations[0].empty() && foundations[0].peek().getRank() == 13 && 
      //    !foundations[1].empty() && foundations[1].peek().getRank() == 13 &&
      //    !foundations[2].empty() && foundations[2].peek().getRank() == 13 && 
      //    !foundations[3].empty() && foundations[3].peek().getRank() == 13 &&
      //    hasWon == false) {
      //  System.out.println("YOU WIN!");
      //  this.hasWon = true;
      //  this.score++;
      //  System.out.println("current score is: " + this.score);
      //  System.out.println("click on stock to reset");
      //    }
    }

    //logic for moving in between piles
    if (this.display.isPileSelected() && this.display.selectedPile() != index) {
      int oi = this.display.selectedPile();
      Stack<Card> c = removeFaceUpCards(oi);
      if (canAddToPile(c.peek(), index)) {
        while (!c.empty()) {
          piles[index].push(c.pop());
        }
        this.display.unselect();
      } else {
        while (!c.empty()) {
          piles[oi].push(c.pop());
        }
        this.display.unselect();
      }
      this.display.unselect();
    }

    //logic for turning up a face down card if nothing is selected
    if (this.display.selectedPile() == -1 && !this.piles[index].empty()) {
      this.piles[index].peek().turnUp();
    }

		System.out.println("pile #" + index + " clicked");
	}

  public class listener implements java.awt.event.KeyListener {
    public void keyPressed(KeyEvent e) {
      if (e.getKeyChar() == 'r') {
        reset();
      }
    }

    public void keyReleased(KeyEvent e) {
    }

    public void keyTyped(KeyEvent e) {
    }

  }
}
