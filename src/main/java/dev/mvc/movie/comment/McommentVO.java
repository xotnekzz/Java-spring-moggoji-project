package dev.mvc.movie.comment;

public class McommentVO {

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
  private String grade;
  /** ȸ�� �̸� */
  private String mname;

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
  public String getGrade() {
    return grade;
  }
  public void setGrade(String grade) {
    this.grade = grade;
  }
  public String getMname() {
    return mname;
  }
  public void setMname(String mname) {
    this.mname = mname;
  }
  
  
  
}
