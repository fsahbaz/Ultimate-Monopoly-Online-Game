package com.nullPointer.Model;
import java.util.*;

import com.nullPointer.Model.Cards.Card;
import com.nullPointer.Model.Square.PropertySquare;
import com.nullPointer.Model.Square.Square;

import sun.security.util.Length;

public class DomainBoard {

	private ArrayList<Square> squares;
	private Queue<Card> cards;

	public String[] squareNames_inner = {"SQUEEZE PLAY","THE EMBARCADERO","FISHERMAN'S WHARF","TELEPHONE COMPANY","COMMUNITY CHEST","BEACON STREET","BONUS",
			"BOYLSTON STREET","NEWBURY STREET","PENNSYLVANIA RAILROAD","FIFTH AVENUE","MADISON AVENUE","STOCK EXCHANGE","WALL STREET","TAX REFUND","GAS COMPANY",
			"CHANCE","FLORIDA AVENUE","HOLLAND TUNNEL","MIAMI AVENUE","BISCAYNE AVENUE","SHORT LINE","REVERSE DIRECTION","LOMBARD STREET"};
			
	public String[] squareNames_middle = {"GO","MEDITERRANEAN AVENUE","COMMUNITY CHEST","BALTIC AVENUE","INCOME TAX","READING RAILROAD","ORIENTAL AVENUE",
			"CHANCE","VERMONT AVENUE","CONNECTICUT AVENUE","IN JAIL","ST. CHARLES PLACE","ELECTRIC COMPANY","STATES AVENUE","VIRGINIA AVENUE",
			"PENNSYLVANIA RAILROAD","ST. JAMES PLACE","COMMUNITY CHEST","TENNESSE AVENUE","NEW YORK AVENUE","FREE PARKING","KENTUCKY AVENUE","CHANCE",
			"INDIANA AVENUE","ILLINOIS AVENUE","B&O RAILROAD","ATLANTIC AVENUE","VENTNOR AVENUE","WATER WORKS","MARVIN GARDENS","ROLL ONCE","PACIFIC AVENUE",
			"NORTH CAROLINA AVENUE","COMMUNITY CHEST","PENNSYLVANIA AVENUE","SHORT LINE","CHANCE","PARK PLACE","LUXURY TAX","BOARDWALK"};

	public String[] squareNames_outer = {"SUBWAY","LAKE STREET","COMMUNITY CHEST", "NICOLLET AVENUE","HENNEPIN AVENUE", "BUS TICKET","CHECKER CAB CO.", "READING RAILROAD",
			"ESPLANADE AVENUE","CANAL STREET","CHANCE","CABLE COMPANY","MAGAZINE STREET","BOURBON STREET","HOLLAND TUNNEL","AUCTION","KATY FREEWAY","WESTHEIMER ROAD",
			"INTERNET SERVICE PROVIDER","KIRBY DRIVE","CULLEN BOULEVARD","CHANCE","BLACK & WHITE CAB CO.","DEKALB AVENUE","COMMUNITY CHEST","ANDREW YOUNG INTL BOULEVARD",
			"DECATUR STREET","PEACHTREE STREET","PAY DAY","RANDOLPH STREET","CHANCE","LAKE SHORE DRIVE","WACKER DRIVE","MICHIGAN AVENUE","YELLOW CAB CO.","B&O RAILROAD",
			"COMMUNITY CHEST","SOUTH TEMPLE","WEST TAMPLE","TRASH COLLECTOR","NORTH TEMPLE","TEMPLE SQUARE","GO TO JAIL","SOUTH STREET","BROAD STREET","WALNUT STREET",
			"COMMUNITY CHEST","MARKET STREET","BUS TICKET","SEWAGE SYSTEM","UTE CAB CO.","BIRTHDAY GIFT","MULHOLLAND DRIVE","VENTURA BOULEVARD","CHANCE","RODEO DRIVE"};

	public int[] price_inner_properties = {};
	public int[] price_middle_properties = {};	
	public int[] price_outer_properties = {};
			
	
	public DomainBoard(){
		squares=new ArrayList<Square>();
		ArrayList<Integer> list=new ArrayList<Integer>();
		list.add(1);
		//for(int i=0;i<15;i++)
		//squares.add(new PropertySquare("First Property","PropertySquare",100,"Blue",list));
		cards=new LinkedList<Card>();
	}
	
	public void addSquare(Square sq)
	{
		squares.add(sq);
	}
	
	public void createBoard()
	{
		for(int i=0; i<squareNames_middle.length;i++) {
			ArrayList<Integer> list=new ArrayList<Integer>();
			list.add(1);
			addSquare(new PropertySquare(squareNames_middle[i], "PropertySquare", 100, "Blue", list));
		}
		//addSquare(new GoSquare("Go", "T"));
		//addSquare(new PropertySquare("Mediterranian Avenue", "T", 0, 0));
//		addSquare(new GoSquare("Go", "T"));
//		addSquare(new GoSquare("Go", "T"));
//		addSquare(new GoSquare("Go", "T"));
//		addSquare(new GoSquare("Go", "T"));
//		addSquare(new GoSquare("Go", "T"));
//		addSquare(new GoSquare("Go", "T"));
//		addSquare(new GoSquare("Go", "T"));
//		addSquare(new GoSquare("Go", "T"));
	}
	
	

	public ArrayList<Square> getSquares() {
		return squares;
	}
	public Queue<Card> getCards() {
		return cards;
	}
}