package com.davenotdavid.musicplayerlite;

import android.content.AsyncTaskLoader;
import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Loads and returns a list of songs by using an AsyncTask to retrieve from the user's device
 * storage.
 */
public class SongLoader extends AsyncTaskLoader<List<Song>> {

    // Log tag constant.
    private static final String LOG_TAG = SongLoader.class.getSimpleName();

    /**
     * Creates a new {@link SongLoader} object.
     *
     * @param context is the passed-in context from MainActivity.
     */
    public SongLoader(Context context) {
        super(context);
    }

    @Override
    protected void onStartLoading() {
        Log.d(LOG_TAG, "onStartLoading()");

        forceLoad();
    }

    @Override
    public List<Song> loadInBackground() {
        Log.d(LOG_TAG, "loadInBackground()");

        // Initializes the following local list/arraylist to add elements to and then return later
        // on.
        List<Song> songList = new ArrayList<>();

        // The following allows access to the content model.
        ContentResolver musicResolver = getContext().getContentResolver();

        // Retrieves the URI for external music files.
        Uri musicUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;

        // Queries the music files into a cursor, or database table, to permit read-write access.
        Cursor musicCursor = musicResolver.query(musicUri, null, null, null, null);

        // Initially checks to see if the data is valid.
        if (musicCursor != null && musicCursor.moveToFirst()) {

            // References each database column.
            int idColumn = musicCursor.getColumnIndex(MediaStore.Audio.Media._ID);
            int titleColumn = musicCursor.getColumnIndex(MediaStore.Audio.Media.TITLE);
            int artistColumn = musicCursor.getColumnIndex(MediaStore.Audio.Media.ARTIST);
            int pathColumn = musicCursor.getColumnIndex(MediaStore.Audio.Media.DATA);

            // Iterates and adds each database row into the song list.
            do {
                long songId = musicCursor.getLong(idColumn);
                String songTitle = musicCursor.getString(titleColumn);
                String songArtist = musicCursor.getString(artistColumn);
                String songPath = musicCursor.getString(pathColumn);
                songList.add(new Song(songId, songTitle, songArtist, songPath));
            }
            while (musicCursor.moveToNext());
        }

        return songList;
    }
}
