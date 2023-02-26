package com.driver;

import org.springframework.stereotype.Repository;

import java.util.*;
@Repository
public class MovieRepository {
    // only define  hashmap and no intailisation
    private HashMap< String, Movie> movieMap;
     private HashMap<String,Director> directorMap;
     private HashMap<String,List<String>> directorMovieMapping;
    public void saveMovie(Movie movie){
        movieMap.put(movie.getName(), movie);
    }

    public void saveDirector(Director director){
        directorMap.put(director.getName(), director);
    }

    public MovieRepository() {
        this.movieMap = new HashMap<String,Movie>();
        this.directorMap = new HashMap<String,Director>();
        this.directorMovieMapping = new HashMap<String,List<String>>();
    }



          public void saveMovieDirectorPair(String movie,String director){
        // 1.add the movie
              if(movieMap.containsKey(movie)&& directorMap.containsKey(director)){
                  List<String>currentMoviesByDirector = new ArrayList<>();
              if(directorMovieMapping.containsKey(director))
                  currentMoviesByDirector=directorMovieMapping.get(director);

              currentMoviesByDirector.add(movie);

              directorMovieMapping.put(director,currentMoviesByDirector);
              }
          }
       public Movie findMovie(String movie){ return movieMap.get(movie);}
      public  Director findDirector(String director) { return directorMap.get(director);}

      public List<String> findMoviesFromDirector(String director){
        List<String> moviesList = new ArrayList<>();
        if(directorMovieMapping.containsKey(director))
            moviesList=directorMovieMapping.get(director);

        return moviesList;
      }
      public List<String> findAllMovies() {
        return new ArrayList<>(movieMap.keySet());
      }
      public void deleteDirector(String director){
        List<String> movies = new ArrayList<>();
        if(directorMovieMapping.containsKey(director)){
            // findind the  the movie by director name

            movies= directorMovieMapping.get(director);
             // del all the movie from movieDB by using moviename

            for(String movie:movies){
                if(movieMap.containsKey(movie)){
                    movieMap.remove(movie);

                }
            }
            //3. Deleteing the pair
            directorMovieMapping.remove(director);
        }
            // delete the director from directorDB

            if(directorMap.containsKey(director)){
                directorMap.remove(director);
                   }
        }
         public void deleteAllDirector(){
            HashSet<String> movieSet= new HashSet<>();
            // deleting director map
           directorMap= new HashMap<>();
           // finding out all the movies by al director

          for(String director:directorMovieMapping.keySet()){
              // iterating in the list of movie by director

              for(String movie:directorMovieMapping.get(director)){
                  movieSet.add(movie);
                  }
          }

          //deleting the movies from the movieDB
          for(String movie:movieSet){
              if(movieMap.containsKey(movie)){
                  movieMap.remove(movie);
              }
          }
          directorMovieMapping= new HashMap<>();
          }


      }

