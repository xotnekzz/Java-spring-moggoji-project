package dev.mvc.director;

public class DirectorVO {
  // ���� ��ȣ
  private int directorno;

  // ���� �̸�
  private String directorNm;

  // ���� �̸� ( ���� )
  private String directorEnNm;

  // ���� �ڵ�
  private String dcode;

  public DirectorVO() {
  }

  public int getDirectorno() {
    return directorno;
  }

  public void setDirectorno(int directorno) {
    this.directorno = directorno;
  }

  public String getDirectorNm() {
    return directorNm;
  }

  public void setDirectorNm(String directorNm) {
    this.directorNm = directorNm;
  }

  public String getDirectorEnNm() {
    return directorEnNm;
  }

  public void setDirectorEnNm(String directorEnNm) {
    this.directorEnNm = directorEnNm;
  }

  public String getDcode() {
    return dcode;
  }

  public void setDcode(String dcode) {
    this.dcode = dcode;
  }

}