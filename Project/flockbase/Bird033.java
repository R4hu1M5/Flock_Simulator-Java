package flockbase;
import flockbase.*;
import java.util.*;
import java.lang.*;
public class BirdX extends Bird
{
  private int maxSpeed;
  private int isLeader;
  private Position pos;
  private Position target;
  private Flock flock;
  public BirdX()
  {
    super();
    isLeader = 0;
    maxSpeed = 10;
  }
  public void becomeLeader()
  {
    isLeader = 1;
  }
  public void retireLead()
  {
    isLeader = 0;
  }
  public boolean collisionDetection(int c1, int c2)
  {
    ArrayList<Bird> b = this.getFlock().getBirds();
    int x = this.getPos().getX();
    int y = this.getPos().getY();
    int xt, yt, xd, yd;
    for(int i=0; i<b.size(); i++)
    {
      xt = b.get(i).getPos().getX();
      yt = b.get(i).getPos().getY();
      if(!(xt == x && yt == y))
      {
        xd = Math.abs(xt - (x + c1*maxSpeed));
        yd = Math.abs(yt - (y + c2*maxSpeed));
        if(xd < 20 && yd < 20)
        {
          return true;
        }
      }
    }
    return false;
  }
  protected void updatePos()
  {
    if(isLeader == 0)
    {
      Position leaderPosition = this.getFlock().getLeader().getPos();
      this.setTarget(leaderPosition.getX(), leaderPosition.getY());
    }
    int x = this.getPos().getX();
    int y = this.getPos().getY();
    int xt = this.getTarget().getX();
    int yt = this.getTarget().getY();
    int dx = xt - x;
    int dy = yt - y;
    int posx = 0, posy = 0;
    if(dx == 0)
    {
      posx = x;
      if(dy > 0)
      {
        if(collisionDetection(0, 1))
          posy = y + maxSpeed;
        else
          posy = y - maxSpeed;
      }
      else if(dy < 0)
      {
        if(collisionDetection(0, -1))
          posy = y - maxSpeed;
        else
          posy = y + maxSpeed;
      }
    }
    else if(dy == 0)
    {
      posy = y;
      if(dx > 0)
      {
        if(collisionDetection(1, 0))
          posx = x + maxSpeed;
        else
          posx = x - maxSpeed;
      }
      else if(dx < 0)
      {
        if(collisionDetection(-1, 0))
          posx = x - maxSpeed;
        else
          posx = x + maxSpeed;
      }
    }
    else if(dx > 0)
    {
      if(dy > 0)
      {
        if(collisionDetection(1, 1))
        {
          posx = x - maxSpeed;
          posy = y - maxSpeed;
        }
        else
        {
          posx = x + maxSpeed;
          posy = y + maxSpeed;
        }
      }
      else if(dy < 0)
      {
        if(collisionDetection(1, -1))
        {
          posx = x - maxSpeed;
          posy = y + maxSpeed;
        }
        else
        {
          posx = x + maxSpeed;
          posy = y - maxSpeed;
        }
      }
    }
    else if(dx < 0)
    {
      if(dy > 0)
      {
        if(collisionDetection(-1, 1))
        {
          posx = x + maxSpeed;
          posy = y - maxSpeed;
        }
        else
        {
          posx = x - maxSpeed;
          posy = y + maxSpeed;
        }
      }
      else if(dy < 0)
      {
        if(collisionDetection(-1, -1))
        {
          posx = x + maxSpeed;
          posy = y + maxSpeed;
        }
        else
        {
          posx = x - maxSpeed;
          posy = y - maxSpeed;
        }
      }
    }
    this.setPos(posx, posy);
  }
  public String getName()
  {
    if(isLeader == 1)
      return "Leader033";
    return "Bird033";
  }
}
