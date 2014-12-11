package xunta.jersey;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement( name = "prediction" )
public class Prediction implements Comparable<Prediction>
{
    static Prediction BAD_Prediction = new Prediction( -1, "", "" );

    private String who;
    private String what;
    private int id;

    public Prediction()
    {
    }

    public Prediction( int id, String who, String what )
    {
        this.who = who;
        this.what = what;
        this.id = id;
    }

    public void setWho( String who )
    {
        this.who = who;
    }

    @XmlElement
    public String getWho()
    {
        return this.who;
    }

    public void setWhat( String what )
    {
        this.what = what;
    }

    @XmlElement
    public String getWhat()
    {
        return this.what;
    }

    public void setId( int id )
    {
        this.id = id;
    }

    @XmlElement
    public int getId()
    {
        return this.id;
    }

    @Override
    public int compareTo( Prediction other )
    {
        if ( this.id > other.id ) {
            return 1;
        }
        else if ( this.id == other.id ) {
            return 0;
        }
        else {
            return -1;
        }
    }

    @Override
    public String toString()
    {
        return "Prediction{" + "who=" + who + ", what=" + what + ", id=" + id + '}';
    }

}
