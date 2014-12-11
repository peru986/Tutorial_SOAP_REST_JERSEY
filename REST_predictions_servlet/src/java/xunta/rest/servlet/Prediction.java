package xunta.rest.servlet;

import java.io.Serializable;
import java.util.Objects;

public class Prediction implements Serializable, Comparable<Prediction>
{
    private int id;
    private String who;
    private String what;

    /*
     * Necesario por que lo usa el XMLEncoder
     */
    public Prediction()
    {
    }

    public Prediction( int id, String who, String what )
    {
        /*
         * Comprobar no null.  Si null, salta NullPointerException
         */
        Objects.requireNonNull( id, "id null en Prediction" );
        Objects.requireNonNull( who, "who null en Prediction" );
        Objects.requireNonNull( what, "what null en Prediction" );

        this.id = id;
        this.who = who;
        this.what = what;
    }

    public String getWho()
    {
        return this.who;
    }

    public String getWhat()
    {
        return this.what;
    }

    public int getId()
    {
        return this.id;
    }

    public void setId( int id )
    {
        this.id = id;
    }

    public void setWho( String who )
    {
        this.who = who;
    }

    public void setWhat( String what )
    {
        this.what = what;
    }

    // implementation de interface Comparable
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
        return "Prediction{" + "id=" + id + ", who=" + who + ", what=" + what + '}';
    }

    @Override
    public int hashCode()
    {
        int hash = 5;
        hash = 97 * hash + this.id;
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

}
