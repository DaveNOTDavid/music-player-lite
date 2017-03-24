package com.davenotdavid.musicplayerlite;

import android.content.AsyncTaskLoader;
import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
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

        ContentResolver musicResolver = getContext().getContentResolver();

        // Retrieves the URI for external music files.
        Uri musicUri = android.provider.MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;

        // Queries the music files.
        Cursor musicCursor = musicResolver.query(musicUri, null, null, null, null);

        // Initially checks to see if the data is valid.
        if (musicCursor != null && musicCursor.moveToFirst()) {

            // Column indexes used for retrieval purposes.
            int idColumn = musicCursor.getColumnIndex
                    (android.provider.MediaStore.Audio.Media._ID);
            int titleColumn = musicCursor.getColumnIndex
                    (android.provider.MediaStore.Audio.Media.TITLE);
            int artistColumn = musicCursor.getColumnIndex
                    (android.provider.MediaStore.Audio.Media.ARTIST);

            // Iterates and adds new Song objects to the list, accordingly..
            do {
                long thisId = musicCursor.getLong(idColumn);
                String thisTitle = musicCursor.getString(titleColumn);
                String thisArtist = musicCursor.getString(artistColumn);
                songList.add(new Song(thisId, thisTitle, thisArtist));
            }
            while (musicCursor.moveToNext());
        }

        return songList;
    }
}
