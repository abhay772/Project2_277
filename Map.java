import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * @author Abhay Solanki
 */
public class Map {

  private static Map instance = null;
  private char[][] map = new char[5][5];
  private boolean[][] revealed = new boolean[5][5];

  /*
   *Map constructor
   */
  private Map( ){

    map = loadMap(1);
    //fills in
    for (int i = 0; i < 5; i++) {
      Arrays.fill(revealed[i], false);
    }
  }

  /**
   * gets the single LandLine instance
   * @return static LandLine object
   * **/
  public static Map getInstance( ) {
    if(instance == null)
    {
      instance = new Map();
    }
    return instance;
  }



  /**
   *Load map method
   *@param mapNum the integer for the map number you wish to load
   *@return char[][] the whole map
   */
  public static char[][] loadMap(int mapNum){

    char[][] map = new char[5][5];

    try {

      //sets the map depending on the number given
      Scanner in = new Scanner(new File("Area1.txt"));
      if (mapNum == 2) {
        in = new Scanner(new File("Area2.txt"));
      } else if (mapNum == 3) {
        in = new Scanner(new File("Area3.txt"));
      }
      //removes spaces
      for (int i = 0; i < 5; i++) {
        map[i] = in.nextLine().replaceAll("\\s", "").toCharArray();
      }

    }

    catch (FileNotFoundException e)
    {
      System.out.println("Map not Loaded");
    }

    return map;
  }


  /**
   *Get Char at a location
   *@param p 
   *@return char for the character you are searching for
   */
  public char getCharAtLoc(Point p){

    int row = (int) p.getX();
    int col = (int) p.getY();

    reveal(p);

    return map[row][col];
  }

  /**
   *Returns the whole map with player
   *@param p the point you wish to use for player
   *@return String of the whole map
   */
  public String mapToString(Point p) {
    String temp = "";

    //fills in the map from 0 - map length
    for (int i = 0; i < map.length; i++) {

      //fills in the map from 0 - map length
      for (int j = 0; j < map[i].length; j++) {

        //check if its the player
        if (i == p.getX() && j == p.getY()) {
          //sets player to a *
          temp += '*' + " ";

          //check if it is a revealed part of the map
        } else if (revealed[i][j]) {
          //sets the revealed part of the map
          temp += map[i][j] + " ";
        } else {
          //else normal section of map
          temp += 'x' + " ";
        }
      }
      temp += '\n';
    }

    return temp;
  }

  /**
   *Find the start of the map
   *@return Point of the start
   */
  public static Point findStart() {

    Point Start = new Point();
    for (int i = 0; i < this.map.length; i++) {
      for (int j = 0; j < this.map[0].length; j++) {

        if (map[i][j] == 's') {
          Start.x = i;
          Start.y = j;
        }
      }
    }
    return Start;
  }

  /**
   *
   *Reveal a point on the map
   *@param p the point you wish to reveal
   */
  public void reveal(Point p) {
    revealed[(int) p.getX()][(int) p.getY()] = true;
  }

  /**
   *Remove char at a location
   *@param p the point you wish to reset
   */
  public void removeCharAtLoc(Point p) {
    map[(int) p.getX()][(int) p.getY()] = 'n';
  }

}