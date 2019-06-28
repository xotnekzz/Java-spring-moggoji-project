1. ���̺� ����
DESC member;

CREATE TABLE member(
        memberno                            NUMBER        NOT NULL,
        memail                              VARCHAR2(100)        NOT NULL,
        mname                               VARCHAR2(20)         NOT NULL,
        mpasswd                             VARCHAR2(30)         NULL,
        mfile                               VARCHAR2(100)        NULL,
        msize                               VARCHAR2(20)             NOT NULL,
        mact           						VARCHAR2(1)         DEFAULT 'Y' NOT NULL, -- M OR Y OR N
        mdate								DATE       NOT NULL,
        PRIMARY KEY (memberno)
);

COMMENT ON TABLE member is 'ȸ��';
COMMENT ON COLUMN member.memberno is 'ȸ�� ��ȣ';
COMMENT ON COLUMN member.memail is 'ȸ�� �̸���';
COMMENT ON COLUMN member.mname is 'ȸ�� �̸�';
COMMENT ON COLUMN member.mpasswd is 'ȸ�� ��й�ȣ';
COMMENT ON COLUMN member.mfile is 'ȸ�� �̹��� ����';
COMMENT ON COLUMN member.msize is 'ȸ�� �̹��� ������';
COMMENT ON COLUMN member.mact is 'ȸ�� ����';
COMMENT ON COLUMN member.mdate is 'ȸ�� ���� ��¥';



2. ���̺� ����

DROP TABLE member cascade constraints;
3. INSERT, ���� ������ �߰� 

1) �ߺ� �̸��� �˻� ���� SQL 
    -- 0: �ߺ� �ƴ�, 1: �ߺ�  
    SELECT COUNT(memail) as cnt
    FROM member
    WHERE memail = 'yeji@naver.com';

2) MASTER ������ ��ȸ
    SELECT COUNT(memberno) as cnt
    FROM member
    WHERE mact = 'M';

3) ȸ�� ���

-- ������  mact = 'M'
INSERT INTO member(memberno, memail, mname, mpasswd, mfile, msize, mact, mdate)
VALUES((SELECT NVL(MAX(memberno), 0)+1 as memberno FROM member), 'yeji@naver.com', '����' ,'1234', 'spring.jpg', 1000, 'Y', SYSDATE);

SELECT COUNT(memail) as cnt
        FROM member
        WHERE memail='yeji@naver.com';

4. ���

1) ȸ�� ��ü ��� 
SELECT memberno, memail, mname, mpasswd, mfile, msize, mact, mdate
FROM member;

 MEMBERNO MEMAIL         MNAME MPASSWD MFILE      MSIZE MACT MDATE
 -------- -------------- ----- ------- ---------- ----- ---- ---------------------
        1 yeji@naver.com ����    1234    spring.jpg 1000  Y    2018-06-28 10:10:24.0


        
        
2) ������ ����
UPDATE member
SET mact = 'Y'
WHERE mname = '����';


4.ȸ�� ���� ����
SELECT memberno, memail, mname, mpasswd, mfile, msize, mact
FROM member
WHERE memberno = 1;

 MEMBERNO MEMAIL         MNAME MPASSWD MFILE      MSIZE MACT
 -------- -------------- ----- ------- ---------- ----- ----
        1 yeji@naver.com ����    1234    spring.jpg 1000  Y


SELECT memberno, memail, mname, mpasswd, mfile, msize, mact
FROM member
WHERE mname = '����';

 MEMBERNO MEMAIL         MNAME MPASSWD MFILE      MSIZE MACT
 -------- -------------- ----- ------- ---------- ----- ----
        1 yeji@naver.com ����    1234    spring.jpg 1000  Y

        
-- ���� �Ϻκ�
SELECT mname, memail
FROM member
ORDER BY memail ASC;

 mname memail         
 ----- -------------- 
 ����    yeji@naver.com
 
5. �н����� ����
1) ���� �н����� �˻�
- DAO: public int countmpasswd(int memberno, String mpasswd){ ... }
SELECT count(*) as cnt
FROM member
WHERE memberno = 1 AND mpasswd='123';
 cnt = 0 -> ��й�ȣ Ʋ�� / cnt = 1 -> ��й�ȣ ����

 
2) �н����� ����
- DAO: public int updatempasswd(int memberno, String mpasswd){ ... }
UPDATE member
SET mpasswd=''
WHERE memberno=1;


6.  ȸ�� ���� ���� 
UPDATE member
SET mname='2', mfile = '2', mpasswd = '2', msize = '2'
WHERE memberno = 2;

 
7. 'member' ȸ�� ���� 
DELETE FROM member;
 
DELETE FROM member
WHERE memberno = 1;
 

8. �α��� ���� SQL 
- DAO: public int login(String memail, String mpasswd){ ... }
 
- �α��� üũ
SELECT count(*) as cnt
FROM member
WHERE memail = 'yeji@naver.com' AND mpasswd='1234';
 
- memail ������ �̿��� ��ȸ
SELECT memberno, memail, mpasswd, mact
FROM member
WHERE memail = 'yeji@naver.com';

SELECT count(*)
FROM member
WHERE memail = 'yeji@naver.com';
 
     
9. �˻��� ��ü ���ڵ� ��
   - LIKE    : ��Ȯ�ϰ� ��ġ���� �ʾƵ� ��� 
   - =(equal): ��Ȯ�ϰ� ��ġ�ؾ� ��� 
   - �˻��� ���� �ʴ� ���, ��ü ��� ��� 
   
- ȸ�� ���
SELECT memberno, memail, mname, mfile, mact, mdate
FROM member
ORDER BY memberno ASC;W

 MEMBERNO MEMAIL         MNAME MFILE      MACT MDATE
 -------- -------------- ----- ---------- ---- ---------------------
        1 yeji@naver.com ����    spring.jpg Y    2018-06-28 10:10:24.0
        
        
        
update member
set mact = 'M'
where memberno = 1;

- �˻�
select memberno, memail, mname, mfile, mact, mdate
from member
where memberno like '%2%';

select memberno, memail, mname, mfile, mact, mdate
from member
where memail like '%y%';



-- ����¡ + �˻�

SELECT memberno, memail, mname, mfile, mact, mdate, r
FROM (
          SELECT memberno, memail, mname, mfile, mact, mdate, rownum as r
          FROM (
                    SELECT memberno, memail, mname, mfile, mact, mdate
                     FROM member
                     WHERE memberno = 1 AND mname LIKE '%��%'
                     ORDER BY memberno DESC
          )
)
WHERE r >=1 AND R<=10;

select *
from member
where memberno = 1;

update member
set mact = 'M'
where memberno = 1;