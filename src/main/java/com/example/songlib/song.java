package com.example.songlib;

public class song implements Comparable<song>
{
    // Song detail, with name, artist, album, and year, of the song that is selected in the song list interface
    public String name;
    public String artist;
    public String album;
    public int year;

    public song(){}

    public song( String name, String artist, String album, int year )
    {
        this.name = name;
        this.artist = artist;
        this.album = album;
        this.year = year;
    }

    public String toString()
    {
        return name + "||" + artist + "||" + album + "||" + Integer.toString(year);
    }

    @Override
    public int compareTo( song other )
    {
        return name.compareTo( other.name );
    }
}