package dev.mvc.review.comment;



public class RcommentVO {
  
  /** �ڸ�Ʈ ��ȣ */
  private int rcno;
  /** �ڸ�Ʈ ���� */
  private String content;
  /** ���� ��ȣ */
  private int reviewno;
  /** �����ȣ */
  private int memberno;
  /** ��� �̸� */
  private String mname;
  /** �ڸ�Ʈ �� ��¥*/
  private String rcdate;
  
  public int getRcno() {
    return rcno;
  }
  public void setRcno(int rcno) {
    this.rcno = rcno;
  }
  public String getContent() {
    return content;
  }
  public void setContent(String content) {
    this.content = content;
  }
  public int getReviewno() {
    return reviewno;
  }
  public void setReviewno(int reviewno) {
    this.reviewno = reviewno;
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
  public String getRcdate() {
    return rcdate;
  }
  public void setRcdate(String rcdate) {
    this.rcdate = rcdate;
  }
  
  

}
