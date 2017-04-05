package com.davenotdavid.musicplayerlite;

import android.content.Context;
import android.view.KeyEvent;
import android.widget.MediaController;

/**
 * A subclass of {@link MediaController} that presents a widget with song functionality including
 * play/pause, fast-forward/rewind, and etc. The widget also contains a seek bar, which updates as
 * the song plays and contains text indicating the duration of the song and the player's current
 * position.
 */
public class MusicController extends MediaController {

    // Context field used to reference MainActivity's UI.
    private Context mContext;

    /**
     * Creates a {@link MusicController} object.
     *
     * @param context is the Activity's context.
     */
    public MusicController(Context context){
        super(context);
        mContext = context;
    }

    /**
     * Overrides the following to stop the control bar from being hidden within three seconds.
     */
    @Override
    public void hide(){}

    /**
     * Invokes MainActivity's onBackPressed() method when the back navigation key is pressed while
     * the controller is displayed.
     *
     * @param event is the object that's used to reference the back navigation key.
     */
    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.KEYCODE_BACK){
            ((MainActivity) mContext).onBackPressed();
            return true;
        }

        return super.dispatchKeyEvent(event);
    }
}
