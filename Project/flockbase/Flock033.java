package flockbase;
import flockbase.*;
import java.util.*;
public class FlockY extends Flock
{
  private ArrayList<Bird> birdList = new ArrayList<Bird>();
  Bird leader_bird;
  public FlockY()
  {
    super();
    leader_bird = null;
  }
  public void addBird(Bird b)
  {
    this.getBirds().add(b);
    b.setFlock(this);
  }
  public void setLeader(Bird leader)
  {
    if(leader_bird != null)
      leader_bird.retireLead();
    leader_bird = leader;
    leader_bird.becomeLeader();
  }
  public ArrayList<Bird> getBirds()
  {
    return birdList;
  }
  public Bird getLeader()
  {
    return leader_bird;
  }
  public Flock split(int pos)
  {
    Flock newFlock = new FlockY();
    Bird currBird = this.getBirds().get(pos);
    currBird.becomeLeader();
    newFlock.addBird(currBird);
    newFlock.setLeader(currBird);
    for (int i = 0; i < pos; i++) {
      newFlock.addBird(this.getBirds().get(i));
    }
    this.getBirds().remove(pos);
    for (int i = 0; i < pos - 1; i++) {
      this.getBirds().remove(i);
    }
    return newFlock;
  }
  public void joinFlock(Flock f)
  {
    this.getLeader().retireLead();
    for(Bird bird: this.getBirds())
    {
      f.addBird(bird);
    }
  }
}
