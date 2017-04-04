package com.davenotdavid.musicplayerlite;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.TextView;

/**
 * Adapter that's used for displaying the songs to the ListView via MainActivity.
 */
public class SongAdapter extends ArrayAdapter<Song> {

    // Log tag constant.
    private static final String LOG_TAG = SongAdapter.class.getSimpleName();

    /**
     * Provides a view for an AdapterView (ListView, GridView, and etc.).
     *
     * @param context is an Activity context.
     * @param songList is a song list.
     */
    public SongAdapter(Context context, List<Song> songList){
        super(context, 0, songList);
    }

    /**
     * Provides a view for an AdapterView (ListView, GridView, and etc.).
     *
     * @param position is the position in the list of data that should be displayed in the
     *                 list item view.
     * @param convertView is the recycled view to populate.
     * @param parent is the parent ViewGroup that is used for inflation.
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // Initializes the following to hold child views later on.
        ViewHolder holder;

        // Checks if the existing view is being reused, otherwise inflates the view.
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent,
                    false);

            // Instantiates the following class with the recycled view.
            holder = new ViewHolder(convertView);

            // Associates the holder with the view for later lookup.
            convertView.setTag(holder);
        } else {

            // Otherwise, the view already exists so retrieve it.
            holder = (ViewHolder) convertView.getTag();
        }

        // Initializes and references, and then sets the songs' details as texts, accordingly.
        Song currentSong = getItem(position);
        if (currentSong != null) {
            holder.song.setText(currentSong.getTitle());
            holder.artist.setText(currentSong.getArtist());
        }

        // Hides the row for the song clicked. Otherwise, leaves it as visible.
        if (position == MainActivity.songPosition) {
            convertView.setVisibility(View.INVISIBLE);
        } else {
            convertView.setVisibility(View.VISIBLE);
        }

        return convertView;
    }

    // ViewHolder class used to hold and initialize the set of child views so they don't get looked
    // up repeatedly.
    private class ViewHolder {
        TextView song;
        TextView artist;

        /**
         * Creates a {@link ViewHolder} object.
         *
         * @param itemView is the passed-in recycled view from the adapter's getView() method.
         */
        private ViewHolder(View itemView) {
            song = (TextView) itemView.findViewById(R.id.song_title);
            artist = (TextView) itemView.findViewById(R.id.song_artist);
        }
    }
}
