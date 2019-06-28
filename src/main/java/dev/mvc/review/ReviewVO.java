package dev.mvc.review;

import org.springframework.web.multipart.MultipartFile;

public class ReviewVO {
  /* ���� ��ȣ */
  private int reviewno;
  /* �� ���� */
  private String title;
  /* ���� */
  private String content;
  /* ����� */
  private String rdate;
  /* ��ǥ �̹��� */
  private String mainimg="";
  /* ������ */
  private long imgsize=0;
  /* ���� ���� */
  private String youtube;
  /* ��ȸ�� */
  private int cnt; 
  /* ��� ���� */
  private char visible;
  /* ���� ���� */
  private String review_rating;
  /* ȸ�� ��ȣ(FK) */
  private int memberno;
  /* ȸ�� �̸�*/
  private String mname;
  
  /** 
  Spring Framework���� �ڵ� ���ԵǴ� ���ε� ���� ��ü,
  DBMS �� ���� �÷��� �������� �ʰ� ���� �ӽ� ���� ����.
*/  
private MultipartFile file1MF;

/** size1�� �ĸ� ���� ��¿� ����, ���� �÷��� �������� ����. */
private String size1Label;



public MultipartFile getFile1MF() {
  return file1MF;
}
public void setFile1MF(MultipartFile file1mf) {
  file1MF = file1mf;
}
public String getSize1Label() {
  return size1Label;
}
public void setSize1Label(String size1Label) {
  this.size1Label = size1Label;
}


  public int getReviewno() {
    return reviewno;
  }
  public void setReviewno(int reviewno) {
    this.reviewno = reviewno;
  }
  public String getTitle() {
    return title;
  }
  public void setTitle(String title) {
    this.title = title;
  }
  public String getContent() {
    return content;
  }
  public void setContent(String content) {
    this.content = content;
  }
  public String getRdate() {
    return rdate;
  }
  public void setRdate(String rdate) {
    this.rdate = rdate;
  }
  public String getMainimg() {
    return mainimg;
  }
  public void setMainimg(String mainimg) {
    this.mainimg = mainimg;
  }
  public long getImgsize() {
    return imgsize;
  }
  public void setImgsize(long imgsize) {
    this.imgsize = imgsize;
  }
  public String getYoutube() {
    return youtube;
  }
  public void setYoutube(String youtube) {
    this.youtube = youtube;
  }
  public int getCnt() {
    return cnt;
  }
  public void setCnt(int cnt) {
    this.cnt = cnt;
  }
  public char getVisible() {
    return visible;
  }
  public void setVisible(char visible) {
    this.visible = visible;
  }
  public String getReview_rating() {
    return review_rating;
  }
  public void setReview_rating(String review_rating) {
    this.review_rating = review_rating;
  }
  public int getMemberno() {
    return memberno;
  }
  public void setMemberno(int memberno) {
    this.memberno = memberno;
  }
  public String getMname() {
    return mname;
  }
  public void setMname(String mname) {
    this.mname = mname;
  }
   
  
}
