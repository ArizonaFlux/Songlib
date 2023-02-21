package com.example.songlib;

import java.util.PriorityQueue;
import java.io.*;


public class library
{
    private PriorityQueue<song> pq;
    private final song no_song = new song( "Empty", "Empty", "Empty", 0000 );
    private song selected;

    // no arg library;
    public library()
    {
        pq = new PriorityQueue<song>();
        selected = no_song;
    }

    // read in a library from file
    public library( String file_name )
    {
        pq = new PriorityQueue<song>();
        try (BufferedReader reader = new BufferedReader( new FileReader( file_name ) ) )
        {
            String line;
            while ( ( line = reader.readLine() ) != null )
            {
                String[] parts = line.split( "\\|\\|" );
                this.add( parts[0], parts[1], parts[2], Integer.parseInt( parts[3] ) );
            }
            selected = pq.peek();
        }
        catch ( IOException e )
        {
            System.err.println( "WARN: Save file not found, creating new save" );
            selected = no_song;
        }
    }

    public String toString()
    {
        String temp = new String();
        for ( song temp_song : pq )
        {
            temp += temp_song;
            temp += "\n";
        }
        return temp;
    }

    public void save( String file_name ) throws IOException
    {
        BufferedWriter writer = new BufferedWriter( new FileWriter( file_name ) );
        writer.write( this.toString() );
        writer.close();
    }

    public boolean add( String str_name, String str_artist, String str_album, String str_year )
    {
        String name = str_name.trim();
        String artist = str_artist.trim();
        String album = str_album.trim();
        int year = Integer.parseInt( str_year.trim() );
        
        // search for duplicated
        for ( song temp : pq )
        {
            if ( temp.name.equals( name ) && temp.artist.equals( artist ) )
            {
                System.err.println( "[WARN]: Song already exist." );
                return false;
            }
        }
        song temp = new song( name, artist, album, year );
        if ( pq.isEmpty() )
        {
            selected = temp;
        }
        pq.add( temp );
        return true;
    }

    public boolean add( String name, String artist, String album, int year )
    {
        // search for duplicated
        for ( song temp : pq )
        {
            if ( temp.name.equals( name ) && temp.artist.equals( artist ) )
            {
                System.err.println( "[WARN]: Song already exist." );
                return false;
            }
        }
        song temp = new song( name, artist, album, year );
        if ( pq.isEmpty() )
        {
            selected = temp;
        }
        pq.add( temp );
        return true;
    }

    public boolean edit( String old_name, String old_artist, String name, String artist, String album, int year )
    {
        for ( song temp : pq )
        {
            // search for the song
            if ( temp.name.equals( old_name ) && temp.artist.equals( old_artist ) )
            {
                temp.name = name;
                temp.artist = artist;
                temp.album = album;
                temp.year = year;
                return true;              
            }
        }
        return false;
    }

    public boolean delete( String name, String artist )
    {
        for ( song temp : pq )
        {
            if ( temp.name.equals(name) && temp.artist.equals(artist) )
            {
                return pq.remove( temp );
            }
        }
        return false;
    }

    public boolean select( String name, String artist )
    {
        for ( song temp : pq )
        {
            if ( temp.name.equals(name) && temp.artist.equals(artist) )
            {
                selected = temp;
                return true;
            }
        }
        return false;
    }

    public song get_selected()
    {
        return selected;
    }

    public song[] to_song()
    {
        song songs[] = new song[pq.size()];
        int counter = 0;
        for ( song temp : pq )
        {
            songs[counter++] = temp;
        }
        return songs;
    }
}
