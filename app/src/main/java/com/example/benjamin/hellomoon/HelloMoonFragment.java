package com.example.benjamin.hellomoon;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class HelloMoonFragment extends Fragment {

    private AudioPlayer mPlayer = new AudioPlayer();
    private Button mPlayButton;
    private Button mStopButton;
    private Button mPauseButton; // challenge 1: Created a pause button.
    private Button mVideoButton; // challenge 2: Created a video button, when pressed, a new Activity hosting a Fragment is created.

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState)
    {
        View v = inflater.inflate(R.layout.fragment_hello_moon, parent, false);

        mPlayButton  = (Button) v.findViewById(R.id.hellomoon_playButton);
        mStopButton  = (Button) v.findViewById(R.id.hellomoon_stopButton);
        mPauseButton = (Button) v.findViewById(R.id.hellomoon_pauseButton); // challenge 1: get ref to pause button.
        mVideoButton = (Button) v.findViewById(R.id.hellomoon_videoButton); // challenge 2: get ref to video button.

        mPlayButton.setOnClickListener(new PlayButtonListener());
        mStopButton.setOnClickListener(new StopButtonListener());
        mPauseButton.setOnClickListener(new PauseButtonListener());
        mVideoButton.setOnClickListener(new VideoButtonListener());

        return v;
    }

    @Override
    public void onDestroy()
    {
        super.onDestroy();
        mPlayer.stop();
    }

    class PlayButtonListener implements View.OnClickListener {
        @Override
        public void onClick(View view)
        {
            mPlayer.start(getActivity());
        }
    }

    class StopButtonListener implements View.OnClickListener {
        @Override
        public void onClick(View view)
        {
            mPlayer.stop();
        }
    }

    // challenge 1: create a listener for pause button.
    class PauseButtonListener implements View.OnClickListener {
        @Override
        public void onClick(View view)
        {
            mPlayer.pause();
        }
    }

    // challenge 2: create a listener for video button.
    class VideoButtonListener implements View.OnClickListener {
        @Override
        public void onClick(View view)
        {
            mPlayer.pause(); // audio may be playing when user presses the video button.
            Intent i = new Intent(getActivity(), VideoContainerActivity.class);
            startActivity(i);
        }
    }
}
