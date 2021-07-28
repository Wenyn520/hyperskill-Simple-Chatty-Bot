package com.example.test.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

//the class handle data source, here is database, if data source changes, only this class need to change,
//other class no need to change
public class DataSource {
    public static final String DB_NAME = "music";
    public static final String CONNECTION_STRING = "jdbc:mysql://localhost/" + DB_NAME +
            "?user=root&password=Winniefyc135&serverTimezone=UTC";

    public static final String TABLE_ALBUMS = "albums";
    public static final String COLUMN_ALBUM_ID = "_id";
    public static final String COLUMN_ALBUM_NAME = "name";
    public static final String COLUMN_ALBUM_ARTIST = "artist";
    public static final int INDEX_ALBUM_ID = 1;
    public static final int INDEX_ALBUM_NAME = 2;
    public static final int INDEX_ALBUM_ARTIST = 3;

    public static final String TABLE_ARTISTS = "artists";
    public static final String COLUMN_ARTIST_ID = "_id";
    public static final String COLUMN_ARTIST_NAME = "name";
    public static final int INDEX_ARTIST_ID = 1;
    public static final int INDEX_ARTIST_NAME = 2;

    public static final String TABLE_SONGS = "songs";
    public static final String COLUMN_SONG_ID = "_id";
    public static final String COLUMN_SONG_TRACK = "track";
    public static final String COLUMN_SONG_TITLE = "title";
    public static final String COLUMN_SONG_ALBUM = "album";
    public static final int INDEX_SONG_ID = 1;
    public static final int INDEX_SONG_TRACK = 2;
    public static final int INDEX_SONG_TITLE = 3;
    public static final int INDEX_SONG_ALBUM = 4;

    //for sorting
    public static final int ORDER_BY_NONE = 1;
    public static final int ORDER_BY_ASC = 2;
    public static final int ORDER_BY_DESC = 3;

    //sql query for select album for particular artist
    //SELECT albums.name FROM albums, artists WHERE albums.artist = artists._id AND artists.name = '
    public static final String QUERY_ALBUMS_BY_ARTIST_START = "SELECT " + TABLE_ALBUMS + "." + COLUMN_ALBUM_NAME + " FROM " +
            TABLE_ALBUMS + "," + TABLE_ARTISTS + " WHERE " + TABLE_ALBUMS + "." + COLUMN_ALBUM_ARTIST + "=" + TABLE_ARTISTS + "." +
            COLUMN_ARTIST_ID + " AND " + TABLE_ARTISTS + "." + COLUMN_ARTIST_NAME + "=\"";
    // ORDER BY albums.name
    public static final String QUERY_ALBUMS_BY_ARTIST_SORT = " ORDER BY " + TABLE_ALBUMS + "." + COLUMN_ALBUM_NAME;

    //get artist, album, track by given songs name
    //SELECT artists.name, albums.name, songs.track FROM songs, artists, albums WHERE albums.artist = artists._id
    //AND songs.album = albums._id AND songs.title = '
    public static final String QUERY_ARTIST_FOR_SONG_START = "SELECT " + TABLE_ARTISTS + "." + COLUMN_ARTIST_NAME + "," +
            TABLE_ALBUMS + "." + COLUMN_ALBUM_NAME + "," + TABLE_SONGS + "." + COLUMN_SONG_TRACK + " FROM " + TABLE_SONGS + "," +
            TABLE_ARTISTS + "," + TABLE_ALBUMS + " WHERE " + TABLE_ALBUMS + "." + COLUMN_ALBUM_ARTIST + "=" + TABLE_ARTISTS + "." +
            COLUMN_ARTIST_ID + " AND " + TABLE_SONGS + "." + COLUMN_SONG_ALBUM + "=" + TABLE_ALBUMS + "." + COLUMN_ALBUM_ID + " AND " +
            TABLE_SONGS + "." + COLUMN_SONG_TITLE + "=\"";
    //ORDER BY artists.name, albums.name;
    public static final String QUERY_ARTIST_FOR_SONG_SORT = " ORDER BY " + TABLE_ARTISTS + "." + COLUMN_ARTIST_NAME +
            "," + TABLE_ALBUMS + "." + COLUMN_ALBUM_NAME;
    //view
    //CREATE OR REPLACE VIEW artist_list AS
    //SELECT artists.name AS Artists, albums.name AS Albums, songs.track, songs.title
    //FROM songs, albums, artist
    //WHERE songs.album = albums._id AND albums.artist = artists._id
    //ORDER BY artists.name, albums.name, songs.track;
    public static final String TABLE_ARTIST_SONG_VIEW = "artist_list";
    public static final String CREATE_ARTIST_FOR_SONG_VIEW = "CREATE OR REPLACE VIEW " + TABLE_ARTIST_SONG_VIEW +
            " AS SELECT " + TABLE_ARTISTS + "." + COLUMN_ARTIST_NAME + " AS Artists," + TABLE_ALBUMS + "." + COLUMN_ALBUM_NAME +
            " AS Albums," + TABLE_SONGS + "." + COLUMN_SONG_TRACK + "," + TABLE_SONGS + "." + COLUMN_SONG_TITLE + " FROM "
            + TABLE_SONGS + "," + TABLE_ALBUMS + "," + TABLE_ARTISTS + " WHERE " + TABLE_SONGS + "." + COLUMN_SONG_ALBUM + "=" +
            TABLE_ALBUMS + "." + COLUMN_ALBUM_ID + " AND " + TABLE_ALBUMS + "." + COLUMN_ALBUM_ARTIST + "=" + TABLE_ARTISTS + "." +
            COLUMN_ARTIST_ID + " ORDER BY " + TABLE_ARTISTS + "." + COLUMN_ARTIST_NAME + "," + TABLE_ALBUMS + "." + COLUMN_ALBUM_NAME
            + "," + TABLE_SONGS + "." + COLUMN_SONG_TRACK;

    //SELECT artists, albums, track
    //FROM artist_list
    //WHERE title = "
    public static final String QUERY_VIEW_SONG_INFO = "SELECT " + TABLE_ARTISTS + "," + TABLE_ALBUMS + "," + COLUMN_SONG_TRACK
            + " FROM " + TABLE_ARTIST_SONG_VIEW + " WHERE " + COLUMN_SONG_TITLE + "=\"";

    //? is placeholder char used in PreparedStatement, when perform the query, will replace placeholder with user
    //input of 1 value
    //SELECT artists, albums, track FROM artist_list WHERE title = ?
    public static final String QUERY_VIEW_SONG_INFO_PREP = "SELECT " + TABLE_ARTISTS + "," + TABLE_ALBUMS + "," + COLUMN_SONG_TRACK
            + " FROM " + TABLE_ARTIST_SONG_VIEW + " WHERE " + COLUMN_SONG_TITLE + "=?";

    //transactions
    //note that id is auto increment field so no need to insert
    //INSERT INTO artists(name)
    //VALUES ("John");
    public static final String INSERT_ARTIST = "INSERT INTO " + TABLE_ARTISTS + "(" + COLUMN_ARTIST_NAME + ") VALUES (?)";
    //INSERT INTO albums(name,artist)
    //VALUES ("albumname","John");
    public static final String INSERT_ALBUM = "INSERT INTO " + TABLE_ALBUMS + "(" + COLUMN_ALBUM_NAME + "," + COLUMN_ALBUM_ARTIST
            + ") VALUES (?,?)";
    //INSERT INTO songs(track,title,album)
    //VALUES ();
    public static final String INSERT_SONG = "INSERT INTO " + TABLE_SONGS + "(" + COLUMN_SONG_TRACK + "," + COLUMN_SONG_TITLE +
            "," + COLUMN_SONG_ALBUM + ") VALUES (?,?,?)";
    //before insert, need to check whether data alr exist
    //SELECT _id FROM artists WHERE name = "";
    public static final String QUERY_ARTIST = "SELECT " + COLUMN_ARTIST_ID + " FROM " + TABLE_ARTISTS + " WHERE " +
            COLUMN_ARTIST_NAME + "=?";
    //SELECT _id FROM albums WHERE name = "";
    public static final String QUERY_ALBUM = "SELECT " + COLUMN_ALBUM_ID + " FROM " + TABLE_ALBUMS + " WHERE " +
            COLUMN_ALBUM_NAME + "=?";

    private Connection conn;
    //use PreparedStatement to avoid sql injection
    private PreparedStatement querySongInfoView;

    private PreparedStatement insertIntoArtists;
    private PreparedStatement insertIntoAlbums;
    private PreparedStatement insertIntoSongs;
    private PreparedStatement queryArtist;
    private PreparedStatement queryAlbum;

    //open connection
    public boolean open() {
        try {
            conn = DriverManager.getConnection(CONNECTION_STRING);
            //create instance of PreparedStatement object for sending parameterized sql statement to database for
            //precompilation, sql statement must contain ? placeholder, which will be replaced every time when make query
            querySongInfoView = conn.prepareStatement(QUERY_VIEW_SONG_INFO_PREP);
            //create PreparedStatement object for precompile, which can retrieve auto generated keys
            //artist id is needed in insert to albums table, album id is needed in insert to songs table
            insertIntoArtists = conn.prepareStatement(INSERT_ARTIST, Statement.RETURN_GENERATED_KEYS);
            insertIntoAlbums = conn.prepareStatement(INSERT_ALBUM, Statement.RETURN_GENERATED_KEYS);
            //no need song id
            insertIntoSongs = conn.prepareStatement(INSERT_SONG);
            queryArtist = conn.prepareStatement(QUERY_ARTIST);
            queryAlbum = conn.prepareStatement(QUERY_ALBUM);
            return true; //means connect
        } catch (SQLException e) {
            System.out.println("Cannot connect to database: " + e.getMessage());
            return false;
        }
    }

    //close connection
    public void close() {
        try {
            //close PreparedStatement
            if (querySongInfoView != null) {
                querySongInfoView.close();
            }
            if (insertIntoArtists != null) {
                insertIntoArtists.close();
            }
            if (insertIntoAlbums != null) {
                insertIntoAlbums.close();
            }
            if (insertIntoSongs != null) {
                insertIntoSongs.close();
            }
            if (queryArtist != null) {
                queryArtist.close();
            }
            if (queryAlbum != null) {
                queryAlbum.close();
            }
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            System.out.println("Cannot close connection: " + e.getMessage());
        }
    }

    //SELECT * from artists, pass into Artist object and arraylist
    //can choose to ORDER BY or not
    public List<Artist> queryArtists(int sortOrder) {
        StringBuilder sb = new StringBuilder("SELECT * FROM ");
        sb.append(TABLE_ARTISTS);
        //when want to ORDER BY
        if (sortOrder != ORDER_BY_NONE) {
            sb.append(" ORDER BY ");
            sb.append(COLUMN_ARTIST_NAME);
            //DESC
            if (sortOrder == ORDER_BY_DESC) {
                sb.append(" DESC");
                //ASC to be default (even when pass in invalid sortOrder)
            } else {
                sb.append(" ASC");
            }
        }
        //try with resource will auto close resource
        try (Statement statement = conn.createStatement();
             //execute above statement
             ResultSet results = statement.executeQuery(sb.toString())) {
            List<Artist> artists = new ArrayList<>();
            while (results.next()) {
                Artist artist = new Artist();
                //use column index to get is more efficient than column name
                artist.setId(results.getInt(INDEX_ARTIST_ID));
                artist.setName(results.getString(INDEX_ARTIST_NAME));
                artists.add(artist);
            }
            return artists;
        } catch (SQLException e) {
            System.out.println("Query failed: " + e.getMessage());
            return null;
        }
    }

    //query all albums of particular artist
    public List<String> queryAlbumsForArtist(String artistName, int sortOrder) {
        //SQL statement: SELECT albums.name FROM albums, artists WHERE albums.artist = artists._id AND
        //artists.name = ' ' ORDER BY albums.name
        StringBuilder sb = new StringBuilder(QUERY_ALBUMS_BY_ARTIST_START);
        sb.append(artistName);
        sb.append("\""); //add double quote
        sb.append(sortOrder(QUERY_ALBUMS_BY_ARTIST_SORT, sortOrder));

        System.out.println("SQL statement: " + sb.toString()); //print out sql statement
        try (Statement statement = conn.createStatement();
             ResultSet results = statement.executeQuery(sb.toString())) {
            List<String> albums = new ArrayList<>();
            while (results.next()) {
                //return only albums.name one column, so can just put 1
                albums.add(results.getString(1));
            }
            return albums;
        } catch (SQLException e) {
            System.out.println("Query failed: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    //get artist name, album name, track number with song name
    public List<SongArtist> queryArtistsForSong(String songName, int sortOrder) {
        //SELECT artists.name, albums.name, songs.track FROM songs, artists, albums WHERE albums.artist = artists._id
        //AND songs.album = albums._id AND songs.title = ' ' ORDER BY artists.name, albums.name;
        StringBuilder sb = new StringBuilder(QUERY_ARTIST_FOR_SONG_START);
        sb.append(songName);
        sb.append("\"");
        sb.append(sortOrder(QUERY_ARTIST_FOR_SONG_SORT, sortOrder));
        System.out.println("SQL statement: " + sb.toString());
        try (Statement statement = conn.createStatement();
             ResultSet results = statement.executeQuery(sb.toString())) {
            List<SongArtist> songArtists = new ArrayList<>();
            //result is artists.name, albums.name, songs.track
            while (results.next()) {
                SongArtist songArtist = new SongArtist();
                songArtist.setArtistName(results.getString(1));
                songArtist.setAlbumName(results.getString(2));
                songArtist.setTrack(results.getInt(3));
                songArtists.add(songArtist);
            }
            return songArtists;
        } catch (SQLException e) {
            System.out.println("Query failed: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    //ORDER BY statement
    public String sortOrder(String query, int sortOrder) {
        StringBuilder sb = new StringBuilder();
        if (sortOrder != ORDER_BY_NONE) {
            sb.append(query);
            //DESC
            if (sortOrder == ORDER_BY_DESC) {
                sb.append(" DESC");
                //ASC to be default (even when pass in invalid sortOrder)
            } else {
                sb.append(" ASC");
            }
        }
        return sb.toString();
    }

    //to print out column name for each column in songs table using metadata
    public void querySongsMetadata() {
        String sql = "SELECT * FROM " + TABLE_SONGS;
        try (Statement statement = conn.createStatement();
             ResultSet results = statement.executeQuery(sql)) {
            //getMetaData() return description of this ResultSet object's columns
            ResultSetMetaData meta = results.getMetaData();
            //getColumnCount() return number of columns in this ResultSet object
            int numColumns = meta.getColumnCount();
            //start from 1 cos column num start from 1 in getColumnName()
            for (int i = 1; i <= numColumns; i++) {
                //getColumnName() return name of specified column, which starts from 1
                System.out.format("Column %d in the songs table is called %s\n", i, meta.getColumnName(i));
            }
        } catch (SQLException e) {
            System.out.println("Query failed: " + e.getMessage());
        }
    }

    //count number of rows in a table
    public int getCount(String table) {
        String sql = "SELECT COUNT(*) AS count, MIN(_id) AS min_id FROM " + table;
        try (Statement statement = conn.createStatement();
             ResultSet results = statement.executeQuery(sql)) {
            results.next(); //cursor initially position before first row, then move next row
            int count = results.getInt("count"); //first column
            int min = results.getInt("min_id"); //second column
            System.out.format("Count = %d, Min = %d\n", count, min);
            return count;
        } catch (SQLException e) {
            System.out.println("Query failed: " + e.getMessage());
            e.printStackTrace();
            return -1;
        }
    }

    //create view for artist name, album of artist, song track and song name
    public boolean createViewForSongArtists() {
        try (Statement statement = conn.createStatement()) {
            //use execute() cos not returning result
            statement.execute(CREATE_ARTIST_FOR_SONG_VIEW);
            return true; //when view successful create
        } catch (SQLException e) {
            System.out.println("Create view failed: " + e.getMessage());
            return false;
        }
    }

    //query view to get artist name, albums, track by song name
    //use PreparedStatement when getting from user input to avoid sql injection, and not using StringBuilder
    public List<SongArtist> querySongInfoView(String title) {
        try {
            //setString() pass the String value to first (specified) ? placeholder
            querySongInfoView.setString(1, title);
            //executeQuery() execute sql query in this PreparedStatement object and return ResultSet object
            ResultSet results = querySongInfoView.executeQuery();
            List<SongArtist> songArtists = new ArrayList<>();
            while (results.next()) {
                SongArtist songArtist = new SongArtist();
                songArtist.setArtistName(results.getString(1));
                songArtist.setAlbumName(results.getString(2));
                songArtist.setTrack(results.getInt(3));
                songArtists.add(songArtist);
            }
            return songArtists;
        } catch (SQLException e) {
            System.out.println("Create view failed: " + e.getMessage());
            return null;
        }
    }

    //insert artist into db, but check if record exist before insert, return artist id
    //call by insertSong() only, so is private
    private int insertArtist(String name) throws SQLException {
        //SELECT _id FROM artists WHERE name = ?;
        queryArtist.setString(1, name);
        ResultSet results = queryArtist.executeQuery();
        //use if, cos will only have one id that match the name, so one row only
        if (results.next()) {
            //one column only
            return results.getInt(1);
        } else { //when there is no row returned
            //insert artist, INSERT INTO artists(name) VALUES (?);
            insertIntoArtists.setString(1, name);
            //executeUpdate() execute sql statement (DML and DDL that return nothing), return row count updated
            int affectedRows = insertIntoArtists.executeUpdate();
            //means that insert got problem
            if (affectedRows != 1) {
                throw new SQLException("Cannot insert artist");
            }
            //getGeneratedKeys() retrieve any auto generated keys (artist id) when insert
            ResultSet generatedKeys = insertIntoArtists.getGeneratedKeys();
            //use if cos only generate one id
            if (generatedKeys.next()) {
                return generatedKeys.getInt(1); //return the new generated id
            } else { //where there is error getting key
                throw new SQLException("Cannot get _id for artist");
            }
        }
    }

    //insert album into db with its artist id, but check if record exist before insert, return album id
    //call by insertSong() only, so is private
    private int insertAlbum(String name, int artistId) throws SQLException {
        //SELECT _id FROM albums WHERE name = ?;
        queryAlbum.setString(1, name);
        ResultSet results = queryAlbum.executeQuery();
        //use if, cos will only have one id that match the name, so one row only
        if (results.next()) {
            //one column only
            return results.getInt(1);
        } else { //when there is no row returned
            //insert album, INSERT INTO albums(name,artist) VALUES (?,?);
            insertIntoAlbums.setString(1, name);
            insertIntoAlbums.setInt(2, artistId);
            //executeUpdate() execute sql statement (DML and DDL that return nothing), return row count updated
            int affectedRows = insertIntoAlbums.executeUpdate();
            //means that insert got problem
            if (affectedRows != 1) {
                throw new SQLException("Cannot insert album");
            }
            //getGeneratedKeys() retrieve any auto generated keys (album id) when insert
            ResultSet generatedKeys = insertIntoAlbums.getGeneratedKeys();
            //use if cos only generate one id
            if (generatedKeys.next()) {
                return generatedKeys.getInt(1); //return the new generated id
            } else { //where there is error getting key
                throw new SQLException("Cannot get _id for album");
            }
        }
    }

    //insert song into db by providing song name, artist, album and track
    //demonstrate transaction
    public void insertSong(String title, String artist, String album, int track) {
        try {
            conn.setAutoCommit(false);
            //get the artist id for album
            int artistId = insertArtist(artist);
            //get album id for song
            int albumId = insertAlbum(album, artistId);
            //INSERT INTO songs(track,title,album) VALUES (?,?,?);
            insertIntoSongs.setInt(1, track);
            insertIntoSongs.setString(2, title);
            insertIntoSongs.setInt(3, albumId);
            //executeUpdate() execute sql statement (DML and DDL that return nothing), return row count updated
            int affectedRows = insertIntoSongs.executeUpdate();
            //means that if rows are being inserted
            if (affectedRows == 1) {
                //commit the changes made since last commit/rollback permanent and release db lock
                conn.commit();
            } else {
                throw new SQLException("Song insert fail");
            }
        } catch (Exception e) { //rollback when there is ANY exception, not only SQLException
            System.out.println("Insert song exception: " + e.getMessage());
            try {
                System.out.println("Performing rollback");
                //rollback() undo all changes made in current transaction and release any database lock currently
                //hold by this Connection object, this method can only used when auto-commit mode has disabled
                //rollback when there is sqlexception been thrown
                conn.rollback();
            } catch (SQLException e2) {
                System.out.println("Cannot rollback " + e2.getMessage());
            }
        } finally { //will reset auto commit
            try {
                System.out.println("Resetting default commit behaviour");
                conn.setAutoCommit(true);
            } catch (SQLException e) {
                System.out.println("Cannot reset auto commit: " + e.getMessage());
            }
        }
    }


}
