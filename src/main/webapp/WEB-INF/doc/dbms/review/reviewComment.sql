1) ���̺� ����

drop table rcomment;

CREATE TABLE rcomment(
    rcno NUMBER NOT NULL, -- �ڸ�Ʈ ��ȣ
    rcdate DATE NOT NULL, -- ��¥
    content VARCHAR(400) NOT NULL, -- ����
    reviewno NUMBER NOT NULL,  -- �����ȣ
    memberno NUMBER  NULL, -- �����ȣ
    mname      VARCHAR2(20)         NOT NULL, --��� �̸�
    PRIMARY KEY (rcno),
    FOREIGN KEY (reviewno) REFERENCES review(reviewno) ON DELETE CASCADE,
    FOREIGN KEY (memberno) REFERENCES member(memberno) ON DELETE CASCADE
);        

COMMENT ON TABLE rcomment is '���� �ڸ�Ʈ';
COMMENT ON COLUMN rcomment.rcno is '���� �ڸ�Ʈ ��ȣ';
COMMENT ON COLUMN rcomment.reviewno is '���� ��ȣ';
COMMENT ON COLUMN rcomment.content is '�ڸ�Ʈ ����';
COMMENT ON COLUMN rcomment.rcdate is '�ڸ�Ʈ �ۼ� ��¥';
COMMENT ON COLUMN rcomment.memberno is '�ڸ�Ʈ�� �� ȸ�� ��ȣ';
COMMENT ON COLUMN rcomment.mname is '�ڸ�Ʈ�� �� ȸ�� �̸�';
     
2) ���̺� ����     
 
DROP TABLE rcomment;

3) ��� ���  

INSERT INTO rcomment(rcno, rcdate, content, reviewno, memberno, mname)
VALUES((SELECT NVL(MAX(rcno), 0)+1 as rcno FROM rcomment), sysdate, '�����ڸ�Ʈ1', 1, 1,'ȸ��');
  
  
4) ��ȸ

select * from rcomment;

SELECT rcno, rcdate, content, reviewno, memberno, mname
FROM rcomment
where reviewno = 4
ORDER BY rcno DESC;

SELECT rcno, rcdate, content, reviewno, memberno, mname
FROM rcomment
WHERE reviewno = 1; 


5) ��� ���� ���ϱ�
  
 SELECT count(*) as cnt
 FROM rcomment
 WHERE reviewno=1;
 
 6) ��� ����¡
 SELECT  rcno, rcdate, content, reviewno, memberno, mname, r
 FROM (
            SELECT rcno, rcdate, content, reviewno, memberno, mname, rownum as r
             FROM (
                        SELECT rcno, rcdate, content, reviewno, memberno, mname
                        FROM rcomment
                        WHERE reviewno = 4
                        ORDER BY rcno DESC
            )
 )
 WHERE r >= 0 AND r <= 10;
 
 7) ��� ����
 DELETE FROM rcomment
 WHERE reviewno = 4 AND rcno = 2 AND memberno = 1;
