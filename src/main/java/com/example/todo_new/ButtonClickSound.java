package com.example.todo_new;

import javafx.scene.media.AudioClip;

import java.io.File;

public class ButtonClickSound {



    public static void setLogBtnClickingSound(){

        File file = new File("C:\\Users\\Tigran\\IdeaProjects\\demo1\\TODO_NEW1\\src\\main\\java\\com\\example\\todo_new\\click.mp3");
        AudioClip clip = new AudioClip(file.toURI().toString());
        clip.play();

    }
}
