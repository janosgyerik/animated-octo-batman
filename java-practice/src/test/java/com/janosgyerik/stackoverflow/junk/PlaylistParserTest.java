package com.janosgyerik.stackoverflow.junk;


import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

class Context {

    public Iterable<? extends String> fileList() {
        return null;
    }

    public FileInputStream openFileInput(String file) {
        return null;
    }
}

class Song {

}

class MediaManager {

    public static Song getSong(Context context, String file) {
        return null;
    }
}

class Playlist {

    private String name;

    public boolean isEmpty() {
        return true;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addSong(Song song) {

    }
}

public class PlaylistParserTest {
    public static ArrayList<Playlist> getAllPlaylists( Context context ){
        ArrayList<Playlist> playlists = new ArrayList<Playlist>();
        ArrayList<String> fileList = new ArrayList<>();

        //this will get me all playlist files available for my application
        for (String file : context.fileList()) {
            if (file.endsWith(".playlist")) {
                fileList.add(file);
            }
        }

        for( String file : fileList ){
            Playlist newPlaylist = loadPlaylist( context, file );

            if( !newPlaylist.isEmpty() ){
                playlists.add(newPlaylist);
            }
        }

        return playlists;
    }

    private static Playlist loadPlaylist(Context context, String file) {
        Playlist playlist = new Playlist();

        try {
            FileInputStream inputStream = context.openFileInput(file);
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String line;
            List<String> files = new ArrayList<String>();
            while ((line = bufferedReader.readLine()) != null) {
                files.add(line);
            }
            bufferedReader.close();

            Iterator<String> iter = files.iterator();
            playlist.setName(iter.next());
            while (iter.hasNext()) {
                String path = iter.next();
                Song song = MediaManager.getSong(context, path);
                if (song != null) {
                    playlist.addSong(song);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return playlist;
    }
}
