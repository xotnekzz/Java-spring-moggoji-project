package dev.mvc.movie.comment;

//ȸ�� ��ȭ ��� ���
public class MovieNmVO {
  
  /** ��� ��ȣ */
  private int mcno;
  /** ��¥ */
  private String rdate;
  /** ȸ�� ��ȣ */
  private int memberno;
  /** ��� */
  private String content;
  /** ��ȭ �ڵ�*/
  private String movieCd;
  /** ���� */
  private int grade;
  /** ��ȭ���� */
  private String movieNm;
  /** ������ ����� */ 
  private String thumb="";

  public int getMcno() {
    return mcno;
  }
  public void setMcno(int mcno) {
    this.mcno = mcno;
  }
  public String getRdate() {
    return rdate;
  }
  public void setRdate(String rdate) {
    this.rdate = rdate;
  }
  public int getMemberno() {
    return memberno;
  }
  public void setMemberno(int memberno) {
    this.memberno = memberno;
  }
  public String getContent() {
    return content;
  }
  public void setContent(String content) {
    this.content = content;
  }
  public String getMovieCd() {
    return movieCd;
  }
  public void setMovieCd(String movieCd) {
    this.movieCd = movieCd;
  }
  public int getGrade() {
    return grade;
  }
  public void setGrade(int grade) {
    this.grade = grade;
  }
  public String getMovieNm() {
    return movieNm;
  }
  public void setMovieNm(String movieNm) {
    this.movieNm = movieNm;
  }
  public String getThumb() {
    return thumb;
  }
  public void setThumb(String thumb) {
    this.thumb = thumb;
  }

}
