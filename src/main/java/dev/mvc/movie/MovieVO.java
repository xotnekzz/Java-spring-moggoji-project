package dev.mvc.movie;

import org.springframework.web.multipart.MultipartFile;

public class MovieVO {
  /** ��ȭ �ڵ� */
  private String movieCd;
  /** ��ȭ ��ȣ */
  private int movieno;
  /** ��ȭ ���� */
  private String movieNm;
  /** ��ȭ �������� */
  private String movieNmEn;
  /** ���� �� */
  private String openDt;
  /** ���ۿ��� */
  private String prdtYear;
  /** �ٰŸ� */
  private String plot;
  /** �� �ð� */
  private String showTm;
  /** ���� */
  private String repNationNm;
  /** ������ URL */
  private String movieImg="";
  /** ������ ����� */ 
  private String thumb = "";
  /** ������ */
  private long imgSize = 0;
  /** ��ǥ ���� ��� */ 
  private String watchGradeNm;
  /** Ű���� */
  private String keyword;
  /** ���� */
  private String vodclass;
  /** �帣 */
  private String genre;
  /** ���� */
  private String director;
  /** ��� */
  private String actors;
  
  /** 
  Spring Framework���� �ڵ� ���ԵǴ� ���ε� ���� ��ü,
  DBMS �� ���� �÷��� �������� �ʰ� ���� �ӽ� ���� ����.
*/  
private MultipartFile movieImgMF;

/** size1�� �ĸ� ���� ��¿� ����, ���� �÷��� �������� ����. */
private String size1Label;


public MovieVO(){
  
}

public MultipartFile getMovieImgMF() {
  return movieImgMF;
}
public void setMovieImgMF(MultipartFile movieImgMF) {
  this.movieImgMF = movieImgMF;
}
public String getSize1Label() {
  return size1Label;
}
public void setSize1Label(String size1Label) {
  this.size1Label = size1Label;
}
  public String getMovieCd() {
    return movieCd;
  }
  public void setMovieCd(String movieCd) {
    this.movieCd = movieCd;
  }
  public int getMovieno() {
    return movieno;
  }
  public void setMovieno(int movieno) {
    this.movieno = movieno;
  }
  public String getMovieNm() {
    return movieNm;
  }
  public void setMovieNm(String movieNm) {
    this.movieNm = movieNm;
  }
  public String getMovieNmEn() {
    return movieNmEn;
  }
  public void setMovieNmEn(String movieNmEn) {
    this.movieNmEn = movieNmEn;
  }
  public String getOpenDt() {
    return openDt;
  }
  public void setOpenDt(String openDt) {
    this.openDt = openDt;
  }
  public String getPrdtYear() {
    return prdtYear;
  }
  public void setPrdtYear(String prdtYear) {
    this.prdtYear = prdtYear;
  }
  public String getPlot() {
    return plot;
  }
  public void setPlot(String plot) {
    this.plot = plot;
  }
  public String getShowTm() {
    return showTm;
  }
  public void setShowTm(String showTm) {
    this.showTm = showTm;
  }
  public String getRepNationNm() {
    return repNationNm;
  }
  public void setRepNationNm(String repNationNm) {
    this.repNationNm = repNationNm;
  }
  public String getMovieImg() {
    return movieImg;
  }
  public void setMovieImg(String movieImg) {
    this.movieImg = movieImg;
  }
  public String getThumb() {
    return thumb;
  }
  public void setThumb(String thumb) {
    this.thumb = thumb;
  }
  public long getImgSize() {
    return imgSize;
  }
  public void setImgSize(long imgSize) {
    this.imgSize = imgSize;
  }
  public String getWatchGradeNm() {
    return watchGradeNm;
  }
  public void setWatchGradeNm(String watchGradeNm) {
    this.watchGradeNm = watchGradeNm;
  }
  public String getKeyword() {
    return keyword;
  }
  public void setKeyword(String keyword) {
    this.keyword = keyword;
  }
  public String getVodclass() {
    return vodclass;
  }
  public void setVodclass(String vodclass) {
    this.vodclass = vodclass;
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
}
