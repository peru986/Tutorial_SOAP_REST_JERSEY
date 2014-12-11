package predictions;

import java.io.Serializable;

public class Prediction implements Serializable, Comparable<Prediction>
{
    private String who;   // person
    private String what;  // his/her prediction
    private int id;    // identifier used as lookup-key

    public Prediction()
    {
    }

    public void setWho( String who )
    {
        this.who = who;
    }

    public String getWho()
    {
        return this.who;
    }

    public void setWhat( String what )
    {
        this.what = what;
    }

    public String getWhat()
    {
        return this.what;
    }

    public void setId( int id )
    {
        this.id = id;
    }

    public int getId()
    {
        return this.id;
    }

    @Override
    public int compareTo( Prediction other )
    {
        return this.id - other.id;
    }

    @Override
    public int hashCode()
    {
        int hash = 7;
        hash = 37 * hash + this.id;
        return hash;
    }

    @Override
    public boolean equals( Object other )
    {
        if ( other == null ) {
            return false;
        }
        if ( getClass() != other.getClass() ) {
            return false;
        }

        return id == ( (Prediction) other ).id;
    }

    @Override
    public String toString()
    {
        return "Prediction[" + "who=" + who + ", what=" + what + ", id=" + id + ']';
    }
}
