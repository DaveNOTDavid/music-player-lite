package com.davenotdavid.musicplayerlite;

/**
 * Class used to model the data for a single audio file.
 */
public class Song {

    // Fields used as data for storing for each track.
    private long mId;
    private String mTitle, mArtist;

    /**
     * Creates a {@link Song} object.
     *
     * @param songID is the ID of the song.
     * @param songTitle is the title of the song.
     * @param songArtist is the artist of the song.
     */
    public Song(long songID, String songTitle, String songArtist) {
        mId = songID;
        mTitle = songTitle;
        mArtist = songArtist;
    }

    /**
     * Getter method for the song's ID.
     */
    public long getID() {
        return mId;
    }

    /**
     * Getter method for the song's title.
     */
    public String getTitle() {
        return mTitle;
    }

    /**
     * Getter method for the song's artist.
     */
    public String getArtist() {
        return mArtist;
    }

    /**
     * Converts a {@link Song} object to a string.
     */
    @Override
    public String toString() {
        return "Song{" +
                "id=" + mId +
                ", title='" + mTitle + '\'' +
                ", artist='" + mArtist + '\'' +
                '}';
    }

    /**
     * Compares two objects - one of them being a {@link Song} object.
     *
     * @param o is the other object being compared with.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Song song = (Song) o;

        if (mId != song.mId) return false;
        if (mTitle != null ? !mTitle.equals(song.mTitle) : song.mTitle != null) return false;
        return mArtist != null ? mArtist.equals(song.mArtist) : song.mArtist == null;
    }
}
