package dev.mvc.review;

//���並 �� ȸ�� �̸�
public class MnameVO {

  /** ȸ�� ��ȣ */
  private int memberno;
  /** ȸ�� �̸� */
  private String mname;
  /** ȸ�� �̸���  */
  private String memail;
  /** ���� ��ȣ */
  private int reviewno;
  
 
  public MnameVO() {
   
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
 
  public String getMemail() {
    return memail;
  }

  public void setMemail(String memail) {
    this.memail = memail;
  }

  public int getReviewno() {
    return reviewno;
  }

  public void setReviewno(int reviewno) {
    this.reviewno = reviewno;
  }
  
  
}
