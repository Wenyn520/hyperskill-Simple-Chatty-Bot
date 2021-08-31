import java.util.ArrayList;
import java.util.LinkedList;

//linkedlist coding challenge
public class Album {
    private String name;
    private String artist;
    private ArrayList <Song> songs;

    public Album(String name, String artist) {
        this.name = name;
        this.artist = artist;
        songs = new ArrayList<>();
    }

    public boolean addSong(String title, double duration){
        //check if song already exist
        //only add song if song not exist
        if(findSong(title) == null){
            Song newSong = new Song (title, duration);
            songs.add(newSong);
            return true;
        }
        return false;
    }

    public Song findSong(String name){
        for(Song checkedSong:songs){
            if(checkedSong.getTitle().equals(name)){
                return checkedSong;
            }
        }
        return null;
    }
    public boolean addToPlaylist(int trackNumber, LinkedList<Song> playlist){
        //track number starts from 1
        int index = trackNumber-1;
        //to ensure there is song in album
        if(index>=0 && index<songs.size()){
            //add the song to playlist
            playlist.add(songs.get(index));
            //indicate success added
            return true;
        }else if (index<0){
            System.out.println("No songs found in album");
            return false;
        }
        System.out.println("Track number not found");
        return false;
    }
    //overload method
    public boolean addToPlaylist(String title, LinkedList<Song> playlist){
        //find whether song exist
        Song checkedSong = findSong(title);
        if(checkedSong!=null){
            playlist.add(checkedSong);
            return true;
        }
        System.out.println("Song not found");
        return false;
    }

    public String getName() {
        return name;
    }

    public String getArtist() {
        return artist;
    }
}
