package dev.mvc.member;

import org.springframework.web.multipart.MultipartFile;

public class MemberVO {
    // ȸ�� ��ȣ.
    private int memberno;   
    
    // ȸ�� �̸���.
    private String memail;
    
    // ȸ�� ��й�ȣ.
    private String mpasswd;
    
    // ȸ�� �̸�.
    private String mname;
    
    // ȸ�� �̹���.
    private String mfile="";
    
    // ȸ�� �̹��� ũ��.
    private long msize=0;
    
    //ȸ�� ����
    private String mact;
    
    // ȸ�� �����
    private String mdate;
    
    /** 
    Spring Framework���� �ڵ� ���ԵǴ� ���ε� ���� ��ü,
    DBMS �� ���� �÷��� �������� �ʰ� ���� �ӽ� ���� ����.
         �ϳ��� ���� ���ε�
    */
    private MultipartFile fileMF;
    
    
    // VO ������.
    public MemberVO() {
        //System.out.println("Member ��ü ������ ����Ǿ���. ");
    }
    
    
    public int getMemberno() {
        return memberno;
    }
    public void setMemberno(int memberno) {
        this.memberno = memberno;
    }
    public String getMemail() {
        return memail;
    }
    public void setMemail(String memail) {
        this.memail = memail;
    }
    public String getMpasswd() {
        return mpasswd;
    }
    public void setMpasswd(String mpasswd) {
        this.mpasswd = mpasswd;
    }
    public String getMname() {
        return mname;
    }
    public void setMname(String mname) {
        this.mname = mname;
    }
    public String getMfile() {
        return mfile;
    }
    public void setMfile(String mfile) {
        this.mfile = mfile;
    }
    public long getMsize() {
      return msize;
    }
    public void setMsize(long msize) {
      this.msize = msize;
    }
    public String getMact() {
      return mact;
    }
    public void setMact(String mact) {
      this.mact = mact;
    }
    public String getMdate() {
      return mdate;
    }
    public void setMdate(String mdate) {
      this.mdate = mdate;
    }
    public MultipartFile getFileMF() {
      return fileMF;
    }
    public void setFileMF(MultipartFile fileMF) {
      this.fileMF = fileMF;
    }
    
    
}
