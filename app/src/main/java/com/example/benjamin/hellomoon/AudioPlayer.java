package com.example.benjamin.hellomoon;

import android.content.Context;
import android.media.MediaPlayer;

public class AudioPlayer {

    private MediaPlayer mPlayer;
    private boolean mIsPaused = false;

    public void stop()
    {
        if (mPlayer != null) {
            mPlayer.release();
            mPlayer = null;
            mIsPaused = false;
        }
    }

    public void start(Context c)
    {
        if (mPlayer != null) if (mPlayer.isPlaying()) return; // prevents audio from restarting from beginning when play button is pressed while audio is playing.

        if (!mIsPaused) {
            stop();

            mPlayer = MediaPlayer.create(c, R.raw.one_small_step);
            mPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer)
                {
                    stop();
                }
            });
        }

        mPlayer.start();
        mIsPaused = false;
    }

    public void pause()
    {
        if (mPlayer != null) {
            if (mPlayer.isPlaying()) {
                mIsPaused = true;
                mPlayer.pause();
            }
        }
    }
}
