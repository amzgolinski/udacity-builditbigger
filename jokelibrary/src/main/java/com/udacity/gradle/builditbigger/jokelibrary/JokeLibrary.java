package com.udacity.gradle.builditbigger.jokelibrary;

import java.util.Random;

public class JokeLibrary {

  private static final Random RANDOM = new Random();

  public JokeLibrary() {
    // empty
  }

  public String getJoke() {
    int index = RANDOM.nextInt(jokes.length);
    latency();
    return jokes[index];
  }

  private void latency() {
    try {
      Thread.sleep(5000);
    } catch(InterruptedException ex) {
      Thread.currentThread().interrupt();
    }
  }

  private static final String[] jokes = {
    "I have an inferiority complex, but it's not a very good one.",
    "There are 2 types of people in the world, those who can extrapolate from "+
      "incomplete data",
    "I saw a sign that said \"watch for children\" and I thought, \"That " +
      "sounds like a fair trade\"",
    "A roman legionnaire walks into a bar, holds up two fingers and says, " +
      "\"Five beers, please.\"",
    "Who's this Rorschach dude and why is he so good at drawing pictures of " +
      "my mom beating me?"
  };


}
