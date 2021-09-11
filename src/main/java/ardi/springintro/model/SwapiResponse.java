package ardi.springintro.model;

import java.util.List;

public class SwapiResponse<T> {
  List<T> results;
//  T result;

  public SwapiResponse() {
  }

//  public SwapiResponse(T result) {
//    this.result = result;
//  }

  public SwapiResponse(List<T> results) {
    this.results = results;
  }

  public List<T> getResults() {
    return results;
  }

  public void setResults(List<T> results) {
    this.results = results;
  }

//  public T getResult() {
//    return result;
//  }
//
//  public void setResult(T result) {
//    this.result = result;
//  }
}
