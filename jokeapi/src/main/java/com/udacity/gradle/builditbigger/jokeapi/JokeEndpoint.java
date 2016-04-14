package com.udacity.gradle.builditbigger.jokeapi;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;

import javax.inject.Named;

import com.udacity.gradle.builditbigger.jokelibrary.JokeLibrary;
/**
 * An endpoint class we are exposing
 */
@Api(
  name = "jokeApi",
  version = "v1",
  namespace = @ApiNamespace(
    ownerDomain = "jokeapi.builditbigger.gradle.udacity.com",
    ownerName = "jokeapi.builditbigger.gradle.udacity.com",
    packagePath = ""
  )
)
public class JokeEndpoint {

  /**
   * A simple endpoint method that takes a name and says Hi back
   */
  @ApiMethod(name = "sayHi")
  public JokeBean sayHi(@Named("name") String name) {
    JokeBean response = new JokeBean();
    response.setData("Hi, " + name);
    return response;
  }

  @ApiMethod(name = "getJoke")
  public JokeBean getJoke() {
    JokeLibrary jokeLibrary = new JokeLibrary();
    JokeBean response = new JokeBean();
    response.setData(jokeLibrary.getJoke());
    return response;
  }

}
