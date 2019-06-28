1) ���̺� ����

CREATE TABLE rcomment_reply(
    rcrno NUMBER NOT NULL,
    rcrdate DATE NOT NULL,
    content VARCHAR(400) NOT NULL,
    rcno NUMBER NOT NULL, 
    memberno NUMBER NOT NULL, 
    PRIMARY KEY (rcrno),
    FOREIGN KEY (rcno) REFERENCES rcomment(rcno) ON DELETE CASCADE,
    FOREIGN KEY (memberno) REFERENCES member(memberno) ON DELETE CASCADE
);         
     
2) ���̺� ����     

DROP TABLE rcomment_reply;

3) ��� ���  

INSERT INTO rcomment_reply(rcrno, rcrdate, content, rcno, memberno)
VALUES((SELECT NVL(MAX(rcrno), 0)+1 as rcrno FROM rcomment_reply), sysdate, '�����׽�Ʈ1', 1, 1);
  
4) ��ȸ

SELECT rcrno, rcrdate, content, rcno, memberno
FROM rcomment_reply
WHERE rcno = 1; 
 

5) ��� ���� ���ϱ�
  
 SELECT count(*) as cnt
 FROM rcomment_reply
 WHERE rcno=1;
