package dev.mvc.movie;

//��ȭ ���ƿ� ��ư
public class MovielikeVO {
  
  /** ���ƿ� ��ȣ */
  private int likeno;
  /** ��ȭ �ڵ� */
  private String movieCd;
  /** ȸ�� ��ȣ */
  private int memberno;
  /** ���ƿ� üũ */
  private int like_check;

  public int getLikeno() {
    return likeno;
  }
  public void setLikeno(int likeno) {
    this.likeno = likeno;
  }
  public String getMovieCd() {
    return movieCd;
  }
  public void setMovieCd(String movieCd) {
    this.movieCd = movieCd;
  }
  public int getMemberno() {
    return memberno;
  }
  public void setMemberno(int memberno) {
    this.memberno = memberno;
  }
  public int getLike_check() {
    return like_check;
  }
  public void setLike_check(int like_check) {
    this.like_check = like_check;
  }
  
  

}
