package dev.mvc.movie;

public class MainBoxOfficeVO {

  /** ������ ����� */ 
  private String thumb = "";
  /** �� �ð� */
  private String showTm;
  /** ��ǥ ���� ��� */ 
  private String watchGradeNm;
  /** �帣 */
  private String genre;
  /** ���� */
  private String director;
  /** ��� */
  private String actors;
  
  /** ��ȭ ���� */
  private int rank;
  /** ��ȭ �ڵ� */
  private String movieCd;
  /** ��ȭ ����*/
  private String movieNm;
  /** ���� ��¥ */
  private String openDt;
  /** ���� ������ */
  private String audiAcc;

  
  public MainBoxOfficeVO(){
    
  }
  
  public String getThumb() {
    return thumb;
  }
  public void setThumb(String thumb) {
    this.thumb = thumb;
  }
  public String getShowTm() {
    return showTm;
  }
  public void setShowTm(String showTm) {
    this.showTm = showTm;
  }
  public String getWatchGradeNm() {
    return watchGradeNm;
  }
  public void setWatchGradeNm(String watchGradeNm) {
    this.watchGradeNm = watchGradeNm;
  }
  public String getGenre() {
    return genre;
  }
  public void setGenre(String genre) {
    this.genre = genre;
  }
  public String getDirector() {
    return director;
  }
  public void setDirector(String director) {
    this.director = director;
  }
  public String getActors() {
    return actors;
  }
  public void setActors(String actors) {
    this.actors = actors;
  }
  public int getRank() {
    return rank;
  }
  public void setRank(int rank) {
    this.rank = rank;
  }
  public String getMovieCd() {
    return movieCd;
  }
  public void setMovieCd(String movieCd) {
    this.movieCd = movieCd;
  }
  public String getMovieNm() {
    return movieNm;
  }
  public void setMovieNm(String movieNm) {
    this.movieNm = movieNm;
  }
  public String getOpenDt() {
    return openDt;
  }
  public void setOpenDt(String openDt) {
    this.openDt = openDt;
  }
  public String getAudiAcc() {
    return audiAcc;
  }
  public void setAudiAcc(String audiAcc) {
    this.audiAcc = audiAcc;
  }
  
  
}
