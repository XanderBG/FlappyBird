package com.example.xander.fappybird;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class GameFragment extends Fragment {

    private LoginListener loginListener;

    public GameFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View myParentView = inflater.inflate(R.layout.fragment_game, container, false);
        // Inflate the layout for this fragment
        GameClock newGameClock = new GameClock();


        Background background = new Background();
        Bitmap cloudImage = BitmapFactory.decodeResource(getResources(), R.raw.clouds);
        background.setImage(cloudImage);

        Obstacle obstacle = new Obstacle();
        Bitmap pipeImage = BitmapFactory.decodeResource(getResources(), R.raw.pipe);
        obstacle.setImage(pipeImage);

        DrawingView drawingView = (DrawingView) myParentView.findViewById(R.id.game_fragment);
        Bird bird = new Bird(obstacle);
        Bitmap birdImage = BitmapFactory.decodeResource(getResources(), R.raw.bird);
        bird.setImage(birdImage);

        drawingView.addObject(background);
        drawingView.addObject(bird);
        drawingView.addObject(obstacle);

        newGameClock.subscribe(drawingView);
        //TODO
        // loginListener.onLogin(v, "bla bla");

        return myParentView;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        loginListener = (LoginListener) activity;
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    public void setLoginListener(LoginListener loginListener) {
        this.loginListener = loginListener;
    }

    @Override
    public void onResume() {
        super.onResume();
//
//        MediaPlayer mediaPlayer = MediaPlayer.create(getActivity(), R.raw.prey_overture);
//        mediaPlayer.start();
    }
}
