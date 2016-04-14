package com.udacity.gradle.builditbigger.jokelibrary;

public class JokeLibrary {

  public JokeLibrary() {
    // empty
  }

  public String getJoke() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("A roman legionnaire walks into a bar, holds up two " +
      "fingers and says, \"Five beers, please.\"\n");
    return stringBuilder.toString();
  }
}
