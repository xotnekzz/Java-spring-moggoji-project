
SELECT * FROM USER_OBJECTS WHERE OBJECT_TYPE='TABLE';

DROP TABLE mcomment;

CREATE TABLE mcomment(
  mcno  NUMBER(7)        NOT NULL    PRIMARY KEY,
  rdate          DATE                 NOT NULL,
  memberno   NUMBER(7)        NOT NULL,
  content       VARCHAR(300)    NOT NULL,
  movieCd     VARCHAR2(1000)     NOT NULL,
  grade         NUMBER(5)   DEFAULT 0 NOT NULL,
  mname      VARCHAR2(20)         NOT NULL, 
  FOREIGN KEY (memberno) REFERENCES MEMBER (memberno),
  FOREIGN KEY (movieCd) REFERENCES MOVIE (movieCd)
);

ALTER TABLE mcomment MODIFY(grade NUMBER(5));


COMMENT ON TABLE mcomment is '��ȭ �ڸ�Ʈ';
COMMENT ON COLUMN mcomment.mcno is '�ڸ�Ʈ ��ȣ';
COMMENT ON COLUMN mcomment.movieCd is '��ȭ �ڵ�';
COMMENT ON COLUMN mcomment.content is '�ڸ�Ʈ ����';
COMMENT ON COLUMN mcomment.grade is '��ȭ ���';
COMMENT ON COLUMN mcomment.rdate is '�ڸ�Ʈ �ۼ� ��¥';
COMMENT ON COLUMN mcomment.memberno is '�ڸ�Ʈ�� �� ȸ�� ��ȣ';
COMMENT ON COLUMN mcomment.mname is '�ڸ�Ʈ�� �� ȸ�� �̸�';

-- ��ȸ
select * from mcomment;

SELECT mcno, rdate, grade, content, movieCd, memberno, mname
FROM mcomment
WHERE movieCd = '20171841'
ORDER BY mcno DESC;

-- ���
INSERT INTO mcomment(mcno, rdate, grade, content, movieCd, memberno, mname )
VALUES((SELECT NVL(MAX(mcno), 0)+1 as mcno FROM mcomment), sysdate, 3,
            '�������̾���.', '20171841', 1, 'ȸ��' );

-- ����
UPDATE mcomment
SET content = '��̾���.' , grade = '1'
WHERE movieCd = '20171841' AND memberno = 1 AND mcno = 1;

-- ����

delete from mcomment;

DELETE FROM mcomment
WHERE movieCd = '20171841'  AND memberno = 1 AND mcno = 1;

-- ��ȭ�� ��� ����
select count(*) as cnt
from mcomment
where movieCd = '20171841'


-- ȸ���� ��ȭ ��� ����
    SELECT count(*) as cnt
    FROM movie m, mcomment mc
    WHERE m.movieCd = mc.movieCd AND memberno = 2 AND m.movieNm LIKE '%��%';
    
    
-- ȸ���� ��ȭ ��� ���
    SELECT mcno, movieCd, content, grade, rdate, r
    FROM (
              SELECT mcno, movieCd, content, grade, rdate, rownum as r
               FROM (
                          SELECT mcno, movieCd, content, grade, rdate
                          FROM  mcomment
                          WHERE memberno = 2
                          ORDER BY mcno DESC
              )
    )
    where r >=1 and r<=10;
    

-- ȸ���� ��ȭ ��� ��ȭ����(join) + ����¡
SELECT mcno, movieCd, movieNm, content, grade, rdate, memberno, thumb, r
FROM(
       SELECT  mcno, movieCd, movieNm, content, grade, rdate, memberno, thumb, rownum as r
       FROM(
               SELECT mc.mcno, mc.movieCd, m.movieNm, mc.content, mc.grade, mc.rdate, mc.memberno, m.thumb
               FROM movie m, mcomment mc
               WHERE m.movieCd = mc.movieCd AND memberno = 3 AND m.movieNm LIKE '%��%'
               ORDER BY mc.mcno DESC
        )
  )
WHERE r >=1 AND r<=10;


-- ȸ���� ��ȭ ��� ��ȭ����(join)
select mc.mcno, mc.movieCd, m.movieNm, mc.content, mc.grade, mc.rdate, rownum
from movie m, mcomment mc
where m.movieCd = mc.movieCd and memberno = 2
order by mc.mcno desc;

-- ��� ����
DELETE FROM mcomment
WHERE movieCd='20185568' AND memberno = 2;

-- ��� ����
UPDATE mcomment
SET grade=4, content='ss'
WHERE movieCd='20171841' AND memberno = 2;

-- ��� �� �� ��ȸ
select mcno, movieCd, grade, content
from MCOMMENT
where movieCd='20171841' AND memberno = 2;

    SELECT mc.mcno, mc.movieCd, m.movieNm, mc.grade, mc.content
    FROM mcomment mc, movie m
    where m.movieCd = mc.movieCd AND mc.movieCd = '20171841' AND mc.memberno = 2;

-- ��ȭ�� ȸ���� ��� ����
SELECT count(*)
FROM mcomment
WHERE movieCd='20171841' and memberno=2;


-- �� ��ȭ�� ���� ���� 
SELECT sum(grade)
FROM mcomment
WHERE movieCd='20171841';






